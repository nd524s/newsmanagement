package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.domain.SearchCriteria;
import com.epam.newsmanagement.domain.Tag;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Никита on 27.05.2016.
 */

public class NewsDAOImpl implements NewsDAO {
    private static final String SQL_CREATE_NEWS = "INSERT INTO NEWS(TITLE, SHORT_TEXT," +
                       " FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE_NEWS = "UPDATE NEWS SET TITLE=?, SHORT_TEXT=?, " +
                        "FULL_TEXT=?, CREATION_DATE=?, MODIFICATION_DATE=? WHERE NEWS_ID=?";
    private static final String SQL_DELETE_NEWS = "DELETE FROM NEWS WHERE NEWS_ID=?";
    private static final String SQL_GET_NEWS_BY_ID = "SELECT NEWS_ID, TITLE, SHORT_TEXT," +
                        " FULL_TEXT, CREATION_DATE, MODIFICATION_DATE FROM NEWS WHERE NEWS_ID=?";
    private static final String SQL_GET_ALL_NEWS = "SELECT N.NEWS_ID, N.TITLE, N.SHORT_TEXT," +
                        " N.FULL_TEXT, N.CREATION_DATE, N.MODIFICATION_DATE, COUNT(C.COMMENT_ID) AS NUM " +
                        "FROM NEWS N LEFT JOIN COMMENTS C ON N.NEWS_ID = C.NEWS_ID GROUP BY" +
                        " N.NEWS_ID, N.TITLE, N.SHORT_TEXT, N.FULL_TEXT, N.CREATION_DATE, N.MODIFICATION_DATE ORDER BY NUM DESC";
    private static final String SQL_GET_NEWS_COUNT = "SELECT COUNT(*) AS ROW_COUNT FROM NEWS";
    private DataSource dataSource;

    public NewsDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long getNewsCount() throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        long rowCount = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_NEWS_COUNT);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                rowCount = resultSet.getLong("ROW_COUNT");
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get count of rows: ", e);
        }
        return rowCount;
    }

    /**
     * Search News by author and tags combination.
     * @param criteria
     * @return List of news
     * @throws DAOException
     */
    @Override
    public ArrayList<News> getNewsBySearchCriteria(SearchCriteria criteria) throws DAOException {
        ArrayList<Tag> tags = criteria.getTags();
        Author author = criteria.getAuthor();
        ArrayList<News> news = new ArrayList<>();

        if (tags.isEmpty() && author != null) {
            news = getNews(buildQueryByAuthor(author));
        } else if (!tags.isEmpty() && author == null) {
            news = getNews(buildQueryByTags(tags));
        } else if (!tags.isEmpty() && author != null) {
            news = getNews(buildQueryByTagsAndAuthor(tags, author));
        }
        return news;
    }

    /**
     * Build query for search by tags.
     * @param tags
     * @return String query.
     */
    private String buildQueryByTags(ArrayList<Tag> tags) {
        StringBuilder builder = new StringBuilder("SELECT DISTINCT N.NEWS_ID, N.TITLE, N.SHORT_TEXT, N.FULL_TEXT, N.CREATION_DATE, N.MODIFICATION_DATE " +
                "FROM NEWS N INNER JOIN NEWS_TAG N_T ON N.NEWS_ID = N_T.NEWS_ID WHERE TAG_ID IN(");
        for (int i = 0; i < tags.size(); i++) {
            if(tags.get(i).getTagId() == tags.get(tags.size()-1).getTagId()) {
                builder.append(tags.get(i).getTagId() + ")");
            } else {
                builder.append(tags.get(i).getTagId() + ",");
            }
        }
        return builder.toString();
    }

    /**
     * Build query for search by author.
     * @param author
     * @return String query.
     */
    private String buildQueryByAuthor(Author author) {
        StringBuilder builder = new StringBuilder("SELECT N.NEWS_ID, N.TITLE, N.SHORT_TEXT, N.FULL_TEXT, N.CREATION_DATE, N.MODIFICATION_DATE " +
                "FROM NEWS N INNER JOIN NEWS_AUTHOR N_A ON N.NEWS_ID = N_A.NEWS_ID WHERE AUTHOR_ID=");
        return builder.append(author.getAuthorId()).toString();
    }

    /**
     * Build query for search by author and tags.
     * @param tags
     * @param author
     * @return String query.
     */
    private String buildQueryByTagsAndAuthor(ArrayList<Tag> tags, Author author) {
        StringBuilder builder = new StringBuilder("SELECT N.NEWS_ID, N.TITLE, N.SHORT_TEXT, N.FULL_TEXT, N.CREATION_DATE, N.MODIFICATION_DATE " +
                "FROM NEWS N INNER JOIN NEWS_AUTHOR N_A ON N.NEWS_ID = N_A.NEWS_ID INNER JOIN NEWS_TAG N_T " +
                "ON N.NEWS_ID=N_T.NEWS_ID WHERE TAG_ID IN (");
        for (int i = 0; i < tags.size(); i++) {
            if(tags.get(i).getTagId() == tags.get(tags.size()-1).getTagId()) {
                builder.append(tags.get(i).getTagId() + ")");
            } else {
                builder.append(tags.get(i).getTagId() + ",");
            }
        }
        builder.append(" AND AUTHOR_ID=" + author.getAuthorId());
        return builder.toString();
    }

    private ArrayList<News> getNews(String query) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        ArrayList<News> newsList = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                long newsId = resultSet.getLong("NEWS_ID");
                String title = resultSet.getString("TITLE");
                String shortText = resultSet.getString("SHORT_TEXT");
                String fullText = resultSet.getString("FULL_TEXT");
                Timestamp creationDate = resultSet.getTimestamp("CREATION_DATE");
                Timestamp modificationDate = resultSet.getTimestamp("MODIFICATION_DATE");
                newsList.add(new News(newsId, title, shortText, fullText, creationDate, modificationDate));
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get list of news: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return newsList;
    }

    @Override
    public ArrayList<News> getAllNews() throws DAOException {
        return getNews(SQL_GET_ALL_NEWS);
    }

    @Override
    public Long create(News item) throws DAOException {
        Long newsId = null;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_NEWS, new String[] { "NEWS_ID" })) {
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setString(2, item.getShortText());
            preparedStatement.setString(3, item.getFullText());
            preparedStatement.setTimestamp(4, item.getCreationDate());
            preparedStatement.setTimestamp(5, item.getModificationDate());
            preparedStatement.executeUpdate();
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys != null && generatedKeys.next()) {
                    newsId = generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not create news: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return newsId;
    }

    @Override
    public News getById(long id) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        News news = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_NEWS_BY_ID)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long newsId = resultSet.getLong("NEWS_ID");
                    String title = resultSet.getString("TITLE");
                    String shortText = resultSet.getString("SHORT_TEXT");
                    String fullText = resultSet.getString("FULL_TEXT");
                    Timestamp creationDate = resultSet.getTimestamp("CREATION_DATE");
                    Timestamp modificationDate = resultSet.getTimestamp("MODIFICATION_DATE");
                    news = new News(newsId, title, shortText, fullText, creationDate, modificationDate);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get news: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return news;
    }

    @Override
    public void update(News item) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_NEWS)) {
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setString(2, item.getShortText());
            preparedStatement.setString(3, item.getFullText());
            preparedStatement.setTimestamp(4, item.getCreationDate());
            preparedStatement.setTimestamp(5, item.getModificationDate());
            preparedStatement.setLong(6, item.getNewsId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Can not update news: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
    }

    @Override
    public void delete(long id) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_NEWS)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Can not delete news by ID: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
    }

}

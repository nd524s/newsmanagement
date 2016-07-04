package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Никита on 26.05.2016.
 */

public class AuthorDAOImpl implements AuthorDAO {
    private static final String SQL_CREATE_AUTHOR = "INSERT INTO AUTHOR(AUTHOR_NAME, EXPIRED)" +
                                                    " VALUES(?, ?)";
    private static final String SQL_GET_NEWS_AUTHOR = "SELECT A.AUTHOR_ID, A.AUTHOR_NAME, A.EXPIRED FROM AUTHOR A INNER JOIN" +
                                                      " NEWS_AUTHOR N_A ON A.AUTHOR_ID = N_A.AUTHOR_ID WHERE N_A.NEWS_ID=?";
    private static final String SQL_CREATE_NEWS_AUTHOR = "INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID)" +
                                                      " VALUES(?,?)";
    private static final String SQL_GET_ALL_AUTHORS = "SELECT AUTHOR_ID, AUTHOR_NAME, EXPIRED" +
                                                    " FROM AUTHOR";

    private DataSource dataSource;

    public AuthorDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ArrayList<Author> getAllAuthors() throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        ArrayList<Author> authors = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_AUTHORS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                long authorId = resultSet.getLong("AUTHOR_ID");
                String authorName = resultSet.getString("AUTHOR_NAME");
                Timestamp expired = resultSet.getTimestamp("EXPIRED");
                authors.add(new Author(authorId, authorName, expired));
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get all authors", e);
        }finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return authors;
    }

    /**
     * Create record in NEWS_AUTHOR table.
     * @param newsId
     * @param authorId
     * @throws DAOException
     */
    @Override
    public void createNewsAuthor(long newsId, long authorId) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_NEWS_AUTHOR)) {
            preparedStatement.setLong(1, newsId);
            preparedStatement.setLong(2, authorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Can not insert into NEWS_AUTHOR table: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
    }

    /**
     *
     * @param newsId
     * @return List of authors that belong to current author.
     * @throws DAOException
     */
    @Override
    public ArrayList<Author> getNewsAuthor(long newsId) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        ArrayList<Author> authors = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_NEWS_AUTHOR)) {
            preparedStatement.setLong(1, newsId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long authorId = resultSet.getLong("AUTHOR_ID");
                    String authorName = resultSet.getString("AUTHOR_NAME");
                    Timestamp expired = resultSet.getTimestamp("EXPIRED");
                    authors.add(new Author(authorId, authorName, expired));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get authors of current news: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return authors;
    }

    @Override
    public Long create(Author author) throws DAOException {
        Long authorId = null;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_AUTHOR,  new String[] { "AUTHOR_ID" })){
            preparedStatement.setString(1, author.getAuthorName());
            preparedStatement.setTimestamp(2,author.getExpired());
            preparedStatement.executeUpdate();
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys != null && generatedKeys.next()) {
                    authorId = generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not create author: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return authorId;
    }

    @Override
    public Author getById(long id) {
        return null;
    }

    @Override
    public void update(Author item) {

    }

    @Override
    public void delete(long id) {

    }

}

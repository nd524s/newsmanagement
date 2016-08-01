package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Tag;
import oracle.jdbc.proxy.annotation.Pre;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Никита on 6/2/2016.
 */
public class TagDAOImpl implements TagDAO {
    private static final String SQL_GET_NEWS_TAGS = "SELECT T.TAG_ID, T.TAG_NAME FROM TAG T INNER JOIN" +
                                                    " NEWS_TAG N_T ON T.TAG_ID = N_T.TAG_ID WHERE N_T.NEWS_ID=?";
    private static final String SQL_CREATE_NEWS_TAG = "INSERT INTO NEWS_TAG(NEWS_ID, TAG_ID)" +
                                                    " VALUES(?,?)";
    private static final String SQL_GET_ALL_TAGS = "SELECT TAG_ID, TAG_NAME FROM TAG";
    private static final String SQL_DELETE_TAG = "DELETE FROM TAG WHERE TAG_ID=?";
    private static final String SQL_UPDATE_TAG = "UPDATE TAG SET TAG_NAME=? WHERE TAG_ID=?";
    private static final String SQL_CREATE_TAG = "INSERT INTO TAG(TAG_NAME) VALUES(?)";

    private DataSource dataSource;

    public TagDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ArrayList<Tag> getAllTags() throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        ArrayList<Tag> tags = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_TAGS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long tagId = resultSet.getLong("TAG_ID");
                    String tagName = resultSet.getString("TAG_NAME");
                    tags.add(new Tag(tagId, tagName));
                }
        } catch (SQLException e) {
            throw new DAOException("Can not get all tags", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return tags;
    }

        /**
     * Create new record in NEWS_TAG table.
     * @param newsId
     * @param tagId
     * @throws DAOException
     */
    @Override
    public void createNewsTag(long newsId, long tagId) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_NEWS_TAG)) {
            preparedStatement.setLong(1, newsId);
            preparedStatement.setLong(2, tagId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Can not insert into NEWS_TAG table: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
    }

    @Override
    public ArrayList<Tag> getNewsTags(long newsId) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        ArrayList<Tag> tags = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_NEWS_TAGS)) {
            preparedStatement.setLong(1, newsId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long tagId = resultSet.getLong("TAG_ID");
                    String tagName = resultSet.getString("TAG_NAME");
                    tags.add(new Tag(tagId, tagName));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get tags of current news: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return tags;
    }

    @Override
    public Long create(Tag item) throws DAOException {
        Long tagId = null;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_TAG, new String[] { "TAG_ID" })) {
            preparedStatement.setString(1, item.getTagName());
            preparedStatement.executeUpdate();
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys != null && generatedKeys.next()) {
                    tagId = generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not create tag: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return tagId;
    }

    @Override
    public Tag getById(long id) throws DAOException {
        return null;
    }

    @Override
    public void update(Tag item) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TAG)) {
            preparedStatement.setString(1, item.getTagName());
            preparedStatement.setLong(2, item.getTagId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Can not update tag: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
    }

    @Override
    public void delete(long id) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TAG)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Can not delete tag by id");
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }

    }
}

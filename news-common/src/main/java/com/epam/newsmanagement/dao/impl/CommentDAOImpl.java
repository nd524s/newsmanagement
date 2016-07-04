package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Comment;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Никита on 6/1/2016.
 */
public class CommentDAOImpl implements CommentDAO {
    private static final String SQL_GET_NEWS_COMMENTS = "SELECT COMMENT_ID, NEWS_ID, COMMENT_TEXT," +
                                                        " CREATION_DATE FROM COMMENTS WHERE NEWS_ID=? ORDER BY CREATION_DATE DESC";
    private static final String SQL_CREATE_COMMENT = "INSERT INTO COMMENTS(NEWS_ID,COMMENT_TEXT,CREATION_DATE) VALUES (?,?,?)";
    private static final String SQL_DELETE_COMMENT = "DELETE FROM COMMENTS WHERE COMMENT_ID=?";
    private static final String SQL_GET_COMMENT_BY_ID = "SELECT COMMENT_ID, NEWS_ID, COMMENT_TEXT," +
                                                        " CREATION_DATE FROM COMMENTS WHERE COMMENT_ID=?";
    private DataSource dataSource;

    public CommentDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     *
     * @param newsId
     * @return List of comments that belongs to current news.
     * @throws DAOException
     */
    @Override
    public ArrayList<Comment> getNewsComments(long newsId) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        ArrayList<Comment> comments = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_NEWS_COMMENTS)) {
            preparedStatement.setLong(1, newsId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    long commentId = resultSet.getLong("COMMENT_ID");
                    long newId = resultSet.getLong("NEWS_ID");
                    String commentText = resultSet.getString("COMMENT_TEXT");
                    Timestamp creationDate = resultSet.getTimestamp("CREATION_DATE");
                    comments.add(new Comment(commentId, newId, commentText, creationDate));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get comments of current news: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return comments;
    }

    @Override
    public Long create(Comment item) throws DAOException {
        Long commentId = null;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_COMMENT, new String[] { "COMMENT_ID" })) {
            preparedStatement.setLong(1, item.getNewsId());
            preparedStatement.setString(2, item.getCommentText());
            preparedStatement.setTimestamp(3, item.getCreationDate());
            preparedStatement.executeUpdate();
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys != null && generatedKeys.next()) {
                    commentId = generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not create new comment: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return commentId;
    }

    @Override
    public Comment getById(long id) throws DAOException {
        Comment comment = null;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COMMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long commentId = resultSet.getLong("COMMENT_ID");
                    long newId = resultSet.getLong("NEWS_ID");
                    String commentText = resultSet.getString("COMMENT_TEXT");
                    Timestamp creationDate = resultSet.getTimestamp("CREATION_DATE");
                    comment = new Comment(commentId, newId, commentText, creationDate);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get comment by id: ", e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
        return comment;
    }

    @Override
    public void update(Comment item) throws DAOException {

    }

    @Override
    public void delete(long id) throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENT)){
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

package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.service.CommentService;
import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.domain.Comment;
import com.epam.newsmanagement.service.exception.ServiceException;
import org.apache.log4j.Logger;

/**
 * Created by Никита on 6/3/2016.
 */
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = Logger.getLogger(CommentServiceImpl.class);
    private CommentDAO commentDAO;

    public CommentServiceImpl() {
    }

    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public void deleteComment(long commentId) throws ServiceException {
        try {
            commentDAO.delete(commentId);
        } catch (DAOException e) {
            logger.error("Can not delete this comment.", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Long createComment(Comment comment) throws ServiceException {
        Long commentId;
        try {
            commentId = commentDAO.create(comment);
        } catch (DAOException e) {
            logger.error("Can not create comment.", e);
            throw new ServiceException(e);
        }
        return commentId;
    }
}

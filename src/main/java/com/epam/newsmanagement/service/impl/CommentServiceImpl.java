package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Comment;
import com.epam.newsmanagement.service.CommentService;
import com.epam.newsmanagement.service.exception.ServiceException;

/**
 * Created by Никита on 6/3/2016.
 */
public class CommentServiceImpl implements CommentService {
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
            throw new ServiceException(e);
        }
    }

    @Override
    public Long createComment(Comment comment) throws ServiceException {
        Long commentId;
        try {
            commentId = commentDAO.create(comment);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return commentId;
    }
}

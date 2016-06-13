package com.epam.newsmanagement.service;

import com.epam.newsmanagement.domain.Comment;
import com.epam.newsmanagement.service.exception.ServiceException;

/**
 * Created by Никита on 6/3/2016.
 */
public interface CommentService {
    void deleteComment(long commentId) throws ServiceException;
    Long createComment(Comment comment) throws ServiceException;
}

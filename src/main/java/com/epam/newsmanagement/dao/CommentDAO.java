package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Comment;

import java.util.ArrayList;

/**
 * Created by Никита on 6/1/2016.
 */
public interface CommentDAO extends GenericDAO<Comment> {
    ArrayList<Comment> getNewsComments(long newsId) throws DAOException;
}

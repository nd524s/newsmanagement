package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Author;

import java.util.ArrayList;

/**
 * Created by Никита on 26.05.2016.
 */
public interface AuthorDAO extends GenericDAO<Author> {
    ArrayList<Author> getNewsAuthor(long newsId) throws DAOException;
    void createNewsAuthor(long newsId, long authorId) throws DAOException;
    ArrayList<Author> getAllAuthors() throws DAOException;
    void deleteNewsAuthor(long id) throws DAOException;
}

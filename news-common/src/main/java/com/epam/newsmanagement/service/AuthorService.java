package com.epam.newsmanagement.service;

import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.service.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Никита on 6/3/2016.
 */
public interface AuthorService {
    void addNewsAuthor(long newsId, long authorId) throws ServiceException;
    Long createUpdateAuthor(Author author) throws ServiceException;
    ArrayList<Author> getAllAuthors() throws ServiceException;
    ArrayList<Author> getUnexpiredAuthors() throws ServiceException;

}

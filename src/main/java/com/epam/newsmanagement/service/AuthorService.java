package com.epam.newsmanagement.service;

import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.service.exception.ServiceException;

/**
 * Created by Никита on 6/3/2016.
 */
public interface AuthorService {
    void addNewsAuthor(long newsId, long authorId) throws ServiceException;
    Long createAuthor(Author author) throws ServiceException;
}

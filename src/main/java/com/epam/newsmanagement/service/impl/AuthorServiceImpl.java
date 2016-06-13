package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.exception.ServiceException;

/**
 * Created by Никита on 6/3/2016.
 */
public class AuthorServiceImpl implements AuthorService {
    private AuthorDAO authorDAO;

    public AuthorServiceImpl() {
    }

    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    /**
     * Create author for current news.
     * @param newsId
     * @param authorId
     * @throws ServiceException
     */
    @Override
    public void addNewsAuthor(long newsId, long authorId) throws ServiceException {
        try {
            authorDAO.createNewsAuthor(newsId, authorId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long createAuthor(Author author) throws ServiceException {
        Long authorId;
        try {
            authorId = authorDAO.create(author);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return authorId;
    }
}

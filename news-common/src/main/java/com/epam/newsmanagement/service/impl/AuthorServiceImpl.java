package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import java.util.ArrayList;

/**
 * Created by Никита on 6/3/2016.
 */
public class AuthorServiceImpl implements AuthorService {
    private static final Logger logger = Logger.getLogger(AuthorServiceImpl.class);
    private AuthorDAO authorDAO;

    public AuthorServiceImpl() {
    }

    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    public ArrayList<Author> getAllAuthors() throws ServiceException {
        ArrayList<Author> authors;
        try {
            authors = authorDAO.getAllAuthors();
        } catch (DAOException e) {
            logger.error("Can not get all authors");
            throw new ServiceException(e);
        }
        return authors;
    }

    @Override
    public ArrayList<Author> getUnexpiredAuthors() throws ServiceException {
        ArrayList<Author> unexpireAuthors = new ArrayList<>();
        ArrayList<Author> allAuthors = getAllAuthors();
        for(Author author : allAuthors) {
            if(author.getExpired() == null) {
                unexpireAuthors.add(author);
            }
        }
        return unexpireAuthors;
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
            logger.error("Can not add this author for current news.", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Long createUpdateAuthor(Author author) throws ServiceException {
        Long authorId;
        if (author.getAuthorId() == null) {
            try {
                authorId = authorDAO.create(author);
            } catch (DAOException e) {
                logger.error("Can not create author");
                throw new ServiceException(e);
            }
        } else {
            try {
                authorDAO.update(author);
            } catch (DAOException e) {
                logger.error("Can not update author");
                throw new ServiceException(e);
            }
            authorId = author.getAuthorId();
        }
        return authorId;
    }

}

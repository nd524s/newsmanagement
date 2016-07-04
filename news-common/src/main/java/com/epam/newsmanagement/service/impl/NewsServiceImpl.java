package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.domain.SearchCriteria;
import com.epam.newsmanagement.domain.Tag;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by Никита on 6/1/2016.
 */

public class NewsServiceImpl implements NewsService {
    private static final Logger logger = Logger.getLogger(NewsServiceImpl.class);
    private NewsDAO newsDAO;
    private TagDAO tagDAO;
    private AuthorDAO authorDAO;
    private CommentDAO commentDAO;

    public NewsServiceImpl() {
    }

    public NewsServiceImpl(NewsDAO newsDAO, TagDAO tagDAO, AuthorDAO authorDAO, CommentDAO commentDAO) {
        this.newsDAO = newsDAO;
        this.tagDAO = tagDAO;
        this.authorDAO = authorDAO;
        this.commentDAO = commentDAO;
    }

    @Override
    public News getNextNews(long id) throws ServiceException {
        ArrayList<News> newsList = getAllNews();
        for (int i = 0; i < newsList.size(); i++) {
            if(newsList.get(i).getNewsId() == id && i != newsList.size() - 1) {
                return newsList.get(i+1);
            }
        }
        return getSingleNews(id);
    }

    @Override
    public News getPreviousNews(long id) throws ServiceException {
        ArrayList<News> newsList = getAllNews();
        for (int i = 0; i < newsList.size(); i++) {
            if(newsList.get(i).getNewsId() == id && i != 0) {
                return newsList.get(i-1);
            }
        }
        return getSingleNews(id);
    }

    @Override
    public long getNewsCount() throws ServiceException {
        long newsCount;
        try {
            newsCount = newsDAO.getNewsCount();
        } catch (DAOException e) {
            logger.error("Can not count number of news.", e);
            throw new ServiceException(e);
        }
        return newsCount;
    }

    /**
     * Search news by SearchCriteria object.
     * @param searchCriteria
     * @return List of news.
     * @throws ServiceException
     */
    @Override
    public ArrayList<News> searchBySearchCriteria(SearchCriteria searchCriteria) throws ServiceException {
        ArrayList<News> news;
        try {
            news = newsDAO.getNewsBySearchCriteria(searchCriteria);
        } catch (DAOException e) {
            logger.error("Can not search by search criteria.", e);
            throw new ServiceException(e);
        }
        return news;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
    public ArrayList<News> getAllNews() throws ServiceException {
        ArrayList<News> newsList = new ArrayList<>();
        try {
            for (News news : newsDAO.getAllNews()) {
                news.setAuthors(authorDAO.getNewsAuthor(news.getNewsId()));
                news.setTags(tagDAO.getNewsTags(news.getNewsId()));
                news.setComments(commentDAO.getNewsComments(news.getNewsId()));
                newsList.add(news);
            }
        } catch (DAOException e) {
            logger.error("Can not get all news.", e);
            throw new ServiceException(e);
        }
        return newsList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
    public News getSingleNews(long newsId) throws ServiceException {
        News singleNews;
        try {
            singleNews = newsDAO.getById(newsId);
            singleNews.setAuthors(authorDAO.getNewsAuthor(newsId));
            singleNews.setTags(tagDAO.getNewsTags(newsId));
            singleNews.setComments(commentDAO.getNewsComments(newsId));
        } catch (DAOException e) {
            logger.error("Can not get single news.", e);
            throw new ServiceException(e);
        }
        return singleNews;
    }

    @Override
    public void deleteNews(long id) throws ServiceException {
        try {
            newsDAO.delete(id);
        } catch (DAOException e) {
            logger.error("Can not delete news.", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void editNews(News news) throws ServiceException {
        try {
            newsDAO.update(news);
        } catch (DAOException e) {
            logger.error("Can not update news.", e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
    public Long addNews(News news) throws ServiceException {
        Long newsId;
        try {
            newsId = newsDAO.create(news);
            insertNewsAuthor(news, newsId);
            insertNewsTag(news, newsId);
        } catch (DAOException e) {
            logger.error("Can not add news.", e);
            throw new ServiceException(e);
        }
        return newsId;
    }

    /**
     * Create NEWS_AUTHOR record.
     * @param news
     * @throws DAOException
     */
    private void insertNewsAuthor(News news, Long newsId) throws DAOException {
        for (Author author : news.getAuthors()) {
            authorDAO.createNewsAuthor(newsId, author.getAuthorId());
        }
    }

    /**
     * Create NEWS_TAG record.
     * @param news
     * @throws DAOException
     */
    private void insertNewsTag(News news, Long newsId) throws DAOException {
        for (Tag tag : news.getTags()) {
            tagDAO.createNewsTag(newsId, tag.getTagId());
        }
    }

}

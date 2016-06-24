package com.epam.newsmanagement.service;

import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.domain.SearchCriteria;
import com.epam.newsmanagement.service.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Никита on 6/1/2016.
 */
public interface NewsService {
    Long addNews(News news) throws ServiceException;
    void deleteNews(long id) throws ServiceException;
    News getSingleNews(long newsId) throws ServiceException;
    ArrayList<News> getAllNews() throws ServiceException;
    void editNews(News news) throws ServiceException;
    ArrayList<News> searchBySearchCriteria(SearchCriteria searchCriteria) throws ServiceException;
    long getNewsCount() throws ServiceException;
    News getNextNews(long id) throws ServiceException;
    News getPreviousNews(long id) throws ServiceException;
}

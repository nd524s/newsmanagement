package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.domain.SearchCriteria;

import java.util.ArrayList;

/**
 * Created by Никита on 27.05.2016.
 */
public interface NewsDAO extends GenericDAO<News> {
    ArrayList<News> getAllNews() throws DAOException;
    ArrayList<News> getNewsBySearchCriteria(SearchCriteria criteria) throws DAOException;
    long getNewsCount() throws DAOException;
}

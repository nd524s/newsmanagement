package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Entity;

/**
 * Created by Никита on 5/27/2016.
 */
public interface GenericDAO<T extends Entity> {
    Long create(T item) throws DAOException;
    T getById(long id) throws DAOException;
    void update(T item) throws DAOException;
    void delete(long id) throws DAOException;
}

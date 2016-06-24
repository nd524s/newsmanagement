package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.domain.Tag;
import com.epam.newsmanagement.dao.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Никита on 6/2/2016.
 */
public interface TagDAO extends GenericDAO<Tag> {
    ArrayList<Tag> getNewsTags(long newsId) throws DAOException;
    void createNewsTag(long newsId, long tagId) throws DAOException;
}

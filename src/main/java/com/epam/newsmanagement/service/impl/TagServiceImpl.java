package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.service.TagService;
import com.epam.newsmanagement.service.exception.ServiceException;

/**
 * Created by Никита on 6/3/2016.
 */
public class TagServiceImpl implements TagService {
    private TagDAO tagDAO;

    public TagServiceImpl() {
    }

    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    /**
     * Create tag for current news.
     * @param newsId
     * @param tagId
     * @throws ServiceException
     */
    @Override
    public void addNewsTag(long newsId, long tagId) throws ServiceException {
        try {
            tagDAO.createNewsTag(newsId, tagId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Tag;
import com.epam.newsmanagement.service.TagService;
import com.epam.newsmanagement.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Никита on 6/3/2016.
 */
public class TagServiceImpl implements TagService {
    private static final Logger logger = Logger.getLogger(TagServiceImpl.class);
    private TagDAO tagDAO;

    public TagServiceImpl() {
    }

    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public Long createUpdateTag(Tag tag) throws ServiceException {
        Long tagId;

        if(tag.getTagId() == null) {
            try {
                tagId = tagDAO.create(tag);
            } catch (DAOException e) {
                logger.error("Can not create tag", e);
                throw new ServiceException(e);
            }
        } else {
            try {
                tagDAO.update(tag);
            } catch (DAOException e) {
                logger.error("Can not update tag", e);
                throw new ServiceException("Can not update tag", e);
            }
            tagId = tag.getTagId();
        }
        return tagId;
    }

    @Override
    public void deleteTag(long id) throws ServiceException {
        try {
            tagDAO.delete(id);
        } catch (DAOException e) {
            logger.error("Can not delete tag", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Tag> getAllTags() throws ServiceException {
        ArrayList<Tag> tags;
        try {
            tags = tagDAO.getAllTags();
        } catch (DAOException e) {
            logger.error("Can not get all tags", e);
            throw new ServiceException(e);
        }
        return tags;
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
            logger.error("Can not add tag for current news.", e);
            throw new ServiceException(e);
        }
    }
}

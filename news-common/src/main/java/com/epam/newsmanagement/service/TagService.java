package com.epam.newsmanagement.service;

import com.epam.newsmanagement.domain.Tag;
import com.epam.newsmanagement.service.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Никита on 6/3/2016.
 */
public interface TagService {
    void addNewsTag(long newsId, long tagId) throws ServiceException;
    ArrayList<Tag> getAllTags() throws ServiceException;
    void deleteTag(long id) throws ServiceException;
    Long createUpdateTag(Tag tag) throws ServiceException;
}

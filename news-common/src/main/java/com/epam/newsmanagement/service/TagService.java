package com.epam.newsmanagement.service;

import com.epam.newsmanagement.service.exception.ServiceException;

/**
 * Created by Никита on 6/3/2016.
 */
public interface TagService {
    void addNewsTag(long newsId, long tagId) throws ServiceException;
}

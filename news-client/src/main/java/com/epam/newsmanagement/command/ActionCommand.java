package com.epam.newsmanagement.command;


import com.epam.newsmanagement.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Никита on 8/8/2016.
 */
public interface ActionCommand {
    String execute(HttpServletRequest request) throws ServiceException;
}

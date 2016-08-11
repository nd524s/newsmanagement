package com.epam.newsmanagement.command;


import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.TagService;
import com.epam.newsmanagement.service.exception.ServiceException;
import com.epam.newsmanagement.util.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Никита on 8/8/2016.
 */
@Component("getNewsList")
public class GetNewsListCommand implements ActionCommand {
    private static final String AUTHORS_ATTRIBUTE = "authors";
    private static final String TAGS_ATTRIBUTE = "tags";
    private static final String NEWS_ATTRIBUTE = "newses";

    @Autowired
    private NewsService newsService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TagService tagService;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {

        request.setAttribute(AUTHORS_ATTRIBUTE, authorService.getUnexpiredAuthors());
        request.setAttribute(TAGS_ATTRIBUTE, tagService.getAllTags());
        request.setAttribute(NEWS_ATTRIBUTE, newsService.getAllNews());
        String page = ResourceManager.getProperty("page.newsList");
        return page;
    }
}

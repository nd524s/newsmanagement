package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by Никита on 6/20/2016.
 */
@Controller
@RequestMapping("/admin")
public class NewsListController {
    private static final String NEWS_LIST_VIEW = "newsList";
    private static final String NEWS_ATTRIBUTE = "newses";

    @Autowired
    private NewsService newsService;

    @RequestMapping("/news")
    public ModelAndView first() throws ServiceException {
        ArrayList<News> newses = newsService.getAllNews();
        ModelAndView modelAndView = new ModelAndView(NEWS_LIST_VIEW);
        modelAndView.addObject(NEWS_ATTRIBUTE, newses);
        return modelAndView;
    }
}

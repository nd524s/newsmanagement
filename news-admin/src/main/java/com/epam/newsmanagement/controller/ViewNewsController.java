package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Никита on 6/24/2016.
 */
@Controller
@RequestMapping("/admin")
public class ViewNewsController {
    private static final String SINGLE_NEWS_VIEW = "viewNews";
    private static final String SINGLE_NEWS_ATTRIBUTE = "news";

    @Autowired
    private NewsService newsService;

    @RequestMapping("/viewNews")
    public ModelAndView showSingleNews(@RequestParam("id") long id) throws ServiceException {
        News news = newsService.getSingleNews(id);
        ModelAndView modelAndView = new ModelAndView(SINGLE_NEWS_VIEW);
        modelAndView.addObject(SINGLE_NEWS_ATTRIBUTE, news);
        return modelAndView;
    }

    @RequestMapping("/nextNews")
    public ModelAndView showNextNews(@RequestParam("id") long id) throws ServiceException {
        News news = newsService.getNextNews(id);
        ModelAndView modelAndView = new ModelAndView(SINGLE_NEWS_VIEW);
        modelAndView.addObject(SINGLE_NEWS_ATTRIBUTE, news);
        return modelAndView;
    }

    @RequestMapping("/previousNews")
    public ModelAndView showPreviousNews(@RequestParam("id") long id) throws ServiceException {
        News news = newsService.getPreviousNews(id);
        ModelAndView modelAndView = new ModelAndView(SINGLE_NEWS_VIEW);
        modelAndView.addObject(SINGLE_NEWS_ATTRIBUTE, news);
        return modelAndView;
    }

}

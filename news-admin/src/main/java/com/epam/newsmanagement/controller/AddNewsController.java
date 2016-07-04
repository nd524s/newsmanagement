package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.domain.*;
import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.TagService;
import com.epam.newsmanagement.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Никита on 6/27/2016.
 */
@Controller
@RequestMapping("/admin")
public class AddNewsController {
    private static final String SINGLE_NEWS_VIEW = "viewNews";
    private static final String SINGLE_NEWS_ATTRIBUTE = "news";
    private static final String ADD_NEWS_VIEW = "addNews";

    @Autowired
    public NewsService newsService;
    @Autowired
    public AuthorService authorService;
    @Autowired
    public TagService tagService;

    @ModelAttribute
    public void fillDropDowns(Model model) throws ServiceException {
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("tags", tagService.getAllTags());
    }

    @RequestMapping(value = "/saveNews", method = RequestMethod.POST)
    public ModelAndView addNews(@ModelAttribute("news") NewsTransferObject news) throws ServiceException {
        Timestamp creationDate = new Timestamp(new Date().getTime());
        ArrayList<Author> authors = new ArrayList<>();
        authors.add(new Author(Long.valueOf(news.getAuthor())));
        ArrayList<Tag> tags = new ArrayList<>();
        for(String str : news.getTags()) {
            tags.add(new Tag(Long.valueOf(str)));
        }
        News currentNews = new News(news.getTitle(), news.getShortText(), news.getFullText(),
                                    creationDate, creationDate, authors, tags);
        Long newsId = newsService.addNews(currentNews);
        currentNews.setNewsId(newsId);
        ModelAndView modelAndView = new ModelAndView(SINGLE_NEWS_VIEW);
        modelAndView.addObject(SINGLE_NEWS_ATTRIBUTE, currentNews);
        return modelAndView;
    }

    @RequestMapping("/addNews")
    public ModelAndView showAddNewsPage() {
        ModelAndView modelAndView = new ModelAndView(ADD_NEWS_VIEW, SINGLE_NEWS_ATTRIBUTE, new NewsTransferObject());
        return modelAndView;
    }

}

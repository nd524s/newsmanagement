package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.domain.Tag;
import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.TagService;
import com.epam.newsmanagement.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Никита on 8/2/2016.
 */
@Controller
@RequestMapping("/")
public class EditNewsController {
    private static final String EDIT_NEWS_VIEW = "editNews";
    private static final String NEWS_ATTRIBUTE = "currentNews";
    private static final String DATE_ATTRIBUTE = "creationDate";
    private static final String AUTHORS_ATTRIBUTE = "authors";
    private static final String TAGS_ATTRIBUTE = "tags";
    private static final String TITLE_PARAMETER = "title";
    private static final String BRIEF_PARAMETER = "shortText";
    private static final String CONTENT_PARAMETER = "fullText";
    private static final String AUTHOR_PARAMETER = "author";
    private static final String TAGS_PARAMETER = "tags";
    private static final String NEWS_ID_PARAMETER = "newsId";
    private static final String SINGLE_NEWS_VIEW = "viewNews";
    private static final String SINGLE_NEWS_ATTRIBUTE = "news";

    @Autowired
    private NewsService newsService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/editNews")
    public ModelAndView showEditNewsPage(@RequestParam("id") long newsId, HttpServletRequest request) throws ServiceException {
        News news = newsService.getSingleNews(newsId);
        String formatDate = new SimpleDateFormat("dd/MM/yyyy").format(news.getCreationDate());
        request.setAttribute(DATE_ATTRIBUTE, formatDate);
        request.setAttribute(AUTHORS_ATTRIBUTE, authorService.getUnexpiredAuthors());
        request.setAttribute(TAGS_ATTRIBUTE, tagService.getAllTags());
        ModelAndView modelAndView = new ModelAndView(EDIT_NEWS_VIEW, NEWS_ATTRIBUTE, news);
        return modelAndView;
    }

    @RequestMapping("/updateNews")
    public ModelAndView updateNews(HttpServletRequest request) throws ServiceException {
        String title = request.getParameter(TITLE_PARAMETER);
        String shortText = request.getParameter(BRIEF_PARAMETER);
        String fullText = request.getParameter(CONTENT_PARAMETER);
        long newsId = Long.parseLong(request.getParameter(NEWS_ID_PARAMETER));
        Timestamp modificationDate = new Timestamp(new Date().getTime());
        ArrayList<Author> authors = new ArrayList<>();
        authors.add(new Author(Long.valueOf(request.getParameter(AUTHOR_PARAMETER))));
        String[] tagIds = request.getParameterValues(TAGS_PARAMETER);
        ArrayList<Tag> tags = new ArrayList<>();
        for (String str : tagIds) {
            tags.add(new Tag(Long.valueOf(str)));
        }
        News news = new News(newsId, title, shortText, fullText, modificationDate, authors, tags);
        newsService.editNews(news);
        News currentNews = newsService.getSingleNews(newsId);
        ModelAndView modelAndView = new ModelAndView(SINGLE_NEWS_VIEW);
        modelAndView.addObject(SINGLE_NEWS_ATTRIBUTE, currentNews);
        return modelAndView;
    }
}

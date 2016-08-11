package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.domain.SearchCriteria;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Никита on 6/20/2016.
 */
@Controller
@RequestMapping("/")
public class NewsListController {
    private static final String NEWS_LIST_VIEW = "newsList";
    private static final String NEWS_ATTRIBUTE = "newses";
    private static final String AUTHORS_ATTRIBUTE = "authors";
    private static final String TAGS_ATTRIBUTE = "tags";
    private static final String DELETE_NEWS_PARAMETER = "deleteItem";
    private static final String AUTHOR_PARAMETER = "author";
    private static final String TAGS_PARAMETER = "tags";


    @Autowired
    private NewsService newsService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TagService tagService;

    @ModelAttribute
    public void fillDropDowns(Model model) throws ServiceException {
        model.addAttribute(AUTHORS_ATTRIBUTE, authorService.getUnexpiredAuthors());
        model.addAttribute(TAGS_ATTRIBUTE, tagService.getAllTags());
    }

    @RequestMapping("/newsList")
    public ModelAndView showAllNews() throws ServiceException {
        ArrayList<News> newses = newsService.getAllNews();
        ModelAndView modelAndView = new ModelAndView(NEWS_LIST_VIEW);
        modelAndView.addObject(NEWS_ATTRIBUTE, newses);
        return modelAndView;
    }

    @RequestMapping("/deleteNews")
    public ModelAndView deleteNews(HttpServletRequest request) throws ServiceException {
        String[] deleteItems = request.getParameterValues(DELETE_NEWS_PARAMETER);
        for (String str : deleteItems) {
            newsService.deleteNews(Long.parseLong(str));
        }
        ArrayList<News> newses = newsService.getAllNews();
        ModelAndView modelAndView = new ModelAndView(NEWS_LIST_VIEW);
        modelAndView.addObject(NEWS_ATTRIBUTE, newses);
        return modelAndView;
    }

    @RequestMapping("/search")
    public ModelAndView search(HttpServletRequest request) throws ServiceException {
        String authorId = request.getParameter(AUTHOR_PARAMETER);
        String[] tagIds = request.getParameterValues(TAGS_PARAMETER);
        SearchCriteria searchCriteria = buildSearchCriteria(authorId, tagIds);
        ArrayList<News> newses = newsService.searchBySearchCriteria(searchCriteria);
        ModelAndView modelAndView = new ModelAndView(NEWS_LIST_VIEW);
        modelAndView.addObject(NEWS_ATTRIBUTE, newses);
        return modelAndView;
    }

    private SearchCriteria buildSearchCriteria(String authorId, String[] tags){
        Author author;
        ArrayList<Tag> tags1 = new ArrayList<>();
        if (authorId.isEmpty()) {
            author = null;
        } else {
            author = new Author(Long.valueOf(authorId));
        }
        if (tags != null) {
            for (String str : tags) {
                tags1.add(new Tag(Long.valueOf(str)));
            }
        }

        SearchCriteria searchCriteria = new SearchCriteria(author, tags1);
        return searchCriteria;
    }

}

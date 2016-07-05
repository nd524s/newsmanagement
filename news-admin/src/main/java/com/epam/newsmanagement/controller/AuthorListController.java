package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.exception.ServiceException;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by Никита on 7/4/2016.
 */
@Controller
@RequestMapping("/admin")
public class AuthorListController {
    private static final String AUTHOR_LIST_VIEW = "authorList";
    private static final String AUTHORS_ATTRIBUTE = "authors";
    private static final String AUTHOR_ID_PARAMETER = "authorId";
    private static final String EXPIRED_PARAMETER = "expired";
    private static final String AUTHOR_NAME_PARAMETER = "authorName";

    @Autowired
    private AuthorService authorService;

    @RequestMapping("/authorList")
    public ModelAndView showAuthorList() throws ServiceException {
        ArrayList<Author> unexpiredAuthors = authorService.getUnexpiredAuthors();
        ModelAndView modelAndView = new ModelAndView(AUTHOR_LIST_VIEW, AUTHORS_ATTRIBUTE, unexpiredAuthors);
        return modelAndView;
    }

    @RequestMapping("/expireAuthor")
    public ModelAndView expireAuthor(HttpServletRequest request) throws ServiceException {
        Long authorId = Long.valueOf(request.getParameter(AUTHOR_ID_PARAMETER));
        String authorName = request.getParameter(AUTHOR_NAME_PARAMETER);
        Author author = new Author(authorId, authorName);
        Timestamp expired = new Timestamp(new Date().getTime());
        author.setExpired(expired);
        authorService.createUpdateAuthor(author);
        ArrayList<Author> unexpiredAuthors = authorService.getUnexpiredAuthors();
        ModelAndView modelAndView = new ModelAndView(AUTHOR_LIST_VIEW, AUTHORS_ATTRIBUTE, unexpiredAuthors);
        return modelAndView;
    }

    @RequestMapping("/updateAuthor")
    public ModelAndView updateAuthor(HttpServletRequest request) throws ServiceException {
        Long authorId = Long.valueOf(request.getParameter(AUTHOR_ID_PARAMETER));
        String authorName = request.getParameter(AUTHOR_NAME_PARAMETER);
        Timestamp expired = null;
        Author author = new Author(authorId, authorName, expired);
        authorService.createUpdateAuthor(author);
        ArrayList<Author> unexpiredAuthors = authorService.getUnexpiredAuthors();
        ModelAndView modelAndView = new ModelAndView(AUTHOR_LIST_VIEW, AUTHORS_ATTRIBUTE, unexpiredAuthors);
        return modelAndView;
    }
}

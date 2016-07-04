package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by Никита on 7/4/2016.
 */
@Controller
@RequestMapping("/admin")
public class AuthorListController {
    private static final String AUTHOR_LIST_VIEW = "authorList";
    private static final String AUTHORS_ATTRIBUTE = "authors";
    @Autowired
    private AuthorService authorService;

    @RequestMapping("/authorList")
    public ModelAndView showAuthorList() throws ServiceException {
        ArrayList<Author> authors = authorService.getAllAuthors();
        ModelAndView modelAndView = new ModelAndView(AUTHOR_LIST_VIEW, AUTHORS_ATTRIBUTE, authors);
        return modelAndView;
    }
}

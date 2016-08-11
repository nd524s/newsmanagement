package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.domain.Tag;
import com.epam.newsmanagement.service.TagService;
import com.epam.newsmanagement.service.exception.ServiceException;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Никита on 8/1/2016.
 */
@Controller
@RequestMapping("/")
public class TagListController {
    private static final String TAGS_ATTRIBUTE = "tags";
    private static final String TAGS_LIST_VIEW = "tagList";
    private static final String TAG_ID_PARAMETER = "tagId";
    private static final String TAG_NAME_PARAMETER = "tagName";


    @Autowired
    private TagService tagService;

    @RequestMapping("/tagList")
    public ModelAndView showTagList() throws ServiceException {
        ArrayList<Tag> tags = tagService.getAllTags();
        ModelAndView modelAndView = new ModelAndView(TAGS_LIST_VIEW, TAGS_ATTRIBUTE, tags);
        return modelAndView;
    }

    @RequestMapping("/deleteTag")
    public ModelAndView deleteTag(HttpServletRequest request) throws ServiceException {
        long tagId = Long.parseLong(request.getParameter(TAG_ID_PARAMETER));
        tagService.deleteTag(tagId);
        ArrayList<Tag> tags = tagService.getAllTags();
        ModelAndView modelAndView = new ModelAndView(TAGS_LIST_VIEW, TAGS_ATTRIBUTE, tags);
        return modelAndView;
    }

    @RequestMapping("/updateTag")
    public ModelAndView updateTag(HttpServletRequest request) throws ServiceException {
        long tagId = Long.parseLong(request.getParameter(TAG_ID_PARAMETER));
        String tagName = request.getParameter(TAG_NAME_PARAMETER);
        Tag tag = new Tag(tagId, tagName);
        tagService.createUpdateTag(tag);
        ArrayList<Tag> tags = tagService.getAllTags();
        ModelAndView modelAndView = new ModelAndView(TAGS_LIST_VIEW, TAGS_ATTRIBUTE, tags);
        return modelAndView;
    }

    @RequestMapping("/saveTag")
    public ModelAndView createTag(HttpServletRequest request) throws ServiceException {
        String tagName = request.getParameter(TAG_NAME_PARAMETER);
        Tag tag = new Tag(tagName);
        tagService.createUpdateTag(tag);
        ArrayList<Tag> tags = tagService.getAllTags();
        ModelAndView modelAndView = new ModelAndView(TAGS_LIST_VIEW, TAGS_ATTRIBUTE, tags);
        return modelAndView;
    }
}

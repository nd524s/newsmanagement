package com.epam.newsmanagement.command;

import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.domain.SearchCriteria;
import com.epam.newsmanagement.domain.Tag;
import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.TagService;
import com.epam.newsmanagement.service.exception.ServiceException;
import com.epam.newsmanagement.util.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Никита on 8/12/2016.
 */
@Component("search")
public class SearchCommand implements ActionCommand {
    private static final String AUTHOR_PARAMETER = "author";
    private static final String TAGS_PARAMETER = "tags";
    private static final String NEWS_ATTRIBUTE = "newses";
    private static final String TAGS_ATTRIBUTE = "tags";
    private static final String AUTHORS_ATTRIBUTE = "authors";


    @Autowired
    private AuthorService authorService;
    @Autowired
    private TagService tagService;
    @Autowired
    private NewsService newsService;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String authorId = request.getParameter(AUTHOR_PARAMETER);
        String[] tagIds = request.getParameterValues(TAGS_PARAMETER);
        SearchCriteria searchCriteria = buildSearchCriteria(authorId, tagIds);
        ArrayList<News> newses = newsService.searchBySearchCriteria(searchCriteria);
        request.setAttribute(NEWS_ATTRIBUTE, newses);
        request.setAttribute(AUTHORS_ATTRIBUTE, authorService.getUnexpiredAuthors());
        request.setAttribute(TAGS_ATTRIBUTE, tagService.getAllTags());
        String page = ResourceManager.getProperty("page.newsList");
        return page;
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

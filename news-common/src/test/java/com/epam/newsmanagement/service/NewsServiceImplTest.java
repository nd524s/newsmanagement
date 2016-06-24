package com.epam.newsmanagement.service;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.*;
import com.epam.newsmanagement.service.exception.ServiceException;
import com.epam.newsmanagement.service.impl.NewsServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Никита on 6/13/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {
    @InjectMocks
    @Autowired
    NewsServiceImpl newsService;
    @Mock
    TagDAO tagDAO;
    @Mock
    NewsDAO newsDAO;
    @Mock
    CommentDAO commentDAO;
    @Mock
    AuthorDAO authorDAO;
    private static final long NEWS_COUNT = 10;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getNewsCountTest() throws DAOException, ServiceException {
        when(newsDAO.getNewsCount()).thenReturn(NEWS_COUNT);
        long newsCount = newsService.getNewsCount();
        Assert.assertEquals(newsCount, NEWS_COUNT);
        verify(newsDAO).getNewsCount();
    }

    @Test
    public void searchBySearchCriteriaTest() throws DAOException, ServiceException {
        SearchCriteria searchCriteria = new SearchCriteria();
        ArrayList<News> news1 = new ArrayList<>();
        news1.add(new News());
        when(newsDAO.getNewsBySearchCriteria(searchCriteria)).thenReturn(news1);
        ArrayList<News> news2 = newsService.searchBySearchCriteria(searchCriteria);
        Assert.assertEquals(news1, news2);
        verify(newsDAO).getNewsBySearchCriteria(searchCriteria);
    }

    @Test
    public void getAllNewsTest() throws DAOException, ServiceException {
        ArrayList<News> news1 = new ArrayList<>();
        when(newsDAO.getAllNews()).thenReturn(news1);
        when(authorDAO.getNewsAuthor(anyLong())).thenReturn(new ArrayList<Author>());
        when(commentDAO.getNewsComments(anyLong())).thenReturn(new ArrayList<Comment>());
        when(tagDAO.getNewsTags(anyLong())).thenReturn(new ArrayList<Tag>());
        ArrayList<News> news2 = newsService.getAllNews();
        Assert.assertNotNull(news2);
        Assert.assertEquals(news1, news2);
        verify(newsDAO).getAllNews();
    }

    @Test
    public void getSingleNewsTest() throws DAOException, ServiceException {
        News news1 = new News();
        when(newsDAO.getById(anyLong())).thenReturn(news1);
        when(authorDAO.getNewsAuthor(anyLong())).thenReturn(new ArrayList<Author>());
        when(commentDAO.getNewsComments(anyLong())).thenReturn(new ArrayList<Comment>());
        when(tagDAO.getNewsTags(anyLong())).thenReturn(new ArrayList<Tag>());
        News news2 = newsService.getSingleNews(anyLong());
        Assert.assertNotNull(news2);
        Assert.assertEquals(news1, news2);
        verify(newsDAO).getById(anyLong());
        verify(tagDAO).getNewsTags(anyLong());
        verify(commentDAO).getNewsComments(anyLong());
        verify(authorDAO).getNewsAuthor(anyLong());
    }

    @Test
    public void deleteNewsTest() throws DAOException, ServiceException {
        newsService.deleteNews(anyLong());
        verify(newsDAO).delete(anyLong());
    }

    @Test
    public void editNewsTest() throws ServiceException, DAOException {
        newsService.editNews(any(News.class));
        verify(newsDAO).update(any(News.class));
    }
}

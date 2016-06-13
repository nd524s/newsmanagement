package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.dao.NewsDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.domain.SearchCriteria;
import com.epam.newsmanagement.domain.Tag;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Никита on 6/9/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@Transactional
@DatabaseSetup(value = "classpath:/data/news-data.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:/data/news-data.xml", type = DatabaseOperation.DELETE_ALL)
public class NewsDAOImplTest {
    private static final long NEWS_COUNT = 3;
    private static final long AUTHOR_ID = 1;
    private static final long TAG_ID = 1;
    private static final long NEWS_ID1 = 1;
    private static final long NEWS_ID2 = 2;
    private static final long NEWS_ID3 = 3;
    private static final String TITLE1 = "A";
    private static final String TITLE2 = "B";
    private static final String TITLE3 = "C";
    private static final String TITLE4 = "D";
    private static final String SHORT_TEXT1 = "AA";
    private static final String SHORT_TEXT2 = "BB";
    private static final String SHORT_TEXT3 = "CC";
    private static final String SHORT_TEXT4 = "DD";
    private static final String FULL_TEXT1 = "AAA";
    private static final String FULL_TEXT2 = "BBB";
    private static final String FULL_TEXT3 = "CCC";
    private static final String FULL_TEXT4 = "DDD";
    private static final Timestamp CREATION_DATE = Timestamp.valueOf("2016-06-02 10:43:33.220000");
    private static final Timestamp MODIFICATION_DATE = Timestamp.valueOf("2016-06-02 10:43:33.000000");
    private static final Timestamp MODIFICATION_DATE_UPDATE = Timestamp.valueOf("2016-07-02 10:43:33.000000");
    private static final String TAG_NATURE = "nature";
    private static final String AUTHOR_NAME = "JOHN TRAVOLTA";
    private static final Timestamp EXPIRED = Timestamp.valueOf("2016-06-02 10:43:33.220000");

    @Autowired
    NewsDAO newsDAO;

    @Test
    public void getNewsCountTest() throws DAOException {
        Assert.assertEquals(NEWS_COUNT, newsDAO.getNewsCount());
    }

    @Test
    public void getNewsBySearchCriteriaTest() throws DAOException, ParseException {
        Author author = new Author(AUTHOR_ID, AUTHOR_NAME, EXPIRED);
        Tag tag = new Tag(TAG_ID, TAG_NATURE);
        News news = new News(NEWS_ID1, TITLE1, SHORT_TEXT1, FULL_TEXT1, CREATION_DATE, MODIFICATION_DATE);
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tag);
        SearchCriteria searchCriteria = new SearchCriteria(author, tags);
        ArrayList<News> news1 = newsDAO.getNewsBySearchCriteria(searchCriteria);
        ArrayList<News> news2 = new ArrayList<>();
        news2.add(news);
        Assert.assertEquals(news1, news2);
    }

    @Test
    public void getAllNewsTest() throws DAOException {
        News news1 = new News(NEWS_ID1, TITLE1, SHORT_TEXT1, FULL_TEXT1, CREATION_DATE, MODIFICATION_DATE);
        News news2 = new News(NEWS_ID2, TITLE2, SHORT_TEXT2, FULL_TEXT2, CREATION_DATE, MODIFICATION_DATE);
        News news3 = new News(NEWS_ID3, TITLE3, SHORT_TEXT3, FULL_TEXT3, CREATION_DATE, MODIFICATION_DATE);
        ArrayList<News> newsList1 = new ArrayList<>();
        newsList1.add(news1);
        newsList1.add(news3);
        newsList1.add(news2);
        ArrayList<News> newsList2 = newsDAO.getAllNews();
        Assert.assertEquals(newsList1, newsList2);
    }

    @Test
    public void getByIdNewsTest() throws DAOException {
        News news1 = new News(NEWS_ID1, TITLE1, SHORT_TEXT1, FULL_TEXT1, CREATION_DATE, MODIFICATION_DATE);
        News news2 = newsDAO.getById(NEWS_ID1);
        Assert.assertEquals(news1, news2);
    }

    @Test
    public void createNewsTest() throws DAOException {
        News news1 = new News(TITLE4, SHORT_TEXT4, FULL_TEXT4, CREATION_DATE, MODIFICATION_DATE);
        Long newsId = newsDAO.create(news1);
        news1.setNewsId(newsId);
        News news2 = newsDAO.getById(newsId);
        Assert.assertEquals(news1, news2);
    }

    @Test
    @ExpectedDatabase(value = "classpath:/data/expected-update-news-data.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void updateNewsTest() throws DAOException {
        News news = new News(NEWS_ID3, TITLE3, SHORT_TEXT3, FULL_TEXT3, CREATION_DATE, MODIFICATION_DATE_UPDATE);
        newsDAO.update(news);
    }

    @Test
    @ExpectedDatabase(value = "classpath:/data/expected-delete-news-data.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void deleteNewsTest() throws DAOException {
        newsDAO.delete(NEWS_ID3);
    }

}

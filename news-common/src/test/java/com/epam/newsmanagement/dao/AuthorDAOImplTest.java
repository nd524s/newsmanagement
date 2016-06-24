package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Author;
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

import java.sql.Timestamp;
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
@DatabaseSetup(value = "classpath:/data/author-data.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:/data/author-data.xml", type = DatabaseOperation.DELETE_ALL)
public class AuthorDAOImplTest {
    private static final long NEWS_ID1 = 1;
    private static final long NEWS_ID2 = 2;
    private static final long AUTHOR_ID1 = 1;
    private static final long AUTHOR_ID2 = 2;
    private static final String AUTHOR_NAME = "JOHN TRAVOLTA";
    private static final Timestamp EXPIRED = Timestamp.valueOf("2016-06-02 10:43:33.220000");

    @Autowired
    AuthorDAO authorDAO;

    @Test
    public void getNewsAuthorTest() throws DAOException {
        ArrayList<Author> authors1 = new ArrayList<>();
        Author author = new Author(AUTHOR_ID1, AUTHOR_NAME, EXPIRED);
        authors1.add(author);
        ArrayList<Author> authors2 = authorDAO.getNewsAuthor(NEWS_ID1);
        Assert.assertEquals(authors1, authors2);
    }

    @Test
    @ExpectedDatabase(value = "classpath:/data/expected-author-data.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void createNewsAuthorTest() throws DAOException {
        authorDAO.createNewsAuthor(NEWS_ID2, AUTHOR_ID2);
    }
}

package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
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

import java.util.ArrayList;

/**
 * Created by Никита on 6/8/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@Transactional
@DatabaseSetup(value = "classpath:/data/tag-data.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:/data/tag-data.xml", type = DatabaseOperation.DELETE_ALL)
public class TagDAOImplTest {
    private static final long NEWS_ID1 = 1;
    private static final long NEWS_ID2 = 2;
    private static final long TAG_ID1 = 1;
    private static final long TAG_ID2 = 2;
    private static final String TAG_NATURE = "nature";
    private static final String TAG_FLOOD = "flood";

    @Autowired
    private TagDAO tagDAO;

    @Test
    public void getNewsTagsTest() throws DAOException {
        ArrayList<Tag> tags1 = new ArrayList<>();
        Tag tag1 = new Tag(TAG_ID1, TAG_NATURE);
        Tag tag2 = new Tag(TAG_ID2, TAG_FLOOD);
        tags1.add(tag1);
        tags1.add(tag2);
        ArrayList<Tag> tags2 = tagDAO.getNewsTags(NEWS_ID1);
        Assert.assertEquals(tags1, tags2);
    }

    @Test
    @ExpectedDatabase(value = "classpath:/data/expected-tag-data.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void createNewsTagTest() throws DAOException {
        tagDAO.createNewsTag(NEWS_ID2, TAG_ID1);
    }
}

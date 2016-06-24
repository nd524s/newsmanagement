package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Comment;
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
 * Created by Никита on 6/10/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@Transactional
@DatabaseSetup(value = "classpath:/data/comment-data.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:/data/comment-data.xml", type = DatabaseOperation.DELETE_ALL)
public class CommentDAOImplTest {
    private static final long NEWS_ID1 = 1;
    private static final Timestamp CREATION_DATE = Timestamp.valueOf("2016-06-02 10:43:33.000000");
    private static final long COMMENT_ID1 = 1;
    private static final String COMMENT_TEXT = "a";
    @Autowired
    private CommentDAO commentDAO;

    @Test
    public void getNewsComments() throws DAOException {
        Comment comment = new Comment(COMMENT_ID1, NEWS_ID1, COMMENT_TEXT, CREATION_DATE);
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(comment);
        ArrayList<Comment> comments1 = commentDAO.getNewsComments(NEWS_ID1);
        Assert.assertEquals(comments, comments1);
    }

    @Test
    public void createCommentTest() throws DAOException {
        Comment comment1 = new Comment(NEWS_ID1, COMMENT_TEXT, CREATION_DATE);
        Long commentId = commentDAO.create(comment1);
        comment1.setCommentId(commentId);
        Comment comment2 = commentDAO.getById(commentId);
        Assert.assertEquals(comment1, comment2);
    }

    @Test
    @ExpectedDatabase(value = "classpath:/data/expected-delete-comment-data.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void deleteCommentTest() throws DAOException {
        commentDAO.delete(COMMENT_ID1);
    }
}

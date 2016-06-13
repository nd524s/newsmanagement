package com.epam.newsmanagement.service;

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Comment;
import com.epam.newsmanagement.service.exception.ServiceException;
import com.epam.newsmanagement.service.impl.CommentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Created by Никита on 6/13/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {
    @InjectMocks
    @Autowired
    CommentServiceImpl commentService;

    @Mock
    CommentDAO commentDAO;
    private static final long COMMENT_ID = 1;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deleteComment() throws ServiceException, DAOException {
        commentService.deleteComment(anyLong());
        verify(commentDAO).delete(anyLong());
    }

    @Test
    public void createComment() throws DAOException, ServiceException {
        Comment comment = new Comment();
        when(commentDAO.create(comment)).thenReturn(COMMENT_ID);
        long commentId = commentService.createComment(comment);
        Assert.assertEquals(COMMENT_ID, commentId);
        verify(commentDAO).create(comment);
    }

}

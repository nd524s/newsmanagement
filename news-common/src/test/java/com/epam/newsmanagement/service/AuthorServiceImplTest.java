package com.epam.newsmanagement.service;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Author;
import com.epam.newsmanagement.service.exception.ServiceException;
import com.epam.newsmanagement.service.impl.AuthorServiceImpl;
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
public class AuthorServiceImplTest {
    @InjectMocks
    @Autowired
    AuthorServiceImpl authorService;
    @Mock
    AuthorDAO authorDAO;
    private static final long AUTHOR_ID = 1;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addNewsAuthorTest() throws ServiceException, DAOException {
        authorService.addNewsAuthor(anyLong(), anyLong());
        verify(authorDAO).createNewsAuthor(anyLong(), anyLong());
    }

    @Test
    public void createAuthor() throws DAOException, ServiceException {
        Author author = new Author();
        when(authorDAO.create(author)).thenReturn(AUTHOR_ID);
        long authorId = authorService.createAuthor(author);
        Assert.assertEquals(AUTHOR_ID, authorId);
        verify(authorDAO).create(author);
    }
}

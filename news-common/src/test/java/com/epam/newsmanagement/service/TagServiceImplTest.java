package com.epam.newsmanagement.service;

import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.service.exception.ServiceException;
import com.epam.newsmanagement.service.impl.TagServiceImpl;
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

/**
 * Created by Никита on 6/10/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class TagServiceImplTest {
    @InjectMocks
    @Autowired
    private TagServiceImpl tagService;

    @Mock
    private TagDAO tagDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addNewsTagTest() throws DAOException, ServiceException {
        tagService.addNewsTag(anyLong(), anyLong());
        verify(tagDAO).createNewsTag(anyLong(), anyLong());
    }


}

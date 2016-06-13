package com.epam.newsmanagement;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.dao.exception.DAOException;
import com.epam.newsmanagement.domain.Author;

import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.domain.SearchCriteria;
import com.epam.newsmanagement.domain.Tag;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by Никита on 26.05.2016.
 */

public class Main {

    private static NewsService newsService;

    public Main(){}

    public Main(NewsService newsService) {
        this.newsService = newsService;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
      //  AuthorDAO authorDAO = (AuthorDAO) applicationContext.getBean("authorDAOImpl");

//        ArrayList<Tag> tags = new ArrayList<>();
//        tags.add(new Tag());
//        tags.add(new Tag());
//        tags.get(0).setTagId(7);
//        tags.get(1).setTagId(0);
//        Author author = new Author();
//        author.setAuthorId(70);
//        SearchCriteria searchCriteria = new SearchCriteria();
//        searchCriteria.setTags(tags);
//        ArrayList<News> news = new ArrayList<>();
//        try {
//             news = newsService.searchBySearchCriteria(searchCriteria);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//        for (News news1 : news) {
//            System.out.println(news1.getNewsId());
//        }

    }
}

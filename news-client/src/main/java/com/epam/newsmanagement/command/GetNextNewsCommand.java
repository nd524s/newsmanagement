package com.epam.newsmanagement.command;

import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.exception.ServiceException;
import com.epam.newsmanagement.util.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Никита on 8/12/2016.
 */
@Component("getNextNews")
public class GetNextNewsCommand implements ActionCommand {
    private static final String NEWS_ID_PARAMETER = "id";
    private static final String SINGLE_NEWS_ATTRIBUTE = "news";
    @Autowired
    private NewsService newsService;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        long newsId = Long.parseLong(request.getParameter(NEWS_ID_PARAMETER));
        News news = newsService.getNextNews(newsId);
        request.setAttribute(SINGLE_NEWS_ATTRIBUTE, news);
        String page = ResourceManager.getProperty("page.singleNews");
        return page;
    }
}

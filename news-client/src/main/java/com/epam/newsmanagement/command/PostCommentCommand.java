package com.epam.newsmanagement.command;

import com.epam.newsmanagement.domain.Comment;
import com.epam.newsmanagement.domain.News;
import com.epam.newsmanagement.service.CommentService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.exception.ServiceException;
import com.epam.newsmanagement.util.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Никита on 8/11/2016.
 */
@Component("postComment")
public class PostCommentCommand implements ActionCommand {
    private static final String COMMENT_TEXT_PARAMETER = "comment";
    private static final String NEWS_ID_PARAMETER = "newsId";
    private static final String SINGLE_NEWS_ATTRIBUTE = "news";

    @Autowired
    private CommentService commentService;
    @Autowired
    private NewsService newsService;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        long newsId = Long.parseLong(request.getParameter(NEWS_ID_PARAMETER));
        String commentText = request.getParameter(COMMENT_TEXT_PARAMETER);
        Comment comment = new Comment(newsId, commentText, new Timestamp(new Date().getTime()));
        commentService.createComment(comment);
        News news = newsService.getSingleNews(newsId);
        request.setAttribute(SINGLE_NEWS_ATTRIBUTE, news);
        String page = ResourceManager.getProperty("page.singleNews");
        return page;
    }
}

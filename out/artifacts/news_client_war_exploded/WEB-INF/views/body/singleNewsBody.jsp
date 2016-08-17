<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 6/20/2016
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/viewNews.css">
</head>
<body>

<a href="/client?command=getNewsList" class="back">Back</a>

<div class="cont">
    <div class="title">
        <h3><c:out value="${news.title}"/></h3>
    </div>
    <div class="author">
        (by ${news.authors[0].authorName})
    </div>
    <span class="date"><fmt:formatDate pattern="dd/MM/yyyy" value="${news.modificationDate}"/></span>
    <div class="full-text">
        <c:out value="${news.fullText}"/>
    </div>
    <c:forEach var="com" items="${news.comments}">
        <span class="comments">
            <fmt:formatDate pattern="dd/MM/yyyy" value="${com.creationDate}"/>
        </span>
            <div class="comment">
                <input type="hidden" name="newsId" value="${news.newsId}">
                <input type="hidden" name="commentId" value="${com.commentId}">
                <c:out value="${com.commentText}"/>
            </div>
    </c:forEach>
    <div class="text-area">
        <textarea name="comment" form="postComment" rows="4" cols="80" required></textarea>
    </div>

    <form id="postComment" action="/client?command=postComment" method="post">
        <input type="hidden" name="newsId" value="${news.newsId}">
        <div>
            <input type="submit" class="button" value="Post comment">
        </div>
    </form>

    <div>
        <a href="/client?command=getPreviousNews&id=${news.newsId}" class="previous">Previous</a>
        <a href="/client?command=getNextNews&id=${news.newsId}" class="next">Next</a>
    </div>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 6/24/2016
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Single News</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/viewNews.css">
</head>
<body>
<div>
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
        <form action="/admin/deleteComment" method="post">
            <div class="comment">
                <input type="hidden" name="newsId" value="${news.newsId}">
                <input type="hidden" name="commentId" value="${com.commentId}">
                <input type="image" src="${pageContext.request.contextPath}/resources/image/exit.jpg" width="20" height="20" class="exit">
                <c:out value="${com.commentText}"/>
            </div>
        </form>
    </c:forEach>
    <div class="text-area">
        <textarea name="comment" form="postComment" rows="4" cols="80">
        </textarea>
    </div>

    <form id="postComment" action="/admin/postComment" method="post">
        <input type="hidden" name="newsId" value="${news.newsId}">
        <div>
            <input type="submit" class="button" value="Post comment">
        </div>
    </form>

    <div>
        <a href="/admin/previousNews?id=${news.newsId}" class="previous">Previous</a>
        <a href="/admin/nextNews?id=${news.newsId}" class="next">Next</a>
    </div>
</div>
</body>
</html>

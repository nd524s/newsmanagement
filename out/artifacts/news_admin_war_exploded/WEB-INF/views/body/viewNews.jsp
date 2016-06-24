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
    <link rel="stylesheet" href="/resources/css/viewNews-style.css">
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
        <div class="comment">
            <a href="http://localhost:8181/admin/newsList"><img src="/resources/image/exit.jpg" width="20" height="20" class="exit"></a>
            <c:out value="${com.commentText}"/>
        </div>
    </c:forEach>
    <div class="text-area" >
        <textarea rows="4" cols="80">
        </textarea>
    </div>
    <div>
        <a href="#"><input  type="button" class="button" value="Post comment"></a>
    </div>
    <div>
        <a href="http://localhost:8181/admin/previousNews?id=${news.newsId}" class="previous">Previous</a>
        <a href="http://localhost:8181/admin/nextNews?id=${news.newsId}" class="next">Next</a>
    </div>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 8/2/2016
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit news</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/css/jquery.multiselect.css"/>" />
    <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/addNews.css">
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body>
    <form id="editNews" action="/admin/updateNews" method="post">
        <div style="margin-left: 70px">
            <div class="title">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" value="${currentNews.title}" size="79"/>
            </div>

            <div class="date">
                <label for="date">Date:</label>
                <input type="text" id="date" value="${creationDate}" readonly/>
            </div>

            <div class="brief">
                <label for="shortText">Brief:</label>
                <textarea id="shortText" name="shortText" rows="4" cols="80">${currentNews.shortText}</textarea>
            </div>
            <div class="content">
                <label for="fullText">Content:</label>
                <textarea id="fullText" name="fullText" rows="6" cols="80">${currentNews.fullText}</textarea>
            </div>

            <div class="button-position" style="margin-top: 30px;">
                <input type="hidden" name="newsId" value="${currentNews.newsId}">
                <input class="button-save" type="submit" value="Update">
            </div>
        </div>
    </form>
</body>
</html>

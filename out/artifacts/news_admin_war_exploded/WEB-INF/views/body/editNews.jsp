<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addNews.css">
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
            <div>
                <select name="author" class="dropdown-position">
                    <c:forEach var="auth" items="${authors}">
                        <c:choose>
                            <c:when test="${auth.authorName == currentNews.authors.get(0).authorName}">
                                <option value="${currentNews.authors.get(0).authorId}" selected="selected">${currentNews.authors.get(0).authorName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${auth.authorId}">${auth.authorName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <select name="tags" multiple="multiple" class="dropdown-position">
                    <c:forEach var="tag" items="${tags}">
                        <c:choose>
                            <c:when test="${fn:contains(currentNews.tags, tag)}">
                                <option value="${tag.tagId}" selected="selected">${tag.tagName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${tag.tagId}">${tag.tagName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="button-position" style="margin-top: 30px;">
                <input type="hidden" name="newsId" value="${currentNews.newsId}">
                <input class="button-save" type="submit" value="Update">
            </div>
        </div>
    </form>
    <script src="<c:url value="/resources/js/jquery.multiselect.js"/>"></script>

    <script>
        $(function() {
            $('select[multiple]').multiselect();
        })
    </script>
</body>
</html>

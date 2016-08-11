<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 6/20/2016
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News List</title>
    <%--<link rel="stylesheet" href="/resources/css/jquery.multiselect.css">--%>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/css/jquery.multiselect.css"/>" />
    <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/newsList.css"/>">
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body>
<div>
    <form id="filterForm" action="/admin/search" method="post">
        <div>
            <select id="authors" name="author" class="dropdown-position">
                <option value="">--Authors--</option>
                <c:forEach var="auth" items="${authors}">
                    <option value="${auth.authorId}">${auth.authorName}</option>
                </c:forEach>
            </select>
            <select id="tags" name="tags" multiple="multiple" class="dropdown-position">
                <c:forEach var="tag" items="${tags}">
                    <option value="${tag.tagId}">${tag.tagName}</option>
                </c:forEach>
            </select>
        <input type="submit" value="Filter">
        <a href="/admin/newsList"><input type="button" value="Reset"></a>
        </div>
    </form>

    <form action="/admin/deleteNews" id="deleteForm" method="post">
        <c:forEach var="news" items="${newses}" varStatus="newsNumber">
            <div style="margin-top: 40px">
                <div >
                    <span class="title"><a href="/admin/viewNews?id=${news.newsId}" ><c:out value="${news.title}"/></a></span>
                    <span class="author">(<c:out value="${news.authors[0].authorName}"/>)</span>
                    <span class="date"><fmt:formatDate pattern="dd/MM/yyyy" value="${news.modificationDate}"/></span>
                </div>

                <div class="short-text">
                <span>
                    <c:out value="${news.shortText}"/>
                </span>
                </div>

                <div>
                    <div class="tags">
                        <c:forEach var="tag" items="${news.tags}" varStatus="num">
                            <c:if test="${num.count == (fn:length(news.tags))}">
                                <c:out value="${tag.tagName}"/>
                            </c:if>
                            <c:if test="${num.count != ((fn:length(news.tags)))}">
                                <c:out value="${tag.tagName}"/>,
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="comments">Comments(${fn:length(news.comments)})</div>
                    <div class="edit">
                        <a href="/admin/editNews?id=${news.newsId}">Edit</a>
                    </div>
                        <input type="checkbox" name="deleteItem" value="${news.newsId}">
                </div>
                <c:if test="${newsNumber.count == fn:length(newses)}">
                    <div class="delete">
                        <input form="deleteForm" type="submit" class="button" value="Delete">
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </form>
    <div class="pagination"></div>
</div>

<script src="<c:url value="/resources/js/jquery.multiselect.js"/>"></script>
<script>
    $(function() {
        $('select[multiple]').multiselect();
    })
</script>
</body>
</html>

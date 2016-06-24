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
    <link rel="stylesheet" href="/resources/css/newsList-style.css">
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body>
<div>
    <div>
        <select id="authors" class="dropdown-position">
            <option value="volvo">Volvo</option>
            <option value="saab">Saab</option>
            <option value="opel">Opel</option>
            <option value="audi">Audi</option>
        </select>
        <select id="tags" multiple="multiple" class="dropdown-position">
            <option value="1">January</option>
            <option value="12">December</option>
        </select>
        <a href="#"><input type="button" value="Filter"></a>
        <a href="#"><input type="button" value="Reset"></a>
    </div>

    <c:forEach var="news" items="${newses}" varStatus="newsNumber">
        <div style="margin-top: 40px">
            <div >
                <span class="title"><a href="http://localhost:8181/admin/viewNews?id=${news.newsId}" ><c:out value="${news.title}"/></a></span>
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
                    <a href="" style="color: red" >Edit</a>
                </div>
                <input type="checkbox" value="a2">
            </div>
            <c:if test="${newsNumber.count == fn:length(newses)}">
                <div class="delete">
                    <a href="#"><input  type="button" class="button" value="Delete"></a>
                </div>
            </c:if>
        </div>
    </c:forEach>
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

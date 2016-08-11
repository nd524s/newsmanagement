<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 6/27/2016
  Time: 6:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add News</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/css/jquery.multiselect.css"/>" />
    <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addNews.css">
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body>
<form:form id="addNews" action="/admin/saveNews" method="post" commandName="news">
    <div style="margin-left: 70px">
        <div class="title">
            <form:label for="title" path="title" >Title:</form:label>
            <form:input type="text" id="title" name="title" size="79" path="title"/>
        </div>
        <jsp:useBean id="now" class="java.util.Date" />

        <div class="date">
            <label for="date">Date:</label>
            <input type="text" id="date" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${now}"/>" readonly/>
        </div>

        <div class="brief">
            <form:label for="shortText" path="shortText">Brief:</form:label>
            <form:textarea id="shortText" name="shortText" rows="4" cols="80" path="shortText"/>
        </div>

        <div class="content">
            <form:label for="fullText" path="fullText">Content:</form:label>
            <form:textarea id="fullText" rows="6" cols="80" path="fullText"/>
        </div>

        <div>
            <form:select path="author" multiple="false" class="dropdown-position">
                <form:option value="NONE" label="--- Authors ---" />
                <form:options items="${authors}" itemLabel="authorName" itemValue="authorId"/>
            </form:select>
            <form:select name="tags" multiple="multiple" class="dropdown-position" path="tags">
                <form:options items="${tags}" itemLabel="tagName" itemValue="tagId"/>
            </form:select>
        </div>

        <div class="button-position">
            <input class="button-save" type="submit" value="Save">
        </div>
    </div>
</form:form>

<script src="<c:url value="/resources/js/jquery.multiselect.js"/>"></script>

<script>
    $(function() {
        $('select[multiple]').multiselect();
    })
</script>
</body>
</html>

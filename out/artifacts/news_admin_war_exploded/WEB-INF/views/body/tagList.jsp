<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 8/1/2016
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tagList.css">
</head>
<body>
<div class="container">
    <c:forEach items="${tags}" var="tag" varStatus="number">
        <c:set var="update" value="update${number.count}"/>
        <c:set var="delete" value="delete${number.count}"/>
        <c:set var="cancel" value="cancel${number.count}"/>
        <c:set var="tagVar" value="tagVar${number.count}"/>
        <c:set var="edit" value="edit${number.count}"/>
        <c:set var="deleteForm" value="deleteForm${number.count}"/>
        <c:set var="updateForm" value="updateForm${number.count}"/>

        <div class="tag">
            <form action="/admin/deleteTag" id="${deleteForm}" method="post">
                <input type="hidden" name="tagId" value="${tag.tagId}">
            </form>
            <form action="/admin/updateTag" id="${updateForm}" method="post" style="display: inline">
                <label for="${authorVar}">Tag:</label>
                <input type="text" name="tagName" id="${tagVar}" size="60" value="${tag.tagName}" disabled>
                <input type="hidden" name="tagId" value="${tag.tagId}">
            </form>
            <button type="submit" form="${updateForm}" class="edit-update" id="${update}"style="display: none">update</button>
            <button type="submit" form="${deleteForm}" class="delete-cancel" id="${delete}"style="display: none">delete</button>

            <button href="#"  class="delete-cancel" id="${cancel}"style="display: none"
                    onclick="this.style.display = 'none';
                            getElementById('${update}').style.display = 'none';
                            getElementById('${delete}').style.display = 'none';
                            getElementById('${edit}').style.display = 'inline';
                            getElementById('${tagVar}').disabled = true;
                            return false;">cancel</button>

            <button class="edit-update" id="${edit}" onclick="this.style.display = 'none';
                    getElementById('${update}').style.display = 'inline';
                    getElementById('${delete}').style.display = 'inline';
                    getElementById('${cancel}').style.display = 'inline';
                    getElementById('${tagVar}').disabled = false;
                    return false;">edit</button>
        </div>
    </c:forEach>
    <div class="tag-save">
        <form action="/admin/saveTag" id="saveForm" method="post">
            <label for="save">Add tag:</label>
            <input type="text" id="save" name="tagName" size="60">
            <button type="submit" form="saveForm" class="save-link">save</button>
        </form>
    </div>
</div>
</body>
</html>

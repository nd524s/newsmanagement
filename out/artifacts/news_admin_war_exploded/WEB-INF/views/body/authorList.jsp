<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 7/4/2016
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/authorList.css">
</head>
<body>
<div class="container">
    <c:forEach items="${authors}" var="author" varStatus="number">
        <c:set var="update" value="update${number.count}"/>
        <c:set var="expire" value="expire${number.count}"/>
        <c:set var="cancel" value="cancel${number.count}"/>
        <c:set var="authorVar" value="authorVar${number.count}"/>
        <c:set var="edit" value="edit${number.count}"/>
        <c:set var="expireForm" value="expireForm${number.count}"/>
        <c:set var="updateForm" value="updateForm${number.count}"/>

        <div class="author">
            <form action="/admin/expireAuthor" id="${expireForm}" method="post">
                <input type="hidden" name="authorId" value="${author.authorId}">
                <input type="hidden" name="authorName" value="${author.authorName}">
            </form>
            <form action="/admin/updateAuthor" id="${updateForm}" method="post" style="display: inline">
                <label for="${authorVar}">Author:</label>
                <input type="text" name="authorName" id="${authorVar}" size="60" value="${author.authorName}" disabled>
                <input type="hidden" name="authorId" value="${author.authorId}">
                <input type="hidden" name="expired" value="${author.expired}">
            </form>
            <button type="submit" form="${updateForm}" class="edit-update" id="${update}"style="display: none">update</button>
            <button type="submit" form="${expireForm}" class="expire-cancel" id="${expire}"style="display: none">expire</button>

            <button href="#"  class="expire-cancel" id="${cancel}"style="display: none"
               onclick="this.style.display = 'none';
                       getElementById('${update}').style.display = 'none';
                       getElementById('${expire}').style.display = 'none';
                       getElementById('${edit}').style.display = 'inline';
                       getElementById('${authorVar}').disabled = true;
                       return false;">cancel</button>

            <button class="edit-update" id="${edit}" onclick="this.style.display = 'none';
                    getElementById('${update}').style.display = 'inline';
                    getElementById('${expire}').style.display = 'inline';
                    getElementById('${cancel}').style.display = 'inline';
                    getElementById('${authorVar}').disabled = false;
                    return false;">edit</button>
        </div>
    </c:forEach>
    <div class="author-save">
        <form action="/admin/saveAuthor" id="saveForm" method="post">
            <label for="save">Add Author:</label>
            <input type="text" id="save" name="authorName" size="60">
            <button type="submit" form="saveForm" class="save-link">save</button>
        </form>
    </div>
</div>
</body>
</html>

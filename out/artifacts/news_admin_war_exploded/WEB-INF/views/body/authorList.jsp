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

        <div class="author">
            <label for="${authorVar}">Author:</label>
            <input type="text" id="${authorVar}" size="60" value="${author.authorName}" disabled>
            <a href="#"  class="edit-update" id="${update}"style="display: none">update</a>
            <a href="#"  class="expire-cancel" id="${expire}"style="display: none">expire</a>
            <a href="#"  class="expire-cancel" id="${cancel}"style="display: none"
               onclick="this.style.display = 'none';
                       getElementById('${update}').style.display = 'none';
                       getElementById('${expire}').style.display = 'none';
                       getElementById('${edit}').style.display = 'inline';
                       getElementById('${authorVar}').disabled = true;
                       return false;">cancel</a>

            <a href="#" class="edit-update" id="${edit}" onclick="this.style.display = 'none';
                    getElementById('${update}').style.display = 'inline';
                    getElementById('${expire}').style.display = 'inline';
                    getElementById('${cancel}').style.display = 'inline';
                    getElementById('${authorVar}').disabled = false;
                    return false;">edit</a>
        </div>
    </c:forEach>
    <div class="author-save">
        <label for="save">Add Author:</label>
        <input type="text" id="save" size="60">
        <a href="#" class="save-link">save</a>
    </div>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 6/20/2016
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css" type="text/css">
</head>
<body>
<div class="bord position-header">
    <h1 class="h1-header">News Portal - Administration</h1>
    <div class="top-right">
        <h3 class="h3-header">Hello, Admin Admin</h3>
    </div>
    <div class="right">
        <a href="#"><input  type="button" class="button" value="Logout"></a>
    </div>
    <div class="bottom-right">
        <a href="#">EN</a>
        <a href="#">RU</a>
    </div>
</div>
</body>
</html>
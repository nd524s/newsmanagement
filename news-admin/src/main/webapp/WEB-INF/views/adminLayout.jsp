<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 6/20/2016
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/layout.css">
</head>
<body>
<header>
    <tiles:insertAttribute name="header"/>
</header>

<div class="sidebar-position">
    <tiles:insertAttribute name="sidebar"/>
</div>


<div class="content-position" style="margin-left: 20%">
    <tiles:insertAttribute name="body"/>
</div>
<footer>
    <tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>

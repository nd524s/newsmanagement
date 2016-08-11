<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="<c:url value="/resources/css/layout.css"/>">
</head>
<body>
<header>
    <tiles:insertAttribute name="header"/>
</header>

<div class="content-position">
    <tiles:insertAttribute name="body"/>
</div>
<footer>
    <tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>
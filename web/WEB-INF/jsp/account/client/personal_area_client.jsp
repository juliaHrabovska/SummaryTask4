<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 09.09.2017
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Personal area</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
<div class="content">
        <h1>Entrance data</h1>
        <h3><c:out value="${enrollee.first_name}"/></h3><br>
        <h3><c:out value="${enrollee.last_name}"/></h3><br>
        <h3><c:out value="${enrollee.patronymic}"/></h3><br>
    </div>
</body>
</html>

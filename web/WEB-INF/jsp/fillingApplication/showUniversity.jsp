<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14.09.2017
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Universities</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<c:choose>
    <c:when test="${account.getRole_id().getName() == 'admin' }">
        <%@ include file="/WEB-INF/jspf/leftMenuAdmin.jspf" %>
    </c:when>
    <c:when test="${account.getRole_id().getName() == 'client'}">
        <%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
    </c:when>
</c:choose>
<div class="content">
    <c:forEach var="university" items="${university_list}">
        <a href="controller?command=universityPage&university_id=${university.id}">${university.name}</a>
    </c:forEach>
</div>
</body>
</html>

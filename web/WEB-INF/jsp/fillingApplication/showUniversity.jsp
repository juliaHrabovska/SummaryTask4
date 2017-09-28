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
</head>
<body>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-11 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header" style="margin-top: 5px;"><fmt:message key="chooseUniversity"/>:</h1>
            <table class="table table-hover">
                <c:forEach var="university" items="${university_list}">
                    <tr>
                        <td>
                            <a href="controller?command=universityPage&university_id=${university.id}">${university.name}</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<c:choose>
    <c:when test="${account.getRole_id().getName() == 'admin' }">
        <%@ include file="/WEB-INF/jspf/leftMenuAdmin.jspf" %>
    </c:when>
    <c:when test="${account.getRole_id().getName() == 'client'}">
        <%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
    </c:when>
</c:choose>
<script src="/bootstrap/js/bootstrap.js"></script>
</body>
</html>

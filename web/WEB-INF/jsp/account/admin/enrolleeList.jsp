<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.09.2017
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Enrollee list</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/leftMenuAdmin.jspf" %>
<div class="content">
    <table border="1">
        <tr>
            <th>&#8470;</th>
            <th>Full name</th>
            <th>Certificate score</th>
            <th>Level of training</th>
            <th>Certificate</th>
            <th>Ban status</th>
            <th>Change ban status</th>
        </tr>
        <c:set var="k" value="0"/>
        <c:forEach var="enrollee" items="${enrollee_list}">
            <c:set var="k" value="${k+1}"/>
            <tr>
                <td><c:out value="${k}"/></td>
                <td>
                    <a href="controller?command=enrolleeInfo&enrolleeId=${enrollee.getId()}">
                            ${enrollee.getFirst_name()}<br>
                            ${enrollee.getLast_name()}<br>
                            ${enrollee.getPatronymic()}<br>
                    </a>
                </td>
                <td>
                        ${enrollee.getCertificate_score()}<br>
                </td>
                <td>
                        ${enrollee.getLevel()}<br>
                </td>
                <td>
                    <a href="${enrollee.getCertificate_path()}">
                        <img style="width:50px;height:50px;"
                             src="${enrollee.getCertificate_path()}"/>
                    </a>
                </td>
                <td>
                        ${enrollee.is_banned()}<br>
                </td>
                <td>
                    <label>
                        <p><a href="controller?command=ban_unbanUser&enrolleeId=${enrollee.id}">Change</a>
                    </label>
                </td>
            </tr>

        </c:forEach>
    </table>
</div>
</body>
</html>

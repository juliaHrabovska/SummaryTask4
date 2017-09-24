<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 22.09.2017
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Enrollee page</title><link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/leftMenuAdmin.jspf" %>

<div class="content">
    <form action="controller" method="get">
        <table border="1">
            <tr>
                <th>Status</th>
                <th>University</th>
                <th>Type of training</th>
                <th>Department</th>
                <th>Cathedra</th>
                <th>Change status</th>
            </tr>
            <c:forEach var="submApp" items="${submitted_app}">
                <tr>
                    <td>
                            ${submApp.getStatus()}
                    </td>
                    <td>
                            ${submApp.getUniversity()}
                    </td>
                    <td>
                            ${submApp.getType_of_training()}
                    </td>
                    <td>
                            ${submApp.getDepartment()}
                    </td>
                    <td>
                            ${submApp.getCathedra()}
                    </td>
                    <td>
                        <p><a href="controller?command=changeStatus&enrolleeId=${enrollee_id}&status=1&cathedraId=${submApp.getCathedra_id()}">Registered</a>
                        <p><a href="controller?command=changeStatus&enrolleeId=${enrollee_id}&status=2&cathedraId=${submApp.getCathedra_id()}">Recommended</a>
                        <p><a href="controller?command=changeStatus&enrolleeId=${enrollee_id}&status=3&cathedraId=${submApp.getCathedra_id()}">Enlisted</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</body>
</html>

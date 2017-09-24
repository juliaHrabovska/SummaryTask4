<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18.09.2017
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Submitted applications</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
<div class="content">
    <form action="controller" method="get">
        <input type="hidden" name="command" value="deleteApp"/>
        <table border="1">
            <tr>
                <th>Status</th>
                <th>University</th>
                <th>Type of training</th>
                <th>Department</th>
                <th>Cathedra</th>
                <th>Delete</th>
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
                        <input type="checkbox" name="cathedraId"
                                       value="${submApp.getCathedra_id()}">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" name="Delete" value="Delete applications"/>
    </form>
</div>
</body>
</html>

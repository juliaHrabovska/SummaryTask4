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
</head>
<body>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>
<div class="container-fluid">
    <div class="row">

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header" style="margin-top: 5px;">Submitted applications</h1>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="deleteApp"/>
                <table class="table table-bordered table-hover">
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
                <div class="col-md-3 col-xs-6 placeholder">
                    <a style="text-decoration:none" href="controller?command=submitted_applications">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Delete applications</button>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
<script src="/bootstrap/js/bootstrap.js"></script>
</body>
</html>

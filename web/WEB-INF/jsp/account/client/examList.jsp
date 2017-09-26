<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 19.09.2017
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Exams</title>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container" style="margin-top: 40px; width: 800px;">
    <div class="row" style="margin-left: 0;">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Choose exams</h3>
            </div>
            <div class="panel-body">
                <div class="col-sm-11 main">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="showChosenExamList">
                        <table class="table table-bordered table-hover">
                            <tr>
                                <th>&#8470;</th>
                                <th>Exam name</th>
                                <th>Check</th>
                            </tr>
                            <c:set var="k" value="0"/>
                            <c:forEach var="exam" items="${exam_list}">
                                <c:set var="k" value="${k+1}"/>
                                <tr>
                                    <td><c:out value="${k}"/></td>
                                    <td>
                                            ${exam.getName()}<br>
                                    </td>
                                    <td>
                                        <input type="checkbox" name="examID"
                                               value="${exam.id}">
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="col-md-3 col-xs-6 placeholder">
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Choose</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

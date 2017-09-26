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

</head>
<body>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>
<div class="container-fluid">
    <div class="row">

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header" style="margin-top: 5px;">Entrance data</h1>

            <div class="row placeholders">

                <div class="col-xs-6 placeholder">
                    <h3>Full name:</h3>
                    <h4><span style="text-align: left;"><c:out value="${enrollee.last_name}"/> <c:out
                            value="${enrollee.first_name}"/> <c:out value="${enrollee.patronymic}"/></span></h4>
                </div>

                <div class="col-xs-6 placeholder">
                    <h3>Certificate:</h3>
                    <a href="${enrollee.getCertificate_path()}">
                        <img style="border-radius:0; width:450px;height:300px;"
                             src="${enrollee.getCertificate_path()}"/>
                    </a>
                </div>
            </div>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Exam results</h1>
            <div class="col-md-10 col-xs-6 placeholder">
                <table class="table table-bordered table-hover">
                    <tr>
                        <th>&#8470;</th>
                        <th>Exam name</th>
                        <th>Result</th>
                    </tr>
                    <c:set var="k" value="0"/>
                    <c:forEach var="exam" items="${results}">
                        <c:set var="k" value="${k+1}"/>
                        <tr>
                            <td><c:out value="${k}"/></td>
                            <td>
                                    ${exam.getKey()}
                            </td>
                            <td>
                                    ${exam.getValue()}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="col-md-4 col-xs-6 placeholder">
                    <a style="text-decoration:none" href="controller?command=showPlace">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Filing applications</button>
                    </a>
                </div>
                <div class="col-md-4 col-xs-6 placeholder">
                    <a style="text-decoration:none" href="controller?command=submitted_applications">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Submitted applications</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
<script src="/bootstrap/js/bootstrap.js"></script>
</body>
</html>

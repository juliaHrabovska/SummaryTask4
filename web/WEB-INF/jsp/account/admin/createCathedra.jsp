<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25.09.2017
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Create cathedra</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>
<div class="container-fluid">
    <div class="row">

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header" style="margin-top: 5px;">Create new cathedra</h1>
            <c:if test="${not empty error}">
                <div class="warningbox">
                    <div class="innerbox">
                        <p><c:out value="${error}"/>
                    </div>
                </div>
            </c:if>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="createCathedra">

                <div class="row placeholders">
                    <div class="form-group">
                        <label for="exampleInputName">Name: </label>
                        <input name="name" type="text" class="form-control" id="exampleInputName"
                               placeholder="Name" required>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputName">Number of budget places:</label>
                        <input name="budget" min=0 max=100 type="number" class="form-control" id="exampleInputBudget"
                               required>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputName">Number of contract places:</label>
                        <input name="contract" min=0 max=100 type="number" class="form-control"
                               id="exampleInputContract" required>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputName">Level</label><br>
                        <label class="radio-inline">
                            <input type="radio" name="level_of_training" id="inlineRadio1" value="1" checked>
                            Bachelor
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="level_of_training" id="inlineRadio2" value="2"> Master
                        </label>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputName">Type</label><br>
                        <label class="radio-inline">
                            <input type="radio" name="type_of_training" id="inlineRadio3" value="1" checked>
                            Full time
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="type_of_training" id="inlineRadio4" value="2">Distance
                        </label>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputName">Department:</label><br>
                        <table class="table table-bordered table-hover">
                            <tr>
                                <th>&#8470;</th>
                                <th>Department name</th>
                                <th>Check</th>
                            </tr>
                            <c:set var="k" value="0"/>
                            <c:forEach var="department" items="${departments}">
                                <c:set var="k" value="${k+1}"/>
                                <tr>
                                    <td><c:out value="${k}"/></td>
                                    <td>
                                            ${department.getName()}<br>
                                    </td>
                                    <td>
                                        <input type="radio" name="departmentId"
                                               value="${department.id}">
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputName">Requirements:</label><br>
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
                    </div>
                </div>
                <div class="row col-md-4 col-xs-6 placeholder">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/leftMenuAdmin.jspf" %>

</body>
</html>

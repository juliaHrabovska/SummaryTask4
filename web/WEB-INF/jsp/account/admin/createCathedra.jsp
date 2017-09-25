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
<form action="controller" method="post">
    <input type="hidden" name="command" value="createCathedra">
    <input type="hidden" name="department_id" value="${department_id}">

    <p>Name: </p><br>
    <input name="name">
    <p>Number of budget places:</p><br>
    <input type="number" name="budget">
    <p>Namber of contract places:</p><br>
    <input type="number" name="contract">
    <p>Type of training:</p><br>
    <input type="radio" name="type_of_training" value="1" checked>Full time<Br>
    <input type="radio" name="type_of_training" value="2">Distance<Br>
    <p>Level of training:</p><br>
    <input type="radio" name="level_of_training" value="1" checked>Bachelor<Br>
    <input type="radio" name="level_of_training" value="2">Master<Br>

    <p>Department:</p>
    <table border="1">
        <tr>
            <th>&#8470;</th>
            <th>Exam name</th>
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

    <p>Exams:</p>
    <table border="1">
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
    <input type="submit" value="Add">
</form>
</body>
</html>

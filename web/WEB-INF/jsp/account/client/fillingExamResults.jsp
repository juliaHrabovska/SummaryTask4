<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.09.2017
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exam results</title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="fillExRes">
    <input type="hidden" name="chosen_exam_list" value="${exam_list}">
    <table border="1">
        <tr>
            <th>&#8470;</th>
            <th>Exam name</th>
            <th>Result</th>
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
                    <input name="exam_${exam.getId()}">
                </td>

            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="Save" value="Check"/>
</form>
</body>
</html>

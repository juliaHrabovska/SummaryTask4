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

</head>
<body>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="showChosenExamList">
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
        <input type="submit" value="Choose"/>
    </form>
</body>
</html>

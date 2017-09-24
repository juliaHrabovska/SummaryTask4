<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17.09.2017
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Contest</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<c:choose>
    <c:when test="${account.getRole_id().getName() == 'admin' }">
        <%@ include file="/WEB-INF/jspf/leftMenuAdmin.jspf" %>
    </c:when>
    <c:when test="${account.getRole_id().getName() == 'client'}">
    <%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
    </c:when>
</c:choose>

<div class="content">
    <table border="1">
        <tr>
            <td>№</td>
            <td>Full name of enrolle</td>
            <td>Status</td>
            <td>Competitive score</td>
            <td>Exam results</td>
            <c:choose>
                <c:when test="${account.getRole_id().getName() == 'admin' }">
                    <td>Change status</td>
                </c:when>
            </c:choose>

        </tr>
        <c:set var="k" value="0"/>
        <c:forEach var="contest" items="${contest}">
            <c:set var="k" value="${k+1}"/>
            <tr>
                <td><c:out value="${k}"/></td>
                <td>
                        ${contest.getEnrollee_first_name()}
                        ${contest.getEnrollee_last_name()}
                        ${contest.getEnrollee_patronymic()}
                </td>
                <td>
                        ${contest.getStatus()}
                </td>
                <td>
                        ${contest.getCompetitive_score()}
                </td>
                <td>
                    <c:forEach var="results" items="${contest.getExam_results()}">
                        ${results.getKey()}:${results.getValue()}<br>
                    </c:forEach>
                </td>
                <c:choose>
                    <c:when test="${account.getRole_id().getName() == 'admin' }">
                        <td>
                            <p>
                                <a href="controller?command=changeStatus&enrolleeId=${contest.getEnrolleeId()}&status=1&cathedraId=${cathedra_id}">Registered</a>
                            <p>
                                <a href="controller?command=changeStatus&enrolleeId=${contest.getEnrolleeId()}&status=2&cathedraId=${cathedra_id}">Recommended</a>
                            <p>
                                <a href="controller?command=changeStatus&enrolleeId=${contest.getEnrolleeId()}&status=3&cathedraId=${cathedra_id}">Enlisted</a>
                        </td>
                    </c:when>
                </c:choose>
            </tr>
        </c:forEach>

    </table>

    <c:choose>
        <c:when test="${account.getRole_id().getName() == 'client'}">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="apply">
                <input type="hidden" name="cathedra_id" value="${cathedra_id}">
                <input type="submit" value="Apply">
            </form>
        </c:when>
    </c:choose>


</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14.09.2017
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/css/tabs.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
<div class="content">
    <h1>${university.name}</h1>

    <%--<p> Sort by--%>
    <%--<table>--%>
    <%--<tr>--%>
    <%--<td>--%>
    <%--<select>--%>
    <%--<option disabled>Name of department</option>--%>
    <%--<option><a href="controller?command=sort&typeOfSorting=NI">A-Z</a></option>--%>
    <%--<option><a href="controller?command=sort&typeOfSorting=ND">Z-A</a></option>--%>
    <%--</select>--%>
    <%--</td>--%>
    <%--<td>--%>
    <%--<p>Number of budget places</p>--%>
    <%--<br>--%>
    <%--<select>--%>
    <%--<option disabled>Number of budget places</option>--%>
    <%--<option><a href="controller?command=sort&typeOfSorting=BI">From largest to smallest</a></option>--%>
    <%--<option><a href="controller?command=sort&typeOfSorting=BD">From smallest to largest</a></option>--%>
    <%--</select>--%>
    <%--</td>--%>
    <%--<td>--%>
    <%--<select>--%>
    <%--<option disabled>Licensed volume</option>--%>
    <%--<a href="controller?command=sort&typeOfSorting=LI">--%>
    <%--<option>From largest to smallest</option>--%>
    <%--</a>--%>
    <%--<option><a href="controller?command=sort&typeOfSorting=LD">From smallest to largest</a></option>--%>
    <%--</select>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--</table>--%>


    <!-- Сами вкладки -->
    <ul class="pixel-tabs" id="type-of-training">
        <li><a href="#content-fullTime" id="fullTime">Full time</a></li>
        <li><a href="#content-distance" id="distance">Distance</a></li>
    </ul>

    <!-- Содержание -->
    <div class="content-region" id="content-type-of-training">

        <!-- Первая вкладка -->
        <div id="content-fullTime">

            <ul class="pixel-tabs" id="fullTime-input">
                <li><a href="#content-fullTime-bachelor" id="bachelor1">Bachelor</a></li>
                <li><a href="#content-fullTime-master" id="master">Master</a></li>
            </ul>

            <!-- Содержание -->
            <div class="content-region" id="content-fullTime-input">

                <!-- Первая вкладка -->
                <div id="content-fullTime-bachelor">
                    <table border="1">
                        <tr>
                            <th>Speciality</th>
                            <th>Contest</th>
                            <th>Volume</th>
                            <th>Requirements</th>
                        </tr>
                        <c:forEach var="cathedra" items="${cathedra_list}">
                            <c:choose>
                                <c:when test="${cathedra.getLevel_of_training() == 'bachelor' and cathedra.getType_of_training() == 'full time'}">
                                    <tr>
                                        <td>
                                                ${cathedra.level_of_training}<br>
                                                ${cathedra.department_name}<br>
                                                ${cathedra.name}
                                        </td>
                                        <td>
                                                ${cathedra.getStatement()}<br>
                                                ${cathedra.getRecommended()}<br>
                                                ${cathedra.getEnlisted()}<br>
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command" value="contest">
                                                <input type="hidden" name="cathedra_id" value="${cathedra.getId()}">
                                                <input type="submit" name="cathedra_info" value="contest">
                                            </form>

                                        </td>
                                        <td>
                                                ${cathedra.getLicensed_volume()}<br>
                                                ${cathedra.getLicensed_volume_budget()}<br>
                                                ${cathedra.getLicensed_volume_contract()}
                                        </td>
                                        <td>

                                            <c:forEach var="requirement" items="${cathedra.getRequirements()}">
                                                ${requirement}<br>
                                            </c:forEach>
                                            <p>Certificate score</p>
                                        </td>
                                    </tr>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </table>
                    <br>

                </div>

                <!-- Вторая вкладка -->
                <div id="content-fullTime-master">
                    <table border="1">
                        <tr>
                            <th>Speciality</th>
                            <th>Contest</th>
                            <th>Volume</th>
                            <th>Requirements</th>
                        </tr>
                        <c:forEach var="cathedra" items="${cathedra_list}">
                            <c:choose>
                                <c:when test="${cathedra.getLevel_of_training() == 'master' and cathedra.getType_of_training() == 'full time'}">
                                    <tr>
                                        <td>
                                                ${cathedra.level_of_training}<br>
                                                ${cathedra.department_name}<br>
                                                ${cathedra.name}
                                        </td>
                                        <td>
                                                ${cathedra.getStatement()}<br>
                                                ${cathedra.getRecommended()}<br>
                                                ${cathedra.getEnlisted()}<br>
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command" value="contest">
                                                <input type="hidden" name="cathedra_id" value="${cathedra.getId()}">
                                                <input type="submit" name="cathedra_info" value="contest">
                                            </form>
                                        </td>
                                        <td>
                                                ${cathedra.getLicensed_volume()}<br>
                                                ${cathedra.getLicensed_volume_budget()}<br>
                                                ${cathedra.getLicensed_volume_contract()}
                                        </td>
                                        <td>
                                            <c:forEach var="requirement" items="${cathedra.getRequirements()}">
                                                ${requirement}<br>
                                            </c:forEach>
                                            <p>Certificate score</p>
                                        </td>
                                    </tr>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </table>
                    <br>
                </div>

            </div> <!-- #content-region -->

        </div>

        <!-- Вторая вкладка -->
        <div id="content-distance">


            <ul class="pixel-tabs" id="distance-input">
                <li><a href="#content-distance-bachelor" id="bachelor2">Bachelor</a></li>
                <li><a href="#content-distance-master" id="master2">Master</a></li>
            </ul>

            <!-- Содержание -->
            <div class="content-region" id="content-distance-input">

                <!-- Первая вкладка -->
                <div id="content-distance-bachelor">
                    <table border="1">
                        <tr>
                            <th>Speciality</th>
                            <th>Contest</th>
                            <th>Volume</th>
                            <th>Requirements</th>
                        </tr>
                        <c:forEach var="cathedra" items="${cathedra_list}">
                            <c:choose>
                                <c:when test="${cathedra.getLevel_of_training() == 'bachelor' and cathedra.getType_of_training() == 'distance'}">
                                    <tr>
                                        <td>
                                                ${cathedra.level_of_training}<br>
                                                ${cathedra.department_name}<br>
                                                ${cathedra.name}
                                        </td>
                                        <td>
                                                ${cathedra.getStatement()}<br>
                                                ${cathedra.getRecommended()}<br>
                                                ${cathedra.getEnlisted()}<br>
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command" value="contest">
                                                <input type="hidden" name="cathedra_id" value="${cathedra.getId()}">
                                                <input type="submit" name="cathedra_info" value="contest">
                                            </form>
                                        </td>
                                        <td>
                                                ${cathedra.getLicensed_volume()}<br>
                                                ${cathedra.getLicensed_volume_budget()}<br>
                                                ${cathedra.getLicensed_volume_contract()}
                                        </td>
                                        <td>
                                            <c:forEach var="requirement" items="${cathedra.getRequirements()}">
                                                ${requirement}<br>
                                            </c:forEach>
                                            <p>Certificate score</p>
                                        </td>
                                    </tr>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </table>
                    <br>
                </div>

                <!-- Вторая вкладка -->
                <div id="content-distance-master">
                    <table border="1">
                        <tr>
                            <th>Speciality</th>
                            <th>Contest</th>
                            <th>Volume</th>
                            <th>Requirements</th>
                        </tr>
                        <c:forEach var="cathedra" items="${cathedra_list}">
                            <c:choose>
                                <c:when test="${cathedra.getLevel_of_training() == 'master' and cathedra.getType_of_training() == 'distance'}">
                                    <tr>
                                        <td>
                                                ${cathedra.level_of_training}<br>
                                                ${cathedra.department_name}<br>
                                                ${cathedra.name}
                                        </td>
                                        <td>
                                                ${cathedra.getStatement()}<br>
                                                ${cathedra.getRecommended()}<br>
                                                ${cathedra.getEnlisted()}<br>
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command" value="contest">
                                                <input type="hidden" name="cathedra_id" value="${cathedra.getId()}">
                                                <input type="submit" name="contest" value="contest">
                                            </form>
                                        </td>
                                        <td>
                                                ${cathedra.getLicensed_volume()}<br>
                                                ${cathedra.getLicensed_volume_budget()}<br>
                                                ${cathedra.getLicensed_volume_contract()}
                                        </td>
                                        <td>
                                            <c:forEach var="requirement" items="${cathedra.getRequirements()}">
                                                ${requirement}<br>
                                            </c:forEach>
                                            <p>Certificate score</p>
                                        </td>
                                    </tr>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </table>
                    <br>
                </div>

            </div> <!-- #content-region -->
        </div>

    </div> <!-- #content-region -->


</div>
</body>
</html>

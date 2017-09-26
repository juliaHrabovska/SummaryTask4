<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14.09.2017
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Cathedra</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>
<div class="container-fluid">
    <div class="row">

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header" style="margin-top: 5px;">Cathedras</h1>

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

            <div class="tabs">
                <ul class="nav nav-tabs nav-justified">
                    <li class="active"><a href="#content-fullTime" data-toggle="tab">Full time</a></li>
                    <li><a href="#content-distance" data-toggle="tab">Distance</a></li>
                </ul>
                <div class="tab-content" style="border-bottom-color: #0f0f0f; border-left-color: #0f0f0f; border-right-color: #0f0f0f;">
                    <div class="tab-pane active" id="content-fullTime">
                        <div class="tabs">
                            <ul class="nav nav-tabs">
                                <li class="active program-memory"><a href="#content-fullTime-bachelor"
                                                                     data-toggle="tab">Bachelor</a></li>
                                <li class="output-information"><a href="#content-fullTime-master" data-toggle="tab">Master</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="content-fullTime-bachelor">
                                    <table class="table table-bordered table-hover">
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
                                                            <p>Department: ${cathedra.department_name}</p>
                                                            <p>Cathedra: ${cathedra.name}</p>
                                                        </td>
                                                        <td>
                                                            <p>Number of applications: ${cathedra.getStatement()}</p>
                                                            <p>Number of recommended: ${cathedra.getRecommended()}</p>
                                                            <p>Number of enlested: ${cathedra.getEnlisted()}</p>
                                                            <form action="controller" method="post">
                                                                <input type="hidden" name="command" value="contest">
                                                                <input type="hidden" name="cathedra_id"
                                                                       value="${cathedra.getId()}">
                                                                <button class="btn btn-lg btn-primary btn-block"
                                                                        type="submit">Contest
                                                                </button>
                                                            </form>

                                                        </td>
                                                        <td>
                                                            <p>Licensed volume: ${cathedra.getLicensed_volume()}</p>
                                                            <p>Budget: ${cathedra.getLicensed_volume_budget()}</p>
                                                            <p>Contract: ${cathedra.getLicensed_volume_contract()}</p>
                                                        </td>
                                                        <td>

                                                            <c:forEach var="requirement"
                                                                       items="${cathedra.getRequirements()}">
                                                                ${requirement}<br>
                                                            </c:forEach>
                                                            <p>Certificate score</p>
                                                        </td>
                                                    </tr>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </table>
                                </div>

                                <div class="tab-pane" id="content-fullTime-master">
                                    <table class="table table-bordered table-hover">
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
                                                            <p>Department: ${cathedra.department_name}</p>
                                                            <p>Cathedra: ${cathedra.name}</p>
                                                        </td>
                                                        <td>
                                                            <p>Number of applications: ${cathedra.getStatement()}</p>
                                                            <p>Number of recommended: ${cathedra.getRecommended()}</p>
                                                            <p>Number of enlested: ${cathedra.getEnlisted()}</p>
                                                            <form action="controller" method="post">
                                                                <input type="hidden" name="command" value="contest">
                                                                <input type="hidden" name="cathedra_id"
                                                                       value="${cathedra.getId()}">
                                                                <button class="btn btn-lg btn-primary btn-block"
                                                                        type="submit">Contest
                                                                </button>
                                                            </form>

                                                        </td>
                                                        <td>
                                                            <p>Licensed volume: ${cathedra.getLicensed_volume()}</p>
                                                            <p>Budget: ${cathedra.getLicensed_volume_budget()}</p>
                                                            <p>Contract: ${cathedra.getLicensed_volume_contract()}</p>
                                                        </td>
                                                        <td>
                                                            <c:forEach var="requirement"
                                                                       items="${cathedra.getRequirements()}">
                                                                ${requirement}<br>
                                                            </c:forEach>
                                                            <p>Certificate score</p>
                                                        </td>
                                                    </tr>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="content-distance">
                        <div class="tabs">
                            <ul class="nav nav-tabs">
                                <li class="active program-memory"><a href="#content-distance-bachelor"
                                                                     data-toggle="tab">Bachelor</a></li>
                                <li class="output-information"><a href="#content-distance-master" data-toggle="tab">Master</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="content-distance-bachelor">
                                    <table class="table table-bordered table-hover">
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
                                                            <p>Department: ${cathedra.department_name}</p>
                                                            <p>Cathedra: ${cathedra.name}</p>
                                                        </td>
                                                        <td>
                                                            <p>Number of applications: ${cathedra.getStatement()}</p>
                                                            <p>Number of recommended: ${cathedra.getRecommended()}</p>
                                                            <p>Number of enlested: ${cathedra.getEnlisted()}</p>
                                                            <form action="controller" method="post">
                                                                <input type="hidden" name="command" value="contest">
                                                                <input type="hidden" name="cathedra_id"
                                                                       value="${cathedra.getId()}">
                                                                <button class="btn btn-lg btn-primary btn-block"
                                                                        type="submit">Contest
                                                                </button>
                                                            </form>

                                                        </td>
                                                        <td>
                                                            <p>Licensed volume: ${cathedra.getLicensed_volume()}</p>
                                                            <p>Budget: ${cathedra.getLicensed_volume_budget()}</p>
                                                            <p>Contract: ${cathedra.getLicensed_volume_contract()}</p>
                                                        </td>
                                                        <td>
                                                            <c:forEach var="requirement"
                                                                       items="${cathedra.getRequirements()}">
                                                                ${requirement}<br>
                                                            </c:forEach>
                                                            <p>Certificate score</p>
                                                        </td>
                                                    </tr>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </table>
                                </div>
                                <div class="tab-pane" id="content-distance-master">
                                    <table class="table table-bordered table-hover">
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
                                                            <p>Department: ${cathedra.department_name}</p>
                                                            <p>Cathedra: ${cathedra.name}</p>
                                                        </td>
                                                        <td>
                                                            <p>Number of applications: ${cathedra.getStatement()}</p>
                                                            <p>Number of recommended: ${cathedra.getRecommended()}</p>
                                                            <p>Number of enlested: ${cathedra.getEnlisted()}</p>
                                                            <form action="controller" method="post">
                                                                <input type="hidden" name="command" value="contest">
                                                                <input type="hidden" name="cathedra_id"
                                                                       value="${cathedra.getId()}">
                                                                <button class="btn btn-lg btn-primary btn-block"
                                                                        type="submit">Contest
                                                                </button>
                                                            </form>

                                                        </td>
                                                        <td>
                                                            <p>Licensed volume: ${cathedra.getLicensed_volume()}</p>
                                                            <p>Budget: ${cathedra.getLicensed_volume_budget()}</p>
                                                            <p>Contract: ${cathedra.getLicensed_volume_contract()}</p>
                                                        </td>
                                                        <td>
                                                            <c:forEach var="requirement"
                                                                       items="${cathedra.getRequirements()}">
                                                                ${requirement}<br>
                                                            </c:forEach>
                                                            <p>Certificate score</p>
                                                        </td>
                                                    </tr>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.js"></script>
</body>
</html>

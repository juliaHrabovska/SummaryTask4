<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.09.2017
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Cathedra info</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>
<div class="container-fluid">
    <div class="row">

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header" style="margin-top: 5px;"><fmt:message key="cathedras"/></h1>
            <div class="row placeholders">

                <div class="tabs">
                    <ul class="nav nav-tabs nav-justified">
                        <li class="active"><a href="#content-fullTime" data-toggle="tab"><fmt:message key="fullTime"/></a></li>
                        <li><a href="#content-distance" data-toggle="tab"><fmt:message key="distance"/></a></li>
                    </ul>
                    <div class="tab-content"
                         style="border-bottom-color: #0f0f0f; border-left-color: #0f0f0f; border-right-color: #0f0f0f;">
                        <div class="tab-pane active" id="content-fullTime">
                            <div class="tabs">
                                <ul class="nav nav-tabs">
                                    <li class="active program-memory"><a href="#content-fullTime-bachelor"
                                                                         data-toggle="tab"><fmt:message key="bachelor"/></a></li>
                                    <li class="output-information"><a href="#content-fullTime-master" data-toggle="tab"><fmt:message key="master"/></a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="content-fullTime-bachelor">
                                        <table class="table table-bordered table-hover">
                                            <tr>
                                                <th><fmt:message key="speciality"/></th>
                                                <th><fmt:message key="contest"/></th>
                                                <th><fmt:message key="volume"/></th>
                                                <th><fmt:message key="requirements"/></th>
                                                <th>Change info</th>
                                                <th><fmt:message key="delete"/></th>
                                            </tr>
                                            <c:forEach var="cathedra" items="${cathedra_list}">
                                                <c:choose>
                                                    <c:when test="${cathedra.getLevel_of_training() == 'bachelor' and cathedra.getType_of_training() == 'full time'}">
                                                        <form action="controller" method="post">
                                                            <input type="hidden" name="command" value="changeCathedra">
                                                            <input type="hidden" name="cathedra_id"
                                                                   value="${cathedra.getId()}">
                                                            <tr>
                                                                <td>
                                                                        ${cathedra.level_of_training}<br>
                                                                    <p><fmt:message key="department"/>: ${cathedra.department_name}</p>
                                                                    <div class="form-group">
                                                                        <input type="text" class="form-control"
                                                                               name="cathedraName"
                                                                               value="${cathedra.name}"/>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <p><fmt:message key="numbApp"/> ${cathedra.getStatement()}</p>
                                                                    <p><fmt:message key="numbRecom"/>: ${cathedra.getRecommended()}</p>
                                                                    <p><fmt:message key="numbEnlest"/>: ${cathedra.getEnlisted()}</p>
                                                                </td>
                                                                <td>
                                                                    <p><fmt:message key="licensVolume"/>: ${cathedra.getLicensed_volume()}</p>
                                                                    <label><fmt:message key="budget"/>: </label>
                                                                    <div class="form-group">
                                                                        <input type="number" class="form-control"
                                                                               name="budget"
                                                                               value="${cathedra.getLicensed_volume_budget()}"/>
                                                                    </div>
                                                                    <label><fmt:message key="contract"/>: </label>
                                                                    <div class="form-group">
                                                                        <input type="number" class="form-control"
                                                                               name="contract"
                                                                               value="${cathedra.getLicensed_volume_contract()}"/>
                                                                    </div>
                                                                </td>
                                                                <td>

                                                                    <c:forEach var="requirement"
                                                                               items="${cathedra.getRequirements()}">
                                                                        ${requirement}<br>
                                                                    </c:forEach>
                                                                    <p>Certificate score</p>
                                                                </td>
                                                                <td>
                                                                    <button type="submit"
                                                                            class="btn btn-lg btn-default btn-reg btn-block">
                                                                        Change
                                                                    </button>
                                                                </td>
                                                                <td>
                                                                    <p>
                                                                        <a href="controller?command=deleteCathedra&cathedraIdDel=${cathedra.getId()}"><fmt:message key="delete"/></a>
                                                                </td>
                                                            </tr>
                                                        </form>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </table>
                                    </div>

                                    <div class="tab-pane" id="content-fullTime-master">
                                        <table class="table table-bordered table-hover">
                                            <tr>
                                                <th><fmt:message key="speciality"/></th>
                                                <th><fmt:message key="contest"/></th>
                                                <th><fmt:message key="volume"/></th>
                                                <th><fmt:message key="requirements"/></th>
                                                <th>Change info</th>
                                                <th><fmt:message key="delete"/></th>
                                            </tr>
                                            <c:forEach var="cathedra" items="${cathedra_list}">
                                                <c:choose>
                                                    <c:when test="${cathedra.getLevel_of_training() == 'master' and cathedra.getType_of_training() == 'full time'}">
                                                        <form action="controller" method="post">
                                                            <input type="hidden" name="command" value="changeCathedra">
                                                            <input type="hidden" name="cathedra_id"
                                                                   value="${cathedra.getId()}">
                                                            <tr>
                                                                <td>
                                                                        ${cathedra.level_of_training}<br>
                                                                    <p><fmt:message key="department"/>: ${cathedra.department_name}</p>
                                                                    <div class="form-group">
                                                                        <input type="text" class="form-control"
                                                                               name="cathedra_name"
                                                                               value="${cathedra.name}"/>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <p><fmt:message key="numbApp"/> ${cathedra.getStatement()}</p>
                                                                    <p><fmt:message key="numbRecom"/>: ${cathedra.getRecommended()}</p>
                                                                    <p><fmt:message key="numbEnlest"/>: ${cathedra.getEnlisted()}</p>
                                                                </td>
                                                                <td>
                                                                    <p><fmt:message key="licensVolume"/>: ${cathedra.getLicensed_volume()}</p>
                                                                    <label><fmt:message key="budget"/>: </label>
                                                                    <div class="form-group">
                                                                        <input type="number" class="form-control"
                                                                               name="budget"
                                                                               value="${cathedra.getLicensed_volume_budget()}"/>
                                                                    </div>
                                                                    <label><fmt:message key="contract"/>: </label>
                                                                    <div class="form-group">
                                                                        <input type="number" class="form-control"
                                                                               name="contract"
                                                                               value="${cathedra.getLicensed_volume_contract()}"/>
                                                                    </div>
                                                                </td>
                                                                <td>

                                                                    <c:forEach var="requirement"
                                                                               items="${cathedra.getRequirements()}">
                                                                        ${requirement}<br>
                                                                    </c:forEach>
                                                                    <p>Certificate score</p>
                                                                </td>
                                                                <td>
                                                                    <button type="submit"
                                                                            class="btn btn-lg btn-default btn-reg btn-block">
                                                                        Change
                                                                    </button>
                                                                </td>
                                                                <td>
                                                                    <p>
                                                                        <a href="controller?command=deleteCathedra&cathedraIdDel=${cathedra.getId()}"><fmt:message key="delete"/></a>
                                                                </td>
                                                            </tr>
                                                        </form>
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
                                                <th><fmt:message key="speciality"/></th>
                                                <th><fmt:message key="contest"/></th>
                                                <th><fmt:message key="volume"/></th>
                                                <th><fmt:message key="requirements"/></th>
                                                <th>Change info</th>
                                                <th><fmt:message key="delete"/></th>
                                            </tr>
                                            <c:forEach var="cathedra" items="${cathedra_list}">
                                                <c:choose>
                                                    <c:when test="${cathedra.getLevel_of_training() == 'bachelor' and cathedra.getType_of_training() == 'distance'}">
                                                        <form action="controller" method="post">
                                                            <input type="hidden" name="command" value="changeCathedra">
                                                            <input type="hidden" name="cathedra_id"
                                                                   value="${cathedra.getId()}">
                                                            <tr>
                                                                <td>
                                                                        ${cathedra.level_of_training}<br>
                                                                    <p><fmt:message key="department"/>: ${cathedra.department_name}</p>
                                                                    <div class="form-group">
                                                                        <input type="text" class="form-control"
                                                                               name="cathedra_name"
                                                                               value="${cathedra.name}"/>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <p><fmt:message key="numbApp"/> ${cathedra.getStatement()}</p>
                                                                    <p><fmt:message key="numbRecom"/>: ${cathedra.getRecommended()}</p>
                                                                    <p><fmt:message key="numbEnlest"/>: ${cathedra.getEnlisted()}</p>
                                                                </td>
                                                                <td>
                                                                    <p><fmt:message key="licensVolume"/>: ${cathedra.getLicensed_volume()}</p>
                                                                    <label><fmt:message key="budget"/>: </label>
                                                                    <div class="form-group">
                                                                        <input type="number" class="form-control"
                                                                               name="budget"
                                                                               value="${cathedra.getLicensed_volume_budget()}"/>
                                                                    </div>
                                                                    <label><fmt:message key="contract"/>: </label>
                                                                    <div class="form-group">
                                                                        <input type="number" class="form-control"
                                                                               name="contract"
                                                                               value="${cathedra.getLicensed_volume_contract()}"/>
                                                                    </div>
                                                                </td>
                                                                <td>

                                                                    <c:forEach var="requirement"
                                                                               items="${cathedra.getRequirements()}">
                                                                        ${requirement}<br>
                                                                    </c:forEach>
                                                                    <p>Certificate score</p>
                                                                </td>
                                                                <td>
                                                                    <button type="submit"
                                                                            class="btn btn-lg btn-default btn-reg btn-block">
                                                                        Change
                                                                    </button>
                                                                </td>
                                                                <td>
                                                                    <p>
                                                                        <a href="controller?command=deleteCathedra&cathedraIdDel=${cathedra.getId()}"><fmt:message key="delete"/></a>
                                                                </td>
                                                            </tr>
                                                        </form>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </table>
                                    </div>
                                    <div class="tab-pane" id="content-distance-master">
                                        <table class="table table-bordered table-hover">
                                            <tr>
                                                <th><fmt:message key="speciality"/></th>
                                                <th><fmt:message key="contest"/></th>
                                                <th><fmt:message key="volume"/></th>
                                                <th><fmt:message key="requirements"/></th>
                                                <th>Change info</th>
                                                <th><fmt:message key="delete"/></th>
                                            </tr>
                                            <c:forEach var="cathedra" items="${cathedra_list}">
                                                <c:choose>
                                                    <c:when test="${cathedra.getLevel_of_training() == 'master' and cathedra.getType_of_training() == 'distance'}">
                                                        <form action="controller" method="post">
                                                            <input type="hidden" name="command" value="changeCathedra">
                                                            <input type="hidden" name="cathedra_id"
                                                                   value="${cathedra.getId()}">
                                                            <tr>
                                                                <td>
                                                                        ${cathedra.level_of_training}<br>
                                                                    <p><fmt:message key="department"/>: ${cathedra.department_name}</p>
                                                                    <div class="form-group">
                                                                        <input type="text" class="form-control"
                                                                               name="cathedra_name"
                                                                               value="${cathedra.name}"/>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <p><fmt:message key="numbApp"/> ${cathedra.getStatement()}</p>
                                                                    <p><fmt:message key="numbRecom"/>: ${cathedra.getRecommended()}</p>
                                                                    <p><fmt:message key="numbEnlest"/>: ${cathedra.getEnlisted()}</p>
                                                                </td>
                                                                <td>
                                                                    <p><fmt:message key="licensVolume"/>: ${cathedra.getLicensed_volume()}</p>
                                                                    <label><fmt:message key="budget"/>: </label>
                                                                    <div class="form-group">
                                                                        <input type="number" class="form-control"
                                                                               name="budget"
                                                                               value="${cathedra.getLicensed_volume_budget()}"/>
                                                                    </div>
                                                                    <label><fmt:message key="contract"/>: </label>
                                                                    <div class="form-group">
                                                                        <input type="number" class="form-control"
                                                                               name="contract"
                                                                               value="${cathedra.getLicensed_volume_contract()}"/>
                                                                    </div>
                                                                </td>
                                                                <td>

                                                                    <c:forEach var="requirement"
                                                                               items="${cathedra.getRequirements()}">
                                                                        ${requirement}<br>
                                                                    </c:forEach>
                                                                    <p>Certificate score</p>
                                                                </td>
                                                                <td>
                                                                    <button type="submit"
                                                                            class="btn btn-lg btn-default btn-reg btn-block">
                                                                        Change
                                                                    </button>
                                                                </td>
                                                                <td>
                                                                    <p>
                                                                        <a href="controller?command=deleteCathedra&cathedraIdDel=${cathedra.getId()}"><fmt:message key="delete"/></a>
                                                                </td>
                                                            </tr>
                                                        </form>
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
            <div class="row col-md-4 col-xs-6 placeholder">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="showExamList">
                    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="addNew"/></button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.js"></script>
<%@ include file="/WEB-INF/jspf/leftMenuAdmin.jspf" %>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 19.09.2017
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Settings</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>
<div class="container-fluid">
    <div class="row">

        <div class="col-sm-10 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <form action="controller" method="post">
                <input type="hidden" name="command" value="editProfile"/>
                <h1 class="page-header" style="margin-top: 5px;"><fmt:message key="accountData"/></h1>
                <c:if test="${not empty error}">
                    <div class="warningbox">
                        <div class="innerbox">
                            <p><c:out value="${error}"/>
                        </div>
                    </div>
                </c:if>
                <div class="row placeholders">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <input name="email" type="email" class="form-control" id="exampleInputEmail1"
                               placeholder="Email" required
                               value="${account.getEmail()}">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputPassword1"><fmt:message key="pass"/></label>
                        <input name="password" type="password" class="form-control" id="exampleInputPassword1"
                               placeholder="Password"
                                required value="${account.getPassword()}">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputPassword2"><fmt:message key="passConf"/></label>
                        <input name="password2" type="password" class="form-control" id="exampleInputPassword2"
                               placeholder="Password"
                                required value="${account.getPassword()}">
                    </div>
                </div>

                <h1 class="page-header"><fmt:message key="data"/></h1>
                <div class="row placeholders">
                    <div class="form-group">
                        <label for="exampleInputName"><fmt:message key="firstName"/></label>
                        <input name="firstName" type="text" class="form-control" id="exampleInputName"
                               placeholder="Name"
                               required
                               maxlength="30" value="${enrollee.getFirst_name()}">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputLastName"><fmt:message key="lastName"/></label>
                        <input name="last_name" type="text" class="form-control" id="exampleInputLastName"
                               placeholder="Lastname" required
                               maxlength="30" value="${enrollee.getFirst_name()}">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputPatronymic"><fmt:message key="patronymic"/></label>
                        <input name="patronymic" type="text" class="form-control" id="exampleInputPatronymic"
                               placeholder="Patronymic" required
                               maxlength="30" value="${enrollee.getPatronymic()}">
                    </div>
                </div>

                <div class="col-md-3 col-xs-6 placeholder">
                        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="save"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
        <%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
        <script src="/bootstrap/js/bootstrap.js"></script>
</body>
</html>

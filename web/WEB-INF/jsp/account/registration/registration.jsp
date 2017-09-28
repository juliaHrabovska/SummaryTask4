<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07.09.2017
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Registration</title>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container" style="margin-top: 40px; width: 800px;">
    <div class="row" style="margin-left: 0;">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Registration</h3>
            </div>
            <div class="panel-body">
                <div class="col-sm-11 main">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="registration"/>

                        <h3 class="page-header" style="margin-top: 5px;">Account data</h3>
                        <div class=" placeholders" style="margin-bottom: 0;">
                            <c:if test="${not empty error}">
                                <div class="warningbox">
                                    <div class="innerbox">
                                        <p><c:out value="${error}"/>
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input name="email" type="email" class="form-control" id="exampleInputEmail1"
                                       placeholder="Email" required>
                            </div>

                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input name="password" type="password" class="form-control" id="exampleInputPassword1"
                                       placeholder="Password"
                                       required>
                            </div>

                            <div class="form-group">
                                <label for="exampleInputPassword2">Confirm password</label>
                                <input name="password2" type="password" class="form-control" id="exampleInputPassword2"
                                       placeholder="Password"
                                       required>
                            </div>
                        </div>

                        <h3 class="page-header">Entrance data</h3>
                        <div class="placeholders" style="margin-bottom: 0;">
                            <div class="form-group">
                                <label for="exampleInputName">First name</label>
                                <input name="first_name" type="text" class="form-control" id="exampleInputName"
                                       placeholder="Name"
                                       required
                                       maxlength="30">
                            </div>

                            <div class="form-group">
                                <label for="exampleInputLastName">Last name</label>
                                <input name="last_name" type="text" class="form-control" id="exampleInputLastName"
                                       placeholder="Lastname" required
                                       maxlength="30">
                            </div>

                            <div class="form-group">
                                <label for="exampleInputPatronymic">Patronymic</label>
                                <input name="patronymic" type="text" class="form-control" id="exampleInputPatronymic"
                                       placeholder="Patronymic" required
                                       maxlength="30">
                            </div>
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
                            <label for="exampleInputName">Certificate score</label><br>
                            <input name="certificate_score" id="certificate_score" type="number" class="form-control"
                                   placeholder="certificate_score" required
                                   min="100" max="200"/>
                        </div>

                        <div class="form-group">
                            <div class="captcha_block">
                                <div class="g-recaptcha"
                                     data-sitekey="6Ld7jhcTAAAAAC16uK5JT2AUh2Icks-skf19j6Gj"></div>
                            </div>
                        </div>

                        <div class="col-md-3 col-xs-6 placeholder">
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Registration</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>

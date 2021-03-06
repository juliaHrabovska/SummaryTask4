<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 13.09.2017
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Upload file</title>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container" style="margin-top: 40px; width: 800px;">
    <div class="row" style="margin-left: 0;">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Upload certificate scan</h3>
            </div>
            <c:if test="${not empty error}">
                <div class="warningbox">
                    <div class="innerbox">
                        <p><c:out value="${error}"/>
                    </div>
                </div>
            </c:if>
            <div class="panel-body">
                <div class="col-sm-11 main">
                    <form action="uploadFile" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <input type="file" name=file required>
                        </div>

                        <div class="col-md-3 col-xs-6 placeholder">
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Upload file</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

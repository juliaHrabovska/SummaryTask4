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
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/leftMenuClient.jspf" %>
<div class="content">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="editProfile"/>
        <table cellpadding="0" cellspacing="0" class="form-table">
            <tr>
                <td>
                    <strong><fmt:message key="email"/></strong></td>
                <td><input title="example@mail.com" name="email" type="text" id="Email"
                           value="${account.getEmail()}"/>
                </td>
            </tr>
            <tr>
                <td><strong><fmt:message key="password"/></strong></td>
                <td>
                    <input name="password" type="password" id="password"
                           title="Letters of the Latin alphabet and numbers"
                           style="width:90%" pattern="^[0-9A-za-z]+$" maxlength="30"
                           required
                           value="${account.getPassword()}"/>
                </td>
            </tr>
            <tr>
                <td><strong><fmt:message key="passwordConf"/></strong></td>
                <td>
                    <input name="password2" type="password" id="password2"
                           title="Letters of the Latin alphabet and numbers"
                           style="width:90%" pattern="^[0-9A-za-z]+$" maxlength="30"
                           required
                           value="${account.getPassword()}"/>
                </td>
            </tr>
        </table>
        <br/>

        <p class="blog-title"><a style="text-decoration:none"><fmt:message key="personalInformation"/></a></p>
        <table cellpadding="0" cellspacing="0" class="form-table">
            <tr>
                <td><strong><fmt:message key="firstName"/></strong></td>
                <td>
                    <input title="Only letters of Cyrillic and Latin alphabets"
                           name="first_name" type="text" id="firstName" style="width:90%"
                           maxlength="30" required
                           value="${enrollee.getFirst_name()}"/>
                </td>
            </tr>
            <tr>
                <td style="width:40%"><strong><fmt:message key="lastName"/></strong></td>
                <td>
                    <input title="Only letters of Cyrillic and Latin alphabets"
                           name="last_name" type="text" id="lastName" style="width:90%"
                           maxlength="30" required
                           value="${enrollee.getLast_name()}"/>
                </td>
            </tr>
            <tr>
                <td style="width:40%"><strong><fmt:message key="patronymic"/></strong></td>
                <td>
                    <input title="Only letters of Cyrillic and Latin alphabets"
                           name="patronymic" type="text" id="patronymic" style="width:90%"
                           maxlength="30" required
                           value="${enrollee.getPatronymic()}"/>
                </td>
            </tr>
        </table>
        <input class="buttonLogIn" type="submit" value="<fmt:message key="save"/>"/>

    </form>
</div>
</body>
</html>

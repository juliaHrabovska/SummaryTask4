<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.09.2017
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Authentication</title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="login"/>

    <strong><fmt:message key="email"/></strong>
    <input name="email" type="text" id="login"/>

    <input type="submit" name="login" value="Login"/>

    <strong><fmt:message key="password"/></strong>
    <input name="password" type="password" id="password"/>
</form>
<a href="controller?command=getRegistrationPage">Registration</a>
</body>
</html>

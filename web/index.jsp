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
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/bootstrap/css/style.css" rel="stylesheet">
    <link href="/css/signin.css" rel="stylesheet">
    <link href="/css/buttons.css" rel="stylesheet">

</head>
<body>


<div class="container">

    <form class="form-signin" role="form" action="controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <h2 class="form-signin-heading">Please sign in</h2>
        <input name="email" type="email" class="form-control" placeholder="Email address" required autofocus>
        <input name="password" type="password" class="form-control" placeholder="Password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <a style="text-decoration:none" href="controller?command=getRegistrationPage"><button type="button" class="btn btn-lg btn-default btn-reg btn-block">Registration</button></a>
    </form>

</div>
</body>
</html>

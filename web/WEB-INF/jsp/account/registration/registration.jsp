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
</head>
<body>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="registration"/>

        <input name="email" id="login" value="enrollee2"/>
        <input type="password" name="password" id="password" value="enrollee2"/>
        <input type="password" name="password2" id="password2" value="enrollee2"/>

        <input name="first_name" id="first_name" value="Petr"/>
        <input name="last_name" id="last_name" value="Petrov"/>
        <input name="patronymic" id="patronymic" value="Petrovich"/>
        <input name="certificate_score" id="certificate_score" value="190"/>

        <input type="radio" name="level_of_training" value="1" checked>Bachelor<Br>
        <input type="radio" name="level_of_training" value="2">Master<Br>
        <div class="captcha_block">
            <div class="g-recaptcha"
                 data-sitekey="6Ld7jhcTAAAAAC16uK5JT2AUh2Icks-skf19j6Gj"></div>
        </div>

        <input type="submit" name="registration" value="Registration"/>

    </form>
</body>
</html>

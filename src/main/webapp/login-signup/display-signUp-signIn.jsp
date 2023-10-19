<%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 10/18/23
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Facebook-Login/SignUp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="style-signIn-signUp.css">
    <link rel="shortcut icon" type="image/png" href="https://static.xx.fbcdn.net/rsrc.php/yb/r/hLRJ1GG_y0J.ico">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="changeToSignUp.js"></script>
</head>
<body>

<div class="login">
    <div class="form-Sign-Up" id="formSignUp">
    </div>
    <main class="form-login">
        <div class="div-img-logo-header">
            <img src="https://static.xx.fbcdn.net/rsrc.php/yI/r/4aAhOWlwaXf.svg" class="img-logo-header">
        </div>
        <div class="div-form-SignIn-SignUp">
            <form action="/session?actionPost=login" method="post" class="form-SignIn-SignUp">
                <div class="divInForm">
                    <div class="input-login login">
                        <div class="loginDiv">
                            <input type="text" placeholder="Email address or phone Number"
                                   name="username" class="input-a-signIn-signUp inputLogin border-all border-login">
                        </div>
                        <div class="loginDiv">
                            <input type="password" placeholder="Password"
                                   name="password" class="input-a-signIn-signUp inputLogin border-all border-login">
                        </div>
                    </div>
                    <div class="div-input-submit login loginDiv">
                        <input type="submit" value="Log in"
                               class="input-a-signIn-signUp inputLogin submit-login loginDiv border-all">
                    </div>
                    <div class="div-a-forgot login">
                        <a href="" class="input-a-signIn-signUp login">Forgotten password?</a>
                    </div>
                    <div class="underline login">

                    </div>
                    <div class="div-input-button login button-signUp">
<%--                        <button type="button" onclick="showFormSignUp()"--%>
<%--                                class="input-a-signIn-signUp signUp input-SignUp border-all" id="showFormSignUp">Create--%>
<%--                            new account--%>
<%--                        </button>--%>
                        <input type="button" onclick="showFormSignUp()" class="input-a-signIn-signUp input-SignUp border-all" value="Create new account">
                    </div>
                </div>
            </form>
        </div>
    </main>

    <footer>
        <div class="footer">
            <p class="p-footer">
                <svg class="logo-footer" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                    <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                    <style>svg {
                        fill: #005eff
                    }</style>
                    <path d="M504 256C504 119 393 8 256 8S8 119 8 256c0 123.78 90.69 226.38 209.25 245V327.69h-63V256h63v-54.64c0-62.15 37-96.48 93.67-96.48 27.14 0 55.52 4.84 55.52 4.84v61h-31.28c-30.8 0-40.41 19.12-40.41 38.73V256h68.78l-11 71.69h-57.78V501C413.31 482.38 504 379.78 504 256z"/>
                </svg>
                <span class="text-footer text-color">The product is developed since 18/10/2023</span>
            </p>
            <p class="p-footer text-color">
                Developed by group 2TN2T
            </p>
            <p class="p-footer text-color">
                -Project Social Network-
            </p>
        </div>
    </footer>
</div>

</body>
</html>

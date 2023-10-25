<%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 10/18/23
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Facebook-Login/SignUp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../login-signup/style-signIn-signUp.css">
    <link rel="shortcut icon" type="image/png" href="https://static.xx.fbcdn.net/rsrc.php/yb/r/hLRJ1GG_y0J.ico">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="changeToSignUp.js"></script>
</head>
<body>

<div class="login">
    <div class="form-Sign-Up" id="formSignUp">
        <div class="div-form-signUp">
            <form class="needs-validation edit form-signUp" action="/session?actionPost=signUp" method="post" id="Signup" novalidate>
                <div class="title-form">
                    <span>Sign up</span>
                    <input type="button" class="icon" onclick="hideFormSignUp()" value="x">
                </div>
                <div class="underline-signup-top"></div>
                <div class="form-row">
                    <div class="col-md-6 mb-2">
                        <div class="title-notify">
                            <label for="validationCustomUsername" class="text-title div-notify">Username(<span class="icon-im">*</span>)</label>
                            <div class="text-notify">
                                <p id="notifyUser"></p>
                            </div>
                        </div>
                        <div class="input-group">
                            <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroupPrepend"><svg xmlns="http://www.w3.org/2000/svg"
                                                                               height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path
                            d="M304 128a80 80 0 1 0 -160 0 80 80 0 1 0 160 0zM96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM49.3 464H398.7c-8.9-63.3-63.3-112-129-112H178.3c-65.7 0-120.1 48.7-129 112zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3z"/></svg></span>
                            </div>
                            <input type="text" class="form-control" id="validationCustomUsername" placeholder="Username"
                                   aria-describedby="inputGroupPrepend" name="username" value="user2"
                                   required>
                        </div>
                    </div>
                    <div class="col-md-6 mb-2">
                        <label for="validationCustom01"  class="text-title">Email(*)</label>
                        <input type="email" class="form-control" id="validationCustom01"
                               placeholder="Ex:tronglai@gmail.com" name="email"
                               required>
                    </div>
                    <div class="col-md-12 mb-2">
                        <label for="validationCustom02"  class="text-title">Password(*)</label>
                        <input type="password" class="form-control" id="validationCustom02" placeholder="password"
                               minlength="6" maxlength="32" name="password"
                               required>
                    </div>
                    <div class="col-md-12 mb-2">
                        <div class="title-notify">
                            <label for="validationCustom03"  class="text-title">Confirm password(*)</label>
                            <div class="text-notify">
                                <p id="notify"></p>
                            </div>
                        </div>
                        <input type="password" class="form-control" id="validationCustom03"
                               placeholder="confirm password" minlength="6" maxlength="32"  required>

                    </div>
                    <div class="col-md-12 mb-2">
                        <label for="validationCustom04"  class="text-title">Phone number</label>
                        <input type="text" pattern="\d*" class="form-control" id="validationCustom04"
                               placeholder="Phone number" maxlength="10" minlength="10" inputmode="numeric"
                               name="phoneNumber"
                               required>
                    </div>
                </div>

                <div class="title-birthday birth text-title" >
                    <span>Date of birth (day/month/year)</span>
                </div>
                <div class="birthday birth">
                    <label class="div-label-select">
                        <select class="label-select">
                            <c:forEach var="i" begin="1" end="31" step="1">
                                <option class="option" value="${i}">${i}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <label class="div-label-select">
                        <select class="label-select">
                            <c:forEach var="i" begin="1" end="12" step="1">
                                <option class="option" value="${i}">${i}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <label class="div-label-select">
                        <select class="label-select">
                            <c:forEach var="i" begin="1905" end="2023">
                                <option class="option" value="${i}">${i}</option>
                            </c:forEach>
                        </select>
                    </label>
                </div>
<%--                <div class="underline-signup-bottom"></div>--%>
                <div class="div-submit">
                    <button class=" button" type="submit">Submit form</button>
                </div>
            </form>
        </div>
    </div>
    <script>
        (function () {
            'use strict';
            window.addEventListener('load', function () {
                const mb2 = document.getElementsByClassName("mb-2");
                let forms = document.getElementsByClassName('needs-validation');
                let validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();


        const password = document.getElementById("validationCustom02");
        const passwordConfirm = document.getElementById("validationCustom03");
        const notify = document.getElementById("notify");
        passwordConfirm.addEventListener("input", checkPassword);

        const form = document.querySelector('.form-signUp');

        form.addEventListener('submit', function (event) {
            if (password.value !== passwordConfirm.value) {
                event.preventDefault();
                event.stopPropagation();
                passwordConfirm.classList.add('is-invalid');
            }
            form.classList.add('was-validated');
        });

        function checkPassword() {
            const pass = password.value;
            const passConfirm = passwordConfirm.value;
            if (pass === passConfirm) {
                notify.textContent = "confirm password";
                notify.style.color = "#18ce11";
            } else {
                notify.textContent = "wrong password";
                notify.style.color = "#ee3434";
            }
        }


        const notifyUsers = document.getElementById("notifyUser")
        $(document).ready(function () {
            $('#Signup').submit(function (event) {
                event.preventDefault();
                $.ajax({
                    url: '/session?actionPost=signUp',
                    method: 'POST',
                    dataType: 'json',
                    data: {
                        username: document.getElementById("validationCustomUsername").value,
                        password: document.getElementById("validationCustom02").value
                    },
                    success: (response) => {
                        console.log(response);
                        notifyUsers.textContent = response.notifyUser;
                        if(response.notifyUser === "success"){
                            // window.location.href ='display-signUp-signIn.jsp';
                            window.location.reload();
                        }
                    }
                })
            })
        })
    </script>
    <main class="form-login">
        <div class="div-img-logo-header">
            <img src="https://static.xx.fbcdn.net/rsrc.php/yI/r/4aAhOWlwaXf.svg" class="img-logo-header">
        </div>
        <div class="div-form-SignIn-SignUp">
            <form action="/session?actionPost=login" method="post" class="form-SignIn-SignUp">
                <div class="divInForm">
                    <div class="input-login login">
                        <div class="loginDiv">
                            <input type="text" placeholder="Email address or username"
                                 name="username"  class="input-a-signIn-signUp inputLogin border-login" required>
                        </div>
                        <div class="loginDiv">
                            <input type="password" placeholder="Password"
                                   name="password" class="input-a-signIn-signUp inputLogin border-login"
                                   required>
                        </div>
                    </div>
                    <div class="div-input-submit login loginDiv">
                        <input type="submit" value="Log in"
                               class="input-a-signIn-signUp inputLogin submit-login loginDiv border-submit">
                    </div>
                    <div class="div-a-forgot login">
                        <a href="" class="input-a-signIn-signUp login">Forgotten password?</a>
                    </div>
                    <div class="underline login">

                    </div>
                    <div class="div-input-button login button-signUp">
                        <input type="button" onclick="showFormSignUp()"
                               class="input-a-signIn-signUp input-SignUp border-submit" value="Create new account">
                    </div>
                </div>
            </form>
        </div>
    </main>
    <script>
        function showFormSignUp(){
            let signUp = document.getElementById("formSignUp");
            console.log(signUp)
            signUp.style.display = "block";
        }

        function hideFormSignUp(){

            let signUp = document.getElementById("formSignUp");
            signUp.style.display = "none";
        }
    </script>
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

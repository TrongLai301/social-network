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
    <link rel="stylesheet" href="style-signIn-signUp.css">
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
<%--            <form class="form-signUp">--%>
<%--                <p class="signUp-text">Sign Up</p>--%>
<%--                <div class="underline-signup"></div>--%>
<%--                <div class="form-signUp-all">--%>
<%--                    <input type="text" placeholder="Name account">--%>
<%--                    <input id="password" onchange="confirmPass()" type="password" placeholder="Password">--%>
<%--                </div>--%>
<%--                <div class="form-signUp-all">--%>
<%--                    <input id="passwordConfirm"  type="password" placeholder="confirm password">--%>
<%--                </div>--%>
<%--                <div class="form-signUp-all">--%>
<%--                    <input type="text" placeholder="Email address">--%>
<%--                </div>--%>
<%--                <div class="form-signUp-all">--%>
<%--                    <input type="number" min="10" max="10" placeholder="Phone number">--%>
<%--                </div>--%>
<%--                <p class="form-signUp-all">Date of birth</p>--%>
<%--                <div class="form-signUp-all">--%>
<%--                    <label>--%>
<%--                        <select>--%>
<%--                            <c:forEach var="i" begin="1" end="31" step="1">--%>
<%--                                <option value="${i}">${i}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
<%--                    </label>--%>
<%--                    <label>--%>
<%--                        <select>--%>
<%--                            <c:forEach var="i" begin="1" end="12" step="1">--%>
<%--                                <option value="${i}">${i}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
<%--                    </label>--%>
<%--                    <label>--%>
<%--                        <select>--%>
<%--                            <c:forEach var="i" begin="1905" end="2023">--%>
<%--                                <option value="${i}">${i}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
<%--                    </label>--%>
<%--                </div>--%>
<%--                <div class="form-signUp-all">--%>
<%--                    <label>--%>
<%--                        <span>Male</span>--%>
<%--                        <input type="radio" value="Male" name="gender">--%>
<%--                    </label>--%>
<%--                    <label>--%>
<%--                        <span>Female</span>--%>
<%--                        <input type="radio" value="Female" name="gender">--%>
<%--                    </label>--%>
<%--                    <label>--%>
<%--                        <span>Other</span>--%>
<%--                        <input type="radio" value="Other" name="gender">--%>
<%--                    </label>--%>
<%--                </div>--%>
<%--                <div class="form-signUp-all">--%>
<%--                    <input type="submit" value="Sign Up">--%>
<%--                </div>--%>
<%--            </form>--%>






    <form class="needs-validation edit form-signUp" novalidate>
        <div class="title-form">
            <span>Sign up</span>
            <input type="button" class="icon" onclick="hideFormSignUp()" value="x">
        </div>
        <div class="underline-signup"></div>
        <div class="form-row">
            <div class="col-md-4 mb-3">
                <label for="validationCustom01">Email</label>
                <input type="email" class="form-control" id="validationCustom01" placeholder="Example: hello@gmail.com"
                       required>
<%--                <div class="valid-feedback">--%>
<%--                    Looks good!--%>
<%--                </div>--%>
            </div>
            <div class="col-md-4 mb-3">
                <label for="validationCustom02">Password</label>
                <input type="password" class="form-control" id="validationCustom02" placeholder="password" minlength="6" maxlength="32"  required>
<%--                <div class="valid-feedback">--%>
<%--                    Looks good!--%>
<%--                </div>--%>
            </div>
            <div class="col-md-4 mb-3">
                <label for="validationCustom03">Confirm password</label>
                <input type="password" class="form-control" id="validationCustom03" placeholder="confirm password" minlength="6" maxlength="32"   required>
                <div class="valid-feedback">
                    <p id="notify"></p>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <label for="validationCustomUsername">Name account</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroupPrepend"><svg xmlns="http://www.w3.org/2000/svg"
                                                                               height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path
                            d="M304 128a80 80 0 1 0 -160 0 80 80 0 1 0 160 0zM96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM49.3 464H398.7c-8.9-63.3-63.3-112-129-112H178.3c-65.7 0-120.1 48.7-129 112zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3z"/></svg></span>
                    </div>
                    <input type="text" class="form-control" id="validationCustomUsername" placeholder="Username"
                           aria-describedby="inputGroupPrepend" required>
                    <div class="invalid-feedback">
                        Choose a username
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="validationCustom04">Phone number</label>
                <input type="text" pattern="\d*" class="form-control" id="validationCustom04" placeholder="Phone number" maxlength="10" minlength="10" inputmode="numeric" required>
                <div class="invalid-feedback">
                    Provide a valid phone
                </div>
            </div>
        </div>
        <div class="title-birth">
            <span>Date of birth ( day/month/year )</span>
        </div>
        <div class="birthday">
            <label class="div-label-select">
                <select class="label-select">
                    <c:forEach var="i" begin="1" end="31" step="1">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </label>
            <label class="div-label-select">
                <select class="label-select">
                    <c:forEach var="i" begin="1" end="12" step="1">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </label>
            <label class="div-label-select">
                <select class="label-select">
                    <c:forEach var="i" begin="1905" end="2023">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </label>
        </div>


        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                <label class="form-check-label" for="invalidCheck">
                    Agree to terms and conditions
                </label>
                <div class="invalid-feedback">
                    You must agree before submitting.
                </div>
            </div>
        </div>
        <div class="underline-signup"></div>
        <button class="btn btn-primary" type="submit">Submit form</button>
    </form>
        </div>
    </div>
    <script>
        (function () {
            'use strict';
            window.addEventListener('load', function () {
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

        form.addEventListener('submit', function(event) {
            if (password.value !== passwordConfirm.value) {
                event.preventDefault();
                event.stopPropagation();
                passwordConfirm.classList.add('is-invalid');
                alert("your password is something wrong")
            }
            form.classList.add('was-validated');
        });

        function checkPassword(){
            const pass = password.value;
            const passConfirm = passwordConfirm.value;
            if (pass === passConfirm){
                notify.textContent = "confirm password";
            }else {
                notify.textContent ="wrong password";
            }
        }
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
                            <input type="text" placeholder="Email address or Name account"
                                   class="input-a-signIn-signUp inputLogin border-all border-login" required>
                        </div>
                        <div class="loginDiv">
                            <input type="password" placeholder="Password"
                                   name="password" class="input-a-signIn-signUp inputLogin border-all border-login" required>
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
                        <input type="button" onclick="showFormSignUp()"
                               class="input-a-signIn-signUp input-SignUp border-all" value="Create new account">
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

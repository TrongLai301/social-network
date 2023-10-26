<%@ page import="java.io.PrintWriter" %><%--@elvariable id="userNeedToEdit" type="model.User"--%>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 23/10/2023
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/ef7e2b893b.js" crossorigin="anonymous"></script>
</head>

<body>
<section style="background-color: #eee;">
    <div class="container py-5">
        <div class="row">
            <div class="col">
                <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="/user">Home</a></li>
                        <li class="breadcrumb-item"><a href="#">User</a></li>
                        <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                    </ol>
                </nav>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4">
                <div class="card mb-4">
                    <div class="card-body text-center">
                        <img src="${userNeedToEdit.avatar}"
                             alt="avatar"
                             class="rounded-circle img-fluid" style="width: 150px;">
                        <br>
                        <h5 class="my-3">${userNeedToEdit.name}</h5>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <form id="js.form" action="/user" method="post">
                    <input type="hidden" name="actionPost" value="updateUser">
                    <input type="hidden" name="id" value="${userNeedToEdit.id}">
                    <input type="hidden" name="username" value="${userNeedToEdit.username}" >
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Full Name</p>
                                </div>
                                <div class="col-sm-9">
                                    <input class="js.inputField js.inputField2" type="text" name="name" onkeyup="textOnly(this)"
                                           value="${userNeedToEdit.name}">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Email</p>
                                </div>
                                <div class="col-sm-9">
                                    <input class="js.inputField" type="email" name="email" value="${userNeedToEdit.email}">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Avatar URL</p>
                                </div>
                                <div class="col-sm-9">
                                    <input class="js.inputField" type="text" name="avatar" onkeyup="textOnly(this)"
                                           value="${userNeedToEdit.avatar}">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Phone</p>
                                </div>
                                <div class="col-sm-9">
                                    <input class="js.inputField" type="tel" maxlength="11" minlength="10" name="phone" value="${userNeedToEdit.phone}">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Birth</p>
                                </div>
                                <div class="col-sm-9">
                                    <input class="js.inputField" id="js.birthInput" type="date" name="birth" value="${userNeedToEdit.birth}">

                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Address</p>
                                </div>
                                <div class="col-sm-9">
                                    <input class="js.inputField js.inputField2" onkeyup="textOnly(this)" type="text" name="address"
                                           value="${userNeedToEdit.address}">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Hooby</p>
                                </div>
                                <div class="col-sm-9">
                                    <input class="js.inputField js.inputField2" onkeyup="textOnly(this)" type="text" name="hobby"
                                           value="${userNeedToEdit.hobby}">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="d-flex justify-content-center mb-2">
                                    <button id="js.submitButton" disabled type="submit" class="btn btn-primary">Save</button>
                                </div>
                            </div>
                            <div class="row ">
                                <p class="text-justify" style="color: red">
                                    <c:if test="${requestScope['message'] != null}">
                                        ${requestScope["message"]}
                                    </c:if>
                                </p>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

</body>
<script>
    setMaxDateForInputBirth();
    setInputAvailable();

    function setMaxDateForInputBirth() {
        let today = new Date();
        today.setFullYear(today.getFullYear() - 13);
        document.getElementById("js.birthInput").max = today.toISOString().split("T")[0];

    }

    function setInputAvailable() {
        let submitBtn = document.getElementById("js.submitButton");
        let inputs = document.getElementsByClassName("js.inputField")
        for (let i = 0; i < inputs.length; i++) {
            inputs[i].onchange = function () {
                submitBtn.disabled = false
            }
        }
    }

    function textOnly(input) {
        var text = /[^aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ
fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu
UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]/gi;
        input.value = input.value.replace(text, "")
    }


</script>
</html>

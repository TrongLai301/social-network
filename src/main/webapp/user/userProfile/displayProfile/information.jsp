<%@ page import="java.io.PrintWriter" %><%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: trong--%>
<%--  Date: 10/25/23--%>
<%--  Time: 9:10 AM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Facebook</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shortcut icon" type="image/png" href="https://static.xx.fbcdn.net/rsrc.php/yb/r/hLRJ1GG_y0J.ico">
    <link rel="stylesheet" href="../../../display-home/style.css">
    <script src="https://kit.fontawesome.com/ef7e2b893b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../../../public/css/user/profile.css">
    <%--    <script src="../display-home/function.js"></script>--%>
</head>

<div class="form-edit" id="edit">
    <div class="editFormDiv" id="divEditForm">
        <%--        <form class="editForm" method="post" action="/user?actionPost=editStatus" id="editForm">--%>
        <%--            <div class="header-edit">--%>
        <%--                <p>Chỉnh sửa bài viết</p>--%>
        <%--                <input type="button" id="close" onclick="hideEdit()" value="x">--%>
        <%--            </div>--%>

        <%--            <div class="underline-edit"></div>--%>
        <%--            <div class="infoHost">--%>
        <%--                <img class="imgHost" src="${requestScope.user.avatar}" style="height: 50px" alt="">--%>
        <%--                <p>${requestScope.user.name}</p>--%>
        <%--            </div>--%>
        <%--            <div class="imgTextEdit">--%>
        <%--                <div class="contentWrapper"></div>--%>
        <%--                            <option value="1">public</option>--%>
        <%--                            <option value="2">private</option>--%>
        <%--                        </select>--%>
        <%--                    </div>--%>
        <%--&lt;%&ndash;                    <div class="divImgEdit" id="imgStatus">&ndash;%&gt;--%>
        <%--&lt;%&ndash;                        <input type="button" onclick="deleteImg()" value="x">&ndash;%&gt;--%>
        <%--&lt;%&ndash;                        <img src="${requestScope.user}" alt="">&ndash;%&gt;--%>
        <%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--            <div class="submit-edit">--%>
        <%--                <input type="submit" value="Edit">--%>
        <%--            </div>--%>
        <%--        </form>--%>
    </div>
</div>
<div class="form-post" id="post">
    <div class="editFormDiv" id="divPostForm">
        <form class="editForm" method="post" action="/user?actionPost=uploadNewStatus">
            <div class="header-edit">
                <p>Post stauts</p>
                <input type="button" id="closePost" onclick="hidePost()" value="x">
            </div>

            <div class="underline-edit"></div>
            <div class="infoHost">
                <img class="imgHost" src="${requestScope.user.avatar}" style="height: 50px;width: 50px" alt="">
                <div class="displayName">
                    <p class="p">${requestScope.user.name}</p>
                    <select class="select1" name="option">
                        <option value="1">public</option>
                        <option value="2">private</option>
                    </select>
                </div>
            </div>
            <div class="imgTextEdit">
                <div class="contentWrapper">
                    <div class="textarea">
                        <textarea placeholder="What do you think?" oninput="descriptions(this)"
                                  name="description" class="textareaDescription"></textarea>
                    </div>
                    <div class="textarea">
                        <textarea placeholder="your picture?" name="media" class="textareaDescription"></textarea>
                    </div>
                </div>
            </div>
            <div class="submit-edit">
                <input type="submit" value="upload">
            </div>
        </form>
    </div>
</div>
<script>
    function description(textarea) {
        textarea.style.height = "auto";
        textarea.style.height = textarea.scrollHeight + "px";
    }

    function deleteImg() {
        let img = document.getElementById("imgStatus");
        img.innerHTML = "";
    }
</script>
<div class="divFather">
    <nav class="navbar">
        <div class="nav-left">
            <%--        <img class="logo" src="images/logo.png" alt="">--%>
            <img class="logo" id="logoFB" onclick="loadWeb()" src="../display-home/images/logoFB.webp" alt="">
            <div class="search-box">
                <img src="../display-home/images/search.png" alt="">
                <form action="home?action=search" method="post">
                    <input type="text" placeholder="Search" name="searchContent">
                </form>
            </div>
        </div>
        <div class="nav-center">
            <ul class="navlogo">
                <li><img src="images/notification.png" alt=""></li>
                <li><a href="/home" style="text-decoration: none">
                    <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
                        <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                        <style>svg {
                            fill: #ffffff
                        }</style>
                        <path d="M575.8 255.5c0 18-15 32.1-32 32.1h-32l.7 160.2c0 2.7-.2 5.4-.5 8.1V472c0 22.1-17.9 40-40 40H456c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1H416 392c-22.1 0-40-17.9-40-40V448 384c0-17.7-14.3-32-32-32H256c-17.7 0-32 14.3-32 32v64 24c0 22.1-17.9 40-40 40H160 128.1c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2H104c-22.1 0-40-17.9-40-40V360c0-.9 0-1.9 .1-2.8V287.6H32c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z"/>
                    </svg>
                </a>
                </li>
                <%--                        <li><img src="images/inbox.png" alt=""></li>--%>
                <li>
                    <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                        <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                        <style>svg {
                            fill: #ffffff
                        }</style>
                        <path d="M224 0c-17.7 0-32 14.3-32 32V51.2C119 66 64 130.6 64 208v18.8c0 47-17.3 92.4-48.5 127.6l-7.4 8.3c-8.4 9.4-10.4 22.9-5.3 34.4S19.4 416 32 416H416c12.6 0 24-7.4 29.2-18.9s3.1-25-5.3-34.4l-7.4-8.3C401.3 319.2 384 273.9 384 226.8V208c0-77.4-55-142-128-156.8V32c0-17.7-14.3-32-32-32zm45.3 493.3c12-12 18.7-28.3 18.7-45.3H224 160c0 17 6.7 33.3 18.7 45.3s28.3 18.7 45.3 18.7s33.3-6.7 45.3-18.7z"/>
                    </svg>
                </li>
                <%--            <li><img src="images/video.png" alt=""></li>--%>
                <li>
                    <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 640 512">
                        <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                        <style>svg {
                            fill: #ffffff
                        }</style>
                        <path d="M96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3zM609.3 512H471.4c5.4-9.4 8.6-20.3 8.6-32v-8c0-60.7-27.1-115.2-69.8-151.8c2.4-.1 4.7-.2 7.1-.2h61.4C567.8 320 640 392.2 640 481.3c0 17-13.8 30.7-30.7 30.7zM432 256c-31 0-59-12.6-79.3-32.9C372.4 196.5 384 163.6 384 128c0-26.8-6.6-52.1-18.3-74.3C384.3 40.1 407.2 32 432 32c61.9 0 112 50.1 112 112s-50.1 112-112 112z"/>
                    </svg>
                </li>
            </ul>
        </div>
        <%--        logo user--%>
        <div class="nav-right">
            <div class="profile-image online" onclick="UserSettingToggle()">
                <img src="${requestScope.user.getAvatar()}" style="height: 40px;" alt="">
            </div>

        </div>
        <div class="user-settings">
            <div class="profile-darkButton">
                <div class="user-profile">
                    <img src="${requestScope.user.avatar}" style="height: 50px;width: 50px" alt="">
                    <div>
                        <p>${requestScope.user.name}</p>
                        <a href="/user?actionGet=showUserProfile">See your profile</a>
                    </div>
                </div>
                <div id="dark-button" onclick="darkModeON()">
                    <span></span>
                </div>
            </div>
            <hr>
            <div class="user-profile">
                <img src="../display-home/images/feedback.png" alt="">
                <div>
                    <p> Give Feedback</p>
                    <a href="#">Help us to improve</a>
                </div>
            </div>
            <hr>
            <div class="settings-links">
                <img src="../display-home/images/setting.png" alt="" class="settings-icon">
                <a href="/user?actionGet=showEditPassword">Settings password <img src="../display-home/images/arrow.png"
                                                                                  alt=""></a>
            </div>

            <div class="settings-links">
                <img src="../display-home/images/help.png" alt="" class="settings-icon">
                <a href="#">Help & Support <img src="../display-home/images/arrow.png" alt=""></a>
            </div>

            <div class="settings-links">
                <img src="../display-home/images/display.png" alt="" class="settings-icon">
                <a href="user?actionGet=updateUserProfile">Edit Profile <img src="../display-home/images/arrow.png"
                                                                             alt=""></a>
            </div>

            <div class="settings-links">
                <img src="../display-home/images/logout.png" alt="" class="settings-icon">
                <a href="/session">Logout <img src="../display-home/images/arrow.png" alt=""></a>
            </div>

        </div>
    </nav>
    <script>
        const logo = document.getElementById("logoFB");

        function loadWeb() {
            setTimeout(function () {
                location.reload()
                location.scrollTo(0, 0)
            }, 1000)
        }
    </script>
    <div style="width: 100% ; height: 400px;background: url('https://images5.alphacoders.com/129/1298299.jpg') no-repeat;background-size: 100% 100%">
    </div>
    <div style="width: 100% ; height: 160px;background-color: #ffffff;display: inline-flex;justify-content: space-between;border-radius: 15px">
       <div style=" float: left;   display: inline-flex; justify-content: space-between;align-items: center; padding-bottom: 100px;  padding-left: 30px;">
           <div><img src="${requestScope.userFind.avatar}" style="border: 1px solid; border-radius: 50%;  height: 150px;width: 150px"></div>
           <div><p style="padding-left: 20px; font-size: 1.7em;padding-top: 10px;font-weight: 500;">${requestScope.userFind.name}<br>
               <p style="padding-left: 20px;">Bạn bè : ${requestScope.countFriend} </p></div>
       </div>
        <div style="float: right;
    display: inline-flex;
    justify-content: space-between;
    align-items: center;
    width: 350px;
    padding-right: 30px;
}">
            <c:choose>
                <c:when test="${requestScope.relationship.equals('stranger')}">
                    <div>
                        <button style="border: 1px solid;
    border-radius: 15px;
    width: 100px;
    height: 30px;">
                            <a href="${pageContext.request.contextPath}/friend?actionGet=sendFriendRequest&id=${requestScope.userFind.id}">
                                Add Friend </a></button>
                    </div>
                </c:when>
                <c:when test="${requestScope.relationship.equals('pending')}">
                    <div>
                        <button style="border: 1px solid;
    border-radius: 15px;
    width: 100px;
    height: 30px;"><a href=""> x Cancel </a></button>
                    </div>
                </c:when>
            </c:choose>
            <div>
                <button style="border: 1px solid;
    border-radius: 15px;
    width: 200px;
    height: 30px;">Edit profile
                </button>
            </div>
        </div>

    </div>
    <div class="container">

        <!-- main-content------- -->
        <div class="content-area" style="float: right">
                <div class="status-field-container write-post-container">
                    <div class="user-profile-box" style="display: flex; align-items: flex-start; flex-direction: column;justify-content: space-between">
                     <div>
                         <p style="float: left">User name :</p>
                         <p style="float: right;padding-left: 40px">${requestScope.userFind.username}</p>

                     </div>
                        <div>
                            <p style="float: left">Avatar :</p>
                            <p style="float: right;padding-left: 40px">${requestScope.userFind.avatar}</p>

                        </div>
                        <div>
                            <p style="float: left">Họ và tên :</p>
                            <p style="float: right;padding-left: 40px">${requestScope.userFind.name}</p>

                        </div>
                        <div>
                            <p style="float: left">Địa chỉ :</p>
                            <p style="float: right;padding-left: 40px">${requestScope.userFind.address}</p>

                        </div>
                        <div>
                            <p style="float: left">SĐT :</p>
                            <p style="float: right;padding-left: 40px">${requestScope.userFind.phone}</p>

                        </div>
                        <div>
                            <p style="float: left">Sở thích :</p>
                            <p style="float: right;padding-left: 40px">${requestScope.userFind.hobby}</p>

                        </div>
                        <div>
                            <p style="float: left">Số lượng bạn bè :</p>
                            <p style="float: right;padding-left: 40px">${requestScope.countFriend}</p>

                        </div>

                    </div>

                </div>

                            <script>
                                let options;
                                let formEdit;
                                let hideOption = document.getElementsByClassName("options")
                                let body = document.querySelector("body");

                                function edit() {
                                    body.style.overflow = "hidden"
                                }
                            </script>

                        <script>
                            function toggleOptions(event) {
                               let options = event.target.nextElementSibling;
                                options.classList.toggle("show");
                            }

                                function toggleOptions(event) {
                                    options = event.target.nextElementSibling;
                                    options.classList.toggle("show");
                                }

                                function optionEdit() {
                                    formEdit = document.querySelector(".form-edit");
                                    options = document.querySelector(".options")
                                    options.classList.toggle("show");
                                    formEdit.classList.toggle("showEdit");
                                }

                                function hideEdit() {
                                    formEdit = document.querySelector(".form-edit");
                                    formEdit.classList.toggle("showEdit");
                                    body.style.overflow = "auto";
                                }

                            function post() {
                                formPost = document.querySelector(".form-post");
                                formPost.classList.toggle("showPost");
                            }

                            function hidePost() {
                                formPost = document.querySelector(".form-post");
                                formPost.classList.toggle("showPost");
                            }
                        </script>

                                </div>
                          </div>
</div>


<footer id="footer">
    <p>&copy; Copyright 2021 - Socialbook All Rights Reserved</p>
</footer>
<script src="../display-home/function.js"></script>
</body>
</html>

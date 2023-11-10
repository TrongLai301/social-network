<%@ page import="java.io.PrintWriter" %>
<%@ page errorPage="homeFB.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Facebook</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shortcut icon" type="image/png" href="https://static.xx.fbcdn.net/rsrc.php/yb/r/hLRJ1GG_y0J.ico">
    <link rel="stylesheet" href="../user/userProfile/friend/style.css">
    <script src="https://kit.fontawesome.com/ef7e2b893b.js" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/39e48099af.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body onload="myFunction()">
<%--<c:if test="${sessionScope.message != null}">--%>
<%--<%--%>
<%--    PrintWriter printWriter = response.getWriter();--%>
<%--    printWriter.println("<script type=\"text/javascript\">");--%>
<%--    printWriter.println("alert('bạn không phải là bạn bè của người dùng này , hãy kết bạn với người dùng đó');");--%>
<%--    printWriter.println("</script>");--%>
<%--%>--%>

<div class="form-post" id="post">
    <div class="editFormDiv" id="divPostForm">
        <form class="editForm" method="post" action="/user?actionPost=uploadNewStatus" enctype="multipart/form-data">
            <div class="header-edit">
                <p>Create status</p>
                <div role="button" id="closePost" onclick="hidePost()">
                    <i>
                        <img style="width: 30px" src="../display-home/images/cross.png">
                    </i>
                </div>
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
                        <textarea id="desciption" placeholder="What do you think?" oninput="descriptions(this)"
                                  name="description" class="textareaDescription"></textarea>
                    </div>
                    <div style="text-align: center">
                        <img src="" alt="" id="image" width="400" height="250"> <br/> <br/>
                        <input type="file" onchange="chooseFile(this)" id="imageFile" name="file" value="" size="60"/>
                        <script>
                            function chooseFile(fileInput) {
                                var reader = new FileReader();

                                reader.onload = function (e) {
                                    $('#image').attr('src', e.target.result);
                                }
                                reader.readAsDataURL(fileInput.files[0]);
                            }
                        </script>
                    </div>
                </div>
            </div>
            <div id="submit" class="submit-edit" style="margin-bottom: 20px">
                <input type="submit" value="upload">
            </div>
            <div>
                <%--                <script>--%>
                <%--                    const description = document.getElementById("desciption")--%>
                <%--                    const submit = document.getElementById("submit")--%>
                <%--                    description.addEventListener("input", checkError)--%>

                <%--                    function checkError(){--%>
                <%--                        const descrip = description.value;--%>
                <%--                        if (description == ""){--%>
                <%--                            submit.hidden--%>
                <%--                        }else {--%>
                <%--                            submit.style.vi--%>
                <%--                        }--%>
                <%--                    }--%>
                <%--                </script>--%>
            </div>
        </form>
    </div>
</div>
<script>
    function descriptions(textarea) {
        textarea.style.height = "auto";
        textarea.style.height = textarea.scrollHeight + "px";
    }
</script>
<div class="divFather">
    <nav class="navbar">
        <div class="nav-left">
            <%--        <img class="logo" src="images/logo.png" alt="">--%>
            <img class="logo" id="logoFB" onclick="loadWeb()" src="../user/userProfile/images/logoFB.webp" alt="">
            <div class="search-box">
                <img src="../display-home/images/search.png" alt="">
                <form action="home?action=search" method="post">
                    <input type="text" placeholder="Search example:content,user,..." name="searchContent">
                </form>
            </div>
        </div>
        <div class="nav-center">
            <ul class="navlogo">
                <%--            <li><img src="images/notification.png" alt=""></li>--%>
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
                <%--            <li><img src="images/inbox.png" alt=""></li>--%>
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
                    <a href="/user?actionGet=showListFriendsUser&id=${requestScope.user.id}">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 640 512">
                            <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                            <style>svg {
                                fill: #ffffff
                            }</style>
                            <path d="M96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3zM609.3 512H471.4c5.4-9.4 8.6-20.3 8.6-32v-8c0-60.7-27.1-115.2-69.8-151.8c2.4-.1 4.7-.2 7.1-.2h61.4C567.8 320 640 392.2 640 481.3c0 17-13.8 30.7-30.7 30.7zM432 256c-31 0-59-12.6-79.3-32.9C372.4 196.5 384 163.6 384 128c0-26.8-6.6-52.1-18.3-74.3C384.3 40.1 407.2 32 432 32c61.9 0 112 50.1 112 112s-50.1 112-112 112z"/>
                        </svg>
                    </a>
                </li>
            </ul>
        </div>
        <%--        logo user--%>
        <div class="nav-right">
            <div class="profile-image online" onclick="UserSettingToggle()">
                <img src="${requestScope.user.getAvatar()}" style="height: 50px;width: 50px" alt="">
            </div>

        </div>
        <div class="user-settings">
            <div class="profile-darkButton">
                <div class="user-profile">
                    <img src="${requestScope.user.avatar}" style="height: 50px;width: 50px" alt="">
                    <div>
                        <p>${requestScope.user.name} </p>
                        <a href="user?actionGet=showUserProfile&id=${requestScope.user.id}">See your profile</a>
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
                <a href="/user?actionGet=showEditPassword">Change password<img
                        src="../display-home/images/arrow.png" alt=""></a>
            </div>

            <div class="settings-links">
                <img src="../display-home/images/help.png" alt="" class="settings-icon">
                <a href="#">Help & Support <img src="../display-home/images/arrow.png" alt=""></a>
            </div>

            <div class="settings-links">
                <img src="../display-home/images/display.png" alt="" class="settings-icon">
                <a href="user?actionGet=updateUserProfile&id=${requestScope.user.id}">Edit profile <img
                        src="../display-home/images/arrow.png" alt=""></a>
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

    <!-- content-area------------ -->

    <div class="container" style="justify-content: space-between;">
        <div class="left-sidebar">
            <div class="important-links">
                <a href="#"><img src="../display-home/images/friends.png" alt="">Friends</a>
            </div>
            <div class="shortcut-links">
                <p>Top your shortcuts</p>
                <a href="#"> <img src="../display-home/images/shortcut-1.png" alt="">Web Developers</a>
                <a href="#"> <img src="../display-home/images/shortcut-2.png" alt="">Web Design Course</a>
                <a href="#"> <img src="../display-home/images/shortcut-3.png" alt="">Full Stack Development</a>
                <a href="#"> <img src="../display-home/images/shortcut-4.png" alt="">Website Experts</a>
                <a href="https://vietnhan.co/ho-tro-khach-hang/su-khac-nhau-giua-website-va-facebook-217" style="text-decoration: none"> <img src="https://vietnhan.co/files/common/viet-nhan-co-xto0hnbp1zvmi4bmvdfv3ii7eek5mh4lo6h.jpg" alt="">Website vs FaceBook</a>
                <a href="https://thanhnien.vn/nhieu-website-go-nut-dang-nhap-bang-facebook-1851497994.htm" style="text-decoration: none"> <img src="https://images2.thanhnien.vn/Uploaded/thanhnx/2022_09_09/website-facebook-login-2-8806.jpeg" alt="">Facebook remove a lot of big website , Why ?</a>
                <a href="https://vnexpress.net/hiem-hoa-xe-ba-gac-cho-sat-thep-tren-duong-tp-hcm-4674971.html"> <img src="https://i1-vnexpress.vnecdn.net/2023/11/09/f2f2e03bc52613784a37-1699516536.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=KlNOYse8VKXuXtXPVBk3cA" alt="">Danger of transport "3 gang"</a>
                <a href="https://vnexpress.net/le-trao-thuong-thanh-tham-kich-cua-quan-doi-ukraine-4674953.html"> <img src="https://i1-vnexpress.vnecdn.net/2023/11/09/imrs-jpeg-6759-1699522831.jpg?w=0&h=0&q=100&dpr=2&fit=crop&s=GEPd5YPbmWf2415huPg4IQ" alt="">The award ceremony turned into a tragedy for the Ukrainian army</a>
                <a href="https://vnexpress.net/chinh-phu-moi-cua-slovakia-huy-goi-vien-tro-quan-su-cho-ukraine-4674644.html"> <img src="https://i1-vnexpress.vnecdn.net/2023/11/08/2023-10-27T101752Z-58012558-RC-6961-7332-1699458649.jpg?w=0&h=0&q=100&dpr=2&fit=crop&s=sN3gt2Iy3Ln6mBS7kn3f5g" alt="">Slovakia's new government cancels military aid package for Ukraine</a>
            </div>
        </div>

        <!-- main-content------- -->

        <div class="content-area">
            <div class="write-post-container">
                <div class="user-profile">
                    <img src="${requestScope.user.getAvatar()}" style="height: 50px;width: 50px" alt="">
                    <div>
                        <p>${requestScope.user.name}</p>
                        <small><i class="fa-solid fa-earth-asia"></i> <i class="fas fa-caret-down"></i></small>
                    </div>
                </div>

                <div class="post-upload-textarea">
                    <textarea name="" placeholder="What's on your mind ?" id="" cols="30" rows="3"
                              onclick="post()"></textarea>
                    <div class="add-post-links">
                        <a href="#"><img src="../display-home/images/live-video.png" alt="">Live Video</a>
                        <a href="#"><img src="../display-home/images/photo.png" alt="">Photo/Video</a>
                        <a href="#"><img src="../display-home/images/feeling.png" alt="">Feeling Activity</a>
                    </div>
                </div>
            </div>

            <c:forEach var="post" items="${requestScope.listStatus}" varStatus="status">
                <c:set var="user" value="${requestScope.listUser[status.index]}"/>
                <c:set var="status" value="${requestScope.check[status.index]}"/>
                <div class="status-field-container write-post-container">
                    <div class="user-profile-box">
                        <div class="user-profile">
                            <img src="${user.avatar}" style="height: 50px;" alt="Avatar">
                            <div>
                                <a href="/user?actionGet=showUserProfile&id=${user.id}"
                                   style="text-decoration: none;color: black">${user.name} </a><br>
                                <small>${post.createTime}</small>
                                <c:choose>
                                    <c:when test="${post.permission == 1}">
                                        <small><i class="fa-solid fa-earth-asia"></i></small>
                                    </c:when>
                                    <c:otherwise>
                                        <small> <i class="fa-solid fa-lock"></i></small>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="ellipsis-container">
                            <p class="choose" onclick="toggleOptions(event)">
                                ...
                            </p>
                            <div class="options" id="option">
<%--                                <ul class="option-ul" style="list-style: none ;height: 60px;">--%>
<%--                                    <li>--%>
<%--                                        <div class="div-li">--%>
<%--                                            <form action="/user?actionPost=deleteStatus" method="post">--%>
<%--                                                <input type="hidden" name="idStatus" value="${post.id}">--%>
<%--                                                <input type="submit" value="Delete"/>--%>
<%--                                            </form>--%>
<%--                                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512"--%>
<%--                                                 class="icon-option">--%>
<%--                                                <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->--%>
<%--                                                <style>svg {--%>
<%--                                                    fill: #2265d8--%>
<%--                                                }</style>--%>
<%--                                                <path d="M576 128c0-35.3-28.7-64-64-64H205.3c-17 0-33.3 6.7-45.3 18.7L9.4 233.4c-6 6-9.4 14.1-9.4 22.6s3.4 16.6 9.4 22.6L160 429.3c12 12 28.3 18.7 45.3 18.7H512c35.3 0 64-28.7 64-64V128zM271 175c9.4-9.4 24.6-9.4 33.9 0l47 47 47-47c9.4-9.4 24.6-9.4 33.9 0s9.4 24.6 0 33.9l-47 47 47 47c9.4 9.4 9.4 24.6 0 33.9s-24.6 9.4-33.9 0l-47-47-47 47c-9.4 9.4-24.6 9.4-33.9 0s-9.4-24.6 0-33.9l47-47-47-47c-9.4-9.4-9.4-24.6 0-33.9z"/>--%>
<%--                                            </svg>--%>
<%--                                        </div>--%>
<%--                                    </li>--%>
<%--                                </ul>--%>
                            </div>
                        </div>
                    </div>

                    <div class="status-field">
                        <p>${post.description}</p>
                        <img src="${post.media}" alt="">
                    </div>
                    <div class="post-reaction">
                        <div class="post__react__information">
                            <div class="react__like">
                                <span class="likeCount">${post.likeCount} likes</span>
                            </div>
                            <div class="react__cmt__share">
                                <span class="commentCount">${post.commentCount}</span>
                            </div>
                        </div>
                        <div class="activity-icons">
                            <div class="likeAndUnlikeButton">
                                <c:choose>
                                    <c:when test="${ status != null}">
                                        <div>
                                            <a onclick="toggleLike(${post.id}, 'unlike', this)" href="#">
                                                <img class="like-button liked"
                                                     src="../display-home/images/like-blue.png">
                                            </a>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div>
                                            <a onclick="toggleLike(${post.id}, 'like', this)" href="#">
                                                <img class="like-button" src="../display-home/images/like.png">
                                            </a>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div>
                                <a href="/home?idStatusCmt=${post.id}"><img src="../display-home/images/comments.png"
                                                                            alt=""></a>
                            </div>
                            <div><img src="../display-home/images/share.png" alt=""></div>

                        </div>
                    </div>

                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                    <script>
                        function toggleLike(idStatus, action, element) {
                            event.preventDefault();
                            var xhr = new XMLHttpRequest();
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    var response = JSON.parse(xhr.responseText);
                                    updateLikeCount(response.likeCount, element);
                                    $(element).toggleClass("liked");

                                    // Reload the page
                                    location.reload();
                                }
                            };
                            xhr.open("GET", "user?actionGet=likeStatus&action=" + action + "&idStatus=" + idStatus, true);
                            xhr.send();
                        }

                        function updateLikeCount(likeCount, element) {
                            var likeCountElement = $(element).closest(".likeAndUnlikeButton").find(".likeCount");
                            likeCountElement.text(likeCount);
                        }

                        function updateLikeCount(likeCount, element) {
                            var likeCountElement = $(element).closest(".likeAndUnlikeButton").find(".likeCount");
                            likeCountElement.text(likeCount);
                        }
                    </script>
                </div>
            </c:forEach>

            <script>
                let formPost;
                let formEdit;
                let hideOption = document.getElementsByClassName("options")
                let body = document.querySelector("body");

                function edit() {
                    body.style.overflow = "hidden"

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
            <button type="button" class="btn-LoadMore" onclick="LoadMoreToggle()">Load More</button>
        </div>

        <!-- sidebar------------ -->
        <div class="right-sidebar">
            <div class="heading-link">
                <h4>Events</h4>
                <a href="">See All</a>
            </div>
            <div class="heading-link">
                <h4>Advertisement</h4>
                <a href="">Close</a>
            </div>
            <div class="advertisement">
                <img src="../display-home/images/advertisement.png" class="advertisement-image" alt="">
            </div>

            <div class="heading-link">
                <h4>Conversation</h4>
                <a href="">Hide Chat</a>
            </div>

            <div class="online-list">
                <div class="online">
                    <img src="../display-home/images/member-1.png" alt="">
                </div>
                <p>Alison Mina</p>
            </div>

            <div class="online-list">
                <div class="online">
                    <img src="../display-home/images/member-2.png" alt="">
                </div>
                <p>Jackson Aston</p>
            </div>
            <div class="online-list">
                <div class="online">
                    <img src="../display-home/images/member-3.png" alt="">
                </div>
                <p>Samona Rose</p>
            </div>
        </div>
    </div>
</div>
<%--    Modal--%>
<div class="modal modal__open">
    <div class="modal__comment ">
        <div class="comment__header">
            <h2>Comments</h2>
            <i class="fa-solid fa-x js-close-modal"></i>
        </div>
        <div class="comment__body">
            <c:forEach items="${requestScope.comments}" var="cmt" varStatus="count">
                <c:set var="userComment" value="${requestScope.userComment[count.index]}"/>
                <div class="comment">
                    <div class="comment__avatar">
                        <img src="${userComment.avatar}">
                    </div>
                    <div class="comment__container">
                        <div class="comment__content">
                            <div>
                                <h5>${userComment.name}</h5>
                                <p>${cmt.content}</p>
                            </div>
                            <div class="comment__more">
                                <uL><i class="fa-solid fa-ellipsis"></i></uL>
                            </div>
                        </div>
                        <div class="comment__react">
                            <a class="react__like"><i class="fa-solid fa-thumbs-up" style="margin-right: 12px"></i> 0
                                Likes </a>
                                <%--                            <div class="comment__time">4 minutes ago</div>--%>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="comment__footer">
            <div class="user__comment">
                <div class="comment__avatar">
                    <img src="${requestScope.user.avatar}">
                </div>
                <div class="comment__container">
                    <form class="comment__content" action="/home" method="post">
                        <input type="hidden" name="action" value="addComment">
                        <input class="js__cmt" type="hidden" name="idStatus" value="">
                        <div>
                            <input type="text" name="commentContent">
                        </div>
                        <div class="comment__more">
                            <button class="reset__Button--css" type="submit">
                                <i class="fa-solid fa-paper-plane"></i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../display-home/function.js"></script>
<script src="../public/js/home/modal.js"></script>

<script>
    let url = window.location.href;
    if (!url.includes("idStatusCmt=")) {
        let modal = document.querySelector(".modal");
        modal.classList.remove('modal__open');
    }
    const indexOfIdStatusCmt = url.indexOf("idStatusCmt=");
    if (indexOfIdStatusCmt !== -1) {
        let inputComment = document.querySelector(".js__cmt");
        inputComment.value = url.substring(indexOfIdStatusCmt + 12);
    }
</script>
</body>
</html>
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
<body>
<c:if test="${requestScope.messageDelete != null}">
    <%
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<script type=\"text/javascript\">");
        printWriter.println("alert('xóa bài viết thành công');");
        printWriter.println("</script>");
    %>
</c:if><c:if test="${requestScope.messageDeleteFalse != null}">
    <%
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<script type=\"text/javascript\">");
        printWriter.println("alert('xóa bài viết thất bại');");
        printWriter.println("</script>");
    %>
</c:if>
<c:if test="${requestScope.messageEditComplete != null}">
    <%
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<script type=\"text/javascript\">");
        printWriter.println("alert('sửa bài viết thành công');");
        printWriter.println("</script>");
    %>
</c:if><c:if test="${requestScope.messageEditFalse != null}">
    <%
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<script type=\"text/javascript\">");
        printWriter.println("alert('sửa bài viết thất bại');");
        printWriter.println("</script>");
    %>
</c:if>
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
                <a href="/user?actionGet=showEditPassword">Settings password <img src="../display-home/images/arrow.png" alt=""></a>
            </div>

            <div class="settings-links">
                <img src="../display-home/images/help.png" alt="" class="settings-icon">
                <a href="#">Help & Support <img src="../display-home/images/arrow.png" alt=""></a>
            </div>

            <div class="settings-links">
                <img src="../display-home/images/display.png" alt="" class="settings-icon">
                <a href="user?actionGet=updateUserProfile">Edit Profile <img src="../display-home/images/arrow.png" alt=""></a>
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
               <p style="padding-left: 20px;">Bạn bè : </p></div>
       </div>
        <div style="float: right;
    display: inline-flex;
    justify-content: space-between;
    align-items: center;
    width: 350px;
    padding-right: 30px;
}">
             <div><button style="border: 1px solid;
    border-radius: 15px;
    width: 100px;
    height: 30px;">up status</button></div>
             <div><button style="border: 1px solid;
    border-radius: 15px;
    width: 200px;
    height: 30px;">Edit profile</button></div>
        </div>

    </div>
    <div class="container" style="justify-content: space-between;display: flex" >

        <!-- main-content------- -->
       <div style="float: left;height: 100%;width: 300px;" >
           <div style="background-color: #ffffff;width: 100%; height: 400px;border-radius: 10px;">
             <p ><h3 >Giới thiệu</h3></p>
               <br>
               <div style="text-align: center">${requestScope.userFind.hobby}</div>
               <p><h4>Quê quán</h4></p>
               <div style="text-align: center">${requestScope.userFind.address}</div>
               <br>
               <p><h4>Sinh nhật</h4></p>
               <div style="text-align: center">${requestScope.userFind.birth}</div>
           </div><br>
           <div style="background-color: #ffffff;width: 100%; height: 400px;border-radius: 10px;">
               <p ><h3 >Picture library</h3></p>
               <br>
               <c:forEach var="media" items="${requestScope.listStatus}">
                   <div style="padding-top: 20px;padding-left: 10px">
                       <div ><img src="${media.media}" style="width: 100px"></div>
                   </div>
               </c:forEach>
           </div><br>
           <div style="background-color: #ffffff;width: 100%; height: 400px;border-radius: 10px;">3</div>
       </div>
        <div class="content-area" style="float: right">
            <div class="write-post-container">
                <div class="user-profile">
                    <img src="${requestScope.user.getAvatar()}" style="height: 50px;width: 50px" alt="">
                    <div>
                        <p>${requestScope.user.name}</p>
                        <small>Public <i class="fas fa-caret-down"></i></small>
                    </div>
                </div>

                <div class="post-upload-textarea">
                    <textarea name="" placeholder="What's on your mind ?" id="" cols="30" rows="3" onclick="post()"></textarea>
                    <div class="add-post-links">
                        <a href="#"><img src="../display-home/images/live-video.png" alt="">Live Video</a>
                        <a href="#"><img src="../display-home/images/photo.png" alt="">Photo/Video</a>
                        <a href="#"><img src="../display-home/images/feeling.png" alt="">Feeling Activity</a>
                    </div>
                </div>
            </div>

            <c:forEach var="post" items="${requestScope.listStatus}" varStatus="status">
                <c:set var="user" value="${requestScope.listUser[status.index]}" />
                <c:set var="status" value="${sessionScope.status[status.index]}"/>
                <div class="status-field-container write-post-container">
                    <div class="user-profile-box">
                        <div class="user-profile">
                            <img src="${user.avatar}" style="height: 50px;width: 50px" alt="Avatar">
                            <div>
                                <p>${user.name}</p>
                                <small>${post.createTime}</small>
                                <c:choose>
                                    <c:when test="${post.permission == 1}">
                                        <small>public</small>
                                    </c:when>
                                    <c:otherwise>
                                        <small>private</small>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="ellipsis-container">
                            <p class="choose" onclick="toggleOptions(event)">
                                ...
                            </p>
                            <div class="options" id="option" style="height: 500px">
                                <ul class="option-ul" style="list-style: none">
                                    <li onclick="">
                                        <div class="div-li"  onclick="edit()">
                                            <form action="/user?actionPost=editStatus" method="post">
                                                <input type="hidden" name="idStatus" value="${post.id}">
                                                <input type="text" name="description" value="${post.description}" >
                                                <input type="text" name="media" value="${post.media}" >
                                                <select name="option">
                                                    <option value="1">public</option>
                                                    <option value="2">private</option>
                                                </select>
                                                <input type="submit" value="Edit Status"/>
                                            </form>
                                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                                <style>svg {
                                                    fill: #2265d8
                                                }</style>
                                                <path d="M471.6 21.7c-21.9-21.9-57.3-21.9-79.2 0L362.3 51.7l97.9 97.9 30.1-30.1c21.9-21.9 21.9-57.3 0-79.2L471.6 21.7zm-299.2 220c-6.1 6.1-10.8 13.6-13.5 21.9l-29.6 88.8c-2.9 8.6-.6 18.1 5.8 24.6s15.9 8.7 24.6 5.8l88.8-29.6c8.2-2.7 15.7-7.4 21.9-13.5L437.7 172.3 339.7 74.3 172.4 241.7zM96 64C43 64 0 107 0 160V416c0 53 43 96 96 96H352c53 0 96-43 96-96V320c0-17.7-14.3-32-32-32s-32 14.3-32 32v96c0 17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H96z"/>
                                            </svg>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="underline"></div>
                                    </li>
                                    <li>
                                        <div class="div-li">
                                            <form action="/user?actionPost=deleteStatus" method="post">
                                                <input type="hidden" name="idStatus" value="${post.id}">
                                                <input type="submit" value="Delete"/>
                                            </form>
                                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512"
                                                 class="icon-option">
                                                <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                                <style>svg {
                                                    fill: #2265d8
                                                }</style>
                                                <path d="M576 128c0-35.3-28.7-64-64-64H205.3c-17 0-33.3 6.7-45.3 18.7L9.4 233.4c-6 6-9.4 14.1-9.4 22.6s3.4 16.6 9.4 22.6L160 429.3c12 12 28.3 18.7 45.3 18.7H512c35.3 0 64-28.7 64-64V128zM271 175c9.4-9.4 24.6-9.4 33.9 0l47 47 47-47c9.4-9.4 24.6-9.4 33.9 0s9.4 24.6 0 33.9l-47 47 47 47c9.4 9.4 9.4 24.6 0 33.9s-24.6 9.4-33.9 0l-47-47-47 47c-9.4 9.4-24.6 9.4-33.9 0s-9.4-24.6 0-33.9l47-47-47-47c-9.4-9.4-9.4-24.6 0-33.9z"/>
                                            </svg>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                                 <div class="status-field">
                                     <p>${post.description}</p>
                                     <img src="${post.media}"/>
                                    </div>
                                    <div class="post-reaction">
                                        <div class="activity-icons">
                                            <c:choose>
                                                <c:when test="${status == 'like'}">
                                                    <div><a href="/home?actionGet=dislike&id=${post.id}"><img src="../../../display-home/images/like-blue.png"></a> </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div><a href="/home?actionGet=like&id=${post.id}"><img src="../../../display-home/images/like.png"></a> </div>
                                                </c:otherwise>
                                            </c:choose>


                                                ${post.likeCount}</div>
                                            <div><img src="../display-home/images/comments.png" alt="">52</div>
                                            <div><img src="../display-home/images/share.png" alt="">35</div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

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
                            <button type="button" class="btn-LoadMore" onclick="LoadMoreToggle()">Load More</button>
                        </div>

                    </div>
               </div>

                <footer id="footer">
                    <p>&copy; Copyright 2021 - Socialbook All Rights Reserved</p>
                </footer>
                <script src="../display-home/function.js"></script>
                </body>
                </html>

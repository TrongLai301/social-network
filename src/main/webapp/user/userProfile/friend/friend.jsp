<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Facebook</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shortcut icon" type="image/png" href="https://static.xx.fbcdn.net/rsrc.php/yb/r/hLRJ1GG_y0J.ico">
    <link rel="stylesheet" href="/user/userProfile/friend/style.css">
    <script src="https://kit.fontawesome.com/ef7e2b893b.js" crossorigin="anonymous"></script>

</head>
<body>
<div class="divFather">
    <nav class="navbar">
        <div class="nav-left">

            <img class="logo" id="logoFB" onclick="loadWeb()" src="../user/userProfile/images/logoFB.webp" alt="">
            <div class="search-box">
                <img src="../display-home/images/search.png" alt="">
                <form action="home?action=search" method="post">
                    <input type="text" placeholder="Search" name="searchContent">
                </form>
            </div>
        </div>
        <div class="nav-center">
            <ul class="navlogo">

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

                <li>
<%--                    <a href="/user?actionGet=showListFriendsUser&id=${requestScope.user.id}"/>--%>
                    <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                        <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                        <style>svg {
                            fill: #ffffff
                        }</style>
                        <path d="M224 0c-17.7 0-32 14.3-32 32V51.2C119 66 64 130.6 64 208v18.8c0 47-17.3 92.4-48.5 127.6l-7.4 8.3c-8.4 9.4-10.4 22.9-5.3 34.4S19.4 416 32 416H416c12.6 0 24-7.4 29.2-18.9s3.1-25-5.3-34.4l-7.4-8.3C401.3 319.2 384 273.9 384 226.8V208c0-77.4-55-142-128-156.8V32c0-17.7-14.3-32-32-32zm45.3 493.3c12-12 18.7-28.3 18.7-45.3H224 160c0 17 6.7 33.3 18.7 45.3s28.3 18.7 45.3 18.7s33.3-6.7 45.3-18.7z"/>
                    </svg>
                </li>

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

        <div class="nav-right">
            <div class="profile-image online" onclick="UserSettingToggle()">
                <img src="<c:out value="${user.getAvatar()}"/>" style="height: 50px;width: 50px" alt="">
            </div>

        </div>
        <div class="user-settings">
            <div class="profile-darkButton">
                <div class="user-profile">
                    <img src="<c:out value="${user.getAvatar()}"/>" style="height: 50px;width: 50px" alt="">
                    <div>
                        <p> </p>
                        <a href="user?actionGet=showUserProfile&id=<c:out value="${user.getId()}"/>">See your profile</a>
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
                <a href="/user?actionGet=showEditPassword">Settings & Privary <img
                        src="../display-home/images/arrow.png" alt=""></a>
            </div>

            <div class="settings-links">
                <img src="../display-home/images/help.png" alt="" class="settings-icon">
                <a href="#">Help & Support <img src="../display-home/images/arrow.png" alt=""></a>
            </div>

            <div class="settings-links">
                <img src="../display-home/images/display.png" alt="" class="settings-icon">
                <a href="user?actionGet=updateUserProfile&id=">Display & Accessibility <img
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


    <div class="header-profile">
        <div class="imgProfile">
            <div class="signatureImg">

            </div>
        </div>
        <div class="nav-Profile">
            <div class="optionHeader">
                <div class="avatarProfile"><img src="<c:out value="${user.getAvatar()}"/>"></div>
                <div class="introduction">
                    <div class="left-introduce">
                        <c:set var="friend1" scope="session" value="${idFriend}"/>
                        <c:if test="${friend1 == 0}">
                            <p class="user"><c:out value="${user.getName()}"/></p>
                        </c:if>
                        <c:if test="${friend1 != 0}">
                            <p class="user">${friend.getName()}</p>
                        </c:if>
                        <p>
                            <c:out value="${numberFriends}"/>
                            <span>Number friends</span>
                        </p>
                    </div>
                    <div class="right-introduce">
                        <input type="button" value="Edit">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><style>svg{fill:#256de9}</style><path d="M362.7 19.3L314.3 67.7 444.3 197.7l48.4-48.4c25-25 25-65.5 0-90.5L453.3 19.3c-25-25-65.5-25-90.5 0zm-71 71L58.6 323.5c-10.4 10.4-18 23.3-22.2 37.4L1 481.2C-1.5 489.7 .8 498.8 7 505s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L421.7 220.3 291.7 90.3z"/></svg>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="nav-feature">
        <div class="underline-profile"></div>
        <div class="feature">

        </div>
    </div>
</div>

<div class="container" style="background-color: #d3d3d3">
    <div class="container-profile">
        <div class="header-listFrs">
            <div><span style="color: black">Friends</span></div>
            <div class="searchFrs">
                <input type="text" placeholder="Search">
                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                    <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                    <style>svg {
                        fill: #2b65ca
                    }</style>
                    <path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"/>
                </svg>
            </div>
        </div>
        <div class="optionFrs"></div>
<%--        <div class="listFrs">--%>
<%--            <c:forEach  var="friend" items="${listFriends}" begin="0" varStatus="loopCounter">--%>
<%--                <div class="frs">--%>
<%--                    <a href="/user?actionGet=showUserProfile&id=<c:out value="${user.getId()}"/>&idFriend=${friend.getId()}">--%>
<%--                        <img src="${friend.getAvatar()}"--%>
<%--                                 alt="avatar">--%>
<%--                    </a>--%>
<%--                    <a href="/user?actionGet=showListFriendsUser&id=<c:out value="${user.getId()}"/>&idFriend=<c:out value="${friend.getId()}"/>">--%>
<%--                        <div style="float: right; margin-top: 27px; font-size: 18px; margin-left: 5px; color: black">--%>
<%--                            <p class="nameFrsInList">${friend.getName()}</p>--%>
<%--                            <p><c:out value="${numberFriendsBoth.get(loopCounter.count-1)}"/> mutual friends</p>--%>
<%--                        </div>--%>
<%--                    </a>--%>
<%--                    <a href="/user?actionGet=showListMutualFriendsUser&id=<c:out value="${user.getId()}"/>&idFriend=<c:out value="${friend.getId()}"/>">--%>
<%--                        <p>Show Mutual Mriends</p>--%>
<%--                    </a>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>
<%--        </div>--%>
        <c:set var="idPermission" scope="session" value="${permission}"/>
        <c:if test="${idPermission == 1}">
            <div class="listFrs">
                <div class="listFrs">
                    <c:forEach  var="friend" items="${listFriends}" begin="0" varStatus="loopCounter">
                        <div class="frs">
                            <a href="/user?actionGet=showUserProfile&id=<c:out value="${user.getId()}"/>&idFriend=${friend.getId()}">
                                <img src="${friend.getAvatar()}"
                                     alt="avatar">
                            </a>
                            <a href="/user?actionGet=showListFriendsUser&id=<c:out value="${user.getId()}"/>&idFriend=<c:out value="${friend.getId()}"/>">
                                <div style="float: right; margin-top: 27px; font-size: 18px; margin-left: 5px; color: black">
                                    <p class="nameFrsInList">${friend.getName()}</p>
<%--                                    <p><c:out value="${numberFriendsBoth.get(loopCounter.count-1)}"/> friends</p>--%>
                                </div>
                            </a>
                            <a href="/user?actionGet=showListMutualFriendsUser&id=<c:out value="${user.getId()}"/>&idFriend=<c:out value="${friend.getId()}"/>">
                                <p style="margin-left: 20px; margin-top: 26px">Mutual Mriends</p>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>
        <c:if test="${idPermission == 2}">
            <div style="width: 100%; height: 600px; background-color: #d3d3d3">

            </div>
        </c:if>
    </div>
</div>
<footer id="footer">
    <p></p>
</footer>
<script src="../display-home/function.js"></script>
</body>
</html>
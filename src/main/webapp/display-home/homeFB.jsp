<%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 10/25/23
  Time: 9:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Facebook</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shortcut icon" type="image/png" href="https://static.xx.fbcdn.net/rsrc.php/yb/r/hLRJ1GG_y0J.ico">
    <link rel="stylesheet" href="../display-home/style.css">
    <script src="https://kit.fontawesome.com/ef7e2b893b.js" crossorigin="anonymous"></script>
    <script src="../display-home/function.js"></script>
</head>
<body>
<nav class="navbar">
    <div class="nav-left">
        <%--        <img class="logo" src="images/logo.png" alt="">--%>
        <img class="logo" onclick="loadWEB()" src="../display-home/images/logoFB.webp" alt="">
        <div class="search-box">
            <img src="images/search.png" alt="">
            <input type="text" placeholder="Search">
        </div>
    </div>
    <div class="nav-center">
        <ul class="navlogo">
            <%--            <li><img src="images/notification.png" alt=""></li>--%>
            <li>
                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
                    <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                    <style>svg {
                        fill: #ffffff
                    }</style>
                    <path d="M575.8 255.5c0 18-15 32.1-32 32.1h-32l.7 160.2c0 2.7-.2 5.4-.5 8.1V472c0 22.1-17.9 40-40 40H456c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1H416 392c-22.1 0-40-17.9-40-40V448 384c0-17.7-14.3-32-32-32H256c-17.7 0-32 14.3-32 32v64 24c0 22.1-17.9 40-40 40H160 128.1c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2H104c-22.1 0-40-17.9-40-40V360c0-.9 0-1.9 .1-2.8V287.6H32c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z"/>
                </svg>
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
            <img src="images/profile-pic.png" alt="">
        </div>

    </div>
    <div class="user-settings">
        <div class="profile-darkButton">
            <div class="user-profile">
                <img src="images/profile-pic.png" alt="">
                <div>
                    <p> Alex Carry</p>
                    <a href="#">See your profile</a>
                </div>
            </div>
            <div id="dark-button" onclick="darkModeON()">
                <span></span>
            </div>
        </div>
        <hr>
        <div class="user-profile">
            <img src="images/feedback.png" alt="">
            <div>
                <p> Give Feedback</p>
                <a href="#">Help us to improve</a>
            </div>
        </div>
        <hr>
        <div class="settings-links">
            <img src="images/setting.png" alt="" class="settings-icon">
            <a href="#">Settings & Privary <img src="images/arrow.png" alt=""></a>
        </div>

        <div class="settings-links">
            <img src="images/help.png" alt="" class="settings-icon">
            <a href="#">Help & Support <img src="images/arrow.png" alt=""></a>
        </div>

        <div class="settings-links">
            <img src="images/Display.png" alt="" class="settings-icon">
            <a href="#">Display & Accessibility <img src="images/arrow.png" alt=""></a>
        </div>

        <div class="settings-links">
            <img src="images/logout.png" alt="" class="settings-icon">
            <a href="#">Logout <img src="images/arrow.png" alt=""></a>
        </div>

    </div>
</nav>

<!-- content-area------------ -->

<div class="container">
    <div class="left-sidebar">
        <div class="important-links">
            <a href="#"><img src="images/news.png" alt="">Latest News</a>
            <a href="#"><img src="images/friends.png" alt="">Friends</a>
            <a href="#"><img src="images/group.png" alt="">Groups</a>
            <a href="#"><img src="images/marketplace.png" alt="">marketplace</a>
            <a href="#"><img src="images/watch.png" alt="">Watch</a>
            <a href="#">See More</a>
        </div>

        <div class="shortcut-links">
            <p>Your Shortcuts</p>
            <a href="#"> <img src="images/shortcut-1.png" alt="">Web Developers</a>
            <a href="#"> <img src="images/shortcut-2.png" alt="">Web Design Course</a>
            <a href="#"> <img src="images/shortcut-3.png" alt="">Full Stack Development</a>
            <a href="#"> <img src="images/shortcut-4.png" alt="">Website Experts</a>
        </div>
    </div>

    <!-- main-content------- -->

    <div class="content-area">
        <%--        <div class="story-gallery">--%>
        <%--            <div class="story story1">--%>
        <%--                <img src="images/upload.png" alt="">--%>
        <%--                <p>Post Story</p>--%>
        <%--            </div>--%>
        <%--            <div class="story story2">--%>
        <%--                <img src="images/member-1.png" alt="">--%>
        <%--                <p>Alison</p>--%>
        <%--            </div>--%>
        <%--            <div class="story story3">--%>
        <%--                <img src="images/member-2.png" alt="">--%>
        <%--                <p>Jackson</p>--%>
        <%--            </div>--%>
        <%--            <div class="story story4">--%>
        <%--                <img src="images/member-3.png" alt="">--%>
        <%--                <p>Samona</p>--%>
        <%--            </div>--%>
        <%--            <div class="story story5">--%>
        <%--                <img src="images/member-4.png" alt="">--%>
        <%--                <p>John</p>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <div class="write-post-container">
            <div class="user-profile">
                <img src="images/profile-pic.png" alt="">
                <div>
                    <p> Alex Carry</p>
                    <small>Public <i class="fas fa-caret-down"></i></small>
                </div>
            </div>

            <div class="post-upload-textarea">
                <textarea name="" placeholder="What's on your mind, Alex?" id="" cols="30" rows="3"></textarea>
                <div class="add-post-links">
                    <a href="#"><img src="images/live-video.png" alt="">Live Video</a>
                    <a href="#"><img src="images/photo.png" alt="">Photo/Video</a>
                    <a href="#"><img src="images/feeling.png" alt="">Feeling Activity</a>
                </div>
            </div>
        </div>

        <div class="status-field-container write-post-container">
            <div class="user-profile-box">
                <div class="user-profile">
                    <img src="images/profile-pic.png" alt="">
                    <div>
                        <p> Alex Carry</p>
                        <small>August 13 1999, 09.18 pm</small>
                    </div>
                </div>
                <div>
                    <a href="#"><i class="fas fa-ellipsis-v"></i></a>
                </div>
            </div>
            <div class="status-field">
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Corporis dolores praesentium dicta
                    laborum nihil accusantium odit laboriosam, sed sit autem! <a
                            href="#">#This_Post_is_Better!!!!</a></p>
                <img src="images/feed-image-1.png" alt="">

            </div>
            <div class="post-reaction">
                <div class="activity-icons">
                    <div><img src="images/like-blue.png" alt="">120</div>
                    <div><img src="images/comments.png" alt="">52</div>
                    <div><img src="images/share.png" alt="">35</div>
                </div>
                <div class="post-profile-picture">
                    <img src="images/profile-pic.png " alt=""> <i class=" fas fa-caret-down"></i>
                </div>
            </div>
        </div>
        <div class="status-field-container write-post-container">
            <div class="user-profile-box">
                <div class="user-profile">
                    <img src="images/profile-pic.png" alt="">
                    <div>
                        <p> Alex Carry</p>
                        <small>August 13 1999, 09.18 pm</small>
                    </div>
                </div>
                <div>
                    <a href="#"><i class="fas fa-ellipsis-v"></i></a>
                </div>
            </div>
            <div class="status-field">
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Corporis dolores praesentium dicta
                    laborum nihil accusantium odit laboriosam, sed sit autem! <a
                            href="#">#This_Post_is_Bigger!!!!</a></p>
                <img src="images/feed-image-2.png" alt="">

            </div>
            <div class="post-reaction">
                <div class="activity-icons">
                    <div><img src="images/like-blue.png" alt="">120</div>
                    <div><img src="images/comments.png" alt="">52</div>
                    <div><img src="images/share.png" alt="">35</div>
                </div>
                <div class="post-profile-picture">
                    <img src="images/profile-pic.png " alt=""> <i class=" fas fa-caret-down"></i>
                </div>
            </div>
        </div>
        <div class="status-field-container write-post-container">
            <div class="user-profile-box">
                <div class="user-profile">
                    <img src="images/profile-pic.png" alt="">
                    <div>
                        <p> Alex Carry</p>
                        <small>August 13 1999, 09.18 pm</small>
                    </div>
                </div>
                <div>
                    <a href="#"><i class="fas fa-ellipsis-v"></i></a>
                </div>
            </div>
            <div class="status-field">
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Corporis dolores praesentium dicta
                    laborum nihil accusantium odit laboriosam, sed sit autem! <a
                            href="#">#This_Post_is_faster!!!!</a></p>
                <img src="images/feed-image-3.png" alt="">

            </div>
            <div class="post-reaction">
                <div class="activity-icons">
                    <div><img src="images/like-blue.png" alt="">120</div>
                    <div><img src="images/comments.png" alt="">52</div>
                    <div><img src="images/share.png" alt="">35</div>
                </div>
                <div class="post-profile-picture">
                    <img src="images/profile-pic.png " alt=""> <i class=" fas fa-caret-down"></i>
                </div>
            </div>
        </div>
        <div class="status-field-container write-post-container">
            <div class="user-profile-box">
                <div class="user-profile">
                    <img src="images/profile-pic.png" alt="">
                    <div>
                        <p> Alex Carry</p>
                        <small>August 13 1999, 09.18 pm</small>
                    </div>
                </div>
                <div>
                    <a href="#"><i class="fas fa-ellipsis-v"></i></a>
                </div>
            </div>
            <div class="status-field">
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Corporis dolores praesentium dicta
                    laborum nihil accusantium odit laboriosam, sed sit autem! <a
                            href="#">#This_Post_is_perfect!!!!</a></p>
                <img src="images/feed-image-4.png" alt="">

            </div>
            <div class="post-reaction">
                <div class="activity-icons">
                    <div><img src="images/like-blue.png" alt="">120</div>
                    <div><img src="images/comments.png" alt="">52</div>
                    <div><img src="images/share.png" alt="">35</div>
                </div>
                <div class="post-profile-picture">
                    <img src="images/profile-pic.png " alt=""> <i class=" fas fa-caret-down"></i>
                </div>
            </div>
        </div>
        <button type="button" class="btn-LoadMore" onclick="LoadMoreToggle()">Load More</button>
    </div>

    <!-- sidebar------------ -->
    <div class="right-sidebar">
        <div class="heading-link">
            <h4>Events</h4>
            <a href="">See All</a>
        </div>

        <div class="events">
            <div class="left-event">
                <h4>13</h4>
                <span>august</span>
            </div>
            <div class="right-event">
                <h4>Social Media</h4>
                <p><i class="fas fa-map-marker-alt"></i> wisdom em Park</p>
                <a href="#">More Info</a>
            </div>
        </div>
        <div class="events">
            <div class="left-event">
                <h4>18</h4>
                <span>January</span>
            </div>
            <div class="right-event">
                <h4>Mobile Marketing</h4>
                <p><i class="fas fa-map-marker-alt"></i> wisdom em Park</p>
                <a href="#">More Info</a>
            </div>
        </div>

        <div class="heading-link">
            <h4>Advertisement</h4>
            <a href="">Close</a>
        </div>
        <div class="advertisement">
            <img src="images/advertisement.png" class="advertisement-image" alt="">
        </div>

        <div class="heading-link">
            <h4>Conversation</h4>
            <a href="">Hide Chat</a>
        </div>

        <div class="online-list">
            <div class="online">
                <img src="images/member-1.png" alt="">
            </div>
            <p>Alison Mina</p>
        </div>

        <div class="online-list">
            <div class="online">
                <img src="images/member-2.png" alt="">
            </div>
            <p>Jackson Aston</p>
        </div>
        <div class="online-list">
            <div class="online">
                <img src="images/member-3.png" alt="">
            </div>
            <p>Samona Rose</p>
        </div>
    </div>
</div>
<footer id="footer">
    <p>&copy; Copyright 2021 - Socialbook All Rights Reserved</p>
</footer>

<script src="function.js"></script>
</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Navbar--%>
<div class="nav-bar">
    <div class="nav-bar-allFeature">
        <div class="feature">
            <div class="logoAdmin">
                <img src="https://static.xx.fbcdn.net/rsrc.php/yI/r/4aAhOWlwaXf.svg" alt="this is image-logo facebook">
            </div>
            <ul class="nav-bar-option">
                <li><a href="admin?action">Manage user</a></li>
                <li><a href="admin?action=Week">Chart</a></li>
            </ul>
        </div>
        <div class="div-logout">
            <form action="session?actionGet=logOut" method="get" class="formLogout">
                <input type="submit" class="logout" value="Log out"/>
                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                    <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                    <style>svg {
                        fill: #6a6a6a
                    }</style>
                    <path d="M304 128a80 80 0 1 0 -160 0 80 80 0 1 0 160 0zM96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM49.3 464H398.7c-8.9-63.3-63.3-112-129-112H178.3c-65.7 0-120.1 48.7-129 112zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3z"/>
                </svg>
            </form>
        </div>
    </div>
</div>


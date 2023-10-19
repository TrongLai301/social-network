<%--
  Created by IntelliJ IDEA.
  User: tuancd
  Date: 19/10/2023
  Time: 08:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mạng xã hội</title>
</head>
<body>
<header>
    <style>
        body {
            font-family: sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #222;
            color: #fff;
            padding: 20px;
        }

        h1 {
            font-size: 30px;
        }

        main {
            width: 70%;
            margin: 0 auto;
        }

        footer {
            background-color: #ccc;
            padding: 20px;
        }

        .btn {
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #4d90d9;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #4d90d9;
        }

    </style>
    <h1>Mạng xã hội</h1>
</header>
<main>
    <h2>Trang chủ</h2>
</main>
<footer>
    <form action="/session?actionGet=logOut" method="get">
        <input type="submit" class="btn btn-danger" value="Đăng xuất"/>
    </form>
</footer>
</body>
</html>


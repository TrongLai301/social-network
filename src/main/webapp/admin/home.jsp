<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
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
    <h1>List User  </h1>
    <c:if test="${requestScope['message'] != null}">
        <%
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('khong the xoa admin');");
            printWriter.println("</script>");
        %>
    </c:if>
    <div class="table">
        <table>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Permission</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach var="user" items="${sessionScope.defaultListUser}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.permission}</td>
                    <td>${user.status}</td>
                    <td><a href="home?action=block&id=${user.id}"><i class="bi bi-ban"></i></a></td>
                    <td></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</main>
<footer>
    <form action="session?actionGet=logOut" method="get">
        <input type="submit" class="btn btn-danger" value="Đăng xuất"/>
    </form>
</footer>
</body>
</html>

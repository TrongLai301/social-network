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
    <title>Admin - Quản lý người dùng</title>
</head>
<body>
<header>
    <style>
         body {
             background-color: #f8f9fa;
         }

        .container {
            margin-top: 50px;
        }

        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .card-header {
            background-color: #007bff;
            color: #fff;
            border-radius: 10px 10px 0 0;
        }

        .card-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 0;
        }

        .card-body {
            padding: 20px;
        }

        .table {
            border-radius: 10px;
        }

        .table th,
        .table td {
            vertical-align: middle;
        }

    </style>
    <h1 style="text-align: center">User account management</h1>
</header>
<main>
    <c:if test="${requestScope['message'] != null}">
        <%
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('khong the xoa admin');");
            printWriter.println("</script>");
        %>
    </c:if>
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2 class="card-title">Quản lý người dùng</h2>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên đăng nhập</th>
                        <th>Mật khẩu</th>
                        <th>Quyền</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${requestScope.defaultListUser}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.password}</td>
                            <td>${user.permission}</td>
                            <td>${user.status}</td>
                            <td><a href="home?action=block&id=${user.id}"><i class="bi bi-ban"></i></a></td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
<footer >
    <div style="float: right;padding-right: 100px;padding-top: 30px">
        <form action="session?actionGet=logOut" method="get">
            <input type="submit" class="btn btn-danger" value="Đăng xuất"/>
        </form>
    </div>

</footer>
</body>
</html>

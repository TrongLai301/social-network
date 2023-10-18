<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="service.UserDAOImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1>List User</h1>
    <div class="container">
        <table>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Birth</th>
                <th>Avatar</th>
                <th>Name</th>
                <th>Address</th>
                <th>Hobby</th>
                <th>Permission</th>
            </tr>
            <c:forEach var="user" items="${sessionScope.defaultListUser}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.id}</td>
                    <td>${user.id}</td>
                    <td>${user.id}</td>
                    <td>${user.id}</td>
                    <td>${user.id}</td>
                    <td>${user.id}</td>
                    <td>${user.id}</td>
                    <td>${user.id}</td>
                    <td>${user.id}</td>
                    <td>${user.id}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
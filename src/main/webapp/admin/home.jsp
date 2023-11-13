<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 08/11/2023
  Time: 10:16 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shortcut icon" type="image/png" href="https://static.xx.fbcdn.net/rsrc.php/yb/r/hLRJ1GG_y0J.ico">
    <link rel="stylesheet" href="../admin/styleAdmin.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <script src="https://kit.fontawesome.com/ef7e2b893b.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../admin/userList.css">

</head>
<body>

<jsp:include page="navbar.jsp"/>
<main>
    <div class="divFeature container">
    </div>
    <c:if test="${requestScope['messageRemove'] != null}">
        <%
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('tài khoản hoạt động trở lại ');");
            printWriter.println("</script>");
        %>
    </c:if>
    <c:if test="${requestScope['messageBlock'] != null}">
        <%
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('tài khoản chặn thành công ');");
            printWriter.println("</script>");
        %>
    </c:if>
    <c:if test="${requestScope['message'] != null}">
        <%
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('tài khoản chặn là admin không thể chặn ');");
            printWriter.println("</script>");
        %>
    </c:if>
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2 class="card-title">user management</h2>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>user name</th>
                        <th>Password</th>
                        <th>Permission</th>
                        <th>Status</th>
                        <th>Action</th>
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
                            <td><a href="admin?action=block&id=${user.id}"><i class="bi bi-ban"></i></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
</body>
</html>

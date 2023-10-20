<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: taminhtri
  Date: 18/10/2023
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Password</title>
    <link rel="stylesheet" href="../user/editPassword/style.css">
</head>
<body>
<div class="container">
    <h1>Edit Password</h1>
    <form action="/user?actionPost=editPassword" method="post">
        <input type="hidden" name="idAccount" value="<c:out value="${idAccount}"/>">
        <label>Current Password</label>
        <input type="password" name="password" required>
        <label>New Password</label>
        <input type="password" name="newPassword" required>
        <label>Confirm New Password</label>
        <input type="password" name="confirmPassword" required>
        <button type="submit">Submit</button>
        <p>
            <c:if test="${requestScope['message'] != null}">
                <span class="message">
                    ${requestScope["message"]}
                </span>
            </c:if>
        </p>
    </form>
</div>
</body>
</html>

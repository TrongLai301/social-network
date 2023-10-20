<%--@elvariable id="userNeedToEdit" type="model.User"--%>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20/10/2023
  Time: 9:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Profile</h1>
<table border="1px">
    <form action="/user" method="post">
        <input type="hidden" name="actionPost" value="updateUser">
        <input type="hidden" name="id" value="${userNeedToEdit.id}">
        <input type="text" name="username" value="${userNeedToEdit.username}" disabled>
        <input type="text" name="avatar" value="${userNeedToEdit.avatar}">
        <input type="text" name="name" value="${userNeedToEdit.name}">
        <input type="text" name="email" value="${userNeedToEdit.email}">
        <input type="text" name="phone" value="${userNeedToEdit.phone}">
        <input type="text" name="birthdate" value="${userNeedToEdit.birthdate}">
        <input type="text" name="address" value="${userNeedToEdit.birthdate}">
        <input type="text" name="hobby" value="${userNeedToEdit.hobby}">
        <input type="submit" value="Save!">
    </form>
</table>

</body>
</html>

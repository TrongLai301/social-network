<%--
  Created by IntelliJ IDEA.
  User: tuancd
  Date: 26/10/2023
  Time: 08:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng status</title>
    <style>
        /* CSS cho khung đăng status */
        .status-container {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
        }

        .status-container textarea {
            width: 100%;
            height: 100px;
            resize: vertical;
            margin-bottom: 10px;
        }

        .status-container button {
            margin-right: 5px;
        }

        .status-info {
            margin-top: 10px;
            border: 1px solid #ccc;
            padding: 10px;
        }

        .status-info img {
            width: 50px;
            height: 50px;
            float: left;
            margin-right: 10px;
        }

        .status-info .status-text {
            margin-bottom: 10px;
        }

        .status-info .status-image {
            max-width: 100%;
            margin-bottom: 10px;
        }

        .status-info .status-likes {
            margin-bottom: 10px;
        }

        .status-info .status-comments {
            margin-bottom: 10px;
        }

        .status-info button {
            margin-right: 5px;
        }
    </style>
</head>
<body>
<div class="status-container">
<form action="/user?actionPost=uploadNewStatus" method="post" enctype='multipart/form-data'>
    <select name="permission">
        <option value="1">Public</option>
        <option value="2">Private</option>
        <option value="3">Friends</option>
    </select>
    <textarea name="description" placeholder="Nhập nội dung"></textarea>
    <input name="media" type="file" accept="image/*">
    <input type="submit" value="Upload">
</form>
</div>
<div id="status-list"></div>
</body>
</html>

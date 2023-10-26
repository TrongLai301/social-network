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
    <textarea id="status-content" placeholder="Nhập nội dung"></textarea>
    <input type="file" id="status-image" accept="image/*">
    <button id="status-submit" onclick="submitStatus()">Đăng</button>
</div>
<div id="status-list"></div>

<script>
    function submitStatus() {
        var content = document.getElementById('status-content').value;
        var image = document.getElementById('status-image').files[0];

        // Gửi dữ liệu đăng status lên server bằng Ajax hoặc fetch

        // Sau khi đăng thành công, cập nhật giao diện
        var statusInfo = document.createElement('div');
        statusInfo.classList.add('status-info');

        // Thêm các phần tử vào statusInfo theo yêu cầu của bạn

        document.getElementById('status-list').prepend(statusInfo);
    }
</script>
</body>
</html>

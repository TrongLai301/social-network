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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <script src="https://kit.fontawesome.com/ef7e2b893b.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <link rel="stylesheet" href="../admin/userList.css">
    <%--    <script src="chart.js"></script>--%>
</head>
<body>
<main>
    <jsp:include page="navbar.jsp"/>
    <div class="mainShow">
        <jsp:include page="navSelect.jsp"/>
        <div class="chart">
            <canvas id="lineChart"></canvas>
            <span>Statistical chart by week</span>
        </div>
    </div>
    <script>
        var quantities = [];
        var quantitiesLogin = [];
    </script>
    <c:forEach var="quantity" items="${listWeekAccess}">
        <script>
            quantities.push(${quantity});
        </script>
    </c:forEach>
    <c:forEach var="quantityLogin" items="${listWeekLogin}">
        <script>
            quantitiesLogin.push(${quantityLogin});
        </script>
    </c:forEach>
    <script>
        var ctxL = document.getElementById("lineChart").getContext('2d');
        var myLineChart = new Chart(ctxL, {
            type: 'line',
            data: {
                labels: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
                datasets: [{
                    label: "Login traffic",
                    data: quantities,
                    backgroundColor: [
                        'rgba(105, 0, 132, .2)',
                    ],
                    borderColor: [
                        'rgba(200, 99, 132, .7)',
                    ],
                    borderWidth: 2
                },
                    {
                        label: "Registration traffic",
                        data: quantitiesLogin,
                        backgroundColor: [
                            'rgba(0, 137, 132, .2)',
                        ],
                        borderColor: [
                            'rgba(0, 10, 130, .7)',
                        ],
                        borderWidth: 2
                    }
                ]
            },
            options: {
                responsive: true
            }
        });

    </script>
</main>
</body>
</html>

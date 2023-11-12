<%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 11/11/2023
  Time: 10:26 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav>
    <div class="titleChartNav">
        <span>Statistical chart</span>
    </div>
    <label>
        <select class="optionShowChart" onchange="changeChart(this)">
            <option id="selectElementS" disabled selected style="">Select chart</option>
            <option value="Week" data-display="Week">Week</option>
            <option value="Month" data-display="Month">Month</option>
            <option value="Year" data-display="Year">Year</option>
        </select>
        <script>
            function changeChart(selectElement) {
                var selectElementS = selectElement.options[selectElement.selectedIndex].getAttribute('data-display');
                var url = "/admin?action=" + selectElementS;
                window.location.href = url;
                if (selectElementS.id === "selectedOption") {
                    selectElementS.disabled = true; // Ẩn tùy chọn "Selected"
                } else {
                    var selectedOptionElement = selectElement.querySelector('#selectedOption');
                    selectedOptionElement.disabled = false; // Hiển thị tùy chọn "Selected"
                }
            }
        </script>
    </label>
</nav>
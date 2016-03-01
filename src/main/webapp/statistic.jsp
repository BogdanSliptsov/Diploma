<%@ page import="com.diploma.service.DeseaseService" %>
<%@ page import="com.diploma.entity.DeseaseEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add statistic</title>
    <jsp:include page="common/styles.jsp"/>
</head>
<body>
<jsp:include page="common/navigation.jsp"/>
<jsp:useBean id="diseaseBean" class="com.diploma.frontend.DiseaseBean"/>

<div class="container">

    <h3>Add new statistic:</h3>

    <div class="btn-group" role="group" aria-label="by">
        <button onclick="showByYear();" type="button" class="btn btn-default">By years</button>
    </div>
    <hr/>
    <div id="year-div" style="display: none;">
        <h3>Add new statistic by year</h3>
        <form id="year-form" onsubmit="return false;">
            <div class="form-group">
                <label for="year">Year:</label>
                <input type="text" class="form-control" id="year" name="year" placeholder="Enter year">

                <br />

                <label for="patients_year">Patients:</label>
                <input type="text" class="form-control" id="patients_year" name="patients_year" placeholder="Enter number of patients">

                <br />

                <label for="disease_year">Disease:</label>
                <select name="disease_year" id="disease_year">
                    <c:forEach var="item" items="${diseaseBean.all}">
                        <option value="${item.name}">${item.name}</option>
                    </c:forEach>
                </select>

                <%--<label for="patients_year">Patients:</label>--%>
                <%--<input type="text" class="form-control" id="disease_year" name="patients_year" placeholder="Enter number of patients">--%>


            </div>
            <button type="button" class="btn btn-default" onclick="saveByYear();">Save</button>
        </form>
    </div>

    <div id="month-form" style="display: none;">
        <h3>Add new statistic by month</h3>
        <p>In version 2.0</p>
    </div>


    <hr/>
</div>


<div class="container">

</div>

<jsp:include page="common/js.jsp"/>
<script type="application/javascript">


    $("#year-form").validate(
            {
                rules: {
                    year: {
                        required: true,
                        range: [1, 2016]
                    },
                    patients_year: {
                        required: true,
                        range: [0, 99999999]
                    }
                }
            });


    function showByYear() {
        $("#year-div").css('display', 'block');
        $("#month-form").css('display', 'none');
    }

    function showByMonth() {
        $("#year-div").css('display', 'none');
        $("#month-form").css('display', 'block');
    }


    function saveByYear() {
        var yearInp = $("#year").val();
        var patientsInp = $("#patients_year").val();
        var diseaseYearInp = $("#disease_year").val();

        jQuery.ajax({
            url: "/rest/patient/create",
            type: "POST",
            data: JSON.stringify({diseaseName: diseaseYearInp, numberOfPatients: patientsInp, yeraNumber: yearInp}),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        });

        alert("Successfully added!");
        location.reload();
    }
</script>
</body>
</html>

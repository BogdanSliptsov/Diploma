<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:p="http://primefaces.org/ui">

<%@ page import="com.diploma.service.DeseaseService" %>
<%@ page import="com.diploma.entity.DeseaseEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forecast</title>
    <jsp:include page="common/styles.jsp"/>
</head>
<body>
<jsp:include page="common/navigation.jsp"/>
<jsp:useBean id="diseaseBean" class="com.diploma.frontend.DiseaseBean"/>

<div class="container">

    <h3>Select disease</h3>
    <form method="get" action="/forecast">
        <select name="disease" id="disease_year">
            <c:forEach var="item" items="${diseaseBean.all}">
                <option value="${item.name}">${item.name}</option>
            </c:forEach>
        </select>
        <hr/>
        <input id="user_year" style="width: 100px" type="text" class="form-control" placeholder="Year">
        <hr>
        <input id="remove_year" style="width: 140px" type="text" class="form-control" placeholder="Year to delete">
        <hr/>
        <button type="button" class="btn btn-default" onclick="getForCast(); return false;">Show info</button>
        <button type="button" class="btn btn-default" onclick="restoreData(); return false;">Resore data</button>
        <button type="button" class="btn btn-default" onclick="forecast(); return false;">Forecast</button>
        <button type="button" class="btn btn-default" onclick="removeDisease(); return false;">Remove disease</button>
        <button type="button" class="btn btn-default" onclick="removePatientsRecord(); return false;">Remove record</button>
    </form>

    <hr/>

    <h3>Info:</h3>
    <table class="table table-bordered" id="table_data">

    </table>

    <div id="fx_values_graph" class="demo-placeholder"></div>


</div>


<jsp:include page="common/js.jsp"/>
<script type="application/javascript">

    function getForCast() {
        var diseaseNameInp = $("#disease_year").val();
        jQuery.ajax({
            url: "/rest/patient/get",
            type: "POST",
            data: JSON.stringify({diseaseName: diseaseNameInp}),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function (data) {
            console.log(data);
            var fx_values = [];
            for (var i = 0; i < data.ResultList.length; i++) {
                fx_values.push([data.ResultList[i].year, data.ResultList[i].numberOfPatients]);
            }
            set_fx_values_table(fx_values, 'table_data')
            $.plot("#fx_values_graph", [
                {label: "Restoration", data: fx_values, points: {show: true}, lines: {show: true}},

            ]);
        });

        function set_fx_values_table(fx_values, html_id) {
            var html = '<thead><tr><th>Years</th><th>Pations</th></tr></thead><tbody>';
            for (var i = 0; i < fx_values.length; i++) {
                html += '<tr><td>' + fx_values[i][0] + '</td><td>' + fx_values[i][1] + '</td></tr>';
            }
            html += '</tbody>';
            document.getElementById(html_id).innerHTML = html;
        }

        alert("Disease " + diseaseNameInp + " successfully added!");
//        location.reload();
    }

    function restoreData() {
        var diseaseNameInp = $("#disease_year").val();
        jQuery.ajax({
            url: "/rest/patient/restore",
            type: "POST",
            data: JSON.stringify({diseaseName: diseaseNameInp}),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function (data) {
            console.log(data);
            var fx_values = [];
            for (var i = 0; i < data.ResultList.length; i++) {
                fx_values.push([data.ResultList[i].year, data.ResultList[i].numberOfPatients]);
            }
            set_fx_values_table(fx_values, 'table_data')
            $.plot("#fx_values_graph", [
                {label: "Restoration", data: fx_values, points: {show: true}, lines: {show: true}},

            ]);
        });

        function set_fx_values_table(fx_values, html_id) {
            var html = '<thead><tr><th>Years</th><th>Pations</th></tr></thead><tbody>';
            for (var i = 0; i < fx_values.length; i++) {
                html += '<tr><td>' + fx_values[i][0] + '</td><td>' + fx_values[i][1] + '</td></tr>';
            }
            html += '</tbody>';
            document.getElementById(html_id).innerHTML = html;
        }

        alert("Disease " + diseaseNameInp + " successfully added!");
//        location.reload();
    }

    function forecast() {
        var diseaseNameInp = $("#disease_year").val();
        var userYear = $("#user_year").val();
        console.log(userYear);
        jQuery.ajax({
            url: "/rest/patient/forecast/smoothing",
            type: "POST",
            data: JSON.stringify({diseaseName: diseaseNameInp, years: userYear}),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function (data) {
            console.log(data);
            var fx_values = [];
            for (var i = 0; i < data.ResultList.length; i++) {
                fx_values.push([data.ResultList[i].year, data.ResultList[i].numberOfPatients]);
            }
            set_fx_values_table(fx_values, 'table_data')
            $.plot("#fx_values_graph", [
                {label: "Restoration", data: fx_values, points: {show: true}, lines: {show: true}},

            ]);
        });

        function set_fx_values_table(fx_values, html_id) {
            var html = '<thead><tr><th>Years</th><th>Pations</th></tr></thead><tbody>';
            for (var i = 0; i < fx_values.length; i++) {
                html += '<tr><td>' + fx_values[i][0] + '</td><td>' + fx_values[i][1] + '</td></tr>';
            }
            html += '</tbody>';
            document.getElementById(html_id).innerHTML = html;
        }

        alert("Disease " + diseaseNameInp + " successfully added!");
//        location.reload();
    }

    function removeDisease() {
        var diseaseNameInp = $("#disease_year").val();
        jQuery.ajax({
            url: "/rest/patient/remove/disease",
            type: "POST",
            data: JSON.stringify({diseaseName: diseaseNameInp}),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function (data) {
            console.log(data);
            var fx_values = [];
            for (var i = 0; i < data.ResultList.length; i++) {
                fx_values.push([data.ResultList[i].year, data.ResultList[i].numberOfPatients]);
            }
            set_fx_values_table(fx_values, 'table_data')
            $.plot("#fx_values_graph", [
                {label: "Restoration", data: fx_values, points: {show: true}, lines: {show: true}},

            ]);
        });

        function set_fx_values_table(fx_values, html_id) {
            var html = '<thead><tr><th>Years</th><th>Pations</th></tr></thead><tbody>';
            for (var i = 0; i < fx_values.length; i++) {
                html += '<tr><td>' + fx_values[i][0] + '</td><td>' + fx_values[i][1] + '</td></tr>';
            }
            html += '</tbody>';
            document.getElementById(html_id).innerHTML = html;
        }

        alert("Disease " + diseaseNameInp + " successfully added!");
//        location.reload();
    }

    function removePatientsRecord() {
        var diseaseNameInp = $("#disease_year").val();
        var yearNumberInp = $("#remove_year").val();
        console.log(yearNumberInp);

        jQuery.ajax({
            url: "/rest/patient/remove/patient",
            type: "POST",
            data: JSON.stringify({diseaseName: diseaseNameInp, yearNumber: yearNumberInp}),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function (data) {
            console.log(data);
            var fx_values = [];
            for (var i = 0; i < data.ResultList.length; i++) {
                fx_values.push([data.ResultList[i].year, data.ResultList[i].numberOfPatients]);
            }
            set_fx_values_table(fx_values, 'table_data')
            $.plot("#fx_values_graph", [
                {label: "Restoration", data: fx_values, points: {show: true}, lines: {show: true}},

            ]);
        });

        function set_fx_values_table(fx_values, html_id) {
            var html = '<thead><tr><th>Years</th><th>Pations</th></tr></thead><tbody>';
            for (var i = 0; i < fx_values.length; i++) {
                html += '<tr><td>' + fx_values[i][0] + '</td><td>' + fx_values[i][1] + '</td></tr>';
            }
            html += '</tbody>';
            document.getElementById(html_id).innerHTML = html;
        }

        alert("Disease " + diseaseNameInp + " successfully added!");
//        location.reload();
    }
</script>
</body>
</html>

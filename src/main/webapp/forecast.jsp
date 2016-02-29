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
        <button type="submit">Show info</button>
    </form>

    <div onclick="getForCast(); return false;">holla</div>

    <hr />

    <h3>Already existing diseases:</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Year</th>
            <th>Patients</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${restoredValues}" var="restoredValues" varStatus="status">
            <tr>
                <td>${restoredValues.key}</td>
                <td>${restoredValues.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</div>

<div class="container">

</div>

<jsp:include page="common/js.jsp"/>
<script type="application/javascript">

    function getForCast() {
        var diseaseNameInp = $("#disease_year").val();
        jQuery.ajax ({
            url: "/rest/patient/get",
            type: "POST",
            data: JSON.stringify({ diseaseName: diseaseNameInp }),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function( data ) {
            console.log(data);
            var fx_values = [];
            for(var  i = 0; i < data.size; i++){
                fx_values.push()
            }
        });


        alert("Disease " + diseaseNameInp + " successfully added!");
//        location.reload();
    }
</script>
</body>
</html>

<%@ page import="com.diploma.service.DeseaseService" %>
<%@ page import="com.diploma.entity.DeseaseEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add diseases</title>
    <jsp:include page="common/styles.jsp"/>
</head>
<body>
<jsp:include page="common/navigation.jsp"/>
<jsp:useBean id="diseaseBean" class="com.diploma.frontend.DiseaseBean"/>

<div class="container">

    <h3>Add new disease:</h3>
    <form>
        <div class="form-group">
            <label for="diseaseNameId">Disease name</label>
            <input type="text" class="form-control" id="diseaseNameId" placeholder="Enter disease name here">
        </div>
        <button type="button" class="btn btn-default" onclick="saveDisease();">Save</button>
    </form>

    <hr />
    <h3>Already existing diseases:</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>№ In order</th>
            <th>DB ID</th>
            <th>Disease</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="count" value="1" scope="page" />
        <c:forEach items="${diseaseBean.all}" var="disease" varStatus="status">
            <tr>
                <td>${count}</td>
                <td>${disease.id}</td>
                <td>${disease.name}</td>
            </tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
        </c:forEach>
        </tbody>
    </table>
</div>


<div class="container">

</div>

<jsp:include page="common/js.jsp"/>
<script type="application/javascript">

    function saveDisease() {
        var diseaseNameInp = $("#diseaseNameId").val();
        jQuery.ajax ({
            url: "/rest/disease/create",
            type: "POST",
            data: JSON.stringify({ diseaseName: diseaseNameInp }),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function( data ) {

        });

        alert("Disease " + diseaseNameInp + " successfully added!");
        location.reload();
    }
</script>
</body>
</html>

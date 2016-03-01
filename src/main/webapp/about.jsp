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
    <title>About product</title>
    <jsp:include page="common/styles.jsp"/>
</head>
<body>
<jsp:include page="common/navigation.jsp"/>

<body>
<div align="center"></div>
<div class="container jumbotron contact-jumbotron" align="center">
        <table class="table-bordered jumbotron newsContainer">
            <tr>
                <th colspan="2"><h4 align="center">This service is used to get forecast for different diseases.</h4>
                </th>
            </tr>
            <tr>
                <td><b>Short summary:</b></td>
            </tr>
            <tr>
                <td>
                    1. Add disease if it doesn't exist. <br>
                    2. Add data for your disease or modify actual data. <br>
                    3. Restore absent values.<br>
                    4. Build forecast.<br>
                </td>
            </tr>
            <tr>
                <td>
                    Forecast data contains of forecast table and forecast chart.
                </td>
            </tr>
            <tr>
                <td>
                    Service might be used by doctors of different spicialities.
                </td>
            </tr>
        </table>

</div>
</body>
</html>

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
    <title>About developer</title>
    <jsp:include page="common/styles.jsp"/>
</head>
<body>
<jsp:include page="common/navigation.jsp"/>

<body>
<div align="center"></div>
<div class="container jumbotron contact-jumbotron" align="center">
    <table class="table-bordered">
        <tr>
            <th colspan="2"><h4 align="center">Personal information</h4></th>
        </tr>
        <tr>
            <td rowspan="2"><img class="img-thumbnail"
                                 src="common/mX1xPm8JjCk.jpg"
                                 style="height: 150px">

            </td><td>Name: <i>Bogdan</i></td>
        </tr>
        <tr>
            <td>Surname: <i>Sliptsov</i></td>
        </tr>
    </table>

</div>

</body>
</html>

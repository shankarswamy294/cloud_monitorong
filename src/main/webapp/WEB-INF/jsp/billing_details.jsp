<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Billing/Cost</title>
</head>
<body>

<table class="table">
<h1>Billing/Cost</h1>
<tr>
<th>Resource_name</th>
<th>quantity</th>
<th>cost</th>
<th>meterId</th>
<th>meterCategory</th>
<th>startTime</th>
<th>location</th>
<th>endTime</th>

</tr>
<c:forEach items="${billing_result}" var="item">
<tr>
	<c:forEach items="${item}" var="item01">
		<td><c:out value="${item01.value}"></c:out></td>
	</c:forEach>
</tr>
</c:forEach>
</table>

</body>
</html>
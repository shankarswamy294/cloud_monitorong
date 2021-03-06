<%@ page language="java" contentType="text/html; charset=UTF-8"  
 pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List</title>
</head>
<body>
<div class="container">

<h1>LIST OF RESOURCES</h1>
<div class="row row-centered pos">
<div class="col-md-8">
<table class="table">
<c:if test="${!empty currect_list}">
 <c:forEach items="${currect_list}" var="item">
 <tr>
 	<td>
 	<a href="list?res_name=${item.key}"><c:out value="${item.key}" ></c:out></a></td>
  	<td><c:out value="${item.value}"></c:out></td>
</tr>
</c:forEach>
</c:if>
</table>
</div>
<div class="col-md-4"></div>
</div>
</div>

</body>
</html>
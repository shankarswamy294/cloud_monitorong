<%@ page language="java" contentType="text/html; charset=UTF-8"  
 pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

</head>
<body>
<div class="container">
<div><h1>ResourseDetails</h1></div>
  <c:if test="${!empty model.getval}">
  <table class="table table">
  <thead class="class="thead-dark"><th colspan="2"><h3>${model.keyname}</h3></th></thead>
  <tbody>
  <c:forEach items="${model.getval}" var="item">
  <tr>
  		<th><c:out value="${item.key}" ></c:out></th>
  		<td><c:out value="${item.value}"></c:out></td>
  </tr>
  </c:forEach>
  </tbody>
  </table>
</c:if> 

</div>
</body>
</html>
<%@page import="net.minidev.json.parser.JSONParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  
 pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Resource Details</title>  
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  
</head>  
<body>  
<div class="container">
  <div><h1>ResourseDetails</h1></div>
  <c:if test="${!empty model}">
  <table class="table">
  <thead>
  <th>Type</th>
  <th>Name</th>
  <th>Details</th>
  </thead>
  <tbody>
  <c:forEach items="${model}" var="ele" varStatus="loop">
  <tr>
  		<td><c:out value="${ele.type}"/></td>
  		<td><c:out value="${ele.name}"/></td>
  		<td><c:forEach items="${ele.details}" var="d_item">
  			<strong><c:out value="${d_item.key}"/></strong>
  			<c:out value="${d_item.value}"/>
  			<br/>
  		</c:forEach></td>

  </tr>
  </c:forEach>
</tbody>
</table>
  </c:if> 
  </div>
  </body>  
</html>  
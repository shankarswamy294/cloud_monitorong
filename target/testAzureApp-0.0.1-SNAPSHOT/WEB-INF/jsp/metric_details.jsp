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
  
  <style type="text/css">
  		#error{
  			color:blue;
  			margin-top:200px;
  			margin-left:200px;
  		}
  </style>
  
</head>  
<body>  
<div class="container">
<c:if test="${empty metric_info}">
  <div class="container" id="error">
  	<h1>Sorry No Data Available..... :( <h1></h1>
  </div>
   </c:if>
   
   <c:if test="${!empty metric_info}">
  <div><h1>MetricDetails</h1></div>
  <c:forEach items="${metric_info}" var="ele" varStatus="loop">
  
 		<c:forEach items="${ele}" var="ele01" varStatus="loop">
 			<c:forEach items="${ele01}" var="ele02" varStatus="loop">
 			<c:forEach items="${ele02}" var="ele03" varStatus="loop">
  				<c:out value="${ ele03 }"></c:out><br/><br/>	
  			</c:forEach>
  			</c:forEach>
  		</c:forEach>
  		<br/>
  </c:forEach>
 </c:if>
  
  </div>
  </body>  
</html>  
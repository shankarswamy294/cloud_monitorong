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

<c:if test="${empty metric_in}">
  <div class="container" id="error">
  	<h1>Sorry No Data Available..... :( <h1></h1>
  </div>
   </c:if>
   
   <c:if test="${!empty metric_in}">
  <div><h1>MetricDetails</h1></div>
  
 <table class="table">
 <thead>
 	<tr>
 		<th>Time Stamp</th>
 		<th>Aggregate</th>	
 	</tr>
 </thead>
  </table>
  <c:forEach items="${metric_in}" var="ele" varStatus="loop">
 		<c:forEach items="${ele}" var="ele01" varStatus="loop">
  					<c:out value="${ ele01 }"></c:out> 
  					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
  					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
  					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
  			</c:forEach><br/>
  		<br/>
  </c:forEach>
 
 
 </c:if>
  </div>
  </body>  
</html>  
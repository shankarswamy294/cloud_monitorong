<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
   var myVar;
 $( "#load" ).click(function() {
  	$("#loader").show();
});
});
</script>
<title>Resource</title>
<style>
	.container{
		color:white;
		margin:200px;
		margin-left:200px;
	}
	button{
		margin:5px;
	}
	h1{
	margin-left:100px;
	}
	body{
	background-image: url('https://www.xmple.com/wallpaper/single-one-colour-solid-color-plain-azure-1920x1080-c-236d8c-f-24.svg');
	}

</style>
</head>
<body>
<div><button style="float:right; margin-right:50px; margin-top:-100px;" class="btn btn-danger" onclick="location.href='/testAzureApp';">Logout</button></div>
<div class="container">
<h1>AZURE CLOUD</h1>
	<button class="btn btn-success" id="load" onclick="location.href='push';"> Discover Resources</button>
	<button class="btn btn-info" onclick="location.href='mainList';">Show Resources</button>
	<button class="btn btn-primary" onclick="location.href='monitor_list';">Monitor Resources</button>
	<button class="btn btn-danger" id="load" onclick="location.href='billing_form';">Billing/Cost</button>
	<div id="loader" style="display:none;">
	<img alt="" style="background-repeat: no-repeat; height:70px; margin-left:100px; margin-top:20px;" src="${pageContext.request.contextPath}/resources/gif/loader01.gif" /><br/>
	<p style=" margin-left:90px;">loading . . . .</p>
	</div>
</div>

</body>
</html>
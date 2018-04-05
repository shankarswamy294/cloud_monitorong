<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>   
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
    
    
    <style>
    	body{
    		background-image: url("https://newsignature.com/wp-content/uploads/2017/12/azure1-1024x683.jpg");
    	}
    	#time_stamp{
		margin-left:30px;
		margin-bottom:30px;
		}
		#mt{
		margin-bottom:30px;
		}
		#login_div{
			margin:22%;
			margin-top:20%;
			border: 2px solid black;
			border-radius:50px;
		}
		#header{
			padding-left:250px;
		}
}
    </style>
    <script>

$(document).ready(function(){
   var myVar;
 $( "#load" ).click(function() {
  	$("#loader").show();
});
});
</script>
    </head>
    <body>
    <div id="login_div">
  	         <form:form method="POST" action="login" modelAttribute="User">

<div class="container">
<div id="login"><br/><br/>
<h2 id="header"><strong>LOGIN</strong></h2></div>
    <div class='col-md-5'>
    <form:label path="user_name">user_name : </form:label>
        <div class="form-group">
            <div class='input-group user_name'>
                 <form:input style='background: transparent; border:2px solid black; border-radius:10px;' path="user_name" type='text' class="form-control" value="user_name"/>
            </div>
        </div>
    </div>
    <div class='col-md-5'>
    <form:label path="password">password : </form:label>
        <div class="form-group">
            <div class='input-group password'>
                <form:input style='background: transparent; border:2px solid black; border-radius:10px;' path="password" type='text' class="form-control" value="password" />
            </div>
        </div>
    </div>
</div>
                


<div class="container">
    <div class='col-md-3'></div>
    <div class='col-md-5'>
<button type="submit" id="load" class="btn btn-success">Submit</button>
<div id="loader" style="display:none;">
	<img alt="" style="background-repeat: no-repeat; height:120px; margin-left:100px; margin-top:20px;" src="${pageContext.request.contextPath}/resources/gif/loader01.gif" /><br/>
	<p style=" margin-left:90px;">Will take several minutes to load . . . .</p>
	</div>
</div>
</div>

</form:form>
</div>

    </body>
</html>
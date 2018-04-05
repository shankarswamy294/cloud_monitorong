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
    	background-image: url("http://st.gde-fon.com/wallpapers_original/176712_abstrakcii_sinij_polosy_5000x3500_www.Gde-Fon.com.jpg");
    }
    	#time_stamp{
		margin-left:50px;
		margin-bottom:30px;
		}
		#mt{
		margin-bottom:30px;
		}
		
		#signup_div{
			margin:18%;
			margin-top:10%;
			border:2px solid black;
			background: transparent;
			border-radius:50px;
			
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
    
   	<div id="signup_div"> 
 <form:form method="POST" action="signup" modelAttribute="User">

<div class="container">
<div id="signup"><strong><br/><br/>
<h2 style="margin-left:280px;"><strong>Signup</strong></h2></div>
    <div class='col-md-5'>
    <form:label path="first_name">first_name : </form:label>
        <div class="form-group">
            <div class='input-group first_name'>
                 <form:input style='background: transparent; border: 2px solid black; border-radius:10px;' path="first_name" type='text' class="form-control" />
            </div>
        </div>
    </div>
    <div class='col-md-5'>
    <form:label path="last_name">last_name : </form:label>
        <div class="form-group">
            <div class='input-group last_name'>
                <form:input style='background: transparent; border: 2px solid black; border-radius:10px;' path="last_name" type='text' class="form-control" />
            </div>
        </div>
    </div>
                
<div class='col-md-5'>
    <form:label path="user_name">user_name : </form:label>
        <div class="form-group">
            <div class='input-group user_name'>
                 <form:input style='background: transparent; border: 2px solid black; border-radius:10px;' path="user_name" type='text' class="form-control" />
            </div>
        </div>
    </div>
    <div class='col-md-5'>
    <form:label path="email_id">email_id : </form:label>
        <div class="form-group">
            <div class='input-group email_id'>
                <form:input style='background: transparent; border: 2px solid black; border-radius:10px;' path="email_id" type='text' class="form-control" />
            </div>
        </div>
    </div>
    <div class='col-md-5'>
    <form:label path="password">password : </form:label>
        <div class="form-group">
            <div class='input-group password'>
                <form:input style='background: transparent; border: 2px solid black; border-radius:10px;' path="password" type='text' class="form-control" />
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class='col-md-3'></div>
    <div class='col-md-5'>
<button style='margin-left:50px;'type="submit" id="load" class="btn btn-success">Submit</button>
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
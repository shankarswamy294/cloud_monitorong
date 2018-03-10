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
    	#time_stamp{
		margin-left:50px;
		margin-bottom:30px;
		}
		#mt{
		margin-bottom:30px;
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
  	         <form:form method="POST" action="monitor?sub_id=${model.sub_id}" modelAttribute="monitoring_form_details">

<div class="container">
<div id="time_stamp"><strong><br/><br/>
<h2>Select TimeStamp</strong></h2></div>
    <div class='col-md-5'>
    <form:label path="startDateTime">Start Date and Time : </form:label>
        <div class="form-group">
            <div class='input-group date' id='datetimepicker6'>
            	
                 <form:input path="startDateTime" type='text' class="form-control" value="start time"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
    <div class='col-md-5'>
    <form:label path="endDateTime">End Date and Time : </form:label>
        <div class="form-group">
            <div class='input-group date' id='datetimepicker7'>
            	
                <form:input path="endDateTime" type='text' class="form-control" value="end time" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
</div>
                
<div class="container">
    <div class='col-md-5' id="mt">
    <form:label path="metricType">Metric Type : </form:label><br/>
    <form:select path="metricType" class="form-control">
    <c:forEach items="${model.name_list}" var="ele">
    <option><c:out value="${ele}"></c:out></option>
    </c:forEach>
  </form:select>
</div>
<div class='col-md-5' id="mt">
    <form:label path="AggregationType">AggregationType: </form:label><br/>
    <form:select path="AggregationType" class="form-control">
    <option><c:out value="Average"></c:out></option>
    <option><c:out value="Count"></c:out></option>
    <option><c:out value="Maximum"></c:out></option>
    <option><c:out value="Minimum"></c:out></option>
    <option><c:out value="None"></c:out></option>
    <option><c:out value="Total"></c:out></option>
  </form:select>
</div>
</div>


<div class="container">
    <div class='col-md-5' id="mt">
    <form:label path="interval">Interval : </form:label><br/>
    <form:select path="interval" class="form-control">
    <option><c:out value="PT1M"></c:out></option>
    <option><c:out value="PT5M"></c:out></option>
    <option><c:out value="PT1H"></c:out></option>
  </form:select>
</div>
</div>
<div class="container">
    <div class='col-md-3'></div>
    <div class='col-md-5'>
<button type="submit" id="load" class="btn btn-success">Success</button>

<div id="loader" style="display:none;">
	<img alt="" style="background-repeat: no-repeat; height:120px; margin-left:100px; margin-top:20px;" src="${pageContext.request.contextPath}/resources/gif/loader01.gif" /><br/>
	<p style=" margin-left:90px;">loading . . . .</p>
	</div>
	
</div>
</div>

</form:form>
        <script type="text/javascript">
    $(function () {
        $('#datetimepicker6').datetimepicker();
        $('#datetimepicker7').datetimepicker({
            useCurrent: false //Important! See issue #1075
        });
        $("#datetimepicker6").on("dp.change", function (e) {
            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker7").on("dp.change", function (e) {
            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
        });
    });
</script>
  	 
    </body>
</html>
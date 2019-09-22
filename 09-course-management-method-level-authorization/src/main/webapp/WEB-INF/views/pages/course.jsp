<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add course</title>
<style type="text/css">
.container {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}
input[type=text]{
     font:sans-serif;
    width: 25%; /* Full width */
    padding: 12px; /* Some padding */  
    border: 1px solid #ccc; /* Gray border */
    border-radius: 4px; /* Rounded borders */
    box-sizing: border-box; /* Make sure that padding and width stays in place */
    margin-top: 6px; /* Add a top margin */
    margin-bottom: 16px; /* Bottom margin */
    resize: vertical /* Allow the user to vertically resize the textarea (not horizontally) */
}


</style>
</head>
<body>
<div class="container">

<form:form action="savecourse" commandName="course" method="post">
Course<form:input path="name"/>
description<form:input path="description"/>
instructor<form:input path="instructor"/>
courseStartDate<form:input path="courseStartDate"/>
durationInDays<form:input path="durationInDays"/>
<input type="submit" value="submit"/>

</form:form>

</div>

</body>
</html>
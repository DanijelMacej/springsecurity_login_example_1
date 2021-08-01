<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 <i>Welcome ${customerDto.username} ${name } &nbsp ${role } </i>      


<a href="/springsecurity_login_example_1/coders">coder</a>

<form:form action="logout" method="post">

<input type="submit" value="logout">


</form:form>







</body>
</html>
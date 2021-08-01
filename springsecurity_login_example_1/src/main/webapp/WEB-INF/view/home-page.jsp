<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<table style="border: 1px solid black;" align="center">
<tr>
<td> <a href="<c:url value="/mylogin" />">login</a></td>
</tr>
<tr>
<td> <a href="<c:url value="/registration" />">registration</a></td>
</tr>
</table>


</body>
</html>
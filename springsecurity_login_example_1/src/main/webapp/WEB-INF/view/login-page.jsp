<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

            <%--  <c:if test="${param.error !=null}">
             
             		      <spring:message code="message.badCredentials" ></spring:message>
             
             
             </c:if> --%>




	<div align="center">
		<h1>LOGIN PAGE</h1>
		<hr>
		<div style="color: red">
			<c:if test="${param.error !=null}">
			
			   Reason: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
<%-- 			   ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
 --%>			  
	           
			</c:if>
	</div>
		<form:form action="process-login" method="post">

			<label for="em">Username :</label>
			<input id="em" type="text" name="customusername">
			<br>
			<label for="pa">Password :</label>
			<input id="pa" type="password" name="customPassword">
			<br>
			<input type="submit" value="login">
		</form:form>
	</div>


</body>
</html>
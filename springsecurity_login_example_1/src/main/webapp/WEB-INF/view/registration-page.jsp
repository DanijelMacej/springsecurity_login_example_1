<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous"> -->


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">



<link rel="stylesheet" type="text/css" href="/springsecurity_login_example_1/URLForCss/registration.css">

</head>


<body>


	<div id="form" class="container" align="center">
		<form:form action="registrationProcess" method="post"
			modelAttribute="customerDto">

			<div class="form-group">
				<form:label for="na" path="name">Name:</form:label>
				<form:input id="na" path="name" class="form-control" />
				<br>
			</div>

		<%-- 	<div class="form-group">
				<form:label for="un" path="username">Username:</form:label>
				<form:input id="un" path="username" class="form-control" />
				<br>
			</div> --%>

			<div class="form-group">
				<form:label for="pa" path="password">Password:</form:label>
				<form:password id="pa" path="password" class="form-control" />
			</div>

			<div class="form-group">
				<form:label for="em" path="username">Username:</form:label>
				<form:input id="em" path="username" class="form-control" />
				<br>
			</div>
			<div class=" form-group has-error   form-check-inline">
				<label for="ge" class="form-check-label">Male:</label>
				<form:radiobutton id="ge" path="gender" value="male"
					class="form-check-input" />
				&emsp; <label for="ge" class="form-check-label">Female:</label>
				<form:radiobutton id="ge" path="gender" value="female"
					class="form-check-input" />
			</div>
			<div class="form-group">
				<form:label path="city">City:</form:label>
				<form:select path="city" class="form-control">
					<form:option value="Beograd" />
					<form:option value="NoviSad" />
					<form:option value="Nis" />

				</form:select>
				<br>
			</div>

			<div class="form-group form-check">
				<label class="form-check-label">I confirm that all entered
					data are correct :</label>
				<form:checkbox path="chack" />
				<br>
			</div>
			<input type="submit" value="submit">

		</form:form>

	</div>

</body>
</html>
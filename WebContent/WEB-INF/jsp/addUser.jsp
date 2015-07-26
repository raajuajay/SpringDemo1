<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello Servlet Copy</title>
</head>
<body>
	<form action="/SpringDemo1/user" method="GET" >
	<h1>Here are the details of the user entered</h1>
		<table>
		
			<tr>
				<td>First Name: </td>
				<td>${firstName}</td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td>${lastName}</td>
			</tr>
			<tr>
				<td>Address:</td>
				<td>${address}</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td>${email}</td>
			</tr>
			<tr>
				<td>Age:</td>
				<td>${age}</td>
			</tr>
			<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="OK" /></td>
			</tr>

		</table>
	</form>
</body>
</html>
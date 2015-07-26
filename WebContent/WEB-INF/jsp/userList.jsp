<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${users }" var="x">
<div>
 Id : ${x.id }   
first name : ${x.firstName }
last name : ${x.lastName }
Email : ${x.email }
Address : ${x.address }
Age : ${x.age }
</div>
</c:forEach>

</body>
</html>
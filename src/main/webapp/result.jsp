<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	${message}

	<table>

<c:if test="${isFromRegister}">
		<tr>
			<th>Name</th>
			<th>Password</th>
			<th>Mail Id</th>
		</tr>
		<c:forEach var="student" items="${studList}">
			<tr>
				<td>${student.name}</td>
				<td>${student.password}</td>
				<td>${student.mail}</td>
				<td><a href="getEditStud?email=${student.mail}">Update/Delete</a></td>
			</tr>
		</c:forEach>

	</table>
</c:if>
	<a href="/orkut/login.jsp">Home</a>
</body>
</html>
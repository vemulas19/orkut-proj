<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">


</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="register" name="studForm" method="post" />
	UserName :
	<input type="text" name="name" /> Password :
	<input type="password" name="password" /> Email Id :
	<input type="text" name="mail" />
	<input type="submit" value="Register" />

	</form>

	<form action="getAllStudDetails" method="get">
		<input type="submit" value="Get All Students" />
	</form>
	<form action="getAllStud" method="get">
		<input type="submit" value="Get All" />
	</form>
</body>
</html>
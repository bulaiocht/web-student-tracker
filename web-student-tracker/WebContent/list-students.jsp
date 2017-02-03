<%@ page import="java.util.*, com.luv2code.web.jdbc.*" %>

<!DOCTYPE html>

<html>
<head>
<title>Student Tracker</title>
</head>
<body>
	<%
		//get the list of students from request attribute
		List<Student> theStudents = (List) request.getAttribute("STUDENT_LIST");
	%>
	
	<%= theStudents %>
</body>
</html>
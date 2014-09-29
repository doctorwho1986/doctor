<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDateTime"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Scriptlets</title>
</head>
<body>
	<%!LocalDateTime classLeveVariable = LocalDateTime.now();%>
	<%
		LocalDateTime methodLocalVariable = LocalDateTime.now();
	%>
	<br /> classLeveVariable 不再变化的 :<%=classLeveVariable%>
	<br /> methodLocalVariable 时刻变化的 :<%=methodLocalVariable%>

	<br/><br/><br/>
	<p>
	<c:out value="
	Declaration tag is a block of java code for declaring class wide variables, 
	methods and classes. Whatever placed inside these tags gets initialized during 
	JSP initialization phase and has class scope. JSP container keeps this code outside
	 of the service method (_jspService()) to make them class level variables and methods.

	As we know that variables can be initialized using scriptlet too but those declaration 
	being placed inside _jspService() method which doesn’t make them class wide declarations. 
	On the other side, declaration tag can be used for defining class level variables, 
	methods and classes."/>
	</p>
	<p>
	Syntax of declaration tag:
	<br/>
	<c:out value="<%!  Declaration %>" />
    <br>
	<p>
	<c:out value="
	Scriptlets are nothing but java code enclosed within <% and %> tags. 
	JSP container moves the statements enclosed in it to _jspService() method 
	while generating servlet from JSP. The reason of copying this code to service method is: 
	For each client’s request the _jspService() method gets invoked, hence the code inside 
	it executes for every request made by client."	/>
	</p>
	<br/><br/><br/><br/><br/><br/>

</body>
</html>
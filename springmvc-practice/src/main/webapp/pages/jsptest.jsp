<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="personvo" class="com.gitjub.springmvc.vo.PersonVo" scope="session"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsp</title>
</head>
<body>
	hello time now is :	<%= LocalDateTime.now()%>
	<br/>
	<jsp:getProperty name="personvo" property="name" />
	<jsp:getProperty name="personvo" property="address" />
	
	<br/>
	jstl:<br/>
	<c:out value="${personvo.name} "/>
	<c:out value="${personvo.address} "/>
	<br/>
	Info: 看看这种技巧<br/>
	<c:out value="${personvo.info} "/>
	<br/>
	<br/>
	Info: 看看这种技巧,静态方法调用<br/>
	<c:out value="${personvo.timeNow} "/>
</body>
</html>
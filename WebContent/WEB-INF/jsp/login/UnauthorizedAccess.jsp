<%--  
	juliet mary
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<jsp:include page="/WEB-INF/jsp/css.jsp"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="project.title"/></title>
</head>
<body>
<jsp:include flush="true" page="/WEB-INF/jsp/Header.jsp"></jsp:include>
<table width="100%">
	<tr>
		<td class="H3 center">
		<fmt:message key="session.Unauthorized"/>
		</td>
	<tr>
	<tr>
		<td align=center>
		<a href="Logout.htm"><h3><fmt:message key="login.Lable"/></h3></a>
		</td>
	<tr>
</table>
</body>
</html>
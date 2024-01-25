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
<br>
<table width="100%"><tr><td class="H3 center red"><fmt:message key="GeneralError"/> </td></tr>
</table>
<br>
<jsp:include page="/WEB-INF/jsp/back.jsp"/>
</body>
</html>
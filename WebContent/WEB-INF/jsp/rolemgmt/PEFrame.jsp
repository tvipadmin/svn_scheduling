
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE><fmt:message key="project.title"/></TITLE>
</HEAD>

<jsp:include page="/WEB-INF/jsp/FrameScript.jsp"/>

<FRAMESET id='mainFrameSet' rows="0,*" frameborder=0 border=0>
	<FRAME NAME="topFrame" src="about:blank" noresize scrolling="yes">
	<FRAME NAME="botFrame" src="Patient.htm" noresize scrolling="yes">
</FRAMESET>

</HTML>
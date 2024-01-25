<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script language="javascript"> 
var newwin;
function openWindow(view) 
{ 
if (view ==  "availability")
newwin = window.open('AvailabilityRequests.htm',"mywindow","menubar=0,resizable=0,width=975,height=675,left=20,top=10,scrolling=yes"); 

} 
</script> 
<table width="98%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td><strong><fmt:message key="view"/>&nbsp;:&nbsp;</strong>
		<a href="javascript:openWindow('availability')"><strong><fmt:message key="search.Availability"/>&nbsp;&&nbsp;<fmt:message key="search.Requests"/></strong></a>
		

	</tr>
</table>
		

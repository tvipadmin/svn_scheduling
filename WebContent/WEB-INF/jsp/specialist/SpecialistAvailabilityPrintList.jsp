<%@include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib uri="/tvitags" prefix="utils"%>
<HTML>
<HEAD>
<TITLE><fmt:message key="project.title"/></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<jsp:include page="/WEB-INF/jsp/css.jsp"/>
</HEAD>
<BODY>
<center><h3><b><fmt:message key="print.page.Header"/></b></h3></center>
<hr>


            <center><h3><b><c:out value="${command.days}"/> &nbsp;<fmt:message key="search.Availability"/>&nbsp;<fmt:message key="schedule.heading.Schedule"/></b></h3></center>
            <b><fmt:message key="ImportHospital.Name"/>&nbsp;:&nbsp;<c:out value="${hospital.hospitalName}"/></b>

<hr>
<table border="0" align="center" width="100%" cellpadding="0" cellspacing="0">

<tr>
    <td><table border="1" align="center" width="100%" cellpadding="1" cellspacing="0">
<tr>




	<th class="center"><fmt:message key="schedule.Row.No"/></th>
				        
				        <th class="left"><fmt:message key="schedule.Specialty"/></th>
				        <th class="left"><fmt:message key="schedule.FromTime"/></th>
				        <th class="left"><fmt:message key="schedule.ToTime"/></th>
 
            

</tr>

<c:forEach var="shhdata" items="${command.sshList}" varStatus="status">
<c:choose>
<c:when test="${status.count % 2 == 0}">
<tr class="nonread">
</c:when>
<c:otherwise>
<tr class="read">
</c:otherwise>
</c:choose>

 <td class="center"><c:out value="${status.count}"/></td>
				        <td class="left"><c:out value="${shhdata.specialty}"/></td>
				        <td class="left"><c:out value="${shhdata.fromTime}"/></td>
				        <td class="left"><c:out value="${shhdata.toTime}"/></td>
</tr>

</c:forEach>
</td>
</table></td>
</tr>
</table>
</BODY>
</HTML>

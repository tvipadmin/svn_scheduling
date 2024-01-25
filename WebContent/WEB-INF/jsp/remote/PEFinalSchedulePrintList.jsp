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
        <c:choose>
          <c:when test="${command.checkday=='selected'}">
            <center><h3><b><c:out value="${command.finalschedule.day}"/> &nbsp;<fmt:message key="print.all.Schedule"/>&nbsp;<fmt:message key="schedule.heading.Schedule"/></b></h3></center>
          </c:when>
          <c:otherwise>
        	<center><h3><b><fmt:message key="print.all.Schedule"/>&nbsp;<fmt:message key="schedule.heading.Schedule"/></TD></b></h3></center>
          </c:otherwise>
        </c:choose>
<b><fmt:message key="ImportHospital.Name"/>:&nbsp;</b><c:out value="${hospital.hospitalName}"/></b>
<hr>
<table border="0" align="center" width="100%" cellpadding="0" cellspacing="0">

<tr>
    <td><table border="1" align="center" width="100%" cellpadding="1" cellspacing="0">
<tr>


<c:choose>
        <c:when test="${command.checkday=='selected'}">

	<th class="center"><fmt:message key="schedule.Row.No"/></th>
				        <th class="left"><fmt:message key="schedule.SSH.Hospital"/></th>
				        
				        <th class="left"><fmt:message key="schedule.Specialty"/></th>
				        <th class="left"><fmt:message key="schedule.FromTime"/></th>
				        <th class="left"><fmt:message key="schedule.ToTime"/></th>
 </c:when>
      <c:otherwise>
             <th class="center"><fmt:message key="schedule.Row.No"/></th>
				        <th class="left"><fmt:message key="schedule.SSH.Hospital"/></th>
				      
				        <th class="left"><fmt:message key="schedule.Specialty"/></th>
                        <th class="left"><fmt:message key="schedule.Day"/></th>
				        <th class="left"><fmt:message key="schedule.FromTime"/></th>
				        <th class="left"><fmt:message key="schedule.ToTime"/></th>
</c:otherwise>
      </c:choose>
</tr>

<c:forEach var="schedulelist" items="${command.pageListHolder.pageList}" varStatus="status">
<c:choose>
<c:when test="${status.count % 2 == 0}">
<tr class="nonread">
</c:when>
<c:otherwise>
<tr class="read">
</c:otherwise>
</c:choose>
<c:choose>
        <c:when test="${command.checkday=='selected'}">
 <td class="center"><c:out value="${status.count}"/></td>
				        <td class="left"><c:out value="${schedulelist.sshHospital.hospitalName}"/></td>
				        <td class="left"><c:out value="${schedulelist.specialty}"/></td>
				        <td class="left"><c:out value="${schedulelist.fromTime}"/></td>
				        <td class="left"><c:out value="${schedulelist.toTime}"/></td>
</tr>
</c:when>
<c:otherwise>
<td class="center"><c:out value="${status.count}"/></td>
				        <td class="left"><c:out value="${schedulelist.sshHospital.hospitalName}"/></td>
				       
				        <td class="left"><c:out value="${schedulelist.specialty}"/></td>
                        <td class="left"><c:out value="${schedulelist.day}"/></td>
				        <td class="left"><c:out value="${schedulelist.fromTime}"/></td>
				        <td class="left"><c:out value="${schedulelist.toTime}"/></td>
</tr>
</c:otherwise>
</c:choose>
</c:forEach>
</td>
</table></td>
</tr>
</table>
</BODY>
</HTML>

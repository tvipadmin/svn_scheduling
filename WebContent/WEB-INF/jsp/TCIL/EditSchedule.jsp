
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<HTML>
<HEAD>
<TITLE><fmt:message key="project.title"/></TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<link href="images/vitalware_profile01.css" rel="stylesheet" type="text/css">
<script language="javascript">

</script>

</HEAD>


<BODY>

<form name="ScheduleChart" method="Post">
  <jsp:include page="/WEB-INF/jsp/menu/MainMenu.jsp"/>
  <tr>
    <td colspan="2" class="h2 center"><fmt:message key="schedule.Edit"/></td>
  </tr>
</table>
<br>
<div align="right"><jsp:include page="/WEB-INF/jsp/menu/ViewAvailabilityRequests.jsp"></jsp:include></div>
<br>
<spring:bind path="sBO.*">
	<c:if test="${status.error}">
	<br>
		<TABLE border="0" align=center cellPadding=0 cellSpacing=0  class="table_bg">
	        <TR>
	          <TD class="topleft">&nbsp;</TD>
		        <td id="bulletred">
		        	<c:forEach var="errorMessage" items="${status.errorMessages}">
		        		<li class="red bold"><c:out value="${errorMessage}"/></li>
		        	</c:forEach> 
		        </td>
		        <TD class="topright">&nbsp;</TD>
	        </TR>
	        <TR>
	          <TD class="bottomleft" colspan="2"></TD>
	          <TD class="bottomright"></TD>
	        </TR>
		</TABLE>
	</c:if> 
</spring:bind>

<TABLE width="100%" border="0" align=center cellPadding=0 cellSpacing=0>
<TR>
	<TD width=50% class="right"><fmt:message key="schedule.Mandatory"/></TD>
</TR>
</TABLE>
<TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0 class="table_bg">
 
  <TR>
    <TD colspan="3"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list">
      <tr class="left"><th width="17%"><fmt:message key="schedule.SSH.Hospital"/></th><th width="17%"><fmt:message key="schedule.PE.Hospital"/></th><th width="17%" align="CENTER"><fmt:message key="schedule.Specialty"/></th><th align="CENTER" width="10%"><fmt:message key="schedule.Day"/></th><th align="CENTER" width="17%"><fmt:message key="schedule.FromTime"/></th><th align="CENTER" width="17%"><fmt:message key="schedule.ToTime"/></th></tr>
      
      	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_edit">													
					<tr>								     
					<td width="17%">
					
			        <spring:bind path="sBO.finalSchedule.sshHospital.hospitalId">
            		<select name='<c:out value="${status.expression}"/>'>
            		<option value=""><fmt:message key="schedule.SSH.Select"/></option>
					<c:if test="${sBO.specialtyList != null}">
						<c:forEach var="specialtyList" items="${sBO.specialtyList}" >
						<option value='<c:out value="${specialtyList.hospitalId}"/>'<c:if test="${status.value == specialtyList.hospitalId}">selected</c:if>> <c:out value="${specialtyList.hospitalName}"/> </option>
					    </c:forEach>
                   </c:if>
         		  </select>
        		  </spring:bind>
        		  </td>
			         <td width="17%"> 
			        <spring:bind path="sBO.finalSchedule.peHospital.hospitalId">
			        
            		<select name='<c:out value="${status.expression}"/>'>
            		<option value=""><fmt:message key="schedule.PE.Select"/></option>
					<c:if test="${sBO.patientEndList != null}">
						<c:forEach var="patientEndList" items="${sBO.patientEndList}">
		                    <option value='<c:out value="${patientEndList.hospitalId}"/>' <c:if test="${status.value == patientEndList.hospitalId}">selected</c:if>><c:out value="${patientEndList.hospitalName}"/></option>
	                   </c:forEach>
                   </c:if>
         		  </select>
        		  </spring:bind>
        		  </td>
			        <td align="CENTER" width="17%"><spring:bind path="sBO.finalSchedule.specialty">
			       
			        <input type="text" size="25" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }"/>" >
			        </spring:bind>
			        </td>
			        
			        <td align="CENTER" width="10%">
			        <spring:bind path="sBO.finalSchedule.day">
			        
            		<select name='<c:out value="${status.expression}"/>'>
            		<option value=""><fmt:message key="schedule.Day"/></option>
					<c:if test="${sBO.dayArray != null}">
						<c:forEach var="dayArray" items="${sBO.dayArray}">
		                    <option value='<c:out value="${dayArray}"/>' <c:if test="${status.value == dayArray}">selected</c:if>><c:out value="${dayArray}"/></option>
	                   </c:forEach>
                   	</c:if>
         		  	</select>
        		 	 </spring:bind>
        		  	</td>
			         <td align="CENTER" width="17%">
			        <spring:bind path="sBO.finalSchedule.fromHour">
			        
            		<select name='<c:out value="${status.expression}"/>'>
            		<option value=""><fmt:message key="schedule.Hours"/></option>
					<c:if test="${sBO.hrsArray != null}">
						<c:forEach var="hrsArray" items="${sBO.hrsArray}">
		                    <option value='<c:out value="${hrsArray}"/>' <c:if test="${status.value == hrsArray}">selected</c:if>><c:out value="${hrsArray}"/></option>
	                   </c:forEach>
                   	</c:if>
         		  	</select>
        		 	 </spring:bind>
        		  
			        <spring:bind path="sBO.finalSchedule.fromMin">
			        
            		<select name='<c:out value="${status.expression}"/>'>
            		<option value=""><fmt:message key="schedule.Minutes"/></option>
					<c:if test="${sBO.minArray != null}">
						<c:forEach var="minArray" items="${sBO.minArray}">
		                    <option value='<c:out value="${minArray}"/>' <c:if test="${status.value == minArray}">selected</c:if>><c:out value="${minArray}"/></option>
	                   </c:forEach>
                   	</c:if>
         		  	</select>
        		  	</spring:bind>
        		  	</td>
        		  	
			         <td align="CENTER" width="17%">
			        <spring:bind path="sBO.finalSchedule.toHour">
			        
            		<select name='<c:out value="${status.expression}"/>'>
            		<option value=""><fmt:message key="schedule.Hours"/></option>
					<c:if test="${sBO.hrsArray != null}">
						<c:forEach var="hrsArray" items="${sBO.hrsArray}">
		                    <option value='<c:out value="${hrsArray}"/>' <c:if test="${status.value == hrsArray}">selected</c:if>><c:out value="${hrsArray}"/></option>
	                   </c:forEach>
                   	</c:if>
         		  	</select>
        		 	 </spring:bind>
        		  
			        <spring:bind path="sBO.finalSchedule.toMin">
			        
            		<select name='<c:out value="${status.expression}"/>'>
            		<option value=""><fmt:message key="schedule.Minutes"/></option>
					<c:if test="${sBO.minArray != null}">
						<c:forEach var="minArray" items="${sBO.minArray}">
		                    <option value='<c:out value="${minArray}"/>' <c:if test="${status.value == minArray}">selected</c:if>><c:out value="${minArray}"/></option>
	                   </c:forEach>
                   	</c:if>
         		  	</select>
        		  	</spring:bind>
        		  	</td>
								            </tr>			
									
			            	</table>
      
    </table></TD>
  </TR>
  <TR>
    <TD class="bottomleft" width="20%"><img src="images/spacer.gif" width="1" height="4"></TD>
    <TD class="bottomright" colspan="2"></TD>
  </TR>
</TABLE>
<br>

<TABLE width="100%" border="0" align=center cellPadding=0 cellSpacing=0>
<TR>
	<TD width=50% class="center">
		<input name="sumbit" type="button" OnClick="javascript:document.location.href='Tcil.htm?';" class="button" value=" <fmt:message key="button.Back"/> "/> &nbsp;
		<input type=submit class="button" id="add_button" value=" <fmt:message key="button.Save"/> ">
	</TD>
</TR>
</TABLE>


<script language="javascript">

</script>
</form>
</BODY>
</HTML>




































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
    <td colspan="2" class="h2 center"><fmt:message key="schedule.Add"/></td>
  </tr>
</table>
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
<br>
<TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0 class="table_bg">

 <TR>
    <TD width="60%" class="bold center"><font size="2"><c:out value="${sBO.day}"/></font></TD>
 </TR>
    </TABLE>
    <br>
<fmt:message key="schedule.Mandatory"/>

  
<TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0 class="table_bg">
  
  <TR>
    <TD colspan="3"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list">
      <tr class="left"><th width="5%"><fmt:message key="schedule.Row.No"/></th><th width="17%" align="CENTER"><fmt:message key="schedule.Specialty"/></th><th align="CENTER" width="17%"><fmt:message key="schedule.FromTime"/></th><th align="CENTER" width="17%"><fmt:message key="schedule.ToTime"/></th></tr>
      <c:set var="scheduleCount" value="0" scope="page"/>
      
			<c:forEach var="schedule" items="${sBO.finalScheduleList}">
			 
				<c:set var="scheduleCount" value="${scheduleCount+1}" scope="page"/>
			</c:forEach> 
			
			
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_edit">													
					<c:choose>
							<c:when test="${scheduleCount > 0 }">
								<c:forEach items="${sBO.finalScheduleList}" varStatus="I">
								 <tr>
								    <td valign=top width="5%">
								     <input type=checkbox id="add_chk_<c:out value="${I.count}"/>" value="<c:out value="${I.count}"/>" class=checkbox name="chkFinalSchedules" onclick="javascript:enableDisableButton('add')">&nbsp;
								    &nbsp;<c:out value="${I.count}"/></td>
								     
					
			        <td align="CENTER" width="17%"><spring:bind path="sBO.finalScheduleList[${I.index}].specialty">
			       
			        <input type="text" size="25" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }"/>" >
			        </spring:bind>
			        </td>
			        
			      			        
			         <td align="CENTER" width="17%">
			        <spring:bind path="sBO.finalScheduleList[${I.index}].fromHour">
			        
            		<select name='<c:out value="${status.expression}"/>'>
            		<option value=""><fmt:message key="schedule.Hours"/></option>
					<c:if test="${sBO.hrsArray != null}">
						<c:forEach var="hrsArray" items="${sBO.hrsArray}">
		                    <option value='<c:out value="${hrsArray}"/>' <c:if test="${status.value == hrsArray}">selected</c:if>><c:out value="${hrsArray}"/></option>
	                   </c:forEach>
                   	</c:if>
         		  	</select>
        		 	 </spring:bind>
        		  
			        <spring:bind path="sBO.finalScheduleList[${I.index}].fromMin">
			        
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
			        <spring:bind path="sBO.finalScheduleList[${I.index}].toHour">
			        
            		<select name='<c:out value="${status.expression}"/>'>
            		<option value=""><fmt:message key="schedule.Hours"/></option>
					<c:if test="${sBO.hrsArray != null}">
						<c:forEach var="hrsArray" items="${sBO.hrsArray}">
		                    <option value='<c:out value="${hrsArray}"/>' <c:if test="${status.value == hrsArray}">selected</c:if>><c:out value="${hrsArray}"/></option>
	                   </c:forEach>
                   	</c:if>
         		  	</select>
        		 	 </spring:bind>
        		  
			        <spring:bind path="sBO.finalScheduleList[${I.index}].toMin">
			        
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
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr><td valign=top class=center> -- </td></tr>
									</c:otherwise>
								</c:choose>
			            	</table>
      
    </table></TD>
  </TR>
  <TR>
    <TD class="bottomleft" width="20%"><img src="images/spacer.gif" width="1" height="4"></TD>
    <TD class="bottomright" colspan="2"></TD>
  </TR>
</TABLE>
<br>

    <br>

<TABLE width="100%" border="0" align=center cellPadding=0 cellSpacing=0>
<TR>
	<TD width=50% class="left">
		<c:if test="${scheduleCount>0}">
			&nbsp;<a href="javascript:checkAll('add')">All</a> | <a href="javascript:checkNone('add')">None</a>
		</c:if>
	</TD>
	<TD width=50% class="right">
		<input name="sumbit" type="button" OnClick="javascript:document.location.href='Specialist.htm?day=<c:out value="${sBO.day}"/>';" class="button" value=" <fmt:message key="button.Back"/> "/>
	</TD>
	<TD width=50% class="right">
		<input type=submit class="button" id="add_button" value=" <fmt:message key="button.Add"/> " disabled>
	</TD>
	
</TR>
</TABLE>


<script language="javascript">
function checkAll(mode)
{
	var index = 1;
	obj = document.all(mode+"_chk_"+index);

	while(obj != null)
	{
		obj.checked = true;
		index = index+1;
		obj = document.all(mode+"_chk_"+index);	
	}
	document.all(mode+"_button").disabled = false;
}
function checkNone(mode)
{
	var index = 1;
	obj = document.all(mode+"_chk_"+index);

	while(obj != null)
	{
		obj.checked = false;
		index = index+1;
		obj = document.all(mode+"_chk_"+index);	
	}
	document.all(mode+"_button").disabled = true;
}
function enableDisableButton(mode)
{
	var chk_checked = false;
	var index = 1;
	obj = document.all(mode+"_chk_"+index);

	while(obj != null)
	{
		if(obj.checked)
		{
			chk_checked = true;
			break;
		}
		index = index+1;
		obj = document.all(mode+"_chk_"+index);	
	}
	
	if(chk_checked)
		document.all(mode+"_button").disabled = false;
	else
		document.all(mode+"_button").disabled = true;
}
</script>
</form>
</BODY>
</HTML>
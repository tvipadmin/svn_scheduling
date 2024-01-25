
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<HTML>
<HEAD>
<TITLE><fmt:message key="project.title"/></TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<link href="images/vitalware_profile01.css" rel="stylesheet" type="text/css">
<script language="javascript">
function submitSearchPage()
{
	document.forms[0].action="ScheduleAvailability.htm";
	document.forms[0].submit();
}
function captureReturnKey(event)
{
	var	code = 0;
	code = event.keyCode;
	if(code	==	13)
		submitSearchPage();
}

function Print()
{
	document.all.Card.style.display="";
	document.all.AddedMessage.style.display="none";
	window.print();
	document.all.Card.style.display="none";
	document.all.AddedMessage.style.display="";
	
}

</script>
</HEAD>
<c:choose>
	<c:when test="${param.chk_Print == 'Print' }">
		<body onload="javascript:Print();"> 
	</c:when>
	<c:otherwise>
		<body>
	</c:otherwise>
</c:choose>

<script language="JavaScript" src="scw.js" type="text/JavaScript"></script>
<div id="Card" style="display:none">
	<jsp:include page="/WEB-INF/jsp/TCIL/TcilScheduleAvailabilityPrintList.jsp"/>
</div>
<div id="AddedMessage"> 
<form name="ScheduleAvailabilityForm" method="Post">


<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" class="h2 center"><fmt:message key="schedule.Availability"/></td>
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
<c:if test="${sBO.searchFilters}">
	<TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0 class="table_bg">
 
  <TR>
    <TD colspan="3">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list">
   	<tr>   
   				
   	<td align="CENTER" width="100%">
   	<strong><fmt:message key="search.param"/></font>&nbsp:&nbsp;&nbsp;</strong>
   	 <spring:bind path="sBO.hospitalName">
     <select name = '<c:out value="${status.expression}"/>'>
     <option value=""><fmt:message key="schedule.SSH.Select"/></option>
	 <c:if test="${sBO.specialtyList != null}">
	 <c:forEach var="specialtyList" items="${sBO.specialtyList}" >
	 <option value='<c:out value="${specialtyList.hospitalId}"/>'<c:if test="${status.value == specialtyList.hospitalId}">selected</c:if>> <c:out value="${specialtyList.hospitalName}"/> </option>
	 </c:forEach>
     </c:if>
     </select>
     </spring:bind>
   &nbsp;&nbsp;
	 
	 <spring:bind path="sBO.day">
			        
     <select name='<c:out value="${status.expression}"/>'>
     <option value=""><fmt:message key="schedule.Select.Day"/></option>
	 <c:if test="${sBO.dayArray != null}">
	 <c:forEach var="dayArray" items="${sBO.dayArray}">
	 <option value='<c:out value="${dayArray}"/>' <c:if test="${status.value == dayArray}">selected</c:if>><c:out value="${dayArray}"/></option>
	 </c:forEach>
     </c:if>
     </select>
     </spring:bind>
   &nbsp;&nbsp;
     <input tabindex="20" name="selectbutton" type='button' OnClick="javascript:submitSearchPage();" class='button' value='Search'></td>
     </tr>
     </table>
     </TD>
     </TR>
  	
  	
</TABLE>
</c:if>
<br>

<c:choose>
	<c:when  test="${sBO.finalScheduleList == null}">
		<TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0 class="table_bg">
		  <TR>
	    	<TD width="100%" class="center bold">
	    	<fmt:message key="search.NoSchedules.Available"/>
	    	</TD>
		  </TR>
		</TABLE>
	</c:when>
	<c:otherwise>
	
			<TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0 class="table_bg">
				 
			  <TR>
				    <TD colspan="2"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list">
				      <tr>
				      	<th class="center"><fmt:message key="schedule.Row.No"/></th>
				        <th class="left"><fmt:message key="schedule.SSH.Hospital"/></th>
				        <th class="left"><fmt:message key="schedule.Specialty"/></th>
				        <th class="left"><fmt:message key="schedule.Day"/></th>
				        <th class="left"><fmt:message key="schedule.FromTime"/></th>
				        <th class="left"><fmt:message key="schedule.ToTime"/></th>
				      </tr>
					<c:forEach var="schedule" items="${sBO.finalScheduleList}" varStatus="status">
					<c:choose>
						<c:when test="${status.count % 2 == 0}">
							<tr class="nonread">
						</c:when>
						<c:otherwise>
							<tr class="read">
						</c:otherwise>
					</c:choose>
				        <td class="center"><c:out value="${status.count}"/></td>
				        <td class="left"><c:out value="${schedule.hospital.hospitalName}"/></td>
				        <td class="left"><c:out value="${schedule.specialty}"/></td>
				        <td class="left"><c:out value="${schedule.day}"/></td>
				        <td class="left"><c:out value="${schedule.fromTime}"/></td>
				        <td class="left"><c:out value="${schedule.toTime}"/></td>
				    </tr>
					</c:forEach>
					
			    </table></TD>
			  </TR>
			  <TR>
			    <TD class="bottomleft"><img src="images/spacer.gif" width="1" height="4"></TD>
			    <TD class="bottomright"></TD>
			  </TR>
			</TABLE>
			
	</c:otherwise>
</c:choose>
<br>
<center>
      <input type="button" value=" <fmt:message key="button.Close"/> " OnClick="javascript:top.window.close();" class='button'>
<c:choose>
	<c:when  test="${sBO.finalScheduleList != null}"> 
       &nbsp;<input type="submit" class="button" name="chk_Print" value="Print">
   </c:when>
</c:choose>
</center>
</form>
</div>
</BODY>
</html>

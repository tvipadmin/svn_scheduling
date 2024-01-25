<%-- * Juliet Mary *  --%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<HTML>
<HEAD>
<TITLE><fmt:message key="project.title"/></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<link href="images/vitalware_profile01.css" rel="stylesheet" type="text/css">

<script language="javascript">

   function checkAll(mode,val)
     {
       var index = 1;
	   obj = document.all(mode+"_chk_"+index);
       while(obj != null)
	   {
		 obj.checked = true;
		 index = index+val;
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

   function enableDisableButton(mode)  //enabling and disabling delete button
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
     
    function deleteFinalSchedule(opt)  //calling the controller for delete option
     {
      if(confirm("Are You Sure,You Wan't To Delete Selected Schedules."))
	   {
		document.ScheduleChart.action="Specialist.htm?day=<c:out value="${command.days}"/>&daycountleft=<c:out value="${command.countleft}"/>&daycountright=<c:out value="${command.countright}"/>&buttonsd="+opt;
	    document.ScheduleChart.submit();
	   }
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
	<jsp:include page="/WEB-INF/jsp/specialist/SpecialistAvailabilityPrintList.jsp"/>
</div>
<div id="AddedMessage"> 
<form name="ScheduleChart" method="Post">
<c:set var="mainSelected" value="mmHome" scope="session"/>
 <c:set var="subSelected" value="smscheduleAvailable" scope="session"/>
<jsp:include page="/WEB-INF/jsp/menu/MainMenu.jsp"/>

<tr>
    <td colspan="2" class="h2 center"><c:out value="${command.days}"/>&nbsp;<fmt:message key="schedule.heading.Schedule"/></td>
  </tr>
</table>
<br><br>

<TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0 class="table_bg">
  
  <TR>
    <TD class="topleft">&nbsp;</TD>
    <TD class="topright bold"><a href="Specialist.htm?daycountleft=<c:out value="${command.countleft}"/>&daycountright=<c:out value="${command.countright}"/>&click=left">&lt;&lt; <fmt:message key="schedule.heading.Previous"/></a> | <a href="Specialist.htm?daycountright=<c:out value="${command.countright}"/>&daycountleft=<c:out value="${command.countleft}"/>&click=right"><fmt:message key="schedule.heading.Next"/> &gt;&gt;</a>&nbsp;</TD>
  </TR>
  <TR>
    <TD colspan="2">
	<TABLE width="100%" border="1" align=center cellPadding=0 cellSpacing=0 class="table_list">	
		<c:choose>
			<c:when test="${command.errormessage=='DNE'}">
				<TR class="read">
					<TD class="center bold"> <br>&quot;<fmt:message key="schedule.error.Notexist"/>&quot; <br><br>
					<input name="buttonsd" type="button" OnClick="javascript:document.location.href='SSHAddSchedule.htm?day=<c:out value="${command.days}"/>';" class="button" value=" <fmt:message key="button.Add.Specialist"/> "/><br>&nbsp;</TD>
				</TR>
    		</c:when>
     	<c:otherwise>
	
    <c:set var="scheduleCount" value="0" scope="page"/>
       <c:forEach var="schedule" items="${command.sshList}">
			<c:set var="scheduleCount" value="${scheduleCount+1}" scope="page"/>
	   </c:forEach> 

      <tr class="left"><th width="2%">&nbsp;</th><th width="32%" align="CENTER"><fmt:message key="schedule.Specialty"/></th><th width="20%" align="CENTER"><fmt:message key="schedule.FromTime"/></th><th width="30%" align="CENTER"><fmt:message key="schedule.ToTime"/></th><th width="30%" align="CENTER"><fmt:message key="schedule.Edit"/></th></tr>
      <c:forEach var="shhdata" items="${command.sshList}" varStatus="status">
               <c:choose>
                 <c:when test="${status.count % 2 == 0}">
                 	<tr class="nonread">
                 </c:when>
                 <c:otherwise>
             	 	<tr class="read">
                 </c:otherwise>
              </c:choose>
	            <td><input type="checkbox" name="chkDelFinalSchedule" id="del_chk_<c:out value="${status.count }"/>" onclick="javascript:enableDisableButton('del')" value="<c:out value="${shhdata.scheduleId}"/>"></td>
	            <td align="CENTER"><c:out value="${shhdata.specialty}"/></td>
	            <td align="CENTER"><c:out value="${shhdata.fromTime}"/> </td>
	            <td align="CENTER"><c:out value="${shhdata.toTime}"/></td>
	            <td align="CENTER"><a href="SSHEditSchedule.htm?scheduleId=<c:out value="${shhdata.scheduleId}"/>"><fmt:message key="schedule.Edit"/></a></td>

       </c:forEach>
	   </tr>
       	</table>
	  	<TR>
	    	<TD class="bottomleft"><img src="images/spacer.gif" width="1" height="4"></TD>
	    	<TD class="bottomright"></TD>
		</TR>
</TABLE>
      		<c:choose>
			    <c:when test="${command.errormessage=='Deleted'}">
					<br><span class="bold"><center><b><font color="darkgreen">&quot;<fmt:message key="schedule.delete.Success"/>&quot;</font></b></center></span><br>
       			</c:when>
        		<c:when test="${command.errormessage=='NotDeleted'}">
      			    <br><span class="bold"><center><b><font color="darkgreen">&quot;<fmt:message key="schedule.delete.Notsuccess"/>&quot;</font></b></center></span><br>
       			</c:when>
       		</c:choose>
<br>
		<TABLE width="100%" border="0" align=center cellPadding=0 cellSpacing=0>
			<TR>
				<TD width=30% class="left">
					<c:if test="${scheduleCount>0}">
						&nbsp;&nbsp;<a href="javascript:checkAll('del',1)"><fmt:message key="schedule.heading.All"/></a> | <a href="javascript:checkNone('del',1)"><fmt:message key="schedule.heading.None"/></a>
					</c:if>
				</TD>
				<TD width=60% class="left">
					<input name="buttonsd" type="button" OnClick="javascript:document.location.href='SSHAddSchedule.htm?day=<c:out value="${command.days}"/>';" class="button" value=" <fmt:message key="button.Add.Specialist"/> "/>
					<input name="buttonsd" type='button' id="del_button" class='button' value=" <fmt:message key="button.Delete"/> "  onclick="javascript:deleteFinalSchedule('Delete')" disabled>&nbsp;
                    <input type="submit" class="button" name="chk_Print" value="Print">  
				</TD>
			</TR>
		</TABLE>
	</c:otherwise>
</c:choose>

</form>
</BODY>
</HTML>
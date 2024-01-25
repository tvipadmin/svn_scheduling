<%-- * Juliet Mary *  --%>

<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<HTML>
<HEAD>
<TITLE><fmt:message key="project.title"/></TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<link href="images/vitalware_profile01.css" rel="stylesheet" type="text/css">

<script language="javascript">

    function addFinalSchedule(opt)  //Calling for Adding a new Schedule
     {
         document.ScheduleChart.action="PEAddSchedule.htm"; //make changes
	     document.ScheduleChart.submit();
     }

    function backFinalSchedule()  //back
     {
         document.ScheduleChart.action="Patient.htm";
	     document.ScheduleChart.submit();
     }
   
    function deleteFinalSchedule(opt)  //calling the controller for delete option
     {
      if(confirm("Are You Sure,You Wan't To Delete Selected Schedules."))
	   {
		document.ScheduleChart.action="Patient.htm?buttonsd="+opt;
	    document.ScheduleChart.submit();
	   }
     }

    function searchFinalSchedule(opt)  //calling the controller for search
     {
         document.ScheduleChart.action="Patient.htm?buttonsd="+opt;
	     document.ScheduleChart.submit();
     }

    function results(opt)
     {
      document.forms[0].action="Patient.htm?search=true&buttonsd=Search&page="+opt;
	  document.forms[0].submit();
     }

    function enableDisableButton(mode,val)  //enabling and disabling delete button
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
		     index = index+val;
		     obj = document.all(mode+"_chk_"+index);	
	     }
	
	    if(chk_checked)
    	   	document.all(mode+"_button").disabled = false;
	    else
		    document.all(mode+"_button").disabled = true;
     }

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
	<jsp:include page="/WEB-INF/jsp/remote/SpecialistRequestPrintList.jsp"/>
</div>
<div id="AddedMessage">
<form name="ScheduleChart" method="Post">
 <c:set var="mainSelected" value="mmHome" scope="session"/>
 <c:set var="subSelected" value="smscheduleRequested" scope="session"/>
  <jsp:include page="/WEB-INF/jsp/menu/MainMenu.jsp"/>
  <tr>
    <td colspan="2" class="h2 center"><fmt:message key="schedule.heading.Patient"/></td>
  </tr>
</table>
<br>
<TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0 class="table_bg">
  <TR>
    <TD width="50%" class="topleft">&nbsp;<strong><fmt:message key="schedule.Search.Exist"/></strong></TD>
    <TD width="49%" class="topright">&nbsp;</TD>
  </TR>
  
  <TR>
    <TD colspan="2"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_edit">
      <tr class="read">
        <td align="CENTER"><fmt:message key="schedule.SSH.Hospital"/><strong>: &nbsp;</strong>
             <spring:bind path="command.peschedule.sshHospital.hospitalId">
		       <select name="<c:out value="${status.expression}"/>" id="peid">
                   <option value=''><fmt:message key="schedule.AllSSH.Select"/></option>
                   <c:forEach var="patient" items="${command.sshId}" varStatus="status">
                     <option value='<c:out value="${patient.hospitalId}"/>' <c:if test="${patient.hospitalId == command.peschedule.sshHospital.hospitalId}"> selected </c:if>> <c:out value="${patient.hospitalName}"/></option>
                   </c:forEach>
               </select>
             </spring:bind>
           &nbsp;<fmt:message key="schedule.Day"/><strong> : </strong> &nbsp;
            <spring:bind path="command.peschedule.day">
		       <select name="<c:out value="${status.expression}"/>" id="day">
                 <option value=''><fmt:message key="schedule.AllDAY.Select"/></option>
                   <option value='Sunday'<c:if test="${'Sunday' == command.peschedule.day}"> selected </c:if>>Sunday</option>
                   <option value='Monday'<c:if test="${'Monday' == command.peschedule.day}"> selected </c:if> >Monday</option>
                   <option value='Tuesday'<c:if test="${'Tuesday' == command.peschedule.day}"> selected </c:if>>Tuesday</option>
                   <option value='Wednesday'<c:if test="${'Wednesday' == command.peschedule.day}"> selected </c:if>>Wednesday</option>
                   <option value='Thursday'<c:if test="${'Thursday' == command.peschedule.day}"> selected </c:if>>Thursday</option>
                   <option value='Friday'<c:if test="${'Friday'== command.peschedule.day}"> selected </c:if>>Friday</option>
                   <option value='Saturday'<c:if test="${'Saturday' == command.peschedule.day}"> selected </c:if>>Saturday</option>
               </select>
            </spring:bind>
          &nbsp;
       <input name="buttonsd" type="button" class='button'  value='Search' onclick="javascript:searchFinalSchedule('Search')"> </td>
     </tr>
  </table></TD>
  </TR>
  <TR>
    <TD class="bottomleft"><img src="images/spacer.gif" width="1" height="4"></TD>
    <TD class="bottomright"></TD>
  </TR>
  </TABLE>
<br>

 <TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0 class="table_bg">
  <c:choose>
    <c:when test="${command.errormessage=='DNE'}">
     <TR>
        <TD class="topleft bold" colspan="2" align="center">&quot;<fmt:message key="schedule.error.Notexist"/>&quot;</TD>
     </TR>
     
     </TABLE>
     <p class="center">
     <input name="buttonsd" type="button" OnClick="javascript:document.location.href='PEAddSchedule.htm';" class="button" value=" <fmt:message key="schedule.request"/> "/>
     </p>
   </c:when>
   
   
   <c:otherwise>
  
  <TR>
   <c:choose>
       <c:when test="${command.checkday=='selected'}">
        <TD class="topleft bold" colspan="2"><c:out value="${command.peschedule.day}"/>&nbsp;<fmt:message key="schedule.heading.Schedule"/></TD>
       </c:when>
       <c:otherwise>
        <TD class="topleft bold" colspan="2"><fmt:message key="schedule.heading.All"/>&nbsp;<fmt:message key="schedule.heading.Schedule"/></TD>
       </c:otherwise>
  </c:choose>
    <TD class="topright" width="50%">
              <c:choose>
				 <c:when test="${!command.pageListHolder.firstPage}">
				 <a href="javascript:results('first')"><fmt:message key="search.result.First"/></a>
				 </c:when>
				   	<c:otherwise>
				   	<fmt:message key="search.result.First"/>
				   	</c:otherwise>
			  </c:choose>
				    	&nbsp;|&nbsp;
			  <c:choose>
				   <c:when test="${!command.pageListHolder.firstPage}">
				   <a href="javascript:results('previous')"><fmt:message key="search.result.Previous"/></a>
				   </c:when>
				    	<c:otherwise>
				    	<fmt:message key="search.result.Previous"/>
				    	</c:otherwise>
			   </c:choose>
				    	&nbsp;|&nbsp;
			   <c:choose>
				  <c:when test="${!command.pageListHolder.lastPage}">
				  <a href="javascript:results('next')"><fmt:message key="search.result.Next"/></a>
			      </c:when>
			    	  <c:otherwise>
			    	  <fmt:message key="search.result.Next"/>
			    	  </c:otherwise>
		       </c:choose>
		    		   &nbsp;|&nbsp;
			   <c:choose>
			       <c:when test="${!command.pageListHolder.lastPage}">
				   <a href="javascript:results('last')"><fmt:message key="search.result.Last"/></a>
			       </c:when>
			    	   <c:otherwise>
			    	   <fmt:message key="search.result.Last"/>
			    	   </c:otherwise>
		      </c:choose></TD>
 </TR>
  <TR>
    <TD colspan="3"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list">
      <c:choose>
        <c:when test="${command.checkday=='selected'}">
       <tr class="left"><th width="2%">&nbsp;</th><th align="CENTER"><fmt:message key="schedule.SSH.Hospital"/></th><th align="CENTER"><fmt:message key="schedule.Specialty"/></th><th align="CENTER"><fmt:message key="schedule.FromTime"/></th><th align="CENTER"><fmt:message key="schedule.ToTime"/></th><th align="CENTER"><fmt:message key="schedule.Edit"/></th></tr> 
      </c:when>
      <c:otherwise>
      <tr class="left"><th width="2%">&nbsp;</th><th align="CENTER"><fmt:message key="schedule.SSH.Hospital"/></th><th align="CENTER"><fmt:message key="schedule.Specialty"/></th><th align="CENTER"><fmt:message key="schedule.FromTime"/></th><th align="CENTER"><fmt:message key="schedule.ToTime"/></th><th align="CENTER"><fmt:message key="schedule.Day"/></th><th align="CENTER"><fmt:message key="schedule.Edit"/></th></tr> 
      </c:otherwise>
      </c:choose>
      
      <c:set var="scheduleCount" value="0" scope="page"/>
       <c:forEach var="schedule" items="${command.pageListHolder.pageList}">
			<c:set var="scheduleCount" value="${scheduleCount+1}" scope="page"/>
	   </c:forEach> 
      <c:choose>
        <c:when test="${command.checkday=='selected'}">
           <c:forEach var="schedulelist" items="${command.pageListHolder.pageList}" varStatus="status">
              <c:choose>
                 <c:when test="${status.count % 2 == 0}">
                   <tr class="nonread">
                 </c:when>
                   <c:otherwise>
                     <tr class="read">
                   </c:otherwise>
              </c:choose>
                  <td><input type="checkbox" name="chkDelFinalSchedule" id="del_chk_<c:out value="${status.count }"/>" onclick="javascript:enableDisableButton('del',1)" value="<c:out value="${schedulelist.scheduleId}"/>"></td>
                  <td align="CENTER"><c:out value="${schedulelist.sshHospital.hospitalName}"/></td>
                  <td align="CENTER"><c:out value="${schedulelist.specialty}"/></td>
                  <td align="CENTER"><c:out value="${schedulelist.fromTime}"/></td>
                  <td align="CENTER"><c:out value="${schedulelist.toTime}"/></td>
                  <td align="CENTER"><a href="PEEditSchedule.htm?scheduleId=<c:out value="${schedulelist.scheduleId}"/>"><fmt:message key="schedule.Edit"/></a></td>
           </c:forEach> 
           <c:choose>
            <c:when test="${command.errormessage=='Deleted'}">
     <TR>
       <TD class="read bold" colspan="6" align="center"><font color="dark green">&quot;<fmt:message key="schedule.delete.Success"/>&quot;</font></TD>
     
  </TR>
  
 </c:when>
  <c:when test="${command.errormessage=='NotDeleted'}">
 <TR>
    <TD class="read bold" colspan="6" align="center"><font color="dark green">&quot;<fmt:message key="schedule.delete.Notsuccess"/>&quot;</font></TD>
     
  </TR>
  
 </c:when>
           </c:choose>
        </c:when>
     <c:otherwise>
          <c:forEach var="schedulelist" items="${command.pageListHolder.pageList}" varStatus="status">
            <c:choose>
                 <c:when test="${status.count % 2 == 0}">
                   <tr class="nonread">
                 </c:when>
                   <c:otherwise>
                     <tr class="read">
                   </c:otherwise>
              </c:choose>
                 <td><input type="checkbox" name="chkDelFinalSchedule" id="del_chk_<c:out value="${status.count }"/>" onclick="javascript:enableDisableButton('del',1)" value="<c:out value="${schedulelist.scheduleId}"/>"></td>
                 <td align="CENTER"><c:out value="${schedulelist.sshHospital.hospitalName}"/></td>
                 <td align="CENTER"><c:out value="${schedulelist.specialty}"/></td>
                 <td align="CENTER"><c:out value="${schedulelist.fromTime}"/></td>
                 <td align="CENTER"><c:out value="${schedulelist.toTime}"/></td>
                 <td align="CENTER"><c:out value="${schedulelist.day}"/></td>
                 <td align="CENTER"><a href="PEEditSchedule.htm?scheduleId=<c:out value="${schedulelist.scheduleId}"/>"><fmt:message key="schedule.Edit"/></a></td>
         </c:forEach> 
         <c:choose>
          <c:when test="${command.errormessage=='Deleted'}">
     <TR>
       <TD class="read bold" colspan="7" align="center"><font color="darkgreen">&quot;<fmt:message key="schedule.delete.Success"/>&quot;</font></TD>
     
  </TR>
  
 </c:when>
  <c:when test="${command.errormessage=='NotDeleted'}">
 <TR>
    <TD class="read bold" colspan="7" align="center"><font color="darkgreen">&quot;<fmt:message key="schedule.delete.Notsuccess"/>&quot;</font></TD>
     
  </TR>
  
 </c:when>
         </c:choose>
         </c:otherwise>
    </c:choose>
   </TR>
  <TR>
    <TD class="bottomleft"><img src="images/spacer.gif" width="1" height="4"></TD>
    <TD class="bottomright" colspan="2"></TD>
  </TR>
</TABLE>
  </TR>
</TABLE>
<br>
  <TABLE width="100%" border="0" align=center cellPadding=0 cellSpacing=0>
<TR>
	<TD width=30% class="left">
		<c:if test="${scheduleCount>0}">
	    &nbsp;&nbsp;<a href="javascript:checkAll('del',1)"><fmt:message key="schedule.heading.All"/></a> | <a href="javascript:checkNone('del',1)"><fmt:message key="schedule.heading.None"/></a>
	    </c:if>
	</TD>
	<TD width=50% class="left">
		<input name="buttonsd" type="button" OnClick="javascript:document.location.href='PEAddSchedule.htm';" class="button" value=" <fmt:message key="schedule.request"/> "/> &nbsp;
		<input name="buttonsd" type='button' id="del_button" class='button' value="<fmt:message key="button.Delete"/>"  onclick="javascript:deleteFinalSchedule('Delete')" disabled>&nbsp;
        <input type="submit" class="button" name="chk_Print" value="Print">  
	</TD>
	
	
</TR>
</TABLE>

</c:otherwise>
</c:choose>
</form>
</BODY>
</HTML>
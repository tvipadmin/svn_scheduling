<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MainMenu</title>
</head>
<script type="text/javascript">
function cl()
{
  closetime = 1;
    if (typeof newwin != "object")
     {
          newwin = window.open("",target="mywindow","menubar=0,resizable=0,width=5,height=5,left=0,top=0"); 
          newwin.document.write('<b>Wait Logging Out<b>');
          setTimeout(newwin.close(),closetime*1000);
     } 
     else { 
        if (!newwin.closed)
         { 
           newwin.close(); 
         } 
          } 
}
</script>
<body>
     <c:choose>
       <c:when test="${login.accountType=='TCILADMIN'}">
       <table width="100%" border="0" cellpadding="0" cellspacing="0">
       <tr>
    <td colspan="2" class="mainnav_bgsml"><table border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <td class="mainnav_slop">&nbsp;</td>
        <td class="main_nav_devider">&nbsp;</td>
        <c:choose>
	        <c:when test='${mainSelected == "mmHome"}'>
        <td class="main_nav_selected">&nbsp;<fmt:message key="schedule.Home"/>&nbsp;</td>
        </c:when>
        	<c:otherwise>
        	 <td class="main_navigation"><a href="Tcil.htm">&nbsp;<fmt:message key="schedule.Home"/>&nbsp;</a></td>
        	 </c:otherwise>
    	 </c:choose>
         <td class="main_nav_devider"></td>
        <c:choose>
	        <c:when test='${mainSelected == "mmHelp"}'>
        <td class="main_nav_selected">&nbsp;<fmt:message key="schedule.Help"/>&nbsp;</td>
        </c:when>
        <c:otherwise>
        <td class="main_navigation"><a href="#">&nbsp;<fmt:message key="schedule.Help"/>&nbsp;</a></td>
        </c:otherwise>
        </c:choose>
        <td class="main_nav_devider"></td>
        <td class="main_navigation"><a href="Logout.htm" onClick="javascript:cl();">&nbsp;<fmt:message key="schedule.Logout"/>&nbsp;</a></td>
        <td class="main_nav_devider"></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td width="67%"><img src="images/tvilogo_new.gif" width=206 height=59 alt="VitalWare 2.0"></td>
    <td width="33%" align="right" class="h2"><fmt:message key="schedule.TCIL"/></td>
  </tr>
  <tr valign="top" class="subnav_bgsml">
    <td><table border="0" cellpadding="0" cellspacing="0">
      <tr align="center">
        <td class="sub_nav_devider">&nbsp;</td>
         <c:choose>
	        <c:when test='${subSelected == "smfinalSchedule"}'>
        <td class="sub_nav_selected"><fmt:message key="schedule.Chart"/>&nbsp;</td>
        </c:when>
        <c:otherwise>
        <td class="sub_navigation"><a href="Tcil.htm"><fmt:message key="schedule.Chart"/>&nbsp;</a></td>
        </c:otherwise>
        </c:choose>
        <td class="sub_nav_devider">&nbsp;</td>
        <c:choose>
	        <c:when test='${subSelected == "smimportHospital"}'>
        <td class="sub_nav_selected">&nbsp;<fmt:message key="schedule.Import.Hospital"/>&nbsp;</td>
            </c:when>
            <c:otherwise>
            <td class="sub_navigation"><a href="ListHospital.htm">&nbsp;<fmt:message key="schedule.Import.Hospital"/>&nbsp;</a></td>
            </c:otherwise>
            </c:choose>
            
            
            
        <td class="sub_nav_devider">&nbsp;</td>
        <td class="subnav_slop">&nbsp;</td>
      </tr>
    </table></td>
    <td align="right"><jsp:include page="/WEB-INF/jsp/CurrentTime.jsp"/>&nbsp;</td>
  </tr>
  <tr>
    <td class="quicklinkbg" height="21" colspan="2"><fmt:message key="schedule.Welcome"/> <strong><c:out value="${login.firstName}"/>&nbsp;<c:out value="${login.lastName}"/></strong></td>
  </tr>
  <tr>
 <td  height="10" colspan="2"></td>
  </tr>
  </c:when>
      <c:when test="${login.accountType=='SPECIALISTADMIN'}">
       
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" class="mainnav_bgsml"><table border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <td class="mainnav_slop">&nbsp;</td>
        <td class="main_nav_devider">&nbsp;</td>
        <c:choose>
	        <c:when test='${mainSelected == "mmHome"}'>
        <td class="main_nav_selected">&nbsp;<fmt:message key="schedule.Home"/>&nbsp;</td>
        </c:when>
        <c:otherwise>
        <td class="main_navigation"><a href="Specialist.htm">&nbsp;<fmt:message key="schedule.Home"/>&nbsp;</a></td>
        </c:otherwise>
        </c:choose>
       <td class="main_nav_devider"></td>
        <c:choose>
	        <c:when test='${mainSelected == "mmHelp"}'>
        <td class="main_nav_selected">&nbsp;<fmt:message key="schedule.Help"/>&nbsp;</td>
        </c:when>
        <c:otherwise>
        <td class="main_navigation"><a href="#">&nbsp;<fmt:message key="schedule.Help"/>&nbsp;</a></td>
        </c:otherwise>
        </c:choose>
        <td class="main_nav_devider"></td>
        <td class="main_navigation"><a href="Logout.htm">&nbsp;<fmt:message key="schedule.Logout"/>&nbsp;</a></td>
        <td class="main_nav_devider"></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td width="67%"><img src="images/tvilogo_new.gif" width=206 height=59 alt="VitalWare 2.0"></td>
    <td width="33%" align="right" class="h2"><c:out value="${hospital.hospitalName}"/></td>
  </tr>
  <tr valign="top" class="subnav_bgsml">
    <td><table border="0" cellpadding="0" cellspacing="0">
      <tr align="center">
        <td class="sub_nav_devider">&nbsp;</td>
        <c:choose>
	        <c:when test='${subSelected == "smscheduleAvailable"}'>
        <td class="sub_nav_selected"><fmt:message key="schedule.heading.Availability"/>&nbsp;</td>
        </c:when>
        <c:otherwise>
        <td class="sub_navigation"><a href="Specialist.htm"><fmt:message key="schedule.heading.Availability"/>&nbsp;</a></td>
        </c:otherwise>
        </c:choose>
        <td class="sub_nav_devider">&nbsp;</td>
         <c:choose>
	        <c:when test='${subSelected == "smscheduleApproved"}'>
        <td class="sub_nav_selected">&nbsp;<fmt:message key="schedule.heading.Approved"/>&nbsp;</td>
        </c:when>
        <c:otherwise>
        <td class="sub_navigation"><a href="SpecialistFinalSchedule.htm">&nbsp;<fmt:message key="schedule.heading.Approved"/>&nbsp;</a></td>
        </c:otherwise>
        </c:choose>
        <td class="sub_nav_devider">&nbsp;</td>
        <td class="subnav_slop">&nbsp;</td>
      </tr>
    </table></td>
    <td align="right"><jsp:include page="/WEB-INF/jsp/CurrentTime.jsp"/>&nbsp;</td>
  </tr>
  <tr>
    <td class="quicklinkbg" height="21" colspan="2"><fmt:message key="schedule.Welcome"/> <strong><c:out value="${login.firstName}"/>&nbsp;<c:out value="${login.lastName}"/></strong></td>
  </tr>
  <tr>
 <td  height="10" colspan="2"></td>
  </tr>
        </c:when>
        <c:when test="${login.accountType=='PATIENTADMIN'}">
       
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" class="mainnav_bgsml"><table border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <td class="mainnav_slop">&nbsp;</td>
        <td class="main_nav_devider">&nbsp;</td>
         <c:choose>
	        <c:when test='${mainSelected == "mmHome"}'>
        <td class="main_nav_selected">&nbsp;<fmt:message key="schedule.Home"/>&nbsp;</td>
        </c:when>
        	<c:otherwise>
        	<td class="main_navigation"><a href="Patient.htm">&nbsp;<fmt:message key="schedule.Home"/>&nbsp;</a></td>
        	</c:otherwise>
        	</c:choose>
        <td class="main_nav_devider"></td>
        <c:choose>
	        <c:when test='${mainSelected == "mmHelp"}'>
        <td class="main_nav_selected">&nbsp;<fmt:message key="schedule.Help"/>&nbsp;</td>
        </c:when>
        <c:otherwise>
        <td class="main_navigation"><a href="#">&nbsp;<fmt:message key="schedule.Help"/>&nbsp;</a></td>
        </c:otherwise>
        </c:choose>
        <td class="main_nav_devider"></td>
        <td class="main_navigation"><a href="Logout.htm" onClick="javascript:cl();">&nbsp;<fmt:message key="schedule.Logout"/>&nbsp;</a></td>
        <td class="main_nav_devider"></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td width="67%"><img src="images/tvilogo_new.gif" width=206 height=59 alt="VitalWare 2.0"></td>
    <td width="33%" align="right" class="h2"><c:out value="${hospital.hospitalName}"/></td>
  </tr>
  <tr valign="top" class="subnav_bgsml">
    <td><table border="0" cellpadding="0" cellspacing="0">
      <tr align="center">
        <td class="sub_nav_devider">&nbsp;</td>
         <c:choose>
	        <c:when test='${subSelected == "smscheduleRequested"}'>
        <td class="sub_nav_selected"><fmt:message key="schedule.heading.Request"/>&nbsp;</td>
        </c:when>
        <c:otherwise>
        <td class="sub_navigation"><a href="Patient.htm"><fmt:message key="schedule.heading.Request"/>&nbsp;</a></td>
        </c:otherwise>
        </c:choose>
        <td class="sub_nav_devider">&nbsp;</td>
        <c:choose>
	        <c:when test='${subSelected == "smscheduleApproved"}'>
        <td class="sub_nav_selected">&nbsp;<fmt:message key="schedule.heading.Approved"/>&nbsp;</td>
        </c:when>
        <c:otherwise>
         <td class="sub_navigation"><a href="ViewPEFinalSchedule.htm">&nbsp;<fmt:message key="schedule.heading.Approved"/>&nbsp;</a></td>
         </c:otherwise>
         </c:choose>
        <td class="sub_nav_devider">&nbsp;</td>
        <td class="subnav_slop">&nbsp;</td>
      </tr>
    </table></td>
    <td align="right"><jsp:include page="/WEB-INF/jsp/CurrentTime.jsp"/>&nbsp;</td>
  </tr>
  <tr>
    <td class="quicklinkbg" height="21" colspan="2"><fmt:message key="schedule.Welcome"/> <strong><c:out value="${login.firstName}"/>&nbsp;<c:out value="${login.lastName}"/></strong></td>
  </tr>
  <tr>
 <td  height="10" colspan="2"></td>
  </tr>
        </c:when>
        </c:choose>
      
</body>
</html>
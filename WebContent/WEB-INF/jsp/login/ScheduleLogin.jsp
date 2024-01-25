<html>
<head>

<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/css.jsp"/>
<title>Pan-African e-Network Telemedicine Services</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<fmt:message key="project.context"/>/images/vw2.css" rel="stylesheet" type="text/css">

</head>

<body background="<fmt:message key="project.context"/>/images/bg_tcil.gif" LEFTMARGIN=0 TOPMARGIN=0 bottommargin=0 onload="javascript:document.all.userId.focus()">
<script langauge="Javascript">
function ViewDetail(var1)
{
	var myBars='directories=no,location=no,menubar=no,status=no';
	myBars+=',titlebar=no,toolbar=no';
	var myOptions='scrollbars=no,width=450 height=275,resizable=no, left = 362,top = 234';
	var myfeatures=myBars + ','+ myOptions;
	newwin=open(var1,'mydoc',myfeatures);
	newwin.focus();
}

</script>

<FORM method="POST">
<table align="CENTER" border="0" cellspacing="0" cellpadding="0" width="780" bgcolor="#FFFFFF" height="100%">
  <tr>
    <td><img src="<fmt:message key="project.context"/>/images/tcil_top.gif" width="780" height="110"></td>
  </tr>
  <tr>
    <td height="100%" valign="BOTTOM"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td valign="TOP"><img src="<fmt:message key="project.context"/>/images/tcil_mea.gif" width="159" height="127"></td>
        <td rowspan="2" align="CENTER" valign="MIDDLE" width="75%">
	        <table border=0 cellpadding=2 cellspacing=0 class="content">
		             <tr>
		               <td valign=middle><strong><fmt:message key="login.UserId"/></strong></td>
		               <td valign=middle><strong>:</strong></td>
			 	<td valign=bottom>
				<spring:bind path="command.userId">
				  <INPUT type="text" name="<c:out value="${status.expression}"/>"  size=14 maxlength=26 value="<c:out value="${status.value}"/>" >
				</spring:bind>
		               </td>
		             </tr>
		             <tr>
		               <td valign=middle><strong><fmt:message key="login.Password"/></strong></td>
		               <td valign=middle><strong>:</strong></td>
		               <td valign=bottom>
				<spring:bind path="command.password">
				  <INPUT type="password" name="<c:out value="${status.expression}"/>"  size=14 maxlength=30 value="<c:out value="${status.value}"/>" >
				</spring:bind>
		               </td>
		             </tr>
		             <tr>
		               <td colspan="2" valign=bottom>&nbsp;</td>
		               <td valign=bottom>
		               <INPUT type = "submit" class="loginbutton" value="<fmt:message key="button.Submit"/>"/>
		               </td>
		             </tr>
		             <tr>
		             <td colspan="3">
			        	<spring:bind path="command.*">
							<c:if test="${status.error}">
								<TABLE border="0" align="right" cellPadding=0 cellSpacing=5 class="red">
							        <TR><td>
								        	<c:forEach var="errorMessage" items="${status.errorMessages}">
								        		<li><c:out value="${errorMessage}"/></li>
								        	</c:forEach>
								     </td></TR>
								</TABLE>
							</c:if>
						</spring:bind>   
		             </td>
		             </tr>
		       </table>
		</td>
      </tr>
      <tr>
        <td valign="BOTTOM"><img src="<fmt:message key="project.context"/>/images/tcil_collage1.gif" width="85" height="115"><img src="<fmt:message key="project.context"/>/images/tcil_tcil.gif" width="134" height="115"></td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td align="RIGHT"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="187"><img src="<fmt:message key="project.context"/>/images/tcil_collage2.gif" width="187" height="130"></td>
        <td background="<fmt:message key="project.context"/>/images/tcil_location.gif" width="231" height="130" align="CENTER" valign="TOP" class="headingsub"><br>
          <br>
          <fmt:message key="project.host"/><br>
          <fmt:message key="project.host.place"/></td>
        <td valign="BOTTOM" background="<fmt:message key="project.context"/>/images/tcil_locationbg.gif" align="RIGHT"><table class="content" border=0 cellpadding=2 cellspacing=0 width="340" >
          
          
          
        </table>
          <br></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td background="<fmt:message key="project.context"/>/images/tcil_bottombg.gif"><img src="<fmt:message key="project.context"/>/images/tcil_collage3.gif" width="537" height="98"><img src="<fmt:message key="project.context"/>/images/tcil_pwy_televital.gif" width="240" height="98"></td>
  </tr>
</table>
</FORM>
</body>
</html>

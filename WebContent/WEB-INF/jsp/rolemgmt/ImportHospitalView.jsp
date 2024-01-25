<%-- * Juliet Mary *  --%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<HTML>
<HEAD>
<TITLE><fmt:message key="project.title"/></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<link href="images/vitalware_profile01.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
  <form name="ImportHospital" method="Post">
    <c:set var="subSelected" value="smimportHospital" scope="session"/>
    <jsp:include page="/WEB-INF/jsp/menu/MainMenu.jsp"/>
    </table>
    <BR>
      <spring:bind path="command.*">				
	    <c:if test="${status.error}">
		  <TABLE border="0" align=center cellPadding=0 cellSpacing=0 class="table_bg">
			<tr><TD class="topleft">&nbsp;</TD>
				<td id="bulletred">	
				   <c:forEach var="error" items="${status.errorMessages}">
				    <li class="red bold"><c:out value="${error}"/></li>
				   </c:forEach>							
				 </td>
				 <TD class="topright">&nbsp;</TD>
			 </tr>
			  <TR>
	            <TD class="bottomleft" colspan="2"></TD>
	            <TD class="bottomright"></TD>
	          </TR>
		 </table>
	  </c:if>
 </spring:bind>

<form name="IHForm" method="Post">
   <br>
    <TABLE width="70%" border="0" align=center cellPadding=0 cellSpacing=0  class="table_bg">
        <TR>
          <TD class="topleft">&nbsp;<strong><fmt:message key="schedule.Import.Hospital"/></strong></TD>
          <TD class="topright"></TD>
        </TR>
        <TR>
          <TD colspan="2">
	          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_edit">
	            <tr>
	              <td width="24%" align='right' class="tablecontent1"><font color='#FF0000'>*</font><fmt:message key="ImportHospital.Id"/></td>
	              <td width=1%  align=center><strong>:</strong></td>
	              <td width="25%" class="tablecontent1">
	              <spring:bind path="command.hospitalId">
	              	<input type=text name='<c:out value="${status.expression}"/>'  value="<c:out value="${status.value}"/>" size='25'> 
	              </spring:bind>
	              <br>
	              </td>
	              <td>
	              <fmt:message key="HospitalId.example"/>
	              </td>
	            </tr>
	            <tr>
	              <td align='right' class="tablecontent1"><font color='#FF0000'>*</font><fmt:message key="ImportHospital.ipaddress"/></td>
	              <td align=center><strong>:</strong></td>
	              <td class="tablecontent1">
	              <spring:bind path="command.ipAddress">
	                <input type=text name='<c:out value="${status.expression}"/>' value="<c:out value="${status.value}"/>" size='25' OnKeyPress="captureReturnKey(event)">
	              </spring:bind> <br>
	              </td>
	              <td>
	               <fmt:message key="Ipaddress.example"/>
	              </td>
	            </tr>
	            <tr>
	              <td align='right' class="tablecontent1"><font color='#FF0000'>*</font><fmt:message key="ImportHospital.type"/></td>
	              <td align=center><strong>:</strong></td>
	              <td class="tablecontent1">
	               <spring:bind path="command.hospitalType">
	       			  <select tabindex="6" name="<c:out value="${status.expression}"/>">
	          		    <option value='' <c:if test="${status.value == ''}">selected</c:if>>--Select--</option>
	          		    <option value='SPECIALISTEND'<c:if test="${status.value == 'SPECIALISTEND'}">selected</c:if>>Specialist</option>
	          		    <option value='PATIENTEND'<c:if test="${status.value == 'REMOTEEND'}">selected</c:if>>Remote</option>
	          		  </select><c:if test="${status.error}"> <span class="warning">&nbsp;</span></c:if>
	    		   </spring:bind> <br>
	              </td>
	              <td>
	               <fmt:message key="Ipaddress.hospitaltype"/>
	              </td>
	            </tr>
	            <tr>
	            </tr>
				<tr>
					<td colspan="4" class="tablecontent1 center"><input type="submit" class="button" value="<fmt:message key="button.Submit"/>">  &nbsp; <input name="sumbit" type="button" OnClick="javascript:document.location.href='ListHospital.htm?';" class="button" value=" <fmt:message key="button.Back"/> "/> </td>
				</tr>
	          </table>
          </TD>
        </TR>
        <TR>
          <TD class="bottomleft"></TD>
          <TD class="bottomright"></TD>
        </TR>
	</TABLE>
</form>
</body>
</html>
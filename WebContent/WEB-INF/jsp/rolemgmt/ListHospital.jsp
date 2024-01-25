<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<HTML>
<HEAD>
<TITLE><fmt:message key="project.title"/></TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<link href="images/vitalware_profile01.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

</script>

</HEAD>


<BODY>

<form name="ScheduleChart" method="Post">
 <c:set var="subSelected" value="smimportHospital" scope="session"/>
  <jsp:include page="/WEB-INF/jsp/menu/MainMenu.jsp"/>
  
</table>
<br>
<TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0>
    <TR>
        <TD>
        	<input type="button" value="<fmt:message key="button.ImportHospital"/>" class="button" OnClick="document.location.href='ImportHospital.htm'">
        </TD>
    </TR>
</TABLE>
<BR>

<c:set var="hospitalCount" value="0" scope="page"/>
<c:forEach var="hospital" items="${listHolder.pageList}">
	<c:set var="hospitalCount" value="${hospitalCount+1}" scope="page"/>
</c:forEach>

<c:choose>
	<c:when test="${hospitalCount > 0}">	
		<script language="javascript">
		function displayPage(page)
		{
			document.location.href="ListHospital.htm?page="+page;
		}
		</script>
		<TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0  class="table_bg">
		    <TR>
		        <TD width="40%" class="topleft">&nbsp;<strong>List of Hospitals</strong></td>
				<TD width="30%">| &nbsp;<fmt:message key="Showing"/>&nbsp;<c:out value="${listHolder.page + 1}"/> / <c:out value="${listHolder.pageCount}"/>&nbsp;|&nbsp;</td>
		        <TD width="28%" class="topright">
		        	<c:choose>
						<c:when test="${!listHolder.firstPage}">
					 		<a href="javascript:displayPage('first')"><fmt:message key="search.result.First"/></a>
					 	</c:when>
					 	<c:otherwise>
					 		<fmt:message key="search.result.First"/>
						</c:otherwise>
					</c:choose>
						&nbsp;|&nbsp;
					<c:choose>
						<c:when test="${!listHolder.firstPage}">
							<a href="javascript:displayPage('previous')"><fmt:message key="search.result.Previous"/></a>
						</c:when>
						<c:otherwise>
							<fmt:message key="search.result.Previous"/>
						</c:otherwise>
					</c:choose>
						&nbsp;|&nbsp;
					<c:choose>
						<c:when test="${!listHolder.lastPage}">
					 	<a href="javascript:displayPage('next')"><fmt:message key="search.result.Next"/></a>
						</c:when>
						<c:otherwise>
							<fmt:message key="search.result.Next"/>
						</c:otherwise>
					</c:choose>
					   &nbsp;|&nbsp;
					<c:choose>
						<c:when test="${!listHolder.lastPage}">
					   	<a href="javascript:displayPage('last')"><fmt:message key="search.result.Last"/></a>
		    		</c:when>
		    		<c:otherwise>
		    			<fmt:message key="search.result.Last"/>
			    		</c:otherwise>
		    		</c:choose>
				   &nbsp;</td>
		    </TR>
		    <TR>
		        <TD colspan="3">
		            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_edit">
					<tr class="read">
						<th width=30%>&nbsp;<strong><fmt:message key="hospital.List.Hospital"/></strong></th>
		        		<th width=30% class="center">&nbsp;<strong><fmt:message key="hospital.List.ContactDetails"/></strong></th>
		        		<th width=30% class="center"><strong><fmt:message key="ImportHospital.type"/></strong></th>
		        		<th width=10% class="center"><strong><fmt:message key="link.Edit"/></strong></th>
					</tr>
					<c:forEach var="hospital" items="${listHolder.pageList}">
			        <tr>
		                <td valign=top >
		                	&nbsp;<c:out value="${hospital.hospitalName}"/>
		                	
		                	<c:if test="${hospital.address.addressLine1 != ''}">
		                		<BR>&nbsp;<c:out value="${hospital.address.addressLine1}"/>
		                	</c:if>  

		                	<c:if test="${hospital.address.addressLine2 != ''}">
		                		<BR>&nbsp;<c:out value="${hospital.address.addressLine2}"/>
		                	</c:if> 		                	 	                

		                	<c:if test="${hospital.address.city != ''}">
		                		<BR>&nbsp;<c:out value="${hospital.address.city}"/>
		                	</c:if>

		                	<c:if test="${hospital.address.state != ''}">
		                		<BR>&nbsp;<c:out value="${hospital.address.state}"/>
		                	</c:if>
		                	
		                	<c:if test="${hospital.address.country != ''}">
		                		&nbsp;<c:out value="${hospital.address.country}"/>
		                	</c:if>
		                </td>
		                <td valign=top class=left>
																
							<c:if test="${hospital.address.contactNumber != ''}">
		                		&nbsp;<fmt:message key="address.ContactNumber"/> : <c:out value="${hospital.address.contactNumber}"/>
		                	</c:if>
		                	
		                	<c:if test="${hospital.address.faxNumber != ''}">
		                		<BR>&nbsp;<fmt:message key="address.Fax"/> : <c:out value="${hospital.address.faxNumber}"/>
		                	</c:if>

						  <c:if test="${hospital.address.email != ''}">
		                		<BR>&nbsp;<fmt:message key="address.Email"/> : <c:out value="${hospital.address.email}"/>
		                	</c:if>

							<c:if test="${hospital.url != ''}">
		                		<BR>&nbsp;<fmt:message key="hospital.Url"/> : <c:out value="${hospital.url}"/>
		                	</c:if>							
		                </td>
			               
		                <td class=center>	
							<c:if test="${hospital.hospitalType == 'SPECIALISTEND'}">SPECIALIST</c:if>	
		                	<c:if test="${hospital.hospitalType == 'PATIENTEND'}">REMOTE</c:if>							
		                </td>
		                <td class=center>&nbsp;<a href="EditHospital.htm?hId=<c:out value="${hospital.hospitalId}"/>"><fmt:message key="link.Edit"/></a></td>
			        </tr>			
					</c:forEach>
	            </table>
	        </TD>
	    </TR>
	<TR>
	    <TD class="bottomleft"><img src="images/spacer.gif" width="1" height="4"></TD>
	    <TD class="bottomright"></TD>
  	</TR>
	</TABLE>
	</c:when>
</c:choose>
</form>
</body>
</html>
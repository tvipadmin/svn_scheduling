<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<HTML>
<HEAD>
<TITLE><fmt:message key="project.title"/></TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<link href="images/vitalware_profile01.css" rel="stylesheet" type="text/css">


</HEAD>

<form name="AddScheduleSuccess" method="Post">
 <jsp:include page="/WEB-INF/jsp/menu/MainMenu.jsp"/>
  
</table>

<table width="100%">
<tr>
<td class="H3 center"><fmt:message key="schedule.Add.Success"/></td>
</tr>
<tr>
<td class="center"><br><input name="sumbit" type="button" OnClick="javascript:document.location.href='PEAddSchedule.htm';" class="button" value=" <fmt:message key="button.Ok"/> "/>
</td>
</tr>
</table>
</form>
</HTML>
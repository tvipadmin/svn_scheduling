<%--  
	* $Id: FrameScript.jsp,v 1.2 2007/01/31 06:03:20 hcvs Exp $ 
    * Nayak
--%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<Script language="javascript">
function ExitEMR(topLocation)
{
	document.frames['topFrame'].location=topLocation;
	moveFrame(100,-10)
}
function ViewEMR(topLocation,botLocation)
{
	document.frames['topFrame'].location=topLocation;
	moveFrame(1,10);
	document.frames['botFrame'].location=botLocation;
}
function ViewEMR1(topLocation,botLocation)
{
	document.frames['topFrame'].location=topLocation;
}
function moveFrame(startFrom,step)
{
	var the_object = document.all('mainFrameSet');
	startFrom = startFrom + step;
	the_object.rows= startFrom+'%,*'

	if(step>0)
	{
	 	if(startFrom<100) { window.setTimeout("moveFrame("+startFrom+","+step+")",1); }
	}
	else
	{
		if(startFrom>0) { window.setTimeout("moveFrame("+startFrom+","+step+")",1); }
	}
}
</SCRIPT>


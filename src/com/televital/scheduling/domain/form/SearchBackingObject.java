/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.domain.form;

import java.util.List;
import org.springframework.beans.support.PagedListHolder;
import com.televital.scheduling.domain.FinalSchedule;
import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.PESchedule;
import com.televital.scheduling.domain.SSHSchedule;


public class SearchBackingObject {
	
	private FinalSchedule    finalschedule;
	private Hospital         hospital;
	private List             patientId;
	private List             sshId;
	private List             finalscheduleList;
	private List             dayscheduleList;
	private List             dayselectedList;
	private String           hospitalSSHName;
	private String           hospitalPEName;
	private String           errormessage;
	private String           checkday="selected";
	private PagedListHolder  pageListHolder;
	private PagedListHolder  pageListDaySelectedHolder;
	private String           firstName;
	private String           lastName;
	private List             sshList;
	private String           days;
	private int              countright;
	private int              countleft;
	private SSHSchedule      sshschedule;
	private PESchedule       peschedule;
		
	public void setHospitalSSHName(String hospitalSSHName)
	{
		this.hospitalSSHName=hospitalSSHName;
	}
	
	public String getHospitalSSHName()
	{
		return this.hospitalSSHName;
	}
	
	public void setHospitalPEName(String hospitalPEName)
	{
		this.hospitalPEName=hospitalPEName;
	}
	
	public String getHospitalPEName()
	{
		return this.hospitalPEName;
	}
	
	public void setFinalschedule(FinalSchedule finalschedule)
	{
		this.finalschedule=finalschedule;
	}
		
	public void setSshschedule(SSHSchedule sshschedule)
	{
		this.sshschedule=sshschedule;
	}
	
	public void setPeschedule(PESchedule peschedule)
	{
		this.peschedule=peschedule;
	}
	
	public void setHospital(Hospital hospital)
	{
		this.hospital=hospital;
	}
	
	public void setPatientId(List patientId)
	{
		this.patientId=patientId;
	}
	
	public void setSshId(List sshId)
	{
		this.sshId=sshId;
	}
	
	public void setDayscheduleList(List dayschedulelist)
	{
		this.dayscheduleList=dayschedulelist;
	}
	
	public void setFinalscheduleList(List finalschedulelist)
	{
		this.finalscheduleList=finalschedulelist;
	}
	
	public void setErrormessage(String errormessage)
	{
		this.errormessage=errormessage;
	}
	
	public void setDayselectedList(List dayselectedList)
	{
		 this.dayselectedList = dayselectedList;
	}
	
	public void setCheckday(String checkday)
	{
		 this.checkday = checkday;
	}
	
	public void setPageListHolder(PagedListHolder pageListHolder)
	{
		this.pageListHolder = pageListHolder;
	}
	
	public void setPageListDaySelectedHolder(PagedListHolder pageListDaySelectedHolder)
	{
		this.pageListDaySelectedHolder = pageListDaySelectedHolder;
	}
	
	public void setSshList(List sshList) 
	{
		this.sshList = sshList;
	}
	
	public void setDays(String days)
	{
		this.days = days;
	}
	
	public void setCountright(int countright)
	{
		this.countright = countright;
	}
	
	public void setCountleft(int countleft)
	{
		this.countleft = countleft;
	}
	
	public void setFirstName(String firstname)
	{
		this.firstName = firstname;
	}
	
	public void setLastName(String lastname)
	{
		this.lastName = lastname;
	}
	
	public FinalSchedule getFinalschedule()
	{
		return this.finalschedule;
	}
	
	public SSHSchedule getSshschedule()
	{
		return this.sshschedule;
	}
	
	public PESchedule getPeschedule()
	{
		return this.peschedule;
	}
	
	public List getPatientId()
	{
		return this.patientId;
	}
	
	public List getSshId()
	{
		return this.sshId;
	}
	
	public List getDayscheduleList()
	{
		return this.dayscheduleList;
	}
	
	public List getFinalscheduleList()
	{
		return this.finalscheduleList;
	}
	
	public Hospital getHospital()
	{
		return this.hospital;
	}
	
	public String getErrormessage()
	{
		return this.errormessage;
	}
	
	public List getDayselectedList()
	{
		return this.dayselectedList;
	}
	
	public String getCheckday()
	{
		 return this.checkday;
	}
	
	public PagedListHolder getPageListHolder()
	{
		return pageListHolder;
	}
	
	public PagedListHolder getPageListDaySelectedHolder() 
	{
		return this.pageListDaySelectedHolder ;
	}
	
	public List getSshList() 
	{
		return this.sshList;
	}

	public String getDays()
	{
		return this.days;
	} 
	
	public int getCountright()
	{
		return this.countright;
	}
	
	public int getCountleft()
	{
		return this.countleft;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
  }

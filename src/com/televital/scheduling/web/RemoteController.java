/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.web;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;
import org.apache.log4j.Logger;

import com.televital.scheduling.domain.FinalSchedule;
import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.Login;
import com.televital.scheduling.domain.PESchedule;
import com.televital.scheduling.domain.form.SearchBackingObject;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.utils.SchedulingConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;

public class RemoteController extends AbstractScheduleController implements InitializingBean{
	
	private static final Logger  log4log = Logger.getLogger(RemoteController.class);
	private Integer dispalyPageSize ;
	
	public RemoteController() {
		setSessionForm(false);
	}
	
	public void setDispalyPageSize(Integer dispalyPageSize)
	{
		this.dispalyPageSize = dispalyPageSize;
	}

	public Integer getDispalyPageSize()
	{
	    return dispalyPageSize;
	}
		
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		
		SearchBackingObject sBO = new SearchBackingObject();
		PESchedule peschedule = new PESchedule();
		//FinalSchedule finalschedule = new FinalSchedule();
		Hospital sshHospital = new Hospital();
		Hospital peHospital = new Hospital();
		List sshidList = null;
				
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_REMOTE,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
		
		try
		{
			peschedule.setSshHospital(sshHospital);
			peschedule.setPeHospital(peHospital);
			sBO.setErrormessage("NewP");
			sBO.setPeschedule(peschedule);
			sBO.setCheckday("notselected");
			//sBO.setFinalschedule(finalschedule);
			try
			  {
				//feteching patient objects from the database
				sshidList = this.getScheduleFacade().loadSSHId();
				log4log.info("SSH ID List :  "+ sshidList);
				sBO.setSshId(sshidList);
			  }
			catch(Exception e)
			  {
				log4log.error("Error while getting SSH from database due to : "+e.toString());
			  }
			
			
			try
			{
				
				if(request.getParameter("search") == null)
				{
				  String pehospitalid=((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getHospitalId();
				  String Sshid="",Day="";
				  List searchList = this.getScheduleFacade().findPatientEndSchedule(Sshid,pehospitalid,Day);
				  log4log.info("Size : "+searchList.size());
					if(searchList.size() > 0)
				     {
					   log4log.info("Serach list greater than zero...");
					   sBO.setCheckday("notselected");
					   PagedListHolder finalschedulePageList = new PagedListHolder(searchList);
			           finalschedulePageList.setPageSize(this.getDispalyPageSize());
	                   //uploading to session
					   request.getSession().setAttribute(SchedulingConstants.SESSION_VAR_PAGE_LIST_HOLDER,finalschedulePageList);
	   				   sBO.setFinalscheduleList(searchList);
					   sBO.setPageListHolder(finalschedulePageList);
					   return sBO;
					}
				else
				 {
					   log4log.info("Does Not Exist....");
					   sBO.setErrormessage("DNE");
					   return sBO;
				 }
			 
			  }
			  else
				{
					//retreving patient list from session, and parameter to go forward or backward
					
					String page = request.getParameter("page");
					sBO.setCheckday("notselected");
					
					PagedListHolder casePageList = (PagedListHolder)request.getSession().getAttribute(SchedulingConstants.SESSION_VAR_PAGE_LIST_HOLDER);
					log4log.info(casePageList.toString());
					
				    	if(page.equals(SchedulingConstants.DISPLAY_PAGE_FIRST))
						{
							log4log.info("first");
							casePageList.setPage(0);
						}
						if(page.equals(SchedulingConstants.DISPLAY_PAGE_NEXT))
						{
							log4log.info("next");
							casePageList.nextPage();
						}
						if(page.equals(SchedulingConstants.DISPLAY_PAGE_PREVIOUS))
						{
							log4log.info("previous");
							casePageList.previousPage();
						}
						if(page.equals(SchedulingConstants.DISPLAY_PAGE_LAST))
						{
							//setting pagenumber as last page
							log4log.info("last");
							casePageList.setPage(casePageList.getPageCount()-1);
						}
					
					//setting pagelist to the backing object to maintain same command object before and after submission of page
					 sBO.setPageListHolder(casePageList);
					//returning backing object instead of paged list  
					 return sBO;
			   }	
			}
			catch(Exception e)
			{
				log4log.error("Later list : "+e);
				return sBO;
			}
			
			
			
			
		}
		catch(Exception e)
		{
			log4log.error("Error while fetching the patient objects from the data base due to :"+e.toString());
			
		}
		
      return sBO;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException error) throws Exception 
	{
		// TODO Auto-generated method stub
		
		SearchBackingObject sBO = (SearchBackingObject)command;
		sBO.setCheckday("selectedd");
		log4log.info("inside backing object");
		PESchedule peschedule;
		try
		{
			//finalschedule =sBO.getFinalschedule();
			peschedule = sBO.getPeschedule();
			List searchList=null;
			PagedListHolder finalschedulePageList=null;
			if(request.getParameter("search") == null)
			{
			
			String pehospitalid=((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getHospitalId();
			log4log.info("SSH ID: "+peschedule.getSshHospital().getHospitalId()+"PE ID: "+pehospitalid+"Day : "+peschedule.getDay());
			
			String buttonname=request.getParameter("buttonsd");
			
			if(buttonname.equals("Search"))
			{
			  searchList = this.getScheduleFacade().findPatientEndSchedule(peschedule.getSshHospital().getHospitalId(),pehospitalid,peschedule.getDay());
			  log4log.info("Size : "+searchList.size() +" "+peschedule.getSshHospital().getHospitalId().equals("")+" "+pehospitalid+" "+peschedule.getDay().equals(""));
				if(searchList.size() > 0)
			     {
				   log4log.info("Search list greater than Zero...");
				    if(peschedule.getDay().equals(""))
				       {
					   sBO.setCheckday("notselected");
				       }
				       else
				       {
				    	sBO.setCheckday("selected");
				       }
				   finalschedulePageList = new PagedListHolder(searchList);
		           finalschedulePageList.setPageSize(this.getDispalyPageSize());
                   			 //uploading to session
				   request.getSession().setAttribute(SchedulingConstants.SESSION_VAR_PAGE_LIST_HOLDER,finalschedulePageList);
		   				   /* hospital = this.getScheduleFacade().loadHospital(finalschedule.getSshHospitalId());
							  String sshHospitalname = hospital.getHospitalName();
							  log4log.info("SSHHospital Name : "+sshHospitalname);
							  hospital = this.getScheduleFacade().loadHospital(finalschedule.getPeHospitalId());
							  String peHospitalname=hospital.getHospitalName(); 
							  log4log.info("PEHospital Name : "+peHospitalname); */
		        
				   sBO.setFinalscheduleList(searchList);
							   //sBO.setHospitalSSHName(sshHospitalname);
							   //sBO.setHospitalPEName(peHospitalname);
				   sBO.setPageListHolder(finalschedulePageList);
				   return new ModelAndView("PatientEndView","command",sBO);
				}
			else
			 {
				   log4log.info("Does Not Exist....");
				   sBO.setErrormessage("DNE");
				   return new ModelAndView("PatientEndView","command",sBO);
			 }
			}
			else if(buttonname.equals("Delete"))
			{
			       String[] delFinalScheduleList = (String[]) request.getParameterValues("chkDelFinalSchedule");
			       log4log.info("sBO.getPeschedule().getScheduleId()"+sBO.getPeschedule().getScheduleId());
			       //peschedule.setScheduleId(sBO.getPeschedule().getScheduleId());
		           if(delFinalScheduleList != null)
				    {	
				      for(int i=0; i < delFinalScheduleList.length; i++)
					   {
					     log4log.info("Inside Delete Option : " + delFinalScheduleList[i] );
					     PESchedule pes = (PESchedule)this.getScheduleFacade().loadPESchedule(delFinalScheduleList[i]).get(0);
					     //peschedule.setScheduleId(delFinalScheduleList[i]);
					     this.getScheduleFacade().deletePatientEndSchedule(pes);
					   }	
				    }
			  	 log4log.info("Inside Delete Option");
			 	 sBO.setErrormessage("Deleted");
			 	 
			 	  searchList = this.getScheduleFacade().findPatientEndSchedule(peschedule.getSshHospital().getHospitalId(),pehospitalid,peschedule.getDay());
				  log4log.info("Size : "+searchList.size() +" "+peschedule.getSshHospital().getHospitalId().equals("")+" "+pehospitalid+" "+peschedule.getDay().equals(""));
					if(searchList.size() > 0)
				     {
					   log4log.info("All Three Data Selected...");
					    if(peschedule.getDay().equals(""))
					      {
						   sBO.setCheckday("notselected");
					       }
					    else
					    {
					    	sBO.setCheckday("selected");
					    }
					   finalschedulePageList = new PagedListHolder(searchList);
			           finalschedulePageList.setPageSize(this.getDispalyPageSize());
	                   //uploading to session
					   request.getSession().setAttribute(SchedulingConstants.SESSION_VAR_PAGE_LIST_HOLDER,finalschedulePageList);
	   				   sBO.setFinalscheduleList(searchList);
					   //sBO.setHospitalSSHName(sshHospitalname);
					   //sBO.setHospitalPEName(peHospitalname);
					   sBO.setPageListHolder(finalschedulePageList);
					   return new ModelAndView("PatientEndView","command",sBO);
					}
				else
				 {
					   log4log.info("Does Not Exist....");
					   sBO.setErrormessage("DNE");
					   return new ModelAndView("PatientEndView","command",sBO);
				 }
				 
			 }
			 else
			  {
				sBO.setErrormessage("NotDeleted");
				return new ModelAndView("PatientEndView","command",sBO);
			  }
		  }
		  else
			{
				//retreving patient list from session, and parameter to go forward or backward
				log4log.info("inside search list else");
				String page = request.getParameter("page");
				if(peschedule.getDay().equals(""))
				{
					sBO.setCheckday("notselected");
				}
				else
				{
					sBO.setCheckday("selected");
				}
				
				PagedListHolder casePageList = (PagedListHolder)request.getSession().getAttribute(SchedulingConstants.SESSION_VAR_PAGE_LIST_HOLDER);
				log4log.info(casePageList.toString());
				
			    	if(page.equals(SchedulingConstants.DISPLAY_PAGE_FIRST))
					{
						log4log.info("first");
						casePageList.setPage(0);
					}
					if(page.equals(SchedulingConstants.DISPLAY_PAGE_NEXT))
					{
						log4log.info("next");
						casePageList.nextPage();
					}
					if(page.equals(SchedulingConstants.DISPLAY_PAGE_PREVIOUS))
					{
						log4log.info("previous");
						casePageList.previousPage();
					}
					if(page.equals(SchedulingConstants.DISPLAY_PAGE_LAST))
					{
						//setting pagenumber as last page
						log4log.info("last");
						casePageList.setPage(casePageList.getPageCount()-1);
					}
				
				//setting pagelist to the backing object to maintain same command object before and after submission of page
				 sBO.setPageListHolder(casePageList);
				//returning backing object instead of paged list  
				 return new ModelAndView("PatientEndView","command",sBO);
		   }	
		}
		catch(Exception e)
		{
			log4log.error("Later list : "+e);
			return new ModelAndView("PatientEndView","command",sBO);
		}
	}
}

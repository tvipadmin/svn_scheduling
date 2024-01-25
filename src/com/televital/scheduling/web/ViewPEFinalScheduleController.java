/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.televital.scheduling.domain.FinalSchedule;
import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.Login;
import com.televital.scheduling.domain.form.SearchBackingObject;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.utils.SchedulingConstants;

public class ViewPEFinalScheduleController extends AbstractScheduleController implements InitializingBean{
	
	private static final Logger  log4log = Logger.getLogger(ViewPEFinalScheduleController.class);
	private Integer dispalyPageSize ;
	
	public ViewPEFinalScheduleController() {
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
		log4log.info("Inside ViewPE... formBackingObject()");
		SearchBackingObject sBO = new SearchBackingObject();
		FinalSchedule finalschedule = new FinalSchedule();
		Hospital sshHospital = new Hospital();
		Hospital peHospital = new Hospital();
		List patientidList = null;
		List sshidList = null;
				
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_REMOTE,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
		
		try
		{
			finalschedule.setSshHospital(sshHospital);
			finalschedule.setPeHospital(peHospital);
			sBO.setErrormessage("NewP");
			sBO.setFinalschedule(finalschedule);
			sBO.setCheckday("notselected");

			try
			{
				//feteching patient objects from the database
				patientidList = this.getScheduleFacade().loadPatientId();
				log4log.info("Patient ID List :  "+ patientidList);
				sBO.setPatientId(patientidList);
			}
			catch(Exception e)
			{
				log4log.error("Error while getting patients from database due to : "+e.toString());
				
			}
			
		   try
			{
			     //feteching patient objects from the database
				 sshidList = this.getScheduleFacade().loadSSHId();
				 log4log.info("SSH ID List :  "+ sshidList);
				 sBO.setSshId(sshidList);
			}
		  catch(Exception e)
			{
			    log4log.error("Error while getting Specialists from database due to : "+e.toString());
					
			}
		  
			try
			{
			
				if(request.getParameter("search") == null)
				{
				    log4log.info("If search is equal to null");
				    String pehospitalid=((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getHospitalId();
				    log4log.info("Got Hospital ID");
				    String Sshid="",Day="";
			 		List searchList = this.getScheduleFacade().findSchedulePEView(Sshid,pehospitalid,Day);
	                log4log.info("Size : "+searchList.size());
				
				    if(searchList.size() > 0)
				    {
					   log4log.info("searchList is greater than zero");
					   sBO.setCheckday("notselected");
					   PagedListHolder finalschedulePageList = new PagedListHolder(searchList);
			           finalschedulePageList.setPageSize(this.getDispalyPageSize());
	                  //	uploading to session
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
					log4log.info("If search is not equal to null");
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
					
						sBO.setPageListHolder(casePageList);  //setting pagelist to the backing object to maintain same command object before and after submission of page
						return sBO;  //returning backing object instead of paged list  
				}	
			}
			catch(Exception e)
			{
				log4log.error("Error Caused due to : "+e);
				return sBO;
			}
		  
		}
		catch(Exception e)
		{
			log4log.error("Error Caused due to :"+e.toString());
			
		}
		
      return sBO;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException error) throws Exception 
	 {
		// TODO Auto-generated method stub
		
		SearchBackingObject sBO = (SearchBackingObject)command;
		sBO.setCheckday("selectedd");
		log4log.info("Inside View PEFinal Schedule");
		Hospital hospital ;
		List dayList =new ArrayList();
		FinalSchedule finalschedule;
		
		try
		{
			finalschedule =sBO.getFinalschedule();
			if(request.getParameter("search") == null)
			{
				String pehospitalid=((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getHospitalId();
				log4log.info("SSH ID: "+finalschedule.getSshHospital().getHospitalId()+"PE ID: "+pehospitalid+"Day : "+finalschedule.getDay());
				String sday=finalschedule.getDay();
				String buttonname=request.getParameter("buttonsd");
			
			if(buttonname.equals("Search"))
			{
					List searchList = this.getScheduleFacade().findSchedulePEView(finalschedule.getSshHospital().getHospitalId(),pehospitalid,finalschedule.getDay());
	                log4log.info("Size : "+searchList.size() +" "+finalschedule.getSshHospital().getHospitalId().equals("")+" "+finalschedule.getDay().equals(""));
			
			    if(searchList.size() > 0)
			    {
				   log4log.info("If searchlist is greater than zero");
				       if(finalschedule.getDay().equals(""))
				       {
					    sBO.setCheckday("notselected");
				       }
				       else
				       {
				    	   sBO.setCheckday("selected");
				       }
				   PagedListHolder finalschedulePageList = new PagedListHolder(searchList);
		           finalschedulePageList.setPageSize(this.getDispalyPageSize());
		           			//	uploading to session
				   request.getSession().setAttribute(SchedulingConstants.SESSION_VAR_PAGE_LIST_HOLDER,finalschedulePageList);
		        		        
									  /* hospital = this.getScheduleFacade().loadHospital(finalschedule.getSshHospitalId());
								         String sshHospitalname = hospital.getHospitalName();
									     log4log.info("SSHHospital Name : "+sshHospitalname);
									     hospital = this.getScheduleFacade().loadHospital(finalschedule.getPeHospitalId());
									     String peHospitalname=hospital.getHospitalName(); 
									     log4log.info("PEHospital Name : "+peHospitalname);*/
				   sBO.setFinalscheduleList(searchList);
										     //sBO.setHospitalSSHName(sshHospitalname);
										     //sBO.setHospitalPEName(peHospitalname);
				   sBO.setPageListHolder(finalschedulePageList);
				   return new ModelAndView("PEFinalScheduleView","command",sBO);
		 		}
		      else
			   {
					log4log.info("If searchlist is less than zero");
					sBO.setErrormessage("DNE");
					return new ModelAndView("PEFinalScheduleView","command",sBO);
			   }
		      }
			else
			{
				return new ModelAndView("PEFinalScheduleView","command",sBO);
			}
			}
			else
			{
				//retreving patient list from session, and parameter to go forward or backward
				log4log.info("if search list not equal to null");
				String page = request.getParameter("page");
				if(finalschedule.getDay().equals(""))
				{
					sBO.setCheckday("notselected");
				}
				else
				{
					sBO.setCheckday("selected");
				}
				
				PagedListHolder casePageList = (PagedListHolder)request.getSession().getAttribute(SchedulingConstants.SESSION_VAR_PAGE_LIST_HOLDER);
				log4log.info("casePageList : "+casePageList.toString());
				
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
				
					sBO.setPageListHolder(casePageList);   //setting pagelist to the backing object to maintain same command object before and after submission of page
					
				//returning backing object instead of paged list  
				return new ModelAndView("PEFinalScheduleView","command",sBO);
			}	
		}
		catch(Exception e)
		{
			log4log.error("Error Caused due to :  "+e);
			return new ModelAndView("PEFinalScheduleView","command",sBO);
		}
	}
}

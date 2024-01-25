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
import com.televital.scheduling.domain.form.SearchBackingObject;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.utils.SchedulingConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;

public class ScheduleController extends AbstractScheduleController implements InitializingBean{
	
	private static final Logger  log4log = Logger.getLogger(ScheduleController.class);
	private Integer dispalyPageSize ;
	
	public ScheduleController() {
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
		FinalSchedule finalschedule = new FinalSchedule();
		Hospital sshHospital = new Hospital();
		Hospital peHospital = new Hospital();
		
		List patientidList = null;
		List sshidList = null;
				
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_ADMIN,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
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
					log4log.info("Patient ID List form Banking Object:  "+ patientidList);
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
					log4log.error("Error while getting SSh from database due to : "+e.toString());
					
				}
				
				
				 try
				  {
					 
					  if(request.getParameter("search") == null)
					   {
					     
					     
					     String buttonname=request.getParameter("buttonsd");
					     String Sshid="",PeId="",Day="";
					     List searchList = this.getScheduleFacade().findSchedule(Sshid,PeId,Day);
					  	 log4log.info("Size : "+searchList.size());
					  	  if(searchList.size() > 0)
					       {
						      log4log.info("Search List grester than zero...");
						      sBO.setCheckday("notselected");
						      PagedListHolder finalschedulePageList = new PagedListHolder(searchList);
				              finalschedulePageList.setPageSize(this.getDispalyPageSize());
		                       //uploading to session
						       request.getSession().setAttribute(SchedulingConstants.SESSION_VAR_PAGE_LIST_HOLDER,finalschedulePageList);
				               sBO.setFinalscheduleList(searchList);
						       //sBO.setHospitalSSHName(sshHospitalname);
						       //sBO.setHospitalPEName(peHospitalname);
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
						log4log.info("if search is not equal to null");
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
					log4log.error("Error due to : "+e.toString());
					return sBO;
				}		

				
		}
		catch(Exception e)
		{
			log4log.error("Error due to :"+e.toString());
			
		}
		
      return sBO;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException error) throws Exception 
	{
		  // TODO Auto-generated method stub
		  SearchBackingObject sBO = (SearchBackingObject)command;
		  FinalSchedule finalschedule;

	   	  try
		  {
			  finalschedule =sBO.getFinalschedule();
			  List searchList = null;
			  PagedListHolder finalschedulePageList =null;
			 
			  sBO.setCheckday("selectedd");
			  if(request.getParameter("search") == null)
			   {
			     
		  		 log4log.info("SSH ID: "+finalschedule.getSshHospital().getHospitalId()+"PE ID: "+finalschedule.getPeHospital().getHospitalId()+"Day : "+finalschedule.getDay());
			     String buttonname=request.getParameter("buttonsd");
			   if(buttonname.equals("Search"))
			     {
				   
			      searchList = this.getScheduleFacade().findSchedule(finalschedule.getSshHospital().getHospitalId(),finalschedule.getPeHospital().getHospitalId(),finalschedule.getDay());
			  	  log4log.info("Size : "+searchList.size() +" "+finalschedule.getSshHospital().getHospitalId().equals("")+" "+finalschedule.getPeHospital().getHospitalId().equals("")+" "+finalschedule.getDay().equals(""));
			  	  if(searchList.size() > 0)
			       {
				      log4log.info("search list greater than zero...");
				      if(finalschedule.getDay().equals(""))
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
				       return new ModelAndView("ScheduleView","command",sBO);
				   }
			       else
			         {
				  	   log4log.info("Does Not Exist....");
					   sBO.setErrormessage("DNE");
					   return new ModelAndView("ScheduleView","command",sBO);
					 }
		   	  }
			  else if(buttonname.equals("Delete"))
			   {							
				  String[] delFinalScheduleList = (String[]) request.getParameterValues("chkDelFinalSchedule");
				  log4log.info("sBO.getFinalschedule().getScheduleId()"+sBO.getFinalschedule().getScheduleId());
				  finalschedule.setScheduleId(sBO.getFinalschedule().getScheduleId());
			        if(delFinalScheduleList != null)
				     {	
					   for(int i=0; i < delFinalScheduleList.length; i++)
					    {
						   log4log.info("Inside Delete Option : " + delFinalScheduleList[i] );
							  FinalSchedule fs = this.getScheduleFacade().loadFinalSchedule(delFinalScheduleList[i]);
							  //finalschedule.setScheduleId(delFinalScheduleList[i]);
							  this.getScheduleFacade().deleteFinalSchedule(fs);
					    }	
			  	     }
				 
				  sBO.setErrormessage("Deleted");
				  searchList = this.getScheduleFacade().findSchedule(finalschedule.getSshHospital().getHospitalId(),finalschedule.getPeHospital().getHospitalId(),finalschedule.getDay());
				  log4log.info("inside delete "+searchList.size());
				  if(searchList.size() > 0)
			       {
					  if(finalschedule.getDay().equals(""))
				       {
						log4log.info(finalschedule.getDay());  
					    sBO.setCheckday("notselected");
				       }
					  else
					  {
						  sBO.setCheckday("selected");
					  }
					 
					   finalschedulePageList = new PagedListHolder(searchList);
		               finalschedulePageList.setPageSize(this.getDispalyPageSize());
		               log4log.info(finalschedulePageList); 
                      //uploading to session
				       request.getSession().setAttribute(SchedulingConstants.SESSION_VAR_PAGE_LIST_HOLDER,finalschedulePageList);
		               sBO.setFinalscheduleList(searchList);
				       //sBO.setHospitalSSHName(sshHospitalname);
				       //sBO.setHospitalPEName(peHospitalname);
				       sBO.setPageListHolder(finalschedulePageList);
				       log4log.info("sBO size "+sBO.getFinalscheduleList().size());
				       return new ModelAndView("ScheduleView","command",sBO);
			       }
				  else
				  {
					  log4log.info("Does Not Exist....");
					   sBO.setErrormessage("DNE");
					   return new ModelAndView("ScheduleView","command",sBO);
				  }
				  
			   }
			   else
		 	    {
				   sBO.setErrormessage("NotDeleted");
				   return new ModelAndView("ScheduleView","command",sBO);
			    }
		     }
			 else
			  {
				//retreving patient list from session, and parameter to go forward or backward
				log4log.info("search list less than zero");
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
				return new ModelAndView("ScheduleView","command",sBO);
			}	
		}
		catch(Exception e)
		{
			log4log.error("Later list : "+e);
			return new ModelAndView("ScheduleView","command",sBO);
		}		
	}
}



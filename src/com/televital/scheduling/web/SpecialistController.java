/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.televital.scheduling.utils.SchedulingConstants;
import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.Login;
import com.televital.scheduling.domain.PESchedule;
import com.televital.scheduling.domain.SSHSchedule;
import com.televital.scheduling.domain.form.SearchBackingObject;
import com.televital.scheduling.utils.ScheduleUtils;
import org.springframework.web.util.WebUtils;
import org.apache.log4j.Logger;


public class SpecialistController  extends AbstractScheduleController implements InitializingBean{
	
	private static final Logger  log4log = Logger.getLogger(SpecialistController.class);
	private Integer dispalyPageSize ;
	
	public SpecialistController() {
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
		   SSHSchedule sshschedule = new SSHSchedule();
		   ScheduleUtils dayname= new ScheduleUtils ();
		   List sshscheduleList = null;
		
		   int daycountright =1;
		   int daycountleft  =0;
		   int daycount =1;
		
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_SPECIALIST,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
		
		try
		{
			sBO.setErrormessage("NewP");
			String type=((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType();
			Hospital hospital =(Hospital)request.getSession().getAttribute(SchedulingConstants.SESSION_VAR_HOSPITAL);
			log4log.info("hospitalId   "+hospital.getHospitalId());
			log4log.info(request.getParameter("daycountright") + request.getParameter("daycountleft"));
			Login login=this.getScheduleFacade().loadLoginObject(hospital.getHospitalId());
			
			if(request.getParameter("daycountright")!=null && request.getParameter("daycountleft")!=null )
			{
			    log4log.info("Value of Click : "+request.getParameter("click"));
		        daycountright=Integer.parseInt(request.getParameter("daycountright"));
		        daycountleft = Integer.parseInt(request.getParameter("daycountleft"));
		        log4log.info("Before setting daycount "+ daycount+"left click "+daycountleft+"right click" +daycountright);
		        if(request.getParameter("click").equals("right"))
		          {
		    	     log4log.info("right click");  
		    	     //sBO.setClick("right");
		    	     daycountleft=daycountright;
			         daycountright++;
			            if(daycountright==7)
					     {
						   daycountright=0;
					     }
			        daycount = daycountright;
		          }
		         else
		          {
		    	      log4log.info("left click");  
		    	      //sBO.setClick("left");
		    	      daycountright=daycountleft;
		    	  	  daycount = daycountleft;
				      daycountleft--;
				         if(daycountleft==-1)
				          {
					        daycountleft=6;
				          }
		           }
		      
		          log4log.info("Afer Setting daycount "+ daycount+"left click "+daycountleft+"right click" +daycountright);
		          sBO.setCountright(daycountright);
			      sBO.setCountleft(daycountleft);
			      String day=dayname.daynames[daycount];
			      log4log.info("Day Selected Is: "+ day +"login.getHospitalId() "+login.getHospitalId());
			      sshscheduleList = this.getScheduleFacade().loadSSHSchedule(login.getHospitalId(),day);
			      
			    if(sshscheduleList.size()==0)
			       {
			    	sBO.setErrormessage("DNE");
			       }
			       log4log.info("Schedule List :  "+ sshscheduleList);
			       sBO.setDays(day);
			       sBO.setSshList(sshscheduleList);
			}
		    else
			  {
				   sBO.setCountright(daycountright);
				   sBO.setCountleft(daycountleft);
				   String day=dayname.daynames[daycount];
				   log4log.info("Day Selected Is: "+ day);
				   sshscheduleList = this.getScheduleFacade().loadSSHSchedule(login.getHospitalId(),day);
				   if(sshscheduleList.size()==0)
				     {
				       sBO.setErrormessage("DNE");
				     }
				   log4log.info("Schedule List :  "+ sshscheduleList);
				   sBO.setDays(day);
				   sBO.setSshList(sshscheduleList);
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
		List sshscheduleList = null;
		
		SearchBackingObject sBO = (SearchBackingObject)command;
		
		Hospital hospital = (Hospital)request.getSession().getAttribute(SchedulingConstants.SESSION_VAR_HOSPITAL);
		
		SSHSchedule sshschedule = new SSHSchedule();
		String day=request.getParameter("day");
		String leftcount=request.getParameter("daycountleft");
		String rightcount = request.getParameter("daycountright");
		log4log.info("day = "+day + leftcount + rightcount);
		try
		{
			String buttonname=request.getParameter("buttonsd");
			if(buttonname.equals("Delete"))
			{
							
				String[] delSSHScheduleList = (String[]) request.getParameterValues("chkDelFinalSchedule");
				if(delSSHScheduleList != null)
				{	
					for(int i=0; i < delSSHScheduleList.length; i++)
					{
						log4log.info("Inside Delete Option : " + delSSHScheduleList[i] );
						SSHSchedule ssh = (SSHSchedule)this.getScheduleFacade().loadSSHSchedule(delSSHScheduleList[i]);
						//sshschedule.setScheduleId(delSSHScheduleList[i]);
						
						this.getScheduleFacade().deleteSSHSchedule(ssh);
					}	
				}
				log4log.info("Inside Delete Option Deleted");
				sBO.setErrormessage("Deleted");
				log4log.info(day);
				sBO.setDays(day);
				sBO.setCountright(Integer.parseInt(rightcount));
				sBO.setCountleft(Integer.parseInt(leftcount));
				sshscheduleList = this.getScheduleFacade().loadSSHSchedule(hospital.getHospitalId(),day);
				sBO.setSshList(sshscheduleList);
				return new ModelAndView("SpecialistView","command",sBO);
			}
			else
			{
				sBO.setErrormessage("NotDeleted");
				return new ModelAndView("SpecialistView","command",sBO);
			}
		}
		catch(Exception e)
		{
			log4log.error("Error due to : "+e);
			return new ModelAndView("SpecialistView","command",sBO);
		}
	}
}

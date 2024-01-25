
package com.televital.scheduling.web;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.televital.scheduling.domain.Login;
import com.televital.scheduling.domain.SSHSchedule;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.utils.SchedulingConstants;

public class SSHEditScheduleController extends AbstractScheduleController implements
		InitializingBean {
	private static final Logger log4log = Logger.getLogger(SSHEditScheduleController.class);
	
	public SSHEditScheduleController() {
	    setCommandName("sBO");
	    setCommandClass(AddScheduleBackingObject.class);
	  }
	 
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_SPECIALIST,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
		//creating the backing object with modules
		AddScheduleBackingObject sBO = new AddScheduleBackingObject();
		List sshScheduleList = new ArrayList ();
		
		String[] hrs = {"00","01","02","03","04","05","06","07","08","09","10","11",
						"12","13","14","15","16","17","18","19","20","21","22","23"};
		String[] min = {"00","15","30","45"};
		String[] day = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		
		SSHSchedule sshSchedule = null;
		
		sBO.setHrsArray(hrs);
		sBO.setMinArray(min);
		sBO.setSshSchedule(new SSHSchedule());
		
		//fetch sshSchedule for populate on the form
		try
		{
			//fetching schedule id from the previous request
			String scheduleId = (String)request.getParameter("scheduleId");
			log4log.info("Schedule Id : "+scheduleId);
			
			//loading and setting sshSchedule to the backing object
			
			sshSchedule = this.getScheduleFacade().loadSSHSchedule(scheduleId);
				
			String fromTime = sshSchedule.getFromTime();
			String toTime = sshSchedule.getToTime();
			StringTokenizer stFromTime = new StringTokenizer(fromTime,":");
			StringTokenizer stToTime = new StringTokenizer(toTime,":");
			sshSchedule.setFromHour(stFromTime.nextToken());
			sshSchedule.setFromMin(stFromTime.nextToken());
			sshSchedule.setToHour(stToTime.nextToken());
			sshSchedule.setToMin(stToTime.nextToken());
			sBO.setSshSchedule(sshSchedule);
			log4log.info("Schedule SSH Id :"+sBO.getSshSchedule().getHospital().getHospitalId());
			sBO.setDay(sshSchedule.getDay());
			return sBO;
		}
		catch(Exception e)
		{
			log4log.error("Error while retreving sshSchedule from the database due : "+e.toString());
			throw new ModelAndViewDefiningException(new ModelAndView("GeneralErrorView"));
		}
	}

	
	@Override
	protected ModelAndView handleInvalidSubmit( HttpServletRequest request,HttpServletResponse response)throws Exception{
		return disallowDuplicateFormSubmission(request, response);
}
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
		AddScheduleBackingObject sBO = (AddScheduleBackingObject)command;
		SSHSchedule sshSchedule = sBO.getSshSchedule();
		String fromTime = null;
		String toTime = null;
				
		//persisting the sshSchedule object
		try
		{
			fromTime = sshSchedule.getFromHour() +":" +sshSchedule.getFromMin();
			toTime = sshSchedule.getToHour() +":" +sshSchedule.getToMin();
			sshSchedule.setFromTime(fromTime);
			sshSchedule.setToTime(toTime);
			this.getScheduleFacade().updateSSHSchedule(sshSchedule);
		}
		catch(Exception e)
		{
			log4log.error("Error while persisting sshSchedule object due to : "+e.toString());
			throw new ModelAndViewDefiningException(new ModelAndView("GeneralErrorView"));
		}

		//redirecting to successview
		return new ModelAndView("SSHEditScheduleSuccessView","sBO",sBO);
	}

}





































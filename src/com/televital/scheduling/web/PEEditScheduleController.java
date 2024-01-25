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

import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.Login;
import com.televital.scheduling.domain.SSHSchedule;
import com.televital.scheduling.domain.PESchedule;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.utils.SchedulingConstants;

public class PEEditScheduleController extends AbstractScheduleController implements
		InitializingBean {
	private static final Logger log4log = Logger.getLogger(PEEditScheduleController.class);
	
	public PEEditScheduleController() {
	    setCommandName("sBO");
	    setCommandClass(AddScheduleBackingObject.class);
	  }
	 
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_REMOTE,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
		//creating the backing object with modules
		AddScheduleBackingObject sBO = new AddScheduleBackingObject();
		List peScheduleList = new ArrayList ();
		List<Hospital> sshList = null;
		String[] hrs = {"00","01","02","03","04","05","06","07","08","09","10","11",
						"12","13","14","15","16","17","18","19","20","21","22","23"};
		String[] min = {"00","15","30","45"};
		String[] day = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		
		PESchedule peSchedule = null;
		
		sBO.setHrsArray(hrs);
		sBO.setMinArray(min);
		sBO.setDayArray(day);
		
		sBO.setPeSchedule(new PESchedule());
		try
		{
			sshList = this.getScheduleFacade().loadSSHospitals();
			
		}
		catch (Exception e)
		{
			log4log.error("Error while loading specialty end hospitals from the database due to: " +e.toString());
			throw new ModelAndViewDefiningException(new ModelAndView("GeneralErrorView"));
		}
		sBO.setSpecialtyList(sshList);
		//fetch peSchedule for populate on the form
		try
		{
			//fetching schedule id from the previous request
			String scheduleId = (String)request.getParameter("scheduleId");
			log4log.info("Schedule Id : "+scheduleId);
			
			//loading and setting peSchedule to the backing object
			
			peSchedule = (PESchedule)this.getScheduleFacade().loadPESchedule(scheduleId).get(0);
				
			String fromTime = peSchedule.getFromTime();
			String toTime = peSchedule.getToTime();
			StringTokenizer stFromTime = new StringTokenizer(fromTime,":");
			StringTokenizer stToTime = new StringTokenizer(toTime,":");
			peSchedule.setFromHour(stFromTime.nextToken());
			peSchedule.setFromMin(stFromTime.nextToken());
			peSchedule.setToHour(stToTime.nextToken());
			peSchedule.setToMin(stToTime.nextToken());
			sBO.setPeSchedule(peSchedule);
			log4log.info("Schedule SSH Id :"+sBO.getPeSchedule().getSshHospital().getHospitalId());
			sBO.setDay(peSchedule.getDay());
			return sBO;
		}
		catch(Exception e)
		{
			log4log.error("Error while retreving peSchedule from the database due : "+e.toString());
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
		PESchedule peSchedule = sBO.getPeSchedule();
		String fromTime = null;
		String toTime = null;
				
		//persisting the peSchedule object
		try
		{
			fromTime = peSchedule.getFromHour() +":" +peSchedule.getFromMin();
			toTime = peSchedule.getToHour() +":" +peSchedule.getToMin();
			peSchedule.setFromTime(fromTime);
			peSchedule.setToTime(toTime);
			this.getScheduleFacade().updatePESchedule(peSchedule);
		}
		catch(Exception e)
		{
			log4log.error("Error while persisting peSchedule object due to : "+e.toString());
			throw new ModelAndViewDefiningException(new ModelAndView("GeneralErrorView"));
		}

		//redirecting to successview
		return new ModelAndView("PEEditScheduleSuccessView","sBO",sBO);
	}

}





































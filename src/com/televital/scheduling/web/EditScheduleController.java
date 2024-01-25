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
import com.televital.scheduling.domain.PESchedule;
import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.Schedule;
import com.televital.scheduling.domain.FinalSchedule;

import com.televital.scheduling.domain.form.AddScheduleBackingObject;

import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.utils.SchedulingConstants;

public class EditScheduleController extends AbstractScheduleController implements
		InitializingBean {
	private static final Logger log4log = Logger.getLogger(EditScheduleController.class);
	
	public EditScheduleController() {
	    setCommandName("sBO");
	    setCommandClass(AddScheduleBackingObject.class);
	  }
	 
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		
		
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_ADMIN,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
      //		creating the backing object with modules
		AddScheduleBackingObject sBO = new AddScheduleBackingObject();
		List<Hospital> peList = null;
		List<Hospital> sshList = null;
		List<FinalSchedule> finalScheduleList = new ArrayList<FinalSchedule> ();
		
		String[] hrs = {"00","01","02","03","04","05","06","07","08","09","10","11",
						"12","13","14","15","16","17","18","19","20","21","22","23"};
		String[] min = {"00","15","30","45"};
		String[] day = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		
		Hospital sshHospital = null;
		Hospital peHospital = null;
		FinalSchedule finalSchedule = null;
		
		try
		{
			peList = this.getScheduleFacade().loadPEHospitals();
			
		}
		catch (Exception e)
		{
			log4log.error("Error while loading patient end hospitals from the database due to: " +e.toString());
			throw new ModelAndViewDefiningException(new ModelAndView("GeneralErrorView"));
		}
		try
		{
			sshList = this.getScheduleFacade().loadSSHospitals();
			
		}
		catch (Exception e)
		{
			log4log.error("Error while loading specialty end hospitals from the database due to: " +e.toString());
			throw new ModelAndViewDefiningException(new ModelAndView("GeneralErrorView"));
		}
		
		sBO.setPatientEndList(peList);
		sBO.setSpecialtyList(sshList);
		sBO.setHrsArray(hrs);
		sBO.setMinArray(min);
		sBO.setDayArray(day);
		sBO.setFinalSchedule(new FinalSchedule());
		
		//fetch finalSchedule for populate on the form
		try
		{
			//fetching schedule id from the previous request
			String scheduleId = (String)request.getParameter("scheduleId");
			log4log.info("Schedule Id : "+scheduleId);
			
			//loading and setting finalSchedule to the backing object
			finalSchedule = this.getScheduleFacade().loadFinalSchedule(scheduleId);
				
			String fromTime = finalSchedule.getFromTime();
			String toTime = finalSchedule.getToTime();
			StringTokenizer stFromTime = new StringTokenizer(fromTime,":");
			StringTokenizer stToTime = new StringTokenizer(toTime,":");
			finalSchedule.setFromHour(stFromTime.nextToken());
			finalSchedule.setFromMin(stFromTime.nextToken());
			finalSchedule.setToHour(stToTime.nextToken());
			finalSchedule.setToMin(stToTime.nextToken());
			sBO.setFinalSchedule(finalSchedule);
			log4log.info("Schedule SSH Id :"+sBO.getFinalSchedule().getSshHospital().getHospitalId());
			return sBO;
		}
		catch(Exception e)
		{
			log4log.error("Error while retreving FinalSchedule from the database due : "+e.toString());
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
		FinalSchedule finalSchedule = sBO.getFinalSchedule();
		String fromTime = null;
		String toTime = null;
				
		//persisting the finalSchedule object
		try
		{
			fromTime = finalSchedule.getFromHour() +":" +finalSchedule.getFromMin();
			toTime = finalSchedule.getToHour() +":" +finalSchedule.getToMin();
			finalSchedule.setFromTime(fromTime);
			finalSchedule.setToTime(toTime);
			this.getScheduleFacade().updateFinalSchedule(finalSchedule);
		}
		catch(Exception e)
		{
			log4log.error("Error while persisting FinalSchedule object due to : "+e.toString());
			throw new ModelAndViewDefiningException(new ModelAndView("GeneralErrorView"));
		}

		//redirecting to successview
		return new ModelAndView("EditScheduleSuccessView");
	}

}





































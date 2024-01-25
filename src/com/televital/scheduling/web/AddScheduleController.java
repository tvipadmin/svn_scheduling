package com.televital.scheduling.web;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
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

public class AddScheduleController extends AbstractScheduleController implements
		InitializingBean {
	private static final Logger log4log = Logger.getLogger(AddScheduleController.class);
	private Integer addSchedulesSize;
	
	public AddScheduleController() {
	    setCommandName("sBO");
	    setCommandClass(AddScheduleBackingObject.class);
	    setSessionForm(true);
	  }
	
	public void setAddSchedulesSize(Integer addSchedulesSize)
	{
		this.addSchedulesSize = addSchedulesSize;
	}

	public Integer getAddSchedulesSize()
	{
	    return addSchedulesSize;
	}


	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
			
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_ADMIN,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
		
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
			return new ModelAndView("GeneralErrorView");
		}
		try
		{
			sshList = this.getScheduleFacade().loadSSHospitals();
			
		}
		catch (Exception e)
		{
			log4log.error("Error while loading specialty end hospitals from the database due to: " +e.toString());
			return new ModelAndView("GeneralErrorView");
		}
		
		sBO.setPatientEndList(peList);
		sBO.setSpecialtyList(sshList);
		sBO.setHrsArray(hrs);
		sBO.setMinArray(min);
		sBO.setDayArray(day);
		
		
		for(int i=0; i<this.getAddSchedulesSize(); i++)
		{		
			sshHospital = new Hospital();
			peHospital = new Hospital();
			finalSchedule = new FinalSchedule();
			finalSchedule.setSshHospital(sshHospital);
			finalSchedule.setPeHospital(peHospital);
			finalScheduleList.add(i,finalSchedule);
		}
		sBO.setFinalScheduleList(finalScheduleList);
		
		return sBO;
	}

	@Override
	protected ModelAndView handleInvalidSubmit( HttpServletRequest request,HttpServletResponse response)throws Exception{
		return disallowDuplicateFormSubmission(request, response);
}
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
		
		AddScheduleBackingObject sBO = (AddScheduleBackingObject)command;
		List<FinalSchedule> finalScheduleList = sBO.getFinalScheduleList();
		List<FinalSchedule> newFinalScheduleList = new ArrayList<FinalSchedule> ();
		
		String[] addFinalSchedulesList = (String[]) request.getParameterValues("chkFinalSchedules" );
		
		
		if( addFinalSchedulesList != null)
		{
			String fromTime = null;
			String toTime = null;
			for(int i=0; i<addFinalSchedulesList.length;i++)
			{
				int index = Integer.parseInt(addFinalSchedulesList[i]);
				FinalSchedule finalSchedule = (FinalSchedule)finalScheduleList.get(index-1);
				fromTime = finalSchedule.getFromHour() +":" +finalSchedule.getFromMin();
				toTime = finalSchedule.getToHour() +":" +finalSchedule.getToMin();
				finalSchedule.setFromTime(fromTime);
				finalSchedule.setToTime(toTime);
				if(!finalSchedule.getSshHospital().getHospitalId().equals("") && !finalSchedule.getPeHospital().getHospitalId().equals("") && !finalSchedule.getSpecialty().equals("") && !finalSchedule.getDay().equals("") && !finalSchedule.getFromHour().equals("") && !finalSchedule.getFromMin().equals("") && !finalSchedule.getToHour().equals("") && !finalSchedule.getToMin().equals(""))
				{ 
					newFinalScheduleList.add(finalSchedule);
				}
			}
		}
		if(newFinalScheduleList.size()>0)
		{
			for(int j=0;j<newFinalScheduleList.size();j++)
			{
				try
				{
					this.getScheduleFacade().storeFinalSchedule(newFinalScheduleList.get(j));
				}
				catch(Exception e)
				{
					log4log.error("Error while storing final schedule objects into the database due to: " +e.toString());
					return new ModelAndView("GeneralErrorView");
				}
			}
			//redirecting to successview
			return new ModelAndView("AddScheduleSuccessView");
		}
		else
			//redirecting to failureview
			return new ModelAndView("AddScheduleFailureView");
		
	}
}
				
				
			
				
					
		
		
	
	





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
import com.televital.scheduling.domain.Schedule;
import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;
import com.televital.scheduling.domain.form.SearchBackingObject;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.utils.SchedulingConstants;

public class SSHAddScheduleController extends AbstractScheduleController implements
		InitializingBean {
	private static final Logger log4log = Logger.getLogger(SSHAddScheduleController.class);
	private Integer addSchedulesSize;
	
	public SSHAddScheduleController() {
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
		
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_SPECIALIST,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
		
		log4log.info("inside form backing after checking");
		String day = request.getParameter("day");
		log4log.info(day);
		request.getSession().setAttribute("DAY",request.getParameter("day"));
		Hospital hospital =(Hospital)request.getSession().getAttribute(SchedulingConstants.SESSION_VAR_HOSPITAL);
		//String hospitalId = hospital.getHospitalId();
		
		AddScheduleBackingObject sBO = new AddScheduleBackingObject();
			
		List sshScheduleList = new ArrayList ();
		
		String[] hrs = {"00","01","02","03","04","05","06","07","08","09","10","11",
						"12","13","14","15","16","17","18","19","20","21","22","23"};
		String[] min = {"00","15","30","45"};
				
		SSHSchedule sshSchedule = null;
		//Hospital hospital = null;
		
		sBO.setHrsArray(hrs);
		sBO.setMinArray(min);
					
		for(int i=0; i<this.getAddSchedulesSize(); i++)
		{		
			sshSchedule = new SSHSchedule();
			//hospital =  new Hospital();
			//hospital.setHospitalId(hospitalId);
			sshSchedule.setHospital(hospital);
			//sshSchedule.getHospital().setHospitalId(hospitalId);
			sshSchedule.setDay(day);
			
			sshScheduleList.add(i, sshSchedule);
			
		}
		
		sBO.setFinalScheduleList(sshScheduleList);
		
		sshSchedule = (SSHSchedule) sshScheduleList.get(0);
		if(day == null)
		{
			
			try
			{
			sBO.setDay(request.getSession().getAttribute("DAY").toString());
			}
			catch(Exception e)
			{
				
				log4log.info("Error while day is empty: "+e.toString());
				throw new ModelAndViewDefiningException(new ModelAndView("GeneralErrorView"));
			}
			
		}
		else
		{
			
			sBO.setDay(day);
		}
		
		return sBO;
	}

	protected ModelAndView handleInvalidSubmit( HttpServletRequest request,HttpServletResponse response)throws Exception{
		return disallowDuplicateFormSubmission(request, response);
}
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
		
		AddScheduleBackingObject sBO = (AddScheduleBackingObject)command;
			
		List sshScheduleList = sBO.getFinalScheduleList();
		
		List newSSHScheduleList = new ArrayList ();
		SSHSchedule sshSchedule = null;
		//String hospitalId = null;
		//String day = null;
		String[] addSchedulesList = (String[]) request.getParameterValues("chkFinalSchedules" );
		
		if( addSchedulesList != null)
		{
			String fromTime = null;
			String toTime = null;
			for(int i=0; i<addSchedulesList.length;i++)
			{
				int index = Integer.parseInt(addSchedulesList[i]);
				sshSchedule = (SSHSchedule)sshScheduleList.get(index-1);
				
				fromTime = sshSchedule.getFromHour() +":" +sshSchedule.getFromMin();
				toTime = sshSchedule.getToHour() +":" +sshSchedule.getToMin();
				sshSchedule.setFromTime(fromTime);
				sshSchedule.setToTime(toTime);
				
				if(!sshSchedule.getHospital().getHospitalId().equals("") && !sshSchedule.getSpecialty().equals("") && !sshSchedule.getDay().equals("") && !sshSchedule.getFromHour().equals("") && !sshSchedule.getFromMin().equals("") && !sshSchedule.getToHour().equals("") && !sshSchedule.getToMin().equals(""))
				{ 
					
					newSSHScheduleList.add(sshSchedule);
				}
			}
		}
		if(newSSHScheduleList.size()>0)
		{
			for(int j=0;j<newSSHScheduleList.size();j++)
			{
				try
				{
					sshSchedule = (SSHSchedule)newSSHScheduleList.get(j);
					
					
					this.getScheduleFacade().storeSSHSchedule(sshSchedule);
					
				}
				catch(Exception e)
				{
					log4log.error("Error while storing ssh schedule objects into the database due to: " +e.toString());
					return new ModelAndView("GeneralErrorView");
				}
			}
			//redirecting to successview
			return new ModelAndView("SSHAddScheduleSuccessView","sBO",sBO);
		}
		else
			//redirecting to failureview
			return new ModelAndView("SSHAddScheduleView","sBO",sBO);
		
	}
}
				
				
			
				
					
		
		
	
	





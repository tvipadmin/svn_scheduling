
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
import com.televital.scheduling.domain.PESchedule;
import com.televital.scheduling.domain.SSHSchedule;
import com.televital.scheduling.domain.Schedule;
import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.utils.SchedulingConstants;

public class PEAddScheduleController extends AbstractScheduleController implements
		InitializingBean {
	private static final Logger log4log = Logger.getLogger(PEAddScheduleController.class);
	private Integer addSchedulesSize;
	
	public PEAddScheduleController() {
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
				
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_REMOTE,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
		List<Hospital> sshList = null;
		
		
		Hospital hospital =(Hospital)request.getSession().getAttribute(SchedulingConstants.SESSION_VAR_HOSPITAL);
		
		
		AddScheduleBackingObject sBO = new AddScheduleBackingObject();
			
		List peScheduleList = new ArrayList ();
		
		String[] hrs = {"00","01","02","03","04","05","06","07","08","09","10","11",
						"12","13","14","15","16","17","18","19","20","21","22","23"};
		String[] min = {"00","15","30","45"};
		String[] day = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};		
		PESchedule peSchedule = null;
		Hospital sshHospital = null;
		
		sBO.setHrsArray(hrs);
		sBO.setMinArray(min);
		sBO.setDayArray(day);
		try
		{
			sshList = this.getScheduleFacade().loadSSHospitals();
			
		}
		catch (Exception e)
		{
			log4log.error("Error while loading specialty end hospitals from the database due to: " +e.toString());
			return new ModelAndView("GeneralErrorView");
		}	
		for(int i=0; i<this.getAddSchedulesSize(); i++)
		{		
			peSchedule = new PESchedule();
			sshHospital = new Hospital();
			
			peSchedule.setPeHospital(hospital);
			peSchedule.setSshHospital(sshHospital);
			peSchedule.setStatus("Pending");
			
			peScheduleList.add(i, peSchedule);
			
		}
		sBO.setSpecialtyList(sshList);
		sBO.setFinalScheduleList(peScheduleList);
		
		peSchedule = (PESchedule) peScheduleList.get(0);
		log4log.info("PESchedule hospital name:" +peSchedule.getPeHospital().getHospitalName());
		log4log.info("PESchedule Status:" +peSchedule.getStatus());
		return sBO;
	}

	protected ModelAndView handleInvalidSubmit( HttpServletRequest request,HttpServletResponse response)throws Exception{
		return disallowDuplicateFormSubmission(request, response);
}
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
		log4log.info("Inside onSubmit");
		AddScheduleBackingObject sBO = (AddScheduleBackingObject)command;
			
		List peScheduleList = sBO.getFinalScheduleList();
		
		List newPEScheduleList = new ArrayList ();
		PESchedule peSchedule = null;
		String[] addSchedulesList = (String[]) request.getParameterValues("chkFinalSchedules" );
		
		if( addSchedulesList != null)
		{
			String fromTime = null;
			String toTime = null;
			for(int i=0; i<addSchedulesList.length;i++)
			{
				int index = Integer.parseInt(addSchedulesList[i]);
				peSchedule = (PESchedule)peScheduleList.get(index-1);
				
				fromTime = peSchedule.getFromHour() +":" +peSchedule.getFromMin();
				toTime = peSchedule.getToHour() +":" +peSchedule.getToMin();
				peSchedule.setFromTime(fromTime);
				peSchedule.setToTime(toTime);
				
				if(!peSchedule.getPeHospital().getHospitalId().equals("") && !peSchedule.getSshHospital().getHospitalId().equals("") && !peSchedule.getSpecialty().equals("") && !peSchedule.getDay().equals("") && !peSchedule.getFromHour().equals("") && !peSchedule.getFromMin().equals("") && !peSchedule.getToHour().equals("") && !peSchedule.getToMin().equals(""))
				{ 
					newPEScheduleList.add(peSchedule);
				}
			}
		}
		if(newPEScheduleList.size()>0)
		{
			for(int j=0;j<newPEScheduleList.size();j++)
			{
				try
				{
					peSchedule = (PESchedule)newPEScheduleList.get(j);
					this.getScheduleFacade().storePESchedule(peSchedule);
					
				}
				catch(Exception e)
				{
					log4log.error("Error while storing ssh schedule objects into the database due to: " +e.toString());
					return new ModelAndView("GeneralErrorView");
				}
			}
			//redirecting to successview
			return new ModelAndView("PEAddScheduleSuccessView","sBO",sBO);
		}
		else
			//redirecting to failureview
			return new ModelAndView("PEAddScheduleView","sBO",sBO);
		
	}
}
				
				
			
				
					
		
		
	
	






package com.televital.scheduling.web;

/**
 * @author Sumati C B
 *
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.Login;
import com.televital.scheduling.domain.SSHSchedule;
import com.televital.scheduling.domain.PESchedule;
import com.televital.scheduling.domain.FinalSchedule;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;
import com.televital.scheduling.utils.SchedulingConstants;

public class ScheduleRequestsController extends AbstractScheduleController implements
InitializingBean {

	private static final Logger  log4log = Logger.getLogger(ScheduleRequestsController.class);
	
	public ScheduleRequestsController() {
		setCommandName("sBO");
	    setCommandClass(AddScheduleBackingObject.class);
		setSessionForm(false);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		/* Check if the user is authorized to access this controller */
		
		AddScheduleBackingObject sBO = new AddScheduleBackingObject();
		
		List<Hospital> peList = null;
		String[] day = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		List<PESchedule> peScheduleList = null;
		
		
		
		try
		{
			peList = this.getScheduleFacade().loadPEHospitals();
			
		}
		catch (Exception e)
		{
			log4log.error("Error while loading patient end hospitals from the database due to: " +e.toString());
			return new ModelAndView("GeneralErrorView");
		}
		if(sBO.getHospitalName() == null)
		{
			sBO.setHospitalName("");
		}
		if(sBO.getDay() == null)
		{
			sBO.setDay("");
		}		
		String params[] = {sBO.getHospitalName(),sBO.getDay()};
		try
		{
			peScheduleList = this.getScheduleFacade().findScheduleRequests(params);
		}
		catch(Exception e)
		{
			log4log.error("Error while getting schedule objects from the database due to: " +e.toString());
			return new ModelAndView("GeneralErrorView");
		}
		
		
		sBO.setPatientEndList(peList);
		sBO.setDayArray(day);
		if(peScheduleList.size() > 0)
		{
			//setting search filters to enabled advanced search filters
			sBO.setSearchFilters(true);
			//setting backing object to maintain constant request attribute for the page after onsubmit is called
			sBO.setFinalScheduleList(peScheduleList);
		}
		else
		{
			//setting search filters to true to enable advanced search
			sBO.setSearchFilters(false);
			//clearing the old page list to display empty list
			sBO.setFinalScheduleList(null);
		}
			
		return sBO;

	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
		
		AddScheduleBackingObject sBO = (AddScheduleBackingObject)command;
		String params[] = {sBO.getHospitalName(),sBO.getDay()};
		if(sBO.getHospitalName() == null)
		{
			sBO.setHospitalName("");
		}
		if(sBO.getDay() == null)
		{
			sBO.setDay("");
		}
		List<PESchedule> peScheduleList = null;
		if(sBO.getHospitalName().equals("") && sBO.getDay().equals(""))
		{
			try
			{
				log4log.info("33333"+params);
				peScheduleList = this.getScheduleFacade().findScheduleRequests(params);
				log4log.info("44444"+params);
			}
			catch(Exception e)
			{
				log4log.error("Error while getting schedule objects from the database due to: " +e.toString());
				return new ModelAndView("GeneralErrorView");
			}
		}
		else
		{
			try
			{
				peScheduleList = this.getScheduleFacade().findScheduleRequests(params);
			}
			catch(Exception e)
			{
				log4log.error("Error while getting schedule objects from the database due to: " +e.toString());
				return new ModelAndView("GeneralErrorView");
			}
		}
		if(peScheduleList.size() > 0)
		{
			//setting search filters to enabled advanced search filters
			sBO.setSearchFilters(true);
			//setting backing object to maintain constant request attribute for the page after onsubmit is called
			sBO.setFinalScheduleList(peScheduleList);
		}
		else
		{
			//setting search filters to true to enable advanced search
			sBO.setSearchFilters(true);
			//clearing the old page list to display empty list
			sBO.setFinalScheduleList(null);
		}	
		
		//redirecting to successview
		return new ModelAndView("ScheduleRequestsView","sBO",sBO);
	}
	
}


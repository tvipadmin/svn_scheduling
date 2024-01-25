
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
import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.SSHSchedule;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;
import com.televital.scheduling.domain.form.HospitalTypeBackingObject;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.utils.SchedulingConstants;

public class EditHospitalController extends AbstractScheduleController implements
		InitializingBean {
	private static final Logger log4log = Logger.getLogger(EditHospitalController.class);
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		log4log.info("inside edithospitalcontroller");
		
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_ADMIN,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
		//creating the backing object
		HospitalTypeBackingObject command = new HospitalTypeBackingObject();
		Hospital hospital = null;
		try
		{
			//fetching schedule id from the previous request
			String hospitalId = (String)request.getParameter("hId");
			hospital = this.getScheduleFacade().loadHospital(hospitalId);
			log4log.info("hospital name:" +hospital.getHospitalName());
			log4log.info("hospital type:" +hospital.getHospitalType());
			command.setHospital(hospital);
		}
		catch(Exception e)
		{
			log4log.info("Error while loading hospital object from database :" +e.toString());
		}
		log4log.info("exit1");
		return command;
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
//		creating the backing object
		log4log.info("inside edithospitalcontroller onSubmit");
		HospitalTypeBackingObject hTBO = (HospitalTypeBackingObject)command;	
		Hospital hospital = null;
		Login login = null;
		
		hospital = hTBO.getHospital();
		log4log.info("hospital Name onSubmit:" +hospital.getHospitalName());
		log4log.info("hospital type1 onSubmit:" +hospital.getHospitalType());
		log4log.info("hospital htbo onSubmit:" +hTBO.getHospitalType());
		hospital.setHospitalType(hTBO.getHospitalType());
		try
		{
			this.getScheduleFacade().updateHospital(hospital);
			login = this.getScheduleFacade().loadLoginObject(hospital.getHospitalId());
			if(hTBO.getHospitalType().equals("SPECIALISTEND"))
			{
				login.setAccountType("SPECIALISTADMIN");
				this.getScheduleFacade().updateLogin(login);
			}
			if(hTBO.getHospitalType().equals("PATIENTEND"))
			{
				login.setAccountType("PATIENTADMIN");
				this.getScheduleFacade().updateLogin(login);
			}
		}
		catch(Exception e)
		{
			log4log.info("Error while storing hospital object from database :" +e.toString());
		}
		log4log.info("hospital type2 onSubmit:" +hospital.getHospitalType());
		//redirecting to successview
		return new ModelAndView(this.getSuccessView());
	}
		
}
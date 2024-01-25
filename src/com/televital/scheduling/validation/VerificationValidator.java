/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.validation;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.televital.scheduling.domain.FinalSchedule;



public class VerificationValidator implements Validator {
	
	private static final Logger log4log = Logger.getLogger(VerificationValidator.class);
	
	public boolean supports(Class arg0) {
		// TODO Auto-generated method stub
		return FinalSchedule.class.isAssignableFrom(arg0);
	}
 
	public void validate(Object arg0, Errors errors) {
		// TODO Auto-generated method stub
		FinalSchedule loginForm = (FinalSchedule) arg0;
		log4log.info("Inside Verification validator file");
		
		if(!StringUtils.hasLength(loginForm.getSshHospital().getHospitalId()))
		{
			errors.rejectValue("userId","login.UserId.Required","Required Field");
			log4log.info("rejected user");
		}
		if(!StringUtils.hasLength(loginForm.getPeHospital().getHospitalId()))
		{
			errors.rejectValue("password","login.Password.Required","Required field");
			log4log.info("rejected password");
		}
		
		if(!StringUtils.hasLength(loginForm.getSpecialty()))
		{
			errors.rejectValue("password","login.Password.Required","Required field");
			log4log.info("rejected password");
		}
		log4log.info("accepted user");
	}

}

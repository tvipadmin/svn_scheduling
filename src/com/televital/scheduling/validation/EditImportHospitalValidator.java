package com.televital.scheduling.validation;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.televital.scheduling.domain.form.HospitalTypeBackingObject;


public class EditImportHospitalValidator implements Validator{

	private static final Logger log4log = Logger.getLogger(EditImportHospitalValidator.class);

	public boolean supports(Class arg0) {
		// TODO Auto-generated method stub
		return HospitalTypeBackingObject.class.isAssignableFrom(arg0);
	}

	public void validate(Object command, Errors errors)
	{
		// TODO Auto-generated method stub
		log4log.info("ImportHospitalValidator");
			
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"hospitalType","ImportHospital.hospitalType.Required");
	}
}











package com.televital.scheduling.validation;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.televital.scheduling.domain.form.ImportHospitalBackingObject;;


public class ImportHospitalValidator implements Validator{

	private static final Logger log4log = Logger.getLogger(ImportHospitalValidator.class);

	public boolean supports(Class arg0) {
		// TODO Auto-generated method stub
		return ImportHospitalBackingObject.class.isAssignableFrom(arg0);
	}

	public void validate(Object command, Errors errors)
	{
		// TODO Auto-generated method stub
		log4log.info("ImportHospitalValidator");
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"hospitalId","ImportHospital.Id.Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"ipAddress","ImportHospital.ipaddress.Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"hospitalType","ImportHospital.hospitalType.Required");
	}
}











package com.televital.scheduling.validation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.televital.scheduling.domain.PESchedule;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;


public class PEEditScheduleValidator implements Validator {
 
	private static final Logger log4log = Logger.getLogger(PEEditScheduleValidator.class);
	public boolean supports(Class arg0) {
		log4log.info("inside peeditschedulevalidator supports");
		return AddScheduleBackingObject.class.isAssignableFrom(arg0);
	}

	
	public void validate(Object command, Errors errors) {
		
		AddScheduleBackingObject sBO = (AddScheduleBackingObject)command;
		PESchedule peSchedule = sBO.getPeSchedule();
		
		if(!peSchedule.getPeHospital().getHospitalId().equals("") && !peSchedule.getSpecialty().equals("") && !peSchedule.getDay().equals("") && !peSchedule.getFromHour().equals("") && !peSchedule.getFromMin().equals("") && !peSchedule.getToHour().equals("") && !peSchedule.getToMin().equals(""))
		{
			
			if(ScheduleUtils.compareTime(peSchedule.getFromHour(),peSchedule.getFromMin(),peSchedule.getToHour(),peSchedule.getToMin()) == 1)
			{
				errors.rejectValue("peSchedule.fromHour","schedule.Add.Time");
				log4log.info("inside editschedulevalidator compareTime " +ScheduleUtils.compareTime(peSchedule.getFromHour(),peSchedule.getFromMin(),peSchedule.getToHour(),peSchedule.getToMin()));
			}
			if(!ScheduleUtils.stringValid(peSchedule.getSpecialty()))
			{
				log4log.info("inside editSchedulevalidator specialty " +ScheduleUtils.stringValid(peSchedule.getSpecialty()));
				errors.rejectValue("peSchedule.specialty","schedule.Add.Specialty");
			}
		}
	}
}
			
			
		


		



		

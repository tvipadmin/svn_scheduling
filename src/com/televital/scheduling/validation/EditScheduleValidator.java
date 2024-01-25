
package com.televital.scheduling.validation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.televital.scheduling.domain.FinalSchedule;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;


public class EditScheduleValidator implements Validator {
 
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	private static final Logger log4log = Logger.getLogger(EditScheduleValidator.class);
	public boolean supports(Class arg0) {
		log4log.info("inside editschedulevalidator supports");
		return AddScheduleBackingObject.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object command, Errors errors) {
		log4log.info("inside editschedulevalidator validate");
		AddScheduleBackingObject sBO = (AddScheduleBackingObject)command;
		FinalSchedule finalSchedule = sBO.getFinalSchedule();
		
		if(!finalSchedule.getSshHospital().getHospitalId().equals("") && !finalSchedule.getPeHospital().getHospitalId().equals("") && !finalSchedule.getSpecialty().equals("") && !finalSchedule.getDay().equals("") && !finalSchedule.getFromHour().equals("") && !finalSchedule.getFromMin().equals("") && !finalSchedule.getToHour().equals("") && !finalSchedule.getToMin().equals(""))
		{
			log4log.info("inside editschedulevalidator validate if");
			if(ScheduleUtils.compareTime(finalSchedule.getFromHour(),finalSchedule.getFromMin(),finalSchedule.getToHour(),finalSchedule.getToMin()) == 1)
			{
				errors.rejectValue("finalSchedule.fromHour","schedule.Add.Time");
				log4log.info("inside editschedulevalidator compareTime " +ScheduleUtils.compareTime(finalSchedule.getFromHour(),finalSchedule.getFromMin(),finalSchedule.getToHour(),finalSchedule.getToMin()));
			}
			if(!ScheduleUtils.stringValid(finalSchedule.getSpecialty()))
			{
				log4log.info("inside editSchedulevalidator specialty " +ScheduleUtils.stringValid(finalSchedule.getSpecialty()));
				errors.rejectValue("finalSchedule.specialty","schedule.Add.Specialty");
			}
		}
	}
}
			
			
		


		



		

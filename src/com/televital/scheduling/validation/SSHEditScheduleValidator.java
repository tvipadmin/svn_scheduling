package com.televital.scheduling.validation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.televital.scheduling.domain.SSHSchedule;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;


public class SSHEditScheduleValidator implements Validator {
 
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	private static final Logger log4log = Logger.getLogger(SSHEditScheduleValidator.class);
	public boolean supports(Class arg0) {
		log4log.info("inside ssheditschedulevalidator supports");
		return AddScheduleBackingObject.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object command, Errors errors) {
		log4log.info("inside ssheditschedulevalidator validate");
		AddScheduleBackingObject sBO = (AddScheduleBackingObject)command;
		SSHSchedule sshSchedule = sBO.getSshSchedule();
		
		if(!sshSchedule.getHospital().getHospitalId().equals("") && !sshSchedule.getSpecialty().equals("") && !sshSchedule.getDay().equals("") && !sshSchedule.getFromHour().equals("") && !sshSchedule.getFromMin().equals("") && !sshSchedule.getToHour().equals("") && !sshSchedule.getToMin().equals(""))
		{
			log4log.info("inside ssheditschedulevalidator validate if");
			if(ScheduleUtils.compareTime(sshSchedule.getFromHour(),sshSchedule.getFromMin(),sshSchedule.getToHour(),sshSchedule.getToMin()) == 1)
			{
				errors.rejectValue("sshSchedule.fromHour","schedule.Add.Time");
				log4log.info("inside editschedulevalidator compareTime " +ScheduleUtils.compareTime(sshSchedule.getFromHour(),sshSchedule.getFromMin(),sshSchedule.getToHour(),sshSchedule.getToMin()));
			}
			if(!ScheduleUtils.stringValid(sshSchedule.getSpecialty()))
			{
				log4log.info("inside editSchedulevalidator specialty " +ScheduleUtils.stringValid(sshSchedule.getSpecialty()));
				errors.rejectValue("sshSchedule.specialty","schedule.Add.Specialty");
			}
		}
	}
}
			
			
		


		



		

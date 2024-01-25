
package com.televital.scheduling.validation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.televital.scheduling.domain.SSHSchedule;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;


public class SSHAddScheduleValidator implements Validator {
 
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	private static final Logger log4log = Logger.getLogger(SSHAddScheduleValidator.class);
	public boolean supports(Class arg0) {
		log4log.info("inside SSHaddschedulevalidator supports");
		return AddScheduleBackingObject.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object command, Errors errors) {
		log4log.info("inside SSHaddschedulevalidator validate");
		AddScheduleBackingObject aSBO = (AddScheduleBackingObject)command;
		String errMsg = null;
		List<SSHSchedule> finalScheduleList = aSBO.getFinalScheduleList();
		log4log.info("inside SSHaddschedulevalidator supports: " +finalScheduleList);
		int j=0;
		for(int i=0; i<finalScheduleList.size(); i++)
		{
			j=i;
			log4log.info("inside addschedulevalidator supports i: " +i);
			SSHSchedule sshSchedule = (SSHSchedule)finalScheduleList.get(i);
			log4log.info("sshScheduleList HospitalId 1: " +sshSchedule);
			log4log.info("sshScheduleList HospitalId 2: " +sshSchedule.getHospital());
			log4log.info("sshScheduleList HospitalId 3: " +sshSchedule.getHospital().getHospitalId());
			log4log.info("sshScheduleList HospitalId 4: " +sshSchedule.getHospital().getHospitalId());
			
			if(!sshSchedule.getHospital().getHospitalId().equals("") && !sshSchedule.getSpecialty().equals("") && !sshSchedule.getDay().equals("") && !sshSchedule.getFromHour().equals("") && !sshSchedule.getFromMin().equals("") && !sshSchedule.getToHour().equals("") && !sshSchedule.getToMin().equals(""))
			{
				j=j+1;
				log4log.info("inside if of validator");
				if(ScheduleUtils.compareTime(sshSchedule.getFromHour(),sshSchedule.getFromMin(),sshSchedule.getToHour(),sshSchedule.getToMin()) == 1)
				{
					errMsg="To Time should be greater than From Time at row number "+j;
					errors.rejectValue("finalScheduleList["+i+"].fromHour","aa",errMsg);
					log4log.info("inside addschedulevalidator compareTime " +ScheduleUtils.compareTime(sshSchedule.getFromHour(),sshSchedule.getFromMin(),sshSchedule.getToHour(),sshSchedule.getToMin()));
				}
				if(!ScheduleUtils.stringValid(sshSchedule.getSpecialty()))
				{
					errMsg="Enter valid specialty at row number "+j;
					errors.rejectValue("finalScheduleList["+i+"].specialty","aa",errMsg);
				}
		
			}
		}
	}
}


		



		

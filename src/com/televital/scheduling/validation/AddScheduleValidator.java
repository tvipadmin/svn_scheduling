
package com.televital.scheduling.validation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.televital.scheduling.domain.FinalSchedule;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;


public class AddScheduleValidator implements Validator {
 
	private static final Logger log4log = Logger.getLogger(AddScheduleValidator.class);
	public boolean supports(Class arg0) {
		log4log.info("inside addschedulevalidator supports");
		return AddScheduleBackingObject.class.isAssignableFrom(arg0);
	}

	public void validate(Object command, Errors errors) {
		log4log.info("inside addschedulevalidator validate");
		AddScheduleBackingObject aSBO = (AddScheduleBackingObject)command;
		String errMsg = null;
		List<FinalSchedule> finalScheduleList = aSBO.getFinalScheduleList();
		log4log.info("finalScheduleList inside addschedulevalidator : " +finalScheduleList);
		int j=0;
		for(int i=0; i<finalScheduleList.size(); i++)
		{
			j=i;
			log4log.info("inside addschedulevalidator supports i: " +i);
			FinalSchedule finalSchedule = (FinalSchedule)finalScheduleList.get(i);
			
			if(!finalSchedule.getSshHospital().getHospitalId().equals("") && !finalSchedule.getPeHospital().getHospitalId().equals("") && !finalSchedule.getSpecialty().equals("") && !finalSchedule.getDay().equals("") && !finalSchedule.getFromHour().equals("") && !finalSchedule.getFromMin().equals("") && !finalSchedule.getToHour().equals("") && !finalSchedule.getToMin().equals(""))//!finalSchedule.getFromTime().equals("") && !finalSchedule.getToTime().equals(""))
			{
				j=j+1;
				log4log.info("inside if of validator");
				if(ScheduleUtils.compareTime(finalSchedule.getFromHour(),finalSchedule.getFromMin(),finalSchedule.getToHour(),finalSchedule.getToMin()) == 1)
				{
					
					errMsg = "To Time should be greater than From Time at row number "+j;
					errors.rejectValue("finalScheduleList["+i+"].fromHour","",errMsg);
					log4log.info("inside addschedulevalidator compareTime " +ScheduleUtils.compareTime(finalSchedule.getFromHour(),finalSchedule.getFromMin(),finalSchedule.getToHour(),finalSchedule.getToMin()));
				}
				if(!ScheduleUtils.stringValid(finalSchedule.getSpecialty()))
				{
					errMsg = "Enter valid specialty at row number "+j;
					errors.rejectValue("finalScheduleList["+i+"].specialty","",errMsg);
				}

			}
	
		}
	}
}


		



		

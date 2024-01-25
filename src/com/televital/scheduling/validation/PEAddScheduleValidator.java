
package com.televital.scheduling.validation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.televital.scheduling.domain.PESchedule;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.domain.form.AddScheduleBackingObject;


public class PEAddScheduleValidator implements Validator {
 
	
	private static final Logger log4log = Logger.getLogger(PEAddScheduleValidator.class);
	public boolean supports(Class arg0) {
				return AddScheduleBackingObject.class.isAssignableFrom(arg0);
	}

	
	public void validate(Object command, Errors errors) {
		
		AddScheduleBackingObject aSBO = (AddScheduleBackingObject)command;
		String errMsg = null;
		List<PESchedule> peScheduleList = aSBO.getFinalScheduleList();
		
		int j=0;
		for(int i=0; i<peScheduleList.size(); i++)
		{
			j=i;
			
			PESchedule peSchedule = (PESchedule)peScheduleList.get(i);
		
			if(!peSchedule.getSshHospital().getHospitalId().equals("") && !peSchedule.getSpecialty().equals("") && !peSchedule.getDay().equals("") && !peSchedule.getFromHour().equals("") && !peSchedule.getFromMin().equals("") && !peSchedule.getToHour().equals("") && !peSchedule.getToMin().equals(""))
			{
				j=j+1;
				
				if(ScheduleUtils.compareTime(peSchedule.getFromHour(),peSchedule.getFromMin(),peSchedule.getToHour(),peSchedule.getToMin()) == 1)
				{
					
					errMsg = "To Time should be greater than From Time at row number "+j;
					
					
					errors.rejectValue("finalScheduleList["+i+"].fromHour","",errMsg);
					log4log.info("inside addschedulevalidator compareTime " +ScheduleUtils.compareTime(peSchedule.getFromHour(),peSchedule.getFromMin(),peSchedule.getToHour(),peSchedule.getToMin()));
				}
				if(!ScheduleUtils.stringValid(peSchedule.getSpecialty()))
				{
					errMsg = "Enter valid specialty at row number "+j;
					errors.rejectValue("finalScheduleList["+i+"].specialty","",errMsg);
				}
			}
		}
	}
}


		



		

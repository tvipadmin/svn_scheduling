/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.televital.scheduling.domain.IScheduleFacade;

public class AbstractScheduleController  extends SimpleFormController{
	
	private static final Logger log4log = Logger.getLogger(AbstractScheduleController.class);
	private IScheduleFacade scheduleFacade;
	
	public void setScheduleFacade(IScheduleFacade ScheduleFacade)
	{
		this.scheduleFacade = ScheduleFacade;
	
	}
	//getter for hospitalfacade
	public IScheduleFacade getScheduleFacade()
	{
	 return this.scheduleFacade;
	}

	
	protected ModelAndView disallowDuplicateFormSubmission(HttpServletRequest request, HttpServletResponse response)
	throws Exception 
	{
		BindException errors = getErrorsForNewForm(request);
		errors.reject("duplicateFormSubmission", "Duplicate form submission");
		return showForm(request, response, errors);
	}
	
	public void afterPropertiesSet() {
		if (this.scheduleFacade == null) {
		 log4log.error("ScheduleFacade not set");
			throw new IllegalArgumentException("object 'ScheduleFacade' is required");
		}
	}
}

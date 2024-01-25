/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.televital.scheduling.domain.Login;
import org.springframework.util.StringUtils;

public class LoginFormValidator implements Validator {

	public boolean supports(Class arg0) {
		// TODO Auto-generated method stub
		return Login.class.isAssignableFrom(arg0);
	}
 
	public void validate(Object arg0, Errors errors) {
		// TODO Auto-generated method stub
		Login loginForm = (Login) arg0;
		if(!StringUtils.hasLength(loginForm.getUserId()))
		{
			errors.rejectValue("userId","login.UserId.Required","User Id Required");
			
		}
		if(!StringUtils.hasLength(loginForm.getPassword()))
		{
			errors.rejectValue("password","login.Password.Required","Password Required");
		}

	}

}

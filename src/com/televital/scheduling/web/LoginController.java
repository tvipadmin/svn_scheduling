/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.web;
import org.springframework.orm.ObjectRetrievalFailureException;
import javax.servlet.ServletException;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindException;

import com.televital.scheduling.domain.Login;
import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.passwordencrypt.IPasswordCodec;
import com.televital.scheduling.passwordencrypt.EncryptionException;
import com.televital.scheduling.utils.SchedulingConstants;

import java.util.List;

import org.apache.log4j.Logger;


public class LoginController extends AbstractScheduleController{
	
	private IPasswordCodec passwordCodec;
		
	public void setPasswordCodec(IPasswordCodec passwordCodec)
	{
		this.passwordCodec = passwordCodec;
	}
		
	private static final Logger log4log = Logger.getLogger(LoginController.class);
	
	public LoginController()
	{
		setCommandClass(Login.class);
		setSessionForm(true); 
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,Object command,BindException errors) throws ServletException {
		
		try
		{
			Login login =null;
			Login loginForm = (Login)command;
			log4log.info("Form Entered UserId: "+loginForm.getUserId());
			if(getScheduleFacade() == null)
			{
				log4log.error("scheduleFacade object is null or not set.");
				return new ModelAndView("GeneralErrorView");
			}
			List loginList = this.getScheduleFacade().loadLogin(loginForm.getUserId());
			
			if(loginList.size() <= 0)
			{
				//redirecting user not found view.
				log4log.debug("Login object for the user specified id is not found.");
				return new ModelAndView("UserNone");		
			}
			else if(loginList.size() >0)
			{
				log4log.debug("Login object for the user id found.");
				login = (Login)loginList.get(0);			
			}
			//encrypting user input password to verify with database password
			log4log.debug("Encrypting user password for comparing.");
			String encryptedPassword = "";
			//encrypting user input password
			try
			{
				//encrypting the user password for validating.
				encryptedPassword = passwordCodec.encrypt(loginForm.getPassword());
				log4log.info("Remove It Later(Encrypted Password) "+encryptedPassword);
			}
			catch(EncryptionException e)
			{
				//logging exception
				log4log.error("Couldn't encrypt user password : "+e.toString());
				//redirecting wrong password view.
				return new ModelAndView("WrongPassword");
			}
	
			//verifying user password with database password
			log4log.debug("comparing user pasword with database stored password.");
			if(login.getPassword().equals(encryptedPassword))
			{			
		    	HttpSession session = request.getSession(true);
				  //setting login to session
				session.setAttribute(SchedulingConstants.SESSION_VAR_LOGIN,login);
			    Hospital hospital = this.getScheduleFacade().loadHospital(login.getHospitalId());
                 //setting hospital to session
				session.setAttribute(SchedulingConstants.SESSION_VAR_HOSPITAL,hospital);
					
			 if(login.getAccountType().equals(SchedulingConstants.LOGIN_VAR_ADMIN))
					{
						//redirecting to admin view.
				         log4log.info("tcil");
						return new ModelAndView("TCILLoggedIn");
					}
					else  if(login.getAccountType().equals(SchedulingConstants.LOGIN_VAR_SPECIALIST))
					{
						 log4log.info("ssh");
						return new ModelAndView("SSHLoggedIn");
							
			         }
					else if(login.getAccountType().equals(SchedulingConstants.LOGIN_VAR_REMOTE))
					{
						 log4log.info("pe");
						return new ModelAndView("PELoggedIn");
			        }
			 return new ModelAndView("GeneralErrorView");
			}
			else
			{
				//redirecting wrong password view.
				log4log.info("wrongpassword");
				return new ModelAndView("WrongPassword");
			}
		}//catching not found exception for the user name
		catch(ObjectRetrievalFailureException o)
		{
			//logging the error message
			log4log.error(" User account not found on the system. :"+o.toString());
			//redirecting user not found view.
			return new ModelAndView("UserNone");		
		}
	  }
	}

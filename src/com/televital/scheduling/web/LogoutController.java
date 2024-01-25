/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.televital.scheduling.domain.IScheduleFacade;
import com.televital.scheduling.domain.Login;
import com.televital.scheduling.utils.SchedulingConstants;
import com.televital.scheduling.utils.ScheduleUtils;

public class LogoutController extends AbstractController {
	
	public static final Logger log4log = Logger.getLogger(LogoutController.class);
	private IScheduleFacade scheduleFacade;
	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String logoutView = "LogoutView";
		// TODO Auto-generated method stub 
		log4log.info("Retreving login object for the user from session");
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			log4log.error("Session is null.");
			return new ModelAndView(logoutView);
		}
		Login login = (Login)session.getAttribute(SchedulingConstants.SESSION_VAR_LOGIN);
		if(login == null)
		{
			log4log.error("Cannot retrive login object from the session.");
			return new ModelAndView(logoutView);
		}
		//invaldiing the current session
		log4log.info("Invalidating the session");
		if(session != null)
		{
			session.invalidate();
		}
		//updating login status
		
		return new ModelAndView(logoutView);
	}
	public IScheduleFacade getScheduleFacade() {
		return scheduleFacade;
	}
	public void setScheduleFacade(IScheduleFacade scheduleFacade) {
		this.scheduleFacade = scheduleFacade;
	}

}


package com.televital.scheduling.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.ModelAndView;

public class ViewsController extends MultiActionController implements
		InitializingBean {

	//private static final Logger log = Logger.getLogger(ViewsController.class);
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}
	
	public ModelAndView SSHFrameHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("SSHFrameView"); 
	}

	public ModelAndView PEFrameHandler(HttpServletRequest request,HttpServletResponse reponse) // inserted by juliet mary
	{ 
		return new ModelAndView("PEFrameView"); 
	}

	public ModelAndView TCILFrameHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("TCILFrameView"); 
	}

	public ModelAndView GeneralErrorHandler(HttpServletRequest request,HttpServletResponse response)
	{ 
		return new ModelAndView("GeneralErrorView"); 
	}
	
	public ModelAndView AvailabilityRequestsHandler(HttpServletRequest request,HttpServletResponse response)  // inserted by juliet mary
	{ 
		return new ModelAndView("AvailabilityRequestsView"); 
	}
	
	public ModelAndView AvailabilityHandler(HttpServletRequest request,HttpServletResponse response)  // inserted by juliet mary
	{ 
		return new ModelAndView("AvailabilityView"); 
	}
		
  }

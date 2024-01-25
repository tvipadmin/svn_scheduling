/* 
  * 
  * Sumathi
  */
package com.televital.scheduling.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.util.WebUtils;

import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.IScheduleFacade;
import com.televital.scheduling.domain.Login;
import com.televital.scheduling.utils.SchedulingConstants;
import com.televital.scheduling.utils.ScheduleUtils;

public class ListHospitalController extends AbstractController implements
		InitializingBean {
	private static final Logger log = Logger.getLogger(ListHospitalController.class);
	
	private IScheduleFacade scheduleFacade;
	private Integer recordsPerPage;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		/* Check if the user is authorized to access this controller */
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_ADMIN,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
		
		String page = (String)request.getParameter("page");
		
		PagedListHolder listHolder = null;
		
		if(page == null)
		{	
			List hospitalList = null;
			
			try
			{
				hospitalList = this.getScheduleFacade().loadRSHospitals();
			}
			catch(Exception e)
			{
				log.error("Error getting hospital List. Exception="+e.toString());
				return new ModelAndView("GeneralError");
			}
			
			Login login = (Login) WebUtils.getSessionAttribute(request, SchedulingConstants.SESSION_VAR_LOGIN);
			listHolder = new PagedListHolder(hospitalList);
			listHolder.setPageSize(recordsPerPage);
			request.getSession().setAttribute(SchedulingConstants.SESSION_VAR_PAGE_LIST_HOLDER,listHolder);
		}
		else
		{
			listHolder = (PagedListHolder) request.getSession().getAttribute(SchedulingConstants.SESSION_VAR_PAGE_LIST_HOLDER);
			
			if(page.equals(SchedulingConstants.DISPLAY_PAGE_FIRST))
			{
				listHolder.setPage(0);
			}
			else if(page.equals(SchedulingConstants.DISPLAY_PAGE_NEXT))
			{
				listHolder.nextPage();
			}
			else if(page.equals(SchedulingConstants.DISPLAY_PAGE_PREVIOUS))
			{
				listHolder.previousPage();
			}
			else if(page.equals(SchedulingConstants.DISPLAY_PAGE_LAST))
			{
				listHolder.setPage(listHolder.getPageCount()-1);
			}
		}
		
		return new ModelAndView("ListHospitalView","listHolder",listHolder);
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	public IScheduleFacade getScheduleFacade() {
		return scheduleFacade;
	}

	public void setScheduleFacade(IScheduleFacade scheduleFacade) {
		this.scheduleFacade = scheduleFacade;
	}

	public Integer getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(Integer recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
}

/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.utils;

import org.apache.log4j.Logger;

public abstract class SchedulingConstants {
	
	public static final Logger log = Logger.getLogger(SchedulingConstants.class);
	
	
	/*** SESSION VARIABLES ***/
	public static final String SESSION_VAR_PAGE_LIST_HOLDER		= "pageListHolder";
		
	public static final String DISPLAY_PAGE_FIRST	 			= "first";
	public static final String DISPLAY_PAGE_LAST	 			= "last";
	public static final String DISPLAY_PAGE_NEXT	 			= "next";
	public static final String DISPLAY_PAGE_PREVIOUS	 		= "previous";
	public static final String SESSION_VAR_HOSPITAL				= "hospital";
	public static final String SESSION_VAR_USERID			    = "userid";
	public static final String SESSION_VAR_LOGIN				= "login";
	public static final String LOGIN_VAR_ADMIN			        = "TCILADMIN";
	public static final String LOGIN_VAR_SPECIALIST			    = "SPECIALISTADMIN";
	public static final String LOGIN_VAR_REMOTE				    = "PATIENTADMIN";
	
}

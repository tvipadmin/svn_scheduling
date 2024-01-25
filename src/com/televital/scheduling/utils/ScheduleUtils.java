package com.televital.scheduling.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import sun.util.resources.CalendarData_uk;



public class ScheduleUtils {
	   private SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	   private SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
	    
	   public String daynames[] ={"Sunday", "Monday", "Tuesday","Wednesday", "Thursday", "Friday", "Saturday"};
	   private  Date now = new Date();
	    
	   public  String strDate = sdfDate.format(now);
	   public  String strTime = sdfTime.format(now);
	    
	   public Calendar cal = Calendar.getInstance();
	   public  int day = cal.get(Calendar.DATE);
	   public  int month = cal.get(Calendar.MONTH) + 1;
	   public  int year = cal.get(Calendar.YEAR);
	   public  int dow = cal.get(Calendar.DAY_OF_WEEK);
	   public int dom = cal.get(Calendar.DAY_OF_MONTH);
	   public int doy = cal.get(Calendar.DAY_OF_YEAR);
	   
	   public static int compareTime(String fromHour,String fromMin, String toHour,String toMin)
	   {
		   int fH = Integer.parseInt(fromHour);
		   int fM = Integer.parseInt(fromMin);
		   int tH = Integer.parseInt(toHour);
		   int tM = Integer.parseInt(toMin);
		   if(tH < fH)
			   return 1;
		   else if(fH == tH)
		   {
			   if(tM < fM)
				   return 1;
			   else if(fM == tM)
				   return 1;
			   else
				   return 0;
		   }
		   else
			   return 0;

	   }
	   
	   public static boolean stringValid(String str)
	   {
		
		   return str.matches("[a-zA-Z ]*");  
		   
	   }
	   
	   public static boolean isAuthorizedUser(String user,String currentUser)
		{
			if(user.equals(currentUser)) return true;
			return false;
		}
	   
	   public static List removeDuplicateWithOrder(List arlList)
	   {
	   Set set = new HashSet();
	   List newList = new ArrayList();
	   for (Iterator iter = arlList.iterator(); iter.hasNext(); ) {
	   Object element = iter.next();
	     if (set.add(element))
	        newList.add(element);
	      }
	      arlList.clear();
	      arlList.addAll(newList);
	      return arlList;
	  }

	   	       
}

/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.domain;
import java.io.Serializable;


public class Schedule implements Serializable {
	
	
	  private String scheduleId;		        
	  private String specialty;			
	  private String fromTime;			
	  private String toTime;
	  private String day;
	  private String fromHour;	//inserted by sumathi
	  private String fromMin;  //inserted by sumathi
	  private String toHour;   //inserted by sumathi
	  private String toMin;   //inserted by sumathi
	  
	  public String getScheduleId()
	  {
		  return this.scheduleId;
	  }
	  
	  public String getSpecialty()
	  {
		  return this.specialty;
	  }
	  
	  public String getFromTime()
	  {
		  return this.fromTime;
	  }
	  
	  public String getToTime()
	  {
		  return this.toTime;
	  }
	 
	  public String getDay()
	  {
		  return this.day;
	  }
	  
	  public void setScheduleId(String scheduleId)
	  {
		  this.scheduleId=scheduleId;
	  }

	  public void setSpecialty(String specialty)
	  {
		  this.specialty=specialty;
	  }
	  
	  public void setFromTime(String fromTime)
	  {
		  this.fromTime=fromTime;
	  }
	  
	  public void setToTime(String toTime)
	  {
		  this.toTime=toTime;
	  }
	  
	  public void setDay(String day)
	  {
		  this.day=day;
	  }
	  	  
	  public String getFromHour()
	  {
			return fromHour;
	  }

	  public void setFromHour(String fromHour)
	  {
	    	this.fromHour = fromHour;
	  }

	  public String getFromMin() 
	  {
			return fromMin;
	  }

   	  public void setFromMin(String fromMin) 
   	  {
		   this.fromMin = fromMin;
	  }

   	  public String getToHour()
   	  {
		  return toHour;
	  }

	  public void setToHour(String toHour) 
	  {
		 this.toHour = toHour;
	  }

	  public String getToMin() 
	  {
		 return toMin;
	  }

	  public void setToMin(String toMin) 
	  {
		 this.toMin = toMin;
	  } 
 }

/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.domain;

import java.io.Serializable;


public class SSHSchedule extends Schedule implements  Serializable
   { 
	  private Hospital hospital;
	  
	  public Hospital getHospital() 
	  {
		return hospital;
      }

	  public void setHospital(Hospital hospital) 
	  {
		this.hospital = hospital;
	  }              
   }

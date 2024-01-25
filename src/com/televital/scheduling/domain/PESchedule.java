/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.domain;

import java.io.Serializable;


public class PESchedule extends Schedule implements Serializable {
	 	          
	  private   String       status;
	  private   Hospital     sshHospital;	
	  private   Hospital     peHospital;
	 
      public Hospital getSshHospital()
	  {
		  return this.sshHospital;
	  }

	  public Hospital getPeHospital()
	  {
		  return this.peHospital;
	  }
  
	  public void setSshHospital(Hospital sshHosital)
	  {
		  this.sshHospital=sshHosital;
	  }

	  public void setPeHospital(Hospital peHosital)
	  {
		  this.peHospital=peHosital;
	  }
	 	  
	  public void setStatus(String Status)
	  {
		  this.status=Status;
	  }
	  	 
	  public String getStatus()
	  {
		  return this.status;
	  }
  }

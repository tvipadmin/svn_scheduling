/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.domain;

import java.io.Serializable;


public class FinalSchedule extends Schedule implements Serializable {
	
	  private Hospital sshHospital;	
	  private Hospital peHospital;
	 
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

	  
}

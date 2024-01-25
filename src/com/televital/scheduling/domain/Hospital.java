/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.domain;

import java.io.Serializable;


public class Hospital  implements Serializable{
	
	
	private String hospitalId; 			
	private String hospitalName; 		
	private String hospitalCode; 		
	private String activationKey;		
	private String logo;					
	private String url;					
	private String disclaimer;		
	private String tstamp;	
	private String hospitalType;
	private Address address;
	
	public void setHospitalId(String hospitalId)
	{
		this.hospitalId = hospitalId;
	}
	
	public void setHospitalName(String hospitalName )
	{
		this.hospitalName=hospitalName;
	}
	public void setHospitalCode(String hospitalCode) 
	{
		this.hospitalCode=hospitalCode;
	}
	public void setActivationKey(String activationKey)
	{
		this.activationKey = activationKey;
	}
	public void setLogo(String logo)
	{
		this.logo = logo;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public void setDisclaimer(String disclaimer)
	{
		this.disclaimer = disclaimer;
	}
	
	
	public void setTstamp(String tStamp)
	{
		this.tstamp = tStamp;
	}
	
	public void setHospitalType(String hospitalType)
	{
		this.hospitalType = hospitalType;
	}
	
	public void setAddress(Address address)
	{
		this.address=address;
	}
	
	public String getHospitalId()
	{
		return this.hospitalId ;
	}
	
	public String getHospitalName()
	{
		return this.hospitalName;
	}
	public String getHospitalCode() 
	{
		return this.hospitalCode;
	}
	public String getActivationKey()
	{
		return this.activationKey;
	}
	public String getLogo()
	{
		return this.logo;
	}
	
	public String getUrl()
	{
		return this.url;
	}
	
	public String getDisclaimer()
	{
		return this.disclaimer;
	}
	
	
	public String getTstamp()
	{
		return this.tstamp;
	}
	
	public String getHospitalType()
	{
		return this.hospitalType;
	}
	
	public Address  getAddress()
	{
		return this.address;
	}
}

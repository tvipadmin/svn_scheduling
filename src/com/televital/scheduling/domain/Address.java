/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.domain;

import java.io.Serializable;

public class Address implements Serializable {
	private String  addressLine1;			
	private String  addressLine2;			
	private String  city;				
	private String  state;				
	private String  country	;		
	private String  postalCode;			
	private String  contactNumber;			
	private String  faxNumber;			
	private String  email;	
	
	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1=addressLine1;
	}
	
	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2=addressLine2;
	}
	
	public void setCity(String city)
	{
		this.city=city;
	}
	
	public void setState(String state)
	{
		this.state=state;
	}
	
	public void setCountry(String country)
	{
		this.country=country;
	}
	
	public void setPostalCode(String postalCode)
	{
		this.postalCode=postalCode;
	}
	
	public void setContactNumber(String contactNumber)
	{
		this.contactNumber=contactNumber;
	}
	
	public void setFaxNumber(String faxNumber)
	{
		this.faxNumber=faxNumber;
	}
	
	public void setEmail(String email)
	{
		this.email=email;
	}
	public String getAddressLine1()
	{
		return this.addressLine1;
	}
	
	public String getAddressLine2()
	{
		return this.addressLine2;
	}
	
	public String getCity()
	{
		return this.city;
	}
	
	public String getState()
	{
		return this.state;
	}
	
	public String getCountry()
	{
		return this.country;
	}
	
	public String getPostalCode()
	{
		return this.postalCode;
	}
	
	public String getContactNumber()
	{
		return this.contactNumber;
	}
	
	public String getFaxNumber()
	{
		return this.faxNumber;
	}
	
	public String getEmail()
	{
		return this.email;
	}
}

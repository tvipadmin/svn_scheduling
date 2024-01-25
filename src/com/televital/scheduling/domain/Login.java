/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.domain;

import java.io.Serializable;

import com.televital.scheduling.domain.Address;


public class Login  implements Serializable{
	
	private String  hospitalId;
	private String  userId;
	private String  password;
	private String  firstName;			
	private String  lastName;		
	private String  tstamp;		
	private String  accountType;	
	private Address address;
	private Hospital hospital;
	
	public String getHospitalId()
	{
		return this.hospitalId;
	}
	
	public String getUserId()
	{
		return this.userId;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public String getTstamp()
	{
		return this.tstamp;
	}
	
	public String getAccountType()
	{
		return this.accountType;
	}
	
	public Address getAddress()
	{
		return this.address;
	}
	
	public void setHospitalId(String hospitalId)
	{
		this.hospitalId =hospitalId;
	}
	
	public void setUserId(String userId)
	{
		this.userId=userId;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	public void setFirstName(String firstName)
	{
		this.firstName=firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName=lastName;
	}
	
	
	public void setTstamp(String tStamp)
	{
		this.tstamp=tStamp;
	}
	
	public void setAccountType(String accountType)
	{
		this.accountType=accountType;
	}
	
	public void setAddress(Address address)
	{
		this.address=address;
	}
	
	public void setHospital(Hospital hospital)
	{
		this.hospital=hospital;
	}
	
	public Hospital getHospital()
	{
		return this.hospital;
	}
}

package com.televital.scheduling.domain.form;

import com.televital.scheduling.domain.Hospital;

public class HospitalTypeBackingObject {

	private Hospital hospital;
	private String hospitalType;
	
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public String getHospitalType() {
		return hospitalType;
	}
	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

}
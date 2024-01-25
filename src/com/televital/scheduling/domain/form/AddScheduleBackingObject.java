package com.televital.scheduling.domain.form;

import java.util.List;
import java.util.ArrayList;
import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.SSHSchedule;
import com.televital.scheduling.domain.PESchedule;
import com.televital.scheduling.domain.FinalSchedule;

public class AddScheduleBackingObject{
	private List finalScheduleList;
	private List<Hospital> patientEndList;
	private List<Hospital> specialtyList;
	private String[] hrsArray;
	private String[] minArray;
	private String[] dayArray;
	private FinalSchedule finalSchedule;
	private SSHSchedule sshSchedule;
	private PESchedule peSchedule;
	private String day;
	private String hospitalName;
	private boolean searchFilters;
	
	
	public boolean isSearchFilters() {
		return searchFilters;
	}
	public void setSearchFilters(boolean searchFilters) {
		this.searchFilters = searchFilters;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public FinalSchedule getFinalSchedule() {
		return finalSchedule;
	}
	public void setFinalSchedule(FinalSchedule finalSchedule) {
		this.finalSchedule = finalSchedule;
	}
	public void setSpecialtyList(List<Hospital> specialtyList) {
		this.specialtyList = specialtyList;
	}
	public List getSpecialtyList()
	{
		return this.specialtyList;
	}

	public void setPatientEndList(List patientEndList)
	{
		this.patientEndList = patientEndList;
	}

	public List getPatientEndList()
	{
		return this.patientEndList;
	}
	
	public String[] getHrsArray() {
		return hrsArray;
	}

	public void setHrsArray(String[] hrsArray) {
		this.hrsArray = hrsArray;
	}

	public String[] getMinArray() {
		return minArray;
	}

	public void setMinArray(String[] minArray) {
		this.minArray = minArray;
	}
	public String[] getDayArray() {
		return dayArray;
	}
	public void setDayArray(String[] dayArray) {
		this.dayArray = dayArray;
	}
	public List getFinalScheduleList() {
		return finalScheduleList;
	}
	public void setFinalScheduleList(List finalScheduleList) {
		this.finalScheduleList = finalScheduleList;
	}
	public SSHSchedule getSshSchedule() {
		return sshSchedule;
	}
	public void setSshSchedule(SSHSchedule sshSchedule) {
		this.sshSchedule = sshSchedule;
	}
	public PESchedule getPeSchedule() {
		return peSchedule;
	}
	public void setPeSchedule(PESchedule peSchedule) {
		this.peSchedule = peSchedule;
	}
	
	
	

}

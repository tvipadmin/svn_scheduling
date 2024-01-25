/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.domain;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;



public interface IScheduleFacade {
	
	public Hospital loadHospital(String hid) throws DataAccessException,ObjectRetrievalFailureException; //inserted by juliet
	public List  findSchedulePEView(String sshid,String peid,String day) throws DataAccessException,ObjectRetrievalFailureException;
	public List  checkHospitalID(String id) throws DataAccessException,ObjectRetrievalFailureException; //inserted by juliet
	public List  checkFinalSchID(String sshid,String peid,String time, String day) throws DataAccessException,ObjectRetrievalFailureException; //inserted by juliet
	public List  loadPatientId()  throws DataAccessException,ObjectRetrievalFailureException; //inserted by juliet
	public List  loadSSHId()  throws DataAccessException,ObjectRetrievalFailureException; //inserted by juliet
	public List  findSchedule(String sshid,String peid,String day)throws DataAccessException,ObjectRetrievalFailureException; //inserted by juliet
	public List  findScheduleDay(String day)throws DataAccessException,ObjectRetrievalFailureException; //inserted by juliet
	public void  deleteFinalSchedule(FinalSchedule finalschedule) throws DataAccessException; //inserted by juliet
	public void deleteSSHSchedule(SSHSchedule finalschedule) throws DataAccessException;
	public List loadLogin(String userId) throws DataAccessException,ObjectRetrievalFailureException;
	public void  checkPEID(String id) throws DataAccessException,ObjectRetrievalFailureException; // inserted by sumathi
	public List<SSHSchedule> loadSSHSchedule(String sshid,String day) throws DataAccessException,ObjectRetrievalFailureException;
	public List<PESchedule> loadPESchedule(String hid) throws DataAccessException,ObjectRetrievalFailureException;
	public Login loadLoginObject(String hid) throws DataAccessException,ObjectRetrievalFailureException;
	public List  loadPEHospitals()  throws DataAccessException,ObjectRetrievalFailureException; // inserted by sumathi
	public List  loadSSHospitals()  throws DataAccessException,ObjectRetrievalFailureException; // inserted by sumathi
	public void storeFinalSchedule(FinalSchedule finalSchedule) throws DataAccessException,ObjectRetrievalFailureException; // inserted by sumathi
	public FinalSchedule loadFinalSchedule(String scheduleId) throws DataAccessException,ObjectRetrievalFailureException;
	public void updateFinalSchedule(FinalSchedule finalSchedule) throws DataAccessException,ObjectRetrievalFailureException;
	public void storeSSHSchedule(SSHSchedule sshSchedule) throws DataAccessException,ObjectRetrievalFailureException;  // inserted by sumathi
	public SSHSchedule loadSSHSchedule(String scheduleId) throws DataAccessException,ObjectRetrievalFailureException;
	public void updateSSHSchedule(SSHSchedule sshSchedule) throws DataAccessException,ObjectRetrievalFailureException;
	public List  findPatientEndSchedule(String sshid,String peid,String day) throws DataAccessException,ObjectRetrievalFailureException;
	public void deletePatientEndSchedule(PESchedule sshschedule) throws DataAccessException;
	public void storePESchedule(PESchedule peSchedule) throws DataAccessException,ObjectRetrievalFailureException;  // inserted by sumathi
	public void updatePESchedule(PESchedule peSchedule) throws DataAccessException,ObjectRetrievalFailureException;
	public List findScheduleAvailability(String[] params) throws DataAccessException,ObjectRetrievalFailureException;
	public List findScheduleRequests(String[] params) throws DataAccessException,ObjectRetrievalFailureException;
	public List findSpecialistFinalSchedule(String[] params) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadHospitals()throws DataAccessException,ObjectRetrievalFailureException;
	public void storeHospital(Hospital hospital, Login login) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadRSHospitals()throws DataAccessException,ObjectRetrievalFailureException;
	public void updateHospital(Hospital hospital) throws DataAccessException,ObjectRetrievalFailureException;
	public void updateLogin(Login login) throws DataAccessException,ObjectRetrievalFailureException;
	


}

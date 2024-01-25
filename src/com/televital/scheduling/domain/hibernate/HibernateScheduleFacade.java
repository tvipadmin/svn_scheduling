/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.domain.hibernate;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.televital.scheduling.domain.Hospital;
import com.televital.scheduling.domain.FinalSchedule;
import com.televital.scheduling.domain.IScheduleFacade;
import com.televital.scheduling.domain.Login;
import com.televital.scheduling.domain.PESchedule;
import com.televital.scheduling.domain.SSHSchedule;
import org.apache.log4j.Logger;

public class HibernateScheduleFacade extends HibernateDaoSupport implements IScheduleFacade {
	
	private static final Logger log4log = Logger.getLogger(HibernateScheduleFacade.class);
	
	public Hospital loadHospital(String hid) throws DataAccessException,ObjectRetrievalFailureException
	{
		return (Hospital)getHibernateTemplate().load(Hospital.class,hid);
	}
	
	public Login loadLoginObject(String hid) throws DataAccessException,ObjectRetrievalFailureException
	{
		return (Login)getHibernateTemplate().load(Login.class,hid);
	}
	
	public List  checkHospitalID(String hid) throws DataAccessException,ObjectRetrievalFailureException
	{
		
		return getHibernateTemplate().find("from Hospital lid where lid.hospitalId=?",hid);
		
    }
	public List  checkFinalSchID(String sshid,String peid,String time,String day) throws DataAccessException,ObjectRetrievalFailureException
	{
		
		return getHibernateTemplate().find("from FinalSchedule fs where fs.sshHospital.hospitalId='"+sshid+"' and fs.peHospital.hospitalId='"+peid+"' and fs.day='"+day+"' and '"+time+"' between fs.fromTime and fs.toTime");
	}

	 public List loadPatientId() throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from Hospital hsp where hsp.hospitalType='PATIENTEND'");
	}
	 
	 public List loadSSHId() throws DataAccessException,ObjectRetrievalFailureException
		{
			return this.getHibernateTemplate().find("from Hospital hsp where hsp.hospitalType='SPECIALISTEND'");
		}
	 
	 public List  findSchedule(String sshid,String peid,String day) throws DataAccessException,ObjectRetrievalFailureException
		{
			
			if(sshid.equals("") && peid.equals("") && day.equals(""))
			{
				return getHibernateTemplate().find("from FinalSchedule");
			}
			else if(!sshid.equals("") && peid.equals("") && day.equals(""))
			{
			return getHibernateTemplate().find("from FinalSchedule fs where fs.sshHospital.hospitalId='"+sshid+"'");	
			}
			else if(sshid.equals("") && !peid.equals("") && day.equals(""))
			{
			return getHibernateTemplate().find("from FinalSchedule fs where fs.peHospital.hospitalId='"+peid+"'");	
			}
			else if(!sshid.equals("") && peid.equals("") && !day.equals(""))
			{
			return getHibernateTemplate().find("from FinalSchedule fs where fs.sshHospital.hospitalId='"+sshid+"' and fs.day='"+day+"'");	
			}
			else if(sshid.equals("") && !peid.equals("") && !day.equals(""))
			{
			return getHibernateTemplate().find("from FinalSchedule fs where fs.peHospital.hospitalId='"+peid+"' and fs.day='"+day+"'");	
			}
			else if(!sshid.equals("") && !peid.equals("") && day.equals(""))
			{
				
			return getHibernateTemplate().find("from FinalSchedule fs where fs.sshHospital.hospitalId='"+sshid+"' and fs.peHospital.hospitalId='"+peid+"'");	
			}
			else if(sshid.equals("") && peid.equals("") && !day.equals(""))
			{
				
				return getHibernateTemplate().find("from FinalSchedule fs where fs.day='"+day+"'");	
			}
			else
			{	
			return getHibernateTemplate().find("from FinalSchedule fs where fs.sshHospital.hospitalId='"+sshid+"' and fs.peHospital.hospitalId='"+peid+"' and fs.day='"+day+"'");
			}
		}
	 
	 public List  findSchedulePEView(String sshid,String peid,String day) throws DataAccessException,ObjectRetrievalFailureException
		{
			
			if(sshid.equals("") && day.equals(""))
			{
				
				return getHibernateTemplate().find("from FinalSchedule fs where fs.peHospital.hospitalId='"+peid+"'");
			}
			else if(!sshid.equals("") && day.equals(""))
			{
				
			return getHibernateTemplate().find("from FinalSchedule fs where fs.sshHospital.hospitalId='"+sshid+"' and fs.peHospital.hospitalId='"+peid+"'");	
			}
			else if(sshid.equals("") && !day.equals(""))
			{
				
				return getHibernateTemplate().find("from FinalSchedule fs where fs.day='"+day+"' and fs.peHospital.hospitalId='"+peid+"'");	
			}
			else
			{	
				
			return getHibernateTemplate().find("from FinalSchedule fs where fs.sshHospital.hospitalId='"+sshid+"' and fs.peHospital.hospitalId='"+peid+"' and fs.day='"+day+"'");
			}
		}
	 
	 
	 public List  findScheduleDay(String day)throws DataAccessException,ObjectRetrievalFailureException
	 {
		 return getHibernateTemplate().find("from FinalSchedule fs where fs.day='"+day+"'");
	 }
	 
	 public List loadLogin(String userId) throws DataAccessException,ObjectRetrievalFailureException
		{
			return getHibernateTemplate().find("from Login login where login.userId='"+userId+"'");
		}
	 
	 
	 public List  findPatientEndSchedule(String sshid,String peid,String day) throws DataAccessException,ObjectRetrievalFailureException
		{
		      
			if(sshid.equals("") && day.equals(""))
			{
				
				return getHibernateTemplate().find("from PESchedule pes where pes.peHospital.hospitalId='"+peid+"'");
			}
			else if(!sshid.equals("") && day.equals(""))
			{
				
			return getHibernateTemplate().find("from PESchedule pes where pes.peHospital.hospitalId='"+peid+"' and pes.sshHospital.hospitalId='"+sshid+"' ");	
			}
			else if(sshid.equals("") && !day.equals(""))
			{
				
			return getHibernateTemplate().find("from PESchedule pes where  pes.day='"+day+"' and pes.peHospital.hospitalId='"+peid+"'");	
			}
			else
			{	
				
			return getHibernateTemplate().find("from PESchedule pes where pes.peHospital.hospitalId='"+peid+"' and pes.sshHospital.hospitalId='"+sshid+"' and pes.day='"+day+"'");
			}
		}
	 
	/* public void deleteFinalSchedule(String sshid,String peid,String day,String specality,String fromtime,String totime) throws DataAccessException
		{
			this.getHibernateTemplate().delete("from FinalSchedule fs where fs.sshHospitalId='"+sshid+"' and fs.peHospitalId='"+peid+"' and fs.day='"+day+"' and fs.");
		}*/
	 
	 public void deleteFinalSchedule(FinalSchedule finalschedule) throws DataAccessException
		{
			this.getHibernateTemplate().delete(finalschedule);
		}
	 
	 public void deletePatientEndSchedule(PESchedule peschedule) throws DataAccessException
		{
		    
			this.getHibernateTemplate().delete(peschedule);
		}
	 
	 public void  checkPEID(String id) throws DataAccessException,ObjectRetrievalFailureException
		{
			//getHibernateTemplate().find("from SSHschedule login where login.userId=?",userId);
			
		}
		public List<SSHSchedule> loadSSHSchedule(String sshid,String day) throws DataAccessException,ObjectRetrievalFailureException
		{
			return this.getHibernateTemplate().find("from SSHSchedule ssh where ssh.hospital.hospitalId='"+sshid+"' and ssh.day='"+day+"'");
			
		}
		
		public List<PESchedule> loadPESchedule(String hid) throws DataAccessException,ObjectRetrievalFailureException
		{
			return this.getHibernateTemplate().find("from PESchedule pe where pe.scheduleId='"+hid+"'");
			
		}
		
		 public void deleteSSHSchedule(SSHSchedule sshschedule) throws DataAccessException
			{
				this.getHibernateTemplate().delete(sshschedule);
			}
		
		 public List<Hospital> loadPEHospitals() throws DataAccessException,ObjectRetrievalFailureException // inserted by sumathi
		{
			
			return this.getHibernateTemplate().find("from Hospital hospital where hospital.hospitalType='PATIENTEND'");
		}
		 
		 public List<Hospital> loadSSHospitals() throws DataAccessException,ObjectRetrievalFailureException // inserted by sumathi
			{
			 
				return this.getHibernateTemplate().find("from Hospital hospital  where hospital.hospitalType='SPECIALISTEND'");
			}
		 public void storeFinalSchedule(FinalSchedule finalSchedule) throws DataAccessException,ObjectRetrievalFailureException  // inserted by sumathi
			{
			 
				this.getHibernateTemplate().save(finalSchedule);
				
			}
		 
		 public FinalSchedule loadFinalSchedule(String scheduleId)throws DataAccessException,ObjectRetrievalFailureException
			{
			  
				return (FinalSchedule)this.getHibernateTemplate().load(FinalSchedule.class,scheduleId);
			}
			public void updateFinalSchedule(FinalSchedule finalSchedule) throws DataAccessException,ObjectRetrievalFailureException
			{
				getHibernateTemplate().update(finalSchedule);
		    }
			
			public void storeSSHSchedule(SSHSchedule sshSchedule) throws DataAccessException,ObjectRetrievalFailureException  // inserted by sumathi
			{
			 
				this.getHibernateTemplate().save(sshSchedule);
			}
			public SSHSchedule loadSSHSchedule(String scheduleId) throws DataAccessException,ObjectRetrievalFailureException
			{
				return (SSHSchedule)this.getHibernateTemplate().load(SSHSchedule.class,scheduleId);
			}
			public void updateSSHSchedule(SSHSchedule sshSchedule) throws DataAccessException,ObjectRetrievalFailureException
			{
				getHibernateTemplate().update(sshSchedule);
			}
			
			public void storePESchedule(PESchedule peSchedule) throws DataAccessException,ObjectRetrievalFailureException  // inserted by sumathi
			{
			 
				this.getHibernateTemplate().save(peSchedule);
			}
			public void updatePESchedule(PESchedule peSchedule) throws DataAccessException,ObjectRetrievalFailureException
			{
				getHibernateTemplate().update(peSchedule);
			}
			public List findScheduleAvailability(String[] params) throws DataAccessException,ObjectRetrievalFailureException
			{
				String hid= params[0];
				String day= params[1];
				
				if(hid.equals("") && day.equals(""))
				{
					return getHibernateTemplate().find("from SSHSchedule");
				}
				else if (!hid.equals("") && day.equals(""))
				{
					return getHibernateTemplate().find("from SSHSchedule sshSchedule where sshSchedule.hospital.hospitalId='"+params[0]+"'");
				}
				else if (hid.equals("") && !day.equals(""))
				{
					return getHibernateTemplate().find("from SSHSchedule sshSchedule where sshSchedule.day='"+params[1]+"'");
				}
				else
				{
					return getHibernateTemplate().find("from SSHSchedule sshSchedule where sshSchedule.hospital.hospitalId='"+params[0]+"' and sshSchedule.day='"+params[1]+"'");// sshSchedule where sshSchedule.hospital.hospitalId='SPECIALISTEND'");
				}
			}
			
			public List findScheduleRequests(String[] params) throws DataAccessException,ObjectRetrievalFailureException
			{
				String hid= params[0];
				String day= params[1];
				
				if(hid.equals("") && day.equals(""))
				{
					return getHibernateTemplate().find("from PESchedule");
				}
				else if (!hid.equals("") && day.equals(""))
				{
					return getHibernateTemplate().find("from PESchedule peSchedule where peSchedule.peHospital.hospitalId='"+params[0]+"'");
				}
				else if (hid.equals("") && !day.equals(""))
				{
					return getHibernateTemplate().find("from PESchedule peSchedule where peSchedule.day='"+params[1]+"'");
				}
				else
				{
					return getHibernateTemplate().find("from PESchedule peSchedule where peSchedule.peHospital.hospitalId='"+params[0]+"' and peSchedule.day='"+params[1]+"'");// peSchedule where peSchedule.hospital.hospitalId='SPECIALISTEND'");
				}
			}
			
			public List findSpecialistFinalSchedule(String[] params) throws DataAccessException,ObjectRetrievalFailureException
			{
				String hid= params[0];
				String day= params[1];
				
				if (!hid.equals("") && day.equals(""))
				{
					return getHibernateTemplate().find("from FinalSchedule finalSchedule where finalSchedule.sshHospital.hospitalId='"+params[0]+"'");
				}
				else
				{
					return getHibernateTemplate().find("from FinalSchedule finalSchedule where finalSchedule.sshHospital.hospitalId='"+params[0]+"' and finalSchedule.day='"+params[1]+"'");// peSchedule where peSchedule.hospital.hospitalId='SPECIALISTEND'");
				}
			}

			public List loadHospitals()throws DataAccessException,ObjectRetrievalFailureException
			{
				return this.getHibernateTemplate().find("from Hospital hospital order by hospital.hospitalName");
			}
			
			public List loadRSHospitals()throws DataAccessException,ObjectRetrievalFailureException
			{
				return this.getHibernateTemplate().find("from Hospital hospital  where hospital.hospitalType ='PATIENTEND' or hospital.hospitalType ='SPECIALISTEND' order by hospital.hospitalName");
			}
			
			public void storeHospital(Hospital hospital,Login login) throws DataAccessException,ObjectRetrievalFailureException
			{
				 
				 getHibernateTemplate().save(hospital);
				 getHibernateTemplate().save(login);

			}

			public void updateHospital(Hospital hospital) throws DataAccessException,ObjectRetrievalFailureException
			{
				getHibernateTemplate().update(hospital);
			}

			public void updateLogin(Login login) throws DataAccessException,ObjectRetrievalFailureException
			{
				getHibernateTemplate().update(login);
			}
}

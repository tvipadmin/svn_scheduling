/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.web;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import com.televital.scheduling.domain.IScheduleFacade;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.vitalware.domain.VitalWareSerializeUpdateObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.apache.log4j.Logger;

public class VerificationController extends AbstractController {
	
	private static final Logger log = Logger.getLogger(VerificationController.class);
	
	private IScheduleFacade scheduleFacade;
		
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub

		    res.setContentType("application/x-java-serialized-object");
		    VitalWareSerializeUpdateObject objSerialize  = new VitalWareSerializeUpdateObject();
      	    ObjectInputStream objectInput = new ObjectInputStream(req.getInputStream());
      	    Map<String,Object> updatedata = new HashMap<String,Object>();
		    try
		    {
              log.info("In VerificationController");
              ScheduleUtils getDateTime = new ScheduleUtils();
              String ldate = getDateTime.strDate;
			  String ltime = getDateTime.strTime;
			  int ldow =  getDateTime.dow;
			  String lday =  getDateTime.daynames[ldow-1];
		 	  log.info(ldate+" "+ltime+" "+lday);
			  objSerialize = (VitalWareSerializeUpdateObject)objectInput.readObject();
			  log.info("Patient Id: "+objSerialize.getMap().get("PEID"));
			  log.info(" SSH ID"+ objSerialize.getMap().get("SSHID"));
			  log.info(" Status "+objSerialize.getMap().get("status"));
			  log.error("Patient Id: "+objSerialize.getMap().get("PEID") + " SSH ID"+ objSerialize.getMap().get("SSHID")+" Ststus "+objSerialize.getMap().get("status"));
			  String sshid = objSerialize.getMap().get("SSHID").toString();
			  String peid = objSerialize.getMap().get("PEID").toString();
			  List sshidL=getScheduleFacade().checkHospitalID(sshid); //check sshid exists in hospital table
					
			  if(sshidL.size() != 0)
			   {
				  List peidL=getScheduleFacade().checkHospitalID(peid); //check peid exists in hospital table
				  if(peidL.size() != 0)
				   {
				   	 List check = getScheduleFacade().checkFinalSchID(sshid,peid,ltime,lday); // check schedule exist in FinalSchedule table
				     if(check.size() !=0)
				     {
					    try
						 {							
							 updatedata.put("status","yes");
							 objSerialize.setMap(updatedata);
							 this.writeResponse(objSerialize,res);
				    	 }
						 catch(Exception e)
						  {
							 log.error("Error while Writing object from applications due :"+e.toString());
							 updatedata.put("status","error");
							 this.writeResponse(objSerialize,res);
						  }
						 finally
						  {
							objectInput.close();
						  }
						 log.info("Appointment Is Scheduled");
				      }
				      else
				       {
					     try
						 {
						   updatedata.put("status","no");
						   objSerialize.setMap(updatedata);
						   this.writeResponse(objSerialize,res);
						 }
						 catch(Exception e)
						 {
							log.error("Error while Writing object from applications due :"+e.toString());
							updatedata.put("status","error");
							this.writeResponse(objSerialize,res);
						 }
						 finally
						 {
							objectInput.close();
						 }
						 log.info("Appointment is not scheduled");
				       }
				   }
			      else
			       {
				    try
					 {
						 updatedata.put("status","error");
						 objSerialize.setMap(updatedata);
						 this.writeResponse(objSerialize,res);
					 }
					 catch(Exception e)
					  {
						log.error("Error while Writing object from applications due :"+e.toString());
						updatedata.put("status","error");
						this.writeResponse(objSerialize,res);
					  }
					 finally
					  {
						objectInput.close();
					  }
					  log.info("PateintEnd Id Doenot exists");
								 
			        }
				  }
			     else
			     {
				   try
					 {
						 updatedata.put("status","error");
						 objSerialize.setMap(updatedata);
						 this.writeResponse(objSerialize,res);
					 }
				    catch(Exception e)
					 {
					    log.error("Error while Writing object from applications due :"+e.toString());
						updatedata.put("status","error");
						this.writeResponse(objSerialize,res);
					 }
					finally
					 {
						objectInput.close();
					 }
					 log.info("Specalist Id Doenot exists");
				
			       }
			 }
		     catch(Exception e)
	 	      {
			    log.error("Error while reading object from applications due :"+e.toString());
			    updatedata.put("status","error");
			    this.writeResponse(objSerialize,res);
		      }
		     finally
		      {
			    objectInput.close();
		      }
		return null;
	  }
   
	  private void writeResponse(VitalWareSerializeUpdateObject obj,HttpServletResponse res)
		{
			try
			{
				OutputStream outstr = res.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(outstr);
				oos.writeObject(obj);
				oos.flush();
				oos.close();
			}
			catch(Exception e)
			{
				log.error("Error while writing to output stream due to :"+e.toString());
			}
	    }
	
     public IScheduleFacade getScheduleFacade()
     {
	   return scheduleFacade;
     }

     public void setScheduleFacade(IScheduleFacade ScheduleFacade)
     {
	    this.scheduleFacade = ScheduleFacade;
     }
   }
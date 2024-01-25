/**
 * @author Juliet Mary
 *
 */

package com.televital.scheduling.web;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;
import com.televital.scheduling.domain.Address;
import com.televital.scheduling.domain.Login;
import com.televital.vitalware.domain.VitalWareSerializeUpdateObject;
import com.televital.scheduling.domain.form.ImportHospitalBackingObject;
import com.televital.scheduling.utils.SchedulingConstants;
import com.televital.scheduling.utils.ScheduleUtils;
import com.televital.scheduling.domain.Hospital;


public class ImportHospitalController extends AbstractScheduleController implements InitializingBean {
	  private final Logger log4log = Logger.getLogger(ImportHospitalController.class);
	  HashMap  xmlAttribute = new HashMap();
	  public ImportHospitalController()
	    {
		     //not using this function because to implement isauthorized function
		     //this.setCommandClass(ImportHospitalController.class);
		   this.setSessionForm(false);
	    }

	protected Object formBackingObject(HttpServletRequest request) throws Exception
	 {
		// TODO Auto-generated method stub
		/* Check if the user is authorized to access this controller */
		if(!ScheduleUtils.isAuthorizedUser(SchedulingConstants.LOGIN_VAR_ADMIN,((Login) WebUtils.getSessionAttribute(request,SchedulingConstants.SESSION_VAR_LOGIN)).getAccountType()))
		{
			throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedView"));		
		}
		return new ImportHospitalBackingObject();
	 }
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
		//taking user entered values

		xmlAttribute.put("xmlFileFlag","false");

		ImportHospitalBackingObject importObj  = (ImportHospitalBackingObject)command;

		String URLinfo = "http://" + importObj.getIpAddress() + "/VitalWare20/SchedulingHospitalProvider.htm";
		
		ObjectOutputStream objectoutPut =null;
		ObjectInputStream outputFromServlet= null;
		
		VitalWareSerializeUpdateObject resFromServer;
		VitalWareSerializeUpdateObject reqToServer = new VitalWareSerializeUpdateObject();

		log4log.error("Connecting to : "+ URLinfo);
		URL url                  = new URL(URLinfo);
		HttpURLConnection conn   = (HttpURLConnection) url.openConnection();
		try
		{

			log4log.error("Opened Connection " + conn);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/octet-stream");

			Map<String,Object> dataMap = new HashMap<String,Object>();

			dataMap.put("hospitalId",importObj.getHospitalId());
			reqToServer.setMap(dataMap);
			OutputStream out = conn.getOutputStream();
			objectoutPut     = new ObjectOutputStream(out);

			try
			{
				objectoutPut.writeObject(reqToServer);
				log4log.error("Trying to get input from connection: " );
				objectoutPut.flush();
			}catch(Exception e)
			{
				log4log.error("Error: writeObject : " + e.toString());
			}
			finally
			{
				//closing the object out stream
				out.close();
			}
			InputStream in = conn.getInputStream();
			log4log.error("InputStream: " + in.available() );
			outputFromServlet = new ObjectInputStream(in);

		}
		catch(Exception e)
		{
			 log4log.error("Error while writing to the response stream due to :"+e.toString());
	         e.printStackTrace();
	         throw new ModelAndViewDefiningException(new ModelAndView("GeneralErrorView"));
	    }

		//Getting response from servlet.

		try
         {
			log4log.info("before reading objects");
			int inputdata = outputFromServlet.read();
			while(inputdata > 0)
			{
				log4log.info(outputFromServlet.readFields());
			}
  	 		resFromServer = (VitalWareSerializeUpdateObject) outputFromServlet.readObject();
			outputFromServlet.close();
  		   	Map<String, Object> hospitalMap= resFromServer.getMap();
  		    log4log.info("Status : "+hospitalMap.get("status"));
  		    if(!(((String)hospitalMap.get("status")).equalsIgnoreCase("Done")))
  		    	{
  		   			//stop the program by giving some dialog with error
  		   		}
  		    else
  		    {
  		    	Hospital hospital = new Hospital();
  		    	Address hospitalAddress = new Address();
  		    	Login login = new Login(); 				

  		    		
  				//hospitl PATIENTEND,SPECIALISTEND,TCILEND
  		    	log4log.info(hospitalMap.get("hospitalId"));
  		    	hospital.setHospitalId(hospitalMap.get("hospitalId").toString());
  		    	log4log.info(hospitalMap.get("hospitalName"));
  		    	hospital.setHospitalName(hospitalMap.get("hospitalName").toString());
  		    	log4log.info(hospitalMap.get("hospitalCode"));
  		    	hospital.setHospitalCode(hospitalMap.get("hospitalCode").toString());
  		    	log4log.info(hospitalMap.get("activationKey"));
  		    	if(hospitalMap.get("activationKey") != null)
  		    	{
  		    		hospital.setActivationKey(hospitalMap.get("activationKey").toString());
  		    	}
  		  	    
  		  	    hospitalAddress.setAddressLine1(hospitalMap.get("addressLine1").toString());
  		      	hospitalAddress.setAddressLine2(hospitalMap.get("addressLine2").toString());
		      	hospitalAddress.setCity(hospitalMap.get("city").toString());
		       	hospitalAddress.setContactNumber(hospitalMap.get("contactNumber").toString());
		       	hospitalAddress.setCountry(hospitalMap.get("country").toString());
		       	hospitalAddress.setEmail(hospitalMap.get("email").toString());
		    	hospitalAddress.setFaxNumber(hospitalMap.get("faxNumber").toString());
		    	hospitalAddress.setPostalCode(hospitalMap.get("pinCode").toString());
		    	hospitalAddress.setState(hospitalMap.get("state").toString());
		    	hospital.setUrl(hospitalMap.get("url").toString());
  		    	hospital.setDisclaimer(hospitalMap.get("disclaimer").toString());
  		    	hospital.setTstamp(hospitalMap.get("tstamp").toString());
  			    hospital.setHospitalType(importObj.getHospitalType());
  			    hospital.setAddress(hospitalAddress);
  		    	
  			    //login  PATIENTADMIN,SPECIALISTADMIN,TCILADMIN
  			    
  			    login.setHospitalId(hospitalMap.get("hospitalId").toString());
		    	login.setUserId(hospitalMap.get("userId").toString());
			    login.setPassword(hospitalMap.get("password").toString());
			    login.setFirstName(hospitalMap.get("firstname").toString());
			    login.setLastName(hospitalMap.get("lastname").toString());
		    	login.setTstamp(hospitalMap.get("admintstamp").toString());
		    	hospitalAddress.setAddressLine1(hospitalMap.get("adminAddressline1").toString());
		    	hospitalAddress.setAddressLine2(hospitalMap.get("adminAddressline2").toString());
		    	hospitalAddress.setCity(hospitalMap.get("adminCity").toString());
		    	hospitalAddress.setContactNumber(hospitalMap.get("adminContactNumber").toString());
		    	hospitalAddress.setCountry(hospitalMap.get("adminCountry").toString());
		    	
		    	hospitalAddress.setEmail(hospitalMap.get("adminEmail").toString());
		    	hospitalAddress.setFaxNumber(hospitalMap.get("faxNumber").toString());
		    	hospitalAddress.setPostalCode(hospitalMap.get("adminPostalCode").toString());
		    	hospitalAddress.setState(hospitalMap.get("adminState").toString());
		    	login.setAddress(hospitalAddress);
		    	if(importObj.getHospitalType().equals("SPECIALISTEND"))
  			    {
		    		login.setAccountType("SPECIALISTADMIN");
  			    }
		    	if(importObj.getHospitalType().equals("PATIENTEND"))
		    	{
		    		login.setAccountType("PATIENTADMIN");
		    	}
		    	
				login.setHospital(hospital); 				
				
		    	
		    	this.getScheduleFacade().storeHospital(hospital,login);
		  			    
  		    }
  		  }
		  catch(Exception exc)
  		   	{
  		      log4log.error("error while receiving information from server due to :"+exc.toString());
  		      throw new ModelAndViewDefiningException(new ModelAndView("GeneralErrorView"));
            }

		//returning the success view
		return new ModelAndView(this.getSuccessView());
	}

}





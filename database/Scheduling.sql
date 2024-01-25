-- ----------------------------
-- MySQL Database Script File
-- ----------------------------

--
-- Database Name : Scheduling
--

DROP DATABASE /*!32312 IF EXISTS*/ Scheduling;
CREATE DATABASE /*!32312 IF NOT EXISTS*/ Scheduling;

USE Scheduling;




DROP TABLE IF EXISTS TBL_Hospital;
CREATE TABLE TBL_Hospital
(
  HospitalId 			VARCHAR(255) NOT NULL PRIMARY KEY,
  HospitalName 			VARCHAR(255) NOT NULL,
  HospitalCode 			VARCHAR(255) NOT NULL UNIQUE KEY,
  ActivationKey			VARCHAR(255) DEFAULT NULL UNIQUE KEY,
  Logo				VARCHAR(255) DEFAULT NULL,
  AddressLine1			VARCHAR(255) DEFAULT NULL,
  AddressLine2			VARCHAR(255) DEFAULT NULL,
  City				VARCHAR(255) DEFAULT NULL,
  State				VARCHAR(255) DEFAULT NULL,
  Country			      VARCHAR(255) DEFAULT NULL,
  PostalCode			VARCHAR(255) DEFAULT NULL,
  ContactNumber			VARCHAR(255) DEFAULT NULL,
  FaxNumber		  	      VARCHAR(255) DEFAULT NULL,
  Email				VARCHAR(255) DEFAULT NULL,
  Url			      	VARCHAR(255) DEFAULT NULL,
  Disclaimer			TEXT,
  TStamp			      DATETIME NOT NULL,
  HospitalType                  ENUM('PATIENTEND','SPECIALISTEND','TCILEND') DEFAULT NULL
 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Insert into TBL_Hospital(HospitalId,HospitalName,HospitalCode,ActivationKey,Logo,AddressLine1,AddressLine2,City,State,Country,PostalCode,ContactNumber,FaxNumber,Email,Url,Disclaimer,TStamp,HospitalType) values('tcil-886d-4fe1-bfb0-a1628170c154','Tcil','fortish144','5657g','yy','No 8th cross','Fahinia street','xyz','sss','Africa','5344434','4522333','55465465','appolo@app.com','ww.applolo.com','disc apolo',SYSDATE(),'TCILEND');

DROP TABLE IF EXISTS TBL_Login;
CREATE TABLE TBL_Login
(
  HospitalId		        VARCHAR(255) NOT NULL PRIMARY KEY,
  UserId                  VARCHAR(255) NOT NULL UNIQUE KEY,
  Password			  VARCHAR(255) NOT NULL,
  FirstName 		  VARCHAR(255) NOT NULL,
  LastName			  VARCHAR(255) DEFAULT NULL,
  AddressLine1		  VARCHAR(255) DEFAULT NULL,
  AddressLine2		  VARCHAR(255) DEFAULT NULL,
  City			  VARCHAR(255) DEFAULT NULL,
  State			  VARCHAR(255) DEFAULT NULL,
  Country			  VARCHAR(255) DEFAULT NULL,
  PostalCode		  VARCHAR(255) DEFAULT NULL,
  ContactNumber		  VARCHAR(255) DEFAULT NULL,
  FaxNumber			  VARCHAR(255) DEFAULT NULL,
  Email			  VARCHAR(255) DEFAULT NULL,
  TStamp			  DATETIME NOT NULL,
  AccountType		  ENUM('PATIENTADMIN','SPECIALISTADMIN','TCILADMIN') DEFAULT NULL,
  FOREIGN KEY (HospitalId) REFERENCES TBL_Hospital (HospitalId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Insert into TBL_Login values('tcil-886d-4fe1-bfb0-a1628170c154','admin','ptbH1jPPu1c=','admin','Ariga','No 8th cross','Fahinia street','xyz','sss','Africa','5344434','4522333','55465465','appolo@app.com',SYSDATE(),'TCILADMIN');

DROP TABLE IF EXISTS TBL_SSHSchedule;
CREATE TABLE TBL_SSHSchedule
(
  SSH_Id 		   INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  HospitalId                       VARCHAR(255) NOT NULL ,
  Specialty			   VARCHAR(255) NOT NULL,
  Day			           VARCHAR(20) NOT NULL,
  FromTime			   TIME NOT NULL,
  ToTime			   TIME NOT NULL,
  FOREIGN KEY (HospitalId) REFERENCES TBL_Hospital (HospitalId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS TBL_PESchedule;
CREATE TABLE TBL_PESchedule
(
  PE_Id 		      INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  SSHHospitalId              VARCHAR(255) NOT NULL ,
  PEHospitalId             VARCHAR(255) NOT NULL ,
  Specialty			 VARCHAR(255) NOT NULL,
  Day			       VARCHAR(20) NOT NULL,
  FromTime			 TIME NOT NULL,
  ToTime			 TIME NOT NULL,
  Status		 VARCHAR(50)  NOT NULL,
  FOREIGN KEY (SSHHospitalId) REFERENCES TBL_Hospital(HospitalId),
  FOREIGN KEY (PEHospitalId)  REFERENCES TBL_Hospital(HospitalId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS TBL_FinalSchedule;
CREATE TABLE TBL_FinalSchedule
(
  TCIL_Id		        INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  SSHHospitalId            VARCHAR(255) NOT NULL ,
  PEHospitalId             VARCHAR(255) NOT NULL ,
  Specialty			  VARCHAR(255) NOT NULL,
  Day			        VARCHAR(20) NOT NULL,
  FromTime			  TIME NOT NULL,
  ToTime			  TIME NOT NULL,
  FOREIGN KEY (SSHHospitalId) REFERENCES TBL_Hospital(HospitalId),
  FOREIGN KEY (PEHospitalId)  REFERENCES TBL_Hospital(HospitalId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
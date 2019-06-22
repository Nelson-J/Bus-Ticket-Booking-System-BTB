DROP DATABASE IF EXISTS ticket_booking_system;

CREATE DATABASE IF NOT EXISTS ticket_booking_system;
# -----------------------------------------------------------------------------
#       TABLE : TBLTIME
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS TBLTIME
 (
   DEPARTURETIME TIME NOT NULL  
   , PRIMARY KEY (DEPARTURETIME) 
 ) 
 comment = "TIMETABLE";

# -----------------------------------------------------------------------------
#       TABLE : TBLCLIENT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS TBLCLIENT
 (
   CLIENT_ID_CARD_NO INTEGER(9) NOT NULL  ,
   CLIENT_FIRST_NAME VARCHAR(10) NOT NULL  ,
   CLIENT_LAST_NAME VARCHAR(50) NULL  
   , PRIMARY KEY (CLIENT_ID_CARD_NO) 
 ) 
 comment = "client table";

# -----------------------------------------------------------------------------
#       TABLE : TBLTICKET
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS TBLTICKET
 (
   TICKET_ID INTEGER(5) NOT NULL  ,
   CLIENT_ID_CARD_NO INTEGER(9) NOT NULL  ,
   TICKET_STATUS BOOL NOT NULL  ,
   TICKET_EXPIRY_DATE DATE NOT NULL  
   , PRIMARY KEY (TICKET_ID) 
 ) 
 comment = "ticket table";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE TBLTICKET
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_TBLTICKET_TBLCLIENT
     ON TBLTICKET (CLIENT_ID_CARD_NO ASC);

# -----------------------------------------------------------------------------
#       TABLE : TBLTRIP
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS TBLTRIP
 (
   TRIP_IDENTITY_NUMBER INTEGER(5) NOT NULL  ,
   TRIP_ORIGIN CHAR(32) NOT NULL  ,
   TRIP_DESTINATION CHAR(32) NOT NULL  ,
   TRIP_FARE DECIMAL(13,2) NOT NULL  ,
   TRIP_TIME TIME NOT NULL  
   , PRIMARY KEY (TRIP_IDENTITY_NUMBER) 
 ) 
 comment = "trip table";

# -----------------------------------------------------------------------------
#       TABLE : TBLVEHICLE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS TBLVEHICLE
 (
   VEHICLE_NUMBER CHAR(7) NOT NULL  ,
   NUMBER_OF_SEATS INTEGER(2) NOT NULL  ,
   AVAILABLE_SEATS INTEGER(2) NOT NULL  
   , PRIMARY KEY (VEHICLE_NUMBER) 
 ) 
 comment = "vehicle table";

# -----------------------------------------------------------------------------
#       TABLE : LNKINVOLVES
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS LNKINVOLVES
 (
   TRIP_IDENTITY_NUMBER INTEGER(5) NOT NULL  ,
   VEHICLE_NUMBER CHAR(7) NOT NULL  
   , PRIMARY KEY (TRIP_IDENTITY_NUMBER,VEHICLE_NUMBER) 
 ) 
 comment = "link between vehicle and trip tables";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE LNKINVOLVES
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_LNKINVOLVES_TBLTRIP
     ON LNKINVOLVES (TRIP_IDENTITY_NUMBER ASC);

CREATE  INDEX I_FK_LNKINVOLVES_TBLVEHICLE
     ON LNKINVOLVES (VEHICLE_NUMBER ASC);

# -----------------------------------------------------------------------------
#       TABLE : LNKDEPARTS_AT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS LNKDEPARTS_AT
 (
   TRIP_IDENTITY_NUMBER INTEGER(5) NOT NULL  ,
   DEPARTURETIME TIME NOT NULL  
   , PRIMARY KEY (TRIP_IDENTITY_NUMBER,DEPARTURETIME) 
 ) 
 comment = "DEPATURE LINK";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE LNKDEPARTS_AT
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_LNKDEPARTS_AT_TBLTRIP
     ON LNKDEPARTS_AT (TRIP_IDENTITY_NUMBER ASC);

CREATE  INDEX I_FK_LNKDEPARTS_AT_TBLTIME
     ON LNKDEPARTS_AT (DEPARTURETIME ASC);

# -----------------------------------------------------------------------------
#       TABLE : LNKBOOKS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS LNKBOOKS
 (
   CLIENT_ID_CARD_NO INTEGER(9) NOT NULL  ,
   TRIP_IDENTITY_NUMBER INTEGER(5) NOT NULL  ,
   DATE_TRIP_BOOKED DATETIME NULL  ,
   TRIPDATE DATETIME NOT NULL  
   , PRIMARY KEY (CLIENT_ID_CARD_NO,TRIP_IDENTITY_NUMBER) 
 ) 
 comment = "books link( link between client and trip)";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE LNKBOOKS
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_LNKBOOKS_TBLCLIENT
     ON LNKBOOKS (CLIENT_ID_CARD_NO ASC);

CREATE  INDEX I_FK_LNKBOOKS_TBLTRIP
     ON LNKBOOKS (TRIP_IDENTITY_NUMBER ASC);


# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE TBLTICKET 
  ADD FOREIGN KEY FK_TBLTICKET_TBLCLIENT (CLIENT_ID_CARD_NO)
      REFERENCES TBLCLIENT (CLIENT_ID_CARD_NO) ;


ALTER TABLE LNKINVOLVES 
  ADD FOREIGN KEY FK_LNKINVOLVES_TBLTRIP (TRIP_IDENTITY_NUMBER)
      REFERENCES TBLTRIP (TRIP_IDENTITY_NUMBER) ;


ALTER TABLE LNKINVOLVES 
  ADD FOREIGN KEY FK_LNKINVOLVES_TBLVEHICLE (VEHICLE_NUMBER)
      REFERENCES TBLVEHICLE (VEHICLE_NUMBER) ;


ALTER TABLE LNKDEPARTS_AT 
  ADD FOREIGN KEY FK_LNKDEPARTS_AT_TBLTRIP (TRIP_IDENTITY_NUMBER)
      REFERENCES TBLTRIP (TRIP_IDENTITY_NUMBER) ;


ALTER TABLE LNKDEPARTS_AT 
  ADD FOREIGN KEY FK_LNKDEPARTS_AT_TBLTIME (DEPARTURETIME)
      REFERENCES TBLTIME (DEPARTURETIME) ;


ALTER TABLE LNKBOOKS 
  ADD FOREIGN KEY FK_LNKBOOKS_TBLCLIENT (CLIENT_ID_CARD_NO)
      REFERENCES TBLCLIENT (CLIENT_ID_CARD_NO) ;


ALTER TABLE LNKBOOKS 
  ADD FOREIGN KEY FK_LNKBOOKS_TBLTRIP (TRIP_IDENTITY_NUMBER)
      REFERENCES TBLTRIP (TRIP_IDENTITY_NUMBER) ;


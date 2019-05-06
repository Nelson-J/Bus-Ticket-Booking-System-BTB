-- -----------------------------------------------------------------------------
--             Génération d'une base de données pour
--                           PostgreSQL
--                        (6/5/2019 1:52:57)
-- -----------------------------------------------------------------------------
--      Nom de la base : Ticket Booking System
--      Projet : Accueil Win'Design version 7
--      Auteur : ndzib
--      Date de dernière modification : 6/5/2019 0:14:47
-- -----------------------------------------------------------------------------

drop database Ticket Booking System;
-- -----------------------------------------------------------------------------
--       CREATION DE LA BASE 
-- -----------------------------------------------------------------------------

CREATE DATABASE Ticket Booking System;

-- -----------------------------------------------------------------------------
--       TABLE : TBLCLIENT
-- -----------------------------------------------------------------------------

CREATE TABLE TBLCLIENT
   (
    CLIENT_ID_CARD_NO int4 NOT NULL  ,
    CLIENT_FIRST_NAME varchar(10) NOT NULL  ,
    CLIENT_LAST_NAME varchar(50) NULL  
,   CONSTRAINT PK_TBLCLIENT PRIMARY KEY (CLIENT_ID_CARD_NO)
   );

-- -----------------------------------------------------------------------------
--       TABLE : TBLTICKET
-- -----------------------------------------------------------------------------

CREATE TABLE TBLTICKET
   (
    TICKET_ID int4 NOT NULL  ,
    CLIENT_ID_CARD_NO int4 NOT NULL  ,
    TICKET_STATUS bool NOT NULL  ,
    TICKET_EXPIRY_DATE date NOT NULL  
,   CONSTRAINT PK_TBLTICKET PRIMARY KEY (TICKET_ID)
   );

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE TBLTICKET
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_TBLTICKET_TBLCLIENT
     ON TBLTICKET (CLIENT_ID_CARD_NO)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : TBLTRIP
-- -----------------------------------------------------------------------------

CREATE TABLE TBLTRIP
   (
    TRIP_IDENTITY_NUMBER int4 NOT NULL  ,
    TRIP_ORIGIN char(32) NOT NULL  ,
    TRIP_DESTINATION char(32) NOT NULL  ,
    TRIP_FARE money NOT NULL  
,   CONSTRAINT PK_TBLTRIP PRIMARY KEY (TRIP_IDENTITY_NUMBER)
   );

-- -----------------------------------------------------------------------------
--       TABLE : TBLVEHICLE
-- -----------------------------------------------------------------------------

CREATE TABLE TBLVEHICLE
   (
    VEHICLE_NUMBER int4 NOT NULL  ,
    NUMBER_OF_SEATS int4 NOT NULL  ,
    AVAILABLE_SEATS int4 NOT NULL  
,   CONSTRAINT PK_TBLVEHICLE PRIMARY KEY (VEHICLE_NUMBER)
   );

-- -----------------------------------------------------------------------------
--       TABLE : LNKINVOLVES
-- -----------------------------------------------------------------------------

CREATE TABLE LNKINVOLVES
   (
    TRIP_IDENTITY_NUMBER int4 NOT NULL  ,
    VEHICLE_NUMBER int4 NOT NULL  
,   CONSTRAINT PK_LNKINVOLVES PRIMARY KEY (TRIP_IDENTITY_NUMBER, VEHICLE_NUMBER)
   );

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE LNKINVOLVES
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_LNKINVOLVES_TBLTRIP
     ON LNKINVOLVES (TRIP_IDENTITY_NUMBER)
    ;

CREATE  INDEX I_FK_LNKINVOLVES_TBLVEHICLE
     ON LNKINVOLVES (VEHICLE_NUMBER)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : LNKBOOKS
-- -----------------------------------------------------------------------------

CREATE TABLE LNKBOOKS
   (
    CLIENT_ID_CARD_NO int4 NOT NULL  ,
    TRIP_IDENTITY_NUMBER int4 NOT NULL  ,
    DATE_TRIP_BOOKED timestamp NULL  ,
    TRIPDATE timestamp NOT NULL  
,   CONSTRAINT PK_LNKBOOKS PRIMARY KEY (CLIENT_ID_CARD_NO, TRIP_IDENTITY_NUMBER)
   );

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE LNKBOOKS
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_LNKBOOKS_TBLCLIENT
     ON LNKBOOKS (CLIENT_ID_CARD_NO)
    ;

CREATE  INDEX I_FK_LNKBOOKS_TBLTRIP
     ON LNKBOOKS (TRIP_IDENTITY_NUMBER)
    ;


-- -----------------------------------------------------------------------------
--       CREATION DES REFERENCES DE TABLE
-- -----------------------------------------------------------------------------


ALTER TABLE TBLTICKET ADD 
     CONSTRAINT FK_TBLTICKET_TBLCLIENT
          FOREIGN KEY (CLIENT_ID_CARD_NO)
               REFERENCES TBLCLIENT (CLIENT_ID_CARD_NO);

ALTER TABLE LNKINVOLVES ADD 
     CONSTRAINT FK_LNKINVOLVES_TBLTRIP
          FOREIGN KEY (TRIP_IDENTITY_NUMBER)
               REFERENCES TBLTRIP (TRIP_IDENTITY_NUMBER);

ALTER TABLE LNKINVOLVES ADD 
     CONSTRAINT FK_LNKINVOLVES_TBLVEHICLE
          FOREIGN KEY (VEHICLE_NUMBER)
               REFERENCES TBLVEHICLE (VEHICLE_NUMBER);

ALTER TABLE LNKBOOKS ADD 
     CONSTRAINT FK_LNKBOOKS_TBLCLIENT
          FOREIGN KEY (CLIENT_ID_CARD_NO)
               REFERENCES TBLCLIENT (CLIENT_ID_CARD_NO);

ALTER TABLE LNKBOOKS ADD 
     CONSTRAINT FK_LNKBOOKS_TBLTRIP
          FOREIGN KEY (TRIP_IDENTITY_NUMBER)
               REFERENCES TBLTRIP (TRIP_IDENTITY_NUMBER);


-- -----------------------------------------------------------------------------
--                FIN DE GENERATION
-- -----------------------------------------------------------------------------
-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 01, 2019 at 04:47 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ticket_booking_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `lnkbooks`
--

DROP TABLE IF EXISTS `lnkbooks`;
CREATE TABLE IF NOT EXISTS `lnkbooks` (
  `CLIENT_ID_CARD_NO` bigint(17) NOT NULL,
  `TRIP_IDENTITY_NUMBER` int(5) NOT NULL,
  `DATE_TRIP_BOOKED` datetime DEFAULT NULL,
  `TRIPDATE` datetime NOT NULL,
  PRIMARY KEY (`CLIENT_ID_CARD_NO`,`TRIP_IDENTITY_NUMBER`),
  KEY `I_FK_LNKBOOKS_TBLCLIENT` (`CLIENT_ID_CARD_NO`),
  KEY `I_FK_LNKBOOKS_TBLTRIP` (`TRIP_IDENTITY_NUMBER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='books link( link between client and trip)';

--
-- Dumping data for table `lnkbooks`
--

INSERT INTO `lnkbooks` (`CLIENT_ID_CARD_NO`, `TRIP_IDENTITY_NUMBER`, `DATE_TRIP_BOOKED`, `TRIPDATE`) VALUES
(978645312, 16, '2019-05-22 00:00:00', '2019-05-25 00:00:00'),
(55648, 17, '2019-05-22 00:00:00', '2019-05-29 00:00:00'),
(132426789, 22, '2019-06-01 00:00:00', '2019-06-01 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `lnkdeparts_at`
--

DROP TABLE IF EXISTS `lnkdeparts_at`;
CREATE TABLE IF NOT EXISTS `lnkdeparts_at` (
  `TRIP_IDENTITY_NUMBER` int(5) NOT NULL,
  `DEPARTURETIME` time NOT NULL,
  PRIMARY KEY (`TRIP_IDENTITY_NUMBER`,`DEPARTURETIME`),
  KEY `I_FK_LNKDEPARTS_AT_TBLTRIP` (`TRIP_IDENTITY_NUMBER`),
  KEY `I_FK_LNKDEPARTS_AT_TBLTIME` (`DEPARTURETIME`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='DEPATURE LINK';

--
-- Dumping data for table `lnkdeparts_at`
--

INSERT INTO `lnkdeparts_at` (`TRIP_IDENTITY_NUMBER`, `DEPARTURETIME`) VALUES
(10, '23:00:00'),
(14, '06:00:00'),
(15, '07:00:00'),
(16, '14:00:00'),
(17, '09:00:00'),
(18, '06:00:00'),
(19, '07:00:00'),
(20, '00:07:00'),
(21, '07:00:00'),
(22, '06:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `lnkinvolves`
--

DROP TABLE IF EXISTS `lnkinvolves`;
CREATE TABLE IF NOT EXISTS `lnkinvolves` (
  `TRIP_IDENTITY_NUMBER` int(5) NOT NULL,
  `VEHICLE_NUMBER` char(7) NOT NULL,
  PRIMARY KEY (`TRIP_IDENTITY_NUMBER`,`VEHICLE_NUMBER`),
  KEY `I_FK_LNKINVOLVES_TBLTRIP` (`TRIP_IDENTITY_NUMBER`),
  KEY `I_FK_LNKINVOLVES_TBLVEHICLE` (`VEHICLE_NUMBER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='link between vehicle and trip tables';

--
-- Dumping data for table `lnkinvolves`
--

INSERT INTO `lnkinvolves` (`TRIP_IDENTITY_NUMBER`, `VEHICLE_NUMBER`) VALUES
(1, 'SW564AC'),
(16, 'AD123BC'),
(17, 'LT568SE'),
(18, 'SW564AC'),
(19, 'SW564AC'),
(20, 'SW564AC'),
(21, 'AD123BC'),
(22, 'QE123BD');

-- --------------------------------------------------------

--
-- Table structure for table `tblclient`
--

DROP TABLE IF EXISTS `tblclient`;
CREATE TABLE IF NOT EXISTS `tblclient` (
  `CLIENT_ID_CARD_NO` bigint(17) NOT NULL,
  `CLIENT_FIRST_NAME` varchar(20) NOT NULL,
  `CLIENT_LAST_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CLIENT_ID_CARD_NO`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='client table';

--
-- Dumping data for table `tblclient`
--

INSERT INTO `tblclient` (`CLIENT_ID_CARD_NO`, `CLIENT_FIRST_NAME`, `CLIENT_LAST_NAME`) VALUES
(978645312, 'Jack', 'Jacky'),
(55648, 'Nelson', 'Ntomb'),
(132426789, 'New', 'Another Test');

-- --------------------------------------------------------

--
-- Table structure for table `tblticket`
--

DROP TABLE IF EXISTS `tblticket`;
CREATE TABLE IF NOT EXISTS `tblticket` (
  `TICKET_ID` int(5) NOT NULL,
  `CLIENT_ID_CARD_NO` int(9) NOT NULL,
  `TICKET_STATUS` tinyint(1) NOT NULL,
  `TICKET_EXPIRY_DATE` date NOT NULL,
  PRIMARY KEY (`TICKET_ID`),
  KEY `I_FK_TBLTICKET_TBLCLIENT` (`CLIENT_ID_CARD_NO`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='ticket table';

-- --------------------------------------------------------

--
-- Table structure for table `tbltime`
--

DROP TABLE IF EXISTS `tbltime`;
CREATE TABLE IF NOT EXISTS `tbltime` (
  `DEPARTURETIME` time NOT NULL,
  PRIMARY KEY (`DEPARTURETIME`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='TIMETABLE';

--
-- Dumping data for table `tbltime`
--

INSERT INTO `tbltime` (`DEPARTURETIME`) VALUES
('06:00:00'),
('07:00:00'),
('09:00:00'),
('10:00:00'),
('14:00:00'),
('22:00:00'),
('23:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `tbltrip`
--

DROP TABLE IF EXISTS `tbltrip`;
CREATE TABLE IF NOT EXISTS `tbltrip` (
  `TRIP_IDENTITY_NUMBER` int(5) NOT NULL AUTO_INCREMENT,
  `TRIP_ORIGIN` char(32) NOT NULL,
  `TRIP_DESTINATION` char(32) NOT NULL,
  `TRIP_FARE` int(5) NOT NULL,
  PRIMARY KEY (`TRIP_IDENTITY_NUMBER`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1 COMMENT='trip table';

--
-- Dumping data for table `tbltrip`
--

INSERT INTO `tbltrip` (`TRIP_IDENTITY_NUMBER`, `TRIP_ORIGIN`, `TRIP_DESTINATION`, `TRIP_FARE`) VALUES
(1, 'Buea', 'Douala', 99000),
(2, 'Limbe', 'south west', 6000),
(3, 'Buea', 'Bertoua', 12000),
(4, 'Yaounde', 'Buea', 34000),
(5, 'Buea', 'Douala', 12000),
(6, 'Buea', 'Douala', 12000),
(8, 'Garoua', 'Tiko', 2000),
(9, 'doo', 'joe', 12000),
(10, 'Ghana', 'Zimbabwe', 1000),
(11, 'Yaounde', 'Buea', 12000),
(12, 'Yaounde', 'Buea', 12000),
(13, 'Yaounde', 'Buea', 12000),
(14, 'Buea', 'Douala', 12),
(15, 'Bamenda', 'Bambili', 500),
(16, 'Bamenda', 'Dschang', 3500),
(17, 'Limbe', 'Buea', 800),
(18, 'Buea', 'Bertoua', 6000),
(19, 'Limbe', 'Garoua', 800),
(20, 'Limbe', 'Buea', 800),
(21, 'nothing', 'jigj', 700),
(22, 'Yaounde', 'Ngaoundere', 12000);

-- --------------------------------------------------------

--
-- Table structure for table `tblvehicle`
--

DROP TABLE IF EXISTS `tblvehicle`;
CREATE TABLE IF NOT EXISTS `tblvehicle` (
  `VEHICLE_NUMBER` char(7) NOT NULL,
  `NUMBER_OF_SEATS` int(2) NOT NULL,
  `AVAILABLE_SEATS` int(2) NOT NULL,
  PRIMARY KEY (`VEHICLE_NUMBER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='vehicle table';

--
-- Dumping data for table `tblvehicle`
--

INSERT INTO `tblvehicle` (`VEHICLE_NUMBER`, `NUMBER_OF_SEATS`, `AVAILABLE_SEATS`) VALUES
('AD123BC', 35, 35),
('SW564AC', 50, 50),
('LT568SE', 70, 70),
('QE123BD', 1, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
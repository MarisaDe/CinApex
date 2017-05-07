-- MySQL dump 10.13  Distrib 5.7.17, for osx10.12 (x86_64)
--
-- Host: localhost    Database: CineApex
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Cost`
--

DROP TABLE IF EXISTS `Cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cost` (
  `AcctType` char(50) NOT NULL,
  `MonthlyFee` int(11) DEFAULT NULL,
  PRIMARY KEY (`AcctType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cost`
--

LOCK TABLES `Cost` WRITE;
/*!40000 ALTER TABLE `Cost` DISABLE KEYS */;
INSERT INTO `Cost` VALUES ('Limited',10),('Unlimited-1',15),('Unlimited-2',20),('Unlimited-3',25);
/*!40000 ALTER TABLE `Cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Manager`
--

DROP TABLE IF EXISTS `Manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Manager` (
  `ManagerId` char(15) NOT NULL,
  PRIMARY KEY (`ManagerId`),
  CONSTRAINT `manager_ibfk_1` FOREIGN KEY (`ManagerId`) REFERENCES `Employee` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Manager`
--

LOCK TABLES `Manager` WRITE;
/*!40000 ALTER TABLE `Manager` DISABLE KEYS */;
INSERT INTO `Manager` VALUES ('0');
/*!40000 ALTER TABLE `Manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `Id` char(15) NOT NULL,
  `DateOpened` date DEFAULT NULL,
  `AccType` char(30) DEFAULT NULL,
  `CustomerId` char(15) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `CustomerId` (`CustomerId`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('1','2006-10-01','Unlimited-2','444-44-4444'),('2','2006-10-15','limited','222-22-2222');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `Id` int(11) NOT NULL,
  `Name` char(20) NOT NULL,
  `Age` int(11) NOT NULL,
  `Sex` char(1) NOT NULL,
  `Rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Al Pacino',63,'M',5),(2,'Tim Robbins',53,'M',2);
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appearedin`
--

DROP TABLE IF EXISTS `appearedin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appearedin` (
  `ActorId` int(11) NOT NULL,
  `MovieId` int(11) NOT NULL,
  PRIMARY KEY (`ActorId`,`MovieId`),
  KEY `MovieId` (`MovieId`),
  CONSTRAINT `appearedin_ibfk_1` FOREIGN KEY (`ActorId`) REFERENCES `actor` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `appearedin_ibfk_2` FOREIGN KEY (`MovieId`) REFERENCES `movie` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appearedin`
--

LOCK TABLES `appearedin` WRITE;
/*!40000 ALTER TABLE `appearedin` DISABLE KEYS */;
INSERT INTO `appearedin` VALUES (1,1),(1,2),(2,2);
/*!40000 ALTER TABLE `appearedin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `countorders`
--

DROP TABLE IF EXISTS `countorders`;
/*!50001 DROP VIEW IF EXISTS `countorders`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `countorders` AS SELECT 
 1 AS `CustId`,
 1 AS `NumOrders`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `Id` char(15) NOT NULL,
  `Email` char(32) DEFAULT NULL,
  `Rating` int(11) DEFAULT NULL,
  `CreditCardNumber` char(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('222-22-2222','vicdu@cs.sunysb.edu',1,'5678-1234-5678-1234'),('333-33-3333','jsmith@ic.sunysb.edu',1,'2345-6789-2345-6789'),('444-44-4444','pml@cs.sunysb.edu',1,'6789-2345-6789-2345');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `Id` char(15) NOT NULL,
  `SSN` char(15) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `HourlyRate` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `SSN` (`SSN`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`SSN`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('0','121-21-2121','2006-02-02',150),('1','123-45-6789','2015-11-05',50),('12','123-45-3212','2011-03-05',23),('2','222-11-2222','2011-01-02',40),('23','213312-2','2012-03-01',21);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `ZipCode` int(11) NOT NULL,
  `City` char(20) NOT NULL,
  `State` char(20) NOT NULL,
  PRIMARY KEY (`ZipCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (0,'-','-'),(11009,'StonyBrook','NY'),(11023,'Glen Cove','NY'),(11790,'Stony Brook','NY'),(11794,'Stony Brook','NY'),(12032,'ColdStone','NY'),(23213,'32323','NY'),(93536,'Los Angeles','CA'),(112233,'StonyBrook','NY'),(123322,'City','Ny'),(223344,'Stony Brook','NY'),(223399,'City','Ny'),(321321,'dsadas','BY'),(987654,'City','Ny'),(2232100,'231','BU');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `monthlyreport`
--

DROP TABLE IF EXISTS `monthlyreport`;
/*!50001 DROP VIEW IF EXISTS `monthlyreport`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `monthlyreport` AS SELECT 
 1 AS `Id`,
 1 AS `DistrFee`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `Id` int(11) NOT NULL,
  `Name` char(20) NOT NULL,
  `Type` char(20) NOT NULL,
  `Rating` int(11) DEFAULT NULL,
  `DistrFee` int(11) DEFAULT NULL,
  `NumCopies` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'The GodFather','Drama',5,10000,2),(2,'Shawshank Redemption','Drama',NULL,1000,1),(3,'S','Horror',5,2,10),(5,'Departed','Drama',5,5000,2),(7,'New Name','Horror',2,2000,3);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movieorder`
--

DROP TABLE IF EXISTS `movieorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movieorder` (
  `Id` int(11) NOT NULL,
  `AccountId` char(15) DEFAULT NULL,
  `MovieId` int(11) DEFAULT NULL,
  `DateAndTime` datetime DEFAULT NULL,
  `ReturnDate` date DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `MovieId` (`MovieId`),
  KEY `AccountId` (`AccountId`),
  CONSTRAINT `movieorder_ibfk_1` FOREIGN KEY (`MovieId`) REFERENCES `movie` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `movieorder_ibfk_2` FOREIGN KEY (`AccountId`) REFERENCES `account` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movieorder`
--

LOCK TABLES `movieorder` WRITE;
/*!40000 ALTER TABLE `movieorder` DISABLE KEYS */;
INSERT INTO `movieorder` VALUES (1,'1',1,'2009-11-09 10:00:00','2009-11-14'),(2,'2',5,'2017-05-03 06:12:04','2017-11-01'),(4,'2',2,'2009-11-21 22:22:00',NULL),(11,'2',5,'2017-05-03 06:27:14','1111-12-12'),(12,'1',2,'2017-05-03 02:15:56','2017-12-12'),(24,'1',2,'2017-05-04 02:17:03','2011-11-01');
/*!40000 ALTER TABLE `movieorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movieq`
--

DROP TABLE IF EXISTS `movieq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movieq` (
  `AccountId` char(15) NOT NULL,
  `MovieId` int(11) NOT NULL,
  PRIMARY KEY (`AccountId`,`MovieId`),
  KEY `MovieId` (`MovieId`),
  CONSTRAINT `movieq_ibfk_1` FOREIGN KEY (`AccountId`) REFERENCES `account` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `movieq_ibfk_2` FOREIGN KEY (`MovieId`) REFERENCES `movie` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movieq`
--

LOCK TABLES `movieq` WRITE;
/*!40000 ALTER TABLE `movieq` DISABLE KEYS */;
INSERT INTO `movieq` VALUES ('1',1),('2',2);
/*!40000 ALTER TABLE `movieq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `name`
--

DROP TABLE IF EXISTS `name`;
/*!50001 DROP VIEW IF EXISTS `name`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `name` AS SELECT 
 1 AS `CustId`,
 1 AS `AcctId`,
 1 AS `FirstName`,
 1 AS `LastName`,
 1 AS `Rating`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `orderlist`
--

DROP TABLE IF EXISTS `orderlist`;
/*!50001 DROP VIEW IF EXISTS `orderlist`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `orderlist` AS SELECT 
 1 AS `MovieId`,
 1 AS `NumOrders`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `SSN` char(15) NOT NULL,
  `LastName` char(20) NOT NULL,
  `FirstName` char(20) NOT NULL,
  `Address` char(20) DEFAULT NULL,
  `ZipCode` int(11) DEFAULT NULL,
  `Telephone` char(20) DEFAULT NULL,
  PRIMARY KEY (`SSN`),
  KEY `ZipCode` (`ZipCode`),
  CONSTRAINT `person_ibfk_1` FOREIGN KEY (`ZipCode`) REFERENCES `location` (`ZipCode`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES ('-','-','-','-',0,'-'),('111-11-1111','Yang','Shang','123 Success Street',11790,'516-632-8959'),('111-22-2323','Test','Tested','123 Test Street',0,'222-233-2222'),('121-21-2121','Scott','Smolka','213 Data Base',11794,'321-123-3213'),('123-45-3212','John','Doe','123 Tested',0,'555-333-2222'),('123-45-6789','Smith','David','123 College road',11790,'516-2152-345'),('213312-2','Jacob','Lemon','213123asa',2232100,'31313414'),('222-11-2222','Kevin','Smith','123 Little Street',11023,'222-112-3345'),('222-11-5555','John','Doe','123 Tested',0,'555-333-2222'),('222-22-2221','Kang','Sangwook','700 Health Science',11790,'631-413-5555'),('222-22-2222','Dune','Victor','456 Fortune Road',11790,'516-632-4360'),('333-33-3333','Smith','John','789 Peace Blvd.',93536,'315-443-4321'),('444-44-4444','Philip','Lewis','135 Knowledge Lane',11794,'516-666-8888');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rental` (
  `AccountId` char(15) NOT NULL,
  `CustRepId` char(15) NOT NULL,
  `OrderId` int(11) NOT NULL,
  `MovieId` int(11) NOT NULL,
  PRIMARY KEY (`AccountId`,`CustRepId`,`OrderId`,`MovieId`),
  KEY `CustRepId` (`CustRepId`),
  KEY `OrderId` (`OrderId`),
  KEY `MovieId` (`MovieId`),
  CONSTRAINT `rental_ibfk_1` FOREIGN KEY (`AccountId`) REFERENCES `account` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `rental_ibfk_2` FOREIGN KEY (`CustRepId`) REFERENCES `employee` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `rental_ibfk_3` FOREIGN KEY (`OrderId`) REFERENCES `movieorder` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `rental_ibfk_4` FOREIGN KEY (`MovieId`) REFERENCES `movie` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` VALUES ('1','1',12,2),('1','1',24,2);
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userratings`
--

DROP TABLE IF EXISTS `userratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userratings` (
  `CustomerId` char(15) NOT NULL,
  `MovieId` int(11) NOT NULL,
  `Rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`CustomerId`,`MovieId`),
  KEY `MovieId` (`MovieId`),
  CONSTRAINT `userratings_ibfk_1` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `userratings_ibfk_2` FOREIGN KEY (`MovieId`) REFERENCES `movie` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userratings`
--

LOCK TABLES `userratings` WRITE;
/*!40000 ALTER TABLE `userratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `userratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `countorders`
--

/*!50001 DROP VIEW IF EXISTS `countorders`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`manager`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `countorders` AS select `n`.`CustId` AS `CustId`,count(0) AS `NumOrders` from (`rental` `R` join `name` `N`) where (`n`.`AcctId` = `R`.`AccountId`) group by `n`.`CustId` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `monthlyreport`
--

/*!50001 DROP VIEW IF EXISTS `monthlyreport`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`manager`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `monthlyreport` AS select `M`.`Id` AS `Id`,`M`.`DistrFee` AS `DistrFee` from (`movie` `M` join `movieorder` `MO`) where ((`M`.`Id` = `MO`.`Id`) and (month(`MO`.`DateAndTime`) = 11)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `name`
--

/*!50001 DROP VIEW IF EXISTS `name`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`manager`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `name` AS select `C`.`Id` AS `CustId`,`A`.`Id` AS `AcctId`,`P`.`FirstName` AS `FirstName`,`P`.`LastName` AS `LastName`,`C`.`Rating` AS `Rating` from ((`customer` `C` join `account` `A`) join `person` `P`) where ((`C`.`Id` = `A`.`Id`) and (`C`.`Id` = `P`.`SSN`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `orderlist`
--

/*!50001 DROP VIEW IF EXISTS `orderlist`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`manager`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `orderlist` AS select `R`.`MovieId` AS `MovieId`,count(`R`.`MovieId`) AS `NumOrders` from `rental` `R` group by `R`.`MovieId` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-04  3:05:57

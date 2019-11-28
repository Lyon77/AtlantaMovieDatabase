CREATE DATABASE  IF NOT EXISTS `team59` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `team59`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: team59
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `fk3` FOREIGN KEY (`username`) REFERENCES `employee` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('cool_class4400');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `comName` varchar(50) NOT NULL,
  PRIMARY KEY (`comName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('4400 Theater Company'),('AI Theater Company'),('Awesome Theater Company'),('EZ Theater Company');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `fk2` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('calcultron'),('calcultron2'),('calcwizard'),('clarinetbeast'),('cool_class4400'),('DNAhelix'),('does2Much'),('eeqmcsquare'),('entropyRox'),('fullMetal'),('georgep'),('ilikemoney$$'),('imready'),('isthisthekrustykrab'),('notFullMetal'),('programerAAL'),('RitzLover28'),('thePiGuy3.14'),('theScienceGuy');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customercreditcard`
--

DROP TABLE IF EXISTS `customercreditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customercreditcard` (
  `creditCardNum` char(16) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`creditCardNum`),
  KEY `fk6_idx` (`username`),
  CONSTRAINT `fk6` FOREIGN KEY (`username`) REFERENCES `customer` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customercreditcard`
--

LOCK TABLES `customercreditcard` WRITE;
/*!40000 ALTER TABLE `customercreditcard` DISABLE KEYS */;
INSERT INTO `customercreditcard` VALUES ('1111111111000000','calcultron'),('1111111100000000','calcultron2'),('1111111110000000','calcultron2'),('1111111111100000','calcwizard'),('2222222222000000','cool_class4400'),('2220000000000000','DNAhelix'),('2222222200000000','does2Much'),('2222222222222200','eeqmcsquare'),('2222222222200000','entropyRox'),('2222222222220000','entropyRox'),('1100000000000000','fullMetal'),('1111111111110000','georgep'),('1111111111111000','georgep'),('1111111111111100','georgep'),('1111111111111110','georgep'),('1111111111111111','georgep'),('2222222222222220','ilikemoney$$'),('2222222222222222','ilikemoney$$'),('9000000000000000','ilikemoney$$'),('1111110000000000','imready'),('1110000000000000','isthisthekrustykrab'),('1111000000000000','isthisthekrustykrab'),('1111100000000000','isthisthekrustykrab'),('1000000000000000','notFullMetal'),('2222222000000000','programerAAL'),('3333333333333300','RitzLover28'),('2222222220000000','thePiGuy3.14'),('2222222222222000','theScienceGuy');
/*!40000 ALTER TABLE `customercreditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerviewmovie`
--

DROP TABLE IF EXISTS `customerviewmovie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerviewmovie` (
  `creditCardNum` char(16) NOT NULL,
  `thName` varchar(25) NOT NULL,
  `comName` varchar(50) NOT NULL,
  `movName` varchar(50) NOT NULL,
  `movReleaseDate` date NOT NULL,
  `movPlayDate` date NOT NULL,
  PRIMARY KEY (`creditCardNum`,`thName`,`comName`,`movName`,`movReleaseDate`,`movPlayDate`),
  KEY `fk14_idx` (`thName`,`comName`,`movName`,`movReleaseDate`,`movPlayDate`),
  CONSTRAINT `fk13` FOREIGN KEY (`creditCardNum`) REFERENCES `customercreditcard` (`creditCardNum`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk14` FOREIGN KEY (`thName`, `comName`, `movName`, `movReleaseDate`, `movPlayDate`) REFERENCES `movieplay` (`thName`, `comName`, `movName`, `movReleaseDate`, `movPlayDate`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerviewmovie`
--

LOCK TABLES `customerviewmovie` WRITE;
/*!40000 ALTER TABLE `customerviewmovie` DISABLE KEYS */;
INSERT INTO `customerviewmovie` VALUES ('1111111111111111','Cinema Star','4400 Theater Company','How to Train Your Dragon','2010-03-21','2010-04-02'),('1111111111111111','Main Movies','EZ Theater Company','How to Train Your Dragon','2010-03-21','2010-03-22'),('1111111111111111','Main Movies','EZ Theater Company','How to Train Your Dragon','2010-03-21','2010-03-23'),('1111111111111100','Star Movies','EZ Theater Company','How to Train Your Dragon','2010-03-21','2010-03-25');
/*!40000 ALTER TABLE `customerviewmovie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `fk1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('calcultron'),('cool_class4400'),('entropyRox'),('fatherAI'),('georgep'),('ghcghc'),('imbatman'),('manager1'),('manager2'),('manager3'),('manager4'),('radioactivePoRa');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `username` varchar(50) NOT NULL,
  `comName` varchar(50) NOT NULL,
  `manStreet` varchar(25) NOT NULL,
  `manCity` varchar(25) NOT NULL,
  `manState` char(2) NOT NULL,
  `manZipcode` char(5) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `Address` (`manStreet`,`manCity`,`manState`,`manZipcode`),
  KEY `fk5_idx` (`comName`),
  CONSTRAINT `fk4` FOREIGN KEY (`username`) REFERENCES `employee` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk5` FOREIGN KEY (`comName`) REFERENCES `company` (`comName`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES ('calcultron','EZ Theater Company','123 Peachtree St','Atlanta','GA','30308'),('entropyRox','4400 Theater Company','200 Cool Place','San Francisco','CA','94016'),('fatherAI','EZ Theater Company','456 Main St','New York','NY','10001'),('georgep','4400 Theater Company','10 Pearl Dr','Seattle','WA','98105'),('ghcghc','AI Theater Company','100 Pi St','Pallet Town','KS','31415'),('imbatman','Awesome Theater Company','800 Color Dr','Austin','TX','78653'),('manager1','4400 Theater Company','123 Ferst Drive','Atlanta','GA','30332'),('manager2','AI Theater Company','456 Ferst Drive','Atlanta','GA','30332'),('manager3','4400 Theater Company','789 Ferst Drive','Atlanta','GA','30332'),('manager4','4400 Theater Company','000 Ferst Drive','Atlanta','GA','30332'),('radioactivePoRa','4400 Theater Company','100 Blu St','Sunnyvale','CA','94088');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movName` varchar(50) NOT NULL,
  `movReleaseDate` date NOT NULL,
  `duration` int(11) NOT NULL,
  PRIMARY KEY (`movName`,`movReleaseDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES ('4400 The Movie','2019-08-12',130),('Avengers: Endgame','2019-04-26',181),('Calculus Returns: A ML Story','2019-09-19',314),('George P Burdell\'s Life Story','1927-08-12',100),('Georgia Tech The Movie','1985-08-13',100),('How to Train Your Dragon','2010-03-21',98),('Spaceballs','1987-06-24',96),('Spider-Man: Into the Spider-Verse','2018-12-01',117),('The First Pokemon Movie','1998-07-19',75),('The King\'s Speech','2010-11-26',119);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movieplay`
--

DROP TABLE IF EXISTS `movieplay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movieplay` (
  `thName` varchar(25) NOT NULL,
  `comName` varchar(50) NOT NULL,
  `movName` varchar(50) NOT NULL,
  `movReleaseDate` date NOT NULL,
  `movPlayDate` date NOT NULL,
  PRIMARY KEY (`thName`,`comName`,`movName`,`movReleaseDate`,`movPlayDate`),
  KEY `fk10_idx` (`movName`,`movReleaseDate`),
  CONSTRAINT `fk10` FOREIGN KEY (`movName`, `movReleaseDate`) REFERENCES `movie` (`movName`, `movReleaseDate`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk9` FOREIGN KEY (`thName`, `comName`) REFERENCES `theater` (`thName`, `comName`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movieplay`
--

LOCK TABLES `movieplay` WRITE;
/*!40000 ALTER TABLE `movieplay` DISABLE KEYS */;
INSERT INTO `movieplay` VALUES ('ABC Theater','Awesome Theater Company','4400 The Movie','2019-08-12','2019-10-12'),('Cinema Star','4400 Theater Company','4400 The Movie','2019-08-12','2019-09-12'),('Star Movies','EZ Theater Company','4400 The Movie','2019-08-12','2019-08-12'),('ML Movies','AI Theater Company','Calculus Returns: A ML Story','2019-09-19','2019-10-10'),('ML Movies','AI Theater Company','Calculus Returns: A ML Story','2019-09-19','2019-12-30'),('Cinema Star','4400 Theater Company','George P Burdell\'s Life Story','1927-08-12','2010-05-20'),('Main Movies','EZ Theater Company','George P Burdell\'s Life Story','1927-08-12','2019-07-14'),('Main Movies','EZ Theater Company','George P Burdell\'s Life Story','1927-08-12','2019-10-22'),('ABC Theater','Awesome Theater Company','Georgia Tech The Movie','1985-08-13','1985-08-13'),('Cinema Star','4400 Theater Company','Georgia Tech The Movie','1985-08-13','2019-09-30'),('Cinema Star','4400 Theater Company','How to Train Your Dragon','2010-03-21','2010-04-02'),('Main Movies','EZ Theater Company','How to Train Your Dragon','2010-03-21','2010-03-22'),('Main Movies','EZ Theater Company','How to Train Your Dragon','2010-03-21','2010-03-23'),('Star Movies','EZ Theater Company','How to Train Your Dragon','2010-03-21','2010-03-25'),('Cinema Star','4400 Theater Company','Spaceballs','1987-06-24','2000-02-02'),('Main Movies','EZ Theater Company','Spaceballs','1987-06-24','1999-06-24'),('ML Movies','AI Theater Company','Spaceballs','1987-06-24','2010-04-02'),('ML Movies','AI Theater Company','Spaceballs','1987-06-24','2023-01-23'),('ML Movies','AI Theater Company','Spider-Man: Into the Spider-Verse','2018-12-01','2019-09-30'),('ABC Theater','Awesome Theater Company','The First Pokemon Movie','1998-07-19','2018-07-19'),('Cinema Star','4400 Theater Company','The King\'s Speech','2010-11-26','2019-12-20'),('Main Movies','EZ Theater Company','The King\'s Speech','2010-11-26','2019-12-20');
/*!40000 ALTER TABLE `movieplay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theater`
--

DROP TABLE IF EXISTS `theater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theater` (
  `thName` varchar(25) NOT NULL,
  `comName` varchar(50) NOT NULL,
  `capacity` int(11) NOT NULL,
  `thStreet` varchar(25) NOT NULL,
  `thCity` varchar(25) NOT NULL,
  `thState` char(2) NOT NULL,
  `thZipcode` char(5) NOT NULL,
  `manUsername` varchar(50) NOT NULL,
  PRIMARY KEY (`thName`,`comName`),
  KEY `fk7_idx` (`comName`),
  KEY `fk8_idx` (`manUsername`),
  CONSTRAINT `fk7` FOREIGN KEY (`comName`) REFERENCES `company` (`comName`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk8` FOREIGN KEY (`manUsername`) REFERENCES `manager` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theater`
--

LOCK TABLES `theater` WRITE;
/*!40000 ALTER TABLE `theater` DISABLE KEYS */;
INSERT INTO `theater` VALUES ('ABC Theater','Awesome Theater Company',5,'880 Color Dr','Austin','TX','73301','imbatman'),('Cinema Star','4400 Theater Company',4,'100 Cool Place','San Francisco','CA','94016','entropyRox'),('Jonathan\'s Movies','4400 Theater Company',2,'67 Pearl Dr','Seattle','WA','98101','georgep'),('Main Movies','EZ Theater Company',3,'123 Main St','New York','NY','10001','fatherAI'),('ML Movies','AI Theater Company',3,'314 Pi St','Pallet Town','KS','31415','ghcghc'),('Star Movies','4400 Theater Company',5,'4400 Rocks Ave','Boulder','CA','80301','radioactivePoRa'),('Star Movies','EZ Theater Company',2,'745 GT St','Atlanta','GA','30332','calcultron');
/*!40000 ALTER TABLE `theater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `status` enum('Approved','Pending','Declined') NOT NULL,
  `password` varchar(32) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('calcultron','Approved','333333333','Dwight','Schrute'),('calcultron2','Approved','444444444','Jim','Halpert'),('calcwizard','Approved','222222222','Issac','Newton'),('clarinetbeast','Declined','999999999','Squidward','Tentacles'),('cool_class4400','Approved','333333333','A. TA','Washere'),('DNAhelix','Approved','777777777','Rosalind','Franklin'),('does2Much','Approved','1212121212','Carl','Gauss'),('eeqmcsquare','Approved','111111110','Albert','Einstein'),('entropyRox','Approved','999999999','Claude','Shannon'),('fatherAI','Approved','222222222','Alan','Turing'),('fullMetal','Approved','111111100','Edward','Elric'),('gdanger','Declined','555555555','Gary','Danger'),('georgep','Approved','111111111','George P.','Burdell'),('ghcghc','Approved','666666666','Grace','Hopper'),('ilikemoney$$','Approved','111111110','Eugene','Krabs'),('imbatman','Approved','666666666','Bruce','Wayne'),('imready','Approved','777777777','Spongebob','Squarepants'),('isthisthekrustykrab','Approved','888888888','Patrick','Star'),('manager1','Approved','1122112211','Manager','One'),('manager2','Approved','3131313131','Manager','Two'),('manager3','Approved','8787878787','Three','Three'),('manager4','Approved','5755555555','Four','Four'),('notFullMetal','Approved','111111100','Alphonse','Elric'),('programerAAL','Approved','3131313131','Ada','Lovelace'),('radioactivePoRa','Approved','1313131313','Marie','Curie'),('RitzLover28','Approved','444444444','Abby','Normal'),('smith_j','Pending','333333333','John','Smith'),('texasStarKarate','Declined','111111110','Sandy','Cheeks'),('thePiGuy3.14','Approved','1111111111','Archimedes','Syracuse'),('theScienceGuy','Approved','999999999','Bill','Nye');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uservisittheater`
--

DROP TABLE IF EXISTS `uservisittheater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uservisittheater` (
  `visitID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `thName` varchar(25) NOT NULL,
  `comName` varchar(50) NOT NULL,
  `visitDate` date NOT NULL,
  PRIMARY KEY (`visitID`),
  KEY `fk11_idx` (`username`),
  KEY `fk12_idx` (`thName`,`comName`),
  CONSTRAINT `fk11` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk12` FOREIGN KEY (`thName`, `comName`) REFERENCES `theater` (`thName`, `comName`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uservisittheater`
--

LOCK TABLES `uservisittheater` WRITE;
/*!40000 ALTER TABLE `uservisittheater` DISABLE KEYS */;
INSERT INTO `uservisittheater` VALUES (1,'georgep','Main Movies','EZ Theater Company','2010-03-22'),(2,'calcwizard','Main Movies','EZ Theater Company','2010-03-22'),(3,'calcwizard','Star Movies','EZ Theater Company','2010-03-25'),(4,'imready','Star Movies','EZ Theater Company','2010-03-25'),(5,'calcwizard','ML Movies','AI Theater Company','2010-03-20');
/*!40000 ALTER TABLE `uservisittheater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'team59'
--

--
-- Dumping routines for database 'team59'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-20 23:51:50

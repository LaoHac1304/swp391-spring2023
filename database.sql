CREATE DATABASE  IF NOT EXISTS `etransporationdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `etransporationdb`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: etransporationdb
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `BookingID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int DEFAULT NULL,
  `TripID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Total` decimal(10,0) DEFAULT NULL,
  `Arrival` varchar(255) DEFAULT NULL,
  `Departure` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `FullName` varchar(255) DEFAULT NULL,
  `List_Seats_Id` varchar(255) DEFAULT NULL,
  `PhoneNumber` varchar(255) DEFAULT NULL,
  `CarID` int DEFAULT NULL,
  `List_Seats_Number` varchar(255) DEFAULT NULL,
  `Payment_Status` varchar(255) DEFAULT NULL,
  `Payment_Type` varchar(255) DEFAULT NULL,
  `CreatedAt` datetime DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `UpdatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`BookingID`),
  KEY `FKb4bssb6qp8653riiryp6u48cf` (`CarID`),
  KEY `FK1oj5awknr00ojhcjjb3ts4ca2` (`CustomerID`),
  KEY `FKca5tgypoho2178jgs4cqui87` (`TripID`),
  CONSTRAINT `FK1oj5awknr00ojhcjjb3ts4ca2` FOREIGN KEY (`CustomerID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `FKb4bssb6qp8653riiryp6u48cf` FOREIGN KEY (`CarID`) REFERENCES `car` (`CarID`),
  CONSTRAINT `FKca5tgypoho2178jgs4cqui87` FOREIGN KEY (`TripID`) REFERENCES `tripinfor` (`TripID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (33,22,7,3,720000,'BX. DA LAT','BX. DA LAT','simnhankid13042002@gmail.com','nhân võ','[162, 166, 170]',NULL,5,'16,20,24','PAID','PAYPAL',NULL,'CONFIRMED',NULL),(34,22,7,3,720000,'BX. DA LAT','BX. DA LAT','simnhankid13042002@gmail.com','nhân võ','[149, 153, 154]',NULL,5,'3,7,8','PENDING','CASH',NULL,'CONFIRMED',NULL),(35,22,7,3,720000,'BX. DA LAT','BX. DA LAT','simnhankid13042002@gmail.com','nhân võ','[166, 169, 170]',NULL,5,'20,23,24','PENDING','CASH',NULL,'CONFIRMED',NULL),(36,22,4,3,840000,'BX. DA LAT','BX. DA LAT','simnhankid13042002@gmail.com','nhân võ','[79, 80, 90]',NULL,6,'3,4,14','PENDING','CASH',NULL,'CONFIRMED',NULL),(37,22,5,2,600000,'BX. DA LAT','BX. DA LAT','simnhankid13042002@gmail.com','nhân võ','[101, 109]',NULL,7,'10,18','PAID','PAYPAL',NULL,'CANCELLED',NULL),(38,22,5,2,600000,'BX. DA LAT','BX. DA LAT','simnhankid13042002@gmail.com','nhân võ','[101, 103]',NULL,7,'10,12','PAID','PAYPAL','2023-03-18 09:06:42','PENDING','2023-03-18 09:06:42');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `CarID` int NOT NULL AUTO_INCREMENT,
  `Capacity` int DEFAULT NULL,
  `CompanyID` int DEFAULT NULL,
  `PlateNumber` varchar(20) NOT NULL,
  PRIMARY KEY (`CarID`),
  KEY `FK_CompanyCar_idx` (`CompanyID`),
  CONSTRAINT `FK_CompanyCar` FOREIGN KEY (`CompanyID`) REFERENCES `carcompany` (`CompanyID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,25,1,'50H1-01234'),(2,20,2,'50H1-05235'),(3,30,3,'50H1-89088'),(4,35,4,'72C1-19284'),(5,25,5,'49B1-23785'),(6,15,6,'70B2-65871'),(7,20,7,'69A1-99999'),(8,35,8,'76C1-11223'),(9,36,9,'70A1-12345'),(10,37,10,'47C1-88888'),(11,22,11,'69B1-56789'),(12,34,12,'59A1-16871'),(13,22,13,'47C1-10011'),(14,32,14,'47B2-12344'),(15,32,15,'49C2-86819'),(16,32,16,'49C1-15689'),(17,32,1,'50C1-23865'),(18,22,17,'43A1-35681'),(19,34,18,'43A2-83176'),(20,22,11,'69B1-28373'),(21,22,13,'47C1-54938'),(22,32,14,'47A1-48971'),(23,32,1,'50H1-90101'),(25,32,1,'50H2-78215'),(26,32,1,'50G2-32174'),(27,32,1,'50C1-14785'),(28,32,1,'50A1-81256'),(29,32,1,'50C2-91275'),(30,32,1,'50B1-87325'),(31,32,1,'50N1-65581'),(32,32,1,'50F1-46189'),(33,32,1,'50N1-67812');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carcompany`
--

DROP TABLE IF EXISTS `carcompany`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carcompany` (
  `CompanyID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `PhoneNumber` varchar(20) NOT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `BusinessLicense` tinyint(1) DEFAULT NULL,
  `Province` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CompanyID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carcompany`
--

LOCK TABLES `carcompany` WRITE;
/*!40000 ALTER TABLE `carcompany` DISABLE KEYS */;
INSERT INTO `carcompany` VALUES (1,'Phuong Trang','0908887655','District 1',1,'Ho Chi Minh','https://futabus.vn/img/SearchConsole/futa16x9.jpg'),(2,'Phuoc Hung','19001234','District 5',1,'Ho Chi Minh','https://limody.vn/wp-content/uploads/2020/12/xe-phuoc-hung-3.jpg'),(3,'Thanh Buoi','19008989','District 1',1,'Ho Chi Minh','https://static.vexere.com/production/images/1656960376767.jpeg?w=250&h=250'),(4,'Dan Anh','19000001','District 7',1,'Vung Tau','https://amazingdalat.com/media/upload/images/thue-xe/Limousine%20Dan%20Anh%20Da%20Lat.jpg'),(5,'Quoc Bao','19008080','Long Thanh',1,'Dong Nai','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLejSt82P3W9eW0q9mueQrJIXNypOF2FcW5w&usqp=CAU'),(6,'Thanh An','19008080','Quan 1',1,'Ho Chi Minh','https://canthoplus.com/wp-content/uploads/2015/12/nha-xe-thanh-buoi-can-tho.jpg'),(7,'Thuan Tien','19000102','Ca Mau',1,'Ca Mau','https://limody.vn/wp-content/uploads/2020/09/xe-Thuan-Tien-1.jpg'),(8,'Bay Viet','18001234','Quang Ngai',1,'Quang Ngai','https://cdn.oto360.net/images/bus/canh_hoa.webp'),(9,'Minh Nghia','18008098','Tay Ninh',1,'Tay Ninh','https://storage.googleapis.com/blogvxr-uploads/2022/08/minh-nghia.jpg'),(10,'Tien Oanh','19001221','Buon Ma Thuot',1,'Dak Lak','https://kenhhomestay.com/wp-content/uploads/2019/06/nha-xe-tien-oanh.jpg'),(11,'Hao','18000112','Ca Mau',1,'Ca Mau','https://lh4.googleusercontent.com/i_YBqeBxMC5DhRgFZyPA3XJVdZljx0N6LdDlWLf-fOqU681IMxbYYaB6f1olsvj8COjcnCwz2Z0JYaUOjyLBCyhOoEC1cmpFygJnhuH5mwgLdHkNUF1crFgrPhxTGZw3oYsqUVuChuTf4p0pjxhsMgk'),(12,'Ngoc Anh','0907123321','Quan 4',1,'TP HCM','https://canthoplus.com/wp-content/uploads/2021/05/2-tong-dai-nha-xe-ngoc-anh-ca-mau.jpg'),(13,'Tuan Trung','0908010020','Buon Ma Thuot',1,'DAK LAK','https://xevati.com/wp-content/uploads/2021/11/Cac-dich-vu-tien-ich-tren-xe-Minh-Map.jpg'),(14,'Nguyen Diu','0902233433','Buon Ma Thuot',1,'DAK LAK','https://lh5.googleusercontent.com/BoC1r1dRzTNrVode5PjyPZINW_QXTL8D1tx-k-GiA2HWFQ9vPDOcVANr9LD25EHhLCz31ywyaQuX6uD0EpIWwyqJNNHG4Hcohn_SupIbVSRnXzZVjPXJMqBx1thtG4CBUcKCDvA-'),(15,'An Anh Limousine','0988132154','DA LAT',1,'LAM DONG','https://nhaxeananh.com/wp-content/uploads/2021/01/e0a7398df98308dd5192-1.jpg'),(16,'Trong Minh','0912345678','DA THIEN',1,'LAM DONG','https://megabus.vn/wp-content/uploads/2019/07/xe-tr%E1%BB%8Dng-minh-4.jpg'),(17,'Kim Chi','0976123123','Da Nang',1,'Da Nang','https://nhaxekimchi.com/wp-content/uploads/2022/07/3f6955aabe607d3e24713.jpg'),(18,'Duong Vu','19007878','BX. Trung Tam',1,'BX. Son Tay','https://saodieu.vn/media/transporter/116/1649156238_Xe-Duong-Vu-Da-Nang.jpg');
/*!40000 ALTER TABLE `carcompany` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `PaymentID` int NOT NULL,
  `BDetailID` int NOT NULL,
  `Status` tinyint(1) DEFAULT NULL,
  `TypeOfPayment` varchar(40) NOT NULL,
  `Total` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`PaymentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `RouteID` int NOT NULL AUTO_INCREMENT,
  `Departure` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Arrival` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`RouteID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'TP.HCM','ĐÀ LẠT'),(2,'VŨNG TÀU','NHA TRANG'),(3,'MŨI NÉ','CẦN THƠ'),(4,'TP.HCM','CÀ MAU'),(5,'ĐÀ LẠT','TP.HCM'),(6,'ĐẮK LẮK','TP.HCM'),(7,'CÀ MAU','TP.HCM'),(8,'TP.HCM','ĐẮK LẮK'),(9,'ĐÀ NẴNG','HÀ NỘI'),(10,'HÀ NỘI','ĐÀ NẴNG');
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `SeatNumber` int NOT NULL,
  `CarID` int DEFAULT NULL,
  `availableSeat` int NOT NULL,
  `TripID` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carid_idx` (`CarID`),
  KEY `fk_tripid_idx` (`TripID`),
  CONSTRAINT `fk_carid` FOREIGN KEY (`CarID`) REFERENCES `car` (`CarID`),
  CONSTRAINT `fk_tripid` FOREIGN KEY (`TripID`) REFERENCES `tripinfor` (`TripID`)
) ENGINE=InnoDB AUTO_INCREMENT=747 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,1,1,0,1),(2,2,1,0,1),(3,3,1,0,1),(4,4,1,0,1),(5,5,1,0,1),(6,6,1,0,1),(7,7,1,0,1),(8,8,1,0,1),(9,9,1,0,1),(10,10,1,0,1),(11,11,1,0,1),(12,12,1,0,1),(13,13,1,0,1),(14,14,1,0,1),(15,15,1,0,1),(16,16,1,0,1),(17,17,1,0,1),(18,18,1,0,1),(19,19,1,0,1),(20,20,1,0,1),(21,21,1,0,1),(22,22,1,0,1),(23,23,1,0,1),(24,24,1,0,1),(25,25,1,0,1),(27,1,2,0,2),(28,2,2,0,2),(29,3,2,0,2),(30,4,2,0,2),(31,5,2,0,2),(32,6,2,0,2),(33,7,2,0,2),(34,8,2,0,2),(35,9,2,0,2),(36,10,2,0,2),(37,11,2,0,2),(38,12,2,0,2),(39,13,2,0,2),(40,14,2,0,2),(41,15,2,0,2),(42,16,2,0,2),(43,17,2,0,2),(44,18,2,0,2),(45,19,2,0,2),(46,20,2,0,2),(47,1,3,0,3),(48,2,3,0,3),(49,3,3,0,3),(50,4,3,0,3),(51,5,3,0,3),(52,6,3,0,3),(53,7,3,0,3),(54,8,3,0,3),(55,9,3,0,3),(56,10,3,0,3),(57,11,3,0,3),(58,12,3,0,3),(59,13,3,0,3),(60,14,3,0,3),(61,15,3,0,3),(62,16,3,0,3),(63,17,3,0,3),(64,18,3,0,3),(65,19,3,0,3),(66,20,3,0,3),(67,21,3,0,3),(68,22,3,0,3),(69,23,3,0,3),(70,24,3,0,3),(71,25,3,0,3),(72,26,3,0,3),(73,27,3,0,3),(74,28,3,0,3),(75,29,3,0,3),(76,30,3,0,3),(77,1,6,1,4),(78,2,6,1,4),(79,3,6,1,4),(80,4,6,1,4),(81,5,6,1,4),(82,6,6,1,4),(83,7,6,1,4),(84,8,6,1,4),(85,9,6,0,4),(86,10,6,1,4),(87,11,6,1,4),(88,12,6,0,4),(89,13,6,0,4),(90,14,6,1,4),(91,15,6,1,4),(92,1,7,0,5),(93,2,7,1,5),(94,3,7,1,5),(95,4,7,0,5),(96,5,7,1,5),(97,6,7,1,5),(98,7,7,1,5),(99,8,7,1,5),(100,9,7,1,5),(101,10,7,1,5),(102,11,7,1,5),(103,12,7,1,5),(104,13,7,1,5),(105,14,7,1,5),(106,15,7,1,5),(107,16,7,1,5),(108,17,7,1,5),(109,18,7,0,5),(110,19,7,0,5),(111,20,7,1,5),(112,1,4,0,6),(113,2,4,0,6),(114,3,4,0,6),(115,4,4,0,6),(116,5,4,1,6),(117,6,4,1,6),(118,7,4,1,6),(119,8,4,1,6),(120,9,4,0,6),(121,10,4,0,6),(122,11,4,1,6),(123,12,4,1,6),(124,13,4,1,6),(125,14,4,0,6),(126,15,4,1,6),(127,16,4,1,6),(128,17,4,1,6),(129,18,4,1,6),(130,19,4,1,6),(131,20,4,1,6),(132,21,4,0,6),(133,22,4,0,6),(134,23,4,1,6),(135,24,4,1,6),(136,25,4,0,6),(137,26,4,0,6),(138,27,4,1,6),(139,28,4,1,6),(140,29,4,0,6),(141,30,4,0,6),(142,31,4,0,6),(143,32,4,1,6),(144,33,4,0,6),(145,34,4,0,6),(146,35,4,0,6),(147,1,5,0,7),(148,2,5,0,7),(149,3,5,0,7),(150,4,5,0,7),(151,5,5,0,7),(152,6,5,0,7),(153,7,5,0,7),(154,8,5,0,7),(155,9,5,1,7),(156,10,5,0,7),(157,11,5,0,7),(158,12,5,1,7),(159,13,5,0,7),(160,14,5,1,7),(161,15,5,1,7),(162,16,5,0,7),(163,17,5,0,7),(164,18,5,0,7),(165,19,5,0,7),(166,20,5,1,7),(167,21,5,1,7),(168,22,5,1,7),(169,23,5,1,7),(170,24,5,1,7),(171,25,5,0,7),(172,1,8,0,8),(173,2,8,0,8),(174,3,8,0,8),(175,4,8,0,8),(176,5,8,0,8),(177,6,8,0,8),(178,7,8,0,8),(179,8,8,0,8),(180,9,8,0,8),(181,10,8,0,8),(182,11,8,0,8),(183,12,8,0,8),(184,13,8,0,8),(185,14,8,0,8),(186,15,8,0,8),(187,16,8,0,8),(188,17,8,0,8),(189,18,8,0,8),(190,19,8,0,8),(191,20,8,0,8),(192,21,8,0,8),(193,22,8,0,8),(194,23,8,0,8),(195,24,8,0,8),(196,25,8,0,8),(197,26,8,0,8),(198,27,8,0,8),(199,28,8,0,8),(200,29,8,0,8),(201,30,8,0,8),(202,31,8,0,8),(203,32,8,0,8),(204,33,8,0,8),(205,34,8,0,8),(206,35,8,0,8),(207,1,7,0,9),(208,2,7,0,9),(209,3,7,0,9),(210,4,7,0,9),(211,5,7,0,9),(212,6,7,0,9),(213,7,7,0,9),(214,8,7,0,9),(215,9,7,0,9),(216,10,7,0,9),(217,11,7,0,9),(218,12,7,0,9),(219,13,7,0,9),(220,14,7,0,9),(221,15,7,0,9),(222,16,7,0,9),(223,17,7,0,9),(224,18,7,0,9),(225,19,7,0,9),(226,20,7,0,9),(227,1,10,0,10),(228,2,10,0,10),(229,3,10,0,10),(230,4,10,0,10),(231,5,10,0,10),(232,6,10,0,10),(233,7,10,0,10),(234,8,10,0,10),(235,9,10,0,10),(236,10,10,0,10),(237,11,10,0,10),(238,12,10,0,10),(239,13,10,0,10),(240,14,10,0,10),(241,15,10,0,10),(242,16,10,0,10),(243,17,10,0,10),(244,18,10,0,10),(245,19,10,0,10),(246,20,10,0,10),(247,21,10,0,10),(248,22,10,0,10),(249,23,10,0,10),(250,24,10,0,10),(251,25,10,0,10),(252,26,10,0,10),(253,27,10,0,10),(254,28,10,0,10),(255,29,10,0,10),(256,30,10,0,10),(257,31,10,0,10),(258,32,10,0,10),(259,33,10,0,10),(260,34,10,0,10),(261,35,10,0,10),(262,36,10,0,10),(263,37,10,0,10),(264,1,6,0,11),(265,2,6,0,11),(266,3,6,0,11),(267,4,6,0,11),(268,5,6,0,11),(269,6,6,0,11),(270,7,6,0,11),(271,8,6,0,11),(272,9,6,0,11),(273,10,6,0,11),(274,11,6,0,11),(275,12,6,0,11),(276,13,6,0,11),(277,14,6,0,11),(278,15,6,0,11),(279,1,2,0,12),(280,2,2,0,12),(281,3,2,0,12),(282,4,2,0,12),(283,5,2,0,12),(284,6,2,0,12),(285,7,2,0,12),(286,8,2,0,12),(287,9,2,0,12),(288,10,2,0,12),(289,11,2,0,12),(290,12,2,0,12),(291,13,2,0,12),(292,14,2,0,12),(293,15,2,0,12),(294,16,2,0,12),(295,17,2,0,12),(296,18,2,0,12),(297,19,2,0,12),(298,20,2,0,12),(299,1,23,0,NULL),(300,2,23,0,NULL),(301,3,23,0,NULL),(302,4,23,0,NULL),(303,5,23,0,NULL),(304,6,23,0,NULL),(305,7,23,0,NULL),(306,8,23,0,NULL),(307,9,23,0,NULL),(308,10,23,0,NULL),(309,11,23,0,NULL),(310,12,23,0,NULL),(311,13,23,0,NULL),(312,14,23,0,NULL),(313,15,23,0,NULL),(314,16,23,0,NULL),(315,17,23,0,NULL),(316,18,23,0,NULL),(317,19,23,0,NULL),(318,20,23,0,NULL),(319,21,23,0,NULL),(320,22,23,0,NULL),(321,23,23,0,NULL),(322,24,23,0,NULL),(323,25,23,0,NULL),(324,26,23,0,NULL),(325,27,23,0,NULL),(326,28,23,0,NULL),(327,29,23,0,NULL),(328,30,23,0,NULL),(329,31,23,0,NULL),(330,32,23,0,NULL),(427,1,25,0,NULL),(428,2,25,0,NULL),(429,3,25,0,NULL),(430,4,25,0,NULL),(431,5,25,0,NULL),(432,6,25,0,NULL),(433,7,25,0,NULL),(434,8,25,0,NULL),(435,9,25,0,NULL),(436,10,25,0,NULL),(437,11,25,0,NULL),(438,12,25,0,NULL),(439,13,25,0,NULL),(440,14,25,0,NULL),(441,15,25,0,NULL),(442,16,25,0,NULL),(443,17,25,0,NULL),(444,18,25,0,NULL),(445,19,25,0,NULL),(446,20,25,0,NULL),(447,21,25,0,NULL),(448,22,25,0,NULL),(449,23,25,0,NULL),(450,24,25,0,NULL),(451,25,25,0,NULL),(452,26,25,0,NULL),(453,27,25,0,NULL),(454,28,25,0,NULL),(455,29,25,0,NULL),(456,30,25,0,NULL),(457,31,25,0,NULL),(458,32,25,0,NULL),(459,1,26,0,NULL),(460,2,26,0,NULL),(461,3,26,0,NULL),(462,4,26,0,NULL),(463,5,26,0,NULL),(464,6,26,0,NULL),(465,7,26,0,NULL),(466,8,26,0,NULL),(467,9,26,0,NULL),(468,10,26,0,NULL),(469,11,26,0,NULL),(470,12,26,0,NULL),(471,13,26,0,NULL),(472,14,26,0,NULL),(473,15,26,0,NULL),(474,16,26,0,NULL),(475,17,26,0,NULL),(476,18,26,0,NULL),(477,19,26,0,NULL),(478,20,26,0,NULL),(479,21,26,0,NULL),(480,22,26,0,NULL),(481,23,26,0,NULL),(482,24,26,0,NULL),(483,25,26,0,NULL),(484,26,26,0,NULL),(485,27,26,0,NULL),(486,28,26,0,NULL),(487,29,26,0,NULL),(488,30,26,0,NULL),(489,31,26,0,NULL),(490,32,26,0,NULL),(491,1,26,0,NULL),(492,2,26,0,NULL),(493,3,26,0,NULL),(494,4,26,0,NULL),(495,5,26,0,NULL),(496,6,26,0,NULL),(497,7,26,0,NULL),(498,8,26,0,NULL),(499,9,26,0,NULL),(500,10,26,0,NULL),(501,11,26,0,NULL),(502,12,26,0,NULL),(503,13,26,0,NULL),(504,14,26,0,NULL),(505,15,26,0,NULL),(506,16,26,0,NULL),(507,17,26,0,NULL),(508,18,26,0,NULL),(509,19,26,0,NULL),(510,20,26,0,NULL),(511,21,26,0,NULL),(512,22,26,0,NULL),(513,23,26,0,NULL),(514,24,26,0,NULL),(515,25,26,0,NULL),(516,26,26,0,NULL),(517,27,26,0,NULL),(518,28,26,0,NULL),(519,29,26,0,NULL),(520,30,26,0,NULL),(521,31,26,0,NULL),(522,32,26,0,NULL),(523,1,27,0,NULL),(524,2,27,0,NULL),(525,3,27,0,NULL),(526,4,27,0,NULL),(527,5,27,0,NULL),(528,6,27,0,NULL),(529,7,27,0,NULL),(530,8,27,0,NULL),(531,9,27,0,NULL),(532,10,27,0,NULL),(533,11,27,0,NULL),(534,12,27,0,NULL),(535,13,27,0,NULL),(536,14,27,0,NULL),(537,15,27,0,NULL),(538,16,27,0,NULL),(539,17,27,0,NULL),(540,18,27,0,NULL),(541,19,27,0,NULL),(542,20,27,0,NULL),(543,21,27,0,NULL),(544,22,27,0,NULL),(545,23,27,0,NULL),(546,24,27,0,NULL),(547,25,27,0,NULL),(548,26,27,0,NULL),(549,27,27,0,NULL),(550,28,27,0,NULL),(551,29,27,0,NULL),(552,30,27,0,NULL),(553,31,27,0,NULL),(554,32,27,0,NULL),(555,1,28,0,NULL),(556,2,28,0,NULL),(557,3,28,0,NULL),(558,4,28,0,NULL),(559,5,28,0,NULL),(560,6,28,0,NULL),(561,7,28,0,NULL),(562,8,28,0,NULL),(563,9,28,0,NULL),(564,10,28,0,NULL),(565,11,28,0,NULL),(566,12,28,0,NULL),(567,13,28,0,NULL),(568,14,28,0,NULL),(569,15,28,0,NULL),(570,16,28,0,NULL),(571,17,28,0,NULL),(572,18,28,0,NULL),(573,19,28,0,NULL),(574,20,28,0,NULL),(575,21,28,0,NULL),(576,22,28,0,NULL),(577,23,28,0,NULL),(578,24,28,0,NULL),(579,25,28,0,NULL),(580,26,28,0,NULL),(581,27,28,0,NULL),(582,28,28,0,NULL),(583,29,28,0,NULL),(584,30,28,0,NULL),(585,31,28,0,NULL),(586,32,28,0,NULL),(587,1,29,0,NULL),(588,2,29,0,NULL),(589,3,29,0,NULL),(590,4,29,0,NULL),(591,5,29,0,NULL),(592,6,29,0,NULL),(593,7,29,0,NULL),(594,8,29,0,NULL),(595,9,29,0,NULL),(596,10,29,0,NULL),(597,11,29,0,NULL),(598,12,29,0,NULL),(599,13,29,0,NULL),(600,14,29,0,NULL),(601,15,29,0,NULL),(602,16,29,0,NULL),(603,17,29,0,NULL),(604,18,29,0,NULL),(605,19,29,0,NULL),(606,20,29,0,NULL),(607,21,29,0,NULL),(608,22,29,0,NULL),(609,23,29,0,NULL),(610,24,29,0,NULL),(611,25,29,0,NULL),(612,26,29,0,NULL),(613,27,29,0,NULL),(614,28,29,0,NULL),(615,29,29,0,NULL),(616,30,29,0,NULL),(617,31,29,0,NULL),(618,32,29,0,NULL),(619,1,30,0,NULL),(620,2,30,0,NULL),(621,3,30,0,NULL),(622,4,30,0,NULL),(623,5,30,0,NULL),(624,6,30,0,NULL),(625,7,30,0,NULL),(626,8,30,0,NULL),(627,9,30,0,NULL),(628,10,30,0,NULL),(629,11,30,0,NULL),(630,12,30,0,NULL),(631,13,30,0,NULL),(632,14,30,0,NULL),(633,15,30,0,NULL),(634,16,30,0,NULL),(635,17,30,0,NULL),(636,18,30,0,NULL),(637,19,30,0,NULL),(638,20,30,0,NULL),(639,21,30,0,NULL),(640,22,30,0,NULL),(641,23,30,0,NULL),(642,24,30,0,NULL),(643,25,30,0,NULL),(644,26,30,0,NULL),(645,27,30,0,NULL),(646,28,30,0,NULL),(647,29,30,0,NULL),(648,30,30,0,NULL),(649,31,30,0,NULL),(650,32,30,0,NULL),(651,1,31,0,NULL),(652,2,31,0,NULL),(653,3,31,0,NULL),(654,4,31,0,NULL),(655,5,31,0,NULL),(656,6,31,0,NULL),(657,7,31,0,NULL),(658,8,31,0,NULL),(659,9,31,0,NULL),(660,10,31,0,NULL),(661,11,31,0,NULL),(662,12,31,0,NULL),(663,13,31,0,NULL),(664,14,31,0,NULL),(665,15,31,0,NULL),(666,16,31,0,NULL),(667,17,31,0,NULL),(668,18,31,0,NULL),(669,19,31,0,NULL),(670,20,31,0,NULL),(671,21,31,0,NULL),(672,22,31,0,NULL),(673,23,31,0,NULL),(674,24,31,0,NULL),(675,25,31,0,NULL),(676,26,31,0,NULL),(677,27,31,0,NULL),(678,28,31,0,NULL),(679,29,31,0,NULL),(680,30,31,0,NULL),(681,31,31,0,NULL),(682,32,31,0,NULL),(683,1,32,0,NULL),(684,2,32,0,NULL),(685,3,32,0,NULL),(686,4,32,0,NULL),(687,5,32,0,NULL),(688,6,32,0,NULL),(689,7,32,0,NULL),(690,8,32,0,NULL),(691,9,32,0,NULL),(692,10,32,0,NULL),(693,11,32,0,NULL),(694,12,32,0,NULL),(695,13,32,0,NULL),(696,14,32,0,NULL),(697,15,32,0,NULL),(698,16,32,0,NULL),(699,17,32,0,NULL),(700,18,32,0,NULL),(701,19,32,0,NULL),(702,20,32,0,NULL),(703,21,32,0,NULL),(704,22,32,0,NULL),(705,23,32,0,NULL),(706,24,32,0,NULL),(707,25,32,0,NULL),(708,26,32,0,NULL),(709,27,32,0,NULL),(710,28,32,0,NULL),(711,29,32,0,NULL),(712,30,32,0,NULL),(713,31,32,0,NULL),(714,32,32,0,NULL),(715,1,33,0,NULL),(716,2,33,0,NULL),(717,3,33,0,NULL),(718,4,33,0,NULL),(719,5,33,0,NULL),(720,6,33,0,NULL),(721,7,33,0,NULL),(722,8,33,0,NULL),(723,9,33,0,NULL),(724,10,33,0,NULL),(725,11,33,0,NULL),(726,12,33,0,NULL),(727,13,33,0,NULL),(728,14,33,0,NULL),(729,15,33,0,NULL),(730,16,33,0,NULL),(731,17,33,0,NULL),(732,18,33,0,NULL),(733,19,33,0,NULL),(734,20,33,0,NULL),(735,21,33,0,NULL),(736,22,33,0,NULL),(737,23,33,0,NULL),(738,24,33,0,NULL),(739,25,33,0,NULL),(740,26,33,0,NULL),(741,27,33,0,NULL),(742,28,33,0,NULL),(743,29,33,0,NULL),(744,30,33,0,NULL),(745,31,33,0,NULL),(746,32,33,0,NULL);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tripinfor`
--

DROP TABLE IF EXISTS `tripinfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tripinfor` (
  `TripID` int NOT NULL AUTO_INCREMENT,
  `RouteID` int DEFAULT NULL,
  `StartTime` time DEFAULT NULL,
  `EndTime` time DEFAULT NULL,
  `CarID` int DEFAULT NULL,
  `price` decimal(10,0) NOT NULL,
  `arrivalDetail` varchar(255) DEFAULT NULL,
  `Day` date DEFAULT NULL,
  `departureDetail` varchar(255) DEFAULT NULL,
  `Is_Special_Day` bit(1) DEFAULT b'0',
  PRIMARY KEY (`TripID`),
  KEY `FK_RouteTrip_idx` (`RouteID`),
  KEY `FK_CarTrip_idx` (`CarID`),
  CONSTRAINT `FK_CarTrip` FOREIGN KEY (`CarID`) REFERENCES `car` (`CarID`),
  CONSTRAINT `FK_RouteTrip` FOREIGN KEY (`RouteID`) REFERENCES `route` (`RouteID`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tripinfor`
--

LOCK TABLES `tripinfor` WRITE;
/*!40000 ALTER TABLE `tripinfor` DISABLE KEYS */;
INSERT INTO `tripinfor` VALUES (1,2,'19:00:00','03:05:00',1,255000,'BX. NHA TRANG','2023-03-20','BX. VUNG TAU',_binary '\0'),(2,2,'08:00:00','16:30:00',2,250000,'VP. NHA TRANG','2023-03-20','VP. HOA MAI',_binary '\0'),(3,2,'13:10:00','20:40:00',3,270000,'BX. NHA TRANG','2023-03-20','BX. VUNG TAU',_binary '\0'),(4,1,'05:00:00','11:35:00',6,280000,'BX. DA LAT','2023-03-20','BX. MIEN DONG',_binary '\0'),(5,1,'22:01:00','05:41:00',7,300000,'BX. DA LAT','2023-03-20','BX. MIEN TAY',_binary '\0'),(6,1,'23:59:00','07:44:00',4,270000,'BX. DA THIEN','2023-03-20','VP AN DUONG VUONG',_binary '\0'),(7,1,'07:00:00','15:00:00',5,240000,'BX. DA LAT','2023-03-20','BX. AN SUONG',_binary '\0'),(8,1,'23:00:00','07:00:00',8,250000,'VP. DA LAT','2023-03-20','VP. TAN BINH',_binary '\0'),(9,3,'15:30:00','01:55:00',7,300000,'BX. TRUNG TAM','2023-03-20','BX. BINH THUAN',_binary '\0'),(10,3,'17:45:00','03:15:00',10,250000,'BX. TRUNG TAM','2023-03-16','BX. LA GI',_binary '\0'),(11,3,'18:30:00','04:45:00',6,280000,'BX. CAN THO','2023-03-16','BX. MUI NE',_binary '\0'),(12,3,'22:00:00','08:15:00',2,250000,'BX. CAN THO','2023-03-16','CHO LUONG SON',_binary '\0'),(13,4,'21:30:00','05:05:00',11,320000,'BX. MIEN TAY','2023-03-16','BX. CA MAU',_binary '\0'),(14,4,'12:00:00','20:10:00',12,200000,'TRAM XE NGOC ANH','2023-03-16','BX. CA MAU',_binary '\0'),(15,6,'08:30:00','17:40:00',13,450000,'BX. NGA TU GA','2023-03-16','CU KUIN',_binary '\0'),(16,6,'18:15:00','06:10:00',14,320000,'NGA TU AN SUONG','2023-03-16','VP. M\'DRAK',_binary '\0'),(17,5,'12:00:00','19:00:00',15,420000,'VP. DA LAT','2023-03-16','VP. TAN BINH',_binary '\0'),(18,5,'14:30:00','22:30:00',16,380000,'BX. DA THIEN','2023-03-16','BX. MIEN TAY',_binary '\0'),(20,7,'22:00:00','06:20:00',11,320000,'BX. DONG TAM','2023-03-16','BX. MIEN TAY',_binary '\0'),(21,7,'09:45:00','18:00:00',12,200000,'BX. CA MAU','2023-03-16','BX. MIEN TAY',_binary '\0'),(22,7,'08:40:00','15:20:00',11,320000,'BX. CA MAU','2023-03-16','BX. MIEN TAY',_binary '\0'),(23,8,'09:30:00','18:15:00',13,450000,'TP. BUON MA THUOT','2023-03-16','VP. TAN BINH',_binary '\0'),(24,8,'19:15:00','04:40:00',14,320000,'TP. BUON MA THUOT','2023-03-16','NGA TU GA',_binary '\0'),(25,9,'20:30:00','10:00:00',18,500000,'VP. DA NANG','2023-03-16','BX. NUOC NGAM',_binary '\0'),(26,10,'16:30:00','05:30:00',18,500000,'BX. NUOC NGAM','2023-03-16','VP. DA NANG',_binary '\0'),(27,9,'17:15:00','07:40:00',19,700000,'BX. TRUNG TAM','2023-03-16','VP.HA NOI',_binary '\0'),(28,10,'14:00:00','06:40:00',19,700000,'VP.HA NOI','2023-03-16','BX. TRUNG TAM',_binary '\0'),(29,1,'17:00:00','01:00:00',6,255000,'BX. DA LAT','2023-03-16','BX. MIEN DONG',_binary '\0'),(30,1,'20:00:00','04:00:00',7,300000,'BX. DA LAT','2023-03-16','BX. MIEN TAY',_binary '\0'),(31,1,'17:00:00','01:00:00',6,255000,'BX. DA LAT','2023-03-16','BX. MIEN DONG',_binary '\0'),(32,1,'20:00:00','04:00:00',7,300000,'BX. DA LAT','2023-03-16','BX. MIEN TAY',_binary '\0'),(33,1,'23:00:00','07:00:00',4,270000,'BX. DA THIEN','2023-03-16','VP AN DUONG VUONG',_binary '\0'),(34,1,'07:00:00','15:00:00',5,240000,'BX. DA LAT','2023-03-24','BX. AN SUONG',_binary '\0'),(35,1,'22:01:00','04:01:00',8,250000,'VP. DA LAT','2023-02-27','VP. TAN BINH',_binary '\0'),(36,2,'19:00:00','03:05:00',1,255000,'BX. NHA TRANG','2023-02-27','BX. VUNG TAU',_binary '\0'),(37,2,'08:00:00','16:30:00',2,250000,'VP. NHA TRANG','2023-02-27','VP. HOA MAI',_binary '\0'),(38,2,'13:10:00','20:40:00',3,270000,'BX. NHA TRANG','2023-02-27','BX. VUNG TAU',_binary '\0'),(39,3,'15:30:00','01:55:00',7,300000,'BX. TRUNG TAM','2023-02-27','BX. BINH THUAN',_binary '\0'),(40,3,'17:45:00','03:15:00',10,250000,'BX. TRUNG TAM','2023-02-27','BX. LA GI',_binary '\0'),(41,3,'22:00:00','08:15:00',6,250000,'BX. CAN THO','2023-02-27','CHO LUONG SON',_binary '\0'),(42,3,'18:30:00','04:45:00',2,280000,'BX. CAN THO','2023-02-27','BX. MUI NE',_binary '\0'),(43,4,'21:30:00','05:05:00',11,320000,'BX. MIEN TAY','2023-04-30','BX. CA MAU',_binary ''),(44,4,'12:00:00','20:10:00',12,200000,'TRAM XE NGOC ANH','2023-04-30','BX. CA MAU',_binary ''),(45,4,'15:30:00','23:40:00',20,320000,'BX. MIEN TAY','2023-04-30','BX. CA MAU',_binary ''),(46,8,'09:30:00','18:15:00',13,450000,'TP. BUON MA THUOT','2023-04-30','VP. TAN BINH',_binary ''),(47,8,'19:15:00','04:40:00',14,320000,'TP. BUON MA THUOT','2023-04-30','NGA TU GA',_binary ''),(48,8,'15:00:00','23:00:00',21,450000,'TP. BUON MA THUOT','2023-04-30','VP. TAN BINh',_binary ''),(49,8,'18:00:00','04:00:00',22,320000,'TP. BUON MA THUOT','2023-04-30','NGA TU GA',_binary ''),(50,2,'11:00:00','17:35:00',17,300000,'BX. NHA TRANG','2023-03-23','BX. VUNG TAU',_binary '\0'),(52,2,'17:35:00','23:35:00',17,300000,'BX. NHA TRANG','2023-03-24','BX. VUNG TAU',_binary '\0'),(53,2,'19:41:00','23:41:00',17,300000,'BX. NHA TRANG','2023-03-23','BX. VUNG TAU',_binary '\0'),(54,2,'23:43:00','03:43:00',17,300000,'BX. NHA TRANG','2023-03-23','BX. VUNG TAU',_binary '\0'),(55,2,'19:46:00','23:46:00',17,300000,'BX. NHA TRANG','2023-03-23','BX. VUNG TAU',_binary '\0'),(56,1,'05:00:00','13:00:00',26,300000,'BX. Đà Lạt','2023-03-23','BX. Miền Tây',_binary '\0'),(57,1,'06:15:00','14:15:00',33,300000,'BX. Đà Lạt','2023-03-23','BX. Miền Đông',_binary '\0'),(58,1,'11:00:00','19:00:00',32,300000,'BX. Đà Lạt','2023-03-23','BX. Ngã 4 Ga',_binary '\0'),(59,1,'14:30:00','22:30:00',30,300000,'BX. Đà Lạt','2023-03-23','BX. Miền Tây',_binary '\0'),(60,1,'23:00:00','19:00:00',29,300000,'BX. Đà Lạt','2023-03-23','BX. An Sương',_binary '\0'),(61,1,'23:30:00','07:30:00',28,300000,'BX. Đà Lạt','2023-03-23','BX. Miền Đông',_binary '\0'),(62,1,'21:30:00','05:30:00',27,300000,'BX. Đà Lạt','2023-03-23','BX. An Sương',_binary '\0'),(64,1,'16:00:00','00:00:00',25,300000,'BX. Đà Lạt','2023-03-23','BX. Miền Đông Mới',_binary '\0'),(65,1,'12:15:00','20:15:00',1,300000,'BX. Đà Lạt','2023-03-23','BX. Ngã 4 Ga',_binary '\0'),(66,1,'05:00:00','13:00:00',23,300000,'BX. Đà Lạt','2023-03-23','BX. Miền Tây',_binary '\0'),(67,1,'12:15:00','08:15:00',31,300000,'BX. Đà Lạt','2023-03-23','BX. Ngã 4 Ga',_binary '\0');
/*!40000 ALTER TABLE `tripinfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Email` varchar(30) DEFAULT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `FullName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Sex` varchar(8) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Role` varchar(255) DEFAULT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Provider` varchar(255) DEFAULT NULL,
  `Enabled` int NOT NULL,
  `Verification_code` varchar(255) DEFAULT NULL,
  `Company` int DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  KEY `FKeo0tof7wcbrqhhmqlil8pcmqe` (`Company`),
  CONSTRAINT `FKeo0tof7wcbrqhhmqlil8pcmqe` FOREIGN KEY (`Company`) REFERENCES `carcompany` (`CompanyID`),
  CONSTRAINT `CHK_Sex` CHECK (((`Sex` = _utf8mb4'Male') or (`Sex` = _utf8mb4'Female')))
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'nhan@gmail.com','0523350097','Vo Danh Nhan','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','admin','nhan','LOCAL',0,NULL,NULL),(5,'duyen@gmail.com','483839','vo my duyen','Female','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','duyen','LOCAL',0,NULL,NULL),(6,'yen@gmail.com','584583483','Nguyen Hai Yen','Female','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','yen','LOCAL',0,NULL,NULL),(8,'phuongtrang@gmail.com','05242142','Phuong Trang Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','phuongtrang','LOCAL',0,NULL,1),(10,'phuochung@gmail.com','6457457457','Phuoc Hung Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','phuochung','LOCAL',0,NULL,2),(11,'thanhbuoi@gmail.com','6457457457','Thanh Buoi Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','thanhbuoi','LOCAL',0,NULL,3),(12,'dananh@gmail.com','748392984','Dan Anh Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','dananh','LOCAL',0,NULL,4),(14,'huhu@gmail.com','6457458','Nguyen Hu Huuuuu','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','customer','huhuhuhu','LOCAL',0,NULL,NULL),(15,'quocbao@gmaill.com','578543','Quoc Bao Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','quocbao','LOCAL',0,NULL,5),(16,'danh@gmail.com','0908883588','Nguyen Thanh Danh','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','customer','danh','LOCAL',0,NULL,NULL),(17,'nhandeptrai@gmail.com','1234567890','Nhan Danh','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','customer','nhandeptrai','LOCAL',0,NULL,NULL),(22,'simnhankid13042002@gmail.com',NULL,'nhân võ','Male',NULL,'customer',NULL,'GOOGLE',0,NULL,NULL),(24,'son@gmail.com','0989898988','Tran Bao Son','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','customer','son','LOCAL',0,NULL,NULL),(25,'thanhan@gmail.com','0786665677','Thanh An Busline','Male','$2a$10$ghO3N6M1tO/OA5R0LkErV.N6GPYcNwPCXSaH2.fLto9Ie.j7FFfPS','employee','nghia','LOCAL',0,NULL,6),(28,'nhanvdse161469@fpt.edu.vn','0908887876','Vo Danh Nhan','Male','$2a$10$0DEjxCaAt1QqdRf2hotNjOx1KkV097xvlvfVezydsVYHQjNqoo6bu','customer','nhanvdse161469','LOCAL',1,NULL,NULL),(30,'vonhan.job@gmail.com','09088892846','Võ Nhân','male','$2a$10$sArFWskg.SFGtJSXg3qKquktFDLhSW2gYiZOJOu5CP/avEyfG.yuC','customer','nhanvo123','LOCAL',1,NULL,NULL),(31,'thuantien@gmail.com','0976823759','Thuan Tien Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','thuantien','LOCAL',0,NULL,7),(32,'bayviet@gmail.com','0942829587','Bay Viet Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','bayviet','LOCAL',0,NULL,8),(33,'minhnghia@gmail.com','0957891234','Minh Nghia Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','minhnghia','LOCAL',0,NULL,9),(34,'tienoanh@gmail.com','0962718954','Tien Oanh Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','tienoanh','LOCAL',0,NULL,10),(35,'hao@gmail.com','0989518975','Hao  Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','haobus','LOCAL',0,NULL,11),(36,'ngocanh@gmail.com','0912346798','Ngoc Anh Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','ngocanh','LOCAL',0,NULL,12),(37,'tuantrung@gmail.com','0122148760','Tuan Trung Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','tuantrung','LOCAL',0,NULL,13),(38,'nguyendiu@gmail.com','0762892375','Nguyen Diu Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','nguyendiu','LOCAL',0,NULL,14),(39,'ananh@gmail.com','0912489789','An Anh Limousine','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','ananh','LOCAL',0,NULL,15),(40,'trongminh@gmail.com','0896779811','Trong Minh Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','trongminh','LOCAL',0,NULL,16),(41,'kimchi@gmail.com','0124786214','Kim Chi Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','kimchi','LOCAL',0,NULL,17),(42,'duongvu@gmail.com','0372987124','Duong Vu Busline','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','duongvu','LOCAL',0,NULL,18);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'etransporationdb'
--

--
-- Dumping routines for database 'etransporationdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-23  0:36:32

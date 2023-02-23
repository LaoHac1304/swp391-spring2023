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
  `BookingID` int NOT NULL,
  `CustomerID` smallint DEFAULT NULL,
  `TripID` smallint DEFAULT NULL,
  `Quantity` tinyint DEFAULT NULL,
  PRIMARY KEY (`BookingID`),
  KEY `FK_CustomerBooking` (`CustomerID`),
  KEY `FK_TripBooking` (`TripID`),
  CONSTRAINT `FK_CustomerBooking` FOREIGN KEY (`CustomerID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `FK_TripBooking` FOREIGN KEY (`TripID`) REFERENCES `tripinfor` (`TripID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookingdetail`
--

DROP TABLE IF EXISTS `bookingdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookingdetail` (
  `BDetailID` int NOT NULL,
  `SeatNumber` tinyint DEFAULT NULL,
  `Price` decimal(4,2) DEFAULT NULL,
  `BookingID` int DEFAULT NULL,
  PRIMARY KEY (`BDetailID`),
  KEY `FK_BookingIDDetail` (`BookingID`),
  CONSTRAINT `FK_BookingIDDetail` FOREIGN KEY (`BookingID`) REFERENCES `booking` (`BookingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookingdetail`
--

LOCK TABLES `bookingdetail` WRITE;
/*!40000 ALTER TABLE `bookingdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookingdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `CarID` smallint NOT NULL AUTO_INCREMENT,
  `Capacity` tinyint DEFAULT NULL,
  `CompanyID` smallint DEFAULT NULL,
  `PlateNumber` varchar(20) NOT NULL,
  PRIMARY KEY (`CarID`),
  KEY `FK_CompanyCar` (`CompanyID`),
  CONSTRAINT `FK_CompanyCar` FOREIGN KEY (`CompanyID`) REFERENCES `carcompany` (`CompanyID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,40,1,'50H1-01234'),(2,35,2,'50H1-05235'),(3,40,3,'50H1-89088'),(4,40,4,'72C1-19284'),(5,32,5,'60C1-78291'),(6,34,6,'70B2-65871'),(7,32,7,'69A1-99999'),(8,35,8,'76C1-11223'),(9,36,9,'70A1-12345'),(10,40,10,'47C1-88888');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carcompany`
--

DROP TABLE IF EXISTS `carcompany`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carcompany` (
  `CompanyID` smallint NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `PhoneNumber` varchar(20) NOT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `BusinessLicense` tinyint(1) DEFAULT NULL,
  `Province` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `RouteID` smallint DEFAULT NULL,
  PRIMARY KEY (`CompanyID`),
  KEY `FK_RouteCarCompany` (`RouteID`),
  CONSTRAINT `FK_RouteCarCompany` FOREIGN KEY (`RouteID`) REFERENCES `route` (`RouteID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carcompany`
--

LOCK TABLES `carcompany` WRITE;
/*!40000 ALTER TABLE `carcompany` DISABLE KEYS */;
INSERT INTO `carcompany` VALUES (1,'Phuong Trang','0908887655','District 1',1,'Ho Chi Minh',1),(2,'Hoa Mai','19001234','District 5',1,'Ho Chi Minh',1),(3,'Thanh Buoi','19008989','District 1',1,'Ho Chi Minh',1),(4,'Toan Thang','19000001','District 7',1,'Vung Tau',1),(5,'Cuong Seven','19008080','Long Thanh',1,'Dong Nai',2),(6,'Dong Phuoc','19008080','Hoa Thanh',1,'Tay Ninh',2),(7,'Tuan Hiep','19000102','Ca Mau',1,'Ca Mau',1),(8,'Bay Viet','18001234','Quang Ngai',1,'Quang Ngai',3),(9,'Le Hai','18008098','Tay Ninh',1,'Tay Ninh',3),(10,'Tien Oanh','19001221','Buon Ma Thuot',1,'Dak Lak',1);
/*!40000 ALTER TABLE `carcompany` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `PayMentID` int NOT NULL,
  `BDetailID` int DEFAULT NULL,
  `Status` tinyint(1) DEFAULT NULL,
  `TypeOfPayment` varchar(40) NOT NULL,
  `Total` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`PayMentID`),
  KEY `FK_BDetailIDPayMent` (`BDetailID`),
  CONSTRAINT `FK_BDetailIDPayMent` FOREIGN KEY (`BDetailID`) REFERENCES `bookingdetail` (`BDetailID`)
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
  `RouteID` smallint NOT NULL AUTO_INCREMENT,
  `Departure` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Arrival` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`RouteID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'TP HCM','DA LAT'),(2,'VUNG TAU','NHA TRANG'),(3,'MUI NE','CAN THO');
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
  `CarID` smallint DEFAULT NULL,
  `availableSeat` int NOT NULL,
  `TripID` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `SeatNumber` (`SeatNumber`),
  KEY `fk_carid` (`CarID`),
  CONSTRAINT `fk_carid` FOREIGN KEY (`CarID`) REFERENCES `car` (`CarID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tripinfor`
--

DROP TABLE IF EXISTS `tripinfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tripinfor` (
  `TripID` smallint NOT NULL AUTO_INCREMENT,
  `RouteID` smallint DEFAULT NULL,
  `StartTime` time DEFAULT NULL,
  `EndTime` time DEFAULT NULL,
  `CarID` smallint DEFAULT NULL,
  `price` int NOT NULL,
  `arrivalDetail` varchar(255) DEFAULT NULL,
  `Day` date DEFAULT NULL,
  `departureDetail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TripID`),
  KEY `FK_RouteTrip` (`RouteID`),
  KEY `FK_CarTrip` (`CarID`),
  CONSTRAINT `FK_CarTrip` FOREIGN KEY (`CarID`) REFERENCES `car` (`CarID`),
  CONSTRAINT `FK_RouteTrip` FOREIGN KEY (`RouteID`) REFERENCES `route` (`RouteID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tripinfor`
--

LOCK TABLES `tripinfor` WRITE;
/*!40000 ALTER TABLE `tripinfor` DISABLE KEYS */;
INSERT INTO `tripinfor` VALUES (1,2,'19:00:00','03:05:00',1,255000,'BX. NHA TRANG','2023-02-24','BX. VUNG TAU'),(2,2,'08:00:00','16:30:00',2,250000,'VP. NHA TRANG','2023-02-24','VP. HOA MAI'),(3,2,'13:10:00','20:40:00',3,270000,'BX. NHA TRANG','2023-02-24','BX. VUNG TAU'),(4,1,'05:00:00','11:35:00',6,280000,'BX. DA LAT','2023-02-24','BX. MIEN DONG'),(5,1,'22:01:00','05:41:00',7,300000,'BX. DA LAT','2023-02-24','BX. MIEN TAY'),(6,1,'23:59:00','07:44:00',4,270000,'BX. DA THIEN','2023-02-25','VP AN DUONG VUONG'),(7,1,'07:00:00','21:00:00',5,240000,'BX. DA LAT','2023-02-24','BX. AN SUONG'),(8,1,'23:00:00','07:00:00',8,250000,'VP. DA LAT','2023-02-25','VP. TAN BINH'),(9,3,'15:30:00','01:55:00',7,300000,'BX. TRUNG TAM','2023-02-24','BX. BINH THUAN'),(10,3,'17:45:00','03:15:00',10,250000,'BX. TRUNG TAM','2023-02-24','BX. LA GI'),(11,3,'18:30:00','04:45:00',6,280000,'BX. CAN THO','2023-02-25','BX. MUI NE'),(12,3,'22:00:00','08:15:00',2,250000,'BX. CAN THO','2023-02-25','CHO LUONG SON');
/*!40000 ALTER TABLE `tripinfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` smallint NOT NULL AUTO_INCREMENT,
  `Email` varchar(30) DEFAULT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `FullName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Sex` varchar(8) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `CHK_Sex` CHECK (((`Sex` = _utf8mb4'Male') or (`Sex` = _utf8mb4'Female')))
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'nhan@gmail.com','0523350097','Vo Danh Nhan','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','admin','nhan','LOCAL'),(5,'duyen@gmail.com','483839','vo my duyen','Female','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','duyen','LOCAL'),(6,'yen@gmail.com','584583483','Nguyen Hai Yen','Female','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','yen','LOCAL'),(8,'chien@gmail.com','05242142','Tran Dinh Chien','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','chien','LOCAL'),(10,'sieunhan@gmail.com','6457457457','Nguyen Sieu Nhan','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','sieu','LOCAL'),(11,'sieunhan123@gmail.com','6457457457','Nguyen Sieu Nhan','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','sieu','LOCAL'),(12,'Siuu@gmail.com','748392984','RolnalSiuuuu','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','Siu','LOCAL'),(14,'huhu@gmail.com','6457458','Nguyen Hu Huuuuu','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','customer','huhuhuhu','LOCAL'),(15,'kohihu@gmail.com','578543','Vo Nhan','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','employee','kokiko','LOCAL'),(16,'danh@gmail.com','0908883588','Nguyen Thanh Danh','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','customer','danh','LOCAL'),(17,'nhandeptrai@gmail.com','1234567890','Nhan Danh','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','customer','nhandeptrai','LOCAL'),(22,'simnhankid13042002@gmail.com',NULL,'nhân võ','Male',NULL,'customer',NULL,'GOOGLE'),(24,'son@gmail.com','0989898988','Tran Bao Son','Male','$2a$10$PXF.O4KutR9C9RE5B7RmAOMATBbz2UzGBlvk8iKLx053LH6Llf9Nq','customer','son','LOCAL'),(25,'nghia@gmail.com','0786665677','Nguyen Huu Nghia','Male','$2a$10$ghO3N6M1tO/OA5R0LkErV.N6GPYcNwPCXSaH2.fLto9Ie.j7FFfPS','employee','nghia','LOCAL'),(26,'nhanvdse161469@fpt.edu.vn',NULL,'Vo Danh Nhan (K16_HCM)',NULL,NULL,'customer',NULL,'GOOGLE');
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

-- Dump completed on 2023-02-24  0:46:19

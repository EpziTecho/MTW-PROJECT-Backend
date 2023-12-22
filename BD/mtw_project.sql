CREATE DATABASE  IF NOT EXISTS `mtw_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mtw_project`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: mtw_project
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `accounting`
--

DROP TABLE IF EXISTS `accounting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounting` (
  `idAccounting` int NOT NULL AUTO_INCREMENT,
  `idBooking` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idAccounting`),
  KEY `fk_accouting_booking` (`idBooking`),
  CONSTRAINT `fk_accouting_booking` FOREIGN KEY (`idBooking`) REFERENCES `booking` (`idBooking`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounting`
--

LOCK TABLES `accounting` WRITE;
/*!40000 ALTER TABLE `accounting` DISABLE KEYS */;
INSERT INTO `accounting` VALUES (1,1,'2023-07-01','12:00:00','Pagado','Nota contabilidad 1'),(2,2,'2023-07-02','13:00:00','Pendiente','Nota contabilidad 2'),(3,3,'2023-07-03','14:00:00','Pagado','Nota contabilidad 3'),(4,4,'2023-07-04','15:00:00','Pendiente','Nota contabilidad 4'),(5,5,'2023-07-05','16:00:00','Pagado','Nota contabilidad 5'),(6,6,'2023-07-06','17:00:00','Pendiente','Nota contabilidad 6'),(7,7,'2023-07-07','18:00:00','Pagado','Nota contabilidad 7'),(8,8,'2023-07-08','19:00:00','Pendiente','Nota contabilidad 8'),(9,9,'2023-07-09','20:00:00','Pagado','Nota contabilidad 9'),(10,10,'2023-07-10','21:00:00','Pendiente','Nota contabilidad 10');
/*!40000 ALTER TABLE `accounting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area` (
  `idArea` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idArea`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'Area 1'),(2,'Area 2'),(3,'Area 3'),(4,'Area 4'),(5,'Area 5'),(6,'Area 6'),(7,'Area 7'),(8,'Area 8'),(9,'Area 9'),(10,'Area 10');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `idBill` int NOT NULL AUTO_INCREMENT,
  `series` varchar(100) DEFAULT NULL,
  `number` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `subTotal` decimal(10,2) DEFAULT NULL,
  `igv` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `idCurrency` int DEFAULT NULL,
  PRIMARY KEY (`idBill`),
  KEY `fk_bill_currency` (`idCurrency`),
  CONSTRAINT `fk_bill_currency` FOREIGN KEY (`idCurrency`) REFERENCES `currency` (`idCurrency`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,'Series 1','Number 1','2023-07-02',100.50,18.09,118.59,1),(2,'Series 2','Number 2','2023-07-02',200.75,36.14,236.89,2),(3,'Series 3','Number 3','2023-07-02',300.00,54.00,354.00,3),(4,'Series 4','Number 4','2023-07-02',400.25,72.05,472.30,4),(5,'Series 5','Number 5','2023-07-02',500.50,90.10,590.60,5),(6,'Series 6','Number 6','2023-07-02',600.75,108.15,709.90,6),(7,'Series 7','Number 7','2023-07-02',700.00,126.20,829.20,7),(8,'Series 8','Number 8','2023-07-02',800.25,144.25,948.50,8),(9,'Series 9','Number 9','2023-07-02',900.50,162.30,1067.80,9),(10,'Series 10','Number 10','2023-07-02',1000.75,180.35,1181.10,10);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `idBooking` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `idCompany` int DEFAULT NULL,
  `idArea` int DEFAULT NULL,
  `idPassenger` int DEFAULT NULL,
  `pickUp` varchar(100) DEFAULT NULL,
  `idDistritPickUp` int DEFAULT NULL,
  `destination` varchar(100) DEFAULT NULL,
  `idDistritDestination` int DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `idDriver` int DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `idBill` int DEFAULT NULL,
  PRIMARY KEY (`idBooking`),
  KEY `fk_booking_company` (`idCompany`),
  KEY `fk_booking_area` (`idArea`),
  KEY `fk_booking_passenger` (`idPassenger`),
  KEY `fk_booking_distrit_pickup` (`idDistritPickUp`),
  KEY `fk_booking_distrit_destination` (`idDistritDestination`),
  KEY `fk_booking_driver` (`idDriver`),
  KEY `fk_booking_bill` (`idBill`),
  CONSTRAINT `fk_booking_area` FOREIGN KEY (`idArea`) REFERENCES `area` (`idArea`),
  CONSTRAINT `fk_booking_bill` FOREIGN KEY (`idBill`) REFERENCES `bill` (`idBill`),
  CONSTRAINT `fk_booking_company` FOREIGN KEY (`idCompany`) REFERENCES `company` (`idCompany`),
  CONSTRAINT `fk_booking_distrit_destination` FOREIGN KEY (`idDistritDestination`) REFERENCES `distrit` (`idDistrit`),
  CONSTRAINT `fk_booking_distrit_pickup` FOREIGN KEY (`idDistritPickUp`) REFERENCES `distrit` (`idDistrit`),
  CONSTRAINT `fk_booking_driver` FOREIGN KEY (`idDriver`) REFERENCES `driver` (`idDriver`),
  CONSTRAINT `fk_booking_passenger` FOREIGN KEY (`idPassenger`) REFERENCES `passenger` (`idPassenger`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'2023-07-01','12:00:00',1,1,1,'Origen 1',1,'Destino 1',2,'Nota 1',50.25,1,'Pendiente',1),(2,'2023-07-02','13:00:00',2,2,2,'Origen 2',2,'Destino 2',3,'Nota 2',60.50,2,'Pendiente',2),(3,'2023-07-03','14:00:00',3,3,3,'Origen 3',3,'Destino 3',4,'Nota 3',70.75,3,'Pendiente',3),(4,'2023-07-04','15:00:00',4,4,4,'Origen 4',4,'Destino 4',5,'Nota 4',80.00,4,'Pendiente',4),(5,'2023-07-05','16:00:00',5,5,5,'Origen 5',5,'Destino 5',6,'Nota 5',90.25,5,'Pendiente',5),(6,'2023-07-06','17:00:00',6,6,6,'Origen 6',6,'Destino 6',7,'Nota 6',100.50,6,'Pendiente',6),(7,'2023-07-07','18:00:00',7,7,7,'Origen 7',7,'Destino 7',8,'Nota 7',110.75,7,'Pendiente',7),(8,'2023-07-08','19:00:00',8,8,8,'Origen 8',8,'Destino 8',9,'Nota 8',121.00,8,'Pendiente',8),(9,'2023-07-09','20:00:00',9,9,9,'Origen 9',9,'Destino 9',10,'Nota 9',131.25,9,'Pendiente',9),(10,'2023-07-10','21:00:00',10,10,10,'Origen 10',10,'Destino 10',1,'Nota 10',141.50,10,'Pendiente',10),(12,'2023-12-21','12:00:00',1,1,1,'Origen 1',1,'Destino 1',2,'Nota ',50.25,10,'Finalizado',10);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `idCompany` int NOT NULL AUTO_INCREMENT,
  `businessName` varchar(100) DEFAULT NULL,
  `idNumber` varchar(100) DEFAULT NULL,
  `adress` varchar(100) DEFAULT NULL,
  `tradeName` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idCompany`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Company 1','12345678901','Direccion 1','Tradename 1','123456789'),(2,'Company 2','23456789012','Direccion 2','Tradename 2','234567890'),(3,'Company 3','34567890123','Direccion 3','Tradename 3','345678901'),(4,'Company 4','45678901234','Direccion 4','Tradename 4','456789012'),(5,'Company 5','56789012345','Direccion 5','Tradename 5','567890123'),(6,'Company 6','67890123456','Direccion 6','Tradename 6','678901234'),(7,'Company 7','78901234567','Direccion 7','Tradename 7','789012345'),(8,'Company 8','89012345678','Direccion 8','Tradename 8','890123456'),(9,'Company 9','90123456789','Direccion 9','Tradename 9','901234567'),(10,'Company 10','01234567890','Direccion 10','Tradename 10','012345678');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency` (
  `idCurrency` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idCurrency`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,'Currency 1'),(2,'Currency 2'),(3,'Currency 3'),(4,'Currency 4'),(5,'Currency 5'),(6,'Currency 6'),(7,'Currency 7'),(8,'Currency 8'),(9,'Currency 9'),(10,'Currency 10');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distrit`
--

DROP TABLE IF EXISTS `distrit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distrit` (
  `idDistrit` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idDistrit`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distrit`
--

LOCK TABLES `distrit` WRITE;
/*!40000 ALTER TABLE `distrit` DISABLE KEYS */;
INSERT INTO `distrit` VALUES (1,'Distrito 1'),(2,'Distrito 2'),(3,'Distrito 3'),(4,'Distrito 4'),(5,'Distrito 5'),(6,'Distrito 6'),(7,'Distrito 7'),(8,'Distrito 8'),(9,'Distrito 9'),(10,'Distrito 10');
/*!40000 ALTER TABLE `distrit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver` (
  `idDriver` int NOT NULL AUTO_INCREMENT,
  `names` varchar(100) DEFAULT NULL,
  `lastNames` varchar(100) DEFAULT NULL,
  `idNumber` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `carPlate` varchar(100) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `color` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idDriver`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'Nombre Conductor 1','Apellido Conductor 1','12345678','123456789','Modelo 1','Marca 1','Plate 1',2020,'Color 1'),(2,'Nombre Conductor 2','Apellido Conductor 2','23456789','234567890','Modelo 2','Marca 2','Plate 2',2021,'Color 2'),(3,'Nombre Conductor 3','Apellido Conductor 3','34567890','345678901','Modelo 3','Marca 3','Plate 3',2022,'Color 3'),(4,'Nombre Conductor 4','Apellido Conductor 4','45678901','456789012','Modelo 4','Marca 4','Plate 4',2023,'Color 4'),(5,'Nombre Conductor 5','Apellido Conductor 5','56789012','567890123','Modelo 5','Marca 5','Plate 5',2024,'Color 5'),(6,'Nombre Conductor 6','Apellido Conductor 6','67890123','678901234','Modelo 6','Marca 6','Plate 6',2025,'Color 6'),(7,'Nombre Conductor 7','Apellido Conductor 7','78901234','789012345','Modelo 7','Marca 7','Plate 7',2026,'Color 7'),(8,'Nombre Conductor 8','Apellido Conductor 8','89012345','890123456','Modelo 8','Marca 8','Plate 8',2027,'Color 8'),(9,'Nombre Conductor 9','Apellido Conductor 9','90123456','901234567','Modelo 9','Marca 9','Plate 9',2028,'Color 9'),(10,'Nombre Conductor 10','Apellido Conductor 10','01234567','012345678','Modelo 10','Marca 10','Plate 10',2029,'Color 10');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `idEmployee` int NOT NULL AUTO_INCREMENT,
  `names` varchar(255) DEFAULT NULL,
  `lastnames` varchar(255) DEFAULT NULL,
  `idNumber` varchar(45) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `idUser` int DEFAULT NULL,
  PRIMARY KEY (`idEmployee`),
  KEY `fk_employee_user_idx` (`idUser`),
  CONSTRAINT `fk_employee_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `navmenu`
--

DROP TABLE IF EXISTS `navmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `navmenu` (
  `idNavMenu` int NOT NULL AUTO_INCREMENT,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idNavMenu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `navmenu`
--

LOCK TABLES `navmenu` WRITE;
/*!40000 ALTER TABLE `navmenu` DISABLE KEYS */;
/*!40000 ALTER TABLE `navmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger` (
  `idPassenger` int NOT NULL AUTO_INCREMENT,
  `names` varchar(100) DEFAULT NULL,
  `lastNames` varchar(100) DEFAULT NULL,
  `idDistrit` int DEFAULT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idPassenger`),
  KEY `fk_passenger_distrit` (`idDistrit`),
  CONSTRAINT `fk_passenger_distrit` FOREIGN KEY (`idDistrit`) REFERENCES `distrit` (`idDistrit`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (1,'Nombre 1','Apellido 1',1,'Direccion 1','123456789'),(2,'Nombre 2','Apellido 2',2,'Direccion 2','234567890'),(3,'Nombre 3','Apellido 3',3,'Direccion 3','345678901'),(4,'Nombre 4','Apellido 4',4,'Direccion 4','456789012'),(5,'Nombre 5','Apellido 5',5,'Direccion 5','567890123'),(6,'Nombre 6','Apellido 6',6,'Direccion 6','678901234'),(7,'Nombre 7','Apellido 7',7,'Direccion 7','789012345'),(8,'Nombre 8','Apellido 8',8,'Direccion 8','890123456'),(9,'Nombre 9','Apellido 9',9,'Direccion 9','901234567'),(10,'Nombre 10','Apellido 10',10,'Direccion 10','012345678');
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passengercompany`
--

DROP TABLE IF EXISTS `passengercompany`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passengercompany` (
  `idPassengerCompany` int NOT NULL AUTO_INCREMENT,
  `idPassenger` int DEFAULT NULL,
  `idCompany` int DEFAULT NULL,
  PRIMARY KEY (`idPassengerCompany`),
  KEY `fk_passengercompany_passenger` (`idPassenger`),
  KEY `fk_passengercompany_company` (`idCompany`),
  CONSTRAINT `fk_passengercompany_company` FOREIGN KEY (`idCompany`) REFERENCES `company` (`idCompany`),
  CONSTRAINT `fk_passengercompany_passenger` FOREIGN KEY (`idPassenger`) REFERENCES `passenger` (`idPassenger`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passengercompany`
--

LOCK TABLES `passengercompany` WRITE;
/*!40000 ALTER TABLE `passengercompany` DISABLE KEYS */;
INSERT INTO `passengercompany` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6),(7,7,7),(8,8,8),(9,9,9),(10,10,10);
/*!40000 ALTER TABLE `passengercompany` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `idUserType` int DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  KEY `fk_user_usertype_idx` (`idUserType`),
  CONSTRAINT `fk_user_usertype` FOREIGN KEY (`idUserType`) REFERENCES `usertype` (`idUserType`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'superAdmin','superAdmin','Disable',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertype` (
  `idUserType` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idUserType`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` VALUES (1,'Adminstrador');
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertypenavmenu`
--

DROP TABLE IF EXISTS `usertypenavmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertypenavmenu` (
  `idUserTypeNavMenu` int NOT NULL AUTO_INCREMENT,
  `idUserType` int DEFAULT NULL,
  `idNavMenu` int DEFAULT NULL,
  PRIMARY KEY (`idUserTypeNavMenu`),
  KEY `fk_usertypenavmenu_usertype_idx` (`idUserType`),
  KEY `fk_usertypenavmenu_navmenu_idx` (`idNavMenu`),
  CONSTRAINT `fk_usertypenavmenu_navmenu` FOREIGN KEY (`idNavMenu`) REFERENCES `navmenu` (`idNavMenu`),
  CONSTRAINT `fk_usertypenavmenu_usertype` FOREIGN KEY (`idUserType`) REFERENCES `usertype` (`idUserType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertypenavmenu`
--

LOCK TABLES `usertypenavmenu` WRITE;
/*!40000 ALTER TABLE `usertypenavmenu` DISABLE KEYS */;
/*!40000 ALTER TABLE `usertypenavmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mtw_project'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-21 21:10:48

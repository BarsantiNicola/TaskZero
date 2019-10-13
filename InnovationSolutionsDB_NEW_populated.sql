-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: innovativesolutionsdb_new
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
-- Table structure for table `assembles`
--

DROP TABLE IF EXISTS `assembles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assembles` (
  `team` int(11) NOT NULL,
  `product` int(11) NOT NULL,
  PRIMARY KEY (`team`,`product`),
  KEY `productCostraintAssembles_idx` (`product`),
  CONSTRAINT `productCostraintAssembles` FOREIGN KEY (`product`) REFERENCES `product` (`IDproduct`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teamCostraintAssembles` FOREIGN KEY (`team`) REFERENCES `team` (`IDteam`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assembles`
--

LOCK TABLES `assembles` WRITE;
/*!40000 ALTER TABLE `assembles` DISABLE KEYS */;
INSERT INTO `assembles` VALUES (1,1),(2,2),(3,3),(4,4),(1,5);
/*!40000 ALTER TABLE `assembles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component` (
  `IDcomponent` int(11) NOT NULL,
  `componentName` varchar(45) NOT NULL,
  `componentDescription` varchar(200) NOT NULL,
  `componentAvailability` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IDcomponent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component`
--

LOCK TABLES `component` WRITE;
/*!40000 ALTER TABLE `component` DISABLE KEYS */;
INSERT INTO `component` VALUES (1,'rubberband','A rubber band',14),(2,'display2d5i','A 2.5\" LCD Display',4),(3,'CortexM3','ARM Low-Power SoC',47),(4,'lock','A Mechanical Lock',9),(5,'touchpad','A Numeric Touchpad',4),(6,'doorbell','A doorbell with a button',12),(7,'display4i','A 4\" LCD Display',8),(8,'CortexM7','ARM SoC with hardware acceleration',6),(9,'lightbulb','A low power light bulb',22),(10,'CortexM1','ARM ultra-low-power SoC',9),(11,'thermostat','A thermostat reading temperature and humidity',15);
/*!40000 ALTER TABLE `component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component_stock`
--

DROP TABLE IF EXISTS `component_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component_stock` (
  `IDcomponent` int(11) NOT NULL AUTO_INCREMENT,
  `componentType` int(11) NOT NULL,
  PRIMARY KEY (`IDcomponent`),
  KEY `componentTypeConstraint_idx` (`componentType`),
  CONSTRAINT `componentTypeConstraint` FOREIGN KEY (`componentType`) REFERENCES `component` (`IDcomponent`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component_stock`
--

LOCK TABLES `component_stock` WRITE;
/*!40000 ALTER TABLE `component_stock` DISABLE KEYS */;
INSERT INTO `component_stock` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(37,2),(38,2),(39,2),(40,2),(41,2),(42,2),(43,2),(44,2),(45,2),(46,2),(47,2),(48,2),(49,2),(50,2),(51,2),(52,2),(53,2),(54,2),(55,2),(56,2),(57,2),(58,2),(59,2),(60,2),(61,2),(62,2),(63,2),(64,3),(65,3),(66,3),(67,3),(68,3),(69,3),(70,3),(71,3),(72,3),(73,3),(74,3),(75,3),(76,3),(77,3),(78,3),(79,3),(80,3),(81,3),(82,3),(83,3),(84,3),(85,3),(86,3),(87,3),(88,3),(89,3),(90,3),(91,3),(92,3),(93,3),(94,3),(95,3),(96,3),(97,3),(98,3),(99,3),(100,3),(101,3),(102,3),(103,3),(104,3),(105,3),(106,3),(107,3),(108,3),(109,3),(110,3),(111,3),(112,3),(113,3),(114,3),(115,3),(116,3),(117,3),(118,3),(119,3),(120,3),(121,3),(122,3),(123,3),(124,3),(125,3),(126,3),(127,3),(128,3),(129,3),(130,3),(131,3),(132,3),(133,3),(134,3),(135,3),(445,3),(446,3),(447,3),(448,3),(449,3),(450,3),(451,3),(452,3),(453,3),(454,3),(455,3),(456,3),(457,3),(458,3),(459,3),(460,3),(461,3),(462,3),(463,3),(464,3),(465,3),(466,3),(467,3),(468,3),(469,3),(470,3),(471,3),(472,3),(136,4),(137,4),(138,4),(139,4),(140,4),(141,4),(142,4),(143,4),(144,4),(145,4),(146,4),(147,4),(148,4),(149,4),(150,4),(151,4),(152,4),(153,4),(154,4),(155,4),(156,4),(157,4),(158,4),(159,4),(160,4),(161,4),(162,4),(163,4),(164,4),(165,4),(166,4),(167,4),(168,4),(169,4),(170,4),(171,4),(172,5),(173,5),(174,5),(175,5),(176,5),(177,5),(178,5),(179,5),(180,5),(181,5),(182,5),(183,5),(184,5),(185,5),(186,5),(187,5),(188,5),(189,5),(190,5),(191,5),(192,5),(193,5),(194,5),(195,5),(196,5),(197,5),(198,5),(199,5),(200,5),(201,5),(202,5),(203,6),(204,6),(205,6),(206,6),(207,6),(208,6),(209,6),(210,6),(211,6),(212,6),(213,6),(214,6),(215,6),(216,6),(217,6),(218,6),(219,6),(220,6),(221,6),(222,6),(223,6),(224,6),(225,6),(226,6),(227,6),(228,6),(229,6),(230,6),(231,6),(232,6),(233,6),(234,6),(235,6),(236,6),(237,6),(238,6),(239,7),(240,7),(241,7),(242,7),(243,7),(244,7),(245,7),(246,7),(247,7),(248,7),(249,7),(250,7),(251,7),(252,7),(253,7),(254,7),(255,7),(256,7),(257,7),(258,7),(259,7),(260,7),(261,7),(262,7),(263,7),(264,7),(265,7),(266,7),(267,7),(268,7),(269,7),(270,7),(473,7),(474,7),(475,7),(476,7),(477,7),(478,7),(479,7),(480,7),(481,7),(482,7),(483,7),(484,7),(485,7),(486,7),(487,7),(488,7),(489,7),(490,7),(491,7),(492,7),(493,7),(494,7),(495,7),(496,7),(497,7),(498,7),(499,7),(500,7),(271,8),(272,8),(273,8),(274,8),(275,8),(276,8),(277,8),(278,8),(279,8),(280,8),(281,8),(282,8),(283,8),(284,8),(285,8),(286,8),(287,8),(288,8),(289,8),(290,8),(291,8),(292,8),(293,8),(294,8),(295,8),(296,8),(297,8),(298,8),(299,8),(300,8),(301,9),(302,9),(303,9),(304,9),(305,9),(306,9),(307,9),(308,9),(309,9),(310,9),(311,9),(312,9),(313,9),(314,9),(315,9),(316,9),(317,9),(318,9),(319,9),(320,9),(321,9),(322,9),(323,9),(324,9),(325,9),(326,9),(327,9),(328,9),(329,9),(330,9),(331,9),(332,9),(333,9),(334,9),(335,9),(336,9),(337,9),(338,9),(339,9),(340,9),(341,9),(342,9),(343,9),(344,9),(345,9),(346,9),(347,9),(348,9),(349,9),(350,9),(351,9),(352,9),(353,9),(354,9),(355,9),(356,9),(357,9),(358,10),(359,10),(360,10),(361,10),(362,10),(363,10),(364,10),(365,10),(366,10),(367,10),(368,10),(369,10),(370,10),(371,10),(372,10),(373,10),(374,10),(375,10),(376,10),(377,10),(378,10),(379,10),(380,10),(381,10),(382,10),(383,10),(384,10),(385,10),(386,10),(387,10),(388,10),(389,10),(390,10),(391,10),(392,10),(393,10),(394,10),(395,10),(396,10),(397,10),(398,10),(399,10),(400,10),(401,10),(402,10),(403,11),(404,11),(405,11),(406,11),(407,11),(408,11),(409,11),(410,11),(411,11),(412,11),(413,11),(414,11),(415,11),(416,11),(417,11),(418,11),(419,11),(420,11),(421,11),(422,11),(423,11),(424,11),(425,11),(426,11),(427,11),(428,11),(429,11),(430,11),(431,11),(432,11),(433,11),(434,11),(435,11),(436,11),(437,11),(438,11),(439,11),(440,11),(441,11),(442,11),(443,11),(444,11);
/*!40000 ALTER TABLE `component_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composes`
--

DROP TABLE IF EXISTS `composes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composes` (
  `component` int(11) NOT NULL,
  `product` int(11) NOT NULL,
  KEY `productConstraint_idx` (`product`),
  KEY `componentConstraint_idx` (`component`),
  CONSTRAINT `componentConstraint` FOREIGN KEY (`component`) REFERENCES `component` (`IDcomponent`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productConstraint` FOREIGN KEY (`product`) REFERENCES `product` (`IDproduct`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composes`
--

LOCK TABLES `composes` WRITE;
/*!40000 ALTER TABLE `composes` DISABLE KEYS */;
INSERT INTO `composes` VALUES (1,1),(4,2),(6,3),(9,4),(3,5),(2,1),(3,2),(7,3),(10,4),(7,5),(3,1),(5,2),(8,3),(11,5);
/*!40000 ALTER TABLE `composes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `IDcustomer` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`IDcustomer`),
  CONSTRAINT `customerCostraintUser` FOREIGN KEY (`IDcustomer`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('adri','Boston'),('amy','New York'),('anto83','San Francisco'),('betty','Dallas'),('brad','New Orleans'),('Dani','Greenville'),('david91','Phoenix'),('Dia','Florence'),('donny','Douglas'),('eth','Mesa'),('Gabriel','Pine Bluff'),('imanne','Beverly Hills'),('james96','New York'),('laura','Hollywood'),('lisa','Boston'),('lola','Washington'),('magicBob','Richmond'),('Marianne','San Jose'),('matty','San Francisco'),('PaulPaul','Manchester'),('Philip','Miami'),('ralph68','Chicago'),('Sarah','New York'),('soJenny','Boston'),('timJ','Washington'),('Vince','Panama City');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `IDemployee` varchar(45) NOT NULL,
  `salary` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  `team` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDemployee`),
  KEY `teamCostraint_idx` (`team`),
  CONSTRAINT `teamCostraint` FOREIGN KEY (`team`) REFERENCES `team` (`IDteam`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usernameCostraint` FOREIGN KEY (`IDemployee`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('admin',10000,'Administrator',NULL),('adrian',3000,'Engineer',1),('agatha',1200,'Janitor',NULL),('bob',1800,'Technician',1),('christopher',1800,'Technician',4),('jacob',1800,'Technician',1),('james',3000,'Engineer',2),('john',20000,'CEO',NULL),('laura',3000,'Engineer',3),('lorry',1800,'Technician',1),('madeline',1800,'Technician',NULL),('maria',1800,'Technician',2),('matt',1800,'Technician',2),('michael',1800,'Technician',3),('randi',1200,'Janitor',NULL),('richard',15000,'CTO',NULL),('rob',3000,'Engineer',4),('roy',1800,'Technician',2),('ruth',1800,'Technician',3),('seli',1800,'Technician',4),('susy',1800,'Technician',4);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `customer` varchar(45) NOT NULL,
  `product` int(11) NOT NULL,
  `purchaseDate` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`customer`,`product`),
  KEY `productCustomer_idx` (`product`),
  CONSTRAINT `customerCostraint` FOREIGN KEY (`customer`) REFERENCES `customer` (`IDcustomer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productCustomer` FOREIGN KEY (`product`) REFERENCES `product_stock` (`IDproduct`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('adri',76,'2019-09-08 11:12:00','delivered'),('adri',111,'2019-09-11 17:54:00','delivered'),('amy',4,'2019-09-06 11:12:00','delivered'),('amy',25,'2019-09-11 18:11:00','delivered'),('amy',112,'2019-09-13 10:48:00','delivered'),('anto83',51,'2019-09-07 12:27:00','delivered'),('anto83',77,'2019-09-10 14:15:00','delivered'),('anto83',78,'2019-09-14 22:15:00','delivered'),('betty',1,'2019-09-05 15:18:00','delivered'),('betty',2,'2019-09-08 12:00:00','delivered'),('betty',23,'2019-09-09 09:15:00','delivered'),('eth',50,'2019-09-06 18:15:00','delivered'),('eth',74,'2019-09-07 02:47:00','delivered'),('eth',75,'2019-09-08 07:12:00','delivered'),('eth',110,'2019-09-14 19:21:00','delivered'),('Gabriel',3,'2019-09-07 11:05:00','delivered'),('Gabriel',24,'2019-09-08 14:56:00','delivered'),('imanne',5,'2019-09-15 13:29:00','delivered'),('imanne',26,'2019-09-20 15:14:00','delivered'),('imanne',52,'2019-09-21 19:51:00','delivered'),('imanne',113,'2019-09-18 23:14:00','delivered'),('james96',6,'2019-09-24 17:12:00','delivered'),('james96',53,'2019-09-17 08:15:00','delivered'),('james96',79,'2019-09-22 13:22:00','delivered'),('james96',114,'2019-09-22 15:34:00','delivered'),('laura',7,'2019-10-12 23:55:00','received'),('laura',27,'2019-10-01 17:12:00','shipping'),('laura',54,'2019-09-28 07:52:00','delivered'),('laura',80,'2019-10-05 22:14:00','shipping'),('laura',115,'2019-09-30 18:25:00','delivered'),('lisa',28,'2019-10-01 11:14:00','delivered'),('lisa',55,'2019-10-03 21:32:00','delivered'),('lisa',116,'2019-10-10 20:19:00','received'),('lola',8,'2019-10-03 05:22:00','delivered'),('lola',29,'2019-10-12 08:23:00','received'),('lola',56,'2019-10-12 08:24:00','received'),('lola',81,'2019-10-05 19:47:00','shipping'),('Marianne',9,'2019-10-04 03:32:00','delivered'),('Marianne',82,'2019-10-09 11:36:00','shipping'),('Marianne',117,'2019-10-08 16:41:00','shipping'),('matty',10,'2019-10-08 11:32:00','shipping'),('matty',57,'2019-10-09 18:15:00','shipping'),('matty',83,'2019-10-06 15:25:00','delivered'),('Philip',11,'2019-10-08 22:23:00','shipping'),('Philip',30,'2019-10-11 08:45:00','received'),('Philip',58,'2019-10-08 23:42:00','shipping'),('Philip',118,'2019-10-11 06:37:00','received'),('ralph68',12,'2019-10-08 15:24:00','delivered'),('ralph68',59,'2019-10-09 17:55:00','shipping'),('ralph68',84,'2019-10-11 14:15:00','received'),('Sarah',59,'2019-10-12 10:15:00','received'),('Sarah',119,'2019-10-09 12:11:00','shipping'),('timJ',60,'2019-10-05 23:41:00','delivered'),('timJ',85,'2019-10-07 12:25:00','shipping'),('timJ',120,'2019-10-12 14:15:00','received'),('Vince',13,'2019-10-06 19:37:00','delivered'),('Vince',14,'2019-10-06 20:21:00','delivered'),('Vince',61,'2019-10-09 05:42:00','shipping'),('Vince',86,'2019-10-12 17:42:00','received');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `IDproduct` int(11) NOT NULL,
  `productName` varchar(45) NOT NULL,
  `productPrice` int(11) NOT NULL,
  `productDescription` varchar(200) NOT NULL,
  `productAvailability` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IDproduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'ISmartBand',20,'A smartband with fitness and medical tracking functions that can be connected via bluetooth to a smartphone',8),(2,'ISmartLock',45,'A lock that can be installed into a door to allow it to be opened remotely or via a touchpad',19),(3,'ISmartVideoBell',120,'A smart doorbell with an integrated display that initiates a video call when someone rings at the door',11),(4,'ISmartLight',10,'A light bulb allowing remote control via Bluetooth',23),(5,'ISmartThermo',55,'A smart thermostat that allows remote readings and programming',17);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_stock`
--

DROP TABLE IF EXISTS `product_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_stock` (
  `IDproduct` int(11) NOT NULL AUTO_INCREMENT,
  `productType` int(11) NOT NULL,
  PRIMARY KEY (`IDproduct`),
  KEY `productTypeConstraint` (`productType`),
  CONSTRAINT `productTypeConstraint` FOREIGN KEY (`productType`) REFERENCES `product` (`IDproduct`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_stock`
--

LOCK TABLES `product_stock` WRITE;
/*!40000 ALTER TABLE `product_stock` DISABLE KEYS */;
INSERT INTO `product_stock` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,2),(24,2),(25,2),(26,2),(27,2),(28,2),(29,2),(30,2),(31,2),(32,2),(33,2),(34,2),(35,2),(36,2),(37,2),(38,2),(39,2),(40,2),(41,2),(42,2),(43,2),(44,2),(45,2),(46,2),(47,2),(48,2),(49,2),(50,3),(51,3),(52,3),(53,3),(54,3),(55,3),(56,3),(57,3),(58,3),(59,3),(60,3),(61,3),(62,3),(63,3),(64,3),(65,3),(66,3),(67,3),(68,3),(69,3),(70,3),(71,3),(72,3),(73,3),(74,4),(75,4),(76,4),(77,4),(78,4),(79,4),(80,4),(81,4),(82,4),(83,4),(84,4),(85,4),(86,4),(87,4),(88,4),(89,4),(90,4),(91,4),(92,4),(93,4),(94,4),(95,4),(96,4),(97,4),(98,4),(99,4),(100,4),(101,4),(102,4),(103,4),(104,4),(105,4),(106,4),(107,4),(108,4),(109,4),(110,5),(111,5),(112,5),(113,5),(114,5),(115,5),(116,5),(117,5),(118,5),(119,5),(120,5),(121,5),(122,5),(123,5),(124,5),(125,5),(126,5),(127,5),(128,5),(129,5),(130,5),(131,5),(132,5),(133,5),(134,5),(135,5),(136,5),(137,5);
/*!40000 ALTER TABLE `product_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `IDsupplier` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(45) NOT NULL,
  `supplierMail` varchar(45) NOT NULL,
  PRIMARY KEY (`IDsupplier`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'HS Electronics','hselectronics@example.com'),(2,'AM Semiconductors','amsemiconductors@example.com'),(3,'Forlink Embedded','forlinkembedded@example.com'),(4,'Sharp Corporation','sharpcorporation@example.com'),(5,'AU Optronics','auoptronics@example.com'),(6,'Alliance Rubber Company','alliancerubbercompany@example.com'),(7,'Moore Industrial Hardware','mooreindustrialhardware@example.com'),(8,'U.S. Lock','uslock@example.com'),(9,'Synaptic Corporation','synapticcorporation@example.com'),(10,'SYG Global Technologies','sygglobaltechnologies@example.com'),(11,'Zamel SP','zamelsp@example.com'),(12,'Bisco Industries','biscoindustries@example.com'),(13,'EGL Company','eglcompany@example.com'),(14,'Selco Corporation','selcocorporation@example.com'),(15,'Thermik Corporation','termikcorporation@example.com');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplies`
--

DROP TABLE IF EXISTS `supplies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplies` (
  `component` int(11) NOT NULL,
  `supplier` int(11) NOT NULL,
  `purchaseDate` datetime NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`component`,`supplier`),
  KEY `supplierCostraint_idx` (`supplier`),
  CONSTRAINT `componentCostraint2` FOREIGN KEY (`component`) REFERENCES `component_stock` (`IDcomponent`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `supplierCostraint` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`IDsupplier`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplies`
--

LOCK TABLES `supplies` WRITE;
/*!40000 ALTER TABLE `supplies` DISABLE KEYS */;
INSERT INTO `supplies` VALUES (1,6,'2019-08-15 18:15:00',3),(2,6,'2019-08-15 18:15:00',3),(3,6,'2019-08-15 18:15:00',3),(4,6,'2019-08-15 18:15:00',3),(5,6,'2019-08-15 18:15:00',3),(6,6,'2019-08-15 18:15:00',3),(7,6,'2019-08-15 18:15:00',3),(8,6,'2019-08-15 18:15:00',3),(9,6,'2019-08-15 18:15:00',3),(10,6,'2019-08-15 18:15:00',3),(11,6,'2019-08-15 18:15:00',3),(12,6,'2019-08-15 18:15:00',3),(13,6,'2019-08-15 18:15:00',3),(14,6,'2019-08-15 18:15:00',3),(15,6,'2019-08-15 18:15:00',3),(16,6,'2019-08-15 18:15:00',3),(17,6,'2019-08-15 18:15:00',3),(18,6,'2019-08-15 18:15:00',3),(19,6,'2019-08-15 18:15:00',3),(20,6,'2019-08-15 18:15:00',3),(21,6,'2019-08-15 18:15:00',3),(22,6,'2019-08-15 18:15:00',3),(23,6,'2019-08-15 18:15:00',3),(24,6,'2019-08-15 18:15:00',3),(25,7,'2019-08-22 10:17:00',2),(26,7,'2019-08-22 10:17:00',2),(27,7,'2019-08-22 10:17:00',2),(28,7,'2019-08-22 10:17:00',2),(29,7,'2019-08-22 10:17:00',2),(30,7,'2019-08-22 10:17:00',2),(31,7,'2019-08-22 10:17:00',2),(32,7,'2019-08-22 10:17:00',2),(33,7,'2019-08-22 10:17:00',2),(34,7,'2019-08-22 10:17:00',2),(35,7,'2019-08-22 10:17:00',2),(36,7,'2019-08-22 10:17:00',2),(37,4,'2019-08-17 12:25:00',4),(38,4,'2019-08-17 12:25:00',4),(39,4,'2019-08-17 12:25:00',4),(40,4,'2019-08-17 12:25:00',4),(41,4,'2019-08-17 12:25:00',4),(42,4,'2019-08-17 12:25:00',4),(43,4,'2019-08-17 12:25:00',4),(44,4,'2019-08-17 12:25:00',4),(45,4,'2019-08-17 12:25:00',4),(46,4,'2019-08-17 12:25:00',4),(47,4,'2019-08-17 12:25:00',4),(48,4,'2019-08-17 12:25:00',4),(49,5,'2019-08-25 17:12:00',6),(50,5,'2019-08-25 17:12:00',6),(51,5,'2019-08-25 17:12:00',6),(52,5,'2019-08-25 17:12:00',6),(53,5,'2019-08-25 17:12:00',6),(54,5,'2019-08-25 17:12:00',6),(55,5,'2019-08-25 17:12:00',6),(56,5,'2019-08-25 17:12:00',6),(57,5,'2019-08-25 17:12:00',6),(58,5,'2019-08-25 17:12:00',6),(59,5,'2019-08-25 17:12:00',6),(60,5,'2019-08-25 17:12:00',6),(61,5,'2019-08-25 17:12:00',6),(62,5,'2019-08-25 17:12:00',6),(63,5,'2019-08-25 17:12:00',6),(64,1,'2019-08-19 09:47:00',4),(65,1,'2019-08-19 09:47:00',4),(66,1,'2019-08-19 09:47:00',4),(67,1,'2019-08-19 09:47:00',4),(68,1,'2019-08-19 09:47:00',4),(69,1,'2019-08-19 09:47:00',4),(70,1,'2019-08-19 09:47:00',4),(71,1,'2019-08-19 09:47:00',4),(72,1,'2019-08-19 09:47:00',4),(73,1,'2019-08-19 09:47:00',4),(74,1,'2019-08-19 09:47:00',4),(75,1,'2019-08-19 09:47:00',4),(76,1,'2019-08-19 09:47:00',4),(77,1,'2019-08-19 09:47:00',4),(78,1,'2019-08-19 09:47:00',4),(79,1,'2019-08-19 09:47:00',4),(80,1,'2019-08-19 09:47:00',4),(81,1,'2019-08-19 09:47:00',4),(82,1,'2019-08-19 09:47:00',4),(83,1,'2019-08-19 09:47:00',4),(84,1,'2019-08-19 09:47:00',4),(85,1,'2019-08-19 09:47:00',4),(86,1,'2019-08-19 09:47:00',4),(87,1,'2019-08-19 09:47:00',4),(88,1,'2019-08-19 09:47:00',4),(89,1,'2019-08-19 09:47:00',4),(90,1,'2019-08-19 09:47:00',4),(91,1,'2019-08-19 09:47:00',4),(92,1,'2019-08-19 09:47:00',4),(93,1,'2019-08-19 09:47:00',4),(94,1,'2019-08-19 09:47:00',4),(95,1,'2019-08-19 09:47:00',4),(96,1,'2019-08-19 09:47:00',4),(97,1,'2019-08-19 09:47:00',4),(98,1,'2019-08-19 09:47:00',4),(99,1,'2019-08-19 09:47:00',4),(100,1,'2019-08-19 09:47:00',4),(101,1,'2019-08-19 09:47:00',4),(102,1,'2019-08-19 09:47:00',4),(103,1,'2019-08-19 09:47:00',4),(104,1,'2019-08-19 09:47:00',4),(105,2,'2019-08-21 14:45:00',3),(106,2,'2019-08-21 14:45:00',3),(107,2,'2019-08-21 14:45:00',3),(108,2,'2019-08-21 14:45:00',3),(109,2,'2019-08-21 14:45:00',3),(110,2,'2019-08-21 14:45:00',3),(111,2,'2019-08-21 14:45:00',3),(112,2,'2019-08-21 14:45:00',3),(113,2,'2019-08-21 14:45:00',3),(114,2,'2019-08-21 14:45:00',3),(115,2,'2019-08-21 14:45:00',3),(116,2,'2019-08-21 14:45:00',3),(117,2,'2019-08-21 14:45:00',3),(118,2,'2019-08-21 14:45:00',3),(119,2,'2019-08-21 14:45:00',3),(120,2,'2019-08-21 14:45:00',3),(121,2,'2019-08-21 14:45:00',3),(122,3,'2019-08-22 09:12:00',4),(123,3,'2019-08-22 09:12:00',4),(124,3,'2019-08-22 09:12:00',4),(125,3,'2019-08-22 09:12:00',4),(126,3,'2019-08-22 09:12:00',4),(127,3,'2019-08-22 09:12:00',4),(128,3,'2019-08-22 09:12:00',4),(129,3,'2019-08-22 09:12:00',4),(130,3,'2019-08-22 09:12:00',4),(131,3,'2019-08-22 09:12:00',4),(132,3,'2019-08-22 09:12:00',4),(133,3,'2019-08-22 09:12:00',4),(134,3,'2019-08-22 09:12:00',4),(135,3,'2019-08-22 09:12:00',4),(136,7,'2019-08-19 08:20:00',3),(137,7,'2019-08-19 08:20:00',3),(138,7,'2019-08-19 08:20:00',3),(139,7,'2019-08-19 08:20:00',3),(140,7,'2019-08-19 08:20:00',3),(141,7,'2019-08-19 08:20:00',3),(142,7,'2019-08-19 08:20:00',3),(143,7,'2019-08-19 08:20:00',3),(144,7,'2019-08-19 08:20:00',3),(145,7,'2019-08-19 08:20:00',3),(146,7,'2019-08-19 08:20:00',3),(147,7,'2019-08-19 08:20:00',3),(148,7,'2019-08-19 08:20:00',3),(149,7,'2019-08-19 08:20:00',3),(150,7,'2019-08-19 08:20:00',3),(151,7,'2019-08-19 08:20:00',3),(152,7,'2019-08-19 08:20:00',3),(153,7,'2019-08-19 08:20:00',3),(154,7,'2019-08-19 08:20:00',3),(155,8,'2019-08-20 09:47:00',2),(156,8,'2019-08-20 09:47:00',2),(157,8,'2019-08-20 09:47:00',2),(158,8,'2019-08-20 09:47:00',2),(159,8,'2019-08-20 09:47:00',2),(160,8,'2019-08-20 09:47:00',2),(161,8,'2019-08-20 09:47:00',2),(162,8,'2019-08-20 09:47:00',2),(163,8,'2019-08-20 09:47:00',2),(164,8,'2019-08-20 09:47:00',2),(165,8,'2019-08-20 09:47:00',2),(166,8,'2019-08-20 09:47:00',2),(167,8,'2019-08-20 09:47:00',2),(168,8,'2019-08-20 09:47:00',2),(169,8,'2019-08-20 09:47:00',2),(170,8,'2019-08-20 09:47:00',2),(171,8,'2019-08-20 09:47:00',2),(172,9,'2019-08-17 18:45:00',3),(173,9,'2019-08-17 18:45:00',3),(174,9,'2019-08-17 18:45:00',3),(175,9,'2019-08-17 18:45:00',3),(176,9,'2019-08-17 18:45:00',3),(177,9,'2019-08-17 18:45:00',3),(178,9,'2019-08-17 18:45:00',3),(179,9,'2019-08-17 18:45:00',3),(180,9,'2019-08-17 18:45:00',3),(181,9,'2019-08-17 18:45:00',3),(182,9,'2019-08-17 18:45:00',3),(183,9,'2019-08-17 18:45:00',3),(184,9,'2019-08-17 18:45:00',3),(185,9,'2019-08-17 18:45:00',3),(186,9,'2019-08-17 18:45:00',3),(187,9,'2019-08-17 18:45:00',3),(188,9,'2019-08-17 18:45:00',3),(189,9,'2019-08-17 18:45:00',3),(190,9,'2019-08-17 18:45:00',3),(191,9,'2019-08-17 18:45:00',3),(192,9,'2019-08-17 18:45:00',3),(193,10,'2019-08-25 11:31:00',4),(194,10,'2019-08-25 11:31:00',4),(195,10,'2019-08-25 11:31:00',4),(196,10,'2019-08-25 11:31:00',4),(197,10,'2019-08-25 11:31:00',4),(198,10,'2019-08-25 11:31:00',4),(199,10,'2019-08-25 11:31:00',4),(200,10,'2019-08-25 11:31:00',4),(201,10,'2019-08-25 11:31:00',4),(202,10,'2019-08-25 11:31:00',4),(203,7,'2019-08-15 09:01:00',3),(204,7,'2019-08-15 09:01:00',3),(205,7,'2019-08-15 09:01:00',3),(206,7,'2019-08-15 09:01:00',3),(207,7,'2019-08-15 09:01:00',3),(208,7,'2019-08-15 09:01:00',3),(209,7,'2019-08-15 09:01:00',3),(210,7,'2019-08-15 09:01:00',3),(211,7,'2019-08-15 09:01:00',3),(212,7,'2019-08-15 09:01:00',3),(213,7,'2019-08-15 09:01:00',3),(214,7,'2019-08-15 09:01:00',3),(215,7,'2019-08-15 09:01:00',3),(216,7,'2019-08-15 09:01:00',3),(217,7,'2019-08-15 09:01:00',3),(218,7,'2019-08-15 09:01:00',3),(219,10,'2019-08-20 08:49:00',2),(220,10,'2019-08-20 08:49:00',2),(221,10,'2019-08-20 08:49:00',2),(222,10,'2019-08-20 08:49:00',2),(223,10,'2019-08-20 08:49:00',2),(224,10,'2019-08-20 08:49:00',2),(225,10,'2019-08-20 08:49:00',2),(226,10,'2019-08-20 08:49:00',2),(227,10,'2019-08-20 08:49:00',2),(228,10,'2019-08-20 08:49:00',2),(229,10,'2019-08-20 08:49:00',2),(230,10,'2019-08-20 08:49:00',2),(231,10,'2019-08-20 08:49:00',2),(232,10,'2019-08-20 08:49:00',2),(233,10,'2019-08-20 08:49:00',2),(234,10,'2019-08-20 08:49:00',2),(235,10,'2019-08-20 08:49:00',2),(236,10,'2019-08-20 08:49:00',2),(237,10,'2019-08-20 08:49:00',2),(238,10,'2019-08-20 08:49:00',2),(239,4,'2019-08-17 12:25:00',7),(240,4,'2019-08-17 12:25:00',7),(241,4,'2019-08-17 12:25:00',7),(242,4,'2019-08-17 12:25:00',7),(243,4,'2019-08-17 12:25:00',7),(244,4,'2019-08-17 12:25:00',7),(245,4,'2019-08-17 12:25:00',7),(246,4,'2019-08-17 12:25:00',7),(247,4,'2019-08-17 12:25:00',7),(248,4,'2019-08-17 12:25:00',7),(249,4,'2019-08-17 12:25:00',7),(250,4,'2019-08-17 12:25:00',7),(251,4,'2019-08-17 12:25:00',7),(252,4,'2019-08-17 12:25:00',7),(253,4,'2019-08-17 12:25:00',7),(254,4,'2019-08-17 12:25:00',7),(255,4,'2019-08-17 12:25:00',7),(256,4,'2019-08-17 12:25:00',7),(257,4,'2019-08-17 12:25:00',7),(258,5,'2019-08-29 15:17:00',9),(259,5,'2019-08-29 15:17:00',9),(260,5,'2019-08-29 15:17:00',9),(261,5,'2019-08-29 15:17:00',9),(262,5,'2019-08-29 15:17:00',9),(263,5,'2019-08-29 15:17:00',9),(264,5,'2019-08-29 15:17:00',9),(265,5,'2019-08-29 15:17:00',9),(266,5,'2019-08-29 15:17:00',9),(267,5,'2019-08-29 15:17:00',9),(268,5,'2019-08-29 15:17:00',9),(269,5,'2019-08-29 15:17:00',9),(270,5,'2019-08-29 15:17:00',9),(271,1,'2019-08-19 09:47:00',6),(272,1,'2019-08-19 09:47:00',6),(273,1,'2019-08-19 09:47:00',6),(274,1,'2019-08-19 09:47:00',6),(275,1,'2019-08-19 09:47:00',6),(276,1,'2019-08-19 09:47:00',6),(277,1,'2019-08-19 09:47:00',6),(278,1,'2019-08-19 09:47:00',6),(279,1,'2019-08-19 09:47:00',6),(280,1,'2019-08-19 09:47:00',6),(281,3,'2019-08-22 09:12:00',7),(282,3,'2019-08-22 09:12:00',7),(283,3,'2019-08-22 09:12:00',7),(284,3,'2019-08-22 09:12:00',7),(285,3,'2019-08-22 09:12:00',7),(286,3,'2019-08-22 09:12:00',7),(287,3,'2019-08-22 09:12:00',7),(288,3,'2019-08-22 09:12:00',7),(289,3,'2019-08-22 09:12:00',7),(290,3,'2019-08-22 09:12:00',7),(291,3,'2019-08-22 09:12:00',7),(292,3,'2019-08-22 09:12:00',7),(293,3,'2019-08-22 09:12:00',7),(294,3,'2019-08-22 09:12:00',7),(295,3,'2019-08-22 09:12:00',7),(296,3,'2019-08-22 09:12:00',7),(297,3,'2019-08-22 09:12:00',7),(298,3,'2019-08-22 09:12:00',7),(299,3,'2019-08-22 09:12:00',7),(300,3,'2019-08-22 09:12:00',7),(302,12,'2019-08-15 17:42:00',2),(303,12,'2019-08-15 17:42:00',2),(304,12,'2019-08-15 17:42:00',2),(305,12,'2019-08-15 17:42:00',2),(306,12,'2019-08-15 17:42:00',2),(307,12,'2019-08-15 17:42:00',2),(308,12,'2019-08-15 17:42:00',2),(309,12,'2019-08-15 17:42:00',2),(310,12,'2019-08-15 17:42:00',2),(311,12,'2019-08-15 17:42:00',2),(312,12,'2019-08-15 17:42:00',2),(313,12,'2019-08-15 17:42:00',2),(314,12,'2019-08-15 17:42:00',2),(315,12,'2019-08-15 17:42:00',2),(316,12,'2019-08-15 17:42:00',2),(317,12,'2019-08-15 17:42:00',2),(318,12,'2019-08-15 17:42:00',2),(319,12,'2019-08-15 17:42:00',2),(320,12,'2019-08-15 17:42:00',2),(321,12,'2019-08-15 17:42:00',2),(322,12,'2019-08-15 17:42:00',2),(323,12,'2019-08-15 17:42:00',2),(324,12,'2019-08-15 17:42:00',2),(325,13,'2019-08-22 14:31:00',1),(326,13,'2019-08-22 14:31:00',1),(327,13,'2019-08-22 14:31:00',1),(328,13,'2019-08-22 14:31:00',1),(329,13,'2019-08-22 14:31:00',1),(330,13,'2019-08-22 14:31:00',1),(331,13,'2019-08-22 14:31:00',1),(332,13,'2019-08-22 14:31:00',1),(333,13,'2019-08-22 14:31:00',1),(334,13,'2019-08-22 14:31:00',1),(335,13,'2019-08-22 14:31:00',1),(336,13,'2019-08-22 14:31:00',1),(337,13,'2019-08-22 14:31:00',1),(338,13,'2019-08-22 14:31:00',1),(339,13,'2019-08-22 14:31:00',1),(340,13,'2019-08-22 14:31:00',1),(341,13,'2019-08-22 14:31:00',1),(342,13,'2019-08-22 14:31:00',1),(343,13,'2019-08-22 14:31:00',1),(344,13,'2019-08-22 14:31:00',1),(345,13,'2019-08-22 14:31:00',1),(346,13,'2019-08-22 14:31:00',1),(347,13,'2019-08-22 14:31:00',1),(348,13,'2019-08-22 14:31:00',1),(349,13,'2019-08-22 14:31:00',1),(350,13,'2019-08-22 14:31:00',1),(351,13,'2019-08-22 14:31:00',1),(352,13,'2019-08-22 14:31:00',1),(353,13,'2019-08-22 14:31:00',1),(354,13,'2019-08-22 14:31:00',1),(355,13,'2019-08-22 14:31:00',1),(356,13,'2019-08-22 14:31:00',1),(357,13,'2019-08-22 14:31:00',1),(359,2,'2019-08-17 11:27:00',4),(360,2,'2019-08-17 11:27:00',4),(361,2,'2019-08-17 11:27:00',4),(362,2,'2019-08-17 11:27:00',4),(363,2,'2019-08-17 11:27:00',4),(364,2,'2019-08-17 11:27:00',4),(365,2,'2019-08-17 11:27:00',4),(366,2,'2019-08-17 11:27:00',4),(367,2,'2019-08-17 11:27:00',4),(368,2,'2019-08-17 11:27:00',4),(369,2,'2019-08-17 11:27:00',4),(370,2,'2019-08-17 11:27:00',4),(371,2,'2019-08-17 11:27:00',4),(372,2,'2019-08-17 11:27:00',4),(373,2,'2019-08-17 11:27:00',4),(374,2,'2019-08-17 11:27:00',4),(375,2,'2019-08-17 11:27:00',4),(376,3,'2019-08-22 18:25:00',5),(377,3,'2019-08-22 18:25:00',5),(378,3,'2019-08-22 18:25:00',5),(379,3,'2019-08-22 18:25:00',5),(380,3,'2019-08-22 18:25:00',5),(381,3,'2019-08-22 18:25:00',5),(382,3,'2019-08-22 18:25:00',5),(383,3,'2019-08-22 18:25:00',5),(384,3,'2019-08-22 18:25:00',5),(385,3,'2019-08-22 18:25:00',5),(386,3,'2019-08-22 18:25:00',5),(387,3,'2019-08-22 18:25:00',5),(388,3,'2019-08-22 18:25:00',5),(389,3,'2019-08-22 18:25:00',5),(390,3,'2019-08-22 18:25:00',5),(391,3,'2019-08-22 18:25:00',5),(392,3,'2019-08-22 18:25:00',5),(393,3,'2019-08-22 18:25:00',5),(394,3,'2019-08-22 18:25:00',5),(395,3,'2019-08-22 18:25:00',5),(396,3,'2019-08-22 18:25:00',5),(397,3,'2019-08-22 18:25:00',5),(398,3,'2019-08-22 18:25:00',5),(399,3,'2019-08-22 18:25:00',5),(400,3,'2019-08-22 18:25:00',5),(401,3,'2019-08-22 18:25:00',5),(402,3,'2019-08-22 18:25:00',5),(403,3,'2019-08-22 18:25:00',5),(404,14,'2019-08-17 10:16:00',5),(405,14,'2019-08-17 10:16:00',5),(406,14,'2019-08-17 10:16:00',5),(407,14,'2019-08-17 10:16:00',5),(408,14,'2019-08-17 10:16:00',5),(409,14,'2019-08-17 10:16:00',5),(410,14,'2019-08-17 10:16:00',5),(411,14,'2019-08-17 10:16:00',5),(412,14,'2019-08-17 10:16:00',5),(413,14,'2019-08-17 10:16:00',5),(414,14,'2019-08-17 10:16:00',5),(415,14,'2019-08-17 10:16:00',5),(416,14,'2019-08-17 10:16:00',5),(417,14,'2019-08-17 10:16:00',5),(418,14,'2019-08-17 10:16:00',5),(419,14,'2019-08-17 10:16:00',5),(420,14,'2019-08-17 10:16:00',5),(421,15,'2019-08-17 15:22:00',6),(422,15,'2019-08-17 15:22:00',6),(423,15,'2019-08-17 15:22:00',6),(424,15,'2019-08-17 15:22:00',6),(425,15,'2019-08-17 15:22:00',6),(426,15,'2019-08-17 15:22:00',6),(427,15,'2019-08-17 15:22:00',6),(428,15,'2019-08-17 15:22:00',6),(429,15,'2019-08-17 15:22:00',6),(430,15,'2019-08-17 15:22:00',6),(431,15,'2019-08-17 15:22:00',6),(432,15,'2019-08-17 15:22:00',6),(433,15,'2019-08-17 15:22:00',6),(434,15,'2019-08-17 15:22:00',6),(435,15,'2019-08-17 15:22:00',6),(436,15,'2019-08-17 15:22:00',6),(437,15,'2019-08-17 15:22:00',6),(438,15,'2019-08-17 15:22:00',6),(439,15,'2019-08-17 15:22:00',6),(440,15,'2019-08-17 15:22:00',6),(441,15,'2019-08-17 15:22:00',6),(442,15,'2019-08-17 15:22:00',6),(443,15,'2019-08-17 15:22:00',6),(444,15,'2019-08-17 15:22:00',6),(445,15,'2019-08-17 15:22:00',6),(446,2,'2019-08-25 18:42:00',3),(447,2,'2019-08-25 18:42:00',3),(448,2,'2019-08-25 18:42:00',3),(449,2,'2019-08-25 18:42:00',3),(450,2,'2019-08-25 18:42:00',3),(451,2,'2019-08-25 18:42:00',3),(452,2,'2019-08-25 18:42:00',3),(453,2,'2019-08-25 18:42:00',3),(454,2,'2019-08-25 18:42:00',3),(455,2,'2019-08-25 18:42:00',3),(456,2,'2019-08-25 18:42:00',3),(457,2,'2019-08-25 18:42:00',3),(458,2,'2019-08-25 18:42:00',3),(459,2,'2019-08-25 18:42:00',3),(460,2,'2019-08-25 18:42:00',3),(461,2,'2019-08-25 18:42:00',3),(462,2,'2019-08-25 18:42:00',3),(463,2,'2019-08-25 18:42:00',3),(464,2,'2019-08-25 18:42:00',3),(465,2,'2019-08-25 18:42:00',3),(466,2,'2019-08-25 18:42:00',3),(467,3,'2019-08-25 18:58:00',4),(468,3,'2019-08-25 18:58:00',4),(469,3,'2019-08-25 18:58:00',4),(470,3,'2019-08-25 18:58:00',4),(471,3,'2019-08-25 18:58:00',4),(472,3,'2019-08-25 18:58:00',4),(473,3,'2019-08-25 18:58:00',4),(474,4,'2019-08-24 09:28:00',7),(475,4,'2019-08-24 09:28:00',7),(476,4,'2019-08-24 09:28:00',7),(477,4,'2019-08-24 09:28:00',7),(478,4,'2019-08-24 09:28:00',7),(479,4,'2019-08-24 09:28:00',7),(480,4,'2019-08-24 09:28:00',7),(481,4,'2019-08-24 09:28:00',7),(482,4,'2019-08-24 09:28:00',7),(483,4,'2019-08-24 09:28:00',7),(484,4,'2019-08-24 09:28:00',7),(485,4,'2019-08-24 09:28:00',7),(486,4,'2019-08-24 09:28:00',7),(487,4,'2019-08-24 09:28:00',7),(488,4,'2019-08-24 09:28:00',7),(489,4,'2019-08-24 09:28:00',7),(490,4,'2019-08-24 09:28:00',7),(491,4,'2019-08-24 09:28:00',7),(492,5,'2019-08-26 15:22:00',9),(493,5,'2019-08-26 15:22:00',9),(494,5,'2019-08-26 15:22:00',9),(495,5,'2019-08-26 15:22:00',9),(496,5,'2019-08-26 15:22:00',9),(497,5,'2019-08-26 15:22:00',9),(498,5,'2019-08-26 15:22:00',9),(499,5,'2019-08-26 15:22:00',9),(500,5,'2019-08-26 15:22:00',9);
/*!40000 ALTER TABLE `supplies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `IDteam` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(45) NOT NULL,
  `teamLeader` varchar(45) NOT NULL,
  PRIMARY KEY (`IDteam`),
  KEY `leaderCostraint_idx` (`teamLeader`),
  CONSTRAINT `leaderCostraint` FOREIGN KEY (`teamLeader`) REFERENCES `employee` (`IDemployee`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'Seattle','adrian'),(2,'Washington','james'),(3,'New York','laura'),(4,'Boston','rob');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','admin','admin','adminPassword','admin@example.com'),('adri','Adrienne','Andrews','adriPassword','adri@example.com'),('adrian','Adrian','Robinson','adrianPassword','adrian@example.com'),('agatha','Agatha','Lane','agathaPassword','agatha@example.com'),('amy','Amy','Lowe','amyPassword','amy@example.com'),('anto83','Anthony','Griffin','anto83Password','anto83@example.com'),('betty','Betty','Jordan','bettyPassword','betty@example.com'),('bob','Robert','Caldwell','bobPassword','bob@example.com'),('brad','Bradley','Proctor','bradPassword','brad@example.com'),('christopher','Christopher','Austin','christopherPassword','christopher@example.com'),('Dani','Danielle','Johnson','DaniPassword','Dani@example.com'),('david91','David','Baldwin','david91Password','david91@example.com'),('Dia','Diana','Williams','DiaPassword','Dia@example.com'),('donny','Donny','Hudson','donnyPassword','donny@example.com'),('eth','Ethan','Rivas','ethPassword','eth@example.com'),('Gabriel','Gabriel','Foster','GabrielPassword','Gabriel@example.com'),('imanne','Anne','Taylor','imannePassword','anne@example.com'),('jacob','Jacob','Chan','jacobPassword','jacob@example.com'),('james','James','Burns','jamesPassword','james@example.com'),('james96','James','Parker','james96Password','james@example.com'),('john','John','Carter','johnPassword','john@example.com'),('john72','John','Branch','john72Password','john72@example.com'),('laura','Laura','Schmitt','lauraPassword','laura@example.com'),('lisa','Lisa','Smith','lisaPassword','lisa@example.com'),('lola','Karola','Leal','lolaPassword','lola@example.com'),('lorry','Lorry','Prenton','lorryPassword','lorry@example.com'),('madeline','Madelaine','Terrel','madelinePassword','madeline@example.com'),('magicBob','Bob','White','magicBobPassword','magicbob@example.com'),('maria','Maria','Morrison','mariaPassword','maria@example.com'),('Marianne','Marianne','Payne','MariannePassword','Marianne@example.com'),('matt','Matthew','Duncan','mattPassword','matt@example.com'),('matty','Matthew','Simon','mattyPassword','matty@example.com'),('michael','Michael','Woods','michaelPassword','michael@example.com'),('PaulPaul','Paul','Scott','PaulPaulPassword','paul@example.com'),('Philip','Philip','Sanders','PhilipPassword','Philip@example.com'),('ralph68','Ralph','Mayo','ralph68Password','ralph68@example.com'),('randi','Randi','Dickson','randiPassword','randi@example.com'),('richard','Richard','Johnson','richardPassword','richard@example.com'),('rob','Robert','Kelley','robPassword','rob@example.com'),('roy','Roy','Smith','royPassword','roy@example.com'),('ruth','Ruth','Spencer','ruthPassword','ruth@example.com'),('Sarah','Sarah','Washington','SarahPassword','Sarah@example.com'),('seli','Selinda','Reyna','seliPassword','seli@example.com'),('soJenny','Jenny','Lingard','soJennyPassword','jenny@example.com'),('susy','Susan','Parks','susyPassword','susy@example.com'),('timJ','Tim','Jones','timJPassword','tim@example.com'),('Vince','Vincent','Ross','VincePassword','Vince@example.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'innovativesolutionsdb_new'
--

--
-- Dumping routines for database 'innovativesolutionsdb_new'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-13  4:16:47
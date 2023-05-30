-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: do_an_hdt
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `su_co`
--

DROP TABLE IF EXISTS `su_co`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `su_co` (
  `MaSC` char(10) NOT NULL,
  `TenNV` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Ngay` date DEFAULT NULL,
  `Gio` time DEFAULT NULL,
  `LoaiXe` char(12) DEFAULT NULL,
  `BienSoXe` char(10) DEFAULT NULL,
  `MoTa` mediumtext,
  PRIMARY KEY (`MaSC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `su_co`
--

LOCK TABLES `su_co` WRITE;
/*!40000 ALTER TABLE `su_co` DISABLE KEYS */;
INSERT INTO `su_co` VALUES ('1234','viet','2023-05-28','15:48:27',NULL,'123','ád'),('SC01','viet','2023-05-27','15:11:22',NULL,'30A231','hỏng động cơ đốt trong'),('sc02','hung','2023-05-26',NULL,NULL,NULL,NULL),('sc03','quyet','2023-05-27',NULL,NULL,NULL,NULL),('sc04','hung','2023-05-29',NULL,NULL,NULL,NULL),('sc05',NULL,NULL,NULL,NULL,NULL,NULL),('sc06',NULL,NULL,NULL,NULL,NULL,NULL),('sc07',NULL,NULL,NULL,NULL,NULL,NULL),('sc08',NULL,NULL,NULL,'ư',NULL,NULL),('sc09',NULL,NULL,NULL,NULL,NULL,NULL),('sc10',NULL,NULL,NULL,NULL,NULL,NULL),('sc11',NULL,NULL,NULL,NULL,NULL,NULL),('SC69','viet','2023-05-28','23:32:56',NULL,'dagva','một người từng thương nhiều thế ngồi lại như người dưng');
/*!40000 ALTER TABLE `su_co` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-30 14:21:22

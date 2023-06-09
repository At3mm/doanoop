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
-- Table structure for table `the_ngay`
--

DROP TABLE IF EXISTS `the_ngay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `the_ngay` (
  `MaTheNgay` char(10) NOT NULL,
  `LoaiThe` char(10) DEFAULT NULL,
  `LoaiXe` char(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `BienSoXe` char(10) DEFAULT NULL,
  PRIMARY KEY (`MaTheNgay`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `the_ngay`
--

LOCK TABLES `the_ngay` WRITE;
/*!40000 ALTER TABLE `the_ngay` DISABLE KEYS */;
INSERT INTO `the_ngay` VALUES ('TN01',NULL,'',''),('TN02',NULL,NULL,NULL),('TN03',NULL,'',''),('TN04',NULL,'',''),('TN05',NULL,NULL,NULL),('TN06',NULL,NULL,NULL),('TN07',NULL,NULL,NULL),('TN08',NULL,NULL,NULL),('TN09',NULL,NULL,NULL),('TN10',NULL,NULL,NULL),('TN11',NULL,NULL,NULL),('TN12',NULL,NULL,NULL),('TN13',NULL,NULL,NULL),('TN14',NULL,NULL,NULL),('TN15',NULL,NULL,NULL),('TN16',NULL,NULL,NULL),('TN17',NULL,NULL,NULL),('TN18',NULL,NULL,NULL),('TN19',NULL,NULL,NULL),('TN20',NULL,NULL,NULL),('TN21',NULL,NULL,NULL),('TN22',NULL,NULL,NULL),('TN23',NULL,NULL,NULL),('TN24',NULL,NULL,NULL),('TN25',NULL,NULL,NULL),('TN26',NULL,NULL,NULL),('TN27',NULL,NULL,NULL),('TN28',NULL,NULL,NULL),('TN29',NULL,NULL,NULL),('TN30',NULL,NULL,NULL),('TN31',NULL,NULL,NULL),('TN32',NULL,NULL,NULL),('TN33',NULL,NULL,NULL),('TN34',NULL,NULL,NULL),('TN35',NULL,NULL,NULL),('TN36',NULL,NULL,NULL),('TN37',NULL,NULL,NULL),('TN38',NULL,NULL,NULL),('TN39',NULL,NULL,NULL),('TN40',NULL,NULL,NULL),('TN41',NULL,NULL,NULL),('TN42',NULL,NULL,NULL),('TN43',NULL,NULL,NULL),('TN44',NULL,NULL,NULL),('TN45',NULL,NULL,NULL),('TN46',NULL,NULL,NULL),('TN47',NULL,NULL,NULL),('TN48',NULL,NULL,NULL),('TN49',NULL,NULL,NULL),('TN50',NULL,NULL,NULL),('TN51',NULL,NULL,NULL),('TN52',NULL,NULL,NULL),('TN53',NULL,NULL,NULL),('TN54',NULL,NULL,NULL),('TN55',NULL,NULL,NULL),('TN56',NULL,NULL,NULL),('TN57',NULL,NULL,NULL),('TN58',NULL,NULL,NULL),('TN59',NULL,NULL,NULL),('TN60',NULL,NULL,NULL);
/*!40000 ALTER TABLE `the_ngay` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-04 11:02:15

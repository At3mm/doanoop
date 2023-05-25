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
-- Table structure for table `nhantraxe`
--

DROP TABLE IF EXISTS `nhantraxe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhantraxe` (
  `MaThe` char(10) NOT NULL,
  `LoaiThe` char(10) DEFAULT NULL,
  `MaNhan` char(10) DEFAULT NULL,
  `MaTra` char(10) DEFAULT NULL,
  `ThoiGianNhan` datetime DEFAULT NULL,
  `ThoiGianTra` datetime DEFAULT NULL,
  `MaKhu` char(10) DEFAULT NULL,
  `MaNV` char(10) DEFAULT NULL,
  PRIMARY KEY (`MaThe`),
  KEY `khu_1_idx` (`MaKhu`),
  KEY `manv_1_idx` (`MaNV`),
  CONSTRAINT `k_1` FOREIGN KEY (`MaKhu`) REFERENCES `khu` (`MaKhu`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nv_1` FOREIGN KEY (`MaNV`) REFERENCES `taikhoan` (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhantraxe`
--

LOCK TABLES `nhantraxe` WRITE;
/*!40000 ALTER TABLE `nhantraxe` DISABLE KEYS */;
INSERT INTO `nhantraxe` VALUES ('The01','ngay','0123','0123',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `nhantraxe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-25 23:03:49

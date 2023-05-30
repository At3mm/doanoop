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
-- Table structure for table `nhan_tra_xe`
--

DROP TABLE IF EXISTS `nhan_tra_xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhan_tra_xe` (
  `MaThe` char(10) NOT NULL,
  `LoaiThe` char(10) DEFAULT NULL,
  `NgayNhan` date DEFAULT NULL,
  `GioNhan` time DEFAULT NULL,
  `NgayTra` date DEFAULT NULL,
  `GioTra` time DEFAULT NULL,
  `SoTien` int DEFAULT NULL,
  `ViTri` char(10) NOT NULL,
  `MaNV` char(10) NOT NULL,
  PRIMARY KEY (`MaThe`),
  KEY `manv_1_idx` (`MaNV`),
  KEY `vitri_1_idx` (`ViTri`),
  CONSTRAINT `nv_1` FOREIGN KEY (`MaNV`) REFERENCES `tai_khoan` (`MaNV`),
  CONSTRAINT `vitri_1` FOREIGN KEY (`ViTri`) REFERENCES `vi_tri` (`ViTri`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhan_tra_xe`
--

LOCK TABLES `nhan_tra_xe` WRITE;
/*!40000 ALTER TABLE `nhan_tra_xe` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhan_tra_xe` ENABLE KEYS */;
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

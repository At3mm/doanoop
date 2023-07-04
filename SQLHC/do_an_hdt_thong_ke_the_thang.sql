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
-- Table structure for table `thong_ke_the_thang`
--

DROP TABLE IF EXISTS `thong_ke_the_thang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thong_ke_the_thang` (
  `MaThe` char(10) NOT NULL,
  `LoaiXe` char(16) DEFAULT NULL,
  `BienSoXe` char(10) DEFAULT NULL,
  `NgayNhan` date DEFAULT NULL,
  `GioNhan` time DEFAULT NULL,
  `NgayTra` date DEFAULT NULL,
  `GioTra` time DEFAULT NULL,
  `Khu` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thong_ke_the_thang`
--

LOCK TABLES `thong_ke_the_thang` WRITE;
/*!40000 ALTER TABLE `thong_ke_the_thang` DISABLE KEYS */;
INSERT INTO `thong_ke_the_thang` VALUES ('TT30','Xe máy','69F22223','2023-06-12','15:54:41','2023-06-12','08:02:11','B'),('TT17','Xe máy','53A12982','2023-06-14','12:21:45','2023-06-14','12:35:09','E'),('TT12','Xe máy','15D56611','2023-06-19','12:32:57','2023-06-19','13:17:04','A'),('TT17','Xe đạp','null','2023-06-18','14:35:36','2023-06-18','15:26:01','I'),('TT26','Xe đạp','null','2023-06-24','07:41:34','2023-06-24','16:28:31','I'),('TT28','Xe máy','29K15785','2023-07-02','13:37:29','2023-07-02','17:03:11','C'),('TT23','Xe máy','30K54231','2023-06-24','07:20:42','2023-06-24','17:07:42','A'),('TT18','Xe đạp','null','2023-06-08','10:13:08','2023-06-08','17:46:20','F'),('TT40','Xe máy','7A16910','2023-06-05','09:26:40','2023-06-05','17:52:28','A'),('TT29','Xe đạp','null','2023-06-08','12:42:03','2023-06-08','17:58:58','G'),('TT39','Xe đạp','null','2023-06-12','11:57:35','2023-06-12','18:11:33','H'),('TT01','Xe máy','23a21314','2023-06-17','08:49:27','2023-06-17','18:52:48','D'),('TT40','Xe máy','7A16910','2023-06-09','14:39:48','2023-06-09','20:22:19','D'),('TT10','Xe máy','21I24070','2023-06-17','11:19:36','2023-06-17','20:34:38','E'),('TT09','Xe máy','10D68786','2023-06-22','19:03:31','2023-06-22','21:20:02','B');
/*!40000 ALTER TABLE `thong_ke_the_thang` ENABLE KEYS */;
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

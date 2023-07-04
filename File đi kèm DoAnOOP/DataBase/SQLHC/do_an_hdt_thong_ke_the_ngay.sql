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
-- Table structure for table `thong_ke_the_ngay`
--

DROP TABLE IF EXISTS `thong_ke_the_ngay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thong_ke_the_ngay` (
  `MaThe` char(10) NOT NULL,
  `LoaiXe` char(10) DEFAULT NULL,
  `BienSoXe` char(10) DEFAULT NULL,
  `NgayNhan` date DEFAULT NULL,
  `GioNhan` time DEFAULT NULL,
  `NgayTra` date DEFAULT NULL,
  `GioTra` time DEFAULT NULL,
  `Khu` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thong_ke_the_ngay`
--

LOCK TABLES `thong_ke_the_ngay` WRITE;
/*!40000 ALTER TABLE `thong_ke_the_ngay` DISABLE KEYS */;
INSERT INTO `thong_ke_the_ngay` VALUES ('TN17','Xe máy','24V78309','2023-06-24','06:15:07','2023-06-24','09:08:10','B'),('TN10','Xe máy','4K75246','2023-06-27','06:29:54','2023-06-27','15:28:19','C'),('TN21','Xe máy','13Y63320','2023-06-18','06:30:23','2023-06-18','14:52:14','D'),('TN18','Xe đạp','null','2023-07-02','07:35:49','2023-07-02','18:15:20','I'),('TN04','Xe máy','60E26913','2023-06-02','09:21:11','2023-06-02','17:08:27','A'),('TN25','Xe đạp','null','2023-06-17','10:26:51','2023-06-17','16:18:08','H'),('TN03','Xe đạp','null','2023-06-29','10:27:21','2023-06-29','14:29:27','G'),('TN18','Xe máy','31U28622','2023-06-20','11:41:04','2023-06-20','17:33:33','A'),('TN30','Xe máy','8R42881','2023-06-24','11:51:24','2023-06-24','18:02:27','D'),('TN16','Xe đạp','null','2023-07-02','11:56:55','2023-07-02','19:50:43','J'),('TN19','Xe máy','42O66780','2023-06-29','12:13:57','2023-06-29','18:23:15','C'),('TN20','Xe máy','74X48614','2023-06-30','12:26:49','2023-06-30','14:00:50','E'),('TN15','Xe máy','75A83443','2023-06-14','13:14:10','2023-06-14','19:24:59','B'),('TN19','Xe đạp','null','2023-06-04','13:39:10','2023-06-04','21:31:39','F'),('TN15','Xe máy','47A15234','2023-06-29','20:26:56','2023-06-29','21:41:04','E');
/*!40000 ALTER TABLE `thong_ke_the_ngay` ENABLE KEYS */;
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

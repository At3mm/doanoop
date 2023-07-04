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
  `MaThe` char(10) DEFAULT NULL,
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
INSERT INTO `su_co` VALUES ('SC01','Công Anh','TT39','2023-06-03','12:46:05','Xe máy','77C26253','Hỏng bánh xe'),('SC02','Hưng','TT28','2023-06-06','07:31:28','Xe máy','6W56878','Mất thẻ xe'),('SC03','Việt','TT09','2023-06-07','18:05:34','Xe máy','28J14914','Xe mất gương'),('SC04','Công Anh','TT36','2023-06-08','12:41:26','Xe máy','54Z95604','Mất thẻ xe'),('SC05','Quyết','TT32','2023-06-08','14:10:27','Xe máy','25D21756','Mất thẻ xe'),('SC06','Việt','TN04','2023-06-10','15:24:17','Xe máy','29A44444','Hỏng bánh xe'),('SC07','Việt','TT03','2023-06-11','10:37:24','Xe máy','27V25093','Mất thẻ xe'),('SC08','Hưng','TT03','2023-06-12','06:56:58','Xe máy','29A56451','Mất chìa khoá xe'),('SC09','Quyết','TT01','2023-06-12','15:25:59','Xe máy','23a21314','Mất thẻ xe'),('SC10','Hưng','TT01','2023-06-13','16:13:32','Xe máy','23a21314','Xe mất gương'),('SC11','Quyết','TT09','2023-06-15','18:46:17','Xe máy','76E94549','Mất thẻ xe'),('SC12','Công Anh','TT04','2023-06-15','21:32:28','Xe máy','51A12345','Mất chìa khoá xe'),('SC13','Hưng','TT03','2023-06-18','10:51:16','Xe máy','29A56451','Mất chìa khoá xe'),('SC14','Công Anh','TT37','2023-06-18','19:21:44','Xe đạp','null','Thủng lốp xe'),('SC15','Quyết','TT18','2023-06-23','15:16:58','Xe máy','10D15748','Mất chìa khoá xe'),('SC16','Hưng','TT30','2023-06-25','16:30:14','Xe máy','29A56451','Mất chìa khoá xe'),('SC17','Việt','TN03','2023-06-28','21:49:38','Xe máy','29D201017','Mất chìa khoá xe'),('SC18','Hưng','TT34','2023-07-02','09:23:58','Xe đạp','null','Mất gương xe'),('SC19','Hưng','TT06','2023-07-02','15:36:30','Xe máy','29K20101','Mất gương xe'),('SC20','Hưng','TT28','2023-07-02','19:27:15','Xe đạp','null','Mất gương xe');
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

-- Dump completed on 2023-07-04 11:02:14

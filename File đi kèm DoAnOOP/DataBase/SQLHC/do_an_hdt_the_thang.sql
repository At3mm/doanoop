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
-- Table structure for table `the_thang`
--

DROP TABLE IF EXISTS `the_thang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `the_thang` (
  `MaTheThang` char(10) NOT NULL,
  `LoaiThe` char(10) DEFAULT NULL,
  `LoaiXe` char(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `BienSoXe` char(10) DEFAULT NULL,
  `TenKH` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `SDT` char(10) DEFAULT NULL,
  `NgayDangKi` date DEFAULT NULL,
  `GioDangKi` time DEFAULT NULL,
  `Do` tinyint DEFAULT NULL,
  PRIMARY KEY (`MaTheThang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `the_thang`
--

LOCK TABLES `the_thang` WRITE;
/*!40000 ALTER TABLE `the_thang` DISABLE KEYS */;
INSERT INTO `the_thang` VALUES ('TT01','Thẻ tháng','Xe máy','23a21314','Ngô Đức Minh','0945146456','2023-06-08','10:09:00',0),('TT02','Thẻ tháng','Xe máy','29A1654','Trần Quang Việt','0246564531','2023-06-08','08:52:08',0),('TT03','Thẻ tháng','Xe máy','29A1654651','Đinh Văn Cường','0654134598','2023-06-08','10:09:00',0),('TT04','Thẻ tháng','Xe máy','29K10101','Vũ Văn Vinh','1013566134','2023-06-08','10:09:00',0),('TT05','Thẻ tháng','Xe máy','16a46456','Nguyễn Đình Khánh','0914565564','2023-06-08','13:40:06',0),('TT06','Thẻ tháng','Xe máy','29K20101','Ngô Mạnh Hùng','0246546540','2023-06-10','14:07:44',0),('TT07','Thẻ tháng','Xe máy','16a123456','Nguyễn Duy Hùng','0366465465','2023-06-19','10:01:52',0),('TT08','Thẻ tháng','Xe máy','29A20101','Trần Văn Việt','0564978443','2023-06-28','08:49:17',0),('TT09','Thẻ tháng','Xe đạp','null','Nguyễn Công Anh','0958459649','2023-06-28','09:03:33',0),('TT10','Thẻ tháng','Xe máy','36A65465','Lê Đình Vinh','095646556','2023-06-28','15:33:36',0),('TT11','Thẻ tháng','Xe đạp','null','Trần Thu Trang','0982017128','2023-06-23','06:21:00',0),('TT12','Thẻ tháng','Xe máy','39D12354','Nguyễn Xuân Phương','0663579172','2023-06-23','08:13:32',0),('TT13','Thẻ tháng','Xe đạp','null','Lưu Đình Khánh','0696184853','2023-06-21','14:23:43',0),('TT14','Thẻ tháng','Xe đạp','null','Nguyễn Thị Hồng Ngọc','0519165710','2023-06-10','21:36:30',0),('TT15','Thẻ tháng','Xe máy','51A12456','Lưu Đình Khánh','0906348408','2023-06-09','21:56:21',0),('TT16','Thẻ tháng','Xe máy','51A83655','Vũ Lan Anh','0243226365','2023-06-02','06:14:25',0),('TT17','Thẻ tháng','Xe đạp','null','Trần Duy Quang','0429166848','2023-06-15','07:53:09',0),('TT18','Thẻ tháng','Xe máy','10D15748','Ngô Đức Mạnh','0743107281','2023-06-10','14:49:48',0),('TT19','Thẻ tháng','Xe máy','15A54865','Nguyễn Mạnh Chính','0112673713','2023-06-23','15:06:20',0),('TT20','Thẻ tháng','Xe máy','31A57486','Lê Trần Hùng Đức','0721288959','2023-06-15','18:09:09',0),('TT21','Thẻ tháng','Xe đạp','null','Nguyễn Phương Linh','0885576962','2023-06-23','07:31:47',0),('TT22','Thẻ tháng','Xe đạp','null','Chu Văn Giang','0840929406','2023-06-23','07:46:05',0),('TT23','Thẻ tháng','Xe máy','30K54231','Chu Minh Cường','0579883826','2023-06-23','18:18:27',0),('TT24','Thẻ tháng','Xe máy','30K54654','Hoàng Văn Ninh','0842844358','2023-06-29','21:04:36',0),('TT25','Thẻ tháng','Xe đạp','null','Ngô Thừa Dụ','0390458631','2023-06-14','10:37:43',0),('TT26','Thẻ tháng','Xe đạp','null','Nguyễn Văn Linh','0382216840','2023-06-14','11:48:10',0),('TT27','Thẻ tháng','Xe máy','29D15674','Chu Văn An','0223708002','2023-06-23','15:17:28',0),('TT28','Thẻ tháng','Xe máy','29K15785','Đinh Mạnh Ninh','0975101563','2023-06-16','18:18:27',0),('TT29','Thẻ tháng','Xe đạp','null','Trần Thu Phương','0487366522','2023-06-14','10:36:32',0),('TT30','Thẻ tháng','Xe máy','29A56451','Trần Văn Quyết','0362138766','2023-06-16','16:04:12',0),('TT31','Thẻ tháng','Xe máy','29D75421','Vũ Xuân Hường','0502782721','2023-06-16','18:48:24',0),('TT32','Thẻ tháng','Xe đạp','null','Nguyễn Mạnh Cường','0805174312','2023-06-25','13:23:10',0),('TT33','Thẻ tháng','Xe đạp','null','Ngô Thị Kiều Diễm','0365912227','2023-06-30','07:31:38',0),('TT34','Thẻ tháng','Xe máy','36B57841','Nguyễn Thị Minh Trang','0194478390','2023-06-30','17:39:37',0),('TT35','Thẻ tháng','Xe máy','36A57813','Trương Thanh Tú','0118129640','2023-06-30','21:00:00',0),('TT36','Thẻ tháng','Xe đạp','null','Trương Nguyên ','0115491616','2023-06-21','08:26:32',0),('TT37','Thẻ tháng','Xe máy','44D48313','Trần Thu Minh','0807823373','2023-06-25','14:36:51',0),('TT38','Thẻ tháng','Xe đạp','null','Lê Thu Phương','0412520073','2023-06-20','10:27:36',0),('TT39','Thẻ tháng','Xe máy','29B57123','Nguyễn Tấn Trinh','0568953184','2023-06-30','21:52:07',0),('TT40','Thẻ tháng','Xe đạp','null','Tân Duy Trọng','0256236730','2023-06-21','20:45:58',0);
/*!40000 ALTER TABLE `the_thang` ENABLE KEYS */;
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

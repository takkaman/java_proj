-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: aleksi
-- ------------------------------------------------------
-- Server version	5.7.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--
use dexin;
DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerId` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `addressline1` varchar(255) NOT NULL,
  `addressline2` varchar(255) DEFAULT NULL,
  `postalcode` varchar(6) NOT NULL,
  `mobile` varchar(8) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `itemId` int(11) NOT NULL AUTO_INCREMENT,
  `itemDescription` varchar(512) NOT NULL,
  `brand` varchar(128) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  `points` int(6) NOT NULL,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (48,'Samsung 32GB BAR (METAL) USB 3.0 Flash Drive (MUF-32BA/AM)','Samsung',18.50,6),(49,'SanDisk Cruzer CZ36 64GB USB 2.0 Flash Drive, Frustration-Free Packaging- SDCZ36-064G-AFFP','SanDisk',24.00,7),(50,'SanDisk Cruzer Blade 32GB USB 2.0 Flash Drive, Frustration-Free Packaging- SDCZ50-032G-AFFP','SanDisk',14.50,4),(51,'TOPSELL 5 Pack 16GB USB 2.0 Flash Drive Memory Stick Thumb Drives (5 Mixed Colors: Black Blue Green Red Silver)','TOPSELL',39.00,12),(52,'SanDisk Cruzer CZ36 32GB USB 2.0 Flash Drive, Frustration-Free Packaging- SDCZ36-032G-AFFP','SanDisk',15.00,5),(53,'SanDisk Cruzer Glide CZ60 128GB USB 2.0 Flash Drive- SDCZ60-128G-B35','SanDisk',43.50,13),(54,'Samsung 64GB BAR (METAL) USB 3.0 Flash Drive (MUF-64BA/AM)','Samsung',27.00,8),(55,'KOOTION 5PCS 1GB USB Flash Drives Thumb Drives Memory Stick USB 2.0(5 Colors: Black Blue Green Purple Red)','KOOTION',26.50,8),(56,'WD 4TB Elements Portable External Hard Drive - USB 3.0 - WDBU6Y0040BBK-WESN','Western Digital',165.00,50),(57,'Seagate Expansion 1TB Portable External Hard Drive USB 3.0 (STEA1000400)','Seagate',82.50,25),(58,'Seagate Backup Plus Slim 2TB Portable External Hard Drive USB 3.0, Silver (STDR2000101)','Seagate',105.00,32),(59,'WD 4TB Black My Passport Portable External Hard Drive - USB 3.0 - WDBYFT0040BBK-WESN','Western Digital',165.00,50),(60,'Silicon Power 1TB Rugged Armor A60 Military-grade Shockproof/Water-Resistant USB 3.0 2.5\" External Hard Drive for','Silicon Power',88.50,27),(61,'WD 2TB Silver My Passport Ultra Metal Edition Portable External Hard Drive - USB 3.0 - WDBEZW0020BSL-NESN','Western Digital',120.00,36),(62,'Toshiba Canvio Connect II 1TB Portable Hard Drive, Red (HDTC810XR3A1)','Toshiba',85.00,26),(63,'LaCie Rugged Mini USB 3.0 / USB 2.0 1TB Portable Hard Drive LAC301558','LaCie',135.00,41),(64,'HP Wired USB Keyboard K1500 (Black)','HP',12.00,4),(65,'Logitech MK270 Wireless Keyboard and Mouse Combo','Logitech',29.50,9),(66,'Logitech Wireless Keyboard K360 - Glossy Black','Logitech',30.00,9),(67,'Rii RK100 3 Colors LED Backlit Mechanical Feeling USB Wired Multimedia Keyboard For working or prime gaming','Rii',28.50,9),(68,'Logitech Wireless Wave Combo MK550 - Curved Comfort, Black (920-002555)','Logitech',66.00,20),(69,'Verbatim Slimline Keyboard - Wired with USB Accessibility - Mac & PC Compatible - Black','Verbatim',10.00,3),(70,'Microsoft Natural Ergonomic Keyboard 4000 for Business - Wired','Microsoft',78.00,23),(71,'Apple Magic Keyboard with Numeric Keypad & Apple Magic Trackpad 2 Bundle','Magic Keyboard and Trackpad',372.00,112),(72,'HP 15.6\" HD WLED Backlit Display Laptop, AMD A6-7310 Quad-Core APU 2GHz, 4GB RAM, 500GB HDD WiFi, DVD+/-RW,','HP',397.50,119),(73,'Acer Aspire E 15 E5-575-33BM 15.6-Inch Full HD Notebook (Intel Core i3-7100U Processor 7th Generation , 4GB DDR4, 1TB','Acer',525.00,158),(74,'2017 Newest Lenovo Premium Built High Performance 15.6 inch HD Laptop Intel Pentium Dual-Core Processor 6GB RAM 1T HDD DVD','Lenovo',452.50,136),(75,'Lenovo 110s Premium Built High Performance 11.6 inch HD Laptop pc Intel Celeron Dual-Core Processor 2GB RAM 32G eMMC','Lenovo',236.50,71),(76,'ASUS L402SA Portable Lightweight Laptop PC, Intel Dual Core Processor, 4GB RAM, 32GB Flash Storage with Windows 10 with 1','Asus',283.50,85),(77,'HP 17.3 inch HD+ Flagship High Performance Laptop PC, Intel Core i7-7500U 2.7GHz Dual-Core, 8GB DDR4, 1TB HDD, DVD RW,','HP',828.00,248),(78,'HP Stream Laptop PC 14-ax010nr (Intel Celeron N3060, 4 GB RAM, 32 GB eMMC) with Office 365 Personal for one year','HP',352.00,106),(79,'Lenovo Ideapad 15.6\" HD Premium High Performance Laptop (2017 Newest), AMD A12-9720P Quad core processor 2.7GHz, 8GB','Lenovo',585.00,176),(80,'Acer R240HY bidx 23.8-Inch IPS HDMI DVI VGA (1920 x 1080) Widescreen Monitor','Acer',195.00,59),(81,'LG 32MA68HY-P 32-Inch IPS Monitor with Display Port and HDMI Inputs','LG',300.00,90),(82,'Acer G206HQLbd 19.5-Inch LED Computer Monitor Back-Lit Widescreen Display','Acer',118.00,35),(83,'Asus VS239H-P 23-Inch Full HD 1920x1080 IPS HDMI DVI VGA Back-lit LED Monitor','Asus',178.00,53),(84,'Samsung SF350 Series 19-Inch Slim Design Monitor (S19F350)','Samsung',120.00,36),(85,'HP 23.8-inch FHD IPS Monitor with Tilt/Height Adjustment and Built-in Speakers (VH240a, Black)','HP',195.00,59),(86,'Dell E1916HV VESA Mountable 19\" Screen LED-Lit Monitor','Dell',92.50,28),(87,'Sceptre E248W-1920 24-Inch Screen LED-Lit Monitor','Sceptre',150.00,45);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderdetails` (
  `orderid` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `quantity` int(3) NOT NULL,
  PRIMARY KEY (`orderid`,`itemid`),
  KEY `ItemId_idx` (`itemid`),
  CONSTRAINT `ItemId` FOREIGN KEY (`itemid`) REFERENCES `item` (`itemId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `OrderId` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `orderprice` decimal(8,2) NOT NULL,
  `orderpoints` int(6) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderid`),
  KEY `CustomerId_idx` (`customerid`),
  CONSTRAINT `CustomerId` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-01 11:37:30

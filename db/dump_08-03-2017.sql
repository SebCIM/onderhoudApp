-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: onderhouddb
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `a_r`
--

DROP TABLE IF EXISTS `a_r`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `a_r` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apuserid` int(11) DEFAULT NULL,
  `reparatieid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `apuserid_idx` (`apuserid`),
  KEY `reparatieid_idx` (`reparatieid`),
  CONSTRAINT `apuserid` FOREIGN KEY (`apuserid`) REFERENCES `apuser` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reparatieid` FOREIGN KEY (`reparatieid`) REFERENCES `reparatie` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a_r`
--

LOCK TABLES `a_r` WRITE;
/*!40000 ALTER TABLE `a_r` DISABLE KEYS */;
/*!40000 ALTER TABLE `a_r` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apuser`
--

DROP TABLE IF EXISTS `apuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `bedrijf` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `district` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apuser`
--

LOCK TABLES `apuser` WRITE;
/*!40000 ALTER TABLE `apuser` DISABLE KEYS */;
INSERT INTO `apuser` VALUES (1,'Paul Kuijper','Rijkswaterstaat','admin','admin',1,'p.kuijper@rijkswaterstaat.nl','06123456789','NNW'),(27,'Willem van Eck','De Jong Zuurmond',NULL,'WTf4B',NULL,'w.van.eck@dejongzuurmond.nl','0653572397','Z&D'),(28,'Test Persoon','Test Bedrijf',NULL,'e83eg',NULL,'test@gmail.com','testzes','test'),(30,'tester','',NULL,'XHbZp',NULL,'','','');
/*!40000 ALTER TABLE `apuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reparatie`
--

DROP TABLE IF EXISTS `reparatie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reparatie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `soort` varchar(45) DEFAULT NULL,
  `district` varchar(45) DEFAULT NULL,
  `rijksweg` varchar(45) DEFAULT NULL,
  `hectometerbord` varchar(45) DEFAULT NULL,
  `baan` varchar(45) DEFAULT NULL,
  `strook` varchar(45) DEFAULT NULL,
  `verhardingssoort` varchar(45) DEFAULT NULL,
  `reparatiemethode` varchar(45) DEFAULT NULL,
  `datumtijd` datetime DEFAULT NULL,
  `noodofspoed` varchar(45) DEFAULT NULL,
  `aantalgaten` varchar(45) DEFAULT NULL,
  `opmerking` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='			';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparatie`
--

LOCK TABLES `reparatie` WRITE;
/*!40000 ALTER TABLE `reparatie` DISABLE KEYS */;
/*!40000 ALTER TABLE `reparatie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-08 16:48:12

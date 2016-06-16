-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: eBank9
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `acc_assignements`
--

DROP TABLE IF EXISTS `acc_assignements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_assignements` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_account` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_isan3ym0sy9q96swt0i9vdtt0` (`id_account`),
  KEY `FK_jx0yvie4l4jpag2qj7heegi22` (`id_user`),
  CONSTRAINT `FK_isan3ym0sy9q96swt0i9vdtt0` FOREIGN KEY (`id_account`) REFERENCES `accounts` (`id`),
  CONSTRAINT `FK_jx0yvie4l4jpag2qj7heegi22` FOREIGN KEY (`id_user`) REFERENCES `users_hb` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_assignements`
--

LOCK TABLES `acc_assignements` WRITE;
/*!40000 ALTER TABLE `acc_assignements` DISABLE KEYS */;
INSERT INTO `acc_assignements` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,2),(8,8,2);
/*!40000 ALTER TABLE `acc_assignements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `IBAN` varchar(34) NOT NULL,
  `acc_type` int(11) NOT NULL,
  `balance` double NOT NULL,
  `currency` int(11) NOT NULL,
  `opening_date` datetime NOT NULL,
  `id_comm` bigint(20) NOT NULL,
  `id_rate` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dbsf1jmd1ttsvdsrah08yt3oe` (`id_comm`),
  KEY `FK_2uikcufueg8vcbqboa8ac2hle` (`id_rate`),
  CONSTRAINT `FK_2uikcufueg8vcbqboa8ac2hle` FOREIGN KEY (`id_rate`) REFERENCES `rates` (`id`),
  CONSTRAINT `FK_dbsf1jmd1ttsvdsrah08yt3oe` FOREIGN KEY (`id_comm`) REFERENCES `commisions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'RO01TEST3262702634709088',2,0,0,'2016-06-14 17:30:24',1,2),(2,'RO02TEST9562979163898794',2,0,1,'2016-06-14 17:30:57',2,3),(3,'RO01TEST6383290919662709',0,1000,0,'2016-06-14 17:31:13',3,1),(4,'RO02TEST1743188689617071',0,1000,1,'2016-06-14 17:31:29',4,1),(5,'RO01TEST8470827035996785',1,1000,0,'2016-06-14 17:31:46',5,4),(6,'RO02TEST8025179927069130',1,1000,1,'2016-06-14 17:33:17',6,5),(7,'RO01TEST8494622164088562',0,100,0,'2016-06-15 18:24:10',3,1),(8,'RO02TEST6696280318880379',0,100,1,'2016-06-15 18:25:03',4,1);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_assignements`
--

DROP TABLE IF EXISTS `card_assignements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card_assignements` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_account` bigint(20) NOT NULL,
  `id_card` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jweek2me5jcufbnc33h4fom7v` (`id_account`),
  KEY `FK_nrwt513ohk6gdb6ykckv8pysm` (`id_card`),
  KEY `FK_72x3g7sm15kwaybnvl0f2175n` (`id_user`),
  CONSTRAINT `FK_72x3g7sm15kwaybnvl0f2175n` FOREIGN KEY (`id_user`) REFERENCES `users_hb` (`id`),
  CONSTRAINT `FK_jweek2me5jcufbnc33h4fom7v` FOREIGN KEY (`id_account`) REFERENCES `accounts` (`id`),
  CONSTRAINT `FK_nrwt513ohk6gdb6ykckv8pysm` FOREIGN KEY (`id_card`) REFERENCES `cards` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_assignements`
--

LOCK TABLES `card_assignements` WRITE;
/*!40000 ALTER TABLE `card_assignements` DISABLE KEYS */;
INSERT INTO `card_assignements` VALUES (1,1,1,1),(2,2,2,1),(3,3,3,1);
/*!40000 ALTER TABLE `card_assignements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cards` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_no` varchar(16) NOT NULL,
  `card_type` int(11) NOT NULL,
  `daily_limit` double NOT NULL,
  `validity` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` VALUES (1,'3738335772419407',0,3000,'12/2020'),(2,'5754411913723993',0,3000,'12/2020'),(3,'3741212974211229',1,3000,'12/2020');
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commisions`
--

DROP TABLE IF EXISTS `commisions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commisions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `comm_type` int(11) NOT NULL,
  `details` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commisions`
--

LOCK TABLES `commisions` WRITE;
/*!40000 ALTER TABLE `commisions` DISABLE KEYS */;
INSERT INTO `commisions` VALUES (1,10,10,'Comm for admin credit acc in RON'),(2,10,10,'Comm for admin credit acc in EUR'),(3,5,8,'Comm for admin curr acc in RON'),(4,5,8,'Comm for admin curr acc in EUR'),(5,5,9,'Comm for admin saving acc in RON'),(6,5,9,'Comm for admin saving acc in EUR'),(7,0,11,'False comm for in-house TRX');
/*!40000 ALTER TABLE `commisions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exchange_rates`
--

DROP TABLE IF EXISTS `exchange_rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exchange_rates` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `buy` double NOT NULL,
  `currency` int(11) NOT NULL,
  `sell` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exchange_rates`
--

LOCK TABLES `exchange_rates` WRITE;
/*!40000 ALTER TABLE `exchange_rates` DISABLE KEYS */;
/*!40000 ALTER TABLE `exchange_rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rates`
--

DROP TABLE IF EXISTS `rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rates` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `details` varchar(255) NOT NULL,
  `rate_type` int(11) NOT NULL,
  `year_percentage` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rates`
--

LOCK TABLES `rates` WRITE;
/*!40000 ALTER TABLE `rates` DISABLE KEYS */;
INSERT INTO `rates` VALUES (1,'False rate',2,0),(2,'Rate for credit card in RON',1,12),(3,'Rate for credit card in EUR',1,10),(4,'Rate for saving acc in RON',0,12),(5,'Rate for saving acc in EUR',0,10);
/*!40000 ALTER TABLE `rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_assignements`
--

DROP TABLE IF EXISTS `token_assignements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token_assignements` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_token` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ssy1w0imjmkl9qk0k6u8s4q6j` (`id_token`),
  KEY `FK_jfm3m8bs68gymck9y0nsyd1a` (`id_user`),
  CONSTRAINT `FK_jfm3m8bs68gymck9y0nsyd1a` FOREIGN KEY (`id_user`) REFERENCES `users_hb` (`id`),
  CONSTRAINT `FK_ssy1w0imjmkl9qk0k6u8s4q6j` FOREIGN KEY (`id_token`) REFERENCES `tokens` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_assignements`
--

LOCK TABLES `token_assignements` WRITE;
/*!40000 ALTER TABLE `token_assignements` DISABLE KEYS */;
/*!40000 ALTER TABLE `token_assignements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tokens` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `serial` bigint(20) NOT NULL,
  `token_type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `beneficiary_IBAN` varchar(34) NOT NULL,
  `date` datetime NOT NULL,
  `details` varchar(255) NOT NULL,
  `payer_IBAN` varchar(34) NOT NULL,
  `status` int(11) NOT NULL,
  `trans_type` int(11) NOT NULL,
  `id_comm` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_d3s88w9ra4jxjiyqivvn5e5d1` (`id_comm`),
  CONSTRAINT `FK_d3s88w9ra4jxjiyqivvn5e5d1` FOREIGN KEY (`id_comm`) REFERENCES `commisions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (4,100,'RO01TEST8494622164088562','2016-06-16 01:29:10','Internal payment with same currency','RO01TEST6383290919662709',0,0,7),(5,10,'RO01TEST8494622164088562','2016-06-16 01:29:53','Internal payment EUR->RON','RO02TEST1743188689617071',0,1,7),(6,1,'RO02TEST6696280318880379','2016-06-16 01:30:42','Internal payment RON->EUR','RO01TEST6383290919662709',0,1,7);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_cp`
--

DROP TABLE IF EXISTS `users_cp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_cp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CNP` varchar(13) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f419h7jw9h8li4q2l8yjvqste` (`CNP`),
  UNIQUE KEY `UK_7mgotvk2ers37752k3w52s2xq` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_cp`
--

LOCK TABLES `users_cp` WRITE;
/*!40000 ALTER TABLE `users_cp` DISABLE KEYS */;
INSERT INTO `users_cp` VALUES (1,'N/A0000000000','N/A','domentiimaxim@yahoo.com','N/A','N/A','07WfylTarjY=','N/A','admin');
/*!40000 ALTER TABLE `users_cp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_hb`
--

DROP TABLE IF EXISTS `users_hb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_hb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CNP` varchar(13) NOT NULL,
  `address` varchar(255) NOT NULL,
  `auth_type` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mf3xdb760i9c68e6xitl1exju` (`CNP`),
  UNIQUE KEY `UK_ip1yx662d25ubv1qdx079na1n` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_hb`
--

LOCK TABLES `users_hb` WRITE;
/*!40000 ALTER TABLE `users_hb` DISABLE KEYS */;
INSERT INTO `users_hb` VALUES (1,'1921029111111','Romania, Bucuresti, Sector 6, Aleea Bradisului, nr 1, sc 2',0,'domentiimaxim@yahoo.com','Maxim','Domentii','zlvuQR/E/jE=','+711111111','client1'),(2,'1111111111111','test test test',0,'test@test.com','Client2','Client2','LThs/uK1SaY=','+400000000000','client2');
/*!40000 ALTER TABLE `users_hb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-16  1:55:20
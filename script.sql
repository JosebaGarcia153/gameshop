-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: gameshop
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.18.04.1

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
-- Current Database: `gameshop`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `gameshop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `gameshop`;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `categories_UN` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (3,'Demonic Musical'),(2,'Meep Meep'),(1,'Pew Pew'),(5,'Punch it \'til it dies'),(7,'sgsdghsdfg'),(6,'test2'),(4,'Wack-a-Monsta');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `count_games`
--

DROP TABLE IF EXISTS `count_games`;
/*!50001 DROP VIEW IF EXISTS `count_games`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `count_games` AS SELECT 
 1 AS `user_id`,
 1 AS `total`,
 1 AS `approved`,
 1 AS `pending`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `games` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(115) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `category_id` int(11) NOT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `approval_date` datetime DEFAULT NULL COMMENT 'if it is null, it is not visible on the public side of the site, it has to be approved by an admin',
  `image` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `games_UN` (`name`),
  KEY `FK_categories` (`category_id`),
  KEY `FK_users` (`user_id`),
  CONSTRAINT `FK_categories` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FK_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
INSERT INTO `games` VALUES (2,'Modern Weefire',60.99,1,'2020-05-13 12:34:17',NULL,'',1),(3,'WaffleField',59.99,1,'2020-03-13 12:34:17',NULL,'',1),(4,'BureiBuru',34.99,5,'2020-04-13 12:34:17',NULL,'',1),(5,'Game: The Game',99.99,1,'2018-07-13 12:34:17',NULL,'',1),(6,'Forza Car Crash Edition',69.99,2,'2020-07-13 12:34:17',NULL,'',1),(7,'Modern Weefire 2',69.99,1,'2020-07-13 12:34:17',NULL,'',1),(8,'DOOT',32.00,3,'2020-07-13 12:34:17','2020-08-29 19:44:46','',1),(9,'DOOT ETERNAL',59.99,3,'2020-07-13 12:34:17','2020-07-18 12:34:17','',2),(10,'Monsta Waldo',59.99,4,'2020-07-13 12:34:17',NULL,'',1),(11,'WaffleField 2',2.00,1,'2020-07-13 12:34:17',NULL,'',1),(12,'WaffleField 3',3.00,1,'2020-07-13 12:34:17',NULL,'',1),(14,'WaffleField 4',59.99,1,'2020-07-13 12:34:17','2020-08-29 19:49:50','',1),(16,'Super Mario Bros 3',79.99,7,'2020-07-21 13:52:46',NULL,'https://picsum.photos/100/100',2),(17,'asfafadfaf',60.00,3,'2020-09-07 10:55:38',NULL,'images/racoon.png',2);
/*!40000 ALTER TABLE `games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rol_UN` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (2,'admin'),(1,'user');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  `rol_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_UN` (`name`),
  KEY `FK_rol` (`rol_id`),
  CONSTRAINT `FK_rol` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','https://picsum.photos/50',2),(2,'user','user','https://picsum.photos/50',1),(3,'Magee','user','https://picsum.photos/50',1),(4,'Kermit','user','https://picsum.photos/50',1),(5,'Oleg','user','https://picsum.photos/50',1),(6,'Cassidy','user','https://picsum.photos/50',1),(7,'Aaron','user','https://picsum.photos/50',1),(8,'Hedwig','user','https://picsum.photos/50',1),(9,'Alan','user','https://picsum.photos/50',1),(10,'Emery','user','https://picsum.photos/50',1),(11,'Macy','user','https://picsum.photos/50',1),(12,'Walker','user','https://picsum.photos/50',1),(13,'Xavier','user','https://picsum.photos/50',1),(14,'Dale','user','https://picsum.photos/50',1),(15,'Cole','user','https://picsum.photos/50',1),(16,'Zephr','user','https://picsum.photos/50',1),(17,'Charissa','user','https://picsum.photos/50',1),(18,'Lance','user','https://picsum.photos/50',1),(19,'David','user','https://picsum.photos/50',1),(20,'Ezra','user','https://picsum.photos/50',1),(21,'Quinn','user','https://picsum.photos/50',1),(22,'Cherokee','user','https://picsum.photos/50',1),(23,'Cody','user','https://picsum.photos/50',1),(24,'Katelyn','user','https://picsum.photos/50',1),(25,'Carly','user','https://picsum.photos/50',1),(26,'Asher','user','https://picsum.photos/50',1),(27,'Larissa','user','https://picsum.photos/50',1),(28,'Teegan','user','https://picsum.photos/50',1),(29,'Courtney','user','https://picsum.photos/50',1),(30,'Rhona','user','https://picsum.photos/50',1),(31,'Caldwell','user','https://picsum.photos/50',1),(32,'Wendy','user','https://picsum.photos/50',1),(33,'Rajah','user','https://picsum.photos/50',1),(34,'Evan','user','https://picsum.photos/50',1),(35,'Danielle','user','https://picsum.photos/50',1),(36,'Camilla','user','https://picsum.photos/50',1),(37,'Tana','user','https://picsum.photos/50',1),(38,'Lunea','user','https://picsum.photos/50',1),(39,'Leslie','user','https://picsum.photos/50',1),(40,'Cruz','user','https://picsum.photos/50',1),(41,'Eden','user','https://picsum.photos/50',1),(42,'Byron','user','https://picsum.photos/50',1),(43,'Ryder','user','https://picsum.photos/50',1),(44,'Ronan','user','https://picsum.photos/50',1),(45,'Norman','user','https://picsum.photos/50',1),(46,'Whoopi','user','https://picsum.photos/50',1),(47,'Honorato','user','https://picsum.photos/50',1),(48,'Tallulah','user','https://picsum.photos/50',1),(49,'Gloria','user','https://picsum.photos/50',1),(50,'Ira','user','https://picsum.photos/50',1),(51,'Barry','user','https://picsum.photos/50',1),(52,'Callum','user','https://picsum.photos/50',1),(53,'Zia','user','https://picsum.photos/50',1),(54,'Logan','user','https://picsum.photos/50',1),(55,'Xena','user','https://picsum.photos/50',1),(56,'Paul','user','https://picsum.photos/50',1),(57,'Elmo','user','https://picsum.photos/50',1),(58,'Gil','user','https://picsum.photos/50',1),(59,'Eliana','user','https://picsum.photos/50',1),(60,'Ursula','user','https://picsum.photos/50',1),(61,'Colby','user','https://picsum.photos/50',1),(62,'Basil','user','https://picsum.photos/50',1),(63,'Justine','user','https://picsum.photos/50',1),(64,'Craig','user','https://picsum.photos/50',1),(65,'Candace','user','https://picsum.photos/50',1),(66,'Burton','user','https://picsum.photos/50',1),(67,'Sean','user','https://picsum.photos/50',1),(68,'Urielle','user','https://picsum.photos/50',1),(69,'Tanek','user','https://picsum.photos/50',1),(70,'Guy','user','https://picsum.photos/50',1),(71,'Uta','user','https://picsum.photos/50',1),(72,'Desiree','user','https://picsum.photos/50',1),(73,'Kenneth','user','https://picsum.photos/50',1),(74,'Talon','user','https://picsum.photos/50',1),(75,'Yuli','user','https://picsum.photos/50',1),(76,'Jennifer','user','https://picsum.photos/50',1),(77,'Camille','user','https://picsum.photos/50',1),(78,'Amos','user','https://picsum.photos/50',1),(79,'Fallon','user','https://picsum.photos/50',1),(80,'Micah','user','https://picsum.photos/50',1),(81,'Darius','user','https://picsum.photos/50',1),(82,'Audra','user','https://picsum.photos/50',1),(83,'Jonas','user','https://picsum.photos/50',1),(85,'Daryl','user','https://picsum.photos/50',1),(86,'Xyla','user','https://picsum.photos/50',1),(87,'Caesar','user','https://picsum.photos/50',1),(88,'Carolyn','user','https://picsum.photos/50',1),(89,'Wanda','user','https://picsum.photos/50',1),(90,'Porter','user','https://picsum.photos/50',1),(91,'Jordan','user','https://picsum.photos/50',1),(92,'Cadman','user','https://picsum.photos/50',1),(93,'Hiram','user','https://picsum.photos/50',1),(95,'Oscar','user','https://picsum.photos/50',1),(96,'Levi','user','https://picsum.photos/50',1),(97,'Bell','user','https://picsum.photos/50',1),(98,'Shoshana','user','https://picsum.photos/50',1),(99,'Lani','user','https://picsum.photos/50',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

DELIMITER //
CREATE DEFINER=`debian-sys-maint`@`localhost` FUNCTION `fn_ejemplo_case`(pLanguage VARCHAR(2)) RETURNS varchar(50) CHARSET latin1
BEGIN
	
	DECLARE salute VARCHAR(50) DEFAULT '';
	/*
	 * CASE es como el switch the java, cada WHEN dice cual es el valor para activar ese CASE, SET es lo que hace cuando se activa
	*/
	CASE pLanguage

		WHEN 'es' THEN BEGIN
			SET salute = 'Bienvenido';
		END;
	
		WHEN 'en' THEN BEGIN
			SET salute = 'Welcome';
		END;
		/*
		 * ELSE es es CASE que se activa si el resto no se activan
		*/
		ELSE BEGIN
			SET salute = 'Sorry, I dont recognice that language';
		END;
	
	END CASE;

RETURN salute;
	
END ;;
DELIMITER //

DELIMITER //
CREATE DEFINER=`debian-sys-maint`@`localhost` FUNCTION `fn_salute`(pName VARCHAR(100)) RETURNS varchar(100) CHARSET latin1
BEGIN
	
	RETURN CONCAT ('Hello ', pName);
	
END ;;
DELIMITER //

DELIMITER //
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_category_by_id`(IN pId INT)
BEGIN
	
	SELECT id, name FROM categories WHERE id = pId LIMIT 500;
	
END ;;
DELIMITER //

DELIMITER //
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_category_delete`(IN pId INT)
BEGIN
	
	DELETE FROM categories WHERE id = pId;
	
END ;;
DELIMITER //

DELIMITER //
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_category_insert`(IN pName VARCHAR(100), OUT pGeneratedId INT)
BEGIN
	
	INSERT INTO categories (name) VALUES (TRIM(pName));

	SET pGeneratedId = LAST_INSERT_ID(); 
	
END ;;
DELIMITER //

DELIMITER //
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_category_list`()
BEGIN
	
	SELECT id, name FROM categories ORDER BY name ASC LIMIT 500;
	
END ;;
DELIMITER //

DELIMITER //
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_category_update`(IN pName VARCHAR(100), IN pId INT)
BEGIN
	
	UPDATE categories SET name = pName WHERE id = pId;

END ;;
DELIMITER //


--
-- Current Database: `gameshop`
--

USE `gameshop`;

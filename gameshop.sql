-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: gameshop
-- ------------------------------------------------------
-- Server version	5.7.31-0ubuntu0.18.04.1

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
  `birthday` date DEFAULT NULL,
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
INSERT INTO `users` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','https://picsum.photos/50',2,NULL),(2,'user','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(3,'Magee','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(4,'Kermit','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(5,'Oleg','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(6,'Cassidy','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(7,'Aaron','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(8,'Hedwig','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(9,'Alan','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(10,'Emery','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(11,'Macy','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(12,'Walker','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(13,'Xavier','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(14,'Dale','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(15,'Cole','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(16,'Zephr','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(17,'Charissa','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(18,'Lance','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(19,'David','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(20,'Ezra','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(21,'Quinn','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(22,'Cherokee','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(23,'Cody','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(24,'Katelyn','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(25,'Carly','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(26,'Asher','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(27,'Larissa','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(28,'Teegan','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(29,'Courtney','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(30,'Rhona','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(31,'Caldwell','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(32,'Wendy','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(33,'Rajah','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(34,'Evan','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(35,'Danielle','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(36,'Camilla','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(37,'Tana','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(38,'Lunea','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(39,'Leslie','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(40,'Cruz','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(41,'Eden','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(42,'Byron','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(43,'Ryder','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(44,'Ronan','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(45,'Norman','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(46,'Whoopi','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(47,'Honorato','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(48,'Tallulah','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(49,'Gloria','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(50,'Ira','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(51,'Barry','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(52,'Callum','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(53,'Zia','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(54,'Logan','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(55,'Xena','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(56,'Paul','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(57,'Elmo','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(58,'Gil','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(59,'Eliana','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(60,'Ursula','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(61,'Colby','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(62,'Basil','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(63,'Justine','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(64,'Craig','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(65,'Candace','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(66,'Burton','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(67,'Sean','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(68,'Urielle','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(69,'Tanek','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(70,'Guy','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(71,'Uta','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(72,'Desiree','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(73,'Kenneth','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(74,'Talon','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(75,'Yuli','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(76,'Jennifer','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(77,'Camille','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(78,'Amos','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(79,'Fallon','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(80,'Micah','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(81,'Darius','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(82,'Audra','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(83,'Jonas','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(85,'Daryl','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(86,'Xyla','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(87,'Caesar','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(88,'Carolyn','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(89,'Wanda','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(90,'Porter','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(91,'Jordan','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(92,'Cadman','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(93,'Hiram','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(95,'Oscar','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(96,'Levi','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(97,'Bell','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(98,'Shoshana','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL),(99,'Lani','ee11cbb19052e40b07aac0ca060c23ee','https://picsum.photos/50',1,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;


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
-- Dumping routines for database 'gameshop'
--
/*!50003 DROP FUNCTION IF EXISTS `fn_ejemplo_case` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ 


DELIMITER $$
CREATE PROCEDURE `pa_category_by_id`(IN pId INT)
BEGIN
	
	SELECT id, name FROM categories WHERE id = pId LIMIT 500;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_category_delete` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER $$
CREATE PROCEDURE `pa_category_delete`(IN pId INT)
BEGIN
	
	DELETE FROM categories WHERE id = pId;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_category_insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER $$
CREATE PROCEDURE `pa_category_insert`(IN pName VARCHAR(100), OUT pGeneratedId INT)
BEGIN
	
	INSERT INTO categories (name) VALUES (TRIM(pName));

	SET pGeneratedId = LAST_INSERT_ID(); 
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_category_list` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER $$
CREATE PROCEDURE `pa_category_list`()
BEGIN
	
	SELECT id, name FROM categories ORDER BY name ASC LIMIT 500;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_category_update` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER $$
CREATE PROCEDURE `pa_category_update`(IN pName VARCHAR(100), IN pId INT)
BEGIN
	
	UPDATE categories SET name = pName WHERE id = pId;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Current Database: `gameshop`
--

USE `gameshop`;

--
-- Final view structure for view `count_games`
--

/*!50001 DROP VIEW IF EXISTS `count_games`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`debian-sys-maint`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `count_games` AS select `g`.`user_id` AS `user_id`,count(`g`.`user_id`) AS `total`,count(`g`.`approval_date`) AS `approved`,sum(isnull(`g`.`approval_date`)) AS `pending` from `games` `g` group by `g`.`user_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-07  8:34:13

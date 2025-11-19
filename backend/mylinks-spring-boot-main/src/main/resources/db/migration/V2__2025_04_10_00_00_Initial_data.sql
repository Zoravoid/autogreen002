-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: localhost    Database: mylinks_db
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'Programming','A category for learning resourses used for programming',1),
                                (2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'Education', 'A category used for teaching',1);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `categories_owners`
--

LOCK TABLES `categories_owners` WRITE;
/*!40000 ALTER TABLE `categories_owners` DISABLE KEYS */;
INSERT INTO `categories_owners` VALUES (1,1,1),(2,2,1),(3,2,2);
/*!40000 ALTER TABLE `categories_owners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `group_categories`
--

LOCK TABLES `group_categories` WRITE;
/*!40000 ALTER TABLE `group_categories` DISABLE KEYS */;
INSERT INTO `group_categories` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,1),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,2,2);
/*!40000 ALTER TABLE `group_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `group_links`
--

LOCK TABLES `group_links` WRITE;
/*!40000 ALTER TABLE `group_links` DISABLE KEYS */;
INSERT INTO `group_links` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,1),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,2),(3,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,2,3);
/*!40000 ALTER TABLE `group_links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `groups_members`
--

LOCK TABLES `groups_members` WRITE;
/*!40000 ALTER TABLE `groups_members` DISABLE KEYS */;
INSERT INTO `groups_members` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,1),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,2),(3,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,2,2);
/*!40000 ALTER TABLE `groups_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `link_categories`
--

LOCK TABLES `link_categories` WRITE;
/*!40000 ALTER TABLE `link_categories` DISABLE KEYS */;
INSERT INTO `link_categories` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,1),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,2),(3,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,2,3);
/*!40000 ALTER TABLE `link_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `links`
--

LOCK TABLES `links` WRITE;
/*!40000 ALTER TABLE `links` DISABLE KEYS */;
INSERT INTO `links` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'2025-04-10','The largest web Q&A platform for developers','Stack Overflow','https://stackoverflow.com',0,1),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'2025-04-10','Popular code hosting platform','GitHub','https://github.com',0,1),(3,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'2025-04-10','Free online courses','Coursera','https://coursera.org',0,2),(4,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'2025-04-10','Video tutorials platform','YouTube','https://youtube.com',0,2);
/*!40000 ALTER TABLE `links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `login_user_statuses`
--

LOCK TABLES `login_user_statuses` WRITE;
/*!40000 ALTER TABLE `login_user_statuses` DISABLE KEYS */;
INSERT INTO `login_user_statuses` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'2025-04-10',_binary '\0','REGISTERED'),
                                         (2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'2025-04-10',_binary '','ACTIVE'),
                                         (3,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'2025-04-10',_binary '','INACTIVE');
/*!40000 ALTER TABLE `login_user_statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,NULL,'2025-04-10 09:39:27.000000','Welcome to MyLinks!',0),(2,NULL,'2025-04-10 09:39:27.000000','You have been added to Developers group',1);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notifications_user_receiver`
--

LOCK TABLES `notifications_user_receiver` WRITE;
/*!40000 ALTER TABLE `notifications_user_receiver` DISABLE KEYS */;
INSERT INTO `notifications_user_receiver` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,2),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,2,2);
/*!40000 ALTER TABLE `notifications_user_receiver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notifications_user_sender`
--

LOCK TABLES `notifications_user_sender` WRITE;
/*!40000 ALTER TABLE `notifications_user_sender` DISABLE KEYS */;
INSERT INTO `notifications_user_sender` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,1),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,2,1);
/*!40000 ALTER TABLE `notifications_user_sender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `owner_links`
--

LOCK TABLES `owner_links` WRITE;
/*!40000 ALTER TABLE `owner_links` DISABLE KEYS */;
INSERT INTO `owner_links` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,1),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,2,1),(3,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,3,2),(4,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,4,2);
/*!40000 ALTER TABLE `owner_links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'USERS','READ'),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'USERS','WRITE'),(3,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'LINKS','READ'),(4,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'LINKS','WRITE');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `permissions_roles`
--

LOCK TABLES `permissions_roles` WRITE;
/*!40000 ALTER TABLE `permissions_roles` DISABLE KEYS */;
INSERT INTO `permissions_roles` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,1),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,2,1),(3,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,3,1),(4,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,4,1),(5,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,3,2),(6,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,4,2);
/*!40000 ALTER TABLE `permissions_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `person_details`
--

LOCK TABLES `person_details` WRITE;
/*!40000 ALTER TABLE `person_details` DISABLE KEYS */;
INSERT INTO `person_details` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'Moldova','Desktop','Admin','127.0.0.1','User','Linux','+373 22 123456'),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'Romania','Mobile','Test','192.168.1.1','User','Android','+40 712 345678');
/*!40000 ALTER TABLE `person_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role_users`
--

LOCK TABLES `role_users` WRITE;
/*!40000 ALTER TABLE `role_users` DISABLE KEYS */;
INSERT INTO `role_users` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,1),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,2,2);
/*!40000 ALTER TABLE `role_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'ADMIN'),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_groups`
--

LOCK TABLES `user_groups` WRITE;
/*!40000 ALTER TABLE `user_groups` DISABLE KEYS */;
INSERT INTO `user_groups` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'Developers',1),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'Students',2);
/*!40000 ALTER TABLE `user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'2025-01-01','2025-04-10','admin@mylinks.com',_binary '','$2a$10$5PxR.fW6Zx7iPkIrn3cZl.ULAKwZgEW5xVCSh3zvV5yvEEznT1wVq','admin', 2, 1),
                           (2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,'2025-01-15','2025-04-10','user@mylinks.com',_binary '\0','$2a$10$Q5TvF9XA9E4MLvS2D4jcmuQUGTwbxg42p5T8VqQXLRH.9fWj.5wgS','user', 1, 2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Sensors
--

-- Temperature
LOCK TABLES `heat` WRITE;
/*!40000 ALTER TABLE `heat` DISABLE KEYS */;
INSERT INTO `heat` VALUES (1,1,1.101,'2025-04-10 09:39:27.000000'),
                          (2,2,49.309, '2025-04-10 09:39:27.000000');
/*!40000 ALTER TABLE `heat` ENABLE KEYS */;
UNLOCK TABLES;

-- Co2
LOCK TABLES `co2` WRITE;
/*!40000 ALTER TABLE `co2` DISABLE KEYS */;
INSERT INTO `co2` VALUES (1,1,70.101,'2025-04-10 09:39:27.000000'),
                          (2,2,19.309, '2025-04-10 09:39:27.000000');
/*!40000 ALTER TABLE `co2` ENABLE KEYS */;
UNLOCK TABLES;

--Humidity
LOCK TABLES `humidity` WRITE;
/*!40000 ALTER TABLE `humidity` DISABLE KEYS */;
INSERT INTO `humidity` VALUES (1,1,55.101,'2025-04-10 09:39:27.000000'),
                          (2,2,39.309, '2025-04-10 09:39:27.000000');
/*!40000 ALTER TABLE `humidity` ENABLE KEYS */;
UNLOCK TABLES;

--Moisture
LOCK TABLES `moisture` WRITE;
/*!40000 ALTER TABLE `moisture` DISABLE KEYS */;
INSERT INTO `moisture` VALUES (1,1,66.101,'2025-04-10 09:39:27.000000'),
                          (2,2,99.309, '2025-04-10 09:39:27.000000');
/*!40000 ALTER TABLE `moisture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `userstatus_users`
--

-- LOCK TABLES `userstatus_users` WRITE;
-- /*!40000 ALTER TABLE `userstatus_users` DISABLE KEYS */;
-- INSERT INTO `userstatus_users` VALUES (1,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,1,1),(2,'2025-04-10 09:39:27.000000','2025-04-10 09:39:27.000000',0,2,1);
-- /*!40000 ALTER TABLE `userstatus_users` ENABLE KEYS */;
-- UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-10  9:40:42

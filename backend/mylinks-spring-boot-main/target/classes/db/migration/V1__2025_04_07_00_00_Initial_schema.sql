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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `owner_creator_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh3ua9l2k7q7chim552o5iuw2x` (`owner_creator_id`),
  CONSTRAINT `FKh3ua9l2k7q7chim552o5iuw2x` FOREIGN KEY (`owner_creator_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categories_owners`
--

DROP TABLE IF EXISTS `categories_owners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories_owners` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` bigint NOT NULL,
  `owner_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrihqcouq5lcr69265jenxc5e3` (`category_id`),
  KEY `FKkf5aud5ila75aihanwyhgv436` (`owner_id`),
  CONSTRAINT `FKkf5aud5ila75aihanwyhgv436` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKrihqcouq5lcr69265jenxc5e3` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group_categories`
--

DROP TABLE IF EXISTS `group_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `category_id` bigint NOT NULL,
  `group_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb7131fnseu12feuom5t580p0o` (`category_id`),
  KEY `FKavl7a4pvxec07uwpn0c98oljj` (`group_id`),
  CONSTRAINT `FKavl7a4pvxec07uwpn0c98oljj` FOREIGN KEY (`group_id`) REFERENCES `user_groups` (`id`),
  CONSTRAINT `FKb7131fnseu12feuom5t580p0o` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group_links`
--

DROP TABLE IF EXISTS `group_links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_links` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `group_id` bigint NOT NULL,
  `link_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhfhyw6jd09y4p9ct9tafqaxfa` (`group_id`),
  KEY `FK86i110nod7eqvcjhsfesggw50` (`link_id`),
  CONSTRAINT `FK86i110nod7eqvcjhsfesggw50` FOREIGN KEY (`link_id`) REFERENCES `links` (`id`),
  CONSTRAINT `FKhfhyw6jd09y4p9ct9tafqaxfa` FOREIGN KEY (`group_id`) REFERENCES `user_groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `groups_members`
--

DROP TABLE IF EXISTS `groups_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groups_members` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `group_id` bigint NOT NULL,
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkxorhb7bumckrrh2s2rqcfau2` (`group_id`),
  KEY `FK9emikbujf5ttt9panicrs5s5f` (`member_id`),
  CONSTRAINT `FK9emikbujf5ttt9panicrs5s5f` FOREIGN KEY (`member_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKkxorhb7bumckrrh2s2rqcfau2` FOREIGN KEY (`group_id`) REFERENCES `user_groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `link_categories`
--

DROP TABLE IF EXISTS `link_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `category_id` bigint NOT NULL,
  `link_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjv5u0guj7jm81yhqlo1beaoe7` (`category_id`),
  KEY `FKlh89460af6wng0m9921r56586` (`link_id`),
  CONSTRAINT `FKjv5u0guj7jm81yhqlo1beaoe7` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKlh89460af6wng0m9921r56586` FOREIGN KEY (`link_id`) REFERENCES `links` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `links`
--

DROP TABLE IF EXISTS `links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `links` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `date_last_accessed` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `url` varchar(2000) DEFAULT NULL,
  `visibility` int DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1crycnbc3ghw9k40e8ia0a2du` (`owner_id`),
  CONSTRAINT `FK1crycnbc3ghw9k40e8ia0a2du` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `login_user_statuses`
--

DROP TABLE IF EXISTS `login_user_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_user_statuses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `date_last_accessed` date NOT NULL,
  `restricted` bit(1) DEFAULT NULL,
  `status_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_notification_read` datetime(6) DEFAULT NULL,
  `date_notification_sent` datetime(6) DEFAULT NULL,
  `message` varchar(45) DEFAULT NULL,
  `priority` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notifications_user_receiver`
--

DROP TABLE IF EXISTS `notifications_user_receiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications_user_receiver` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `notification_id` bigint NOT NULL,
  `user_receiver_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr1evy87nontntmu4826foykqx` (`notification_id`),
  KEY `FKhcn9mg462r2ufhk7tok6t1gvm` (`user_receiver_id`),
  CONSTRAINT `FKhcn9mg462r2ufhk7tok6t1gvm` FOREIGN KEY (`user_receiver_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKr1evy87nontntmu4826foykqx` FOREIGN KEY (`notification_id`) REFERENCES `notifications` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notifications_user_sender`
--

DROP TABLE IF EXISTS `notifications_user_sender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications_user_sender` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `notification_id` bigint NOT NULL,
  `user_sender_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK28i24hfnpb6b4e2k485sncm9t` (`notification_id`),
  KEY `FKpu918yyu8nbqpa60ct34gle5h` (`user_sender_id`),
  CONSTRAINT `FK28i24hfnpb6b4e2k485sncm9t` FOREIGN KEY (`notification_id`) REFERENCES `notifications` (`id`),
  CONSTRAINT `FKpu918yyu8nbqpa60ct34gle5h` FOREIGN KEY (`user_sender_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `owner_links`
--

DROP TABLE IF EXISTS `owner_links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `owner_links` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `link_id` bigint NOT NULL,
  `owner_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhoo2nxv856a70k29pb492yj1c` (`link_id`),
  KEY `FKoou9ld000h9weoryykx33dv3l` (`owner_id`),
  CONSTRAINT `FKhoo2nxv856a70k29pb492yj1c` FOREIGN KEY (`link_id`) REFERENCES `links` (`id`),
  CONSTRAINT `FKoou9ld000h9weoryykx33dv3l` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `resource` varchar(45) DEFAULT NULL,
  `type_of_permission` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `permissions_roles`
--

DROP TABLE IF EXISTS `permissions_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `permission_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKff6bcp6bbaup2irutar3dfaks` (`permission_id`),
  KEY `FK9j7vx1vojmoa6rs21eggd46xn` (`role_id`),
  CONSTRAINT `FK9j7vx1vojmoa6rs21eggd46xn` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKff6bcp6bbaup2irutar3dfaks` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person_details`
--

DROP TABLE IF EXISTS `person_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `device` varchar(255) DEFAULT NULL,
  `first_name` varchar(20) NOT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `last_name` varchar(20) NOT NULL,
  `operating_system` varchar(30) DEFAULT NULL,
  `phone_number` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_users`
--

DROP TABLE IF EXISTS `role_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `role_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKppcayeo6xulu3covhr279whq5` (`role_id`),
  KEY `FKf685pkoo051sx8gk6o3l6116m` (`user_id`),
  CONSTRAINT `FKf685pkoo051sx8gk6o3l6116m` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKppcayeo6xulu3covhr279whq5` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `title` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_groups`
--

DROP TABLE IF EXISTS `user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_groups` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `title` varchar(25) DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKik51mjx3t1go2o5fnu27rvhap` (`owner_id`),
  CONSTRAINT `FKik51mjx3t1go2o5fnu27rvhap` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `date_created_account` date NOT NULL,
  `date_last_accessed` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `online` bit(1) NOT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `user_status_id` bigint NOT NULL,
  `person_details_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FK120mv0onp97k8058oeuraqtsl` (`person_details_id`),
  CONSTRAINT `FK120mv0onp97k8058oeuraqtsl` FOREIGN KEY (`person_details_id`) REFERENCES `person_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKexllqqgiw0xx85h1ogld9t3g1` FOREIGN KEY (`user_status_id`) REFERENCES `login_user_statuses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Sensors
--

-- Temperature

DROP TABLE IF EXISTS `heat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heat` (
    `id` bigint NOT NULL,
    `device_id` bigint NOT NULL,
    `heat_val` float NOT NULL,
    `time_stamp` datetime(6) NOT NULL,
    `created_date` datetime(6) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Co2

DROP TABLE IF EXISTS `co2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `co2` (
    `id` bigint NOT NULL,
    `device_id` bigint NOT NULL,
    `co2_val` float NOT NULL,
    `time_stamp` datetime(6) NOT NULL,
    `created_date` datetime(6) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Humidity

DROP TABLE IF EXISTS `humidity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `humidity` (
    `id` bigint NOT NULL,
    `device_id` bigint NOT NULL,
    `humidity_val` float NOT NULL,
    `time_stamp` datetime(6) NOT NULL,
    `created_date` datetime(6) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Moisture

DROP TABLE IF EXISTS `moisture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moisture` (
    `id` bigint NOT NULL,
    `device_id` bigint NOT NULL,
    `moisture_val` float NOT NULL,
    `time_stamp` datetime(6) NOT NULL,
    `created_date` datetime(6) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Light

DROP TABLE IF EXISTS `light`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `light` (
    `id` bigint NOT NULL,
    `device_id` bigint NOT NULL,
    `light_val` float NOT NULL,
    `time_stamp` datetime(6) NOT NULL,
    `created_date` datetime(6) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `userstatus_users`
--

-- DROP TABLE IF EXISTS `userstatus_users`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `userstatus_users` (
--   `id` bigint NOT NULL AUTO_INCREMENT,
--   `created_date` datetime(6) NOT NULL,
--   `last_modified_date` datetime(6) DEFAULT NULL,
--   `version` bigint DEFAULT NULL,
--   `user_id` bigint NOT NULL,
--   `user_status_id` bigint NOT NULL,
--   PRIMARY KEY (`id`),
--   KEY `FKfstrc0rnsdxrfd13lkvbl59d4` (`user_id`),
--   KEY `FK146rxxfr9i2vntsd9wvi73sxn` (`user_status_id`),
--   CONSTRAINT `FK146rxxfr9i2vntsd9wvi73sxn` FOREIGN KEY (`user_status_id`) REFERENCES `login_user_statuses` (`id`),
--   CONSTRAINT `FKfstrc0rnsdxrfd13lkvbl59d4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-07 15:21:51

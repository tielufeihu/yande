/*
SQLyog Trial v13.1.8 (64 bit)
MySQL - 8.0.19 : Database - yande
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yande` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `yande`;

/*Table structure for table `img_clazz` */

DROP TABLE IF EXISTS `img_clazz`;

CREATE TABLE `img_clazz` (
  `clazz_id` int NOT NULL AUTO_INCREMENT,
  `clazz_name` varchar(128) DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `master` int DEFAULT NULL,
  PRIMARY KEY (`clazz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `img_collection` */

DROP TABLE IF EXISTS `img_collection`;

CREATE TABLE `img_collection` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `cn_name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `img_tag` */

DROP TABLE IF EXISTS `img_tag`;

CREATE TABLE `img_tag` (
  `img_id` int DEFAULT NULL,
  `tag_id` int DEFAULT NULL,
  KEY `img_tag_ibfk_1` (`tag_id`),
  KEY `imgidyj` (`img_id`),
  CONSTRAINT `img_tag_ibfk_1` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `imgidyj` FOREIGN KEY (`img_id`) REFERENCES `imginfo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `imginfo` */

DROP TABLE IF EXISTS `imginfo`;

CREATE TABLE `imginfo` (
  `id` int NOT NULL,
  `path` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `img` mediumblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) DEFAULT NULL,
  `role_grade` int DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `role_authority` */

DROP TABLE IF EXISTS `role_authority`;

CREATE TABLE `role_authority` (
  `role_id` int DEFAULT NULL,
  `authority_id` int DEFAULT NULL,
  KEY `user_id` (`authority_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `role_authority_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `message` varchar(64) DEFAULT NULL,
  `cn_name` varchar(64) DEFAULT NULL,
  `clazz` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `img_count` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `NameSy` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=64110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `account` int NOT NULL AUTO_INCREMENT,
  `password` varchar(128) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  KEY `account` (`account`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11409 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

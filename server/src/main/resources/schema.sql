DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `phone_id` int(11) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`phone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `admin_manager`;
CREATE TABLE `admin_manager` (
  `admin_admin_id` bigint(20) NOT NULL,
  `managers_manager_id` bigint(20) NOT NULL,
  UNIQUE KEY `managers_manager_id` (`managers_manager_id`),
  KEY `FK6C49FABDBE8944F3` (`admin_admin_id`),
  KEY `FK6C49FABD6043967C` (`managers_manager_id`),
  CONSTRAINT `FK6C49FABD6043967C` FOREIGN KEY (`managers_manager_id`) REFERENCES `manager` (`manager_id`),
  CONSTRAINT `FK6C49FABDBE8944F3` FOREIGN KEY (`admin_admin_id`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `manager_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `admin_id` bigint(20) NOT NULL,
  PRIMARY KEY (`manager_id`),
  KEY `FK31C90FADA8ECCE03` (`admin_id`),
  CONSTRAINT `FK31C90FADA8ECCE03` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `order_m`;
CREATE TABLE `order_m` (
  `order_id` int(11) NOT NULL,
  `car_registration_sign` varchar(255) DEFAULT NULL,
  `estimation_time` time DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `manager_id` bigint(20) NOT NULL,
  `taxi_id` bigint(20) NOT NULL,
  `phone_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKB80CF7BC93378931` (`taxi_id`),
  KEY `FKB80CF7BCA246358E` (`phone_id`),
  KEY `FKB80CF7BC2620303` (`manager_id`),
  CONSTRAINT `FKB80CF7BC2620303` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`manager_id`),
  CONSTRAINT `FKB80CF7BC93378931` FOREIGN KEY (`taxi_id`) REFERENCES `taxi` (`taxi_id`),
  CONSTRAINT `FKB80CF7BCA246358E` FOREIGN KEY (`phone_id`) REFERENCES `user` (`phone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `taxi`;
CREATE TABLE `taxi` (
  `taxi_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `car_class` varchar(255) DEFAULT NULL,
  `car_registration_sign` varchar(255) DEFAULT NULL,
  `is_free` tinyint(1) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`taxi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
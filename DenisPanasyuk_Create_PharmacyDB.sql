#CREATE DATABASE `pharmacy`;
#use `pharmacy`;

CREATE TABLE `drug` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `is_prescription_required` tinyint(4) NOT NULL DEFAULT '0',
  `price` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` char(64) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `role` enum('client','doctor','admin') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `drug_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `status` enum('new','at_work','completed','rejected') NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_drug_order_user1_idx` (`user_id`),
  CONSTRAINT `fk_drug_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `manufacturer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `manufacturer_drug` (
  `id` int(11) unsigned NOT NULL,
  `manufacturer_id` int(11) unsigned NOT NULL,
  `drug_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_manufacturer_drug_manufacturer1_idx` (`manufacturer_id`),
  KEY `fk_manufacturer_drug_drug1_idx` (`drug_id`),
  CONSTRAINT `fk_manufacturer_drug_drug1` FOREIGN KEY (`drug_id`) REFERENCES `drug` (`id`),
  CONSTRAINT `fk_manufacturer_drug_manufacturer1` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `drug_order_details` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number_of_drugs` smallint(11) unsigned NOT NULL,
  `dosage` smallint(11) unsigned NOT NULL,
  `drug_id` int(11) unsigned NOT NULL,
  `drug_order_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_drug_order_details_drug1_idx` (`drug_id`),
  KEY `fk_drug_order_details_drug_order1_idx` (`drug_order_id`),
  CONSTRAINT `fk_drug_order_details_drug1` FOREIGN KEY (`drug_id`) REFERENCES `drug` (`id`),
  CONSTRAINT `fk_drug_order_details_drug_order1` FOREIGN KEY (`drug_order_id`) REFERENCES `drug_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `release_form` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `release_form_drug` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `release_form_id` int(11) unsigned NOT NULL,
  `drug_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_release_form_drug_release_form1_idx` (`release_form_id`),
  KEY `fk_release_form_drug_drug1_idx` (`drug_id`),
  CONSTRAINT `fk_release_form_drug_drug1` FOREIGN KEY (`drug_id`) REFERENCES `drug` (`id`),
  CONSTRAINT `fk_release_form_drug_release_form1` FOREIGN KEY (`release_form_id`) REFERENCES `release_form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `prescription` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `date of issue` datetime(6) NOT NULL,
  `validity date` datetime(6) NOT NULL,
  `drug_id` int(11) unsigned NOT NULL,
  `user_from` int(11) unsigned NOT NULL,
  `user_to` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_prescription_drug1_idx` (`drug_id`),
  KEY `fk_prescription_user1_idx` (`user_from`),
  KEY `fk_prescription_user2_idx` (`user_to`),
  CONSTRAINT `fk_prescription_drug1` FOREIGN KEY (`drug_id`) REFERENCES `drug` (`id`),
  CONSTRAINT `fk_prescription_user1` FOREIGN KEY (`user_from`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_prescription_user2` FOREIGN KEY (`user_to`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


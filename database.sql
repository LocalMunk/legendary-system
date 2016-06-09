# ************************************************************
# Sequel Pro SQL dump
# Version 4529
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.11)
# Database: projekt
# Generation Time: 2016-05-13 08:39:57 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table get_all_operatoer
# ------------------------------------------------------------

DROP VIEW IF EXISTS `get_all_operatoer`;

CREATE TABLE `get_all_operatoer` (
   `opr_id` INT(11) NOT NULL DEFAULT '0',
   `opr_navn` TEXT NULL DEFAULT NULL,
   `ini` TEXT NULL DEFAULT NULL,
   `cpr` TEXT NULL DEFAULT NULL,
   `password` TEXT NULL DEFAULT NULL
) ENGINE=MyISAM;



# Dump of table get_all_produktbatch
# ------------------------------------------------------------

DROP VIEW IF EXISTS `get_all_produktbatch`;

CREATE TABLE `get_all_produktbatch` (
   `pb_id` INT(11) NOT NULL DEFAULT '0',
   `status` INT(11) NULL DEFAULT NULL,
   `recept_id` INT(11) NULL DEFAULT NULL
) ENGINE=MyISAM;



# Dump of table get_all_produktbatchkomponent
# ------------------------------------------------------------

DROP VIEW IF EXISTS `get_all_produktbatchkomponent`;

CREATE TABLE `get_all_produktbatchkomponent` (
   `pb_id` INT(11) NOT NULL,
   `rb_id` INT(11) NOT NULL,
   `tara` DOUBLE NULL DEFAULT NULL,
   `netto` DOUBLE NULL DEFAULT NULL,
   `opr_id` INT(11) NULL DEFAULT NULL
) ENGINE=MyISAM;



# Dump of table get_all_recept
# ------------------------------------------------------------

DROP VIEW IF EXISTS `get_all_recept`;

CREATE TABLE `get_all_recept` (
   `recept_id` INT(11) NOT NULL DEFAULT '0',
   `recept_navn` TEXT NULL DEFAULT NULL
) ENGINE=MyISAM;



# Dump of table get_all_recept_with_raavare
# ------------------------------------------------------------

DROP VIEW IF EXISTS `get_all_recept_with_raavare`;

CREATE TABLE `get_all_recept_with_raavare` (
   `recept_id` INT(11) NOT NULL DEFAULT '0',
   `recept_navn` TEXT NULL DEFAULT NULL,
   `raavare_id` INT(11) NOT NULL DEFAULT '0',
   `nom_netto` DOUBLE NULL DEFAULT NULL,
   `tolerance` DOUBLE NULL DEFAULT NULL,
   `raavare_navn` TEXT NULL DEFAULT NULL,
   `leverandoer` TEXT NULL DEFAULT NULL
) ENGINE=MyISAM;



# Dump of table get_all_receptkomponent
# ------------------------------------------------------------

DROP VIEW IF EXISTS `get_all_receptkomponent`;

CREATE TABLE `get_all_receptkomponent` (
   `recept_id` INT(11) NOT NULL,
   `raavare_id` INT(11) NOT NULL,
   `nom_netto` DOUBLE NULL DEFAULT NULL,
   `tolerance` DOUBLE NULL DEFAULT NULL
) ENGINE=MyISAM;



# Dump of table get_all_raavare
# ------------------------------------------------------------

DROP VIEW IF EXISTS `get_all_raavare`;

CREATE TABLE `get_all_raavare` (
   `raavare_id` INT(11) NOT NULL DEFAULT '0',
   `raavare_navn` TEXT NULL DEFAULT NULL,
   `leverandoer` TEXT NULL DEFAULT NULL
) ENGINE=MyISAM;



# Dump of table get_all_raavarebatch
# ------------------------------------------------------------

DROP VIEW IF EXISTS `get_all_raavarebatch`;

CREATE TABLE `get_all_raavarebatch` (
   `rb_id` INT(11) NOT NULL DEFAULT '0',
   `raavare_id` INT(11) NULL DEFAULT NULL,
   `maengde` DOUBLE NULL DEFAULT NULL
) ENGINE=MyISAM;



# Dump of table operatoer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `operatoer`;

CREATE TABLE `operatoer` (
  `opr_id` int(11) NOT NULL AUTO_INCREMENT,
  `opr_navn` text,
  `ini` text,
  `cpr` text,
  `password` text,
  PRIMARY KEY (`opr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `operatoer` WRITE;
/*!40000 ALTER TABLE `operatoer` DISABLE KEYS */;

INSERT INTO `operatoer` (`opr_id`, `opr_navn`, `ini`, `cpr`, `password`)
VALUES
	(1,'Angelo A','AA','070770-7007','lKje4fa'),
	(2,'Antonella B','AB','080880-8008','atoJ21v'),
	(3,'Luigi C','LC','090990-9009','jEfm5aQ');

/*!40000 ALTER TABLE `operatoer` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table produktbatch
# ------------------------------------------------------------

DROP TABLE IF EXISTS `produktbatch`;

CREATE TABLE `produktbatch` (
  `pb_id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) DEFAULT NULL,
  `recept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pb_id`),
  KEY `recept_id` (`recept_id`),
  CONSTRAINT `produktbatch_ibfk_1` FOREIGN KEY (`recept_id`) REFERENCES `recept` (`recept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `produktbatch` WRITE;
/*!40000 ALTER TABLE `produktbatch` DISABLE KEYS */;

INSERT INTO `produktbatch` (`pb_id`, `status`, `recept_id`)
VALUES
	(1,2,1),
	(2,2,1),
	(3,2,2),
	(4,1,3),
	(5,0,3);

/*!40000 ALTER TABLE `produktbatch` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table produktbatchkomponent
# ------------------------------------------------------------

DROP TABLE IF EXISTS `produktbatchkomponent`;

CREATE TABLE `produktbatchkomponent` (
  `pb_id` int(11) NOT NULL,
  `rb_id` int(11) NOT NULL,
  `tara` double DEFAULT NULL,
  `netto` double DEFAULT NULL,
  `opr_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pb_id`,`rb_id`),
  KEY `rb_id` (`rb_id`),
  KEY `opr_id` (`opr_id`),
  CONSTRAINT `produktbatchkomponent_ibfk_1` FOREIGN KEY (`pb_id`) REFERENCES `produktbatch` (`pb_id`),
  CONSTRAINT `produktbatchkomponent_ibfk_2` FOREIGN KEY (`rb_id`) REFERENCES `raavarebatch` (`rb_id`),
  CONSTRAINT `produktbatchkomponent_ibfk_3` FOREIGN KEY (`opr_id`) REFERENCES `operatoer` (`opr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `produktbatchkomponent` WRITE;
/*!40000 ALTER TABLE `produktbatchkomponent` DISABLE KEYS */;

INSERT INTO `produktbatchkomponent` (`pb_id`, `rb_id`, `tara`, `netto`, `opr_id`)
VALUES
	(1,1,0.5,10.05,1),
	(1,2,0.5,2.03,1),
	(1,4,0.5,1.98,1),
	(2,1,0.5,10.01,2),
	(2,2,0.5,1.99,2),
	(2,5,0.5,1.47,2),
	(3,1,0.5,10.07,1),
	(3,3,0.5,2.06,2),
	(3,4,0.5,1.55,1),
	(3,6,0.5,1.53,2),
	(4,1,0.5,10.02,3),
	(4,5,0.5,1.57,3),
	(4,6,0.5,1.03,3),
	(4,7,0.5,0.99,3);

/*!40000 ALTER TABLE `produktbatchkomponent` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table recept
# ------------------------------------------------------------

DROP TABLE IF EXISTS `recept`;

CREATE TABLE `recept` (
  `recept_id` int(11) NOT NULL AUTO_INCREMENT,
  `recept_navn` text,
  PRIMARY KEY (`recept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `recept` WRITE;
/*!40000 ALTER TABLE `recept` DISABLE KEYS */;

INSERT INTO `recept` (`recept_id`, `recept_navn`)
VALUES
	(1,'margherita'),
	(2,'prosciutto'),
	(3,'capricciosa');

/*!40000 ALTER TABLE `recept` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table receptkomponent
# ------------------------------------------------------------

DROP TABLE IF EXISTS `receptkomponent`;

CREATE TABLE `receptkomponent` (
  `recept_id` int(11) NOT NULL,
  `raavare_id` int(11) NOT NULL,
  `nom_netto` double DEFAULT NULL,
  `tolerance` double DEFAULT NULL,
  PRIMARY KEY (`recept_id`,`raavare_id`),
  KEY `raavare_id` (`raavare_id`),
  CONSTRAINT `receptkomponent_ibfk_1` FOREIGN KEY (`recept_id`) REFERENCES `recept` (`recept_id`),
  CONSTRAINT `receptkomponent_ibfk_2` FOREIGN KEY (`raavare_id`) REFERENCES `raavare` (`raavare_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `receptkomponent` WRITE;
/*!40000 ALTER TABLE `receptkomponent` DISABLE KEYS */;

INSERT INTO `receptkomponent` (`recept_id`, `raavare_id`, `nom_netto`, `tolerance`)
VALUES
	(1,1,10,0.1),
	(1,2,2,0.1),
	(1,5,2,0.1),
	(2,1,10,0.1),
	(2,3,2,0.1),
	(2,5,1.5,0.1),
	(2,6,1.5,0.1),
	(3,1,10,0.1),
	(3,4,1.5,0.1),
	(3,5,1.5,0.1),
	(3,6,1,0.1),
	(3,7,1,0.1);

/*!40000 ALTER TABLE `receptkomponent` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table raavare
# ------------------------------------------------------------

DROP TABLE IF EXISTS `raavare`;

CREATE TABLE `raavare` (
  `raavare_id` int(11) NOT NULL AUTO_INCREMENT,
  `raavare_navn` text,
  `leverandoer` text,
  PRIMARY KEY (`raavare_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `raavare` WRITE;
/*!40000 ALTER TABLE `raavare` DISABLE KEYS */;

INSERT INTO `raavare` (`raavare_id`, `raavare_navn`, `leverandoer`)
VALUES
	(1,'dej','Wawelka'),
	(2,'tomat','Knoor'),
	(3,'tomat','Veaubais'),
	(4,'tomat','Franz'),
	(5,'ost','Ost og Skinke A/S'),
	(6,'skinke','Ost og Skinke A/S'),
	(7,'champignon','Igloo Frostvarer');

/*!40000 ALTER TABLE `raavare` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table raavarebatch
# ------------------------------------------------------------

DROP TABLE IF EXISTS `raavarebatch`;

CREATE TABLE `raavarebatch` (
  `rb_id` int(11) NOT NULL AUTO_INCREMENT,
  `raavare_id` int(11) DEFAULT NULL,
  `maengde` double DEFAULT NULL,
  PRIMARY KEY (`rb_id`),
  KEY `raavare_id` (`raavare_id`),
  CONSTRAINT `raavarebatch_ibfk_1` FOREIGN KEY (`raavare_id`) REFERENCES `raavare` (`raavare_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `raavarebatch` WRITE;
/*!40000 ALTER TABLE `raavarebatch` DISABLE KEYS */;

INSERT INTO `raavarebatch` (`rb_id`, `raavare_id`, `maengde`)
VALUES
	(1,1,1000),
	(2,2,300),
	(3,3,300),
	(4,5,100),
	(5,5,100),
	(6,6,100),
	(7,7,100);

/*!40000 ALTER TABLE `raavarebatch` ENABLE KEYS */;
UNLOCK TABLES;




# Replace placeholder table for get_all_produktbatchkomponent with correct view syntax
# ------------------------------------------------------------

DROP TABLE `get_all_produktbatchkomponent`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `get_all_produktbatchkomponent`
AS SELECT
   `produktbatchkomponent`.`pb_id` AS `pb_id`,
   `produktbatchkomponent`.`rb_id` AS `rb_id`,
   `produktbatchkomponent`.`tara` AS `tara`,
   `produktbatchkomponent`.`netto` AS `netto`,
   `produktbatchkomponent`.`opr_id` AS `opr_id`
FROM `produktbatchkomponent`;


# Replace placeholder table for get_all_raavare with correct view syntax
# ------------------------------------------------------------

DROP TABLE `get_all_raavare`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `get_all_raavare`
AS SELECT
   `raavare`.`raavare_id` AS `raavare_id`,
   `raavare`.`raavare_navn` AS `raavare_navn`,
   `raavare`.`leverandoer` AS `leverandoer`
FROM `raavare`;


# Replace placeholder table for get_all_recept_with_raavare with correct view syntax
# ------------------------------------------------------------

DROP TABLE `get_all_recept_with_raavare`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `get_all_recept_with_raavare`
AS SELECT
   `R`.`recept_id` AS `recept_id`,
   `R`.`recept_navn` AS `recept_navn`,
   `RV`.`raavare_id` AS `raavare_id`,
   `RK`.`nom_netto` AS `nom_netto`,
   `RK`.`tolerance` AS `tolerance`,
   `RV`.`raavare_navn` AS `raavare_navn`,
   `RV`.`leverandoer` AS `leverandoer`
FROM ((`recept` `R` join `receptkomponent` `RK` on((`R`.`recept_id` = `RK`.`recept_id`))) join `raavare` `RV` on((`RK`.`raavare_id` = `RV`.`raavare_id`)));


# Replace placeholder table for get_all_produktbatch with correct view syntax
# ------------------------------------------------------------

DROP TABLE `get_all_produktbatch`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `get_all_produktbatch`
AS SELECT
   `produktbatch`.`pb_id` AS `pb_id`,
   `produktbatch`.`status` AS `status`,
   `produktbatch`.`recept_id` AS `recept_id`
FROM `produktbatch`;


# Replace placeholder table for get_all_recept with correct view syntax
# ------------------------------------------------------------

DROP TABLE `get_all_recept`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `get_all_recept`
AS SELECT
   `recept`.`recept_id` AS `recept_id`,
   `recept`.`recept_navn` AS `recept_navn`
FROM `recept`;


# Replace placeholder table for get_all_raavarebatch with correct view syntax
# ------------------------------------------------------------

DROP TABLE `get_all_raavarebatch`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `get_all_raavarebatch`
AS SELECT
   `raavarebatch`.`rb_id` AS `rb_id`,
   `raavarebatch`.`raavare_id` AS `raavare_id`,
   `raavarebatch`.`maengde` AS `maengde`
FROM `raavarebatch`;


# Replace placeholder table for get_all_operatoer with correct view syntax
# ------------------------------------------------------------

DROP TABLE `get_all_operatoer`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `get_all_operatoer`
AS SELECT
   `operatoer`.`opr_id` AS `opr_id`,
   `operatoer`.`opr_navn` AS `opr_navn`,
   `operatoer`.`ini` AS `ini`,
   `operatoer`.`cpr` AS `cpr`,
   `operatoer`.`password` AS `password`
FROM `operatoer`;


# Replace placeholder table for get_all_receptkomponent with correct view syntax
# ------------------------------------------------------------

DROP TABLE `get_all_receptkomponent`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `get_all_receptkomponent`
AS SELECT
   `receptkomponent`.`recept_id` AS `recept_id`,
   `receptkomponent`.`raavare_id` AS `raavare_id`,
   `receptkomponent`.`nom_netto` AS `nom_netto`,
   `receptkomponent`.`tolerance` AS `tolerance`
FROM `receptkomponent`;

--
-- Dumping routines (PROCEDURE) for database 'projekt'
--
DELIMITER ;;

# Dump of PROCEDURE update_operatoer
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `update_operatoer` */;;
/*!50003 SET SESSION SQL_MODE="ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `update_operatoer`(In _opr_id integer, IN _opr_navn varchar(35), IN _ini varchar(5), IN _cpr varchar(10), IN _password varchar(8))
BEGIN  
	START TRANSACTION;
	insert into operatoer (operatoer.opr_navn, ini, cpr, `password`) VALUES (_opr_navn, _ini, _cpr, _password)
	on duplicate key update
            opr_navn = _opr_navn,
            ini = _ini,
            cpr = _cpr,
            `password` = _password;
	COMMIT;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE update_produktbatch
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `update_produktbatch` */;;
/*!50003 SET SESSION SQL_MODE="ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `update_produktbatch`(In pb_id integer, recept_id integer, `status` integer)
begin  
	START TRANSACTION;
	insert into produktbatch
		set
			recept_id = recept_id,
            `status` = `status`
			
            on duplicate key update
            recept_id = values(recept_id),
            `status` = values(`status`);
            
	COMMIT;
	END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE update_produktbatchkomponent
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `update_produktbatchkomponent` */;;
/*!50003 SET SESSION SQL_MODE="ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `update_produktbatchkomponent`(In pb_id integer, rb_id integer, opr_id integer, tara real, netto real)
begin  
	START TRANSACTION;
	insert into produktbatchkomponent
		set
			rb_id = rb_id,
            opr_id = opr_id,
            tara = tara,
            netto = netto
			
            on duplicate key update
            rb_id = values(rb_id),
            opr_id = values(opr_id),
            tara = values(tara),
            netto = values(netto);
            
	COMMIT;
	END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE update_raavare
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `update_raavare` */;;
/*!50003 SET SESSION SQL_MODE="ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `update_raavare`(In raavare_id integer, raavare_navn varchar(35), leverandoer varchar(40))
begin  
	START TRANSACTION;
	insert into raavare
		set
			raavare_navn = raavare_navn,
            leverandoer = leverandoer
			
            on duplicate key update
            raavare_navn = values(raavare_navn),
            leverandoer = values(leverandoer);
            
	COMMIT;
	END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE update_raavarebatch
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `update_raavarebatch` */;;
/*!50003 SET SESSION SQL_MODE="ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `update_raavarebatch`(In rb_id integer, recept_id integer, maengde real)
begin  
	START TRANSACTION;
	insert into raavarebatch
		set 
			rb_id = rb_id,
            recept_id = recept_id,
            maengde = maengde
            on duplicate key update
            recept_id = values(recept_id),
            maengde = values(maengde);
	COMMIT;
	END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE update_recept
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `update_recept` */;;
/*!50003 SET SESSION SQL_MODE="ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `update_recept`(In recept_id integer, recept_navn varchar(35))
begin  
	START TRANSACTION;
	insert into recept
		set
			recept_navn = recept_navn
			
            on duplicate key update
            recept_navn = values(recept_navn);
            
	COMMIT;
	END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE update_receptkomp
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `update_receptkomp` */;;
/*!50003 SET SESSION SQL_MODE="ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `update_receptkomp`(In recept_id integer, raavare_id integer, nom_netto real,tolerance real)
begin  
	START TRANSACTION;
	insert into receptkomponent
		set
			nom_netto= nom_netto,
            tolerance = tolerance
			
            on duplicate key update
            nom_netto = values(nom_netto),
            tolerance = values(tolerance);
	COMMIT;
	END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
DELIMITER ;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

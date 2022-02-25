/*
SQLyog Ultimate v9.02 
MySQL - 5.6.17 : Database - db_SGA
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_SGA` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_SGA`;

/*Table structure for table `alumno` */

DROP TABLE IF EXISTS `alumno`;

CREATE TABLE `alumno` (
  `alu_dni` int(11) NOT NULL,
  `alu_nombre` varchar(45) DEFAULT NULL,
  `alu_apellido` varchar(45) DEFAULT NULL,
  `alu_fec_nac` date DEFAULT NULL,
  `alu_domicilio` varchar(45) DEFAULT NULL,
  `alu_telefono` varchar(45) DEFAULT NULL,
  `alu_insc_cod` int(11) DEFAULT NULL,
  PRIMARY KEY (`alu_dni`),
  UNIQUE KEY `alu_dni_UNIQUE` (`alu_dni`),
  KEY `FK_inscripcion_idx` (`alu_insc_cod`),
  CONSTRAINT `FK_inscripcion` FOREIGN KEY (`alu_insc_cod`) REFERENCES `inscripcion` (`insc_cod`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `alumno` */

insert  into `alumno`(`alu_dni`,`alu_nombre`,`alu_apellido`,`alu_fec_nac`,`alu_domicilio`,`alu_telefono`,`alu_insc_cod`) values (27589641,'Damian','Sanchez','2002-06-20','Av. San Martin 1254','2615874986',8866),(29321587,'Sofia','Gomez','1982-11-18','Colon 2800','2615489743',5554),(31254687,'Agustin','Alvarez','1984-06-13','Las Heras 235','2634501287',NULL),(35689741,'Javier','Martinez','1991-02-23','Catamarca 224','2614587956',NULL),(36415879,'Exequiel','Rodriguez','1999-06-16','Belgrano 500','2634589621',5512),(38125478,'Romina','Lopez','1991-04-26','Balcarce 458','2615478954',3322),(38478562,'Valentina','Romeo','1992-06-24','Mitre 120','2615478962',NULL);

/*Table structure for table `carrera` */

DROP TABLE IF EXISTS `carrera`;

CREATE TABLE `carrera` (
  `car_cod` int(11) NOT NULL,
  `car_nombre` varchar(45) DEFAULT NULL,
  `car_duracion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`car_cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `carrera` */

insert  into `carrera`(`car_cod`,`car_nombre`,`car_duracion`) values (47,'Tec. en Redes de Datos y Telecomunicaciones','3 años'),(48,'Ing. Electronica','5 años'),(58,'Tec. Superior en Programacion','2 años'),(59,'Higiene y seguridad','3 años'),(67,'Ing. Quimica','5 años');

/*Table structure for table `cursado` */

DROP TABLE IF EXISTS `cursado`;

CREATE TABLE `cursado` (
  `cur_alu_dni` int(11) NOT NULL,
  `cur_mat_cod` int(11) NOT NULL,
  `cur_nota` double DEFAULT NULL,
  PRIMARY KEY (`cur_alu_dni`,`cur_mat_cod`),
  KEY `FK_materia` (`cur_mat_cod`),
  KEY `FK_alumno` (`cur_alu_dni`),
  CONSTRAINT `FK_alumno` FOREIGN KEY (`cur_alu_dni`) REFERENCES `alumno` (`alu_dni`),
  CONSTRAINT `FK_materia` FOREIGN KEY (`cur_mat_cod`) REFERENCES `materia` (`mat_cod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cursado` */

insert  into `cursado`(`cur_alu_dni`,`cur_mat_cod`,`cur_nota`) values (27589641,865,9),(29321587,874,8),(36415879,587,6),(38125478,258,8);

/*Table structure for table `inscripcion` */

DROP TABLE IF EXISTS `inscripcion`;

CREATE TABLE `inscripcion` (
  `insc_cod` int(11) NOT NULL,
  `insc_nombre` varchar(45) DEFAULT NULL,
  `insc_fecha` date DEFAULT NULL,
  `insc_car_cod` int(11) DEFAULT NULL,
  PRIMARY KEY (`insc_cod`),
  KEY `FK_carrera_idx` (`insc_car_cod`),
  CONSTRAINT `FK_carrera` FOREIGN KEY (`insc_car_cod`) REFERENCES `carrera` (`car_cod`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `inscripcion` */

insert  into `inscripcion`(`insc_cod`,`insc_nombre`,`insc_fecha`,`insc_car_cod`) values (3322,'Romina Lopez','2014-06-03',59),(5512,'Exequiel Rodriguez','2016-06-19',67),(5554,'Sofia Gomez','2019-06-12',59),(8866,'Damian Sanchez','2020-06-09',47);

/*Table structure for table `materia` */

DROP TABLE IF EXISTS `materia`;

CREATE TABLE `materia` (
  `mat_cod` int(11) NOT NULL,
  `mat_nombre` varchar(45) DEFAULT NULL,
  `mat_prof_dni` int(11) DEFAULT NULL,
  PRIMARY KEY (`mat_cod`),
  KEY `FK_profesor_idx` (`mat_prof_dni`),
  CONSTRAINT `FK_profesor` FOREIGN KEY (`mat_prof_dni`) REFERENCES `profesor` (`prof_dni`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `materia` */

insert  into `materia`(`mat_cod`,`mat_nombre`,`mat_prof_dni`) values (258,'Redes LAN',28956471),(478,'Estadistica I',16121352),(587,'Programacion I',22365478),(865,'Tacticas y ataque ',18745986),(874,'Historia',20365415);

/*Table structure for table `profesor` */

DROP TABLE IF EXISTS `profesor`;

CREATE TABLE `profesor` (
  `prof_dni` int(11) NOT NULL,
  `prof_nombre` varchar(45) DEFAULT NULL,
  `prof_apellido` varchar(45) DEFAULT NULL,
  `prof_fec_nac` varchar(45) DEFAULT NULL,
  `prof_domicilio` varchar(45) DEFAULT NULL,
  `prof_telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`prof_dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profesor` */

insert  into `profesor`(`prof_dni`,`prof_nombre`,`prof_apellido`,`prof_fec_nac`,`prof_domicilio`,`prof_telefono`) values (16121352,'Marcelo','Gallardo','1992-10-23','Funes 145','2634506589'),(18745986,'Ramon','Diaz','1992-10-23','15247895','2613548971'),(20365415,'Angel','Labruna','2018-07-13','Las Heras 222','2615478236'),(20874596,'Jorge','Garcia','1992-10-23','Los Tamarindos 120','261587462'),(22365478,'Oscar','Leon','1992-03-26','El Challao','2615423658'),(28956471,'Paula','Martinez','1987-05-22','La Rioja 547','261587496');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

CREATE DATABASE  IF NOT EXISTS `etfbl_ip_aero` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `etfbl_ip_aero`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: etfbl_ip_aero
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `access`
--

DROP TABLE IF EXISTS `access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `access` (
  `datum` date NOT NULL,
  `broj_pristupa` int NOT NULL,
  PRIMARY KEY (`datum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `email` varchar(100) NOT NULL,
  `korisnicko_ime` varchar(100) NOT NULL,
  `lozinka` varchar(256) NOT NULL,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `adresa` varchar(100) NOT NULL,
  `vrsta_naloga` varchar(45) NOT NULL,
  `drzava` varchar(100) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `korisnicko_ime_UNIQUE` (`korisnicko_ime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `let`
--

DROP TABLE IF EXISTS `let`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `let` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tip` varchar(45) NOT NULL,
  `putanja_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm0p90e3t3wydr9bo5xwf4y7vy` (`putanja_id`),
  CONSTRAINT `FKm0p90e3t3wydr9bo5xwf4y7vy` FOREIGN KEY (`putanja_id`) REFERENCES `putanja` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lokacija`
--

DROP TABLE IF EXISTS `lokacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lokacija` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `grad` varchar(100) NOT NULL,
  `drzava` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poruka`
--

DROP TABLE IF EXISTS `poruka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poruka` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  `datum` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `naslov` varchar(100) NOT NULL,
  `sadrzaj` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `putanja`
--

DROP TABLE IF EXISTS `putanja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `putanja` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `odredisna_lokacija_id` bigint NOT NULL,
  `polazna_lokacija_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK723ro3kueyrhn6jp798cb9n5t` (`polazna_lokacija_id`),
  KEY `FKiulcd7120ojdkeoyur9cf5u40` (`odredisna_lokacija_id`),
  CONSTRAINT `FK723ro3kueyrhn6jp798cb9n5t` FOREIGN KEY (`polazna_lokacija_id`) REFERENCES `lokacija` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKiulcd7120ojdkeoyur9cf5u40` FOREIGN KEY (`odredisna_lokacija_id`) REFERENCES `lokacija` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `putnicka_rezervacija`
--

DROP TABLE IF EXISTS `putnicka_rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `putnicka_rezervacija` (
  `broj_mjesta` int NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_putnicka_rezervacija_rezervacija1` FOREIGN KEY (`id`) REFERENCES `rezervacija` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rezervacija`
--

DROP TABLE IF EXISTS `rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rezervacija` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  `datum_kreiranja` date NOT NULL,
  `razlog` varchar(500) DEFAULT ' ',
  `let_id` bigint NOT NULL,
  `email` varchar(100) NOT NULL,
  `termin_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rezervacija_let1_idx` (`let_id`),
  KEY `fk_rezervacija_korisnik1_idx` (`email`),
  KEY `fk_rezervacija_termin1_idx` (`termin_id`),
  CONSTRAINT `fk_rezervacija_korisnik1` FOREIGN KEY (`email`) REFERENCES `korisnik` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_rezervacija_let1` FOREIGN KEY (`let_id`) REFERENCES `let` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_rezervacija_termin` FOREIGN KEY (`termin_id`) REFERENCES `termin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teretna_rezervacija`
--

DROP TABLE IF EXISTS `teretna_rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teretna_rezervacija` (
  `opis_tereta` mediumtext NOT NULL,
  `specifikacija` mediumtext NOT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_transportna_rezervacija_rezervacija` FOREIGN KEY (`id`) REFERENCES `rezervacija` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `termin`
--

DROP TABLE IF EXISTS `termin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `termin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dan` date NOT NULL,
  `polazak` time NOT NULL,
  `dolazak` time NOT NULL,
  `l_id` bigint NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'Ceka',
  PRIMARY KEY (`id`),
  KEY `FKhegj671jpur13utqa7njr0c6q` (`l_id`),
  CONSTRAINT `FKhegj671jpur13utqa7njr0c6q` FOREIGN KEY (`l_id`) REFERENCES `let` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `zaposleni`
--

DROP TABLE IF EXISTS `zaposleni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zaposleni` (
  `korisnicko_ime` varchar(100) NOT NULL,
  `lozinka` varchar(256) NOT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL DEFAULT 'ROLE_USER',
  `enabled` tinyint NOT NULL DEFAULT '1',
  `logged_in` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`korisnicko_ime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-04 20:52:11

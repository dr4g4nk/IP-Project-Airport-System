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
-- Dumping data for table `access`
--

LOCK TABLES `access` WRITE;
/*!40000 ALTER TABLE `access` DISABLE KEYS */;
INSERT INTO `access` VALUES ('2021-08-12',72),('2021-08-13',150),('2021-08-14',53),('2021-08-15',20),('2021-08-16',50),('2021-08-29',51),('2021-09-01',3),('2021-09-02',13),('2021-09-03',465),('2021-09-04',216),('2021-09-05',12),('2021-09-06',68),('2021-09-07',16),('2021-09-08',18),('2021-09-13',1),('2021-09-20',1),('2021-09-28',7),('2021-09-29',14),('2021-09-30',3),('2021-10-02',12),('2021-10-03',14),('2021-10-04',5);
/*!40000 ALTER TABLE `access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES ('dasbfhshdg@dfgdads.sfgha','daghtujyerthd','73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=','asdgerhre','sadhrehsdfh','fdhrhfbs rehwe','Teretni','Denmark'),('gorankos@mail.com','gorank','73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=','Goran','Kos','Komlenac bb, Kozarska Dubica','on','Bosnia and Herzegovina'),('korisnik@mail.com','dsaghefrhe','73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=','Korisnik','korisnik','Bosnia and Herzegovina','Putnicki','Bosnia and Herzegovina'),('teretni@mail.com','teretni','73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=','asfsds','dasfsd','sdfdsafdsvz\\vdsvshuiknu','Teretni','Bosnia and Herzegovina'),('test@test.com','test123','73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=','test','test','test','Putnicki','Ãland Islands');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `let`
--

LOCK TABLES `let` WRITE;
/*!40000 ALTER TABLE `let` DISABLE KEYS */;
INSERT INTO `let` VALUES (11,'Teretni',8),(12,'Putnicki',9),(19,'Putnicki',16),(20,'Putnicki',17),(21,'Putnicki',18),(22,'Putnicki',19),(23,'Putnicki',20),(24,'Putnicki',21);
/*!40000 ALTER TABLE `let` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lokacija`
--

LOCK TABLES `lokacija` WRITE;
/*!40000 ALTER TABLE `lokacija` DISABLE KEYS */;
INSERT INTO `lokacija` VALUES (1,'Banja Luka','Bosnia and Herzegovina'),(2,'Beograd','Serbia'),(3,'Zagreb','Croatia'),(5,'Tuzla','Bosnia and Herzegovina'),(6,'Bec','Austria'),(7,'Brisel','Belgium'),(8,'Tirana','Albania'),(9,'Berlin','Germany'),(18,'Skoplje','Macedonia (the former Yugoslav Republic of)'),(19,'Atina','Greece'),(20,'Kopenhagen','Denmark'),(21,'Prag','Czech Republic');
/*!40000 ALTER TABLE `lokacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `poruka`
--

LOCK TABLES `poruka` WRITE;
/*!40000 ALTER TABLE `poruka` DISABLE KEYS */;
INSERT INTO `poruka` VALUES (1,'Procitana','15.8.2021','korisnik@mail.com','dsbadg','d\\vsabhdfbnzfbsdfb rsd shdf hr sdhweg'),(2,'Procitana','15.8.2021','dsgsag','sdager','zbasdzbdsabasrgsdv sdvad twqe gfq ww trqweteqwtqwet'),(3,'Procitana','04.09.2021','dragan_kos@outlook.com','Rezervacija','Kako da rezervisem let ako nemam nalog?'),(4,'Procitana','04.09.2021','dragankos96@hotmail.com','Let','Da li imate let za'),(5,'Neprocitana','04.10.2021','haudusghliru@mail.com','dsnjglag','sdgnmka;osgda gdsainm; dsagionafg asdiogn');
/*!40000 ALTER TABLE `poruka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `putanja`
--

LOCK TABLES `putanja` WRITE;
/*!40000 ALTER TABLE `putanja` DISABLE KEYS */;
INSERT INTO `putanja` VALUES (1,2,1),(3,1,3),(8,3,1),(9,1,2),(16,18,1),(17,9,1),(18,1,18),(19,1,9),(20,6,1),(21,1,6),(22,7,1),(23,1,7),(49,1,19);
/*!40000 ALTER TABLE `putanja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `putnicka_rezervacija`
--

LOCK TABLES `putnicka_rezervacija` WRITE;
/*!40000 ALTER TABLE `putnicka_rezervacija` DISABLE KEYS */;
INSERT INTO `putnicka_rezervacija` VALUES (15,13),(18,14);
/*!40000 ALTER TABLE `putnicka_rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rezervacija`
--

LOCK TABLES `rezervacija` WRITE;
/*!40000 ALTER TABLE `rezervacija` DISABLE KEYS */;
INSERT INTO `rezervacija` VALUES (13,'Nova','2021-10-04',' ',12,'korisnik@mail.com',153),(14,'Nova','2021-10-04',' ',20,'korisnik@mail.com',144),(15,'Nova','2021-10-04',' ',11,'teretni@mail.com',156);
/*!40000 ALTER TABLE `rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `teretna_rezervacija`
--

LOCK TABLES `teretna_rezervacija` WRITE;
/*!40000 ALTER TABLE `teretna_rezervacija` DISABLE KEYS */;
INSERT INTO `teretna_rezervacija` VALUES ('bdh\\voasi\\dhvunpbasr','server.p12',15);
/*!40000 ALTER TABLE `teretna_rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `termin`
--

LOCK TABLES `termin` WRITE;
/*!40000 ALTER TABLE `termin` DISABLE KEYS */;
INSERT INTO `termin` VALUES (130,'2021-10-04','15:00:00','17:00:00',24,'Sletio'),(131,'2021-10-05','15:00:00','17:00:00',24,'Ceka'),(132,'2021-10-06','15:00:00','17:00:00',24,'Ceka'),(133,'2021-10-04','12:00:00','14:00:00',23,'Sletio'),(134,'2021-10-05','12:00:00','14:00:00',23,'Ceka'),(135,'2021-10-06','12:00:00','14:00:00',23,'Ceka'),(136,'2021-10-04','10:00:00','12:00:00',22,'Sletio'),(137,'2021-10-06','10:00:00','12:00:00',22,'Ceka'),(138,'2021-10-05','10:00:00','12:00:00',22,'Ceka'),(139,'2021-10-04','09:00:00','11:00:00',21,'Sletio'),(140,'2021-10-05','09:00:00','11:00:00',21,'Ceka'),(141,'2021-10-06','09:00:00','11:00:00',21,'Ceka'),(142,'2021-10-04','18:00:00','14:00:00',20,'Sletio'),(143,'2021-10-05','18:00:00','14:00:00',20,'Ceka'),(144,'2021-10-06','18:00:00','14:00:00',20,'Ceka'),(148,'2021-10-04','19:00:00','21:00:00',19,'Poletio'),(149,'2021-10-05','19:00:00','21:00:00',19,'Ceka'),(150,'2021-10-06','19:00:00','21:00:00',19,'Ceka'),(151,'2021-10-04','16:00:00','18:00:00',12,'Sletio'),(152,'2021-10-06','16:00:00','18:00:00',12,'Ceka'),(153,'2021-10-05','16:00:00','18:00:00',12,'Ceka'),(154,'2021-10-04','08:00:00','10:00:00',11,'Sletio'),(155,'2021-10-05','08:00:00','10:00:00',11,'Ceka'),(156,'2021-10-06','08:00:00','10:00:00',11,'Ceka'),(160,'2021-10-04','21:20:00','23:00:00',24,'Ceka'),(161,'2021-10-04','22:00:00','23:30:00',20,'Ceka');
/*!40000 ALTER TABLE `termin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `zaposleni`
--

LOCK TABLES `zaposleni` WRITE;
/*!40000 ALTER TABLE `zaposleni` DISABLE KEYS */;
INSERT INTO `zaposleni` VALUES ('ASGDSAGCBXDARG','73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=','sfgsdfhh','asdgghaf','ROLE_USER',1,_binary '\0'),('dragank','73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=','Dragan','Kos','ROLE_USER',1,_binary '\0'),('dragan_kos','73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=','Dragan','Kos','ROLE_ADMIN',1,_binary '\0'),('zaposleni','73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=','zaposleni','zaposleni','ROLE_USER',1,_binary '\0');
/*!40000 ALTER TABLE `zaposleni` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-04 20:53:28

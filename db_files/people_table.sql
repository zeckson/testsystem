-- MySQL dump 10.10
--
-- Host: localhost    Database: autority_db
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt

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
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `PERSON_ID` int(11) NOT NULL auto_increment,
  `PERSON_PRIVILIGIES` varchar(255) NOT NULL,
  `PERSON_NAME` varchar(255) default NULL,
  `PERSON_SURENAME` varchar(255) default NULL,
  `PERSON_INFO` varchar(255) default NULL,
  `PERSON_LOGIN` varchar(255) default NULL,
  `PERSON_PASSWORD` varchar(255) default NULL,
  `PERSON_SUBJECTS` varchar(255) default NULL,
  `PERSON_GROUP` varchar(255) default NULL,
  `PERSON_TUTOR` varchar(255) default NULL,
  PRIMARY KEY  (`PERSON_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `people`
--


/*!40000 ALTER TABLE `people` DISABLE KEYS */;
LOCK TABLES `people` WRITE;
INSERT INTO `people` VALUES (1,'USER','Vasya','Pupkin','user','user','user','','null','null'),(2,'USER','Ivan','Ivanov','user','user2','user2','','null','null'),(3,'ADMIN','Admin','Admin','admin','admin','admin','',NULL,NULL),(4,'USER','Абитуриент 2008','a08cp001','user','a08cp001','yjqgplle','','null','null'),(5,'USER','Абитуриент 2008','a08cp002','user','a08cp002','knafggmq','','null','null'),(6,'USER','Абитуриент 2008','a08cp002','user','a08cp002','xcfrseve','','null','null'),(7,'USER','Абитуриент 2008','a08cp002','user','a08cp002','knafggmq','','null','null'),(8,'USER','Абитуриент 2008','a08cp003','user','a08cp003','xcfrseve','','null','null'),(9,'USER','Абитуриент 2008','a08cp004','user','a08cp004','bsggcgsi','','null','null'),(10,'USER','Абитуриент 2008','a08cp005','user','a08cp005','ijvqskud','','null','null'),(11,'USER','Абитуриент 2008','a08cp006','user','a08cp006','scehgevq','','null','null'),(12,'USER','Абитуриент 2008','a08cp007','user','a08cp007','rnuojmmf','','null','null'),(13,'USER','Абитуриент 2008','a08cp008','user','a08cp008','ngtneweh','','null','null'),(14,'USER','Абитуриент 2008','a08cp009','user','a08cp009','nfwjehyc','','null','null'),(15,'USER','Абитуриент 2008','a08cp010','user','a08cp010','yotkugyk','','null','null'),(16,'USER','Абитуриент 2008','a08cp011','user','a08cp011','ggowtnfq','','null','null'),(17,'USER','Абитуриент 2008','a08cp012','user','a08cp012','dykdrgka','','null','null'),(18,'USER','Абитуриент 2008','a08cp013','user','a08cp013','ifdptsfn','','null','null'),(19,'USER','Абитуриент 2008','a08cp014','user','a08cp014','siyjxxxs','','null','null'),(20,'USER','Абитуриент 2008','a08cp015','user','a08cp015','lyshfogu','','null','null'),(21,'USER','Абитуриент 2008','a08cp016','user','a08cp016','gfpunvem','','null','null'),(22,'USER','Абитуриент 2008','a08cp017','user','a08cp017','rkpmirvo','','null','null'),(23,'USER','Абитуриент 2008','a08cp018','user','a08cp018','bmqwkvcy','','null','null'),(24,'USER','Абитуриент 2008','a08cp019','user','a08cp019','obekxgco','','null','null'),(25,'USER','Абитуриент 2008','a08cp020','user','a08cp020','paojnmhj','','null','null'),(26,'USER','Абитуриент 2008','a08cp021','user','a08cp021','eqbgllnu','','null','null'),(27,'USER','Абитуриент 2008','a08cp022','user','a08cp022','icaqkfwt','','null','null'),(28,'USER','Абитуриент 2008','a08cp023','user','a08cp023','oqsbulvi','','null','null'),(29,'USER','Абитуриент 2008','a08cp024','user','a08cp024','oovdnhad','','null','null'),(30,'USER','Абитуриент 2008','a08cp025','user','a08cp025','qhltqexs','','null','null'),(31,'USER','Абитуриент 2008','a08cp026','user','a08cp026','yeloujci','','null','null'),(32,'USER','Абитуриент 2008','a08cp027','user','a08cp027','fsqetotv','','null','null'),(33,'USER','Абитуриент 2008','a08cp028','user','a08cp028','wqiolodi','','null','null'),(34,'USER','Абитуриент 2008','a08cp029','user','a08cp029','eginobqm','','null','null'),(35,'USER','Абитуриент 2008','a08cp030','user','a08cp030','mnayhpig','','null','null'),(36,'USER','Абитуриент 2008','a08cp031','user','a08cp031','sdnxuugn','','null','null'),(37,'USER','Абитуриент 2008','a08cp032','user','a08cp032','xocdglgc','','null','null'),(38,'USER','Абитуриент 2008','a08cp033','user','a08cp033','rhlasrqm','','null','null');
UNLOCK TABLES;
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


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
INSERT INTO `people` VALUES (1,'USER','Vasya','Pupkin','user','user','user','','null','null'),(2,'USER','Ivan','Ivanov','user','user2','user2','','null','null'),(3,'ADMIN','Admin','Admin','admin','admin','admin','',NULL,NULL),(4,'USER','Абитуриент 2008','a08cp001','user','a08cp001','yjqgplle','','null','null'),(5,'USER','Абитуриент 2008','a08cp002','user','a08cp002','knafggmq','','null','null'),(6,'USER','Абитуриент 2008','a08cp002','user','a08cp002','xcfrseve','','null','null'),(7,'USER','Абитуриент 2008','a08cp002','user','a08cp002','knafggmq','','null','null'),(8,'USER','Абитуриент 2008','a08cp003','user','a08cp003','xcfrseve','','null','null'),(9,'USER','Абитуриент 2008','a08cp004','user','a08cp004','bsggcgsi','','null','null'),(10,'USER','Абитуриент 2008','a08cp005','user','a08cp005','ijvqskud','','null','null'),(11,'USER','Абитуриент 2008','a08cp006','user','a08cp006','scehgevq','','null','null'),(12,'USER','Абитуриент 2008','a08cp007','user','a08cp007','rnuojmmf','','null','null'),(13,'USER','Абитуриент 2008','a08cp008','user','a08cp008','ngtneweh','','null','null'),(14,'USER','Абитуриент 2008','a08cp009','user','a08cp009','nfwjehyc','','null','null'),(15,'USER','Абитуриент 2008','a08cp010','user','a08cp010','yotkugyk','','null','null'),(16,'USER','Абитуриент 2008','a08cp011','user','a08cp011','ggowtnfq','','null','null'),(17,'USER','Абитуриент 2008','a08cp012','user','a08cp012','dykdrgka','','null','null'),(18,'USER','Абитуриент 2008','a08cp013','user','a08cp013','ifdptsfn','','null','null'),(19,'USER','Абитуриент 2008','a08cp014','user','a08cp014','siyjxxxs','','null','null'),(20,'USER','Абитуриент 2008','a08cp015','user','a08cp015','lyshfogu','','null','null'),(21,'USER','Абитуриент 2008','a08cp016','user','a08cp016','gfpunvem','','null','null'),(22,'USER','Абитуриент 2008','a08cp017','user','a08cp017','rkpmirvo','','null','null'),(23,'USER','Абитуриент 2008','a08cp018','user','a08cp018','bmqwkvcy','','null','null'),(24,'USER','Абитуриент 2008','a08cp019','user','a08cp019','obekxgco','','null','null'),(25,'USER','Абитуриент 2008','a08cp020','user','a08cp020','paojnmhj','','null','null'),(26,'USER','Абитуриент 2008','a08cp021','user','a08cp021','eqbgllnu','','null','null'),(27,'USER','Абитуриент 2008','a08cp022','user','a08cp022','icaqkfwt','','null','null'),(28,'USER','Абитуриент 2008','a08cp023','user','a08cp023','oqsbulvi','','null','null'),(29,'USER','Абитуриент 2008','a08cp024','user','a08cp024','oovdnhad','','null','null'),(30,'USER','Абитуриент 2008','a08cp025','user','a08cp025','qhltqexs','','null','null'),(31,'USER','Абитуриент 2008','a08cp026','user','a08cp026','yeloujci','','null','null'),(32,'USER','Абитуриент 2008','a08cp027','user','a08cp027','fsqetotv','','null','null'),(33,'USER','Абитуриент 2008','a08cp028','user','a08cp028','wqiolodi','','null','null'),(34,'USER','Абитуриент 2008','a08cp029','user','a08cp029','eginobqm','','null','null'),(35,'USER','Абитуриент 2008','a08cp030','user','a08cp030','mnayhpig','','null','null'),(36,'USER','Абитуриент 2008','a08cp031','user','a08cp031','sdnxuugn','','null','null'),(37,'USER','Абитуриент 2008','a08cp032','user','a08cp032','xocdglgc','','null','null'),(38,'USER','Абитуриент 2008','a08cp033','user','a08cp033','rhlasrqm','','null','null'),(39,'USER','Абитуриент 2008','a08cp051','user','a08cp051','dhtwqenp','','null','null'),(40,'USER','Абитуриент 2008','a08cp052','user','a08cp052','ptihmxgp','','null','null'),(41,'USER','Абитуриент 2008','a08cp053','user','a08cp053','yfqhhngd','','null','null'),(42,'USER','Абитуриент 2008','a08cp054','user','a08cp054','jgubtkte','','null','null'),(43,'USER','Абитуриент 2008','a08cp055','user','a08cp055','tpupybca','','null','null'),(44,'USER','Абитуриент 2008','a08cp056','user','a08cp056','qcbycxfr','','null','null'),(45,'USER','Абитуриент 2008','a08cp057','user','a08cp057','bcfconju','','null','null'),(46,'USER','Абитуриент 2008','a08cp071','user','a08cp071','yhdjsmce','','null','null'),(47,'USER','Абитуриент 2008','a08cp058','user','a08cp058','nojhmgbf','','null','null'),(48,'USER','Абитуриент 2008','a08cp061','user','a08cp061','gqtybphf','','null','null'),(49,'USER','Абитуриент 2008','a08cp062','user','a08cp062','tlscfjyd','','null','null'),(50,'USER','Абитуриент 2008','a08cp059','user','a08cp059','vnlfwvao','','null','null'),(51,'USER','Абитуриент 2008','a08cp034','user','a08cp034','hkkuhuag','','null','null'),(52,'USER','Абитуриент 2008','a08cp063','user','a08cp063','kqqoymbo','','null','null'),(53,'USER','Абитуриент 2008','a08cp060','user','a08cp060','bcyndexu','','null','null'),(54,'USER','Абитуриент 2008','a08cp064','user','a08cp064','lrtvatyw','','null','null'),(55,'USER','Абитуриент 2008','a08cp100','user','a08cp100','vahqofeu','','null','null'),(56,'USER','Абитуриент 2008','a08cp065','user','a08cp065','aaytvjgb','','null','null'),(57,'USER','Абитуриент 2008','a08cp099','user','a08cp099','oglrxvby','','null','null'),(58,'USER','Абитуриент 2008','a08cp066','user','a08cp066','ijtdlsqr','','null','null'),(59,'USER','Абитуриент 2008','a08cp035','user','a08cp035','hvppfomd','','null','null'),(60,'USER','Абитуриент 2008','a08cp098','user','a08cp098','kfjjeswf','','null','null'),(61,'USER','Абитуриент 2008','a08cp072','user','a08cp072','yohdgjyi','','null','null'),(62,'USER','Абитуриент 2008','a08cp067','user','a08cp067','turekbah','','null','null'),(63,'USER','Абитуриент 2008','a08cp097','user','a08cp097','kkpscftp','','null','null'),(64,'USER','Абитуриент 2008','a08cp068','user','a08cp068','ibkivkkk','','null','null'),(65,'USER','Абитуриент 2008','a08cp036','user','a08cp036','uifrkyki','','null','null'),(66,'USER','Абитуриент 2008','a08cp069','user','a08cp069','vvndyird','','null','null'),(67,'USER','Абитуриент 2008','a08cp073','user','a08cp073','mjbqsyol','','null','null'),(68,'USER','Абитуриент 2008','a08cp096','user','a08cp096','tdxrkdop','','null','null'),(69,'USER','Абитуриент 2008','a08cp070','user','a08cp070','wruxeoll','','null','null'),(70,'USER','Абитуриент 2008','a08cp095','user','a08cp095','mbmphdde','','null','null'),(71,'USER','Абитуриент 2008','a08cp037','user','a08cp037','ntxdwede','','null','null'),(72,'USER','Абитуриент 2008','a08cp038','user','a08cp038','crdbdgxp','','null','null'),(73,'USER','Абитуриент 2008','a08cp081','user','a08cp081','ykjpblwj','','null','null'),(74,'USER','Абитуриент 2008','a08cp094','user','a08cp094','nmkpyscm','','null','null'),(75,'USER','Абитуриент 2008','a08cp039','user','a08cp039','hpwnemtw','','null','null'),(76,'USER','Абитуриент 2008','a08cp082','user','a08cp082','oeojgfyo','','null','null'),(77,'USER','Абитуриент 2008','a08cp074','user','a08cp074','jsyjbyeh','','null','null'),(78,'USER','Абитуриент 2008','a08cp093','user','a08cp093','rcdokgll','','null','null'),(79,'USER','Абитуриент 2008','a08cp040','user','a08cp040','aslifowq','','null','null'),(80,'USER','Абитуриент 2008','a08cp092','user','a08cp092','xmfnjeoi','','null','null'),(81,'USER','Абитуриент 2008','a08cp083','user','a08cp083','nyaaxstx','','null','null'),(82,'USER','Абитуриент 2008','a08cp075','user','a08cp075','nvyjfacd','','null','null'),(83,'USER','Абитуриент 2008','a08cp041','user','a08cp041','kwprproq','','null','null'),(84,'USER','Абитуриент 2008','a08cp084','user','a08cp084','xjfywlpi','','null','null'),(85,'USER','Абитуриент 2008','a08cp091','user','a08cp091','lbxycyxt','','null','null'),(86,'USER','Абитуриент 2008','a08cp085','user','a08cp085','dbkvwjem','','null','null'),(87,'USER','Абитуринет 2008','a08cp042','user','a08cp042','syaeumqp','','null','null'),(88,'USER','Абитуриент 2008','a08cp086','user','a08cp086','rnexecsa','','null','null'),(89,'USER','Абитуринет 2008','a08cp043','user','a08cp043','kmgyutca','','null','null'),(90,'USER','Абитуриент 2008','a08cp087','user','a08cp087','wrjxmnvi','','null','null'),(91,'USER','Абитуриент 2008','a08cp044','user','a08cp044','knxqovmj','','null','null'),(92,'USER','Абитуриент 2008','a08cp088','user','a08cp088','ecvwyimn','','null','null'),(93,'USER','Абитуриент 2008','a08cp045','user','a08cp045','vfluhtqx','','null','null'),(94,'USER','Абитуриент 2008','a08cp089','user','a08cp089','nimnnsog','','null','null'),(95,'USER','Абитуриент 2008','a08cp076','user','a08cp076','fvxgofky','','null','null'),(96,'USER','Абитуриент 2008','a08cp090','user','a08cp090','malntbmp','','null','null'),(97,'USER','Абитуриент 2008','a08cp046','user','a08cp046','vgfucssp','','null','null'),(98,'USER','Абитуриент 2008','a08cp047','user','a08cp047','gwrrjmob','','null','null'),(99,'USER','Абитуриент 2008','a08cp077','user','a08cp077','kwinwrax','','null','null'),(100,'USER','Абитуриент 2008','a08cp048','user','a08cp048','blyvdnpn','','null','null'),(101,'USER','Абитуриент 2008','a08cp078','user','a08cp078','phdvlgeb','','null','null'),(102,'USER','Абитуриент 2008','a08cp049','user','a08cp049','bnlkdlkh','','null','null'),(103,'USER','Абитуриент 2008','a08cp050','user','a08cp050','fisesvet','','null','null'),(104,'USER','Абитуриент 2008','a08cp079','user','a08cp079','irxryoqk','','null','null'),(105,'USER','Абитуриент 2008','a08cp080','user','a08cp080','hptuuxlv','','null','null');
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


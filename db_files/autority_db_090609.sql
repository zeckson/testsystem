-- MySQL dump 10.11
--
-- Host: localhost    Database: autority_db
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt

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
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (1,'ADMIN','admin','admin','admin','admin','PhotoN2004','',NULL,NULL),(2,'USER','Test','User','user','test','test','','null','null'),(3,'ADMIN','Роман','Ромамн','admin','Роман','Роман','',NULL,NULL),(4,'USER','Анастасия   ','Бабкина   ','user','babkina ','x2xdu  ','','null','null'),(5,'USER','Максим   ','Брякин   ','user','bryakin   ','uf058  ','','null','null'),(6,'USER','Артем   ','Веньгин   ','user','vengin ','0aaed  ','','null','null'),(7,'USER','Александра   ','Григорьева   ','user','grigorieva ','da519  ','','null','null'),(8,'USER','Дмитрий  ','Виноградов  ','user','vinogradov','ot4gl','','null','null'),(9,'USER','Сергей  ','Власов  ','user','vlasov','dk8xn','','null','null'),(10,'USER','Сергей     ','Иванов ','user','ivanov ','7c4ii  ','','null','null'),(11,'USER','Диана  ','Гальтер  ','user','galter','uw33r','','null','null'),(12,'USER','Данила   ','Кочетков   ','user','kochetkov ','wz7yr ','','null','null'),(13,'USER','Алексей ','Лебедев ','user','lebedev','38nxb','','null','null'),(14,'USER','Алёна','Москаленко','user','moskalenko','oxp4u','','null','null'),(15,'USER','Юрий','Назаров','user','nazarov','vvyn6','','null','null'),(16,'USER','Дмитрий','Павлов','user','pavlov','8rfab','','null','null'),(17,'USER','Илья','Павловец','user','pavlovec','x0owc','','null','null'),(18,'USER','Андрей','Крапивин','user','krapivin','hqm5c','','null','null'),(19,'USER','Ефим','Полынчук','user','polynchuk','pjq4l','','null','null'),(20,'USER','Павел','Рогов','user','rogov','96nyz','','null','null'),(21,'USER','Евгений','Гончар','user','gonchar','dmm','','null','null'),(22,'USER','Сергей','Сандыга','user','sandyga','1t39m','','null','null'),(23,'USER','Олег','Ларионов','user','larionov','9xmun','','null','null'),(24,'USER','Семен','Смирнов','user','smirnov','j7sel','','null','null'),(25,'USER','Пётр','Громов','user','gromov','00we6','','null','null'),(26,'USER','Алена','Ступишина','user','stupishina','cjnnt','','null','null'),(27,'USER','Екатерина','Дмитриева','user','dmitrieva','j9vco','','null','null'),(28,'USER','Михаил','Тарасенко','user','tarasenko','mry4v','','null','null'),(29,'USER','Татьяна','Дубровина','user','dubrovina','576im','','null','null'),(30,'USER','Андрей','Лебедев','user','lebedev','nzjhz','','null','null'),(31,'USER','Юлия','Филиппова','user','filippova','c9uc0','','null','null'),(32,'USER','Юлия','Егорова','user','egorova','lihbg','','null','null'),(33,'USER','Артем','Штыков','user','stykov','q0o5y','','null','null'),(34,'USER','Андрей','Лысенко','user','lysenko','zetf1','','null','null'),(35,'USER','Андрей','Юрлевич','user','yurlevich','qp5dz','','null','null'),(36,'USER','Анастасия','Маслова','user','maslova','h8je2','','null','null'),(37,'USER','Александр','Иванов','user','ivanov','wk02q','','null','null'),(38,'USER','Нурман','Ортаев','user','ortaev','k6t01','','null','null'),(39,'USER','Павел','Кабанов','user','kabanov','9wmy5','','null','null'),(40,'USER','Сергей','Кынев','user','kynev','wb90j','','null','null'),(41,'USER','Максим','Приказов','user','prikazov','dk2ln','','null','null'),(42,'USER','Анна','Левшина','user','levshina','buzjq','','null','null'),(43,'USER','Сергей','Косульников','user','kosulnikov','zcsmd','','null','null'),(44,'USER','Александр','Протченко','user','protchenko','seocz','','null','null'),(45,'USER','Борис','Манухин','user','manuhin','ictsw','','null','null'),(46,'USER','Леонид','Конев','user','konev','7lbts','','null','null'),(47,'USER','Александр','Савин','user','savin','3ujml','','null','null'),(48,'USER','Алена','Иванова','user','ivanova','gri7x','','null','null'),(49,'USER','Александр','Поляков','user','polyakov','6vlle','','null','null'),(50,'USER','Павел','Иванов','user','ivanov','wor7o','','null','null'),(51,'USER','Дмитрий','Семичев','user','semichev','id5qx','','null','null'),(52,'USER','Иван','Ермаков','user','ermakov','6gwtt','','null','null'),(53,'USER','Анна','Езерская','user','ezerskaya','h8ba1','','null','null'),(54,'USER','Софья','Франк','user','frank','5q9x9','','null','null'),(55,'USER','Даниэль','Гомон','user','gomon','mybny','','null','null'),(56,'USER','Александра','Шпигун','user','shpigun','narvp','','null','null'),(57,'USER','Николай','Богачёв','user','bogachev','eaof8','','null','null'),(58,'USER','Николай','Балбекин','user','balbekin','isg05','','null','null'),(59,'USER','Андрей','Пустовалов','user','pustovalov','br2df','','null','null'),(60,'USER','Андрей','Афонюшкин','user','afonuskin','fl63x','','null','null'),(61,'USER','Кристина','Сивухина','user','sivuhina','eyhl2','','null','null'),(62,'USER','Александр','Агейский','user','ageisky','rlhu0','','null','null'),(63,'USER','','','user','','','','null','null'),(64,'USER','Андрей	','Смекалов','user','smekalov','dxt6i','','null','null'),(65,'USER','Марина	','Сорокина','user','sorokina','bfmxd','','null','null'),(66,'USER','Алина','Суркова','user','surkova','wq5zh','','null','null'),(67,'USER','Дина','Фатхуллина','user','fathullina','rbheo','','null','null'),(68,'ADMIN','Максим','Волынский','admin','Volynsky','volynsky_password','',NULL,NULL),(69,'USER','Дмитрий','Фёдоров','user','fedorov','1c2aj','','null','null'),(70,'USER','Владислав','Филатов','user','filatov','6ltef','','null','null'),(71,'USER','Антон','Шумилин','user','shumilin','nuohb','','null','null'),(72,'USER','Анастасия','Яикова','user','yaikova','jpbc8','','null','null');
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistic`
--

DROP TABLE IF EXISTS `statistic`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `statistic` (
  `SELF_ID` int(11) NOT NULL auto_increment,
  `PERSON_ID` int(11) default NULL,
  `QUESTION_ID` int(11) default NULL,
  `ANSWER` longtext,
  `DATE` bigint(20) default NULL,
  `TIME` int(11) default NULL,
  `TRY_NUMBER` int(11) default NULL,
  `SCORE` double default NULL,
  PRIMARY KEY  (`SELF_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `statistic`
--

LOCK TABLES `statistic` WRITE;
/*!40000 ALTER TABLE `statistic` DISABLE KEYS */;
INSERT INTO `statistic` VALUES (1,2,120,'условные вероятности появления символов суммируются;',1244552709141,3,0,0),(2,2,122,'каналы передачи сообщений;',1244552710485,2,0,0),(3,2,126,'сигналом, переданным в дискретный момент времени;',1244552711938,2,0,0),(4,2,130,'расширяется в k раз;',1244552713360,2,0,0),(5,2,133,'действительный;',1244552715125,2,0,0),(6,2,135,'векторы линейно независимы;',1244552716453,2,0,0),(7,2,139,'частоты сигнала;',1244552717688,2,0,0),(8,2,140,'комплексная функция;',1244552719235,2,0,0),(9,2,145,'взятие отсчетов непрерывной функции;выбор дискретного значения непрерывной функции;формирование кодового слова;',1244552768281,50,0,0),(10,2,147,'свертки с преобразованием Фурье от апертурной функции элемента дискретизации;произведения со спектром апертурной функции элемента дискретизации;',1244552774281,6,0,0),(11,2,149,'произведением спектров сигнала-переносчика и информационного сигнала;',1244552775797,2,0,0),(12,2,152,'плотность вероятности значений амплитуды информационного сигнала;',1244552777250,2,0,0),(13,2,155,'является равномерным в полосе частот информационного сигнала;',1244552778797,2,0,0),(14,2,157,'фильтр, настраиваемый на несущую частоту;',1244552780047,2,0,0),(15,2,163,'число различных символов в сообщении;',1244552781750,2,0,0),(16,2,164,'дискретные отсчеты сигнала преобразуют в кодовые слова;',1244552783344,2,0,0),(17,2,167,'величина, обратная разности периода и длительносьти импульсов;',1244552785063,2,0,0),(18,2,172,'дополнение множеств;',1244552786594,2,0,0),(19,2,174,'по средней мощности передаваемого сигнала;',1244552787922,2,0,3),(20,2,183,'минимизации полной вероятности ошибки;',1244552789422,2,0,0),(21,2,185,'определении значений параметров, которые считаются постоянными в течениие некоторого интервала;',1244552791125,2,0,0),(22,2,187,'полная совокупность условных символов, которую применяют для кодирования сообщений;',1244552793031,2,0,0),(23,2,191,'снижение априорной вероятности ошибки и условного среднего риска;',1244552794625,2,0,0),(24,2,193,'увеличения средней длины кодовой комбинации и избыточности кода;',1244552796094,2,0,2),(25,2,198,'уменьшение вероятности пропуска информационного сигнала;',1244552797625,2,0,3),(26,63,74,'тестирование',1244560873575,39,0,0),(27,63,76,'компьютер, сервер',1244560903380,30,0,0),(28,63,86,'последовательная',1244560925898,23,0,0),(29,63,108,'R;',1244560945409,20,0,0),(30,63,107,'ни о чем',1244560964271,19,0,0),(31,63,89,'думать',1244560971718,8,0,0),(32,63,96,'быстро',1244560984085,13,0,0),(33,63,98,'Необязательным атрибутом;',1244561001155,18,0,0),(34,63,110,'{ };',1244561012722,12,0,0),(35,63,101,'там открываются\\ спеЦиАныке АКРОШки',1244561064741,53,0,0),(36,63,106,'тестовое задание;анимированный кадр;',1244561074596,10,0,0),(37,63,103,'си++',1244561090210,16,0,0);
/*!40000 ALTER TABLE `statistic` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2009-06-09 15:32:02

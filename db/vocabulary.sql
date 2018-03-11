-- MySQL dump 10.13  Distrib 5.7.20, for Win32 (AMD64)
--
-- Host: localhost    Database: vocabulary
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Current Database: `vocabulary`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `vocabulary` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `vocabulary`;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `language_id` int(11) NOT NULL AUTO_INCREMENT,
  `language_name` varchar(255) NOT NULL,
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10020 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (10017,'English'),(10018,'Russian'),(10019,'German');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word_classes`
--

DROP TABLE IF EXISTS `word_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `word_classes` (
  `word_class_id` int(11) NOT NULL AUTO_INCREMENT,
  `word_class_name` varchar(255) NOT NULL,
  PRIMARY KEY (`word_class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10012 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word_classes`
--

LOCK TABLES `word_classes` WRITE;
/*!40000 ALTER TABLE `word_classes` DISABLE KEYS */;
INSERT INTO `word_classes` VALUES (10003,'noun'),(10004,'verb'),(10005,'adjective'),(10006,'adverb'),(10007,'pronoun'),(10008,'preposition'),(10009,'conjunction'),(10011,'collocation');
/*!40000 ALTER TABLE `word_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word_collection_relationships`
--

DROP TABLE IF EXISTS `word_collection_relationships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `word_collection_relationships` (
  `collection_relationship_id` int(11) NOT NULL AUTO_INCREMENT,
  `relationships_word` int(11) NOT NULL,
  `relationships_collection` int(11) NOT NULL,
  PRIMARY KEY (`collection_relationship_id`),
  KEY `relationships_word` (`relationships_word`),
  KEY `relationships_collection` (`relationships_collection`),
  CONSTRAINT `relationships_collection` FOREIGN KEY (`relationships_collection`) REFERENCES `word_collections` (`word_collection_id`),
  CONSTRAINT `relationships_word` FOREIGN KEY (`relationships_word`) REFERENCES `words` (`word_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10350 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word_collection_relationships`
--

LOCK TABLES `word_collection_relationships` WRITE;
/*!40000 ALTER TABLE `word_collection_relationships` DISABLE KEYS */;
INSERT INTO `word_collection_relationships` VALUES (10202,10474,10011),(10203,10476,10011),(10204,10478,10011),(10205,10480,10011),(10206,10482,10011),(10207,10484,10011),(10208,10486,10011),(10209,10489,10011),(10210,10491,10011),(10211,10493,10011),(10212,10496,10011),(10213,10498,10011),(10214,10500,10011),(10215,10502,10011),(10216,10504,10011),(10217,10506,10011),(10218,10508,10011),(10219,10510,10011),(10220,10512,10011),(10221,10514,10011),(10222,10516,10011),(10223,10518,10011),(10224,10520,10011),(10225,10522,10011),(10226,10524,10011),(10227,10526,10011),(10228,10528,10011),(10229,10530,10011),(10230,10533,10011),(10231,10535,10011),(10232,10538,10011),(10233,10540,10011),(10234,10543,10011),(10235,10546,10011),(10236,10548,10011),(10237,10550,10011),(10238,10552,10011),(10239,10554,10011),(10240,10556,10011),(10241,10558,10011),(10242,10560,10011),(10243,10562,10011),(10244,10565,10011),(10245,10567,10011),(10246,10569,10011),(10247,10571,10011),(10248,10573,10012),(10249,10577,10012),(10250,10580,10012),(10251,10583,10012),(10252,10586,10012),(10253,10589,10012),(10254,10591,10012),(10255,10593,10012),(10256,10596,10012),(10257,10599,10012),(10258,10602,10012),(10259,10605,10012),(10260,10608,10012),(10261,10611,10012),(10262,10613,10012),(10263,10615,10012),(10264,10618,10012),(10265,10621,10012),(10266,10623,10012),(10267,10627,10012),(10268,10630,10012),(10269,10634,10012),(10270,10636,10012),(10271,10639,10012),(10272,10642,10012),(10273,10647,10012),(10274,10650,10012),(10275,10653,10012),(10276,10656,10012),(10277,10659,10012),(10278,10662,10012),(10279,10666,10012),(10280,10669,10012),(10281,10671,10012),(10282,10674,10012),(10283,10679,10012),(10284,10681,10012),(10285,10686,10012),(10286,10688,10012),(10287,10693,10012),(10288,10696,10012),(10289,10700,10012),(10290,10703,10012),(10291,10707,10012),(10292,10710,10012),(10293,10713,10012),(10294,10716,10012),(10295,10719,10012),(10296,10722,10012),(10297,10724,10012),(10298,10727,10012),(10299,10732,10012),(10300,10735,10013),(10301,10738,10013),(10302,10740,10013),(10303,10743,10013),(10304,10745,10013),(10305,10747,10013),(10306,10749,10013),(10307,10751,10013),(10308,10753,10013),(10309,10755,10013),(10310,10757,10013),(10311,10760,10013),(10312,10762,10013),(10313,10764,10013),(10314,10766,10013),(10315,10769,10013),(10316,10771,10013),(10317,10773,10013),(10318,10775,10013),(10319,10778,10013),(10320,10780,10013),(10321,10782,10013),(10322,10784,10013),(10323,10786,10013),(10324,10788,10013),(10325,10790,10013),(10326,10792,10013),(10327,10794,10013),(10328,10796,10013),(10329,10798,10013),(10330,10800,10013),(10331,10802,10013),(10332,10805,10013),(10333,10807,10013),(10334,10809,10013),(10335,10811,10013),(10336,10813,10013),(10337,10815,10013),(10338,10817,10013),(10339,10819,10013),(10340,10821,10013),(10341,10823,10013),(10342,10825,10013),(10343,10827,10013),(10344,10829,10013),(10345,10831,10013),(10346,10833,10013),(10347,10835,10013),(10348,10837,10013),(10349,10839,10013);
/*!40000 ALTER TABLE `word_collection_relationships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word_collections`
--

DROP TABLE IF EXISTS `word_collections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `word_collections` (
  `word_collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `word_collection_name` varchar(255) NOT NULL,
  PRIMARY KEY (`word_collection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10014 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word_collections`
--

LOCK TABLES `word_collections` WRITE;
/*!40000 ALTER TABLE `word_collections` DISABLE KEYS */;
INSERT INTO `word_collections` VALUES (10011,'Antonymen'),(10012,'Adjective aus Kopien'),(10013,'Adjective mit Präpositionen');
/*!40000 ALTER TABLE `word_collections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word_meaning_relationships`
--

DROP TABLE IF EXISTS `word_meaning_relationships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `word_meaning_relationships` (
  `meaning_relationship_id` int(11) NOT NULL AUTO_INCREMENT,
  `relationship_word` int(11) NOT NULL,
  `relationship_meaning` int(11) NOT NULL,
  PRIMARY KEY (`meaning_relationship_id`),
  KEY `relationship_meaning` (`relationship_meaning`),
  KEY `relationship_word` (`relationship_word`),
  CONSTRAINT `relationship_meaning` FOREIGN KEY (`relationship_meaning`) REFERENCES `words` (`word_id`),
  CONSTRAINT `relationship_word` FOREIGN KEY (`relationship_word`) REFERENCES `words` (`word_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10468 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word_meaning_relationships`
--

LOCK TABLES `word_meaning_relationships` WRITE;
/*!40000 ALTER TABLE `word_meaning_relationships` DISABLE KEYS */;
INSERT INTO `word_meaning_relationships` VALUES (10262,10474,10475),(10263,10476,10477),(10264,10478,10479),(10265,10480,10481),(10266,10482,10483),(10267,10484,10485),(10268,10486,10487),(10269,10486,10488),(10270,10489,10490),(10271,10491,10492),(10272,10493,10494),(10273,10493,10495),(10274,10496,10497),(10275,10498,10499),(10276,10500,10501),(10277,10502,10503),(10278,10504,10505),(10279,10506,10507),(10280,10508,10509),(10281,10510,10511),(10282,10512,10513),(10283,10514,10515),(10284,10516,10517),(10285,10518,10519),(10286,10520,10521),(10287,10522,10523),(10288,10524,10525),(10289,10526,10527),(10290,10528,10529),(10291,10530,10531),(10292,10530,10532),(10293,10533,10534),(10294,10535,10536),(10295,10535,10537),(10296,10538,10539),(10297,10540,10541),(10298,10540,10542),(10299,10543,10544),(10300,10543,10545),(10301,10546,10547),(10302,10548,10549),(10303,10550,10551),(10304,10552,10553),(10305,10554,10555),(10306,10556,10557),(10307,10558,10559),(10308,10560,10561),(10309,10562,10563),(10310,10562,10564),(10311,10565,10566),(10312,10567,10568),(10313,10569,10570),(10314,10571,10572),(10315,10573,10574),(10316,10573,10575),(10317,10573,10576),(10318,10577,10578),(10319,10577,10579),(10320,10580,10581),(10321,10580,10582),(10322,10583,10584),(10323,10583,10585),(10324,10586,10587),(10325,10586,10588),(10326,10589,10590),(10327,10591,10592),(10328,10593,10594),(10329,10593,10595),(10330,10596,10597),(10331,10596,10598),(10332,10599,10600),(10333,10599,10601),(10334,10602,10603),(10335,10602,10604),(10336,10605,10606),(10337,10605,10607),(10338,10608,10609),(10339,10608,10610),(10340,10611,10612),(10341,10613,10614),(10342,10615,10616),(10343,10615,10617),(10344,10618,10619),(10345,10618,10620),(10346,10621,10622),(10347,10623,10624),(10348,10623,10625),(10349,10623,10626),(10350,10627,10628),(10351,10627,10629),(10352,10630,10631),(10353,10630,10632),(10354,10630,10633),(10355,10634,10635),(10356,10636,10637),(10357,10636,10638),(10358,10639,10640),(10359,10639,10641),(10360,10642,10643),(10361,10642,10644),(10362,10642,10645),(10363,10647,10648),(10364,10647,10649),(10365,10653,10654),(10366,10656,10657),(10367,10656,10658),(10368,10659,10660),(10369,10659,10661),(10370,10662,10664),(10371,10662,10665),(10372,10666,10667),(10373,10669,10670),(10374,10671,10672),(10375,10671,10673),(10376,10674,10675),(10377,10674,10677),(10378,10674,10678),(10379,10679,10680),(10380,10681,10682),(10381,10681,10683),(10382,10681,10684),(10383,10681,10685),(10384,10686,10687),(10385,10688,10690),(10386,10688,10691),(10387,10688,10692),(10388,10693,10694),(10389,10693,10695),(10390,10696,10697),(10391,10696,10698),(10392,10696,10699),(10393,10700,10701),(10394,10700,10702),(10395,10703,10706),(10396,10707,10708),(10397,10707,10709),(10398,10710,10711),(10399,10710,10712),(10400,10713,10714),(10401,10713,10715),(10402,10716,10717),(10403,10719,10720),(10404,10719,10721),(10405,10722,10723),(10406,10724,10726),(10407,10727,10728),(10408,10727,10729),(10409,10727,10730),(10410,10727,10731),(10411,10732,10733),(10412,10732,10734),(10413,10735,10736),(10414,10735,10737),(10415,10738,10739),(10416,10740,10741),(10417,10740,10742),(10418,10743,10744),(10419,10745,10746),(10420,10747,10748),(10421,10749,10750),(10422,10751,10752),(10423,10753,10754),(10424,10755,10756),(10425,10757,10758),(10426,10757,10759),(10427,10760,10761),(10428,10762,10763),(10429,10764,10765),(10430,10766,10767),(10431,10766,10768),(10432,10769,10770),(10433,10771,10772),(10434,10773,10774),(10435,10775,10776),(10436,10775,10777),(10437,10778,10779),(10438,10780,10781),(10439,10782,10783),(10440,10784,10785),(10441,10786,10787),(10442,10788,10789),(10443,10790,10791),(10444,10792,10793),(10445,10794,10795),(10446,10796,10797),(10447,10798,10799),(10448,10800,10801),(10449,10802,10803),(10450,10802,10804),(10451,10805,10806),(10452,10807,10808),(10453,10809,10810),(10454,10811,10812),(10455,10813,10814),(10456,10815,10816),(10457,10817,10818),(10458,10819,10820),(10459,10821,10822),(10460,10823,10824),(10461,10825,10826),(10462,10827,10828),(10463,10831,10832),(10464,10833,10834),(10465,10835,10836),(10466,10837,10838),(10467,10839,10840);
/*!40000 ALTER TABLE `word_meaning_relationships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `words`
--

DROP TABLE IF EXISTS `words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `words` (
  `word_id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) NOT NULL,
  `class` int(11) NOT NULL,
  `language` int(11) NOT NULL,
  PRIMARY KEY (`word_id`),
  KEY `language` (`language`),
  KEY `class` (`class`),
  CONSTRAINT `class` FOREIGN KEY (`class`) REFERENCES `word_classes` (`word_class_id`),
  CONSTRAINT `language` FOREIGN KEY (`language`) REFERENCES `languages` (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10841 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `words`
--

LOCK TABLES `words` WRITE;
/*!40000 ALTER TABLE `words` DISABLE KEYS */;
INSERT INTO `words` VALUES (10474,'e Heiterkeit',10003,10019),(10475,'веселость',10003,10018),(10476,'e Zustimmung',10003,10019),(10477,'согласие',10003,10018),(10478,'e Traurigkeit',10003,10019),(10479,'грустность',10003,10018),(10480,'e Geborgenheit',10003,10019),(10481,'чувство безопасности',10003,10018),(10482,'e Zerstörung',10003,10019),(10483,'уничтожение',10003,10018),(10484,'e Muße',10003,10019),(10485,'время прилежного делания раби себя',10003,10018),(10486,'e Unrast',10003,10019),(10487,'неугомонность',10003,10018),(10488,'обеспокоеннсть',10003,10018),(10489,'e Tätigkeit',10003,10019),(10490,'занятость (есть что делать)',10003,10018),(10491,'r Nutzen',10003,10019),(10492,'польща',10003,10018),(10493,'e Schande',10003,10019),(10494,'стыд',10003,10018),(10495,'бесчестье',10003,10018),(10496,'e Fülle',10003,10019),(10497,'изобилие',10003,10018),(10498,'e Gleichg[ltigkeit',10003,10019),(10499,'безразличие',10003,10018),(10500,'r Schaden',10003,10019),(10501,'вред',10003,10018),(10502,'e Gerechtigkeit',10003,10019),(10503,'справедливость',10003,10018),(10504,'s Lob',10003,10019),(10505,'похвала',10003,10018),(10506,'r Argwohn',10003,10019),(10507,'недоверие',10003,10018),(10508,'e Treue',10003,10019),(10509,'верность',10003,10018),(10510,'s Vertrauen',10003,10019),(10511,'доверие',10003,10018),(10512,'e Härte',10003,10019),(10513,'твёрдость',10003,10018),(10514,'s Fanatismus',10003,10019),(10515,'фанатизм',10003,10018),(10516,'r Tadel',10003,10019),(10517,'порицание',10003,10018),(10518,'e Zurückwisung',10003,10019),(10519,'отказ',10003,10018),(10520,'e Grobheit',10003,10019),(10521,'грубость',10003,10018),(10522,'e Freiheit',10003,10019),(10523,'свобода',10003,10018),(10524,'r Zwang',10003,10019),(10525,'принуждение',10003,10018),(10526,'r Verrat',10003,10019),(10527,'предательство',10003,10018),(10528,'e Mangel',10003,10019),(10529,'недостаток',10003,10018),(10530,'r Schmach',10003,10019),(10531,'позор',10003,10018),(10532,'бесславие',10003,10018),(10533,'r Ruhm',10003,10019),(10534,'слава',10003,10018),(10535,'e Anteilnahme',10003,10019),(10536,'участие',10003,10018),(10537,'сочувствие',10003,10018),(10538,'e Ordnung',10003,10019),(10539,'порядок',10003,10018),(10540,'e Mißschätzung',10003,10019),(10541,'неуважение',10003,10018),(10542,'презрение',10003,10018),(10543,'r Aufbau',10003,10019),(10544,'постройка',10003,10018),(10545,'развитие',10003,10018),(10546,'e Hilfsbereitschaft',10003,10019),(10547,'готовность помочь',10003,10018),(10548,'e Ehre',10003,10019),(10549,'честь',10003,10018),(10550,'r Widerspruch',10003,10019),(10551,'сопростивление',10003,10018),(10552,'r Willkür',10003,10019),(10553,'произвол',10003,10018),(10554,'e Langweile',10003,10019),(10555,'скука',10003,10018),(10556,'e Verlogenheit',10003,10019),(10557,'лживость',10003,10018),(10558,'s Entgegenkommen',10003,10019),(10559,'хождение навстречу',10003,10018),(10560,'r Eigennutz',10003,10019),(10561,'корысть',10003,10018),(10562,'e Verlassenheit',10003,10019),(10563,'заброшенность',10003,10018),(10564,'одиночество',10003,10018),(10565,'e Höflichkeit',10003,10019),(10566,'вежливость',10003,10018),(10567,'e Nachsicht',10003,10019),(10568,'снисхождение',10003,10018),(10569,'e Aufrichtigkeit',10003,10019),(10570,'искренность',10003,10018),(10571,'e Anerkennung',10003,10019),(10572,'признание',10003,10018),(10573,'unbeholfen',10005,10019),(10574,'нерасторопный',10005,10018),(10575,'неловкий',10005,10018),(10576,'неуклюжиый',10005,10018),(10577,'seicht',10005,10019),(10578,'мелководный',10005,10018),(10579,'ограниченный (умственно)',10005,10018),(10580,'unverfroren',10005,10019),(10581,'наглый',10005,10018),(10582,'дерзкий',10005,10018),(10583,'einheitlich',10005,10019),(10584,'единый',10005,10018),(10585,'стадартный',10005,10018),(10586,'ranzig',10005,10019),(10587,'прогорклый',10005,10018),(10588,'затхлый',10005,10018),(10589,'sämtlich',10005,10019),(10590,'все (без исключения)',10005,10018),(10591,'ausnahmslos',10005,10019),(10592,'не знающий исключений',10005,10018),(10593,'solide',10005,10019),(10594,'солидный',10005,10018),(10595,'надежный',10005,10018),(10596,'ungestüm',10005,10019),(10597,'вспыльчивый',10005,10018),(10598,'стремительный',10005,10018),(10599,'vollzählig',10005,10019),(10600,'полный',10005,10018),(10601,'в полном составе',10005,10018),(10602,'schlüssig',10005,10019),(10603,'обоснованный',10005,10018),(10604,'убедительный',10005,10018),(10605,'ungekürzt',10005,10019),(10606,'неукороченный',10005,10018),(10607,'неубавленный',10005,10018),(10608,'unnahbar',10005,10019),(10609,'высокомерный',10005,10018),(10610,'неприступный',10005,10018),(10611,'berechtigt',10005,10019),(10612,'резонный',10005,10018),(10613,'gepflegt',10005,10019),(10614,'ухоженный',10005,10018),(10615,'schwerfällig',10005,10019),(10616,'неповоротливый',10005,10018),(10617,'неуклюжий',10005,10018),(10618,'matt',10005,10019),(10619,'слабый',10005,10018),(10620,'вялый',10005,10018),(10621,'unverhofft',10005,10019),(10622,'неожиданный',10005,10018),(10623,'vollendet',10005,10019),(10624,'совершенный',10005,10018),(10625,'законченный',10005,10018),(10626,'завершенный',10005,10018),(10627,'gedrückt',10005,10019),(10628,'угнетенный',10005,10018),(10629,'подавленный',10005,10018),(10630,'vollauf',10005,10019),(10631,'вдоболь',10005,10018),(10632,'достаточно',10005,10018),(10633,'вполне',10005,10018),(10634,'uneingeschränkt',10005,10019),(10635,'неограниченный',10005,10018),(10636,'dicht',10005,10019),(10637,'плотный',10005,10018),(10638,'непроницаемый',10005,10018),(10639,'﻿unverbindlich',10005,10019),(10640,'ни к чему не обязывающий',10005,10018),(10641,'слишком общий',10005,10018),(10642,'faul',10005,10019),(10643,'ленивый',10005,10018),(10644,'гнилой',10005,10018),(10645,'тухлый',10005,10018),(10646,'затхлый',10005,10018),(10647,'schal',10005,10019),(10648,'безвкусный',10005,10018),(10649,'пресный',10005,10018),(10650,'völlig',10005,10019),(10651,'полный',10005,10018),(10652,'совершенный',10005,10018),(10653,'dumpf',10005,10019),(10654,'глухой',10005,10018),(10655,'затхлый',10005,10018),(10656,'unversehrt',10005,10019),(10657,'невредимый',10005,10018),(10658,'целый',10005,10018),(10659,'artig',10005,10019),(10660,'вежливый',10005,10018),(10661,'воспитанный',10005,10018),(10662,'voll',10005,10019),(10663,'полный',10005,10018),(10664,'заполненный',10005,10018),(10665,'исполненный',10005,10018),(10666,'total',10005,10019),(10667,'всеобщиий',10005,10018),(10668,'полный',10005,10018),(10669,'zahn',10005,10019),(10670,'ручной',10005,10018),(10671,'kultiviert',10005,10019),(10672,'образцовый',10005,10018),(10673,'умственно развитый',10005,10018),(10674,'gesamt',10005,10019),(10675,'весь',10005,10018),(10676,'целый',10005,10018),(10677,'общий',10005,10018),(10678,'совокупный',10005,10018),(10679,'besonnen',10005,10019),(10680,'рассудительный',10005,10018),(10681,'vollständig',10005,10019),(10682,'полный (о данныйх)',10005,10018),(10683,'совершенный (о покое)',10005,10018),(10684,'абсолютно',10005,10018),(10685,'совершенно',10005,10018),(10686,'unschlüssig',10005,10019),(10687,'неприветливый',10005,10018),(10688,'lückenlos',10005,10019),(10689,'полный',10005,10018),(10690,'без пробелов',10005,10018),(10691,'нерперывный',10005,10018),(10692,'сплошной',10005,10018),(10693,'unbeschwert',10005,10019),(10694,'беззаботный',10005,10018),(10695,'неотягощенный',10005,10018),(10696,'dünn',10005,10019),(10697,'тонкий',10005,10018),(10698,'худой',10005,10018),(10699,'редний (волосы)',10005,10018),(10700,'dürr',10005,10019),(10701,'сухой',10005,10018),(10702,'высохший',10005,10018),(10703,'vollkommen',10005,10019),(10704,'полный',10005,10018),(10705,'совершенный',10005,10018),(10706,'абсолютный',10005,10018),(10707,'rundweg',10005,10019),(10708,'сразу',10005,10018),(10709,'прямо',10005,10018),(10710,'heiser',10005,10019),(10711,'хриплый',10005,10018),(10712,'охрипший',10005,10018),(10713,'schwül',10005,10019),(10714,'душный',10005,10018),(10715,'знойный',10005,10018),(10716,'unwirsch',10005,10019),(10717,'грубый',10005,10018),(10718,'неприветливый',10005,10018),(10719,'planmäßig',10005,10019),(10720,'планомерный',10005,10018),(10721,'штатный',10005,10018),(10722,'unleserlich',10005,10019),(10723,'неразборчивый',10005,10018),(10724,'welk',10005,10019),(10725,'вялый',10005,10018),(10726,'блеклый',10005,10018),(10727,'umfassend',10005,10019),(10728,'обшщирный',10005,10018),(10729,'всеобъемлющий',10005,10018),(10730,'широко',10005,10018),(10731,'всеобъемлюще',10005,10018),(10732,'verschwommen',10005,10019),(10733,'туманный',10005,10018),(10734,'расплывчатый',10005,10018),(10735,'(un)fähig sein zu',10005,10019),(10736,'быть способным',10005,10018),(10737,'быть неспособным на что-то',10005,10018),(10738,'gewöhnt sein an A',10005,10019),(10739,'привыкнуть к чему-то',10005,10018),(10740,'(un)schädlich sein für',10005,10019),(10741,'быть вредным',10005,10018),(10742,'быть безвредным для',10005,10018),(10743,'wütend sein über',10005,10019),(10744,'быть в ярости из-за',10005,10018),(10745,'abhängig sein von',10005,10019),(10746,'зависеть от чего-то',10005,10018),(10747,'verwundert sein über',10005,10019),(10748,'быть удивленным чем-то',10005,10018),(10749,'enttäuscht sein von',10005,10019),(10750,'быть разочарованным в чем-то',10005,10018),(10751,'verliebt sein in A',10005,10019),(10752,'быть влюбленным в кого-то',10005,10018),(10753,'nützlich sein  für',10005,10019),(10754,'быть полезным для',10005,10018),(10755,'verwandt sein mit',10005,10019),(10756,'быть родственным для кого-то',10005,10018),(10757,'(un)erfahren sein in D',10005,10019),(10758,'быть опытным',10005,10018),(10759,'быть неопытным в чем-то',10005,10018),(10760,'immun sein gegen A',10005,10019),(10761,'быть неуязвимым к чему-то',10005,10018),(10762,'zufrieden sein mit',10005,10019),(10763,'быть довольным чем-то',10005,10018),(10764,'interessiert sein an D',10005,10019),(10765,'быть заинтересованным в чем-то',10005,10018),(10766,'(un)geeignet sein für',10005,10019),(10767,'быть пригодным',10005,10018),(10768,'быть непригодным к чему-то',10005,10018),(10769,'böse sein auf A',10005,10019),(10770,'злиться из-за чего-то',10005,10018),(10771,'schuld sein an D',10005,10019),(10772,'быть виноватым в чем-то',10005,10018),(10773,'verantwortlich sein für',10005,10019),(10774,'быть ответственным за что-то',10005,10018),(10775,'(un)wichtig sein für',10005,10019),(10776,'быть важным',10005,10018),(10777,'быть неважным для',10005,10018),(10778,'glücklich sein über',10005,10019),(10779,'быть радостным из-за',10005,10018),(10780,'erkrankt sein an D',10005,10019),(10781,'быть больным чем-то',10005,10018),(10782,'befreundet sein mit',10005,10019),(10783,'быть дружественным кому-то',10005,10018),(10784,'entschlossen sein zu',10005,10019),(10785,'решиться на что-то',10005,10018),(10786,'angesehen sein bei',10005,10019),(10787,'быть в почете среди кого-то',10005,10018),(10788,'reich sein an D',10005,10019),(10789,'быть богатым чем-то',10005,10018),(10790,'beteiligt sein an D',10005,10019),(10791,'быть задействованным в чем-то',10005,10018),(10792,'entfernt sein von',10005,10019),(10793,'быть отдаленным от чего-то',10005,10018),(10794,'freundlich sein zu',10005,10019),(10795,'быть дружественным к кому-то',10005,10018),(10796,'Bereit sein zu',10005,10019),(10797,'быть готовым на что-то',10005,10018),(10798,'aufgeschlossen sein gegenüber',10005,10019),(10799,'быть открытым перед кем-то',10005,10018),(10800,'beschäftigt sein mit',10005,10019),(10801,'быть занятым чем-то',10005,10018),(10802,'gut sein in D',10005,10019),(10803,'быть хорошим в чем-то',10005,10018),(10804,'быть хорошим для чего-то',10005,10018),(10805,'stolz sein auf A',10005,10019),(10806,'быть гордым чем-то',10005,10018),(10807,'traurig sein über',10005,10019),(10808,'быть грустным из-за',10005,10018),(10809,'erstaunt sein über',10005,10019),(10810,'быть изумленным чем-то',10005,10018),(10811,'nett sein zu',10005,10019),(10812,'быть любезным с кем-то',10005,10018),(10813,'adressiert sein an A',10005,10019),(10814,'быть адресованным кому-то',10005,10018),(10815,'fertig sein mit',10005,10019),(10816,'быть готовым с чем-то',10005,10018),(10817,'zurückhaltend sein gegenüber',10005,10019),(10818,'быть сдержанным перед кем-то',10005,10018),(10819,'eifersüchtig sein auf A',10005,10019),(10820,'завидовать чему-то',10005,10018),(10821,'verrückt sein nach',10005,10019),(10822,'сходить с ума из-за',10005,10018),(10823,'erfreut sein über',10005,10019),(10824,'быть обрадованным чем-то',10005,10018),(10825,'(un)beliebt sein bei',10005,10019),(10826,'быть не любимым среди',10005,10018),(10827,'überzeugt sein von',10005,10019),(10828,'быть убежденным в чем-то',10005,10018),(10829,'ärgerlich sein auf A',10005,10019),(10830,'злиться из-за чего-то',10005,10018),(10831,'nachlässig sein in D',10005,10019),(10832,'быть неосторожным в',10005,10018),(10833,'angewiesen sein auf A',10005,10019),(10834,'быть уверенным в чем-то',10005,10018),(10835,'begeistert sein von',10005,10019),(10836,'быть взволнованным чем-то',10005,10018),(10837,'(un)empfindlich sein gegen A',10005,10019),(10838,'быть нечувствительным к чему-то',10005,10018),(10839,'unterteilt sein in A',10005,10019),(10840,'быть разделенным на',10005,10018);
/*!40000 ALTER TABLE `words` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-11 19:18:47
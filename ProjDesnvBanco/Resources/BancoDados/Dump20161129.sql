CREATE DATABASE  IF NOT EXISTS `projeto_banco` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `projeto_banco`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: projeto_banco
-- ------------------------------------------------------
-- Server version	5.5.5-10.0.27-MariaDB

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(100) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `CPF` varchar(11) DEFAULT NULL,
  `Rua` varchar(45) DEFAULT NULL,
  `Complemento` varchar(45) DEFAULT NULL,
  `Bairro` varchar(45) DEFAULT NULL,
  `CEP` varchar(8) DEFAULT NULL,
  `Cidade` varchar(45) DEFAULT NULL,
  `Numero` int(11) DEFAULT NULL,
  `Telefone` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (4,'Francisco Alcofoorado',NULL,'12345678984','Rua Guedes Pereira','Apt 1401','Parnamirim','52060150','Recife',105,NULL),(5,'Francisco ALcoforado',NULL,'78945612320','Rua Guedes Pereira','3Apt1401','Parnamiraim','52060150','Recife',105,NULL),(7,'Mauricio Manoel',NULL,'89756421532','Rua Agamenon Magalhaes','Apt 505','Espinehiro','56897457','Recife',54,NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_cliente`
--

DROP TABLE IF EXISTS `email_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_cliente` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(45) DEFAULT NULL,
  `Primario` int(11) DEFAULT NULL,
  `Codigo_Cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Email_Client_idx` (`Codigo_Cliente`),
  CONSTRAINT `Email_Client` FOREIGN KEY (`Codigo_Cliente`) REFERENCES `cliente` (`Codigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_cliente`
--

LOCK TABLES `email_cliente` WRITE;
/*!40000 ALTER TABLE `email_cliente` DISABLE KEYS */;
INSERT INTO `email_cliente` VALUES (1,'francisco.alcoforado@gmail.com',1,5),(2,'mauricio.manoel@gmail.com',1,7);
/*!40000 ALTER TABLE `email_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_fornecedor`
--

DROP TABLE IF EXISTS `email_fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_fornecedor` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(45) DEFAULT NULL,
  `Primario` int(11) DEFAULT NULL,
  `Codigo_Fornecedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Email_Forn_idx` (`Codigo_Fornecedor`),
  CONSTRAINT `Email_Forn` FOREIGN KEY (`Codigo_Fornecedor`) REFERENCES `fornecedor` (`Codigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_fornecedor`
--

LOCK TABLES `email_fornecedor` WRITE;
/*!40000 ALTER TABLE `email_fornecedor` DISABLE KEYS */;
INSERT INTO `email_fornecedor` VALUES (1,'casas@email.com',1,2),(3,'pern@email.com',1,4),(4,'ferreira.costa@costa.com',1,5);
/*!40000 ALTER TABLE `email_fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_vendedor`
--

DROP TABLE IF EXISTS `email_vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_vendedor` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(45) DEFAULT NULL,
  `Primario` int(11) DEFAULT NULL,
  `Codigo_Vendedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Email_Vend_idx` (`Codigo_Vendedor`),
  CONSTRAINT `Email_Vend` FOREIGN KEY (`Codigo_Vendedor`) REFERENCES `vendedor` (`Codigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_vendedor`
--

LOCK TABLES `email_vendedor` WRITE;
/*!40000 ALTER TABLE `email_vendedor` DISABLE KEYS */;
INSERT INTO `email_vendedor` VALUES (3,'ulisses@email.com',1,3),(4,'mario.lopez@gmail.com',1,4);
/*!40000 ALTER TABLE `email_vendedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (2,'Casas Bahia'),(4,'Pernambucanas'),(5,'Ferreira Costa');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota_fiscal`
--

DROP TABLE IF EXISTS `nota_fiscal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nota_fiscal` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Emitente` varchar(100) DEFAULT NULL,
  `Codigo_Pedido` int(11) DEFAULT NULL,
  `Data_Emissao` date DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Not_Ped_idx` (`Codigo_Pedido`),
  CONSTRAINT `Not_Ped` FOREIGN KEY (`Codigo_Pedido`) REFERENCES `pedido` (`Codigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota_fiscal`
--

LOCK TABLES `nota_fiscal` WRITE;
/*!40000 ALTER TABLE `nota_fiscal` DISABLE KEYS */;
/*!40000 ALTER TABLE `nota_fiscal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo_Vendedor` int(11) DEFAULT NULL,
  `Codigo_Cliente` int(11) DEFAULT NULL,
  `Valor` double DEFAULT NULL,
  `Data_Pedido` date DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Vend_Ped_idx` (`Codigo_Vendedor`),
  KEY `Cli_Ped_idx` (`Codigo_Cliente`),
  CONSTRAINT `Cli_Ped` FOREIGN KEY (`Codigo_Cliente`) REFERENCES `cliente` (`Codigo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Vend_Ped` FOREIGN KEY (`Codigo_Vendedor`) REFERENCES `vendedor` (`Codigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (3,4,7,1450,'2016-11-29'),(4,3,5,600,'2016-11-29');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) DEFAULT NULL,
  `Valor` double DEFAULT NULL,
  `Categoria` varchar(45) DEFAULT NULL,
  `Codigo_Fornecedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Fornecedor_idx` (`Codigo_Fornecedor`),
  CONSTRAINT `Fornecedor` FOREIGN KEY (`Codigo_Fornecedor`) REFERENCES `fornecedor` (`Codigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (2,'Bola',20,'Esporte',2),(4,'Sapato',250,'Vestimenta',2),(5,'Tenis Adidas',300,'Tenis',4);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone_cliente`
--

DROP TABLE IF EXISTS `telefone_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefone_cliente` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Telefone` varchar(45) DEFAULT NULL,
  `Codigo_Cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Client_Tel_idx` (`Codigo_Cliente`),
  CONSTRAINT `Client_Tel` FOREIGN KEY (`Codigo_Cliente`) REFERENCES `cliente` (`Codigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone_cliente`
--

LOCK TABLES `telefone_cliente` WRITE;
/*!40000 ALTER TABLE `telefone_cliente` DISABLE KEYS */;
INSERT INTO `telefone_cliente` VALUES (1,'32641785',5),(2,'34415878',7);
/*!40000 ALTER TABLE `telefone_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone_fornecedor`
--

DROP TABLE IF EXISTS `telefone_fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefone_fornecedor` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Telefone` varchar(45) DEFAULT NULL,
  `Codigo_Fornecedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Forn_Tel_idx` (`Codigo_Fornecedor`),
  CONSTRAINT `Forn_Tel` FOREIGN KEY (`Codigo_Fornecedor`) REFERENCES `fornecedor` (`Codigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone_fornecedor`
--

LOCK TABLES `telefone_fornecedor` WRITE;
/*!40000 ALTER TABLE `telefone_fornecedor` DISABLE KEYS */;
INSERT INTO `telefone_fornecedor` VALUES (1,'3548795',4),(2,'32478965',5);
/*!40000 ALTER TABLE `telefone_fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone_vendedor`
--

DROP TABLE IF EXISTS `telefone_vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefone_vendedor` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Telefone` varchar(45) DEFAULT NULL,
  `Codigo_Vendedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Vend_Tel_idx` (`Codigo_Vendedor`),
  CONSTRAINT `Vend_Tel` FOREIGN KEY (`Codigo_Vendedor`) REFERENCES `vendedor` (`Codigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone_vendedor`
--

LOCK TABLES `telefone_vendedor` WRITE;
/*!40000 ALTER TABLE `telefone_vendedor` DISABLE KEYS */;
INSERT INTO `telefone_vendedor` VALUES (2,'32654789',3),(3,'34234585',4);
/*!40000 ALTER TABLE `telefone_vendedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venda` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo_Pedido` int(11) DEFAULT NULL,
  `Codigo_Produto` int(11) DEFAULT NULL,
  `Quantidade` int(11) DEFAULT NULL,
  `Valor` double DEFAULT NULL,
  `Data_Venda` date DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Prod_Vend_idx` (`Codigo_Produto`),
  KEY `Ped_Vend_idx` (`Codigo_Pedido`),
  CONSTRAINT `Ped_Vend` FOREIGN KEY (`Codigo_Pedido`) REFERENCES `pedido` (`Codigo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Prod_Vend` FOREIGN KEY (`Codigo_Produto`) REFERENCES `produto` (`Codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (3,3,4,5,1250,'2016-11-29'),(4,3,2,10,200,'2016-11-29'),(5,4,5,2,600,'2016-11-29');
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedor` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(100) DEFAULT NULL,
  `Senha` varchar(10) DEFAULT NULL,
  `CPF` varchar(11) DEFAULT NULL,
  `Rua` varchar(45) DEFAULT NULL,
  `Complemento` varchar(45) DEFAULT NULL,
  `Bairro` varchar(45) DEFAULT NULL,
  `CEP` varchar(8) DEFAULT NULL,
  `Cidade` varchar(45) DEFAULT NULL,
  `Numero` int(11) DEFAULT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (3,'Ulisses','bola','78945612325','Rua Palmira','Apt 901','Emeterio','2547895','Recife',145),(4,'Mario Lopez','bola','89547856325','Rua Maripolsa','Apt 102','Peixinhos','7854126','Olinda',78);
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-29 20:48:46

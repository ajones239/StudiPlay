CREATE DATABASE  IF NOT EXISTS `quizprogram` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `quizprogram`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: quizprogram
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `examId` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `quizId` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `correct` int(11) DEFAULT NULL,
  PRIMARY KEY (`examId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `questionId` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(100) DEFAULT NULL,
  `option1` varchar(45) DEFAULT NULL,
  `option2` varchar(45) DEFAULT NULL,
  `option3` varchar(45) DEFAULT NULL,
  `option4` varchar(45) DEFAULT NULL,
  `correctIndex` int(11) DEFAULT NULL,
  `help` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`questionId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quiz` (
  `quizId` int(11) NOT NULL AUTO_INCREMENT,
  `quizName` varchar(45) DEFAULT NULL,
  `subject` varchar(45) DEFAULT NULL,
  `quizKey` varchar(45) DEFAULT NULL,
  `totalQuestions` int(11) DEFAULT NULL,
  `timeLimit` int(11) DEFAULT NULL,
  `teacherId` int(11) DEFAULT NULL,
  `practice` int(11) DEFAULT NULL,
  PRIMARY KEY (`quizId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quizQuestionHelper`
--

DROP TABLE IF EXISTS `quizQuestionHelper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quizQuestionHelper` (
  `quizQuestionId` int(11) NOT NULL AUTO_INCREMENT,
  `quizId` int(11) DEFAULT NULL,
  `questionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`quizQuestionId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*
 * Default quiz for Basic Math - Easy: 20 questions on addition, subtraction, multiplication, division, and powers.
 */
INSERT INTO quiz(quizName, subject, quizKey, totalQuestions, timeLimit, teacherId, practice) 
	VALUES('Default: Basic Math - Easy', 'Basic Math - Easy', 'default0', 10, 5, -1, 1);
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('3 * 7 = ?', '14', '21', '18', '24', 1, '3 groups of 7 = 7 + 7 + 7');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('18 + 22 = ?', '38', '42', '40', '256', 2, '18 = 10 + 8; 22 = 20 + 2');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('81 / 9 = ?', '9', '8', '7', '10', 0, '9 * ? = 81');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('44 + 57 = ?', '101', '100', '102', '103', 0, '44 = 40 + 4; 57 = 60 - 3');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('20 * 20 = ?', '40', '400', '160', '1600', 1, '20 * 20 = 2 * 2 * 10 * 10');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('22 / 6 = 3 remainder ?', '2', '3', '4', '5', 2, '22 - 3 * 6 = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('66 - 48 = ?', '17', '16', '20', '18', 3, '66 - 48 = 26 - 8');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('2^4 = ?', '4', '8', '16', '32', 2, '2^4 = (2^2)^2');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('6 + 22 - 6', '22', '0', '76', '1488', 0, '6 - 6 = 0');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('(587 * 234)^0 = ?', '137358', '454534', '0', '1', 3, 'what is anything raised to 0 power?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));

/*
 * Default quiz for Basic Math - Medium: 10 questions on addition, subtraction, multiplication, division, and powers.
 */
INSERT INTO quiz(quizName, subject, quizKey, totalQuestions, timeLimit, teacherId, practice) 
	VALUES('Default: Basic Math - Medium', 'Basic Math - Medium', 'default1', 10, 3, -1, 1);
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('3 * 7 = ?', '14', '21', '18', '24', 1, '3 groups of 7 = 7 + 7 + 7');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('18 + 22 = ?', '38', '42', '40', '256', 2, '18 = 10 + 8; 22 = 20 + 2');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('81 / 9 = ?', '9', '8', '7', '10', 0, '9 * ? = 81');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('44 + 57 = ?', '101', '100', '102', '103', 0, '44 = 40 + 4; 57 = 60 - 3');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('20 * 20 = ?', '40', '400', '160', '1600', 1, '20 * 20 = 2 * 2 * 10 * 10');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('22 / 6 = 3 remainder ?', '2', '3', '4', '5', 2, '22 - 3 * 6 = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('66 - 48 = ?', '17', '16', '20', '18', 3, '66 - 48 = 26 - 8');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('2^4 = ?', '4', '8', '16', '32', 2, '2^4 = (2^2)^2');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('6 + 22 - 6', '22', '0', '76', '1488', 0, '6 - 6 = 0');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('(587 * 234)^0 = ?', '137358', '454534', '0', '1', 3, 'what is anything raised to 0 power?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));

/*
 * Default quiz for Basic Math - Hard: 10 questions on addition, subtraction, multiplication, division, and powers.
 */
INSERT INTO quiz(quizName, subject, quizKey, totalQuestions, timeLimit, teacherId, practice) 
	VALUES('Default: Basic Math - Hard', 'Basic Math - Hard', 'default2', 10, 1, -1, 1);
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('3 * 7 = ?', '14', '21', '18', '24', 1, '3 groups of 7 = 7 + 7 + 7');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('18 + 22 = ?', '38', '42', '40', '256', 2, '18 = 10 + 8; 22 = 20 + 2');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('81 / 9 = ?', '9', '8', '7', '10', 0, '9 * ? = 81');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('44 + 57 = ?', '101', '100', '102', '103', 0, '44 = 40 + 4; 57 = 60 - 3');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('20 * 20 = ?', '40', '400', '160', '1600', 1, '20 * 20 = 2 * 2 * 10 * 10');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('22 / 6 = 3 remainder ?', '2', '3', '4', '5', 2, '22 - 3 * 6 = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('66 - 48 = ?', '17', '16', '20', '18', 3, '66 - 48 = 26 - 8');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('2^4 = ?', '4', '8', '16', '32', 2, '2^4 = (2^2)^2');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('6 + 22 - 6', '22', '0', '76', '1488', 0, '6 - 6 = 0');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('(587 * 234)^0 = ?', '137358', '454534', '0', '1', 3, 'what is anything raised to 0 power?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));

/*
 * Default quiz for Algebra - Easy: 10 basic algebra questions
 */
INSERT INTO quiz(quizName, subject, quizKey, totalQuestions, timeLimit, teacherId, practice)
	VALUES('Default: Algebra - Easy', 'Algebra - Easy', 'default3', 10, 5, -1, 1);
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('3x + 2 = 11. x = ?', '3', '5', '6', '-1', 0, ' first subtract two from both sides');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x^2 = 4. Find x.', '2', '-2', 'All of the above', 'None of the above', 2, '2^2 = ? (-2)^2 = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('8x + 16 = -8. x = ?', '-4', '4', '3', '-3', 3, 'first divide both sides by 8');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x^3 - 27 = 0. Find x.', '3', '-3', 'All of the above', 'None of the above', 0, 'What is the cubed root of 27?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('3x + 5 = -7x -15. Find x.', '5', '-5', '2', '-2', 3, 'first isolate the x terms');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('16x^2 + 16x^2 = 0. x = ?', '0', '1', '-1', '1488', 0, '0 divided by anything = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('4x - 16 = 32x + 12. Find x.', '1', '-1', '2', '-2', 1, 'first isolate the x terms');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x(3x + 17)^0 = 1. x = ?', '1', '223', '76', '4', 0, 'What is anything raised to 0 power?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('5x + 7 = 92. x = ?', '15', '16', '19', '17', 3, 'first isolate the x term');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x^2 - 8 = 1. Find x.', '+/- 3', '-3', '3', '+/- 9', 0, 'sqrt(9) = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));

/*
 * Default quiz for Algebra - Medium: 10 basic algebra questions
 */
INSERT INTO quiz(quizName, subject, quizKey, totalQuestions, timeLimit, teacherId, practice)
	VALUES('Default: Algebra - Medium', 'Algebra - Medium', 'default4', 10, 3, -1, 1);
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('3x + 2 = 11. x = ?', '3', '5', '6', '-1', 0, ' first subtract two from both sides');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x^2 = 4. Find x.', '2', '-2', 'All of the above', 'None of the above', 2, '2^2 = ? (-2)^2 = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('8x + 16 = -8. x = ?', '-4', '4', '3', '-3', 3, 'first divide both sides by 8');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x^3 - 27 = 0. Find x.', '3', '-3', 'All of the above', 'None of the above', 0, 'What is the cubed root of 27?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('3x + 5 = -7x -15. Find x.', '5', '-5', '2', '-2', 3, 'first isolate the x terms');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('16x^2 + 16x^2 = 0. x = ?', '0', '1', '-1', '1488', 0, '0 divided by anything = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('4x - 16 = 32x + 12. Find x.', '1', '-1', '2', '-2', 1, 'first isolate the x terms');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x(3x + 17)^0 = 1. x = ?', '1', '223', '76', '4', 0, 'What is anything raised to 0 power?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('5x + 7 = 92. x = ?', '15', '16', '19', '17', 3, 'first isolate the x term');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x^2 - 8 = 1. Find x.', '+/- 3', '-3', '3', '+/- 9', 0, 'sqrt(9) = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));

/*
 * Default quiz for Algebra - Hard: 10 basic algebra questions
 */
INSERT INTO quiz(quizName, subject, quizKey, totalQuestions, timeLimit, teacherId, practice)
	VALUES('Default: Algebra - Hard', 'Algebra - Hard', 'default5', 10, 1, -1, 1);
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('3x + 2 = 11. x = ?', '3', '5', '6', '-1', 0, ' first subtract two from both sides');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x^2 = 4. Find x.', '2', '-2', 'All of the above', 'None of the above', 2, '2^2 = ? (-2)^2 = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('8x + 16 = -8. x = ?', '-4', '4', '3', '-3', 3, 'first divide both sides by 8');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x^3 - 27 = 0. Find x.', '3', '-3', 'All of the above', 'None of the above', 0, 'What is the cubed root of 27?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('3x + 5 = -7x -15. Find x.', '5', '-5', '2', '-2', 3, 'first isolate the x terms');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('16x^2 + 16x^2 = 0. x = ?', '0', '1', '-1', '1488', 0, '0 divided by anything = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('4x - 16 = 32x + 12. Find x.', '1', '-1', '2', '-2', 1, 'first isolate the x terms');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x(3x + 17)^0 = 1. x = ?', '1', '223', '76', '4', 0, 'What is anything raised to 0 power?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('5x + 7 = 92. x = ?', '15', '16', '19', '17', 3, 'first isolate the x term');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('x^2 - 8 = 1. Find x.', '+/- 3', '-3', '3', '+/- 9', 0, 'sqrt(9) = ?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));

/*
 * Default quiz for Science - Easy: 10 basic science questions
 */
INSERT INTO quiz(quizName, subject, quizKey, totalQuestions, timeLimit, teacherId, practice)
	VALUES('Default: Science - Easy', 'Science - Easy', 'default6', 10, 5, -1, 1);
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the chemical formula for water?', 'CO2', 'H2O', 'CH3', 'WAt3R', 1, 'water molecules have 3 atoms');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('How many planets in the solar system?', '8', '9', '7', 'Impossible to know', 0, 'Pluto is not a planet');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is gamete cell division called?', 'mitosis', 'meiosis', 'osmosis', 'sex', 1, 'answer is not osmosis or sex');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the opposite of photosynthesis?', 'photodestructis', 'apt', 'cellular recognition', 'cellular respiration', 3, 'answer is not apt or photodestructis');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the main gas in the atmosphere?', 'water', 'CO2', 'Oxygen', 'Nitrogen', 3, 'answer is not water or Oxygen');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is an AU?', 'distance from Earth to sun', 'distance light travels in a year', 'speed of sound in a vacuum', '9.8m/s/s', 0, 'it is a distance');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the term for rate of change of position?', 'speed', 'acceleration', 'd/dx(v(x))', 'velocity', 3, 'What is the difference between speed and velocity?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What does the sun do to create energy?', 'combustion', 'fission', 'helioncis', 'fusion', 3, 'answer is not helioncis or combustion');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What are single cell organisms without nuclear membranes?', 'bacteria', 'eukaryotes', 'prokaryotes', 'phages', 2, 'answer is not a or d');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What family are crows in?', 'covid', 'corvidae', 'birdidae', 'who cares', 1, 'families end with ae');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));

/*
 * Default quiz for Science - Medium: 10 basic science questions
 */
INSERT INTO quiz(quizName, subject, quizKey, totalQuestions, timeLimit, teacherId, practice)
	VALUES('Default: Science - Medium', 'Science - Medium', 'default7', 10, 3, -1, 1);
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the chemical formula for water?', 'CO2', 'H2O', 'CH3', 'WAt3R', 1, 'water molecules have 3 atoms');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('How many planets in the solar system?', '8', '9', '7', 'Impossible to know', 0, 'Pluto is not a planet');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is gamete cell division called?', 'mitosis', 'meiosis', 'osmosis', 'sex', 1, 'answer is not osmosis or sex');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the opposite of photosynthesis?', 'photodestructis', 'apt', 'cellular recognition', 'cellular respiration', 3, 'answer is not apt or photodestructis');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the main gas in the atmosphere?', 'water', 'CO2', 'Oxygen', 'Nitrogen', 3, 'answer is not water or Oxygen');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is an AU?', 'distance from Earth to sun', 'distance light travels in a year', 'speed of sound in a vacuum', '9.8m/s/s', 0, 'it is a distance');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the term for rate of change of position?', 'speed', 'acceleration', 'd/dx(v(x))', 'velocity', 3, 'What is the difference between speed and velocity?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What does the sun do to create energy?', 'combustion', 'fission', 'helioncis', 'fusion', 3, 'answer is not helioncis or combustion');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What are single cell organisms without nuclear membranes?', 'bacteria', 'eukaryotes', 'prokaryotes', 'phages', 2, 'answer is not a or d');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What family are crows in?', 'covid', 'corvidae', 'birdidae', 'who cares', 1, 'families end with ae');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));

/*
 * Default quiz for Science - Hard: 10 basic science questions
 */
INSERT INTO quiz(quizName, subject, quizKey, totalQuestions, timeLimit, teacherId, practice)
	VALUES('Default: Science - Hard', 'Science - Hard', 'default8', 10, 1, -1, 1);
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the chemical formula for water?', 'CO2', 'H2O', 'CH3', 'WAt3R', 1, 'water molecules have 3 atoms');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('How many planets in the solar system?', '8', '9', '7', 'Impossible to know', 0, 'Pluto is not a planet');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is gamete cell division called?', 'mitosis', 'meiosis', 'osmosis', 'sex', 1, 'answer is not osmosis or sex');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the opposite of photosynthesis?', 'photodestructis', 'apt', 'cellular recognition', 'cellular respiration', 3, 'answer is not apt or photodestructis');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the main gas in the atmosphere?', 'water', 'CO2', 'Oxygen', 'Nitrogen', 3, 'answer is not water or Oxygen');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is an AU?', 'distance from Earth to sun', 'distance light travels in a year', 'speed of sound in a vacuum', '9.8m/s/s', 0, 'it is a distance');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What is the term for rate of change of position?', 'speed', 'acceleration', 'd/dx(v(x))', 'velocity', 3, 'What is the difference between speed and velocity?');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What does the sun do to create energy?', 'combustion', 'fission', 'helioncis', 'fusion', 3, 'answer is not helioncis or combustion');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What are single cell organisms without nuclear membranes?', 'bacteria', 'eukaryotes', 'prokaryotes', 'phages', 2, 'answer is not a or d');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));
INSERT INTO question(question, option1, option2, option3, option4, correctIndex, help) 
	VALUES('What family are crows in?', 'covid', 'corvidae', 'birdidae', 'who cares', 1, 'families end with ae');
INSERT INTO quizQuestionHelper(quizId, questionId) VALUES(
	(SELECT MAX(`quizId`) FROM quiz), (SELECT MAX(`questionId`) FROM question));

-- Dump completed on 2020-04-25 19:20:45

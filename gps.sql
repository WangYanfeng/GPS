/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : gps

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2015-03-01 17:50:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `gps_data`
-- ----------------------------
DROP TABLE IF EXISTS `gps_data`;
CREATE TABLE `gps_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationNo` int(11) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gps_data
-- ----------------------------
INSERT INTO `gps_data` VALUES ('6', '1', '11425.96E', '3802.46N', '2', '2015-02-05 17:10:35');

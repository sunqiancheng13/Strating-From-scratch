CREATE TABLE `message` (
  `名称` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `starttime` datetime NOT NULL,
  `endtime` datetime NOT NULL,
  `startprice` float(255,2) NOT NULL,
  `operation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`名称`)
)



CREATE TABLE `record` (
  `name` varchar(255) NOT NULL,
  `time` datetime DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  `payname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`name`)
)



CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `identity` varchar(255) NOT NULL,
  `phone` int NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `postal` varchar(255) DEFAULT NULL,
  `jurisdiction` int DEFAULT NULL,
  PRIMARY KEY (`username`)
)
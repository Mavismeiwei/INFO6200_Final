/*
 Navicat Premium Data Transfer

 Source Server         : local-mysql
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : csye6200

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 15/04/2023 04:10:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_memorize_status
-- ----------------------------
DROP TABLE IF EXISTS `user_memorize_status`;
CREATE TABLE `user_memorize_status`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `word_id` int(0) NOT NULL,
  `score` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_fk1`(`user_id`) USING BTREE,
  INDEX `word_id_fk1`(`word_id`) USING BTREE,
  CONSTRAINT `user_id_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `word_id_fk1` FOREIGN KEY (`word_id`) REFERENCES `word` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_memorize_status
-- ----------------------------
INSERT INTO `user_memorize_status` VALUES (1, 1, 1, 0);
INSERT INTO `user_memorize_status` VALUES (2, 1, 2, 0);
INSERT INTO `user_memorize_status` VALUES (3, 1, 3, 0);
INSERT INTO `user_memorize_status` VALUES (4, 1, 4, 0);
INSERT INTO `user_memorize_status` VALUES (5, 2, 1, 0);
INSERT INTO `user_memorize_status` VALUES (6, 2, 3, 0);
INSERT INTO `user_memorize_status` VALUES (7, 1, 30, 0);
INSERT INTO `user_memorize_status` VALUES (8, 1, 31, 0);
INSERT INTO `user_memorize_status` VALUES (9, 1, 32, 13);

SET FOREIGN_KEY_CHECKS = 1;

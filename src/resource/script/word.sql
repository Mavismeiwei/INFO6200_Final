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

 Date: 15/04/2023 04:10:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for word
-- ----------------------------
DROP TABLE IF EXISTS `word`;
CREATE TABLE `word`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增长 ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单词英文',
  `meaning_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单词英文释义',
  `meaning_cn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单词中文释义',
  `word_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单词词性',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of word
-- ----------------------------
INSERT INTO `word` VALUES (1, 'obstrusive', 'having or showing a disposition to obtrude, as by imposing oneself or one\'s opinions on others', '鲁莽的，显眼的', 'adj.');
INSERT INTO `word` VALUES (2, 'recapitulate', 'You can say that you are going to recapitulate the main points of an explanation, argument, or description when you want to draw attention to the fact that you are going to repeat the most important points as a summary.', '重述要点，简要回顾', 'v.');
INSERT INTO `word` VALUES (3, 'chivalrous', 'A chivalrous man is polite, kind, and unselfish, especially towards women.', '彬彬有礼的，有骑士精神的', 'adj.');
INSERT INTO `word` VALUES (4, 'word', 'This is a word', '单词', 'noun');
INSERT INTO `word` VALUES (15, '2', '2', '2', '2');
INSERT INTO `word` VALUES (16, '3', '3', '3', '3');
INSERT INTO `word` VALUES (17, '4', '44', '4', '4');
INSERT INTO `word` VALUES (18, '213', '213123', '21321', '123');
INSERT INTO `word` VALUES (30, 'hhh', 'hhh1', 'hhh', 'hhh');
INSERT INTO `word` VALUES (31, 'hhh123', 'hhh1', 'hhh', 'hhh');
INSERT INTO `word` VALUES (32, 'hhh1234', 'hhh1', 'hhh', 'hhh');
INSERT INTO `word` VALUES (33, 'hhh12', 'hhh1', 'hhh', 'hhh');

SET FOREIGN_KEY_CHECKS = 1;

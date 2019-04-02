/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : timetable

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 02/04/2019 11:23:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` varchar(32) NOT NULL COMMENT '课程id',
  `name` varchar(64) DEFAULT NULL COMMENT '课程名称',
  `descreption` text COMMENT '课程描述',
  `price` decimal(6,2) DEFAULT NULL COMMENT '价格',
  `period` int(11) DEFAULT NULL COMMENT '课时',
  `teacher_id` varchar(32) DEFAULT NULL COMMENT '教师id',
  `status` int(11) DEFAULT NULL COMMENT '课程状态：0-待发布，1-已发布，2-下架',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(64) DEFAULT NULL COMMENT '学生名称',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `truancy_num` int(11) DEFAULT NULL COMMENT '旷课次数',
  `leave_num` int(11) DEFAULT NULL COMMENT '请假次数',
  `score` int(11) DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for student_purchased_course
-- ----------------------------
DROP TABLE IF EXISTS `student_purchased_course`;
CREATE TABLE `student_purchased_course` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `student_id` varchar(32) DEFAULT NULL COMMENT '学生id',
  `course_id` varchar(32) DEFAULT NULL COMMENT '课程id',
  `remain` int(11) DEFAULT NULL COMMENT '课时余量',
  `due_time` datetime DEFAULT NULL COMMENT '课程到期时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for student_timtable
-- ----------------------------
DROP TABLE IF EXISTS `student_timtable`;
CREATE TABLE `student_timtable` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `student_id` varchar(32) DEFAULT NULL COMMENT '学生id',
  `course_id` varchar(32) DEFAULT NULL COMMENT '课程id',
  `course_time` datetime DEFAULT NULL COMMENT '上课时间',
  `status` int(11) DEFAULT NULL COMMENT '状态，0-请假，1-旷课，2-已参与上课',
  `homework` text COMMENT '课后作业',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '用户ID',
  `usename` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户昵称',
  `name` varchar(64) DEFAULT NULL COMMENT '用户真实姓名',
  `account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信号',
  `open_id` varchar(50) NOT NULL COMMENT '用户的微信open ID',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `img_url` varchar(50) DEFAULT NULL COMMENT '微信头像',
  `photo_url` varchar(50) DEFAULT NULL COMMENT '个人头像',
  `gender` int(11) DEFAULT NULL COMMENT '性别，0-女；1-男',
  `access_token` varchar(50) DEFAULT NULL COMMENT '用户token',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型：0-管理员；1-教师；2-学生',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` varchar(32) NOT NULL COMMENT '标签ID',
  `name` varchar(20) DEFAULT NULL COMMENT '标签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(32) NOT NULL COMMENT '教师ID',
  `name` varchar(64) DEFAULT NULL COMMENT '教师名称',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '教师简介，500字以内',
  `tag` varchar(20) DEFAULT NULL COMMENT '教师标签，主要传授什么课程',
  `score` int(11) DEFAULT NULL COMMENT '教师综合评分',
  `photo` varchar(255) DEFAULT NULL COMMENT '照片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for teacher_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `teacher_evaluation`;
CREATE TABLE `teacher_evaluation` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `teacher_id` varchar(32) DEFAULT NULL COMMENT '教师id',
  `teacher_name` varchar(64) DEFAULT NULL COMMENT '教师名称',
  `score` int(11) DEFAULT NULL COMMENT '评分',
  `content` text COMMENT '评价内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for teacher_timetable
-- ----------------------------
DROP TABLE IF EXISTS `teacher_timetable`;
CREATE TABLE `teacher_timetable` (
  `id` varchar(32) NOT NULL COMMENT '教师课表id',
  `teacher_id` varchar(32) DEFAULT NULL COMMENT '教师id',
  `course_id` varchar(32) DEFAULT NULL COMMENT '课程ID',
  `course_type` int(11) DEFAULT NULL COMMENT '课程类型：1-正式课程；2-试听课程',
  `number` int(11) DEFAULT NULL,
  `homework` text COMMENT '课后作业',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;

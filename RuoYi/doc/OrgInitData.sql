-- 插入基础组织结构数据

-- 部门表 ;
DELETE FROM Sys_Dept;
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (100, 0, '0', '集团总部', 0, 'admin', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-03 19:49:11', '', NULL);
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1001, 100, '0,100', '集团市场部', 1, 'admin', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-03 19:49:11', 'admin', '2023-03-07 14:39:43');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1002, 100, '0,100', '集团研发部', 2, 'admin', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-03 19:49:11', 'admin', '2023-03-07 14:40:01');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1003, 100, '0,100,101', '集团服务部', 1, 'admin', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-03 19:49:11', 'admin', '2023-03-07 14:43:08');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1004, 100, '0,100,101', '集团财务部', 2, 'zhanghaicheng', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-03 19:49:11', 'admin', '2023-04-04 14:06:01');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1005, 100, '0,100,101', '集团人力资源部', 3, 'admin', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-03 19:49:11', 'admin', '2023-03-07 14:44:07');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1060, 100, '0,100,101', '南方分公司', 4, 'admin', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-03 19:49:11', 'admin', '2023-03-07 14:44:24');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1061, 1060, '0,100,101', '市场部', 5, 'admin', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-03 19:49:11', 'admin', '2023-03-07 14:44:44');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1062, 1060, '0,100,102', '财务部', 1, 'admin', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-03 19:49:11', '', NULL);
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1063, 1060, '0,100,102', '销售部', 2, 'admin', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-03 19:49:11', '', NULL);
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1070, 100, '0,100', '北方分公司', 3, '', '', '', '0', '0', 'admin', '2023-03-07 14:45:08', 'admin', '2023-03-07 14:45:21');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1071, 1070, '0,100', '市场部', 3, '', '', '', '0', '0', 'admin', '2023-03-07 14:45:08', 'admin', '2023-03-07 14:45:21');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1072, 1070, '0,100', '财务部', 3, '', '', '', '0', '0', 'admin', '2023-03-07 14:45:08', 'admin', '2023-03-07 14:45:21');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1073, 1070, '0,100', '销售部', 3, '', '', '', '0', '0', 'admin', '2023-03-07 14:45:08', 'admin', '2023-03-07 14:45:21');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1099, 100, '0,100', '外来单位', 3, '', '', '', '0', '0', 'admin', '2023-03-07 14:45:08', 'admin', '2023-03-07 14:45:21');

-- 用户表 ;
DELETE FROM sys_user;
-- 总经理部 ;
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, 100, 'admin', '驰骋BMP超管', '00', '6666@163.com', '15888888888', '0', '/profile/avatar/2023/10/24/blob_20231024153507A001.png', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '27.211.7.148', '2023-12-28 12:03:29', 'admin', '2023-08-13 20:49:44', '', '2023-12-28 12:03:28', '管理员');
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, 100, 'yuwen', '钰雯', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
-- 市场部 ;
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, 1001, 'zhanghaicheng', '张海成', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, 1001, 'zhangyifan', '张一帆', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (5, 1001, 'zhoushengyu', '周升雨', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
-- 研发部 ;
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (6, 1002, 'qifenglin', '祁凤林', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (7, 1002, 'zhoutianjiao', '周天娇', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
-- 服务部 ;
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (8, 1003, 'guoxiangbin', '郭祥斌', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (9, 1003, 'fuhui', '福惠', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
-- 财务部 ;
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (10, 1004, 'yangyilei', '杨依雷', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (11, 1004, 'guobaogeng', '郭宝庚', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
-- 人力资源部 ;
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (12, 1005, 'liping', '李萍', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (13, 1005, 'liyan', '李言', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');
-- 外来单位人员
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (14, 1099, 'Guest', '外来人员', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-08-13 20:49:44', 'admin', '2023-08-13 20:49:44', '', NULL, '测试员');



-- 岗位
DELETE FROM sys_post;
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`,  `post_sort`,`station_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, 'ceo', '总经理', 1,2, '0', 'admin', '2022-01-03 19:49:13', 'admin', '2023-03-07 14:46:04', '');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `station_type`,`status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, 'se', '市场部经理', 2,2, '0', 'admin', '2022-01-03 19:49:13', 'admin', '2023-03-07 14:46:13', '');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `station_type`,`status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, 'hr', '研发部经理', 3,2, '0', 'admin', '2022-01-03 19:49:13', 'admin', '2023-03-07 14:46:20', '');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `station_type`,`status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (5, 'kfjl', '客服部经理', 4, 2,'0', 'admin', '2023-03-07 14:46:38', '', NULL, NULL);
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `station_type`,`status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (6, 'cwbjl', '财务部经理', 6,2, '0', 'admin', '2023-03-07 14:46:52', '', NULL, NULL);
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `station_type`,`status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (7, 'rlzybjl', '人力资源部经理',2, 7, '0', 'admin', '2023-03-07 14:47:08', '', NULL, NULL);
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `station_type`,`status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (8, 'xsryg', '销售人员岗', 8, 3,'0', 'admin', '2023-03-07 14:47:20', '', NULL, NULL);
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `station_type`,`status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (9, 'cxyg', '程序员岗', 10,3, '0', 'admin', '2023-03-07 14:47:31', '', NULL, NULL);
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `station_type`,`status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (10, 'jsfwg', '技术服务岗', 11,3, '0', 'admin', '2023-03-07 14:47:41', '', NULL, NULL);
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `station_type`,`status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (11, 'cng', '出纳岗', 12,3, '0', 'admin', '2023-03-07 14:47:52', '', NULL, NULL);
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`,`station_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (12, 'rlzyzlg', '人力资源助理岗',3, 13, '0', 'admin', '2023-03-07 14:48:05', '', NULL, NULL);
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `station_type`,`status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (13, 'wlryg', '外来人员岗', 14, 3,'0', 'admin', '2023-03-07 14:48:16', '', NULL, NULL);

-- 用户职位表
DELETE FROM sys_user_post;
-- 市场部;
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (3, 2);
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (4, 2);
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (5, 2);
-- 研发部 ;
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (6, 3);
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (7, 3);
-- 服务部;
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (8, 10);
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (9, 10);
-- 财务部;
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (10, 6);
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (11, 6);
-- 人力资源部;
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (12,7);
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (13,7);
-- 外来单位人员;
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (14,13);

-- 用户岗位表
DELETE FROM sys_user_post;
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (2, 1);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (3, 2);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (4, 7);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (5, 7);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (6, 3);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (7, 8);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (8, 4);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (9, 10);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (10, 5);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (11, 10);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (12, 6);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (13, 11);
INSERT INTO `sys_user_post` (`user_id`, `post_id`)  VALUES (14, 12);



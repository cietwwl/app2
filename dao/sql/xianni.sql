DROP TABLE IF EXISTS tb_templatetest;
CREATE TABLE `tb_templatetest` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '姓名',
  `createTime` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试模板表';

DROP TABLE IF EXISTS tb_u_player_info;
CREATE TABLE `tb_u_player_info` (
  `playerId` bigint(20) NOT NULL COMMENT '玩家角色ID',
  `userId` BIGINT(11) NOT NULL COMMENT '用户ID',
  `nickName` varchar(255) NOT NULL COMMENT '角色名字',
  `level` int(11) NOT NULL COMMENT '等级',
  `exp` BIGINT(11) NOT NULL COMMENT '经验',
  `toalExp` BIGINT(11) NOT NULL COMMENT '经验',
  `money` int(11) NOT NULL COMMENT '金币(灵石)',
  `bindCash` int(11) NOT NULL COMMENT '绑定元宝(绑定仙玉)',
  `vipLevel` smallint(6) NOT NULL DEFAULT '0' COMMENT 'VIP等级',
  `fight` int(11) NOT NULL DEFAULT '0' COMMENT '战斗力',
  `skinId` int(11) NOT NULL DEFAULT '0' COMMENT '皮肤ID',
  PRIMARY KEY (`playerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户基础数据表';

DROP TABLE IF EXISTS tb_u_userinfo;
CREATE TABLE tb_u_userinfo(
userId BIGINT NOT NULL COMMENT '用户ID',
userName VARCHAR(100) NOT NULL COMMENT '用户名',
passWords VARCHAR(100) NOT NULL COMMENT '密码',
PRIMARY KEY (userId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户表';




DROP TABLE IF EXISTS tb_u_item_info;
CREATE TABLE `tb_u_item_info` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `templateId` int(11) NOT NULL COMMENT '模板ID',
  `playerId` bigint(20) NOT NULL COMMENT '玩家ID',
  `pos` int(11) NOT NULL COMMENT '背包位置',
  `isExist` tinyint(1) DEFAULT NULL COMMENT '是否存在',
  `objectId` bigint(20) NOT NULL COMMENT '所属对象ID（多对象装备背包使用）',
  `bagType` int(11) NOT NULL COMMENT '背包类型',
  `isBinds` tinyint(1) DEFAULT NULL COMMENT '是否绑定',
  `isUsed` tinyint(1) DEFAULT NULL COMMENT '是否使用',
  `validDate` int(11) NOT NULL COMMENT '单位分钟',
  `beginDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '获得时间',
  `count` int(11) NOT NULL COMMENT '数量',
  `removeType` int(11) NOT NULL COMMENT '删除类型',
  `removeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
  `addType` int(11) NOT NULL COMMENT '添加类型',
  `addDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `playerId` (`playerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户物品表';

CREATE TABLE `tb_u_campaign_info` (
  `id` int(11) NOT NULL COMMENT '序列化自增id',
  `playerId` bigint(20) NOT NULL COMMENT '玩家ID',
  `campaignId` int(11) NOT NULL COMMENT '副本ID',
  `statu` int(11) DEFAULT '0' COMMENT '完成状态',
  `assess` int(11) DEFAULT '0' COMMENT '最好成绩',
  `updateTime` datetime DEFAULT '2016-01-01 00:00:00' COMMENT '完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户副本完成进度表';



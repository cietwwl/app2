CREATE TABLE `tb_log_itemlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'index',
  `templateId` int(11) NOT NULL COMMENT '模板ID',
  `itemId` int(11) NOT NULL COMMENT '物品唯一ID',
  `playerId` bigint(20) NOT NULL COMMENT '角色ID',
  `oldCount` int(11) NOT NULL COMMENT '旧有数量',
  `nowCount` int(11) NOT NULL COMMENT '新数量',
  `oldPro` int(11) NOT NULL COMMENT '装备属性',
  `newPro` int(11) NOT NULL COMMENT '装备属性',
  `oldGrow` int(11) NOT NULL COMMENT '装备成长系数',
  `newGrow` int(11) NOT NULL COMMENT '装备成长系数',
  `oldAwaken` int(11) NOT NULL COMMENT '武器觉醒等级',
  `newAwaken` int(11) NOT NULL COMMENT '武器觉醒等级',
  `oldAwakenPoint` int(11) NOT NULL COMMENT '武器觉醒等级突破点',
  `newAwakenPoint` int(11) NOT NULL COMMENT '武器觉醒等级突破点',
  `oldStone` int(11) NOT NULL COMMENT '插入石头模板',
  `newStone` int(11) NOT NULL COMMENT '插入石头模板',
  `changeType` int(11) NOT NULL COMMENT '变更类型',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='物品日志表';


CREATE TABLE `tb_log_monetarylog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'index',
  `playerId` int(11) NOT NULL COMMENT '角色ID',
  `moneyType` int(11) NOT NULL COMMENT '货币类型',
  `changeWay` bigint(20) NOT NULL COMMENT '变更原因',
  `toalCount` int(11) NOT NULL COMMENT '当前总数',
  `changeCount` int(11) NOT NULL COMMENT '变更数量',
  `changeTime` int(11) NOT NULL COMMENT '变更时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='物品日志表';

CREATE TABLE `tb_log_monetarylog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'index',
  `playerId` bigint(11) NOT NULL COMMENT '角色ID',
  `moneyType` int(11) NOT NULL COMMENT '货币类型',
  `changeWay` int(20) NOT NULL COMMENT '变更原因',
  `toalCount` int(11) NOT NULL COMMENT '当前总数',
  `changeCount` int(11) NOT NULL COMMENT '变更数量',
  `changeTime` datetime NOT NULL COMMENT '变更时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='货币日志表';



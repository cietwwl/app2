DROP TABLE IF EXISTS tb_z_skill_buffer;
CREATE TABLE tb_z_skill_buffer(
templateId INT NOT NULL COMMENT '模板ID',
bufferName VARCHAR(255) COMMENT 'buff名字',
icon	INT  COMMENT 'ICON图标',
isHelpful INT COMMENT '是否有益 1 有益 2 有害',
type INT COMMENT '作用类型',
targetType INT COMMENT '作用目标 0 技能目标 1 自己',
exeWay INT COMMENT '生效时机 1 攻击 2 受伤，3 定时 ,4 ..',
durableType INT COMMENT '耐久类型 1 时间 2 次数 3 同时计数',
exeTime INT COMMENT '生效持续时间(秒)',
exeCount INT COMMENT '执行次数',
cooldown INT  COMMENT '执行间隔时间',
canSuperpose INT COMMENT '是否可叠加',
isTips INT COMMENT '是否显示图标',
isSave INT COMMENT '是否离线保存',
valueType INT COMMENT '作用属性类型',
value  INT COMMENT '固定数值',
valuePercent INT COMMENT '百分比数值',
PRIMARY KEY (templateId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '技能buffer表';


DROP TABLE IF EXISTS tb_z_skillinfo;
CREATE TABLE tb_z_skillinfo(
templateId INT COMMENT '模板ID',
templateName VARCHAR(255) COMMENT '技能名称',
icon INT COMMENT '技能图片',
masterType INT COMMENT '技能主类型',
sonType INT COMMENT '子类型',
level INT COMMENT '等级',
preTemplateId VARCHAR(255) COMMENT '前置技能',
nextTemplateId VARCHAR(255) COMMENT '后置技能',
needGrades    INT COMMENT '需要等级',
useWay  INT COMMENT '使用方式 0被动， 1主动',
actionId INT COMMENT '释放技能ID',
costType INT COMMENT '技能升级消耗类型',
costCount INT COMMENT '升级消耗数量',
propertyIds  VARCHAR(255) COMMENT '技能增加属性ID',
sysBufferIds VARCHAR(255) COMMENT '系统bufferID',
fightBufferIds VARCHAR(255) COMMENT '战斗bufferId',
description VARCHAR(255) COMMENT '描述',
PRIMARY KEY (templateId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '基础技能表';

DROP TABLE IF EXISTS tb_z_skill_actioninfo;
CREATE TABLE tb_z_skill_actioninfo(
templateId INT COMMENT '模板ID',
masterType INT COMMENT '技能主类型',
sonType INT COMMENT '子类型',
combo INT COMMENT '连击',
maxCombo INT COMMENT '最大连击数',
animation INT COMMENT '动作',
audio INT COMMENT '音效',
search INT COMMENT '搜索ID',
effect INT COMMENT '施法效果',
move INT COMMENT '对应位移ID',
camera INT COMMENT '相机ID',
attackTimes INT COMMENT '攻击次数',
costType INT COMMENT '消耗类型 0 无消耗，1元魂，2 气血',
costCount INT COMMENT '消耗数量',
attackType INT COMMENT '攻击类型',
paramValue1 INT COMMENT '攻击参数1',
paramValue2 INT COMMENT '攻击参数2',
paramValue3 INT COMMENT '攻击参数3',
paramParent1 INT COMMENT '攻击参数1%',
paramParent2 INT COMMENT '攻击参数2%',
paramParent3 INT COMMENT '攻击参数3%',
bufferIds VARCHAR(255) COMMENT 'ID集',
random  INT COMMENT '技能随机数',
isCrit INT COMMENT '参与暴击计算',
priority INT COMMENT '技能优先级',
costTime INT COMMENT '技能释放时间()',
cooldown INT COMMENT 'CD时间',
PRIMARY KEY(templateId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '执行技能表';



DROP TABLE IF EXISTS tb_z_skill_property;
CREATE TABLE tb_z_skill_property(
templateId	INT	COMMENT	'属性模板ID',
type	INT	COMMENT	'类型',
soul 	INT	COMMENT	'元魂',
blood 	INT	COMMENT	'气血',
attack 	INT	COMMENT	'攻击',
defence 	INT	COMMENT	'防御',
soulAttack 	INT	COMMENT	'魂攻',
soulDefence 	INT	COMMENT	'魂防',
accurate 	INT	COMMENT	'命中',
dodge 	INT	COMMENT	'闪避',
crit 	INT	COMMENT	'暴击',
critDefence 	INT	COMMENT	'抗暴',
critAddtion 	INT	COMMENT	'暴击伤害',
critCut 	INT	COMMENT	'抗暴减伤',
attackAddtion 	INT	COMMENT	'气血伤害增加',
attackCut 	INT	COMMENT	'气血伤害减免',
soulAttackAddtion 	INT	COMMENT	'元魂伤害增加',
soulAttackCut 	INT	COMMENT	'元魂伤害减免',
regainSoul 	INT	COMMENT	'每10秒回魂',
regainBlood 	INT	COMMENT	'每10秒回血',
metal 	INT	COMMENT	'金',
wood 	INT	COMMENT	'木',
water 	INT	COMMENT	'水',
fire 	INT	COMMENT	'火',
earth 	INT	COMMENT	'土',
metalDefence 	INT	COMMENT	'金抗',
woodDefence 	INT	COMMENT	'木抗',
waterDefence 	INT	COMMENT	'水抗',
fireDefence 	INT	COMMENT	'火抗',
earthDefence 	INT	COMMENT	'土抗',
speed 	INT	COMMENT	'速度',
PRIMARY KEY(templateId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '技能属性表';


CREATE TABLE `tb_z_system_buffer` (
  `templateId` int(11) NOT NULL DEFAULT '0' COMMENT '模板ID',
  `name` varchar(255) DEFAULT NULL COMMENT 'bf名称',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `grade` int(11) DEFAULT NULL COMMENT 'bf等级',
  `icon` int(11) DEFAULT NULL COMMENT '图标',
  `addWay` int(11) DEFAULT NULL COMMENT '	添加方式	',
  `canSuperpose` int(11) DEFAULT NULL COMMENT '	是否允许叠加',
  `dataType1` int(11) DEFAULT NULL COMMENT '作用属性类型',
  `dataValue1` int(11) DEFAULT NULL COMMENT '作用属性值(固定值)',
  `dataPerV1` int(11) DEFAULT NULL COMMENT '作用属性值(百分比)',
  `dataType2` int(11) DEFAULT NULL COMMENT '作用属性类型',
  `dataValue2` int(11) DEFAULT NULL COMMENT '作用属性值(固定值)	',
  `dataPerV2` int(11) DEFAULT NULL COMMENT '作用属性值(百分比)',
  `dataType3` int(11) DEFAULT NULL COMMENT '作用属性类型',
  `dataValue3` int(11) DEFAULT NULL COMMENT '作用属性值(固定值)',
  `dataPerV3` int(11) DEFAULT NULL COMMENT '作用属性值(百分比)',
  `isTips` int(11) DEFAULT NULL COMMENT '	是否显示图标',
  PRIMARY KEY (`templateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统BUFF表';


DROP TABLE IF EXISTS `tb_u_playerPositionInfo`;
CREATE TABLE `tb_u_playerPositionInfo` (
  `playerId` bigint(20) NOT NULL COMMENT '角色ID',
  `mapId` int(11) NOT NULL COMMENT '地图ID',
  `mapTempId` int(11) NOT NULL COMMENT '地图模型ID',
  `x` int(11) NOT NULL COMMENT 'x轴,',
  `y` int(11) NOT NULL COMMENT 'y轴,',
  `z` int(11) NOT NULL COMMENT 'z轴,',
  `preMapId` int(11) NOT NULL COMMENT '地图ID',
  `preMapTempId` int(11) NOT NULL COMMENT '地图模型ID',
  `prex` int(11) NOT NULL COMMENT 'prex轴,',
  `prey` int(11) NOT NULL COMMENT 'prey轴,',
  `prez` int(11) NOT NULL COMMENT 'prez轴,',
  PRIMARY KEY (`playerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色位置';


CREATE TABLE `tb_z_campaign_info` (
  `templateId` int(11) NOT NULL DEFAULT '0' COMMENT '模型ID',
  `campaignName` varchar(255) DEFAULT NULL COMMENT '副本名字',
  `storyId` int(11) DEFAULT NULL COMMENT '副本所属章节标记(讲故事)',
  `type` int(11) DEFAULT NULL COMMENT '副本类型',
  `minLevel` int(11) DEFAULT NULL COMMENT '进入最小等级',
  `maxLevel` int(11) DEFAULT NULL COMMENT '进入最大等级',
  `preCampaignId` int(11) DEFAULT NULL COMMENT '前置副本',
  `nextCampaignId` varchar(255) DEFAULT NULL COMMENT '后置副本',
  `capacity` int(11) DEFAULT NULL COMMENT '副本容量',
  `rewardItems` varchar(655) DEFAULT NULL COMMENT '副本奖励',
  `description` varchar(255) DEFAULT NULL COMMENT '副本描述',
  `openType` int(11) DEFAULT NULL COMMENT '开启类型 1 玩家创建 2 定时开启 3 每周N开启 4 每月X号开启，等等，参数，待约定',
  `openParams` varchar(255) DEFAULT NULL COMMENT '开启参数',
  `openTime` int(11) DEFAULT NULL COMMENT '开启时间 (分)',
  `startScriptId` int(11) DEFAULT NULL COMMENT '开始时触发脚本ID',
  `endScriptId` int(11) DEFAULT NULL COMMENT '结束时触发脚本ID',
  PRIMARY KEY (`templateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='副本信息';

CREATE TABLE `tb_z_map_info` (
  `mapKey` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '1' COMMENT '1公共地图;2副本地图',
  `_desc` varchar(255) DEFAULT NULL,
  `campaignId` int(11) DEFAULT NULL,
  `canpaignIndex` int(11) DEFAULT NULL,
  `preMapKey` varchar(255) DEFAULT '0' COMMENT '副本类型才有前置mapKey',
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `z` int(11) DEFAULT NULL,
  `initScriptId` int(11) DEFAULT '0' COMMENT '初始化脚本id',
  `resName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mapKey`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT '地图信息表';


CREATE TABLE tb_z_role_status(
id INT NOT NULL DEFAULT 0 COMMENT '自增ID',
name VARCHAR(64) NOT NULL DEFAULT '' COMMENT '名称',
type VARCHAR(64) NOT NULL DEFAULT '' COMMENT '类型 0 一般 1 有益 2 负面 3 控制',
subBlood INT NOT NULL DEFAULT 0 COMMENT '扣气血',
subSoul INT NOT NULL DEFAULT 0 COMMENT '扣元魂',
addBlood INT NOT NULL DEFAULT 0 COMMENT '气血治疗',
addSoul INT NOT NULL DEFAULT 0 COMMENT '元魂治疗',
move INT NOT NULL DEFAULT 0 COMMENT '移动',
attackMove  INT NOT NULL DEFAULT 0 COMMENT '攻击位移',
beHitMove  INT NOT NULL DEFAULT 0 COMMENT '被击位移',
beHitFloat INT NOT NULL DEFAULT 0 COMMENT '击飞',
beHitDown INT NOT NULL DEFAULT 0 COMMENT '击倒',
normalAttack INT NOT NULL DEFAULT 0 COMMENT '普通攻击',
skillAttack INT NOT NULL DEFAULT 0 COMMENT '释放主动技能',
perks INT NOT NULL DEFAULT 0 COMMENT '触发型技能',
beControl INT NOT NULL DEFAULT 0 COMMENT '是否吃控制',
PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色状态表';

CREATE TABLE `tb_z_property_fighting` (
  `property` int(11) NOT NULL COMMENT '属性',
  `pname` varchar(255) NOT NULL DEFAULT '' COMMENT '属性名',
  `fighting` int(11) NOT NULL DEFAULT '0' COMMENT '对应战斗力'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='属性对应战力模板表';

CREATE TABLE `tb_z_team_target` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '目标ID',
  `parentId` int(11) NOT NULL DEFAULT '0' COMMENT '父ID',
  `name` varchar(60) DEFAULT '' COMMENT '名称',
  `levLimitMin` int(11) NOT NULL DEFAULT '0' COMMENT '目标限制等级下限',
  `levLimitMax` int(11) NOT NULL DEFAULT '0' COMMENT '目标限制等级上限',
  `targetType` int(11) NOT NULL DEFAULT '0' COMMENT '前往目标类型',
  `target` int(11) NOT NULL DEFAULT '0' COMMENT '前往目标',
  `entityId` int(11) NOT NULL DEFAULT '0' COMMENT '实体ID',
  `v3` varchar(100) DEFAULT '0' COMMENT '地点坐标'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组队目标表';

CREATE TABLE `tb_z_campaign_task` (
  `id` int(11) NOT NULL COMMENT '任务ID',
  `conditionType` int(11) DEFAULT NULL COMMENT '条件类型',
  `name` varchar(255) DEFAULT NULL COMMENT '任务名',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `point` int(11) DEFAULT NULL COMMENT '挑战点奖励',
  `repair` int(11) DEFAULT NULL COMMENT '修为',
  `param1` int(11) DEFAULT NULL COMMENT '条件参数1',
  `param2` int(11) DEFAULT NULL COMMENT '条件参数2',
  `param3` int(11) DEFAULT NULL COMMENT '条件参数3',
  `param4` int(11) DEFAULT NULL COMMENT '条件参数4',
  `strParam1` varchar(255) DEFAULT NULL COMMENT '条件参数str1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='副本任务';


CREATE TABLE `tb_z_snare_info` (
  `id` int(11) NOT NULL COMMENT '序列化自增id',
  `skinId` int(21) DEFAULT '0' COMMENT '模型ID',
  `type` int(11) DEFAULT '0' COMMENT '陷阱类型',
  `lifetime` int(11) DEFAULT '0' COMMENT '陷阱生命周期',
  `validCount` int(11) DEFAULT '0' COMMENT '陷阱生效次数',
  `exeWay` int(11) DEFAULT '0' COMMENT '陷阱生效次数',
  `coolDown` int(11) DEFAULT '0' COMMENT '作用CD时间',
  `checkX` int(11) DEFAULT '0' COMMENT '校验范围X',
  `checkZ` int(11) DEFAULT '0' COMMENT '校验范围Y',
  `hp` int(11) DEFAULT '0' COMMENT '血量',
  `target` int(11) DEFAULT '0' COMMENT '作用目标 0 地方 1友方',
  `bornType` int(11) DEFAULT '0' COMMENT '作用CD时间',
  `moveType` int(11) DEFAULT '0' COMMENT '移动类型',
  `lockingType` int(11) DEFAULT '0' COMMENT '锁定类型',
  `stateId` int(11) DEFAULT '0' COMMENT '作用状态',
  `addBuffers` varchar(255) DEFAULT NULL COMMENT '执行时添加bufferId集',
  `soulPercent` int(11) DEFAULT '0' COMMENT '元魂百分比',
  `soulValue` int(11) DEFAULT '0' COMMENT '元魂固定值',
  `bloodPercent` int(11) DEFAULT '0' COMMENT '气血百分比',
  `bloodValue` int(11) DEFAULT '0' COMMENT '气血固定值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户陷阱配表';






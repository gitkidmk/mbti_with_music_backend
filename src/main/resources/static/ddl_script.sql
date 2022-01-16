show databases;

create database mbti_music;

-- mbti_music.mbti_music_user definition

CREATE TABLE `mbti_music_user` (
  `music_id` varchar(1000) NOT NULL,
  `mbti_name` char(4) DEFAULT NULL,
  `album` varchar(1000) DEFAULT NULL,
  `artist` varchar(1000) DEFAULT NULL,
  `session_id` varchar(1000) DEFAULT NULL,
  `mbti_music_user_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`mbti_music_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;


-- mbti_music.mbti_result definition

CREATE TABLE `mbti_result` (
  `result_id` int(11) NOT NULL,
  `mbti` char(4) DEFAULT NULL,
  `EI` float DEFAULT NULL,
  `SN` float DEFAULT NULL,
  `TF` float DEFAULT NULL,
  `JP` float DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`result_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- mbti_music.music definition

CREATE TABLE `music` (
  `music_id` varchar(1000) NOT NULL,
  `album` varchar(1000) DEFAULT NULL,
  `artist` varchar(1000) DEFAULT NULL,
  `music_name` varchar(1000) NOT NULL,
  PRIMARY KEY (`music_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- mbti_music.mbti_music_great definition

CREATE TABLE `mbti_music_great` (
  `music_id` varchar(1000) NOT NULL,
  `session_id` varchar(1000) DEFAULT NULL,
  `mbti_music_great_id` int(11) NOT NULL AUTO_INCREMENT,
  `mbti_name` char(4) NOT NULL,
  PRIMARY KEY (`mbti_music_great_id`),
  KEY `FK_music_TO_mbti_music_great_2` (`music_id`),
  CONSTRAINT `FK_music_TO_mbti_music_great_2` FOREIGN KEY (`music_id`) REFERENCES `music` (`music_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;


-- mbti_music.music_mbti_rel definition

CREATE TABLE `music_mbti_rel` (
  `music_mbti_id` int(11) NOT NULL AUTO_INCREMENT,
  `music_id` varchar(1000) NOT NULL,
  `mbti_name` char(4) DEFAULT NULL,
  PRIMARY KEY (`music_mbti_id`),
  KEY `FK_music_TO_music_mbti_rel_1` (`music_id`),
  CONSTRAINT `FK_music_TO_music_mbti_rel_1` FOREIGN KEY (`music_id`) REFERENCES `music` (`music_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-----------------------
-- old table ddl
-----------------------

CREATE TABLE `mbti_result` (
	`result_id`	INT	NOT NULL,
	`mbti`	CHAR(4)	NULL,
	`EI`	FLOAT	NULL,
	`SN`	FLOAT	NULL,
	`TF`	FLOAT	NULL,
	`JP`	FLOAT	NULL,
	`time`	DATETIME	NULL
);

create table `music` (
	`music_id` VARCHAR(1000) not null,
	`album` VARCHAR(1000) null,
	`artist` VARCHAR(1000) null
);

CREATE TABLE `mbti_music_great` (
	`music_mbti_id`	INT	NOT NULL,
	`music_id`	VARCHAR(1000)	NOT NULL,
	`session_id`	VARCHAR(1000)	NULL
);

CREATE TABLE `mbti_music_user` (
	`music_id`	VARCHAR(1000)	NOT NULL,
	`mbti_name`	CHAR(4)	NULL,
	`album`	VARCHAR(1000)	NULL,
	`artist`	VARCHAR(1000)	NULL,
	`session_id`	VARCHAR(1000)	NULL
);

CREATE TABLE `music_mbti_rel` (
	`music_mbti_id`	INT	NOT NULL,
	`music_id`	VARCHAR(1000)	NOT NULL,
	`mbti_name`	CHAR(4)	NULL
);

ALTER TABLE `mbti_result` ADD CONSTRAINT `PK_MBTI_RESULT` PRIMARY KEY (
	`result_id`
);

ALTER TABLE `music` ADD CONSTRAINT `PK_MUSIC` PRIMARY KEY (
	`music_id`
);

ALTER TABLE `mbti_music_great` ADD CONSTRAINT `PK_MBTI_MUSIC_GREAT` PRIMARY KEY (
	`music_mbti_id`,
	`music_id`
);

ALTER TABLE `mbti_music_user` ADD CONSTRAINT `PK_MBTI_MUSIC_USER` PRIMARY KEY (
	`music_id`
);

ALTER TABLE `music_mbti_rel` ADD CONSTRAINT `PK_MUSIC_MBTI_REL` PRIMARY KEY (
	`music_mbti_id`,
	`music_id`
);

ALTER TABLE `mbti_music_great` ADD CONSTRAINT `FK_music_mbti_rel_TO_mbti_music_great_1` FOREIGN KEY (
	`music_mbti_id`
)
REFERENCES `music_mbti_rel` (
	`music_mbti_id`
);

ALTER TABLE `mbti_music_great` ADD CONSTRAINT `FK_music_TO_mbti_music_great_1` FOREIGN KEY (
	`music_id`
)
REFERENCES `music` (
	`music_id`
);

ALTER TABLE `music_mbti_rel` ADD CONSTRAINT `FK_music_TO_music_mbti_rel_1` FOREIGN KEY (
	`music_id`
)
REFERENCES `music` (
	`music_id`
);


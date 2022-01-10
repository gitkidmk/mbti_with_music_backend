show databases;

create database mbti_music;

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


-- mysql -u root -p mariadb

-- DB 생성
create database mbti_music;
use mbti_music;

-- user 생성
create user 'mbti_music'@'%' identified by 'mbti_music';
grant all privileges on mbti_music.* to 'mbti_music'@'%';
flush privileges;

-- mysql -u mbti_music -p mbti_music

-- mbti_music.mbti_music_user definition

CREATE TABLE `mbti_music_user` (
  `music_id` varchar(1000) NOT NULL,
  `mbti_name` char(4) DEFAULT NULL,
  `album` varchar(1000) DEFAULT NULL,
  `artist` varchar(1000) DEFAULT NULL,
  `session_id` varchar(1000) DEFAULT NULL,
  `mbti_music_user_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`mbti_music_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;


-- mbti_music.mbti_question definition

CREATE TABLE `mbti_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit` char(4) NOT NULL,
  `weight` int(11) NOT NULL,
  `contents` varchar(200) NOT NULL,
  `question_set` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb3;


-- mbti_music.mbti_result definition

CREATE TABLE `mbti_result` (
  `result_id` int(11) NOT NULL AUTO_INCREMENT,
  `mbti` char(4) DEFAULT NULL,
  `EI` float DEFAULT NULL,
  `NS` float DEFAULT NULL,
  `TF` float DEFAULT NULL,
  `JP` float DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`result_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;


-- mbti_music.music definition

CREATE TABLE `music` (
  `music_id` varchar(1000) NOT NULL,
  `album` varchar(1000) DEFAULT NULL,
  `artist` varchar(1000) DEFAULT NULL,
  `music_name` varchar(1000) DEFAULT NULL,
  `thumbnail` varchar(1000) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb3;


-- mbti_music.music_mbti_rel definition

CREATE TABLE `music_mbti_rel` (
  `music_mbti_id` int(11) NOT NULL AUTO_INCREMENT,
  `music_id` varchar(1000) NOT NULL,
  `mbti_name` char(4) DEFAULT NULL,
  PRIMARY KEY (`music_mbti_id`),
  KEY `FK_music_TO_music_mbti_rel_1` (`music_id`),
  CONSTRAINT `FK_music_TO_music_mbti_rel_1` FOREIGN KEY (`music_id`) REFERENCES `music` (`music_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
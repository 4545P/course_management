CREATE TABLE IF NOT EXISTS `Course` (
     `course_code` varchar(45) NOT NULL,
     `course_title` varchar(45) NOT NULL,
     `course_instructor` varchar(45) NOT NULL,
     `course_description` varchar(45) NOT NULL,
     `course_week` varchar(45) NOT NULL,
     `course_date` date NOT NULL,
     `course_end_date` date NOT NULL,
     `class_time` time NOT NULL,
     `class_end_time` time NOT NULL,
     `class_enable` tinyint NOT NULL,
     `class_city` varchar(45) NOT NULL,
     `class_add` datetime NOT NULL,
     `class_revise` datetime DEFAULT NULL,
     `personnel` varchar(45) DEFAULT NULL,
     PRIMARY KEY (`course_code`)
    );

CREATE  TABLE IF NOT EXISTS `Course_Schedule` (
    `id` int NOT NULL AUTO_INCREMENT,
    `course_code` varchar(45) NOT NULL,
    `course_date` date NOT NULL,
    `course_outline` varchar(45) NOT NULL,
    `course_project` varchar(45) NOT NULL,
    `course_content` varchar(45) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_course_outline_idx` (`course_outline`),
    KEY `fk_course_project` (`course_project`)
);

CREATE TABLE IF NOT EXISTS `Course_Selection` (
     `student_id` int NOT NULL,
     `name` varchar(45) NOT NULL,
     `course_code` varchar(45) DEFAULT NULL,
     PRIMARY KEY (`student_id`)
);

CREATE TABLE IF NOT EXISTS `Personnel` (
     `id` int NOT NULL AUTO_INCREMENT,
     `name` varchar(45) NOT NULL,
     `password` varchar(45) NOT NULL,
     `email` varchar(45) NOT NULL,
     `role` varchar(45) NOT NULL,
     `birthday` date NOT NULL,
     `register_date` datetime NOT NULL,
     `enable` tinyint NOT NULL,
     `verification_code` varchar(45) DEFAULT NULL,
     `verification_date` datetime DEFAULT NULL,
     PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Schedule_Management` (
     `id` int NOT NULL AUTO_INCREMENT,
     `student_id` int DEFAULT NULL,
     `course_code` varchar(45) NOT NULL,
     `course_outline` varchar(45) NOT NULL,
     `course_project` varchar(45) NOT NULL,
     `understand` tinyint DEFAULT NULL,
     `question` varchar(45) DEFAULT NULL,
     `solve` tinyint DEFAULT NULL,
     PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Student` (
     `student_id` int NOT NULL AUTO_INCREMENT,
     `name` varchar(45) NOT NULL,
     `password` varchar(45) NOT NULL,
     `email` varchar(45) NOT NULL,
     `birthday` date NOT NULL,
     `register_date` datetime NOT NULL,
     `enable` tinyint NOT NULL,
     `support` tinyint NOT NULL,
     `verification_code` varchar(45) DEFAULT NULL,
     `verification_date` datetime DEFAULT NULL,
     PRIMARY KEY (`student_id`),
     KEY `idx_name` (`name`)
);
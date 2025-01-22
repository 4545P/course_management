CREATE TABLE IF NOT EXISTS "Course" (
    "course_code" VARCHAR(45) NOT NULL,
    "course_title" VARCHAR(45) NOT NULL,
    "course_instructor" VARCHAR(45) NOT NULL,
    "course_description" VARCHAR(45) NOT NULL,
    "course_week" VARCHAR(45) NOT NULL,
    "course_date" DATE NOT NULL,
    "course_end_date" DATE NOT NULL,
    "class_time" TIME NOT NULL,
    "class_end_time" TIME NOT NULL,
    "class_enable" SMALLINT NOT NULL,
    "class_city" VARCHAR(45) NOT NULL,
    "class_add" TIMESTAMP NOT NULL,
    "class_revise" TIMESTAMP DEFAULT NULL,
    "personnel" VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY ("course_code")
    );

CREATE TABLE IF NOT EXISTS "Course_Schedule" (
    "id" SERIAL PRIMARY KEY,
    "course_code" VARCHAR(45) NOT NULL,
    "course_date" DATE NOT NULL,
    "course_outline" VARCHAR(45) NOT NULL,
    "course_project" VARCHAR(45) NOT NULL,
    "course_content" VARCHAR(45) NOT NULL,
    CONSTRAINT fk_course_outline FOREIGN KEY ("course_outline") REFERENCES "Course"("course_code"),
    CONSTRAINT fk_course_project FOREIGN KEY ("course_project") REFERENCES "Course"("course_code")
    );

CREATE TABLE IF NOT EXISTS "Course_Selection" (
    "student_id" INT NOT NULL,
    "name" VARCHAR(45) NOT NULL,
    "course_code" VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY ("student_id")
    );

CREATE TABLE IF NOT EXISTS "Personnel" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(45) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "email" VARCHAR(45) NOT NULL,
    "role" VARCHAR(45) NOT NULL,
    "birthday" DATE NOT NULL,
    "register_date" TIMESTAMP NOT NULL,
    "enable" SMALLINT NOT NULL,
    "verification_code" VARCHAR(45) DEFAULT NULL,
    "verification_date" TIMESTAMP DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS "Schedule_Management" (
    "id" SERIAL PRIMARY KEY,
    "student_id" INT DEFAULT NULL,
    "course_code" VARCHAR(45) NOT NULL,
    "course_outline" VARCHAR(45) NOT NULL,
    "course_project" VARCHAR(45) NOT NULL,
    "understand" SMALLINT DEFAULT NULL,
    "question" VARCHAR(45) DEFAULT NULL,
    "solve" SMALLINT DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS "Student" (
    "student_id" SERIAL PRIMARY KEY,
    "name" VARCHAR(45) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "email" VARCHAR(45) NOT NULL,
    "birthday" DATE NOT NULL,
    "register_date" TIMESTAMP NOT NULL,
    "enable" SMALLINT NOT NULL,
    "support" SMALLINT NOT NULL,
    "verification_code" VARCHAR(45) DEFAULT NULL,
    "verification_date" TIMESTAMP DEFAULT NULL,
    CONSTRAINT idx_name UNIQUE ("name")
    );
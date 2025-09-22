CREATE SCHEMA IF NOT EXISTS demo;
set search_path to demo, public;

CREATE TABLE students (
  student_id serial primary key,
  student_name varchar(150) not null,
  birthdate date,
  phone varchar(15) unique CHECK (phone ~ '^[0-9]+$'),
  email varchar(150) unique
) TABLESPACE demo_tz;

CREATE TABLE teachers (
  teacher_id serial PRIMARY KEY,
  teacher_name varchar(150) not null,
  expertise varchar(255),
  phone varchar(15) unique CHECK (phone ~ '^[0-9]+$'),
  email varchar(150) unique
)

CREATE TABLE courses (
  course_id serial PRIMARY KEY,
  course_name varchar(150) not null,
  duration numeric(5,2) default '0.00' check (duration >= 0.00)
  tuition numeric(12,2) check (tuition >= 0.00)
)
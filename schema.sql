CREATE DATABASE student_marks_tracker;

USE student_marks_tracker;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE marks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    subject VARCHAR(100),
    marks INT,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

CREATE TABLE IF NOT EXISTS employees (
    id         INT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    salary     DOUBLE       NOT NULL,
    department VARCHAR(100) NOT NULL,
    phone      VARCHAR(30)  DEFAULT NULL
);

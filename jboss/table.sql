create database if not exists mashdb;
CREATE TABLE if not exists mashdb.Customer (
         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(100)
       );
mysql> create database if not exists mashdb;
mysql> CREATE TABLE if not exists mashdb.customers (
         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(100)
       );
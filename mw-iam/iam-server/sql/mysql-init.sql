/*
 create database
 */
DROP DATABASE IF EXISTS iam_db;
CREATE DATABASE iam_db;

USE iam_db;

/*-----------init all table-----------------------------*/
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id       int(32) NOT NULL UNIQUE,
    name     varchar(128),
    password varchar(256),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS group_info;
CREATE TABLE group_
(
    id   int(32) NOT NULL UNIQUE,
    name varchar(128),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS resource;
CREATE TABLE resource
(
    id   int(32) NOT NULL UNIQUE,
    name varchar(128),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS policy;
CREATE TABLE policy
(
    id   int(32) NOT NULL UNIQUE,
    name varchar(128),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS action;
CREATE TABLE action
(
    id   int(32) NOT NULL UNIQUE,
    name varchar(128),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create databases iamdb if exists;

use iamdb;
DROP IF EXISTS user;
CREATE TABLE user(
  id int(32) NOT NULL UNIQUE ;
  name varchar(128);
  passwd varchar(256);
);

DROP IF EXISTS group;
CREATE TABLE group(

);


DROP IF EXISTS resource;
CREATE TABLE resource(

);


DROP IF EXISTS policy;
CREATE TABLE policy(

);

DROP IF EXISTS permition;
CREATE TABLE permition(

);






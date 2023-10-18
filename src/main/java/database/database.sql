create database SocialNetwork ;
use SocialNetwork;
create table userAccount(
idAccount int auto_increment primary key,
username varchar(50) not null,
password varchar(32) not null,
CONSTRAINT CHK_PasswordLength CHECK (length(password) >= 6 AND length(password)<= 32),
permision int,
foreign key(permision) references permision(idPermision)
);
create table permision(
idPermision int auto_increment primary key,
namePermision varchar(20) not null
);
insert into permision(namePermision) values ('admin');
insert into permision(namePermision) values ('user');
insert into userAccount(username,password,permision) value ('user','123456',2);

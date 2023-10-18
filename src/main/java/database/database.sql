create database SocialNetwork ;
use SocialNetwork;
create table userAccount(
idAccount int auto_increment primary key,
username varchar(50) not null,
password varchar(32) not null,
CONSTRAINT CHK_PasswordLength CHECK (length(password) >= 6 AND length(password)<= 32),
permission int,
foreign key(permission) references permission(idPermision)
);
create table permission(
idPermission int auto_increment primary key,
namePermission varchar(20) not null
);
insert into permission(namePermission) values ('admin');
insert into permission(namePermission) values ('user');
insert into userAccount(username,password,permission) value ('user','123456',2);

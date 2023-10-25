drop database socialnetwork;
create database socialnetwork;
use socialnetwork;
--  tao bang user lam viec voi profile
create table permission(
                           idPermission int auto_increment primary key,
                           namePermission varchar(30)
);
create table user(
                     id int auto_increment primary key,
                     username varchar(50) not null,
                     password varchar(32) not null,
                     fullname nvarchar(100),
                     email varchar(100),
                     birth date,
                     phone varchar(20),
                     gender boolean,
                     hobby nvarchar(200),
                     avatar nvarchar(200),
                     address nvarchar(200),
                     status enum('working','block') default 'working',
                     CONSTRAINT CHK_PasswordLength CHECK (length(password) >= 6 AND length(password)<= 32),
                     idPermission int,
                     foreign key(idPermission) references permission(idPermission)

);
-- tao bang permision lam viec voi quyen thuc thi


-- tao thu tuc lam viec xem cac du lieu co ban tai khoan nguoi dung
DELIMITER $$
create procedure showUserWithStatus()
begin
    select user.id,username,password,namePermission,status,email,phone from user inner join permission on user.idPermission = permission.idPermission;
end $$
call showUserWithStatus();
-- tao thu tuc them du lieu nguoi dung
DELIMITER $$
create procedure insertUser(in usernameWeb varchar(45),in passwordWeb varchar(32), in PermissionWeb int)
begin
    insert into user (username, password,idPermission ) values (usernameWeb,passwordWeb,permissionWeb);
end $$
-- *  du leu tham so dau vao
-- du lieu bang permission
insert into permission(namePermission) values ('admin'),('user');
-- du lieu bang account
insert into user(username , password,idPermission) values('admin','123456',1);
insert into user(username , password,idPermission) values('user','123456',2);
insert into user(username , password,idPermission) values('user2','123456',2);
select user.id, user.username, user.password, user.fullname, user.avatar, user.email, user.birth, user.address, user.phone, user.hobby, status, namePermission from user inner join permission on user.idPermission = permission.idPermission where user.id = 1;
-- bang quyen status
create table permissionStatus(
                                 idPermission int primary key auto_increment ,
                                 namePermission enum('public','private')
);
-- bang status
create table status(
                       idStatus int auto_increment primary key,
                       createTime datetime,
                       description nvarchar(600),
                       img text(65535),
                       video text(65535),
                       idUser int,
                       idPermission int,
                       foreign key(idUser) references user(id),
                       foreign key(idPermission) references permissionStatus(idPermission)
);


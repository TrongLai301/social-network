drop database socialnetwork;
create database socialnetwork;
use socialnetwork;
--  tao bang user lam viec voi profile
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
                     CONSTRAINT CHK_PasswordLength CHECK (length(password) >= 6 AND length(password)<= 32),
                     idPermission int,
                     foreign key(idPermission) references permission(idPermission)

);
-- tao bang permision lam viec voi quyen thuc thi
create table permission(
                           idPermission int auto_increment primary key,
                           namePermission varchar(30)
);
-- tao bang lam viec voi trang thai tai khoan
create table userStatus(
                           idAccount int,
                           status boolean default 0,
                           primary key(idAccount),
                           foreign key(idAccount) references user(id)
);

-- tao thu tuc lam viec xem cac du lieu co ban tai khoan nguoi dung
DELIMITER $$
create procedure showUserWithStatus()
begin
    select user.id,username,password,namePermission,status from user inner join permission on user.idPermission = permission.idPermission left join userStatus on user.id = userStatus.idAccount;
end $$
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
drop database socialnetwork;
create database socialnetwork;
use socialnetwork;
--  tao bang user lam viec voi profile
create table permission
(
    idPermission   int auto_increment primary key,
    namePermission varchar(30)
);

create table user
(
    id           int auto_increment primary key,
    username     varchar(50) not null,
    password     varchar(32) not null,
    fullname     nvarchar(100),
    email        varchar(100),
    birth        date,
    phone        varchar(20),
    gender       boolean,
    hobby        nvarchar(200),
    avatar       nvarchar(500),
    address      nvarchar(200),
    status       enum ('working','block') default 'working',
    timeCreate   DATETIME not null,
    CONSTRAINT CHK_PasswordLength CHECK (length(password) >= 6 AND length(password) <= 32),
    idPermission int,
    foreign key (idPermission) references permission (idPermission)
);
-- tao bang permision lam viec voi quyen thuc thi

-- tao thu tuc lam viec xem cac du lieu co ban tai khoan nguoi dung

DELIMITER $$
create procedure showUserWithStatus()
begin
select user.id, username, password, email, namePermission, status, phone, timeCreate
from user
         inner join permission on user.idPermission = permission.idPermission;
end $$
call showUserWithStatus();
-- tao thu tuc them du lieu nguoi dung
DELIMITER $$
create procedure insertUser(in usernameWeb varchar(45), in passwordWeb varchar(32), in emailWeb varchar(100),
                            in birthWeb date, in phoneNumberWeb varchar(20), in PermissionWeb int)
begin
insert into user (username, password, email, birth, phone, idPermission)
values (usernameWeb, passwordWeb, emailWeb, birthWeb, phoneNumberWeb, PermissionWeb);
end $$
call insertUser();
-- *  du leu tham so dau vao
-- du lieu bang permission
insert into permission(namePermission)
values ('admin'),
       ('user');
-- bang quyen status
create table permissionStatus
(
    idPermission   int primary key auto_increment,
    namePermission enum ('public','private','friend')
);

-- bang status
create table status
(
    idStatus     int auto_increment primary key,
    createTime   date,
    description  nvarchar(600),
    media        text(65530),
    idUser       int,
    idPermission int,
    likeCount    int default 0,
    foreign key (idUser) references user (id),
    foreign key (idPermission) references permissionStatus (idPermission)
);
insert into permissionStatus(namePermission)
values ('public'),
       ('private'),
       ('friend');

# Database friendship

CREATE table Friendships
(
    requestId  int primary key auto_increment,
    senderId   int,
    receiverId int,
    status     varchar(10) default 'pending',
    FOREIGN KEY (senderId) references user (id),
    FOREIGN KEY (receiverId) references user (id)
);
INSERT INTO user (username, password, fullname, email, birth, phone, gender, hobby, avatar, address, status, idPermission ,timeCreate)
VALUES ('user1', 'password1', 'John Doe', 'john.doe@example.com', NULL, '1234567890', 1, 'Reading, swimming', 'https://images6.alphacoders.com/130/1300662.png', '123 Main Street, City', 'working', 2,now()),
       ('user2', 'password2', 'Jane Smith', 'jane.smith@example.com', NULL, '9876543210', 0, 'Gardening, hiking', 'https://scontent.fhan14-4.fna.fbcdn.net/v/t1.6435-9/100052525_2652901648323241_2493762482842107904_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=7f8c78&_nc_ohc=e6fEUiwObE0AX8gOTif&_nc_ht=scontent.fhan14-4.fna&oh=00_AfCtBObtHxt5-NpR1vZLhnvOMXhosh-9Xc8Y-75NyGLA9w&oe=6571C36E', '456 First Avenue, Town', 'working', 2,now()),
       ('user3', 'password3', 'Alex Johnson', 'alex.johnson@example.com', NULL, '5555555555', 1, 'Painting, cooking', 'https://w0.peakpx.com/wallpaper/216/504/HD-wallpaper-fu-hua-honkai-impact-character-sign-aquarius-girl.jpg', '789 Elm Road, Village', 'working', 2,now()),
       ('user4', 'password4', 'Emily Wilson', 'emily.wilson@example.com', NULL, '1112223333', 0, 'Photography, dancing', 'https://w0.peakpx.com/wallpaper/861/121/HD-wallpaper-honkai-impact-3.jpg', '321 Oak Lane, County', 'working', 2,now()),
       ('user5', 'password5', 'Michael Brown', 'michael.brown@example.com', NULL, '9998887777', 1, 'Running, cycling', 'https://s1.zerochan.net/Silver.Wolf.600.3956698.jpg', '567 Pine Court, State', 'working', 2,now()),
       ('user6', 'password6', 'Sophia Lee', 'sophia.lee@example.com', NULL, '4444444444', 0, 'Singing, shopping', 'https://s1.zerochan.net/Silver.Wolf.600.3969401.jpg', '890 Maple Avenue, Town', 'working', 2,now()),
       ('user7', 'password7', 'Jacob Wilson', 'jacob.wilson@example.com', NULL, '7777777777', 1, 'Playing guitar, hiking', 'https://s1.zerochan.net/Kafka.%28Honkai.Star.Rail%29.600.3936545.jpg', '123 Oak Street, City', 'working', 2,now()),
       ('user8', 'password8', 'Olivia Johnson', 'olivia.johnson@example.com', NULL, '5554443333', 0, 'Drawing, photography', 'https://s1.zerochan.net/Kafka.%28Honkai.Star.Rail%29.600.3864455.jpg', '456 Elm Road, Village', 'working', 2,now()),
       ('user9', 'password9', 'William Smith', 'william.smith@example.com', NULL, '9999999999', 1, 'Cooking, gardening', 'https://s1.zerochan.net/Tingyun.600.3943392.jpg', '789 Pine Lane, County', 'working', 2,now()),
       ('user10', 'password10', 'Ava Davis', 'ava.davis@example.com', NULL, '8888888888', 0, 'Reading, dancing', 'https://s1.zerochan.net/Jingliu.600.4034519.jpg', '321 Maple Court, State', 'working', 2,now()),
       ('admin1','123456','admin1','Kamito0620042gmail.com',NULL,'0777280105',0,'Loli','https://images6.alphacoders.com/130/1300662.png','VN','working',1,now()),
       ('admin2','123456','admin2','Kami0620042gmail.com',NULL,'0777280105',0,'Loli','https://images6.alphacoders.com/130/1300662.png','VN','working',1,now());
INSERT INTO status (createTime, description, media, idUser, idPermission)
VALUES (date(now()), 'update new version with best waifu ', 'https://fastcdn.hoyoverse.com/content-v2/bh3/113389/fe26b6ed796f996ef25a00e84c143db3_7064957493634494323.png', 5, 1);
INSERT INTO status (createTime, description, media, idUser, idPermission)
VALUES (date(now()), 'New guy is comming', 'https://images8.alphacoders.com/131/1316156.jpeg', 2, 1);
INSERT INTO status (createTime, description, media, idUser, idPermission)
VALUES (date(now()), 'New girl', 'https://images7.alphacoders.com/129/1295443.jpg', 3, 2);
INSERT INTO status (createTime, description, media, idUser, idPermission)
VALUES (date(now()), 'New girl so cuter', 'https://images4.alphacoders.com/130/1302820.jpg', 5, 2);
INSERT INTO status (createTime, description, media, idUser, idPermission)
VALUES (date(now()), 'New event ariver', 'https://fastcdn.hoyoverse.com/content-v2/bh3/113389/fe26b6ed796f996ef25a00e84c143db3_7064957493634494323.png', 4, 1);
INSERT INTO status (createTime, description, media, idUser, idPermission)
VALUES (date(now()), 'New event ariver', 'https://fastcdn.hoyoverse.com/content-v2/bh3/113389/fe26b6ed796f996ef25a00e84c143db3_7064957493634494323.png', 9, 1);
select idStatus , createTime , description ,media, status.idPermission , idUser from status;
INSERT INTO status (createTime, description, media, idUser, idPermission)
VALUES (date(now()), 'New event ariver', 'https://fastcdn.hoyoverse.com/content-v2/bh3/113389/fe26b6ed796f996ef25a00e84c143db3_7064957493634494323.png', 9, 1);
select idStatus , createTime , description ,media, status.idPermission , idUser from status;
create table likes (
                       idLike int primary key auto_increment,
                       idStatus int,
                       idUser int,
                       foreign key (idStatus) references status (idStatus),
                       foreign key (idUser) references user (id)
);
create table permissionFriends
(
    idPermission   int primary key auto_increment,
    namePermission enum ('public','private')
);
insert into permissionFriends(namePermission)
values ('public'),
       ('private');
alter table user
    add column idPermissionFriends int default 1;

create table Comment (
                         idCmt int auto_increment primary key ,
                         idUser int not null ,
                         idStatus int not null,
                         content varchar(255) not null ,
                         likeCount int default 0,
                         createTime timestamp default current_timestamp,
                         FOREIGN KEY (idUser) references  user(id),
                         FOREIGN KEY  (idStatus) references  status(idStatus)
);
# Create table admin

create table admin (
                       id int primary key auto_increment,
                       idUser int not null,
                       idPermission int not null,
                       timeAccess DATETIME not null default now(),
                       foreign key (idPermission) references user (idPermission),
                       foreign key (idUser) references user (id)
);
INSERT INTO admin (idUser, timeAccess, idPermission) VALUES
                                                         (6, '2023-10-01 09:12:00',2),
                                                         (5, '2023-11-10 10:00:00',2),
                                                         (5, '2023-11-09 09:00:00',2),
                                                         (5, '2023-10-01 09:00:00',2),
                                                         (8, '2023-10-02 10:30:00',2),
                                                         (9, '2023-10-03 14:15:00',2),
                                                         (5, '2023-10-04 16:45:00',2),
                                                         (6, '2023-10-05 11:20:00',2),
                                                         (5, '2023-10-06 13:10:00',2),
                                                         (5, '2023-10-07 08:45:00',2),
                                                         (5, '2023-10-08 17:30:00',2),
                                                         (9, '2023-10-09 12:00:00',2),
                                                         (10, '2023-10-10 15:20:00',2),
                                                         (10, '2023-10-10 15:20:00',2),
                                                         (1, '2023-10-10 15:20:00',1);

INSERT INTO admin (idUser, idPermission) VALUES (6,2);

INSERT INTO admin (idUser, timeAccess, idPermission) VALUES (10, '2023-11-08 15:20:00',2);
INSERT INTO admin (idUser, timeAccess, idPermission) VALUES (10, '2023-11-07 15:20:00',2);
INSERT INTO admin (idUser, timeAccess, idPermission) VALUES (10, '2023-11-06 15:20:00',2);
INSERT INTO admin (idUser, timeAccess, idPermission) VALUES (10, '2023-11-05 15:20:00',2);
INSERT INTO admin (idUser, timeAccess, idPermission) VALUES (10, '2022-11-05 15:20:00',2);
INSERT INTO admin (idUser, timeAccess, idPermission) VALUES (10, '2021-11-05 15:20:00',2);
INSERT INTO admin (idUser, timeAccess, idPermission) VALUES (10, '2022-11-05 15:20:00',2);

DELIMITER $$
create procedure userPerWeekAdmin()
begin
SELECT *
FROM admin
WHERE WEEKDAY(timeAccess) >= 0 -- Thứ 2
  AND WEEKDAY(timeAccess) <= 6 -- Chủ nhật
  AND timeAccess >= DATE_ADD(CURDATE(), INTERVAL (0 - WEEKDAY(CURDATE())) DAY);
end $$
call userPerWeekAdmin();

DELIMITER $$
create procedure userPerMonthAdmin()
begin
select * from admin where MONTH(timeAccess) = MONTH(now());
end $$

call userPerMonthAdmin;

DELIMITER $$
create procedure userPerYearAdmin()
begin
select * from admin where year(timeAccess) =year(now());
end $$

call userPerYearAdmin;

#login
DELIMITER $$
create procedure userPerWeekLogin()
begin
SELECT *
FROM user
WHERE WEEKDAY(timeCreate) >= 0 -- Thứ 2
  AND WEEKDAY(timeCreate) <= 6 -- Chủ nhật
  AND timeCreate >= DATE_ADD(CURDATE(), INTERVAL (0 - WEEKDAY(CURDATE())) DAY);
end $$
call userPerWeekLogin();

DELIMITER $$
create procedure userPerMonthLogin()
begin
select * from user where MONTH(timeCreate) = MONTH(now());
end $$
call userPerMonthLogin();
DELIMITER $$
create procedure userPerYearLogin()
begin
select * from user where year(timeCreate) =year(now());
end $$
#login

DELIMITER $$
create procedure showAllUserLogin()
begin
select * from admin;
end $$
call showAllUserLogin();

DELIMITER $$
create procedure inserQuantitytUserLogin(in idUserLogin int, in idPermissionLogin int)
begin
insert into admin (idUser, idPermission) values (idUserLogin, idPermissionLogin);
end $$

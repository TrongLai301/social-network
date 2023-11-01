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
    CONSTRAINT CHK_PasswordLength CHECK (length(password) >= 6 AND length(password) <= 32),
    idPermission int,
    foreign key (idPermission) references permission (idPermission)

);
-- tao bang permision lam viec voi quyen thuc thi


-- tao thu tuc lam viec xem cac du lieu co ban tai khoan nguoi dung
DELIMITER $$
create procedure showUserWithStatus()
begin
    select user.id, username, password, email, namePermission, status, phone
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
-- *  du leu tham so dau vao
-- du lieu bang permission
insert into permission(namePermission)
values ('admin'),
       ('user');
-- du lieu bang account
insert into user(username, password, idPermission)
values ('admin', '123456', 1);
insert into user(username, password, idPermission)
values ('user', '123456', 2);
insert into user(username, password, idPermission)
values ('user2', '123456', 2);
select user.id,
       user.username,
       user.password,
       user.fullname,
       user.avatar,
       user.email,
       user.birth,
       user.address,
       user.phone,
       user.hobby,
       status,
       namePermission
from user
         inner join permission on user.idPermission = permission.idPermission
where user.id = 1;
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
    like int,
    foreign key (idUser) references user (id),
    foreign key (idPermission) references permissionStatus (idPermission)
);
insert into permissionStatus(namePermission)
values ('public'),
       ('private'),
       ('friend');
INSERT INTO user (username, password, fullname, email, birth, phone, gender, hobby, avatar, address, status,
                  idPermission)
VALUES ('user1', 'password1', 'John Doe', 'john.doe@example.com', NULL, '1234567890', 1, 'Reading, swimming',
        'avatar1.jpg', '123 Main Street, City', 'working', 1),
       ('user2', 'password2', 'Jane Smith', 'jane.smith@example.com', NULL, '9876543210', 0, 'Gardening, hiking',
        'avatar2.jpg', '456 First Avenue, Town', 'working', 2),
       ('user3', 'password3', 'Alex Johnson', 'alex.johnson@example.com', NULL, '5555555555', 1, 'Painting, cooking',
        'avatar3.jpg', '789 Elm Road, Village', 'working', 1),
       ('user4', 'password4', 'Emily Wilson', 'emily.wilson@example.com', NULL, '1112223333', 0, 'Photography, dancing',
        'avatar4.jpg', '321 Oak Lane, County', 'working', 2),
       ('user5', 'password5', 'Michael Brown', 'michael.brown@example.com', NULL, '9998887777', 1, 'Running, cycling',
        'avatar5.jpg', '567 Pine Court, State', 'working', 1),
       ('user6', 'password6', 'Sophia Lee', 'sophia.lee@example.com', NULL, '4444444444', 0, 'Singing, shopping',
        'avatar6.jpg', '890 Maple Avenue, Town', 'working', 1),
       ('user7', 'password7', 'Jacob Wilson', 'jacob.wilson@example.com', NULL, '7777777777', 1,
        'Playing guitar, hiking', 'avatar7.jpg', '123 Oak Street, City', 'working', 2),
       ('user8', 'password8', 'Olivia Johnson', 'olivia.johnson@example.com', NULL, '5554443333', 0,
        'Drawing, photography', 'avatar8.jpg', '456 Elm Road, Village', 'working', 1),
       ('user9', 'password9', 'William Smith', 'william.smith@example.com', NULL, '9999999999', 1, 'Cooking, gardening',
        'avatar9.jpg', '789 Pine Lane, County', 'working', 2),
       ('user10', 'password10', 'Ava Davis', 'ava.davis@example.com', NULL, '8888888888', 0, 'Reading, dancing',
        'avatar10.jpg', '321 Maple Court, State', 'working', 1),
       ('user11', 'password11', 'James Anderson', 'james.anderson@example.com', NULL, '1111111111', 1,
        'Playing football, painting', 'avatar11.jpg', '567 Oak Avenue, Town', 'working', 2),
       ('user12', 'password12', 'Mia Martinez', 'mia.martinez@example.com', NULL, '2222222222', 0, 'Singing, shopping',
        'avatar12.jpg', '890 Elm Road, Village', 'working', 1),
       ('user13', 'password13', 'Benjamin Johnson', 'benjamin.johnson@example.com', NULL, '3333333333', 1,
        'Playing piano, hiking', 'avatar13.jpg', '123 Pine Lane, County', 'working', 2),
       ('user14', 'password14', 'Charlotte Thompson', 'charlotte.thompson@example.com', NULL, '4445556666', 0,
        'Drawing, photography', 'avatar14.jpg', '456 Oak Street, City', 'working', 1),
       ('user15', 'password15', 'Daniel Brown', 'daniel.brown@example.com', NULL, '7778889999', 1, 'Cooking, gardening',
        'avatar15.jpg', '789 Maple Avenue, Town', 'working', 2),
       ('user16', 'password16', 'Amelia Davis', 'amelia.davis@example.com', NULL, '2223334444', 0, 'Reading, dancing',
        'avatar16.jpg', '321 Elm Road, Village', 'working', 1),
       ('user17', 'password17', 'Joseph Wilson', 'joseph.wilson@example.com', NULL, '5556667777', 1,
        'Playing basketball, painting', 'avatar17.jpg', '567 Pine Lane, County', 'working', 2),
       ('user18', 'password18', 'Evelyn Anderson', 'evelyn.anderson@example.com', NULL, '1112223333', 0,
        'Singing, shopping', 'avatar18.jpg', '890 Oak Street, City', 'working', 1),
       ('user19', 'password19', 'Michael Johnson', 'michael.johnson@example.com', NULL, '4444444444', 1,
        'Playing guitar, hiking', 'avatar19.jpg', '123 Maple Avenue, Town', 'working', 2),
       ('user20', 'password20', 'Sophia Martinez', 'sophia.martinez@example.com', NULL, '7777777777', 0,
        'Drawing, photography', 'avatar20.jpg', '456 Elm Road, Village', 'working', 1),
       ('user21', 'password21', 'Oliver Thompson', 'oliver.thompson@example.com', NULL, '5555555555', 1,
        'Cooking, gardening', 'avatar21.jpg', '789 Pine Lane, County', 'working', 2),
       ('user22', 'password22', 'Emma Davis', 'emma.davis@example.com', NULL, '9999999999', 0, 'Reading, dancing',
        'avatar22.jpg', '321 Oak Street, City', 'working', 1),
       ('user23', 'password23', 'William Wilson', 'william.wilson@example.com', NULL, '8888888888', 1,
        'Playing football, painting', 'avatar23.jpg', '567 Maple Avenue, Town', 'working', 2),
       ('user24', 'password24', 'Olivia Thompson', 'olivia.thompson@example.com', NULL, '4444444444', 0,
        'Singing, shopping', 'avatar24.jpg', '890 Elm Road, Village', 'working', 1),
       ('user25', 'password25', 'Noah Davis', 'noah.davis@example.com', NULL, '7777777777', 1, 'Playing guitar, hiking',
        'avatar25.jpg', '123 Pine Lane, County', 'working', 2),
       ('user26', 'password26', 'Ava Johnson', 'ava.johnson@example.com', NULL, '5554443333', 0, 'Drawing, photography',
        'avatar26.jpg', '456 Oak Street, City', 'working', 1),
       ('user27', 'password27', 'James Smith', 'james.smith@example.com', NULL, '9999999999', 1, 'Cooking, gardening',
        'avatar27.jpg', '789 Maple Avenue, Town', 'working', 2),
       ('user28', 'password28', 'Isabella Anderson', 'isabella.anderson@example.com', NULL, '2223334444', 0,
        'Reading, dancing', 'avatar28.jpg', '321 Elm Road, Village', 'working', 1),
       ('user29', 'password29', 'Mason Johnson', 'mason.johnson@example.com', NULL, '5556667777', 1,
        'Playing basketball, painting', 'avatar29.jpg', '567 Pine Lane, County', 'working', 2),
       ('user30', 'password30', 'Sophia Brown', 'sophia.brown@example.com', NULL, '1112223333', 0, 'Singing, shopping',
        'avatar30.jpg', '890 Oak Street, City', 'working', 1),
       ('user31', 'password31', 'Logan Wilson', 'logan.wilson@example.com', NULL, '4445556666', 1,
        'Playing piano, hiking', 'avatar31.jpg', '123 Maple Avenue, Town', 'working', 2),
       ('user32', 'password32', 'Ava Johnson', 'ava.johnson@example.com', NULL, '7778889999', 0, 'Drawing, photography',
        'avatar32.jpg', '456 Elm Road, Village', 'working', 1),
       ('user33', 'password33', 'Lucas Thompson', 'lucas.thompson@example.com', NULL, '5555555555', 1,
        'Cooking, gardening', 'avatar33.jpg', '789 Pine Lane, County', 'working', 2),
       ('user34', 'password34', 'Mia Davis', 'mia.davis@example.com', NULL, '2222222222', 0, 'Reading, dancing',
        'avatar34.jpg', '321 Oak Street, City', 'working', 1),
       ('user35', 'password35', 'Jackson Wilson', 'jackson.wilson@example.com', NULL, '7777777777', 1,
        'Playing football, painting', 'avatar35.jpg', '567 Maple Avenue, Town', 'working', 2),
       ('user36', 'password36', 'Olivia Thompson', 'olivia.thompson@example.com', NULL, '4444444444', 0,
        'Singing, shopping', 'avatar36.jpg', '890 Elm Road, Village', 'working', 1),
       ('user37', 'password37', 'Aiden Davis', 'aiden.davis@example.com', NULL, '9999999999', 1,
        'Playing guitar, hiking', 'avatar37.jpg', '123 Pine Lane, County', 'working', 2),
       ('user38', 'password38', 'Emma Johnson', 'emma.johnson@example.com', NULL, '5554443333', 0,
        'Drawing, photography', 'avatar38.jpg', '456 Elm Road, Village', 'working', 1),
       ('user39', 'password39', 'Lucas Thompson', 'lucas.thompson@example.com', NULL, '5555555555', 1,
        'Cooking, gardening', 'avatar39.jpg', '789 Pine Lane, County', 'working', 2),
       ('user40', 'password40', 'Mia Davis', 'mia.davis@example.com', NULL, '2222222222', 0, 'Reading, dancing',
        'avatar40.jpg', '321 Oak Street, City', 'working', 1),
       ('user40', 'password40', 'Mia Davis', 'mia.davis@example.com', NULL, '2222222222', 0, 'Reading, dancing',
        'avatar40.jpg', '321 Oak Street, City', 'working', 1),
       ('user41', 'password41', 'Ethan Wilson', 'ethan.wilson@example.com', NULL, '7777777777', 1,
        'Playing football, painting', 'avatar41.jpg', '567 Maple Avenue, Town', 'working', 2),
       ('user42', 'password42', 'Ava Thompson', 'ava.thompson@example.com', NULL, '4444444444', 0, 'Singing, shopping',
        'avatar42.jpg', '890 Elm Road, Village', 'working', 1),
       ('user43', 'password43', 'Liam Davis', 'liam.davis@example.com', NULL, '9999999999', 1, 'Playing guitar, hiking',
        'avatar43.jpg', '123 Pine Lane, County', 'working', 2),
       ('user44', 'password44', 'Olivia Johnson', 'olivia.johnson@example.com', NULL, '5554443333', 0,
        'Drawing, photography', 'avatar44.jpg', '456 Oak Street, City', 'working', 1),
       ('user45', 'password45', 'Noah Smith', 'noah.smith@example.com', NULL, '8888888888', 1, 'Cooking, gardening',
        'avatar45.jpg', '789 Maple Avenue, Town', 'working', 2),
       ('user46', 'password46', 'Sophia Anderson', 'sophia.anderson@example.com', NULL, '2223334444', 0,
        'Reading, dancing', 'avatar46.jpg', '321 Elm Road, Village', 'working', 1),
       ('user47', 'password47', 'Logan Johnson', 'logan.johnson@example.com', NULL, '5556667777', 1,
        'Playing basketball, painting', 'avatar47.jpg', '567 Pine Lane, County', 'working', 2),
       ('user48', 'password48', 'Jackson Brown', 'jackson.brown@example.com', NULL, '1112223333', 0,
        'Singing, shopping', 'avatar48.jpg', '890 Oak Street, City', 'working', 1),
       ('user49', 'password49', 'Ava Wilson', 'ava.wilson@example.com', NULL, '4445556666', 1, 'Playing piano, hiking',
        'avatar49.jpg', '123 Maple Avenue, Town', 'working', 2),
       ('user50', 'password50', 'Lucas Thompson', 'lucas.thompson@example.com', NULL, '7778889999', 0,
        'Drawing, photography', 'avatar50.jpg', '456 Elm Road, Village', 'working', 1),
       ('user51', 'password51', 'Mia Davis', 'mia.davis@example.com', NULL, '5555555555', 1, 'Cooking, gardening',
        'avatar51.jpg', '789 Pine Lane, County', 'working', 2),
       ('user52', 'password52', 'Aiden Johnson', 'aiden.johnson@example.com', NULL, '2222222222', 0, 'Reading, dancing',
        'avatar52.jpg', '321 Oak Street, City', 'working', 1),
       ('user53', 'password53', 'Emma Wilson', 'emma.wilson@example.com', NULL, '7777777777', 1,
        'Playing football, painting', 'avatar53.jpg', '567 Maple Avenue, Town', 'working', 2),
       ('user54', 'password54', 'Oliver Thompson', 'oliver.thompson@example.com', NULL, '4444444444', 0,
        'Singing, shopping', 'avatar54.jpg', '890 Elm Road, Village', 'working', 1),
       ('user55', 'password55', 'Ava Davis', 'ava.davis@example.com', NULL, '9999999999', 1, 'Playing guitar, hiking',
        'avatar55.jpg', '123 Pine Lane, County', 'working', 2),
       ('user56', 'password56', 'Liam Johnson', 'liam.johnson@example.com', NULL, '2222222222', 0, 'Reading, dancing',
        'avatar56.jpg', '321 Oak Street, City', 'working', 1),
       ('user57', 'password57', 'Olivia Wilson', 'olivia.wilson@example.com', NULL, '7777777777', 1,
        'Playing football, painting', 'avatar57.jpg', '567 Maple Avenue, Town', 'working', 2),
       ('user58', 'password58', 'Noah Thompson', 'noah.thompson@example.com', NULL, '4444444444', 0,
        'Singing, shopping', 'avatar58.jpg', '890 Elm Road, Village', 'working', 1),
       ('user59', 'password59', 'Sophia Davis', 'sophia.davis@example.com', NULL, '9999999999', 1,
        'Playing guitar, hiking', 'avatar59.jpg', '123 Pine Lane, County', 'working', 2),
       ('user60', 'password60', 'Logan Johnson', 'logan.johnson@example.com', NULL, '5554443333', 0,
        'Drawing, photography', 'avatar60.jpg', '456 Oak Street, City', 'working', 1),
       ('user61', 'password61', 'Jackson Smith', 'jackson.smith@example.com', NULL, '8888888888', 1,
        'Cooking, gardening', 'avatar61.jpg', '789 Maple Avenue, Town', 'working', 2),
       ('user62', 'password62', 'Ava Anderson', 'ava.anderson@example.com', NULL, '2223334444', 0, 'Reading, dancing',
        'avatar62.jpg', '321 Elm Road, Village', 'working', 1),
       ('user63', 'password63', 'Lucas Johnson', 'lucas.johnson@example.com', NULL, '5556667777', 1,
        'Playing basketball, painting', 'avatar63.jpg', '567 Pine Lane, County', 'working', 2),
       ('user64', 'password64', 'Emma Brown', 'emma.brown@example.com', NULL, '1112223333', 0, 'Singing, shopping',
        'avatar64.jpg', '890 Oak Street, City', 'working', 1),
       ('user65', 'password65', 'Oliver Wilson', 'oliver.wilson@example.com', NULL, '4445556666', 1,
        'Playing piano, hiking', 'avatar65.jpg', '123 Maple Avenue, Town', 'working', 2),
       ('user66', 'password66', 'Mia Johnson', 'mia.johnson@example.com', NULL, '7778889999', 0, 'Drawing, photography',
        'avatar66.jpg', '456 Elm Road, Village', 'working', 1),
       ('user67', 'password67', 'Liam Smith', 'liam.smith@example.com', NULL, '1112223333', 1, 'Cooking, gardening',
        'avatar67.jpg', '789 Pine Lane, County', 'working', 2),
       ('user68', 'password68', 'Olivia Anderson', 'olivia.anderson@example.com', NULL, '4445556666', 0,
        'Reading, dancing', 'avatar68.jpg', '321 Oak Street, City', 'working', 1),
       ('user69', 'password69', 'Noah Johnson', 'noah.johnson@example.com', NULL, '8889990000', 1,
        'Playing basketball, painting', 'avatar69.jpg', '567 Maple Avenue, Town', 'working', 2),
       ('user70', 'password70', 'Sophia Davis', 'sophia.davis@example.com', NULL, '1112223333', 0, 'Singing, shopping',
        'avatar70.jpg', '890 Elm Road, Village', 'working', 1),
       ('user71', 'password71', 'Logan Wilson', 'logan.wilson@example.com', NULL, '5556667777', 1,
        'Playing piano, hiking', 'avatar71.jpg', '123 Pine Lane, County', 'working', 2),
       ('user72', 'password72', 'Jackson Johnson', 'jackson.johnson@example.com', NULL, '2223334444', 0,
        'Drawing, photography', 'avatar72.jpg', '456 Oak Street, City', 'working', 1),
       ('user73', 'password73', 'Ava Smith', 'ava.smith@example.com', NULL, '7778889999', 1, 'Cooking, gardening',
        'avatar73.jpg', '789 Maple Avenue, Town', 'working', 2),
       ('user74', 'password74', 'Lucas Anderson', 'lucas.anderson@example.com', NULL, '4445556666', 0,
        'Reading, dancing', 'avatar74.jpg', '321 Elm Road, Village', 'working', 1),
       ('user75', 'password75', 'Emma Johnson', 'emma.johnson@example.com', NULL, '8889990000', 1,
        'Playing basketball, painting', 'avatar75.jpg', '567 Pine Lane, County', 'working', 2),
       ('user76', 'password76', 'Oliver Smith', 'oliver.smith@example.com', NULL, '1112223333', 0, 'Singing, shopping',
        'avatar76.jpg', '890 Oak Street, City', 'working', 1),
       ('user77', 'password77', 'Mia Davis', 'mia.davis@example.com', NULL, '5556667777', 1, 'Playing piano, hiking',
        'avatar77.jpg', '123 Maple Avenue, Town', 'working', 2),
       ('user78', 'password78', 'Liam Johnson', 'liam.johnson@example.com', NULL, '2223334444', 0, 'Reading, dancing',
        'avatar78.jpg', '456 Elm Road, Village', 'working', 1),
       ('user79', 'password79', 'Olivia Wilson', 'olivia.wilson@example.com', NULL, '7778889999', 1,
        'Cooking, gardening', 'avatar79.jpg', '789 Pine Lane, County', 'working', 2),
       ('user80', 'password80', 'Noah Anderson', 'noah.anderson@example.com', NULL, '4445556666', 0,
        'Drawing, photography', 'avatar80.jpg', '321 Oak Street, City', 'working', 1),
       ('user81', 'password81', 'Sophia Johnson', 'sophia.johnson@example.com', NULL, '8889990000', 1,
        'Playing basketball, painting', 'avatar81.jpg', '567 Maple Avenue, Town', 'working', 2),
       ('user82', 'password82', 'Logan Smith', 'logan.smith@example.com', NULL, '1112223333', 0, 'Singing, shopping',
        'avatar82.jpg', '890 Elm Road, Village', 'working', 1),
       ('user83', 'password83', 'Jackson Davis', 'jackson.davis@example.com', NULL, '5556667777', 1,
        'Playing piano, hiking', 'avatar83.jpg', '123 Pine Lane, County', 'working', 2),
       ('user84', 'password84', 'Ava Johnson', 'ava.johnson@example.com', NULL, '2223334444', 0, 'Reading, dancing',
        'avatar84.jpg', '456 Oak Street, City', 'working', 1),
       ('user85', 'password85', 'Lucas Wilson', 'lucas.wilson@example.com', NULL, '7778889999', 1, 'Cooking, gardening',
        'avatar85.jpg', '789 Maple Avenue, Town', 'working', 2),
       ('user86', 'password86', 'Emma Smith', 'emma.smith@example.com', NULL, '4445556666', 0, 'Reading, dancing',
        'avatar86.jpg', '321 Elm Road, Village', 'working', 1),
       ('user87', 'password87', 'Oliver Davis', 'oliver.davis@example.com', NULL, '8889990000', 1,
        'Playing basketball, painting', 'avatar87.jpg', '567 Pine Lane, County', 'working', 2),
       ('user88', 'password88', 'Mia Johnson', 'mia.johnson@example.com', NULL, '1112223333', 0, 'Singing, shopping',
        'avatar88.jpg', '890 Oak Street, City', 'working', 1),
       ('user89', 'password89', 'Liam Wilson', 'liam.wilson@example.com', NULL, '5556667777', 1,
        'Playing piano, hiking', 'avatar89.jpg', '123 Maple Avenue, Town', 'working', 2),
       ('user90', 'password90', 'Olivia Anderson', 'olivia.anderson@example.com', NULL, '2223334444', 0,
        'Reading, dancing', 'avatar90.jpg', '456 Elm Road, Village', 'working', 1),
       ('user91', 'password91', 'Noah Johnson', 'noah.johnson@example.com', NULL, '7778889999', 1, 'Cooking, gardening',
        'avatar91.jpg', '789 Pine Lane, County', 'working', 2),
       ('user92', 'password92', 'Sophia Smith', 'sophia.smith@example.com', NULL, '4445556666', 0,
        'Drawing, photography', 'avatar92.jpg', '321 Oak Street, City', 'working', 1),
       ('user93', 'password93', 'Logan Davis', 'logan.davis@example.com', NULL, '8889990000', 1,
        'Playing basketball, painting', 'avatar93.jpg', '567 Maple Avenue, Town', 'working', 2),
       ('user94', 'password94', 'Jackson Johnson', 'jackson.johnson@example.com', NULL, '1112223333', 0,
        'Singing, shopping', 'avatar94.jpg', '890 Elm Road, Village', 'working', 1),
       ('user95', 'password95', 'Ava Wilson', 'ava.wilson@example.com', NULL, '5556667777', 1, 'Playing piano, hiking',
        'avatar95.jpg', '123 Pine Lane, County', 'working', 2),
       ('user96', 'password96', 'Lucas Anderson', 'lucas.anderson@example.com', NULL, '2223334444', 0,
        'Reading, dancing', 'avatar96.jpg', '456 Oak Street, City', 'working', 1),
       ('user97', 'password97', 'Emma Johnson', 'emma.johnson@example.com', NULL, '7778889999', 1, 'Cooking, gardening',
        'avatar97.jpg', '789 Maple Avenue, Town', 'working', 2),
       ('user98', 'password98', 'Oliver Smith', 'oliver.smith@example.com', NULL, '4445556666', 0,
        'Drawing, photography', 'avatar98.jpg', '321 Elm Road, Village', 'working', 1),
       ('user99', 'password99', 'Mia Davis', 'mia.davis@example.com', NULL, '8889990000', 1,
        'Playing basketball, painting', 'avatar99.jpg', '567 Pine Lane, County', 'working', 2),
       ('user100', 'password100', 'Liam Johnson', 'liam.johnson@example.com', NULL, '1112223333', 0,
        'Singing, shopping', 'avatar100.jpg', '890 Oak Street, City', 'working', 1);

CREATE table Friendships
(
    requestId int primary key auto_increment,
    senderId    int,
    receiverId  int,
    status    varchar(10),
    FOREIGN KEY (senderId) references user (id),
    FOREIGN KEY (receiverId) references user (id)
);

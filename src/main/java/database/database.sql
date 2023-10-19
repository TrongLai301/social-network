create database SocialNetwork ;
use SocialNetwork;

// quản lý tài khoản người dùng đăng nhập
create table userAccount(
idAccount int auto_increment primary key,
username varchar(50) not null,
password varchar(32) not null,
CONSTRAINT CHK_PasswordLength CHECK (length(password) >= 6 AND length(password)<= 32),
permission int,
<<<<<<< HEAD
foreign key(permission) references permission(idPermision)
);
=======
foreign key(permission) references permission(idPermission)
);

// quản lý vai trò người dùng
>>>>>>> be9b0f3c07c5eeed94e878af9d52db035426340a
create table permission(
idPermission int auto_increment primary key,
namePermission varchar(20) not null
);
<<<<<<< HEAD
<<<<<<< HEAD
insert into permission(namePermission) values ('admin');
insert into permission(namePermission) values ('user');
insert into userAccount(username,password,permission) value ('user','123456',2);
=======
<<<<<<< HEAD
=======
=======

>>>>>>> ba99ff3e6d64f460c27ae2ee4c0411f409b25a98
// thêm vai trò quyền hạn
insert into permission(namePermission) values ('admin');
insert into permission(namePermission) values ('user');

// thêm người dùng
>>>>>>> 9df50fa518dcb77710f577d411e754c57b1c311a
insert into userAccount(username,password,permission) value ('user','123456',2);
// tạo bảng trạng thái người dùng
create table userStatus(
                           idAccount int,
                           status varchar(10),
                           primary key(idAccount),
                           foreign key(idAccount) references userAccount(idAccount)
);

// thêm dữ liệu trạng thái người dùng
insert into userStatus(idAccount,status) values (2,"block");

// tạo thủ tục hiển thị danh sách người dùng gồm id , tên tài khoản , mật khẩu , quyền hạn , trạng thái
DELIMITER $$
create procedure showUserWithStatus()
begin
select userAccount.idAccount,username,password,namePermission,status from userAccount inner join permission on userAccount.permission = permission.idPermission left join userStatus on userAccount.idAccount = userStatus.idAccount;
end $$
<<<<<<< HEAD
>>>>>>> be9b0f3c07c5eeed94e878af9d52db035426340a
=======

>>>>>>> ba99ff3e6d64f460c27ae2ee4c0411f409b25a98

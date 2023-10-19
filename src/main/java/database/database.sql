create database SocialNetwork ;
use SocialNetwork;
# // quản lý tài khoản người dùng đăng nhập
create table userAccount(
idAccount int auto_increment primary key,
username varchar(50) not null,
password varchar(32) not null,
CONSTRAINT CHK_PasswordLength CHECK (length(password) >= 6 AND length(password)<= 32),
permission int,
foreign key(permission) references permission(idPermission)
);
# // quản lý vai trò người dùng
create table permission(
idPermission int auto_increment primary key,
namePermission varchar(20) not null
);
# // thêm vai trò quyền hạn
insert into permission(namePermission) values ('admin');
insert into permission(namePermission) values ('user');
# // thêm người dùng
insert into userAccount(username,password,permission) value ('user','123456',2);
# // tạo bảng trạng thái người dùng
create table userStatus(
                           idAccount int,
                           status varchar(10),
                           primary key(idAccount),
                           foreign key(idAccount) references userAccount(idAccount)
);
# // thêm dữ liệu trạng thái người dùng
# insert into userStatus(idAccount,status) values (2,"block");
# // tạo thủ tục hiển thị danh sách người dùng gồm id , tên tài khoản , mật khẩu , quyền hạn , trạng thái
DELIMITER $$
create procedure showUserWithStatus()
begin
select userAccount.idAccount,username,password,namePermission,status from userAccount inner join permission on userAccount.permission = permission.idPermission left join userStatus on userAccount.idAccount = userStatus.idAccount;
end $$
package model;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String birthdate;
    private String avatar;
    private String name;
    private String address;
    private String hobby;

    private int permission;

//
    public User(int id,String username, String password, String email, String phone, String birthdate, String avatar, String name, String address, String hobby) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.avatar = avatar;
        this.name = name;
        this.address = address;
        this.hobby = hobby;
        this.id = id;
    }
    //form dang ky
    public User(String username , String password,String email,String phone,String birthdate){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
    }
// form dang nhap
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // form profile
    public User(String username, String avatar, String name, String address,String hobby,String phone){
        this.username = username;
        this.avatar = avatar;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.hobby = hobby;
    }

    public User(int id, String username, String password, int permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permission = permission;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", hobby='" + hobby + '\'' +
                ", permission=" + permission +
                '}';
    }
}

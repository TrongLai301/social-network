package model;

import java.sql.Blob;
import java.time.LocalDate;

public class Status {
    private int id;
    private LocalDate createTime;
    private String description;
    private String media;
    private int permission;
    private int idUser;


    public Status(int id, LocalDate createTime, String description, String media, int permission, int idUser) {
        this.id = id;
        this.createTime = createTime;
        this.description = description;
        this.media = media;
        this.permission = permission;
        this.idUser = idUser;
    }

    public Status() {
    }

    public Status(String description, int idUser, LocalDate createTime, String media, int permission) {
        this.createTime = createTime;
        this.description = description;
        this.media = media;
        this.idUser = idUser;
        this.permission=permission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", media='" + media + '\'' +
                ", permission=" + permission +
                ", idUser=" + idUser +
                '}';
    }
}

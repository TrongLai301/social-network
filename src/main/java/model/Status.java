package model;

import java.sql.Blob;
import java.time.LocalDateTime;

public class Status {
    private int id;
    private LocalDateTime createTime;
    private String description;
    private Blob media;
    private int permission;
    private int idUser;

    public Status(int id, LocalDateTime createTime, String description, Blob media, int permission,int idUser) {
        this.id = id;
        this.createTime = createTime;
        this.description = description;
        this.media = media;
        this.permission = permission;
        this.idUser = idUser;
    }
    public Status(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getMedia() {
        return media;
    }

    public void setMedia(Blob media) {
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
}

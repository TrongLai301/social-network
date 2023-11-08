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
    private int likeCount;
    private int commentCount;


    public Status(int id, LocalDate createTime, String description, String media, int permission, int idUser, int likeCount,int commentCount) {
        this.id = id;
        this.createTime = createTime;
        this.description = description;
        this.media = media;
        this.permission = permission;
        this.idUser = idUser;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }

    public Status() {
    }

    public Status(String description, int idUser, LocalDate createTime, String media, int permission, int likeCount,int commentCount) {
        this.createTime = createTime;
        this.description = description;
        this.media = media;
        this.idUser = idUser;
        this.permission = permission;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
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


    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}

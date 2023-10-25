package model;

import java.time.LocalDate;
import java.util.Date;

public class Status {
    private int id;
    private LocalDate createTime;
    private String description;
    private String img;
    private String video;
    private String permission;

    public Status(int id, LocalDate createTime, String description, String img, String video, String permission) {
        this.id = id;
        this.createTime = createTime;
        this.description = description;
        this.img = img;
        this.video = video;
        this.permission = permission;
    }
    public Status(){}

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}

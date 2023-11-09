package model;

import java.util.Date;

public class Comment {
    private int idComment;
    private int idUser;
    private int idStatus;
    private String content;
    private int likeCount;
    private Date createdTime;

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", idUser=" + idUser +
                ", idStatus=" + idStatus +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", createdTime=" + createdTime +
                '}';
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

}

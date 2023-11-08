package model;

public class Comment {
   private int idCmt ;
    private int idUser ;
    private int idStatus ;
    private String content ;
    private int likeCount;

    public Comment() {
    }

    public Comment(int idCmt, int idUser, int idStatus, String content, int likeCount) {
        this.idCmt = idCmt;
        this.idUser = idUser;
        this.idStatus = idStatus;
        this.content = content;
        this.likeCount = likeCount;
    }

    public int getIdCmt() {
        return idCmt;
    }

    public void setIdCmt(int idCmt) {
        this.idCmt = idCmt;
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
}

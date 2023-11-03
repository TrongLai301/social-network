package model;

public class Like {
    private int id;
    private int idUser;
    private int idStatus;
    private int status;
    public Like(){}

    public Like(int id, int idUser, int idStatus,int status) {
        this.id = id;
        this.idUser = idUser;
        this.idStatus = idStatus;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}

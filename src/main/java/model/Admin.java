package model;

import java.util.Date;

public class Admin {
    private int id;
    private  int idUser;
    private Date date;
    private  int idPermission;
    private String week;
    private String day;
    private String month;
    private String year;
    private String hour;
    private String minutes;
    private String second;


    public Admin(int id, int idUser, Date date) {
        this.id = id;
        this.idUser = idUser;
        this.date = date;
    }
    public Admin(int idUser, int idPermission) {
        this.idUser = idUser;
        this.idPermission = idPermission;
    }

    public Admin(int id, int idUser, String week, String day, String month, String year, String hour, String minutes, String second) {
        this.id = id;
        this.idUser = idUser;
        this.week = week;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
        this.second = second;
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

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }


    public int getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(int idPermission) {
        this.idPermission = idPermission;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

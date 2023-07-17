package com.example.personnel;

public class clockOutModel {

//    variables

    private int id;

    private String clockOutTime;

    private String date;

//    constructor


    public clockOutModel(int id, String clockOutTime, String date) {
        this.id = id;
        this.clockOutTime = clockOutTime;
        this.date = date;
    }

//    to string method


    @Override
    public String toString() {
        return "clockOutModel{" +
                "id=" + id +
                ", clockOutTime='" + clockOutTime + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(String clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

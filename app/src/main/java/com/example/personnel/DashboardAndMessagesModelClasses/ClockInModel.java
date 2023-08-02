package com.example.personnel.DashboardAndMessagesModelClasses;

public class ClockInModel {

//    define variables for data
    private int id;

    private String date;

    private String clockInTime;


//    create constructor


    public ClockInModel(int id, String date, String clockInTime) {
        this.id = id;
        this.date = date;
        this.clockInTime = clockInTime;
    }

//    create to string method for testing purposes

    @Override
    public String toString() {
        return "clockInModel{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", clockInTime='" + clockInTime + '\'' +
                '}';
    }

//    create getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(String clockInTime) {
        this.clockInTime = clockInTime;
    }
}

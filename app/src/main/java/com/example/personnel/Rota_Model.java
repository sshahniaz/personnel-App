package com.example.personnel;

public class Rota_Model {

    private int rotaId ;
    private int employeeId;
    private String day;

    private String startTime;
    private String endTime;
    private String breakTime;

    public Rota_Model(int rotaId, String day, String startTime, String endTime) {
        this.rotaId = rotaId;

        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public int getRotaId() {
        return rotaId;
    }

    public void setRotaId(int rotaId) {
        this.rotaId = rotaId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    @Override
    public String toString() {
        return "Rota_Model{" +
                "rotaId=" + rotaId +
                ", employeeId=" + employeeId +
                ", day='" + day + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", breakTime='" + breakTime + '\'' +
                '}';
    }
}

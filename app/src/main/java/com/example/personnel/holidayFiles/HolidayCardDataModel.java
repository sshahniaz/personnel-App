package com.example.personnel.holidayFiles;

public class HolidayCardDataModel {

    //This class is used to display holiday cards for the list view

    private String startDate;
    private String endDate;
    private String leaveType;
    private int status;

    public HolidayCardDataModel(String startDate, String endDate, String leaveType, int status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

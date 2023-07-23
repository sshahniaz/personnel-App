package com.example.personnel.DashboardAndMessagesModelClasses;

public class MessageModel {
    private int id;

    private String messageTitle;

    private String messageDate;

    private String messageText;

    private boolean expanded;

//    Constructor
    public MessageModel(int id, String messageTitle, String messageDate, String messageText) {
        this.id = id;
        this.messageTitle = messageTitle;
        this.messageDate = messageDate;
        this.messageText = messageText;
        expanded = false;
    }

//    Getter and Setter Methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}

package assignment.com.smsapplication.sms.model;

public class Sms {
    private String sender;
    private String read;
    private String message;
    private String date;
    private String hoursAgo ="";

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getHoursAgo() {
        return hoursAgo;
    }

    public void setHoursAgo(String hoursAgo) {
        this.hoursAgo = hoursAgo;
    }
}
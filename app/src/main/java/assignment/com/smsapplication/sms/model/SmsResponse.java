package assignment.com.smsapplication.sms.model;

import java.util.List;

public class SmsResponse {
    private List<Sms> smsList;

    public List<Sms> getSmsList() {
        return smsList;
    }

    public void setSmsList(List<Sms> smsList) {
        this.smsList = smsList;
    }
}

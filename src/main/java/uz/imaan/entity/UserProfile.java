package uz.imaan.entity;

public class UserProfile {
    private long chatId;
    private String name;
    private String phoneNumber;
    private double lat;
    private double lon;

    public UserProfile(long chatId) {
        this.chatId = chatId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.lat = lat;
        this.lon = lon;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLokatsiya(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }
}



















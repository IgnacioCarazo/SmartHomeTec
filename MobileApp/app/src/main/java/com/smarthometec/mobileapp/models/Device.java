package com.smarthometec.mobileapp.models;
public class Device {
    private int serialNumber;
    private String description;
    private String consumption;
    private String brand;
    private String type;
    private String room;
    private String date_created;
    public Device(int serialNumber,String description, String consumption, String brand, String type, String room, String date_created) {
        this.serialNumber = serialNumber;
        this.description = description;
        this.consumption = consumption;
        this.brand = brand;
        this.type = type;
        this.room = room;
        this.date_created =date_created;
    }
    public int getSerialNumber() { return serialNumber; }
    public String getDescription(){return  description;}
    public String getConsumption() { return consumption; }
    public String getBrand() { return brand; }
    public String getType() { return type; }
    public String getRoom() { return room; }
    public String getDate_created() { return date_created; }
}

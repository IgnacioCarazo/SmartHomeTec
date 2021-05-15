package com.smarthometec.mobileapp.models;
/**
 * @class device
 * Clase molde lo para el guardar los dispositivos
 * @author JosephJimenez
 */
public class Device {
    private int serialNumber;
    private String description;
    private String consumption;
    private String brand;
    private String type;
    private String room;
    private String date_created;
    private String userEmail;
    private boolean active;
    public Device(int serialNumber, String description, String consumption, String brand, String type, String room, String date_created, String userEmail) {
        this.serialNumber = serialNumber;
        this.description = description;
        this.consumption = consumption;
        this.brand = brand;
        this.type = type;
        this.room = room;
        this.date_created =date_created;
        this.userEmail = userEmail;
        this.active = true;
    }
    public Device() {
        this.serialNumber = 0;
        this.description = null;
        this.consumption = null;
        this.brand = null;
        this.type = null;
        this.room = null;
        this.date_created =null;
        this.active=true;
    }
    public int getSerialNumber() { return serialNumber; }
    public String getDescription(){return  description;}
    public String getConsumption() { return consumption; }
    public String getBrand() { return brand; }
    public String getType() { return type; }
    public String getRoom() { return room; }
    public String getDate_created() { return date_created; }
    public String getUserEmail() { return userEmail; }
    public boolean isActive() { return active; }
    public void setSerialNumber(int serialNumber) { this.serialNumber = serialNumber; }
    public void setDescription(String description) { this.description = description; }
    public void setConsumption(String consumption) { this.consumption = consumption; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setType(String type) { this.type = type; }
    public void setRoom(String room) { this.room = room; }
    public void setDate_created(String date_created) { this.date_created = date_created; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setActive(boolean active) { this.active = active; }
}

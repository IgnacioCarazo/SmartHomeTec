package com.smarthometec.mobileapp.models;
public class Device {
    private String name;
    private int serialNumber;
    private String consumption;
    private String brand;
    private String associated;
    private String type;
    private String owner;
    private int idDistributor;
    private int price;
    private String room;
    private String date_created;
    public Device(String name, int serialNumber, String consumption, String brand, String associated, String type, String owner, int idDistributor, int price, String room, String date_created) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.consumption = consumption;
        this.brand = brand;
        this.associated = associated;
        this.type = type;
        this.owner = owner;
        this.idDistributor = idDistributor;
        this.price = price;
        this.room = room;
        this.date_created =date_created;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getSerialNumber() { return serialNumber; }
    public String getConsumption() { return consumption; }
    public String getBrand() { return brand; }
    public String getAssociated() { return associated; }
    public String getType() { return type; }
    public String getOwner() { return owner; }
    public int getIdDistributor() { return idDistributor; }
    public int getPrice() { return price; }
    public String getRoom() { return room; }
    public String getDate_created() { return date_created; }
}

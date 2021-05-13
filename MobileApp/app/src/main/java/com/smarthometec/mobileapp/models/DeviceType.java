package com.smarthometec.mobileapp.models;
/**
 * @class DeviceType
 * Clase mode lo para el deviceType
 * @author JosephJimenez
 */
public class DeviceType {
    private String name;
    private String description;
    private String warrantyTime;
    public DeviceType(String name, String description, String warrantyTime) {
        this.name = name;
        this.description = description;
        this.warrantyTime = warrantyTime;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getWarrantyTime() { return warrantyTime; }
    public void setWarrantyTime(String warrantyTime) { this.warrantyTime = warrantyTime; }
}

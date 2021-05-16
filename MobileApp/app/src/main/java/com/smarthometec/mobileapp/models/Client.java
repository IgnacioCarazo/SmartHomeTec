package com.smarthometec.mobileapp.models;
/**
 * @class Client
 * Molde para datos del usuario
 * @author JosephJimenez
 */
public class Client {
    private String name;
    private String primaryLastName;
    private String secondaryName;
    private String email;
    private String password;
    private String continent;
    private String country;
    private String deliveryAddress;
    public Client() {
        this.name = null;
        this.primaryLastName = null;
        this.secondaryName = null;
        this.email = null;
        this.password = null;
        this.continent = null;
        this.country = null;
        this.deliveryAddress = null;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPrimaryLastName() { return primaryLastName; }
    public void setPrimaryLastName(String primaryLastName) { this.primaryLastName = primaryLastName; }
    public String getSecondaryName() { return secondaryName; }
    public void setSecondaryName(String secondaryName) { this.secondaryName = secondaryName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getContinent() { return continent; }
    public void setContinent(String continent) { this.continent = continent; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
}
package com.smarthometec.mobileapp.models;
/**
 * @class Room
 * Clase molde lo para el aposento
 * @author JosephJimenez
 */
public class Room {
    private String name;
    private String userEmail;
    public Room(String name, String userEmail) {
        this.name = name;
        this.userEmail = userEmail;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
}

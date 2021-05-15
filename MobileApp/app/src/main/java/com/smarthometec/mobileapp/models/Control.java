package com.smarthometec.mobileapp.models;
/**
 * @class Control
 * Clase molde lo para el guardar el tiempo encendido
 * @author JosephJimenez
 */
public class Control {
    private int id;
    private int time;
    private String date;
    private int serialNumber;
    public Control() {
        this.id = 0;
        this.time = 0;
        this.date = null;
        serialNumber = 0;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getTime() { return time; }
    public void setTime(int time) { this.time = time; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public int getSerialNumber() { return serialNumber; }
    public void setSerialNumber(int serialNumber) { this.serialNumber = serialNumber; }
}

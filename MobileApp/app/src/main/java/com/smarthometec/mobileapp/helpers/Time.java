package com.smarthometec.mobileapp.helpers;
import java.util.concurrent.TimeUnit;
import static com.smarthometec.mobileapp.helpers.DevicesAdapter.time;
/**
 * @class Time
 * Clase hilo que permite contar el tiempo en que se encuentra encendido el dispositivo
 * @author JosephJimenez
 */
public class Time implements Runnable{
    private boolean turnOnData;
    public Time() {
        this.turnOnData = false;
    }
    @Override
    public void run() {
        while(this.turnOnData==true){
            try {
                time+=1;
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void setTurnOff(){
        this.turnOnData = false;
    }
    public void setTurnOn(){
        this.turnOnData = true;
    }
}

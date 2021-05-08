import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ClientService } from "../client-view/client-profile/client.service";
import { map, tap } from 'rxjs/operators';
import { Client } from "../models/client.model";
import { Observable } from "rxjs";
import { Device } from "../models/device.model";
import { DeviceType } from "../models/device-type.model";
import { Order } from "../models/order.model";
import { DeviceService } from "../admin-view/devices/device.service";
import { DeviceTypeService } from "../admin-view/device-types/device-types.service";



@Injectable({ providedIn: 'root' })
export class DataStorageService {

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json'})};

    constructor(private http: HttpClient,
                private clientService: ClientService,
                private deviceService: DeviceService,
                private deviceTypeService: DeviceTypeService,
                ) {

    }

/**
   * ------------------------------------------------
  * http request del login/register
  * ------------------------------------------------
  */

  /**
  * @name sendLoginInfo()
  * @argument {string} email
  * @argument {string} password
  * @argument {boolean} isAdmin
  * @description  It sends an http get request to the backend wiht the info of the user's email and password. The
  * link varies dependin of the value of isAdmin.
  * @returns {Observable<User>} A user observable.
  */
   sendLoginInfo(email: string, password: string, isAdmin: boolean) {

    if (isAdmin) {
        
    } else {
    }
      
  }

/**
 * ------------------------------------------------
  * http requests de dispositivos
  * ------------------------------------------------
  */

  /**
  * @name storeDevices()
  * @description It sends an http put request to the backend to store all the devices.
  */
  storeDevices() {
    
  }

  /**
  * @name deleteDevice()
  * @argument {Device} device
  * @description Deletes a device from the backend by sending an http delete request with the device serial number.
  */
  deleteDevice(device: Device) {
  }

  /**
  * @name storeDevice()
  * @argument {Device} device
  * @description It sends an http post request with a device as argument to store the respective device 
  * in the database.
  */
  storeDevice(device: Device) {
    
  }

  /**
  * @name fetchDevices()
  * @returns An observable array of devices  
  */
  fetchDevices() {
    
  }
  /**
   * ------------------------------------------------
  * http requests de tipo de dispositivos
  * ------------------------------------------------
  */

  /**
  * @name storeDeviceTypes()
  * @description Sends an http put request to store the device types array in the database
  */
   storeDeviceTypes() {
     
  }

  /**
  * @name storeDeviceType()
  * @argument {DeviceType} deviceType
  * @description  Sends an http post request to the database to store the deviceType.
  */
   storeDeviceType(deviceType: DeviceType) {
   
  }

  /**
  * @name fetchDeviceTypes()
  * @description  Sends an http get request to the backend to fetch the array of device types.
  * @returns An observable of an array of device types.
  */
   fetchDeviceTypes() {
    
  }

  /**
   * ------------------------------------------------
  * http requests de ordenes
  * ------------------------------------------------
  */

   /**
  * @name sendOrder()
  * @description Sends an http get request to send the order to  the database.
  */
   sendOrder() {
    
  } 
  
}
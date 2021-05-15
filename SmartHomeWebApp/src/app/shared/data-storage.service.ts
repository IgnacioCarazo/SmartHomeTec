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
import { Admin } from "../models/admin.model";
import { Distributor } from "../models/distributor.model";
import { DistributorService } from "../admin-view/distributor/distributor.service";
import { DashboardService } from "../admin-view/dashboard/dashboard.service";
import { AppDevice } from "../models/app-device.model";



@Injectable({ providedIn: 'root' })
export class DataStorageService {

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json'})};

    constructor(private http: HttpClient,
                private clientService: ClientService,
                private deviceService: DeviceService,
                private deviceTypeService: DeviceTypeService,
                private distributorService: DistributorService,
                private dashboardService: DashboardService
                ) {

    }

/**
   * ------------------------------------------------
  * http request del login/register
  * ------------------------------------------------
  */

  /**
  * @name sendLoginInfoClient()
  * @argument {string} email
  * @argument {string} password
  * @description  It sends an http get request to the backend wiht the info of the client's email and password. 
  * @returns {Observable<Client>} A client observable.
  */
   sendLoginInfoClient(email: string, password: string): Observable<Client> {
    return this.http.get<Client>('https://localhost:5001/api/Client/login/'+ email + '/' + password);    
  }


  /**
  * @name sendLoginInfoAdmin()
  * @argument {string} email
  * @argument {string} password
  * @description  It sends an http get request to the backend wiht the info of the admin's email and password.
  * @returns {Observable<Admin>} An admin observable.
  */
   sendLoginInfoAdmin(email: string, password: string) {
      return this.http.get<Admin>('https://localhost:5001/api/Admin/login/'+ email + '/' + password);  
  }

  /**
  * @name sendRegisterInfo()
  * @description  It sends an http get request to the backend wiht the info of the client's registration.
  */
   sendRegisterInfo(client: Client) {
    this.http
    .post(
      'https://localhost:5001/api/Client',
      client, this.httpOptions
    )
    .subscribe(response => {
      console.log(response);
    });      
  }

   /**
  * @name updateClient()
  * @description It sends an http put request to the backend to store all the devices.
  */
    updateClient(client: Client) {
      console.log("client", client);
      this.http
        .put(
          'https://localhost:5001/api/Client/',
          client
        )
        .subscribe(response => {
          console.log(response);
        });
        
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
  updateDevices(device: Device) {
    this.http
      .put(
        'https://localhost:5001/api/Device',
        device
      )
      .subscribe(response => {
        console.log(response);
      });
      this.fetchDevices();
  }

  /**
  * @name deleteDevice()
  * @argument {Device} device
  * @description Deletes a device from the backend by sending an http delete request with the device serial number.
  */
  deleteDevice(device: Device) {
    this.http.delete<Device>('https://localhost:5001/api/Device/' + device.serialNumber, this.httpOptions).subscribe();
    this.fetchDevices();
  }

  /**
  * @name storeDevice()
  * @argument {Device} device
  * @description It sends an http post request with a device as argument to store the respective device 
  * in the database.
  */
  storeDevice(device: Device) {
    this.http
      .post(
        'https://localhost:5001/api/Device',
        device, this.httpOptions
      )
      .subscribe(response => {
        console.log(response);
      });
      this.fetchDevices();

  }

  /**
  * @name fetchDevices()
  * @returns An observable array of devices  
  */
  fetchDevices() {
    return this.http
      .get<Device[]>(
        'https://localhost:5001/api/Device'
      )
      .pipe(
        map(devices => {
          return devices.map(device => {
            return {
              ...device
            };
          });
        }),
        tap(devices => {
          console.log(devices);
          this.deviceService.setDevices(devices);
        })
      )
  }

  /**
  * @name fetchAppDevices()
  * @returns An observable array of app devices  
  */
   fetchAppDevices() {
    return this.http
      .get<AppDevice[]>(
        'https://localhost:5001/api/AppDevice'
      )
      .pipe(
        map(appDevices => {
          return appDevices.map(appDevice => {
            return {
              ...appDevice
            };
          });
        }),
        tap(appDevices => {
          console.log(appDevices);
          this.deviceService.setAppDevices(appDevices);
        })
      )
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
   updateDeviceType(deviceType: DeviceType) {
    this.http
      .put(
        'https://localhost:5001/api/DeviceType',
        deviceType
      )
      .subscribe(response => {
        console.log(response);
      });
      this.fetchDeviceTypes(); 
  }

  /**
  * @name storeDeviceType()
  * @argument {DeviceType} deviceType
  * @description  Sends an http post request to the database to store the deviceType.
  */
   storeDeviceType(deviceType: DeviceType) {
    this.http
    .post(
      'https://localhost:5001/api/DeviceType',
      deviceType, this.httpOptions
    )
    .subscribe(response => {
      console.log(response);
    });
  this.fetchDeviceTypes();
  }

  /**
  * @name fetchDeviceTypes()
  * @description  Sends an http get request to the backend to fetch the array of device types.
  * @returns An observable of an array of device types.
  */
   fetchDeviceTypes() {
    return this.http
      .get<DeviceType[]>(
        'https://localhost:5001/api/DeviceType'
      )
      .pipe(
        map(deviceTypes => {
          return deviceTypes.map(deviceType => {
            return {
              ...deviceType
            };
          });
        }),
        tap(deviceTypes => {
          console.log(deviceTypes);
          this.deviceTypeService.setDeviceTypes(deviceTypes);
        })
      )
  }

  /**
  * @name deleteDeviceType()
  * @argument {DeviceType} deviceType
  * @description Deletes a device type from the backend by sending an http delete request with the device name.
  */
   deleteDeviceType(deviceType: DeviceType) {
    console.log(deviceType.name);
    this.http.delete<DeviceType>('https://localhost:5001/api/DeviceType/' + deviceType.name, this.httpOptions).subscribe();
    this.fetchDeviceTypes();
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
   sendOrder(order: Order) {
    this.http
      .post(
        'https://localhost:5001/api/Order',
        order, this.httpOptions
      )
      .subscribe(response => {
        console.log(response);
      });
      this.fetchDevices();

  } 

  /**
   * ------------------------------------------------
  * http requests de distributors
  * ------------------------------------------------
  */

  /**
  * @name fetchDistributors()
  * @description  Sends an http get request to the backend to fetch the array of distributors.
  * @returns An observable of an array of distributors.
  */
   fetchDistributors() {
    return this.http
      .get<Distributor[]>(
        'https://localhost:5001/api/Distributor'
      )
      .pipe(
        map(distributors => {
          return distributors.map(distributor => {
            return {
              ...distributor
            };
          });
        }),
        tap(distributors => {
          console.log(distributors);
          this.distributorService.setDistributors(distributors);
        })
      )
  }
  /**
  * @name getExcel()
  * @description  It sends an http get request to the backend to generate the excel file. 

  */
   getExcel() {
     console.log("EXCEL");
    return this.http.get('https://localhost:5001/api/Distributor/excel').subscribe();    
  }

  /**
   * ------------------------------------------------
  * http requests de dashboard
  * ------------------------------------------------
  */

  /**
  * @name fetchDevicesAverage()
  * @description Sends an http get request to fetch the Dashboard average value from the database.
  */
   fetchDevicesAverage() {
    return this.http.get<number>('https://localhost:5001/api/Device/average')  
} 

/**
  * @name fetchDevicesRegion()
  * @description  Sends an http get request to fetch the Dashboard devicesByRegion value from the database..
  */
 fetchDevicesRegion() {
  return this.http.get('https://localhost:5001/api/Distributor/region')
}

  
}
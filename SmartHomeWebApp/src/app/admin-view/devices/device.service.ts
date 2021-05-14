import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ClientService } from 'src/app/client-view/client-profile/client.service';
import { AppDevice } from 'src/app/models/app-device.model';
import { Client } from 'src/app/models/client.model';
import { DeviceType } from 'src/app/models/device-type.model';

import { Device } from 'src/app/models/device.model';
import { Distributor } from 'src/app/models/distributor.model';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { DistributorService } from '../distributor/distributor.service';

@Injectable()
export class DeviceService {
  device: Device;
  client: Client;
  private devices: Device[] = [];
  private appDevices: AppDevice[] = [];
  devicesChanged = new Subject<Device[]>();
  appDevicesChanged = new Subject<AppDevice[]>();

  private devicesByClientRegion: Device[];


  constructor(private clientService: ClientService,
              private distributorService: DistributorService) {

  }

  /**
  * @name setDevices()
  * @argument {Device[]} devices
  * @description  It set this service devices with the value of the devices argument.
  */
   setDevices(devices: Device[]) {
    this.devices = devices;
    this.devicesChanged.next(this.devices.slice());
  }

  /**
  * @name setAppDevices()
  * @argument {AppDevice[]} app devices
  * @description  It set this service appDevices with the value of the devices argument.
  */
   setAppDevices(appDevices: AppDevice[]) {
    this.appDevices = appDevices;
    this.appDevicesChanged.next(this.appDevices.slice());
  }

  /**
  * @name getAppDevices()
  * @returns The array of app devices of this service.  
  */
   getAppDevices() {
    return this.appDevices.slice();
  }

  /**
  * @name setDevice()
  * @argument {Device} device
  * @description  It sets the value of this service device with the device from the argument.
  */
  setDevice(device: Device) {
    this.device = device;
  }

  /**
  * @name getDevices()
  * @returns The array of devices of this service.  
  */
  getDevices() {
    console.log(this.devices);
    return this.devices.slice();
  }

  

  /**
  * @name addDevice()
  * @argument {Device} device
  * @argument {string} deviceTypeName
  * @description  Adds a Recipe to this service array of recipes
  */
   addDevice(device: Device, deviceTypeName: string) {
    this.devices.push(device);
    this.devicesChanged.next(this.devices.slice());
  }

  /**
  * @name updateDevice()
  * @argument {number} index
  * @argument {Device} newDevice
  * @argument {string} deviceTypeName
  * @description  It updates the value of a device of this service devices array. 
  */
  updateDevice(index: number, newDevice: Device, recipeTypeName: string) {
    this.devices[index] = newDevice;
    this.devicesChanged.next(this.devices.slice());
  }

  /**
  * @name getDevice()
  * @description It searches a device by its index
  * @returns {Device} A device
  */
   getDevice(index: number) {
    return this.devices[index];
  }

  /**
  * @name getActualDevice()
  * @returns This service recipe  
  */
  getActualDevice() {
    return this.device;
  }

  getDeviceByRegion(index: number) {
    console.log(123456);
    console.log(this.devicesByClientRegion)

    console.log(this.devicesByClientRegion[index])
    return this.devicesByClientRegion[index];
    

  }

  
  getDevicesByRegion() {  
    return this.devicesByClientRegion;
  }

  /**
  * @name deleteDevice()
  * @argument {number} index
  * @description deletes a device by its index from this service devices array.
  */
   deleteDevice(index: number) {
    this.devices.splice(index, 1);
    this.devicesChanged.next(this.devices.slice());
  }

  SetDevicesByRegion() {
    const distributorsByClientRegion = this.distributorService.getDistributorsByRegion();
    const list = [];
    for (let device of this.devices) {
      if (!device.associated) {
        for (let distributor of distributorsByClientRegion) {
          if (device.dniDistributor === distributor.dni) {
            list.push(device);
          }
        }
      }
    }
    this.devicesByClientRegion = list;

  }
 



  






}
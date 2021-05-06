import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { DeviceType } from 'src/app/models/device-type.model';

import { Device } from 'src/app/models/device.model';

@Injectable()
export class DeviceService {
  device: Device;
  private devices: Device[] = [
      new Device("Iphone 12", 342341212, "450mW", "Apple",true, new DeviceType("SmartPhone","esta es la descripcion"),"Ignacio Carazo"),
      new Device("Mate 20", 342341212, "400mW", "Huawei",false, new DeviceType("SmartPhone","esta es la descripcion"), ""),
      new Device("Iphone X", 342341212, "600mW", "Apple",false, new DeviceType("SmartPhone","esta es la descripcion"), ""),
      new Device("Macbook Pro", 342341212, "1100mW", "Apple",true, new DeviceType("Laptop","esta es la descripcion"), "Joseph Jimenez"),
      new Device("Refrigerador", 342341212, "46W", "Sony",false, new DeviceType("Electrodomestico","esta es la descripcion"), "")];
  devicesChanged = new Subject<Device[]>();


  constructor() {

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
    return this.devices.slice();
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

 



  






}
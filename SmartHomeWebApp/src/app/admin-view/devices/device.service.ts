import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Client } from 'src/app/models/client.model';
import { DeviceType } from 'src/app/models/device-type.model';

import { Device } from 'src/app/models/device.model';
import { Distributor } from 'src/app/models/distributor.model';

@Injectable()
export class DeviceService {
  device: Device;
  private devices: Device[] = [
      new Device("Iphone 12", 534634631, "450mW", "Apple",true, new DeviceType("SmartPhone","esta es la descripcion"),"Ignacio Carazo",new Distributor("Mario", "Hernandez", "Mena", 23451346, "America", "Costa Rica")),
      new Device("Mate 20", 3466793, "400mW", "Huawei",false, new DeviceType("SmartPhone","esta es la descripcion"), "",new Distributor("Mario", "Hernandez", "Mena", 23451346, "America", "Costa Rica")),
      new Device("Iphone X", 23466434, "600mW", "Apple",false, new DeviceType("SmartPhone","esta es la descripcion"), "",new Distributor("Mario", "Hernandez", "Mena", 23451346, "America", "Costa Rica")),
      new Device("Macbook Pro", 25364715, "1100mW", "Apple",true, new DeviceType("Laptop","esta es la descripcion"), "Joseph Jimenez", new Distributor("Manrique", "Jimenez", "De la Paz", 23543245, "America", "Costa Rica")),
      new Device("Refrigerador", 908635246, "46W", "Sony",false, new DeviceType("Electrodomestico","esta es la descripcion"), "",new Distributor("Haziel", "Gudino", "Rovira", 2325462, "Asia", "Japon")),
      new Device("Refrigerador", 342341212, "46W", "Sony",false, new DeviceType("Electrodomestico","esta es la descripcion"), "",new Distributor("Manrique", "Jimenez", "De la Paz", 23543245, "America", "Costa Rica"))];
  devicesChanged = new Subject<Device[]>();
  private devicesByClientRegion: Device[];


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

  getDeviceByRegion(index: number) {
    return this.devicesByClientRegion[index];

  }

  getDevicesByRegion(client: Client) {
    let _devices = [];
    for (var device of this.devices) {
        if (!device.associated) {
          if (device.distributor.continent === client.continent) {
              if (device.distributor.country === client.country) {
                _devices.push(device);
              }
          }
        }
    }
    this.devicesByClientRegion = _devices;
    return this.devicesByClientRegion;
  }

 



  






}
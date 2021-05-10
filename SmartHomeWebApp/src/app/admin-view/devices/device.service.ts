import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ClientService } from 'src/app/client-view/client-profile/client.service';
import { Client } from 'src/app/models/client.model';
import { DeviceType } from 'src/app/models/device-type.model';

import { Device } from 'src/app/models/device.model';
import { Distributor } from 'src/app/models/distributor.model';
import { DistributorService } from '../distributor/distributor.service';

@Injectable()
export class DeviceService {
  device: Device;
  client: Client;
  private devices: Device[] = [
      new Device("Iphone 12", 534634631, "450mW", "Apple",true, "SmartPhone","Ignacio Carazo",83726374, 300000),
      new Device("Mate 20", 3466793, "400mW", "Huawei",false, "SmartPhone", "", 23451346, 150000),
      new Device("Iphone X", 23466434, "600mW", "Apple",false, "SmartPhone", "", 23451346, 250000),
      new Device("Macbook Pro", 25364715, "1100mW", "Apple",true, "Laptop", "Joseph Jimenez", 71653621, 750000),
      new Device("Refrigerador", 908635246, "416W", "Sony",false, "Electrodomestico","", 2325462, 500000),
      new Device("Refrigerador", 342341212, "46W", "Sony",false, "Electrodomestico", "", 23543245, 70000)];
  devicesChanged = new Subject<Device[]>();
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
    console.log(123456);
    console.log(this.devicesByClientRegion)

    console.log(this.devicesByClientRegion[index])
    return this.devicesByClientRegion[index];
    

  }

  
  getDevicesByRegion() {
    
    return this.devicesByClientRegion;
    

  }

  SetDevicesByRegion() {
    const distributorsByClientRegion = this.distributorService.getDistributorsByRegion();
    console.log(distributorsByClientRegion)
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
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { DeviceType } from 'src/app/models/device-type.model';

import { Device } from 'src/app/models/device.model';

@Injectable()
export class DeviceTypeService {
  deviceType: DeviceType;
  deviceTypes: DeviceType[] = [new DeviceType("SmartPhone","esta es la descripcion"),
                               new DeviceType("Laptop","esta es la descripcion"),
                               new DeviceType("PC","esta es la descripcion"),
                               new DeviceType("Electrodomestico","esta es la descripcion"),
                               new DeviceType("Audifono","esta es la descripcion")];
  deviceTypesChanged = new Subject<DeviceType[]>();


  constructor() {

}

/**
* @name setDeviceTypes()
* @argument {DeviceType[]} deviceTypes
* @description  It set this service deviceTypes with the value of the deviceTypes argument.
*/
 setDeviceTypes(deviceTypes: DeviceType[]) {
  this.deviceTypes = deviceTypes;
  this.deviceTypesChanged.next(this.deviceTypes.slice());
}

/**
* @name setDeviceType()
* @argument {DeviceType} deviceType
* @description  It sets the value of this service device with the device from the argument.
*/
setDeviceType(deviceType: DeviceType) {
  this.deviceType = deviceType;
}

/**
* @name getDeviceTypes()
* @returns The array of deviceTypes of this service.  
*/
getDeviceTypes() {
  return this.deviceTypes.slice();
}

/**
* @name getDeviceType()
* @description It searches a deviceType by its index
* @returns {DeviceType} A deviceType
*/
 getDeviceType(index: number) {
  return this.deviceTypes[index];
}

/**
* @name getActualDeviceType()
* @returns This service deviceType  
*/
getActualDeviceType() {
  return this.deviceType;
}






  






}
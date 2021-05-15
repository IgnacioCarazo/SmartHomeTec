import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { DeviceType } from 'src/app/models/device-type.model';

import { Device } from 'src/app/models/device.model';
import { DataStorageService } from 'src/app/shared/data-storage.service';

@Injectable()
export class DeviceTypeService {
  deviceType: DeviceType;
  deviceTypes: DeviceType[] = [];
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
* @description  It sets the value of this service device type with the device type from the argument.
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

/**
  * @name deleteDeviceType()
  * @argument {number} index
  * @description deletes a device type by its index from this service devices array.
  */
 deleteDeviceType(index: number) {
  this.deviceTypes.splice(index, 1);
  this.deviceTypesChanged.next(this.deviceTypes.slice());
}





/**
  * @name addDeviceType()
  * @argument {DeviceType} deviceType
  * @description Adds a recipe type to the array of recipe types of this service.
  */
 addDeviceType(deviceType: DeviceType) {
  this.deviceTypes.push(deviceType);
  this.deviceTypesChanged.next(this.deviceTypes.slice());
}

/**
* @name updateDeviceType()
* @argument {number} index
* @argument {DeviceType} newDeviceType
* @description  Updates the value of a recipe type within the recipe types array of this service.
*/
updateDeviceType(index: number, newDeviceType: DeviceType) {
  this.deviceTypes[index] = newDeviceType;
  this.deviceTypesChanged.next(this.deviceTypes.slice());
}

}
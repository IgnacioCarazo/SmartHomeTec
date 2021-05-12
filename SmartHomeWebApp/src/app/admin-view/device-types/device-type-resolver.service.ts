import { Injectable } from '@angular/core';
import {
  Resolve,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { DeviceType } from 'src/app/models/device-type.model';
import { Device } from 'src/app/models/device.model';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { DeviceTypeService } from './device-types.service';


@Injectable({ providedIn: 'root' })
export class DeviceTypeResolverService implements Resolve<DeviceType[]> {
  constructor(
    private dataStorageService: DataStorageService,
    private deviceTypeService: DeviceTypeService
  ) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const recipes = this.deviceTypeService.getDeviceTypes();

    if (recipes.length === 0) {
      return this.dataStorageService.fetchDeviceTypes();
    } else {
      return recipes;
    }
  }
}

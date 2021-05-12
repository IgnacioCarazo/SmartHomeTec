import { Injectable } from '@angular/core';
import {
  Resolve,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { Device } from 'src/app/models/device.model';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { DeviceService } from './device.service';


@Injectable({ providedIn: 'root' })
export class DeviceResolverService implements Resolve<Device[]> {
  constructor(
    private dataStorageService: DataStorageService,
    private deviceService: DeviceService
  ) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const recipes = this.deviceService.getDevices();

    if (recipes.length === 0) {
      return this.dataStorageService.fetchDevices();
    } else {
      return recipes;
    }
  }
}

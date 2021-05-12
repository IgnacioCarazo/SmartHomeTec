import { Injectable } from '@angular/core';
import {
  Resolve,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from '@angular/router';
import { Device } from 'src/app/models/device.model';
import { Distributor } from 'src/app/models/distributor.model';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { DistributorService } from './distributor.service';


@Injectable({ providedIn: 'root' })
export class DistributorResolverService implements Resolve<Distributor[]> {
  constructor(
    private dataStorageService: DataStorageService,
    private distributorService: DistributorService
  ) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const recipes = this.distributorService.getDistributors();

    if (recipes.length === 0) {
      return this.dataStorageService.fetchDistributors();
    } else {
      return recipes;
    }
  }
}

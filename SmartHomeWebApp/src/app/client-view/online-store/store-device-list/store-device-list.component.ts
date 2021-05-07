import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DeviceService } from 'src/app/admin-view/devices/device.service';
import { DistributorService } from 'src/app/admin-view/distributor/distributor.service';
import { Client } from 'src/app/models/client.model';
import { Device } from 'src/app/models/device.model';
import { Distributor } from 'src/app/models/distributor.model';
import { ClientService } from '../../client-profile/client.service';

@Component({
  selector: 'app-store-device-list',
  templateUrl: './store-device-list.component.html',
  styleUrls: ['./store-device-list.component.css']
})
export class StoreDeviceListComponent implements OnInit {
  devices: Device[];
  client: Client;
  devicesByClientRegion: Device[] = [];
  distributors: Distributor[];
  subscription: Subscription;

  constructor(private deviceService: DeviceService,
              private clientService: ClientService,
              private distributorService: DistributorService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.subscription = this.deviceService.devicesChanged
      .subscribe(
        (devices: Device[]) => {
          this.devices = devices;
        }
      );
    this.distributors = this.distributorService.getDistributors();
    this.client = this.clientService.getClient();
    this.devices = this.deviceService.getDevices();
    this.distributors = this.distributorService.getDistributorsByRegion(this.client);
    this.getDevices();
  }

  


  getDevices() {
    for (let distributor of this.distributors) {    
      for (let device of distributor.devices) {
        if (!device.associated) {
          this.devicesByClientRegion.push(device)
        }
      }
    }
    this.deviceService.SetDevicesByRegion(this.devicesByClientRegion);
  }



  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DeviceService } from 'src/app/admin-view/devices/device.service';
import { Client } from 'src/app/models/client.model';
import { Device } from 'src/app/models/device.model';
import { ClientService } from '../../client-profile/client.service';

@Component({
  selector: 'app-store-device-list',
  templateUrl: './store-device-list.component.html',
  styleUrls: ['./store-device-list.component.css']
})
export class StoreDeviceListComponent implements OnInit {
  devices: Device[];
  client: Client;
  devicesByClientRegion: Device[];
  subscription: Subscription;

  constructor(private deviceService: DeviceService,
              private clientService: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.subscription = this.deviceService.devicesChanged
      .subscribe(
        (devices: Device[]) => {
          this.devices = devices;
        }
      );
    this.client = this.clientService.getClient();
    this.devices = this.deviceService.getDevices();
    this.devicesByClientRegion = this.deviceService.getDevicesByRegion(this.client);
    console.log(this.devices);
    console.log(this.client);
    console.log(this.devicesByClientRegion)
  }



  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
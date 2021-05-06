import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Device } from 'src/app/models/device.model';
import { DeviceService } from '../device.service';

@Component({
  selector: 'app-device-list',
  templateUrl: './device-list.component.html',
  styleUrls: ['./device-list.component.css']
})
export class DeviceListComponent implements OnInit {

  devices: Device[];
  subscription: Subscription;

  constructor(private deviceService: DeviceService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.subscription = this.deviceService.devicesChanged
      .subscribe(
        (devices: Device[]) => {
          this.devices = devices;
        }
      );
    this.devices = this.deviceService.getDevices();
    console.log(this.devices);
  }

  /**
  * @name onNewDevice
  * @description Sets the link to 'new'
  */
   onNewDevice() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}

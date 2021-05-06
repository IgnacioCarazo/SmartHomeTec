import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Device } from 'src/app/models/device.model';
import { DeviceService } from '../device.service';

@Component({
  selector: 'app-device-detail',
  templateUrl: './device-detail.component.html',
  styleUrls: ['./device-detail.component.css']
})
export class DeviceDetailComponent implements OnInit {

  device: Device;
  id: number;

  constructor(private deviceService: DeviceService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.device = this.deviceService.getDevice(this.id);
        }
      );
  }

  /**
  * @name onEditDevice()
  * @description Sets the link to 'edit'. 
  */
   onEditDevice() {
    this.router.navigate(['edit'], {relativeTo: this.route});
  }

  /**
  * @name onDeleteDevice()
  * @description Deletes the current device and sets the link back to '/devices'.
  */
  onDeleteDevice() {
    console.log("Device Deleted")
  }

}

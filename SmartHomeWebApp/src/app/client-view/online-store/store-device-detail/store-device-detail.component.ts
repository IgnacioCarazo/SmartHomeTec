import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { DeviceService } from 'src/app/admin-view/devices/device.service';
import { Device } from 'src/app/models/device.model';

@Component({
  selector: 'app-store-device-detail',
  templateUrl: './store-device-detail.component.html',
  styleUrls: ['./store-device-detail.component.css']
})
export class StoreDeviceDetailComponent implements OnInit {
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
          this.device = this.deviceService.getDeviceByRegion(this.id);
        }
      );
  }

  /**
  * @name onOrderDevice()
  * @description Orders a device. 
  */
   onOrderDevice() {
    console.log("Dispositivo Ordenado")
  }

  

}

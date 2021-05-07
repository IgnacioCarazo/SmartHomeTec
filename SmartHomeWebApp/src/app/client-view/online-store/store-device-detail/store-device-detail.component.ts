import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { DeviceService } from 'src/app/admin-view/devices/device.service';
import { Client } from 'src/app/models/client.model';
import { Device } from 'src/app/models/device.model';
import { ClientService } from '../../client-profile/client.service';

@Component({
  selector: 'app-store-device-detail',
  templateUrl: './store-device-detail.component.html',
  styleUrls: ['./store-device-detail.component.css']
})
export class StoreDeviceDetailComponent implements OnInit {
  device: Device;
  id: number;
  client: Client;

  constructor(private deviceService: DeviceService,
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService) { }

  ngOnInit() {
    console.log(11111111111);

    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.device = this.deviceService.getDeviceByRegion(this.id);
          console.log(11111111111);
          console.log(this.device);
        }
      );
      this.client = this.clientService.getClient();
  }

  /**
  * @name onOrderDevice()
  * @description Orders a device. 
  */
   onOrderDevice() {
    console.log("Dispositivo Ordenado")
  }

  

}

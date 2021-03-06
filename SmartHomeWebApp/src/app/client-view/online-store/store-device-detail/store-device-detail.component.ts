import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { DeviceService } from 'src/app/admin-view/devices/device.service';
import { Client } from 'src/app/models/client.model';
import { Device } from 'src/app/models/device.model';
import { ClientService } from '../../client-profile/client.service';
import { DatePipe } from '@angular/common';
import { Order } from 'src/app/models/order.model';
import { DistributorService } from 'src/app/admin-view/distributor/distributor.service';
import { DataStorageService } from 'src/app/shared/data-storage.service';

@Component({
  selector: 'app-store-device-detail',
  templateUrl: './store-device-detail.component.html',
  styleUrls: ['./store-device-detail.component.css'],
  providers: [DatePipe]
})
export class StoreDeviceDetailComponent implements OnInit {
  device: Device;
  id: number;
  client: Client;
  myDate = new Date();
  order: Order;


  constructor(private deviceService: DeviceService,
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService,
    private datePipe: DatePipe,
    private distributorService: DistributorService,
    private dataStorageService: DataStorageService) { 
      
    }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.device = this.deviceService.getDeviceByRegion(this.id);
        }
      );
      this.client = this.clientService.getClient();
  }

  /**
  * @name onOrderDevice()
  * @description Orders a device. 
  */
   onOrderDevice() {
    const hour = this.datePipe.transform(this.myDate, 'HH:mm:ss')
    const date = this.datePipe.transform(this.myDate, 'dd/MM/yyyy');
    this.order = new Order(this.device.serialNumber, date, hour, this.device.price, this.client.email, this.device.brand);
    this.device.associated = true;
    this.device.ownerEmail = this.client.email;
    this.deviceService.updateDevice(this.id, this.device, "");
    this.dataStorageService.updateDevices(this.device);
    this.dataStorageService.sendOrder(this.order);
  }


  /**
  * @name getDistributor()
  * @description returns a distributor name according to the region of the client. 
  */
  getDistributor(dni: number) {
    const distributors = this.distributorService.getDistributorsByRegion();
    for (let distributor of distributors) {
      if (dni === distributor.dni) {
        return distributor.name;
      }
    }
  }

  

}

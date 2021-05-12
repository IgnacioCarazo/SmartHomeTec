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
    const hour = this.datePipe.transform(this.myDate, 'h:mm a')
    const date = this.datePipe.transform(this.myDate, 'dd-MM-yyyy');
    this.order = new Order(this.device.serialNumber, date, hour, this.device.price, this.client.email);
    console.log(this.order);
    console.log(date);
    console.log(this.myDate);
    console.log("Dispositivo Ordenado")
    //this.dataStorageService.sendOrder(this.order);
  }


  getDistributor(dni: number) {
    const distributors = this.distributorService.getDistributorsByRegion();
    for (let distributor of distributors) {
      if (dni === distributor.dni) {
        return distributor.name;
      }
    }
  }

  

}

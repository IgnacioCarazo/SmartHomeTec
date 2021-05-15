import { Component, OnInit } from '@angular/core';
import { Device } from 'src/app/models/device.model';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { DeviceService } from '../devices/device.service';
import { DashboardService } from './dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  devices: Device[];
  usuarios = [["Daniel", 5],["Marco", 7],["Jesus", 3],["Francisco", 2]];
  dispRegiones = [];
  average = 0;
  listaDispositivos = [];
  check = '✓';

  constructor(private deviceService: DeviceService,
              private dataStorageService: DataStorageService,
              private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.dataStorageService.fetchDevicesAverage().
    subscribe( average => {
      this.dashboardService.setAverageDevices(average);
      this.average = average;
    });
    this.dataStorageService.fetchDevicesRegion().
    subscribe( dispRegiones => {
      console.log(dispRegiones);
      this.dashboardService.setDispRegiones(dispRegiones);
    });
    this.dataStorageService.fetchDevices();
    this.devices = this.deviceService.getDevices();
    this.dispRegiones = this.dashboardService.getDispRegiones();
    this.average = this.dashboardService.getAverageDevices();
   
    

    
    for (var device of this.devices) {
      console.log(12334);
      if (device.associated) {
        let _device = [device.name,"✓", device.ownerEmail]
        this.listaDispositivos.push(_device)
      } else {
        let _device = [device.name,"X", " "]

        this.listaDispositivos.push(_device)
      }
    }
    console.log(this.devices);

  }

}

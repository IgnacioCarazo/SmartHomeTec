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
  appDevices = [];
  dispRegiones = [];
  average = 0;
  cantActivos = 0;

  listaDispositivos = [];
  check = '✓';

  constructor(private deviceService: DeviceService,
              private dataStorageService: DataStorageService,
              private dashboardService: DashboardService) { }


  getAppDevice(serialNumber : number) {
    for (let appDevice of this.appDevices) {
      if (appDevice.serialNumber === serialNumber)
        return true;
    }
    return false;
  }

  ngOnInit(): void {
    // Recupera los datos necesarios para mostrar lo solicitado en el dashboard
    this.dataStorageService.fetchActiveDevices().
    subscribe( cantActivos => {
      this.dashboardService.setActivos(cantActivos);
      this.cantActivos = cantActivos;
    });
    this.dataStorageService.fetchDevicesAverage().
    subscribe( average => {
      this.dashboardService.setAverageDevices(average);
      this.average = average;
    });

    this.dataStorageService.fetchDevicesRegion().
    subscribe( dispRegiones => {
      this.dashboardService.setDispRegiones(dispRegiones);
    });

    this.dataStorageService.fetchAppDevices().
    subscribe( appDevices => {
      this.deviceService.setAppDevices(appDevices)
      this.appDevices = appDevices;
      for (var device of this.devices) { 
         if (device.associated) {
          let flag = this.getAppDevice(device.serialNumber)

          if (flag) {
            let _device = [device.name,"✓", device.ownerEmail]
            this.listaDispositivos.push(_device)

          } else {
            let _device = [device.name,"X", device.ownerEmail]
            this.listaDispositivos.push(_device)

          }
          
        } else {
          let _device = [device.name,"X", " "]
          this.listaDispositivos.push(_device)
        }
      }

    });

    this.dataStorageService.fetchDevices();
    this.devices = this.deviceService.getDevices();
    this.dispRegiones = this.dashboardService.getDispRegiones();
    this.average = this.dashboardService.getAverageDevices();
    this.appDevices = this.deviceService.getAppDevices();

    
   
    

  }

}

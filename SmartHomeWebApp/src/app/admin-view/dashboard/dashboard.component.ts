import { Component, OnInit } from '@angular/core';
import { Device } from 'src/app/models/device.model';
import { DeviceService } from '../devices/device.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  devices: Device[];
  usuarios = [["Daniel", 5],["Marco", 7],["Jesus", 3],["Francisco", 2]];
  dispRegiones = [["Africa", 5],["America", 7],["Europa", 3],["Asia", 2]];
  listaDispositivos = [];
  check = '✓';

  constructor(private deviceService: DeviceService) { }

  ngOnInit(): void {
    this.devices = this.deviceService.getDevices();
    for (var device of this.devices) {
      if (device.associated) {
        let _device = [device.name,"✓", device.ownerName]
        this.listaDispositivos.push(_device)
      } else {
        let _device = [device.name,"X", " "]

        this.listaDispositivos.push(_device)
      }
    }
    console.log(this.listaDispositivos);
  }

}

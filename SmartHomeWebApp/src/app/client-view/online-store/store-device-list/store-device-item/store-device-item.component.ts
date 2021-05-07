import { Component, Input, OnInit } from '@angular/core';
import { Device } from 'src/app/models/device.model';

@Component({
  selector: 'app-store-device-item',
  templateUrl: './store-device-item.component.html',
  styleUrls: ['./store-device-item.component.css']
})
export class StoreDeviceItemComponent implements OnInit {

  @Input() device: Device;
  @Input() index: number;


  constructor() { }

  ngOnInit(): void {
  }

}

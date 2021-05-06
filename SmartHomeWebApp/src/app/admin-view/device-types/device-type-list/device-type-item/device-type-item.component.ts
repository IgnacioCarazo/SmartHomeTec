import { Component, Input, OnInit } from '@angular/core';
import { DeviceType } from 'src/app/models/device-type.model';

@Component({
  selector: 'app-device-type-item',
  templateUrl: './device-type-item.component.html',
  styleUrls: ['./device-type-item.component.css']
})
export class DeviceTypeItemComponent implements OnInit {

  @Input() deviceType: DeviceType;
  @Input() index: number;

  constructor() { }

  ngOnInit(): void {
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { DeviceType } from 'src/app/models/device-type.model';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { DeviceTypeService } from '../device-types.service';

@Component({
  selector: 'app-device-type-detail',
  templateUrl: './device-type-detail.component.html',
  styleUrls: ['./device-type-detail.component.css']
})
export class DeviceTypeDetailComponent implements OnInit {

  deviceType: DeviceType;
  id: number;

  constructor(private deviceTypeService: DeviceTypeService,
    private route: ActivatedRoute,
    private router: Router,
    private dataStorageService: DataStorageService) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.deviceType = this.deviceTypeService.getDeviceType(this.id);
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
    this.dataStorageService.deleteDeviceType(this.deviceType);
    this.deviceTypeService.deleteDeviceType(this.id);

  }

}
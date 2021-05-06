import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DeviceType } from 'src/app/models/device-type.model';
import { DeviceTypeService } from '../device-types.service';

@Component({
  selector: 'app-device-type-list',
  templateUrl: './device-type-list.component.html',
  styleUrls: ['./device-type-list.component.css']
})
export class DeviceTypeListComponent implements OnInit {

  deviceTypes: DeviceType[];
  subscription: Subscription;

  constructor(private deviceTypeService: DeviceTypeService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.subscription = this.deviceTypeService.deviceTypesChanged
      .subscribe(
        (deviceTypes: DeviceType[]) => {
          this.deviceTypes = deviceTypes;
        }
      );
    this.deviceTypes = this.deviceTypeService.getDeviceTypes();
    console.log(this.deviceTypes);
  }

  /**
  * @name onNewDeviceType
  * @description Sets the link to 'new'
  */
   onNewDeviceType() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}

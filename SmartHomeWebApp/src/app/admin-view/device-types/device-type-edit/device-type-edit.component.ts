import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { DeviceService } from '../../devices/device.service';
import { DeviceTypeService } from '../device-types.service';

@Component({
  selector: 'app-device-type-edit',
  templateUrl: './device-type-edit.component.html',
  styleUrls: ['./device-type-edit.component.css']
})
export class DeviceTypeEditComponent implements OnInit {

  id: number;
  editMode = false;
  deviceTypeForm: FormGroup;
  constructor(private route: ActivatedRoute,
              private deviceTypeService: DeviceTypeService,
              private router: Router,
              private dataStorageService: DataStorageService) { }

 
  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.editMode = params['id'] != null;
      this.initForm();
    });
  }

  /**
  * @name onSubmit()
  * @description Depending if its edit mode or add new device type mode it will update or add the current device type.
  */
   onSubmit() {
    if (this.editMode) {
      this.deviceTypeService.updateDeviceType(this.id, this.deviceTypeForm.value)
      this.dataStorageService.updateDeviceType(this.deviceTypeForm.value);
    } else {
      this.deviceTypeService.addDeviceType(this.deviceTypeForm.value)
      this.dataStorageService.storeDeviceType(this.deviceTypeForm.value);
    }
    this.onCancel();
  }

  /**
  * @name onCancel()
  * @description Returns the link to its previous link.
  */
   onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

   /**
  * @name initForm()
  * @description If its edit mode it will load the inputs of the form with the preexistent values of the device type. Otherwise
  * it will just set thes values 'empty' for the user to fill.
  */
    private initForm() {
      let name = '';
      let description = '';
      let warrantyTime = 0;
      
  
      if (this.editMode) {
        const deviceType = this.deviceTypeService.getDeviceType(this.id);
        name = deviceType.name;
        description = deviceType.description;
        warrantyTime = deviceType.warrantyTime;}
  
  
  
       
  
        this.deviceTypeForm = new FormGroup({
        name: new FormControl(name, Validators.required),
        description: new FormControl(description, Validators.required),
        warrantyTime: new FormControl(warrantyTime, Validators.required)
      });
    }
  
}


  

 


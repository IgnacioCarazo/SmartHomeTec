import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
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
              private router: Router) { }

 
  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.editMode = params['id'] != null;
      this.initForm();
    });
  }

  /**
  * @name onSubmit()
  * @description Depending if its edit mode or add new recipe mode it will update or add the current recipe.
  */
   onSubmit() {
    if (this.editMode) {
        console.log("Tipo de Dispositivo modificado")
    } else {
      console.log("Tipo de Dispositivo agregado")
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
  * @description If its edit mode it will load the inputs of the form with the preexistent values of the recipe. Otherwise
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


  

 

import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { DeviceType } from 'src/app/models/device-type.model';
import { DeviceService } from '../device.service';

@Component({
  selector: 'app-device-edit',
  templateUrl: './device-edit.component.html',
  styleUrls: ['./device-edit.component.css']
})
export class DeviceEditComponent implements OnInit {

  id: number;
  editMode = false;
  deviceForm: FormGroup;
  deviceTypes = [];
  constructor(private route: ActivatedRoute,
              private deviceService: DeviceService,
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
        console.log("Dispositivo modificado")
    } else {
      console.log("Dispositivo agregado")
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
      let serialNumber = 0;
      let eConsumption = '';
      let brand = '';
      let type: DeviceType;
      let recipeFinishTime = '';
      let deviceTypeName = '';
  
      if (this.editMode) {
        const device = this.deviceService.getDevice(this.id);
        name = device.name;
        serialNumber = device.serialNumber;
        eConsumption = device.eConsumption;
        brand = device.brand;
        deviceTypeName = device.type.name;}
  
  
  
       
  
      this.deviceForm = new FormGroup({
        name: new FormControl(name, Validators.required),
        serialNumber: new FormControl(serialNumber),
        eConsumption: new FormControl(eConsumption),
        brand: new FormControl(brand, Validators.required),
        type: new FormControl(deviceTypeName, Validators.required),
      });
    }

}


  

 


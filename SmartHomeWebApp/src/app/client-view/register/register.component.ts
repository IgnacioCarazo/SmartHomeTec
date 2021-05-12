import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Client } from 'src/app/models/client.model';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { ClientService } from '../client-profile/client.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  clientForm: FormGroup;
  client: Client;

  constructor(private router: Router,
              private clientService: ClientService,
              private dataStorageService: DataStorageService) { }

  ngOnInit(): void {
    this.initForm();
  }

  /**
  * @name onAddDeliveryAdressess()
  * @description  Adds a new input for adding ingredients to the recipe
  */
   onAddAdress() {
    (<FormArray>this.clientForm.get('deliveryAdresses')).push(
      new FormGroup({
        adress: new FormControl(null, Validators.required)
      })
    );
  }

  get controls() {
    return (<FormArray>this.clientForm.get('deliveryAdresses')).controls;
  }


   /**
  * @name onDeleteAddress
  * @argument {number} index
  * @description It deletes an ingredient from the recipeform ingredients with an specific index.
  */
    onDeleteAdress(index: number) {
      (<FormArray>this.clientForm.get('deliveryAdresses')).removeAt(index);
    }

  onSubmit() {      
    console.log(this.clientForm.value);
    this.client = this.clientForm.value;
    //this.dataStorageService.sendRegisterInfo(this.client);
    this.router.navigate(['/client/login']);

    this.clientForm.reset();
  }

  /**
  * @name initForm()
  * @description If its edit mode it will load the inputs of the form with the preexistent values of the recipe. Otherwise
  * it will just set thes values 'empty' for the user to fill.
  */
   private initForm() {
    let name = '';
    let primaryLastName = '';
    let secondaryLastName = '';
    let email = '';
    let password = '';
    let continent = '';
    let country = '';
    let deliveryAdresses = new FormArray([]);







    this.clientForm = new FormGroup({
      name: new FormControl(name, Validators.required),
      primaryLastName: new FormControl(primaryLastName,Validators.required),
      secondaryLastName: new FormControl(secondaryLastName,Validators.required),
      email: new FormControl(email,Validators.required),
      password: new FormControl(password, Validators.required),
      continent: new FormControl(continent, Validators.required),
      country: new FormControl(country, Validators.required),
      deliveryAdresses: deliveryAdresses
      
    });
  }
  

}

import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { ClientService } from './client.service';

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {

  clientForm: FormGroup;


  constructor(private router: Router,
    private clientService: ClientService,
    private dataStorageService: DataStorageService) { }


  onSubmit() {      
    this.clientService.client = this.clientForm.value;
    let dA = [];
    for (let dAdd of this.clientForm.value.deliveryAdresses) {
      dA.push(dAdd.adress);
    }
    this.clientForm.value.deliveryAdresses = dA;
    this.dataStorageService.updateClient(this.clientForm.value);
    this.clientService.updateClient(this.clientForm.value)
    this.clientForm.reset();
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


  ngOnInit(): void {
    this.initForm();
  }

   /**
  * @name initForm()
  * @description If its edit mode it will load the inputs of the form with the preexistent values of the recipe. Otherwise
  * it will just set thes values 'empty' for the user to fill.
  */
    private initForm() {
      const client = this.clientService.getClient();

      let name = '';
      let primaryLastName = '';
      let secondaryLastName = '';
      let email = '';
      let password = '';
      let continent = '';
      let country = '';
      let deliveryAdresses = new FormArray([]);


      
  
    
      name = client.name;
      primaryLastName = client.primaryLastName;
      secondaryLastName = client.secondaryLastName;
      email = client.email;
      password = client.password;
      continent = client.continent;
      country = client.country;
      

      if (client['deliveryAdresses']) {
        for (let adress of client.deliveryAdresses) {
          deliveryAdresses.push(
            new FormGroup({
              adress: new FormControl(adress, Validators.required)
            })
          );
        }
      }
  
  
      this.clientForm = new FormGroup({
        name: new FormControl(name, Validators.required),
        primaryLastName: new FormControl(primaryLastName,Validators.required),
        secondaryLastName: new FormControl(secondaryLastName, Validators.required),
        email: new FormControl(email),
        password: new FormControl(password, Validators.required),
        continent: new FormControl(continent, Validators.required),
        country: new FormControl(country, Validators.required),
        deliveryAdresses: deliveryAdresses

      });
    }

}

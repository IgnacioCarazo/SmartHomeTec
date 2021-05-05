import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from './client.service';

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {

  clientForm: FormGroup;


  constructor(private router: Router,
    private clientService: ClientService) { }


  onSubmit() {      
    console.log(this.clientForm.value);

    this.clientForm.reset();
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
      let name = '';
      let primaryLastName = '';
      let secondaryLastName = '';
      let email = '';
      let password = '';
      let continent = '';
      let country = '';
      let deliveryAdresses = [];
  
    
      const client = this.clientService.getClient();
      console.log(client);
      name = client.name;
      primaryLastName = client.primaryLastName;
      secondaryLastName = client.secondaryLastName;
      email = client.email;
      password = client.password;
      continent = client.continent;
      country = client.country;
      deliveryAdresses = client.deliveryAdresses;
  
  
  
  
  
  
      this.clientForm = new FormGroup({
        name: new FormControl(name, Validators.required),
        primaryLastName: new FormControl(primaryLastName,Validators.required),
        secondaryLastName: new FormControl(secondaryLastName,),
        password: new FormControl(password, Validators.required),
        continent: new FormControl(continent, Validators.required),
        country: new FormControl(country, Validators.required),
        
      });
    }

}

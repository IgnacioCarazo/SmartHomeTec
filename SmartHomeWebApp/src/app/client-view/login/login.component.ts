import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ClientService } from '../client-profile/client.service';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { Client } from 'src/app/models/client.model';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    isAdmin: boolean;
    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    email: string;
    password: string;
    client: Client;

    constructor(private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private clientService: ClientService,
        private dataStorageService: DataStorageService
    ) {
    }

    ngOnInit() {
    }

    

    onSubmit(form: NgForm) {  
    this.dataStorageService.sendLoginInfoClient(form.value.email,form.value.password).
            subscribe( client => {
                this.client = client;
                if (this.client.email !== "") {
                  this.clientService.setClient(this.client);
                  this.clientService.login = true; 
                  this.router.navigate(['/client/perfil']);

                }  
            });
      form.reset();
    }

}

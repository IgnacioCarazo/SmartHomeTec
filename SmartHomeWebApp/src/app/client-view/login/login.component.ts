import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ClientService } from '../client-profile/client.service';
import { DataStorageService } from 'src/app/shared/data-storage.service';
import { Client } from 'src/app/models/client.model';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [DatePipe]
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
    myDate = new Date();


    constructor(private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private datePipe: DatePipe,
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
                if (this.client.name !== "") {
                  this.clientService.setClient(this.client);
                  this.clientService.login = true; 
                  this.router.navigate(['/client/perfil']);

                }  
            });

      const hour = this.datePipe.transform(this.myDate, 'HH:mm:ss')
      const date = this.datePipe.transform(this.myDate, 'dd/MM/yyyy');
      form.reset();
    }

}

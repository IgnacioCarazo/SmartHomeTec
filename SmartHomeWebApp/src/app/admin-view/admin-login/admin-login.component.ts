import { Component, OnInit } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from 'src/app/client-view/client-profile/client.service';
import { Admin } from 'src/app/models/admin.model';
import { DataStorageService } from 'src/app/shared/data-storage.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {
  isAdmin: boolean;
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  email: string;
  password: string;
  admin: Admin;

  constructor(private router: Router,
              private clientService: ClientService,
              private dataStorageService: DataStorageService
  ) {
   
  }

  ngOnInit() {
  }

  

  onSubmit(form: NgForm) {     
    this.dataStorageService.sendLoginInfoAdmin(form.value.email,form.value.password).
            subscribe( admin => {
                this.admin = admin;
                if (this.admin.email !== "") {
                  this.clientService.login = true; 

                }     

            });
    this.clientService.login = true;               
    
    this.router.navigate(['/admin/dashboard']);
    form.reset();
  }
}
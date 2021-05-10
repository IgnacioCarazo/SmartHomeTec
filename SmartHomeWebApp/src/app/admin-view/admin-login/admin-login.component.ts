import { Component, OnInit } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from 'src/app/client-view/client-profile/client.service';

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

  constructor(private router: Router,
              private clientService: ClientService
  ) {
   
  }

  ngOnInit() {
  }

  

  onSubmit(form: NgForm) {     
    this.clientService.login = true; 
    console.log(form.value.email, form.value.password)
    this.router.navigate(['/admin/dashboard']);
    form.reset();
  }
}
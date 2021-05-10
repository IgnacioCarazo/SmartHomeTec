import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from '../client-profile/client.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  constructor(private router: Router,
              private clientService: ClientService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {      
    console.log(form.value);
    this.router.navigate(['/client/login']);

    form.reset();
  }

 
  

}

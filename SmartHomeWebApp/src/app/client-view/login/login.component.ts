import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';


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

    constructor(private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
    ) {
    }

    ngOnInit() {
    }

    

    onSubmit(form: NgForm) {      
      console.log(form.value.email, form.value.password)
      this.router.navigate(['/client/perfil']);
      form.reset();
    }

}

import { Component, OnChanges, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../client-view/client-profile/client.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})

export class HeaderComponent implements OnInit {
    isAdmin = false;
    
    email: string;
    password: string;
    
    /**
    @constructor
    */
    constructor(private router: Router,
                private clientService: ClientService){
    }

    ngOnInit() {
    }

    /**
    * @name changeView()
    * @description When the user changes the page's view with the respective button this method is called and changes 
    * the value of this.isAdmin' which is true when in Admin View and false when in Chef View.
    */
    changeView() {
        if (this.isAdmin) {
            this.router.navigate(['/client/login']);
        } else {
            this.router.navigate(['/admin/login']);
        }
        this.isAdmin = !this.isAdmin;
    }

    /**
    * @name logout() 
    * @description If the user is logged in any view and logs out with the respective button to do so this method will 
    * be called. It changes the valie of 'login' to false which indicates there's no one logged in and changes the
    * web link to '/login'.
    */
    logout() {
        this.clientService.login = false;    

        if (this.isAdmin) {
            this.router.navigate(['/admin/login']);
        } else {
            this.router.navigate(['/client/login']);
        }
    }

    /**
    * @name onSubmit() 
    * @argument {NgForm} form - A form argument which is filled with the user's email and password
    * @description When the button to login is clicked this method is called and it sets the actual user in the page which
    * is returned by the backend. 
    */
    onSubmit(form: NgForm) {
        this.email = form.value.email;
        this.password = form.value.password;        
        
        form.reset();

        

      }

   

}
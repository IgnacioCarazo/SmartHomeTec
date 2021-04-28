import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './client-view/login/login.component';
import { AlertComponent } from './alert/alert.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DropdownDirective } from './shared/dropdown.directive';
import { AdminLoginComponent } from './admin-view/admin-login/admin-login.component';
import { DashboardComponent } from './admin-view/dashboard/dashboard.component';
import { DevicesComponent } from './admin-view/devices/devices.component';
import { StoreComponent } from './admin-view/store/store.component';
import { DistributorComponent } from './admin-view/distributor/distributor.component';
import { DeviceTypesComponent } from './admin-view/device-types/device-types.component';
import { RegisterComponent } from './client-view/register/register.component';
import { SmartHomeComponent } from './client-view/smart-home/smart-home.component';
import { ClientDevicesComponent } from './client-view/client-devices/client-devices.component';
import { ClientProfileComponent } from './client-view/client-profile/client-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    AlertComponent,
    DropdownDirective,
    AdminLoginComponent,
    DashboardComponent,
    DevicesComponent,
    DeviceTypesComponent,
    DistributorComponent,
    StoreComponent,
    SmartHomeComponent,
    ClientDevicesComponent,
    ClientProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

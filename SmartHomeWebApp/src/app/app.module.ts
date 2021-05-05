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
import { ClientProfileComponent } from './client-view/client-profile/client-profile.component';
import { ClientService } from './client-view/client-profile/client.service';
import { OnlineStoreComponent } from './client-view/online-store/online-store.component';
import { ClientReportsComponent } from './client-view/client-reports/client-reports.component';
import { DeviceEditComponent } from './admin-view/devices/device-edit/device-edit.component';
import { DeviceListComponent } from './admin-view/devices/device-list/device-list.component';
import { DeviceItemComponent } from './admin-view/devices/device-list/device-item/device-item.component';
import { DeviceDetailComponent } from './admin-view/devices/device-detail/device-detail.component';
import { DeviceTypeDetailComponent } from './admin-view/device-types/device-type-detail/device-type-detail.component';
import { DeviceTypeEditComponent } from './admin-view/device-types/device-type-edit/device-type-edit.component';
import { DeviceTypeListComponent } from './admin-view/device-types/device-type-list/device-type-list.component';
import { DeviceTypeItemComponent } from './admin-view/device-types/device-type-list/device-type-item/device-type-item.component';

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
    ClientProfileComponent,
    OnlineStoreComponent,
    ClientReportsComponent,
    DeviceEditComponent,
    DeviceListComponent,
    DeviceItemComponent,
    DeviceDetailComponent,
    DeviceTypeDetailComponent,
    DeviceTypeEditComponent,
    DeviceTypeListComponent,
    DeviceTypeItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }

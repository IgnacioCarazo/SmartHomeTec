import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin-view/admin-login/admin-login.component';
import { DashboardComponent } from './admin-view/dashboard/dashboard.component';
import { DeviceTypesComponent } from './admin-view/device-types/device-types.component';
import { DevicesComponent } from './admin-view/devices/devices.component';
import { DistributorComponent } from './admin-view/distributor/distributor.component';
import { StoreComponent } from './admin-view/store/store.component';
import { ClientDevicesComponent } from './client-view/client-devices/client-devices.component';
import { ClientProfileComponent } from './client-view/client-profile/client-profile.component';
import { LoginComponent } from './client-view/login/login.component';
import { RegisterComponent } from './client-view/register/register.component';
import { SmartHomeComponent } from './client-view/smart-home/smart-home.component';

const appRoutes: Routes = [ 
  { path: '', redirectTo: '/client/login', pathMatch: 'full' },
  // client paths
  { path: 'client/login', component: LoginComponent },
  { path: 'client/register', component: RegisterComponent }, 
  { path: 'client/gestion-dispositivos', component: ClientDevicesComponent }, 
  { path: 'client/smart-home', component: SmartHomeComponent }, 
  { path: 'client/perfil', component: ClientProfileComponent }, 


  
  //admin paths
  { path: 'admin/login', component: AdminLoginComponent },
  { path: 'admin/dashboard', component: DashboardComponent },
  { path: 'admin/gestion-dispositivos', component: DevicesComponent },
  { path: 'admin/gestion-tipo-dispositivos', component: DeviceTypesComponent },
  { path: 'admin/gestion-distribuidores', component: DistributorComponent },
  { path: 'admin/tienda', component: StoreComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

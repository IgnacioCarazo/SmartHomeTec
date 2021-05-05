import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin-view/admin-login/admin-login.component';
import { DashboardComponent } from './admin-view/dashboard/dashboard.component';
import { DeviceTypesComponent } from './admin-view/device-types/device-types.component';
import { DevicesComponent } from './admin-view/devices/devices.component';
import { DistributorComponent } from './admin-view/distributor/distributor.component';
import { StoreComponent } from './admin-view/store/store.component';
import { ClientProfileComponent } from './client-view/client-profile/client-profile.component';
import { ClientReportsComponent } from './client-view/client-reports/client-reports.component';
import { LoginComponent } from './client-view/login/login.component';
import { OnlineStoreComponent } from './client-view/online-store/online-store.component';
import { RegisterComponent } from './client-view/register/register.component';

const appRoutes: Routes = [ 
  { path: '', redirectTo: '/client/login', pathMatch: 'full' },


  // client paths
  { path: 'client/login', component: LoginComponent },
  { path: 'client/register', component: RegisterComponent }, 
  { path: 'client/tienda-en-linea', component: OnlineStoreComponent }, 
  { path: 'client/reportes-cliente', component: ClientReportsComponent }, 
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

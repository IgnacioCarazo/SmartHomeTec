import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin-view/admin-login/admin-login.component';
import { DashboardComponent } from './admin-view/dashboard/dashboard.component';
import { DeviceTypeDetailComponent } from './admin-view/device-types/device-type-detail/device-type-detail.component';
import { DeviceTypeEditComponent } from './admin-view/device-types/device-type-edit/device-type-edit.component';
import { DeviceTypesComponent } from './admin-view/device-types/device-types.component';
import { DeviceDetailComponent } from './admin-view/devices/device-detail/device-detail.component';
import { DeviceEditComponent } from './admin-view/devices/device-edit/device-edit.component';
import { DevicesComponent } from './admin-view/devices/devices.component';
import { DistributorDetailComponent } from './admin-view/distributor/distributor-detail/distributor-detail.component';
import { DistributorComponent } from './admin-view/distributor/distributor.component';
import { StoreComponent } from './admin-view/store/store.component';
import { ClientProfileComponent } from './client-view/client-profile/client-profile.component';
import { ClientReportsComponent } from './client-view/client-reports/client-reports.component';
import { LoginComponent } from './client-view/login/login.component';
import { OnlineStoreComponent } from './client-view/online-store/online-store.component';
import { StoreDeviceDetailComponent } from './client-view/online-store/store-device-detail/store-device-detail.component';
import { RegisterComponent } from './client-view/register/register.component';

const appRoutes: Routes = [ 
  { path: '', redirectTo: '/client/login', pathMatch: 'full' },


  // client paths
  { path: 'client/login', component: LoginComponent },
  { path: 'client/register', component: RegisterComponent }, 
  { path: 'client/tienda-en-linea', component: OnlineStoreComponent, children: [
    {
      path: ':id',
      component: StoreDeviceDetailComponent
    }
  ] }, 
  { path: 'client/reportes-cliente', component: ClientReportsComponent }, 
  { path: 'client/perfil', component: ClientProfileComponent }, 


  
  //admin paths
  { path: 'admin/login', component: AdminLoginComponent },
  { path: 'admin/dashboard', component: DashboardComponent },
  { path: 'admin/gestion-dispositivos', component: DevicesComponent, children: [
    {
      path: 'new',
      component: DeviceEditComponent
    },{
    path: ':id',
    component: DeviceDetailComponent
  },{
    path: ':id/edit',
    component: DeviceEditComponent
  },
]},
  { path: 'admin/gestion-tipo-dispositivos', component: DeviceTypesComponent, children: [
    {
      path: 'new',
      component: DeviceTypeEditComponent
    },{
    path: ':id',
    component: DeviceTypeDetailComponent
  },{
    path: ':id/edit',
    component: DeviceTypeEditComponent
  },
]},
  { path: 'admin/gestion-distribuidores', component: DistributorComponent, children: [
    {
      path: ':id',
      component: DistributorDetailComponent
    }
  ] },
  { path: 'admin/tienda', component: StoreComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

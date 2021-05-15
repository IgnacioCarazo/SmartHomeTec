import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin-view/admin-login/admin-login.component';
import { DashboardComponent } from './admin-view/dashboard/dashboard.component';
import { DeviceTypeDetailComponent } from './admin-view/device-types/device-type-detail/device-type-detail.component';
import { DeviceTypeEditComponent } from './admin-view/device-types/device-type-edit/device-type-edit.component';
import { DeviceTypeResolverService } from './admin-view/device-types/device-type-resolver.service';
import { DeviceTypesComponent } from './admin-view/device-types/device-types.component';
import { DeviceDetailComponent } from './admin-view/devices/device-detail/device-detail.component';
import { DeviceEditComponent } from './admin-view/devices/device-edit/device-edit.component';
import { DeviceResolverService } from './admin-view/devices/devices-resolver.service';
import { DevicesComponent } from './admin-view/devices/devices.component';
import { DistributorDetailComponent } from './admin-view/distributor/distributor-detail/distributor-detail.component';
import { DistributorResolverService } from './admin-view/distributor/distributor-resolver.service';
import { DistributorComponent } from './admin-view/distributor/distributor.component';
import { DistributorService } from './admin-view/distributor/distributor.service';
import { StoreComponent } from './admin-view/store/store.component';
import { ClientProfileComponent } from './client-view/client-profile/client-profile.component';
import { ClientReportsComponent } from './client-view/client-reports/client-reports.component';
import { LoginComponent } from './client-view/login/login.component';
import { OnlineStoreComponent } from './client-view/online-store/online-store.component';
import { StoreDeviceDetailComponent } from './client-view/online-store/store-device-detail/store-device-detail.component';
import { RegisterComponent } from './client-view/register/register.component';
import { AvailableRegionsComponent } from './shared/available-regions/available-regions.component';

const appRoutes: Routes = [ 
  { path: '', redirectTo: '/client/login', pathMatch: 'full' },


  // client paths
  { path: 'client/login', component: LoginComponent },
  { path: 'client/register', component: RegisterComponent, children :[  
    { path: '', component: AvailableRegionsComponent },
   ] }, 
  { path: 'client/tienda-en-linea', component: OnlineStoreComponent,
  resolve: [DeviceResolverService, DistributorResolverService], children: [
    {
      path: ':id',
      component: StoreDeviceDetailComponent
    }
  ] }, 
  { path: 'client/reportes-cliente', component: ClientReportsComponent }, 
  { path: 'client/perfil', component: ClientProfileComponent }, 


  
  //admin paths
  { path: 'admin/login', component: AdminLoginComponent },
  { path: 'admin/dashboard', component: DashboardComponent ,
  resolve: [DeviceResolverService]},
  { path: 'admin/gestion-dispositivos', component: DevicesComponent,
  resolve: [DeviceResolverService, DistributorResolverService],
   children: [
    {
      path: 'new',
      component: DeviceEditComponent,
      resolve: [DeviceResolverService]
    },{
    path: ':id',
    component: DeviceDetailComponent,
    resolve: [DeviceResolverService]
  },{
    path: ':id/edit',
    component: DeviceEditComponent,
    resolve: [DeviceResolverService]
  },
]},
  { path: 'admin/gestion-tipo-dispositivos', component: DeviceTypesComponent,
  resolve: [DeviceTypeResolverService],
  children: [
    {
      path: 'new',
      component: DeviceTypeEditComponent,
      resolve: [DeviceTypeResolverService]
    },{
    path: ':id',
    component: DeviceTypeDetailComponent,
    resolve: [DeviceTypeResolverService]
  },{
    path: ':id/edit',
    component: DeviceTypeEditComponent,
    resolve: [DeviceTypeResolverService]
  },
]},
  { path: 'admin/gestion-distribuidores', component: DistributorComponent,
  resolve: [DistributorResolverService], 
  children: [
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

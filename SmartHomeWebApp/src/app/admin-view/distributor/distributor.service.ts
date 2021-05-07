import { Injectable } from '@angular/core';
import { Client } from 'src/app/models/client.model';
import { DeviceType } from 'src/app/models/device-type.model';
import { Device } from 'src/app/models/device.model';
import { Distributor } from 'src/app/models/distributor.model';

@Injectable()
export class DistributorService {
 
  private distributors: Distributor[] = [
      new Distributor("Juan", "Segura", "Cortez", 83726374, "Europa", "España",[new Device("Iphone 12", 534634631, "450mW", "Apple",true, new DeviceType("SmartPhone","esta es la descripcion"),"Ignacio Carazo"),
      new Device("Mate 20", 3466793, "400mW", "Huawei",false, new DeviceType("SmartPhone","esta es la descripcion"), "")]),
      new Distributor("Marcos", "Guevara", "Viales", 37263739, "America", "Canada",[new Device("Iphone X", 23466434, "600mW", "Apple",false, new DeviceType("SmartPhone","esta es la descripcion"), ""),
      new Device("Macbook Pro", 25364715, "1100mW", "Apple",true, new DeviceType("Laptop","esta es la descripcion"), "Joseph Jimenez")]),
      new Distributor("Felipe", "Mora", "Quiros", 71653621, "America", "Colombia",[new Device("Refrigerador", 908635246, "46W", "Sony",false, new DeviceType("Electrodomestico","esta es la descripcion"), "")]),
      new Distributor("Ignacio", "Carazo", "Nieto", 8886253, "America", "Costa Rica",[new Device("Refrigerador", 908635246, "46W", "Sony",false, new DeviceType("Electrodomestico","esta es la descripcion"), ""),
      new Device("Refrigerador", 342341212, "46W", "Sony",false, new DeviceType("Electrodomestico","esta es la descripcion"), "")]),
      new Distributor("Haziel", "Gudino", "Rovira", 2325462, "Asia", "Japon",[new Device("Mate 20", 3466793, "400mW", "Huawei",false, new DeviceType("SmartPhone","esta es la descripcion"), ""),
      new Device("Iphone X", 23466434, "600mW", "Apple",false, new DeviceType("SmartPhone","esta es la descripcion"), "")]),
      new Distributor("Mario", "Hernandez", "Mena", 23451346, "America", "Costa Rica",[new Device("Iphone 12", 534634631, "450mW", "Apple",false, new DeviceType("SmartPhone","esta es la descripcion"),"")]),
      new Distributor("Manrique", "Jimenez", "De la Paz", 23543245, "America", "Costa Rica",[new Device("Macbook Pro", 25364715, "1100mW", "Apple",false, new DeviceType("Laptop","esta es la descripcion"), ""),
      new Device("Refrigerador", 908635246, "46W", "Sony",false, new DeviceType("Electrodomestico","esta es la descripcion"), "")])];
  
    private distributorsByClientRegion: Distributor[] = [];

  constructor() {

  }
  /**
  * @name getDistributors()
  * @returns The array of distributors of this service.  
  */
  getDistributors() {
    return this.distributors.slice();
  }

  /**
  * @name getDistributor()
  * @description It searches a distributor by its index
  * @returns {Distributor} A distributor
  */
   getDistributor(index: number) {
    return this.distributors[index];
  }

  getDistributorsByRegion(client: Client) {
      for (let distributor of this.distributors) {
          if (distributor.continent === client.continent) {
              if (distributor.country === client.country) {
                  this.distributorsByClientRegion.push(distributor)
              }
          }
      }
      return this.distributorsByClientRegion;
  }

}
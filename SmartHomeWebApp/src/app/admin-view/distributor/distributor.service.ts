import { Injectable } from '@angular/core';
import { Client } from 'src/app/models/client.model';
import { DeviceType } from 'src/app/models/device-type.model';
import { Device } from 'src/app/models/device.model';
import { Distributor } from 'src/app/models/distributor.model';

@Injectable()
export class DistributorService {
 
  private distributors: Distributor[] = [
      new Distributor("Importadora Monge", 83726374, "Europa", "España"),
      new Distributor("Gollo",37263739, "America", "Canada"),
      new Distributor("La Guacamaya",71653621, "America", "Colombia"),
      new Distributor("Wallmart", 2325462, "Asia", "Japon"),
      new Distributor("Target", 23451346, "America", "Costa Rica"),
      new Distributor("Casa Blanca", 23543245, "America", "Costa Rica")];
  
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

  isWithinRegion(dni: number) {
    for (let distributor of this.distributorsByClientRegion) {
      if (distributor.dni === dni) {
        return true;
      }
    }
    return false;
  }

  setDistributorsByRegion(client: Client) {
    const list = [];
    for (let distributor of this.distributors) {
      if (distributor.continent === client.continent) {
          if (distributor.country === client.country) {
              list.push(distributor)
          }
      }
    }
    this.distributorsByClientRegion = list;
    
  }

  getDistributorsByRegion() {
    return this.distributorsByClientRegion;
  }
  

}
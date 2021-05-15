import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Client } from 'src/app/models/client.model';
import { DeviceType } from 'src/app/models/device-type.model';
import { Device } from 'src/app/models/device.model';
import { Distributor } from 'src/app/models/distributor.model';

@Injectable()
export class DistributorService {
 
  private distributors: Distributor[] = [];
  
    private distributorsByClientRegion: Distributor[] = [];
    distributorsChanged = new Subject<Distributor[]>();


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

  /**
  * @name isWithinRegion()
  * @description It searches a distributor by its dni to check if its in this client region
  * @returns {boolean} true or false
  */
  isWithinRegion(dni: number) {
    for (let distributor of this.distributorsByClientRegion) {
      if (distributor.dni === dni) {
        return true;
      }
    }
    return false;
  }

  /**
  * @name setDistributor()
  * @description sets the distributors
  */
  setDistributors(distributors: Distributor[]) {
    this.distributors = distributors;
  }

  /**
  * @name   setDistributorsByRegion()
  * @description sets the distributors by region
  */
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

  /**
  * @name getDistributorsByRegion()
  * @description It returns the distributors by client region array
  * @returns {Distributor[]} A distributor array
  */
  getDistributorsByRegion() {
    return this.distributorsByClientRegion;
  }
  

}
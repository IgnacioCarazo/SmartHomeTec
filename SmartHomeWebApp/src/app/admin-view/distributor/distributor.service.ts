import { Injectable } from '@angular/core';
import { Distributor } from 'src/app/models/distributor.model';

@Injectable()
export class DistributorService {
 
  private distributors: Distributor[] = [
      new Distributor("Juan", "Segura", "Cortez", 83726374, "Europa", "España"),
      new Distributor("Marcos", "Guevara", "Viales", 37263739, "America", "Canada"),
      new Distributor("Felipe", "Mora", "Quiros", 71653621, "America", "Colombia"),
      new Distributor("Ignacio", "Carazo", "Nieto", 8886253, "America", "Costa Rica"),
      new Distributor("Haziel", "Gudino", "Rovira", 2325462, "Asia", "Japon"),
      new Distributor("Mario", "Hernandez", "Mena", 23451346, "America", "Costa Rica"),
      new Distributor("Manrique", "Jimenez", "De la Paz", 23543245, "America", "Costa Rica")];
  


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


}
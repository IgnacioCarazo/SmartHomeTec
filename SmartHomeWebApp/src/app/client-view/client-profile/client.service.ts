import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

import { Client } from 'src/app/models/client.model';

@Injectable()
export class ClientService {
  client: Client;
  login: boolean;

  constructor() {

  }

  /**
  * @name setClient()
  * @argument {Client} client
  * @description  Sets the actual client connected
  */
  setClient(client: Client) {
  }

  


  /**
  * @name getClient
  * @description returns the actual client
  * @returns {Cliente} A client
  */
  getClient() {
     this.client = new Client("Ignacio", "Carazo", "Nieto", "nachocarazo18@gmail.com", "nacho123", "America", "Costa Rica", ["1","2","3"]);

    return this.client;
  }


  /**
  * @name updateRecipe()
  * @argument {number} index
  * @argument {Recipe} newRecipe
  * @argument {string} recipeTypeName
  * @description  It updates the value of a recipe of this service recipes array. 
  */
  updateRecipe(client: Client) {
      this.client = client;
  }

  /**
  * @name deleteClient
  * @description deletes the actual client
  */
  deleteClient() {
    
  }
}
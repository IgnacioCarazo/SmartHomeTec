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
    this.client = client;

  }

  /**
  * @name getClient
  * @description returns the actual client
  * @returns {Cliente} A client
  */
  getClient() {
    return this.client;
  }


  /**
  * @name updateClient()
  * @argument {number} index
  * @argument {Client} client
  * @description  It updates the value of the client of this service. 
  */
  updateClient(client: Client) {
      this.client = client;
  }

  /**
  * @name deleteClient
  * @description deletes the actual client
  */
  deleteClient() {
    
  }
}
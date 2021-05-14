import { Injectable } from "@angular/core";

@Injectable()
export class DashboardService {
  private _dispRegiones = [];
    
  private _averageDevices: number;
    
  



  constructor() {

  }

    getDispRegiones() {
        return this._dispRegiones;
    }
    setDispRegiones(value) {
        for (let region of value) {
            let lista = [];
            lista.push(region.Region);
            lista.push(region.Cantidad);
            this._dispRegiones.push(lista);
        }
    }

    getAverageDevices(): number {
        return this._averageDevices;
    }
    setAverageDevices(value: number) {
        this._averageDevices = value;
    }
}
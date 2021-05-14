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
        console.log(value);
        let dispRegiones = [];
        for (let region of value) {
            let lista = [];
            lista.push(region.Region);
            lista.push(region.Cantidad);
            dispRegiones.push(lista);
        }
        console.log(dispRegiones)
        this._dispRegiones = dispRegiones;
    }

    getAverageDevices(): number {
        return this._averageDevices;
    }
    setAverageDevices(value: number) {
        this._averageDevices = value;
    }
}
import { Injectable } from "@angular/core";

@Injectable()
export class DashboardService {
  private _dispRegiones = [];
    
  private _averageDevices: number;

  private activos: number;
    
  



  constructor() {

  }

    getDispRegiones() {
        return this._dispRegiones;
    }
    setDispRegiones(value) {
        let dispRegiones = [];
        for (let region of value) {
            let lista = [];
            lista.push(region.Region);
            lista.push(region.Cantidad);
            dispRegiones.push(lista);
        }
        this._dispRegiones = dispRegiones;
    }

    getAverageDevices(): number {
        return this._averageDevices;
    }
    setAverageDevices(value: number) {
        this._averageDevices = value;
    }
    getActivos(): number {
        return this.activos;
    }
    setActivos(value: number) {
        this.activos = value;
    }
}
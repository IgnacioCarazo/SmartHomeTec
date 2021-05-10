import { Device } from "./device.model";

export class Distributor {
    public dni: number;
    public name: string;
    public continent: string;
    public country: string;

    constructor(name: string, dni: number, continent: string, country: string) {
        this.name = name;
        this.dni = dni;
        this.continent = continent;
        this.country = country;
    }

    
}
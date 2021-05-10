import { DeviceType } from "./device-type.model";
import { Distributor } from "./distributor.model";

export class Device {

    public name: string;
    public serialNumber: number;
    public eConsumption: string;
    public brand: string;
    public associated: boolean;
    public typeName: string;
    public ownerEmail: string;
    public dniDistributor: number;
    public price: number;
    public roomName: string;
    

    constructor(name: string, serialNumber: number, eConsumption: string, brand: string, associated: boolean, typeName: string, ownerEmail: string, dniDistributor: number, price: number) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.eConsumption = eConsumption;
        this.brand = brand;
        this.typeName = typeName;
        this.associated = associated;
        this.ownerEmail = ownerEmail
        this.dniDistributor = dniDistributor;
        this.price = price;
    }

}
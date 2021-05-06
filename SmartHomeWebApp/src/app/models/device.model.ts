import { DeviceType } from "./device-type.model";

export class Device {

    public name: string;
    public serialNumber: number;
    public eConsumption: string;
    public brand: string;
    public associated: boolean;
    public type: DeviceType;
    public ownerName: string;
    

    constructor(name: string, serialNumber: number, eConsumption: string, brand: string, associated: boolean, type: DeviceType, ownerName: string) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.eConsumption = eConsumption;
        this.brand = brand;
        this.type = type;
        this.associated = associated;
        this.ownerName = ownerName
    }

}
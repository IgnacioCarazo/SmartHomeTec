import { DeviceType } from "./device-type.model";

export class Device {

    public name: string;
    public serialNumber: number;
    public eConsumption: string;
    public brand: string;
    public associated: boolean;
    public type: DeviceType;
    public ownerName: string;
    

}
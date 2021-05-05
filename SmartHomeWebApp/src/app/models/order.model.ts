import { Device } from "./device.model";
import { Warranty } from "./warranty.model";

export class Order {

    consecutiveNumberOrder: number;
    device: Device;
    warranty: Warranty;
    date: string;
    price: number;
    
}
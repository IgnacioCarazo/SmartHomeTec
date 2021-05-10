import { Device } from "./device.model";
import { Warranty } from "./warranty.model";

export class Order {

    consecutiveNumberOrder: number;
    deviceSerialNumber: number;
    date: string;
    hour: string;
    brand: string;
    price: number;
    deviceOwner: string;
    orderID: number;

    constructor(deviceSerialNumber: number, date: string, price: number, deviceOwner: string) {
        this.consecutiveNumberOrder = 0;
        this.deviceSerialNumber = deviceSerialNumber;
        this.date = date;
        this.price = price;
        this.deviceOwner = deviceOwner;
    }
    
}
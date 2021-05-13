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

    constructor(deviceSerialNumber: number, date: string, hour: string, price: number, deviceOwner: string, brand: string) {
        this.consecutiveNumberOrder = 0;
        this.deviceSerialNumber = deviceSerialNumber;
        this.date = date;
        this.hour = hour;
        this.price = price;
        this.deviceOwner = deviceOwner;
        this.brand = brand;
    }
    
}
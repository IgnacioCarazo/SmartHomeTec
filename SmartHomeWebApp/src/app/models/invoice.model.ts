import { DeviceType } from "./device-type.model";
import { Device } from "./device.model";

export class Invoice {

    invoiceNumber: number;
    deviceSerialNumber: number;
    deviceTypeName: string;
    price: number;
    date: string;

}
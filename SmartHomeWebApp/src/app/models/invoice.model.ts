import { DeviceType } from "./device-type.model";
import { Device } from "./device.model";

export class Invoice {

    invoiceNumber: number;
    device: Device;
    deviceType: DeviceType;
    price: number;
    date: string;

}
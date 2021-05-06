export class DeviceType {

    public name: string;
    public description: string;
    public warrantyTime: number;

    constructor(name: string, description: string) {
        this.name = name;
        this.description = description;
        this.warrantyTime = 5;
 }
}
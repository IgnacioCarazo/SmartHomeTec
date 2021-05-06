export class Distributor {
    public dni: number;
    public name: string;
    public primaryLastName: string;
    public secondaryLastName: string;
    public continent: string;
    public country: string;

    constructor(name: string, primaryLastName: string, secondaryLastName: string, dni: number, continent: string, country: string) {
        this.name = name;
        this.primaryLastName = primaryLastName;
        this.secondaryLastName = secondaryLastName;
        this.dni = dni;
        this.continent = continent;
        this.country = country;
    }

    
}
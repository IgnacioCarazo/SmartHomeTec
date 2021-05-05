export class Client {

    public name: string;
    public primaryLastName: string;
    public secondaryLastName: string;
    public email: string;
    public password: string;
    public continent: string;
    public country: string;
    public deliveryAdresses: string[];
  
      constructor(name: string, primaryLastName: string, secondaryLastName: string,
                  email: string, password: string, continent: string, country: string, deliveryAdresses: string[]) {
          this.name = name;
          this.primaryLastName = primaryLastName;
          this.secondaryLastName = secondaryLastName;
          this.email = email;
          this.password = password;
          this.continent = continent;
          this.country = country;
          this.deliveryAdresses = deliveryAdresses;
     }
    }
    
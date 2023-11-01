import {Car} from "./car";

export class User {
  id : number;
  name : string;
  cars : Car [];

  constructor(id: number, name: string, cars : Car []) {
    this.id = id;
    this.name = name;
    this.cars = cars;
  }
}

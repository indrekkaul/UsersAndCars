
export class Car {
  id: number;
  make: string;
  model: string;
  numberplate: string;

  constructor(id: number, make: string, model: string, numberplate: string) {
    this.id = id;
    this.make = make;
    this.model = model;
    this.numberplate = numberplate;
  }
}

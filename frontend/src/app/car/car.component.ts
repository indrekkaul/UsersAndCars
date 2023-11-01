import { Component, OnInit } from '@angular/core';
import {CarService} from "../car.service";
import {Car} from "../car";

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {

  cars : Car[] | undefined;

  searchKeyword = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  constructor(private carService : CarService) {
  }

  filterCars(): void {
    this.carService.getCars().subscribe(data => {
      this.cars = data.filter(car =>
        car.make.toLowerCase().includes(this.searchKeyword.toLowerCase())
      );
    });
  }

  toggleSortDirection(): void {
    this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    this.sortCars();
  }

  sortCars(): void {
    if (this.sortDirection === 'asc') {
      // @ts-ignore
      this.cars.sort((a,b) => (a.make > b.make ? -1 : 1));
    } else {
      // @ts-ignore
      this.cars.sort((a,b) => (a.make < b.make ? -1 : 1));
    }
  }

  ngOnInit(): void {
    this.carService.getCars().subscribe((data : Car[])=> {
      console.log(data);
      this.cars = data;
    })
  }

}

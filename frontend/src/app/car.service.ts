import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Car} from "./car";

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private baseURL = "http://localhost:8080/cars"

  constructor(private http:  HttpClient) { }

  getCars(): Observable<Car[]>{
    return this.http.get<Car[]>(`${this.baseURL}`);
  }
}

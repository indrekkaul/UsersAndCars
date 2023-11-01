package com.home.user.controllers;


import com.home.user.model.Car;
import com.home.user.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    private final CarService carService;

    /**
     * GET: <code>/cars</code>
     * @return all cars
     *
     */
    @RequestMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> cars = carService.getAllCars();
        if (cars.isEmpty()){
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars,HttpStatus.OK);
    }

    /**
     * GET: <code>/cars</code>
     * @return car by id
     *
     */
    @RequestMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") int id){
        Car car = carService.getCarById(id);
        if (Objects.isNull(car)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(car,HttpStatus.OK);
    }
}

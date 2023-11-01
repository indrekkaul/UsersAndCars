package com.home.user.services;

import com.home.user.model.Car;
import com.home.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CarService {

    private final ReadDataFromFile readDataFromFile;

    public List<Car> getAllCars (){
        List<User> allUsersWithCars = readDataFromFile.getUserData();
        List<Car> allCarList = new ArrayList<>();
        for (User user : allUsersWithCars){
            allCarList.addAll(user.getCars());
        }
        return allCarList;
    }

    public Car getCarById(int id){
        List<Car> allCarList = getAllCars();
        for (Car car : allCarList){
            if(Objects.equals(car.getId(), id)){
                return car;
            }
        }
        return null;
    }

}

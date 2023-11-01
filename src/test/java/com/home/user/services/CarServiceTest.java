package com.home.user.services;

import com.home.user.model.Car;
import com.home.user.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CarServiceTest {

    private CarService carService;
    private ReadDataFromFile readDataFromFile;

    @BeforeEach
    void setUp() {
        readDataFromFile = Mockito.mock(ReadDataFromFile.class);
        carService = new CarService(readDataFromFile);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAllCarsWhenCalledThenReturnCorrectNumberOfCars() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setCars(Arrays.asList(new Car(), new Car()));
        User user2 = new User();
        user2.setCars(Arrays.asList(new Car()));
        users.add(user1);
        users.add(user2);

        when(readDataFromFile.getUserData()).thenReturn(users);

        List<Car> cars = carService.getAllCars();

        assertEquals(3, cars.size());
    }

    @Test
    void testGetAllCarsWhenReadDataIsEmptyThenReturnEmptyList() {
        when(readDataFromFile.getUserData()).thenReturn(new ArrayList<>());

        List<Car> cars = carService.getAllCars();

        assertTrue(cars.isEmpty());
    }

    @Test
    void getCarById() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        Car car1 = new Car();
        car1.setId(1);
        Car car2 = new Car();
        car2.setId(2);
        user1.setCars(Arrays.asList(car1, car2));
        users.add(user1);

        when(readDataFromFile.getUserData()).thenReturn(users);

        Car car = carService.getCarById(1);

        assertEquals(1, car.getId());
    }

    @Test
    void testGetAllCarsWhenNoCarsThenReturnEmptyList() {
        when(readDataFromFile.getUserData()).thenReturn(new ArrayList<>());

        List<Car> cars = carService.getAllCars();

        assertTrue(cars.isEmpty());
    }

    @Test
    void testGetAllCarsWhenNoCarsInUsersThenReturnEmptyList() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setCars(new ArrayList<>());
        users.add(user);
        when(readDataFromFile.getUserData()).thenReturn(users);

        List<Car> cars = carService.getAllCars();

        assertTrue(cars.isEmpty());
    }
}
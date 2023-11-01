package com.home.user.controllers;

import com.home.user.model.Car;
import com.home.user.services.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    void testGetAllCarsWhenCarServiceReturnsNonEmptyListThenReturnListOfCarsWithStatusOk() throws Exception {
        Car car1 = new Car();
        car1.setId(1);
        car1.setMake("Toyota");
        car1.setModel("Corolla");
        car1.setNumberplate("ABC-123");

        Car car2 = new Car();
        car2.setId(2);
        car2.setMake("Honda");
        car2.setModel("Civic");
        car2.setNumberplate("XYZ-789");

        List<Car> cars = Arrays.asList(car1, car2);

        when(carService.getAllCars()).thenReturn(cars);

        mockMvc.perform(MockMvcRequestBuilders.get("/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(car1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].make").value(car1.getMake()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value(car1.getModel()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberplate").value(car1.getNumberplate()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(car2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].make").value(car2.getMake()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].model").value(car2.getModel()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].numberplate").value(car2.getNumberplate()));
    }

    @Test
    void testGetAllCarsWhenCarServiceReturnsEmptyListThenReturnNoContent() throws Exception {
        when(carService.getAllCars()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testGetCarByIdWhenCarServiceReturnsCarThenReturnCarWithStatusOk() throws Exception {
        Car car = new Car();
        car.setId(1);
        car.setMake("Toyota");
        car.setModel("Corolla");
        car.setNumberplate("ABC-123");

        when(carService.getCarById(1)).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(car.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.make").value(car.getMake()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value(car.getModel()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberplate").value(car.getNumberplate()));
    }

    @Test
    void testGetCarByIdWhenCarServiceReturnsNullThenReturnNoContent() throws Exception {
        when(carService.getCarById(1)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
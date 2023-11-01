package com.home.user.controllers;

import com.home.user.model.Car;
import com.home.user.model.User;
import com.home.user.services.ReadDataFromFile;
import com.home.user.services.UserService;
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

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ReadDataFromFile readDataFromFile;

    @Test
    public void testGetAllUsersWhenUsersNotEmptyThenReturnUsers() throws Exception {
        User user1 = new User();
        user1.setId(1);
        user1.setName("John");

        User user2 = new User();
        user2.setId(2);
        user2.setName("Jane");

        when(readDataFromFile.getUserData()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(user1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(user1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(user2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(user2.getName()));
    }

    @Test
    public void testGetAllUsersWhenUsersEmptyThenReturnNoContent() throws Exception {
        when(readDataFromFile.getUserData()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetUserByIdWhenUserExistsThenReturnUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("John");

        when(userService.getUserById(1)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()));
    }

    @Test
    public void testGetUserByIdWhenUserDoesNotExistThenReturnNoContent() throws Exception {
        when(userService.getUserById(1)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetUserCarsByUserIdWhenUserExistsThenReturnCars() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("John");
        user.setCars(Arrays.asList(
                new Car(1, "BMW", "X5", "342"),
                new Car(2, "Audi", "A6", "A6")));

        when(userService.getUserById(1)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].make").value("BMW"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value("X5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].make").value("Audi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].model").value("A6"));
    }

    @Test
    public void testGetUserCarsByUserIdWhenUserDoesNotExistThenReturnNoContent() throws Exception {
        when(userService.getUserById(1)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
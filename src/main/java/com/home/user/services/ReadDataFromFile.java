package com.home.user.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.user.model.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadDataFromFile {

    public List<User> getUserData() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> listOfUsers = new ArrayList<>();

        try {
            File jsonFile = new File("src/main/resources/usersAndCars");
            listOfUsers = objectMapper.readValue(jsonFile, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

}

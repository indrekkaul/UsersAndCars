package com.home.user.services;

import com.home.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private final ReadDataFromFile readDataFromFile;

    public User getUserById (int id){
        List<User> users = readDataFromFile.getUserData();
        for (User user : users){
         if (Objects.equals(user.getId(),id)){
            return user;
         }
        }
        return null;
    }
}

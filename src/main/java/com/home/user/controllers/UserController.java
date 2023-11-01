package com.home.user.controllers;

import com.home.user.model.Car;
import com.home.user.model.User;
import com.home.user.services.ReadDataFromFile;
import com.home.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class UserController {

    private final ReadDataFromFile readDataFromFile;

    private final UserService userService;

    /**
     * GET: <code>/users</code>
     * @return all users
     *
     */
    @RequestMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = readDataFromFile.getUserData();
        if (users.isEmpty()){
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    /**
     * GET: <code>/users/id</code>
     * @return user containing id
     *
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
        User user = userService.getUserById(id);

        if (Objects.isNull(user)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    /**
     * GET: <code>/users/id</code>
     * @return user containing id
     *
     */
    @GetMapping("/users/{id}/cars")
    public ResponseEntity<List<Car>> getUserCarsByUserId(@PathVariable("id") int id){
        User user = userService.getUserById(id);

        if (Objects.isNull(user)){
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.getCars(),HttpStatus.OK);
    }
}

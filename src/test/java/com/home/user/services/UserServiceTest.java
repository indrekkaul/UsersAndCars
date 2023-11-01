package com.home.user.services;

import com.home.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private ReadDataFromFile readDataFromFile;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserByIdWhenUserExistsThenReturnUser() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("John");

        User user2 = new User();
        user2.setId(2);
        user2.setName("Jane");

        List<User> users = Arrays.asList(user1, user2);

        when(readDataFromFile.getUserData()).thenReturn(users);

        User result = userService.getUserById(1);

        assertEquals(user1, result);
    }

    @Test
    void testGetUserByIdWhenUserDoesNotExistThenReturnNull() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("John");

        User user2 = new User();
        user2.setId(2);
        user2.setName("Jane");

        List<User> users = Arrays.asList(user1, user2);

        when(readDataFromFile.getUserData()).thenReturn(users);

        User result = userService.getUserById(3);

        assertNull(result);
    }

}
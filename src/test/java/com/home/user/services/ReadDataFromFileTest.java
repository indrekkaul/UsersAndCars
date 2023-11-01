package com.home.user.services;

import com.home.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ReadDataFromFileTest {

    @InjectMocks
    private ReadDataFromFile readDataFromFile;

    private File file;

    @BeforeEach
    public void setUp() {
        file = new File("src/main/resources/usersAndCars");
    }

    @Test
    public void testGetUserData() {
        List<User> users = readDataFromFile.getUserData();

        // Testing the data is loaded correctly
        assertNotNull(users);
        assertEquals(5, users.size()); // User list size should be 5
        assertEquals("Teet Järveküla", users.get(0).getName()); // Check the name of the first user
    }
}
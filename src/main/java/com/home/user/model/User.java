package com.home.user.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    int id;
    String name;
    List<Car> cars;
}

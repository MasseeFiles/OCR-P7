package com.nnk.springboot.services;

import com.nnk.springboot.domain.UserApp;

import java.util.List;

public interface UserService {
    List<UserApp> findAll();

    UserApp findById(Integer id);

    void add(UserApp userApp);

    void update(UserApp userApp);

    void delete(Integer id);
}
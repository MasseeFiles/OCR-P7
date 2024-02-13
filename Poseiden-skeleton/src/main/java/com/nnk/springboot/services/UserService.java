package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();

    User findById(Integer id);

    void add(User user);

    void update(User user);

    void delete(Integer id);
}
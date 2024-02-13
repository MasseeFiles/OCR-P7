package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        User userToFind = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found : Id used " + id));

        return userToFind;
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        User userToUpdate = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User to update not found : Id used " + user.getUserId()));

        userRepository.delete(userToUpdate);
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User to delete not found : Id used " + id));

        userRepository.delete(userToDelete);
    }
}

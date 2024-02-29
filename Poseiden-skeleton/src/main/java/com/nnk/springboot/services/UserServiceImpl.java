package com.nnk.springboot.services;

import com.nnk.springboot.domain.UserApp;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserApp> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserApp findById(Integer id) {
        UserApp userAppToFind = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserApp not found : Id used " + id));

        return userAppToFind;
    }

    @Override
    public void add(UserApp userApp) {
        userRepository.save(userApp);
    }

    @Override
    public void update(UserApp userApp) {
        UserApp userAppToUpdate = userRepository.findById(userApp.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("UserApp to update not found : Id used " + userApp.getUserId()));

        userRepository.delete(userAppToUpdate);
        userRepository.save(userApp);
    }

    @Override
    public void delete(Integer id) {
        UserApp userAppToDelete = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserApp to delete not found : Id used " + id));

        userRepository.delete(userAppToDelete);
    }
}

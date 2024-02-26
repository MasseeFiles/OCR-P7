package com.nnk.springboot.configuration;

import com.nnk.springboot.domain.UserApp;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserApp> optionalUser = userRepository.findByUserName(username);

        if (optionalUser.isPresent()) {
            var userFound = optionalUser.get();

            UserDetails userDetails = User.builder()        //classe User de springSecurity differente de classe User du domain
                    .username(userFound.getUserName())
                    .password(userFound.getPassword())
                    .roles(userFound.getRole())
//                    .roles(getRoles(userFound))
                    .build();

            return userDetails;

        } else {
            throw new UsernameNotFoundException("User not found in DB - UserName used : " + username);
        }
    }

    private String getRoles(UserApp userApp) {
        return userApp.getRole();

    }
}

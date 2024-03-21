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

/**
 * Classe implémentant un UserDetailService.
 * Permet de définir où et quelles informations doivent être
 * recherchées dans l'appli correspondant au UserName et au Password
 * fournit par un utilisateur qui veut s'identifier
 * Renvoit un UserDetails qui contient les informations
 * disponibles en interne correspondant au UserName et au Password
 * fournis
 * @see SpringSecurityConfig
 */

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserApp> optionalUser = userRepository.findByUserName(username);

        if (optionalUser.isPresent()) {
            var userFound = optionalUser.get();
            UserDetails userDetails = User.builder()        //classe User de SpringSecurity differente de classe User du domain (UserApp)
                    .username(userFound.getUserName())
                    .password(userFound.getPassword())
                    .roles(userFound.getRole())
                    .build();
            return userDetails;
        } else {
            throw new UsernameNotFoundException("User not found in DB - UserName used : " + username);
        }
    }
}

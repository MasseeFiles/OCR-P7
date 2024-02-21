package com.nnk.springboot.configuration;

import ch.qos.logback.core.encoder.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    //penser à effacer historique de navigation avant de lancer appli pouir spring security
    //logout implemente par defaut par spring
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity    //ordre des requestMatchers IMPORTANT!!!
                .authorizeHttpRequests((requests) -> requests   //AUTHORISATIONS
                        .requestMatchers("/home").permitAll()   //acces a l'appli
                        .requestMatchers("/user/**").hasRole("admin")   //partie uniquement accessible à utilisateur admin
                        .requestMatchers("/bidList/**").authenticated()
                        .requestMatchers("/curvePoint/**").authenticated()
                        .requestMatchers("/rating/**").authenticated()
                        .requestMatchers("/ruleName/**").authenticated()
                        .requestMatchers("/trade/**").authenticated()
                )
                .formLogin((form) -> form   //AUTHENTIFICATION
                                .successHandler(customAuthenticationSuccessHandler)
//                        .successForwardUrl("/bidList/list").   // definit la page à afficher si authentification ok
//                        .failureForwardUrl("/rating/list")
//                        .defaultSuccessUrl("/rating/list")
                );
        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {      // creétioan d'unuser en memeoire  (pas dans BDD)
        UserDetails userDetails = User.builder()
                .username("u")
                .password("$2a$13$nkQiKy98XqzWyywjCU9J3urCxRTBZN8..3fv.g4LsyoVtX2bp0dXu")
                .roles("admin")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }



}
package com.nnk.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    //penser à effacer historique de navigation avant de lancer appli pouir spring security
    //login et logout implementés par defaut par spring

    @Autowired
    private UserDetailsService userDetailsService;
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
    public UserDetailsService userDetailsService() {     //implementé par CustomUserDetailsService
        return userDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    @Autowired
//    void configureGlobal(DataSource projectDataBase, AuthenticationManagerBuilder auth) throws Exception {  //parametre auth permet de configurer le mechanisme d'authentification - configuration d'un filtre particulier
//        auth.jdbcAuthentication()   //methode particuliere pour authentification via une bdd (JDBC - Java Database Connectivity).
//                .dataSource(projectDataBase)
//                .usersByUsernameQuery("SELECT user_name , password , true FROM user WHERE user_name = ?")
//                .authoritiesByUsernameQuery("SELECT user_name , 'role' , true FROM user WHERE user_name = ?")
//        ;
//    }


//    @Bean
//    public UserDetailsService userDetailsService() {      // creétioan d'unuser en memeoire  (pas dans BDD)
//        UserDetails userDetails = User.builder()
//                .username("u")
//                .password("$2a$13$nkQiKy98XqzWyywjCU9J3urCxRTBZN8..3fv.g4LsyoVtX2bp0dXu")
//                .roles("admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }


}
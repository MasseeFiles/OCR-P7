//package com.nnk.springboot.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.header.writers.StaticHeadersWriter;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .formLogin((form) -> form
//                    .loginPage("/login")
//                    .usernameParameter("userName") //definit dans le form la valeur considérée par spring comme un username
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/transfer") // definit la page à afficher si authentification ok
//                    .permitAll()
//            );
//        return http.build();
//    }
//    @Autowired
//    void configureGlobal(DataSource projectDataBase, AuthenticationManagerBuilder auth) throws Exception {  //parametre auth permet de configurer le mechanisme d'authentification - configuration d'un filtre particulier
//        auth.jdbcAuthentication()   //methode particuliere pour authentification via une bdd (JDBC - Java Database Connectivity).
//                .dataSource(projectDataBase)
//                .usersByUsernameQuery("SELECT user_email , password , true FROM users WHERE user_email = ?")
//                .authoritiesByUsernameQuery("SELECT user_email , 'user' FROM users WHERE user_email = ?")
//        ;
//    }
//}
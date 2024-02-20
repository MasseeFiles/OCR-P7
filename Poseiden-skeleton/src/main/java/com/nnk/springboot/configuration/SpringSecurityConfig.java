//package com.nnk.springboot.configuration;
//
//import ch.qos.logback.core.encoder.Encoder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.header.writers.StaticHeadersWriter;
//
//import javax.sql.DataSource;
//
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user1 = User.withUsername("user1")
//                .password(passwordEncoder().encode("password1"))
//                .roles("admin")
//                .build();
//        return new InMemoryUserDetailsManager(user1);
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeHttpRequests((requests) -> requests
//                    .requestMatchers("/bidList/add").authenticated()
//                    .requestMatchers("/bidList/list").authenticated()
//                    .requestMatchers("/bidList/update").authenticated()
//                    .requestMatchers("/curvePoint/add").authenticated()
//                    .requestMatchers("/curvePoint/list").authenticated()
//                    .requestMatchers("/curvePoint/update").authenticated()
//                    .requestMatchers("/rating/add").authenticated()
//                    .requestMatchers("/rating/list").permitAll()
//                    .requestMatchers("/rating/update").authenticated()
//                    .requestMatchers("/ruleName/add").authenticated()
//                    .requestMatchers("/ruleName/list").authenticated()
//                    .requestMatchers("/ruleName/update").authenticated()
//                    .requestMatchers("/trade/add").authenticated()
//                    .requestMatchers("/trade/list").authenticated()
//                    .requestMatchers("/trade/update").authenticated()
//                    .requestMatchers("/user/add").authenticated()
//                    .requestMatchers("/user/list").authenticated()
//                    .requestMatchers("/user/update").authenticated()
//                    .requestMatchers("login").permitAll()
//
//                );
////            .formLogin((form) -> form
////                    .loginPage("/login")
////                    .usernameParameter("userName") //definit dans le form la valeur considérée par spring comme un username
////                    .passwordParameter("password")
////                    .defaultSuccessUrl("/bidList/list") // definit la page à afficher si authentification ok
////                    .permitAll()
////            );
//        return httpSecurity.build();
//    }
//
//}
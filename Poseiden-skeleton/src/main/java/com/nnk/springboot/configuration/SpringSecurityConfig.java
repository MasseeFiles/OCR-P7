package com.nnk.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.SpringAuthorizationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    //penser à effacer historique de navigation avant de lancer appli pour spring security
    //login et logout implementés par defaut par spring

    @Autowired
    private UserDetailsService userDetailsService;
//    @Autowired
//    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity    //ordre des requestMatchers IMPORTANT!!!
//                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests   //AUTHORISATIONS
                        .requestMatchers("/home").permitAll()   //acces a l'appli
                        .requestMatchers("/user/**").hasRole("admin")   //partie uniquement accessible à utilisateur admin
                        .requestMatchers("/bidList/**").authenticated()
                        .requestMatchers("/curvePoint/**").authenticated()
                        .requestMatchers("/rating/**").authenticated()
                        .requestMatchers("/ruleName/**").authenticated()
                        .requestMatchers("/trade/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults()
                );
//
//                .formLogin((form) -> form   //AUTHENTIFICATION
////                                .successHandler(customAuthenticationSuccessHandler)
////                        .successForwardUrl("/bidList/list").   // definit la page à afficher si authentification ok
//                        .failureForwardUrl("/403")
////                        .defaultSuccessUrl("/rating/list")
//                );
        return httpSecurity.build();
    }

    //AuthenticationProvider permet de configurer la procedure d'authentification
    //AuthenticationProvider est ici implementé par DaoAuthenticationProvider qui permet de configurer le userDetails et le passwordEncoder
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    //methode pour definir le userDetailService à utiliser par SpringSecurity
    //Interface implementée dans classe CustomUserDetailService
    //UserDetailservice sert à indiquer à SpringSecurity ou chercher les infos d'un userApp qui veut s'identifier (repository, bdd à utiliser, type de données à cherhcer...)
    @Bean
    public UserDetailsService userDetailsService() {     //implementé par CustomUserDetailsService
        return userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // bean pour ecouter events (ex: cas d'une authorisation refusée)
//    @Bean
//    public AuthorizationEventPublisher authorizationEventPublisher
//            (ApplicationEventPublisher applicationEventPublisher) {
//        return new SpringAuthorizationEventPublisher(applicationEventPublisher);
//    }

//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return new CustomAccessDeniedHandler();
//    }

// création d'un userApp en memoire  (pas dans BDD)
//    @Bean
//    public UserDetailsService userDetailsService() {      // creétioan d'unuser en memeoire  (pas dans BDD)
//        UserDetails userDetails = User.builder()
//                .username("u")
//                .password("$2a$12$bXaAltyyonTxFDt2kJijcO0mYninZlMS4VzZe.PinSCrNfjB.LY6S")
//                .roles("admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }


}
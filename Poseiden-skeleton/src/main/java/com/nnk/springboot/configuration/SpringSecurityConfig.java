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

import static org.springframework.security.config.Customizer.withDefaults;


/**
 * Classe de configuration de SpringSecurity et fournit par defaut...
 *
 * @author JB
 * @version v1
 * @since 2024
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
//    @Autowired
//    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
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
                .formLogin(withDefaults()   //AUTHENTIFICATIONS
                );

        return httpSecurity.build();
    }

    /**
     * Bean AuthenticationProvider permet de configurer la procedure d'authentification
     * Implementé par DaoAuthenticationProvider qui permet de configurer le userDetails et le passwordEncoder
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * methode pour definir le userDetailService à utiliser par SpringSecurity
     * Le userDetailService sert à indiquer à SpringSecurity ou chercher en interne les infos d'un userApp pour le processus d'iddentification (bdd à utiliser, type de données à chercher...)
     * Interface userDetailService implementée dans classe CustomUserDetailService
     *
     * @see CustomUserDetailService
     */
    @Bean
    public UserDetailsService userDetailsService() {     //implementé par CustomUserDetailsService
        return userDetailsService;
    }

    /**
     * methode pour instancier un encoder pour le mot de passe
     * voir bean AuhenticationProvider()
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
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



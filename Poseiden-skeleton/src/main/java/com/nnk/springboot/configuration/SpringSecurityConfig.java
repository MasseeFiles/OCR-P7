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
 * Classe de configuration de SpringSecurity.
 *
 * Permet de configurer la procédure d'authentification et d'authorisation de l'appli
 * @author JB Massee
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Bean de configuration de la SecurityFilterChain.
     *
     * Permet de définir quels filtres de controle SpringSecurity doit appliquer et
     * à quels moments pour la partie authentification et la partie authorisation
     * Configuration via un objet HttpSecurity
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                /**
                 * Configuration de la partie AUTHORISATION.
                 *
                 * Correspond aux filtres à appliquer pour avoir accès aux différents
                 * endpoints proposés par l'appli
                 */
                .authorizeHttpRequests((requests) -> requests
                        /**
                         * Rend accessible la page /home à tous les utilisateurs sans condition d'authentification.
                         * L'URI /home correspond au point d'entrée de l'appli pour permettre
                         * à un utilisateur de se logger
                         */
                        .requestMatchers("/home").permitAll()
                        /**
                         * Authorisation basée sur le role du User
                         * Reserve l'accès aux URI commencant par /user aux
                         * utilisateurs ayant un role d'admin
                         */
                        .requestMatchers("/user/**").hasRole("admin")
                        /**
                         * Reserve l'accès aux URI mentionnées
                         * uniquement aux utilisateurs ayant passé avec succès
                         * la procédure d'authentification
                         */
                        .requestMatchers("/bidList/**").authenticated()
                        .requestMatchers("/curvePoint/**").authenticated()
                        .requestMatchers("/rating/**").authenticated()
                        .requestMatchers("/ruleName/**").authenticated()
                        .requestMatchers("/trade/**").authenticated()
                        /**
                         * Reserve l'accès à toutes les endpoints non-mentionnées précedemment
                         * à des utilisateurs ayant passé avec succès la procédure d'authentification
                         * Protection par defaut de l'appli
                         */
                        .anyRequest().authenticated()
                )
                /**
                 * Configuration d'une partie de la procedure d'AUTHENTIFICATION.
                 *
                 * Utilisation d'un template HTML fournit par defaut par SpringSecurity
                 * permettant à un utilisateur d'entrer son nom et mot de passe
                 */
                .formLogin(withDefaults()
                );

        return httpSecurity.build();
    }

    /**
     * Bean authenticationProvider sert à configurer une partie de la procédure d'AUTHENTIFICATION.
     *
     * Implementé par un objet DaoAuthenticationProvider qui permet de définir:
     * un userDetailsService:
     *      comment sont récupérées dans l'appli les données à comparer à celle fournies par un utilisateur qui veut s'identifier
     * @see CustomUserDetailService
     * un passwordEncoder:
     *      comment est codé le mot de passe d'un utilisateur
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Bean userDetailsService sert à spécifier le userDetailService à utiliser pour la procedure d'authentification.
     *
     * Interface userDetailService implementée ici dans une classe CustomUserDetailService
     * @see CustomUserDetailService
     * @see AuthenticationProvider
     */
    @Bean
    public UserDetailsService userDetailsService() {     //implementé par CustomUserDetailsService
        return userDetailsService;
    }

    /**
     * Bean passwordEncoder sert à instancier un encoder pour la sauvegarde en base du mot de passe d'un utilisateur.
     *
     * Ici, bCryptPasswordEncoder
     * @see AuthenticationProvider
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



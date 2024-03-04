package com.nnk.springboot;

import com.nnk.springboot.configuration.SpringSecurityConfig;
import com.nnk.springboot.domain.UserApp;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserAppServiceTests {
    @Autowired
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
    }

    @Test
    void findById_Ok() {
        //GIVEN
        UserApp userAppExpected = new UserApp();

        userAppExpected.setUserId(1);
        userAppExpected.setUserName("user1");
        userAppExpected.setPassword("$2a$12$9M0ZJtiyAmaY7LV3HZ4GwuX3l9JyAKCRQUegsyMJgOwFRqYmREpfO");
        userAppExpected.setFullName("user1FullName");
        userAppExpected.setRole("admin");

        when(userRepository.findById(1)).thenReturn(Optional.of(userAppExpected));

        //WHEN
        UserApp userAppActual = userService.findById(1);

        //THEN
        assertThat(userAppExpected).usingRecursiveComparison()
                .isEqualTo(userAppActual);
    }

    @Test
    void findById_User_Not_Found() {
        //GIVEN
        Integer idNotInDb = 10; //id = 10 non present dans DBDataInitializerTest

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            userService.findById(idNotInDb);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("UserApp not found : Id used 10");
    }

    @Test
    void update_User_Not_Found() {
        //GIVEN
        UserApp userAppNotInDB = new UserApp();
        userAppNotInDB.setUserId(100);

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            userService.update(userAppNotInDB);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("UserApp to update not found : Id used 100");
    }

    @Test
    void delete_User_Not_Found() {
        //GIVEN
        Integer idNotInDb = 100;

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            userService.delete(idNotInDb);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("UserApp to delete not found : Id used 100");
    }
}


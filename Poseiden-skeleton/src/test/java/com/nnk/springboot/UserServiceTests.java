package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {
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
        User userExpected = new User();
        userExpected.setUserId(1);
        userExpected.setUserName("tom");
        userExpected.setPassword("pass1");
        userExpected.setFullName("tomName");
        userExpected.setRole("admin");

        when(userRepository.findById(1)).thenReturn(Optional.of(userExpected));

        //WHEN
        User userActual = userService.findById(1);

        //THEN
        assertThat(userExpected).usingRecursiveComparison()
                .isEqualTo(userActual);
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
                .hasMessageContaining("User not found : Id used 10");
    }

    @Test
    void update_User_Not_Found() {
        //GIVEN
        User userNotInDB = new User();
        userNotInDB.setUserId(100);

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            userService.update(userNotInDB);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User to update not found : Id used 100");
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
                .hasMessageContaining("User to delete not found : Id used 100");
    }
}


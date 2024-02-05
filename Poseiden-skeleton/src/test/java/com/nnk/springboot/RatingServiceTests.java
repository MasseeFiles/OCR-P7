package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.RatingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RatingServiceTests {
    @Autowired
    private RatingService ratingService;
    @Mock
    private RatingRepository ratingRepository;

    @BeforeEach
    public void setup() {
        ratingRepository = mock(RatingRepository.class);
//        ratingService = new RatingServiceImpl(ratingRepository);    // Implementation plutot qu'interface???
    }

    @Test
    void findById_Ok() {    //necessaire???
        //GIVEN
        Rating ratingExpected = new Rating(1, "AA", "AA", "AA", 1);
        when(ratingRepository.findById(1)).thenReturn(Optional.of(ratingExpected));

        //WHEN
        Rating ratingActual = ratingService.findById(1);

        //THEN
        assertThat(ratingExpected).usingRecursiveComparison()
                .isEqualTo(ratingActual);
    }
    @Test
    void findById_Rating_Not_Found() {
        //GIVEN
        Integer idNotInDb = 10; //IDtest = 10 non present dans DBDataInitializerTest

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            ratingService.findById(idNotInDb);
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Rating not found : Id used 10");
    }
    @Test
    void update_Rating_Not_Found() {
        //GIVEN
        Rating ratingNotInDB = new Rating(10, "AA", "AA", "AA", 1);

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            ratingService.update(ratingNotInDB);
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Rating to update not found : Id used 10");
    }

    @Test
    void delete_Rating_Not_Found() {
        //GIVEN
        Integer idNotInDb = 10;

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            ratingService.delete(idNotInDb);
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Rating to delete not found : Id used 10");
    }
}

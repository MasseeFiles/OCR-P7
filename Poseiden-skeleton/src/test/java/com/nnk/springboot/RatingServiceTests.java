package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RatingServiceTests {
    @Autowired
    private RatingService ratingService;

    @Test
    void findById_Rating_Not_Found() {
        //GIVEN
        Integer idTest = 10; //IDtest = 10 non present dans DBDataInitializer

        //WHEN

        //THEN
        assertThrows(RuntimeException.class, () -> {
            ratingService.findById(idTest);
        });
    }

    @Test
    void update_Rating_Not_Found() {
        //GIVEN
        Rating ratingNotInDB = new Rating(10, "AA", "AA", "AA", 1);

        //WHEN

        //THEN
        assertThrows(RuntimeException.class, () -> {
            ratingService.update(ratingNotInDB);
        });
    }

    @Test
    void delete_Rating_Not_Found() {
        //GIVEN
        Rating ratingNotInDB = new Rating(10, "AA", "AA", "AA", 1);

        //WHEN

        //THEN
        assertThrows(RuntimeException.class, () -> {
            ratingService.update(ratingNotInDB);
        });
    }
    //TODO : enlever application-test.properties si necessaire
}

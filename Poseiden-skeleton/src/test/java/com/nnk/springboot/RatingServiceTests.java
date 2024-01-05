package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RatingServiceTests {
    @Autowired
    private RatingService ratingService;
    // test pour methode findall??

    @Test
    void findById_NotFound(){
        //GIVEN
        Integer idTest = 20;

        //WHEN

        //THEN
        assertThrows(RuntimeException.class, () -> {ratingService.findById(idTest);
        });
    }
}

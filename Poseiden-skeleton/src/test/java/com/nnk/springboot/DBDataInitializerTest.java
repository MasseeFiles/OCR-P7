package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
//@Profile("test")
public class DBDataInitializerTest {
    public DBDataInitializerTest(RatingRepository ratingRepository){   //ajouter bcryptencoder apres configuration de springsecurity
        Rating[] ratings = new Rating[3];

        ratings[0] = new Rating(1, "11", "22" , "33" , 1);
        ratings[1] = new Rating(2, "44", "55" , "66" , 2);
        ratings[2] = new Rating(3, "77", "88" , "99" , 3);

        ratingRepository.saveAll(Arrays.asList(ratings));
    }
}

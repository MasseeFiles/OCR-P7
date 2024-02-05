package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBDataInitializerApp{
    public DBDataInitializerApp(RatingRepository ratingRepository){
        Rating[] ratings = new Rating[3];

            ratings[0] = new Rating(1, "11", "22" , "33" , 1);
            ratings[1] = new Rating(2, "44", "55" , "66" , 2);
            ratings[2] = new Rating(3, "77", "88" , "99" , 3);

            ratingRepository.saveAll(Arrays.asList(ratings));
    }
}

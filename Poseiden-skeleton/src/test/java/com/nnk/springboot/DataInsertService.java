package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DataInsertService {
    public DataInsertService (RatingRepository ratingRepository){
        Rating[] ratings = new Rating[4];

        ratings[0] = new Rating(1, "AA", "AA" , "AA" , 1);
        ratings[1] = new Rating(2, "BB", "BB" , "BB" , 2);
        ratings[2] = new Rating(3, "CC", "CC" , "CC" , 3);

        ratingRepository.saveAll(Arrays.asList(ratings));
    }
}

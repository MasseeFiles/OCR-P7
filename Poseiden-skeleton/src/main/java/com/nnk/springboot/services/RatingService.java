package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    List<Rating> findAll();

    Rating findById(Integer id);

    void save(Rating rating);
    void add(Rating rating);


    void update(Rating rating);

    void delete(Integer id);

}

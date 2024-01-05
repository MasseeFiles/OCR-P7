package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public void save(Rating ratingToSave) {
        ratingRepository.save(ratingToSave);
    }

    @Override
    public Rating findById(Integer id) {
        Rating ratingToFind = ratingRepository.findById(id)     //optional<rating>
                .orElseThrow(() -> new RuntimeException("Rating not found : Id used " + id));   //objet rating

        return ratingToFind;
    }

    @Override
    public void update(Rating rating) { // autre solution : delete et insert - ex bidlist
        Rating ratingToUpdate = ratingRepository.findById(rating.getRatingId())
                .orElseThrow(() -> new RuntimeException("Rating to update not found : Id used " + rating.getRatingId()));

        ratingToUpdate.setMoodysRating(rating.getMoodysRating());
        ratingToUpdate.setSandPRating(rating.getSandPRating());
        ratingToUpdate.setFitchRating(rating.getFitchRating());
        ratingToUpdate.setOrderNumber(rating.getOrderNumber());

        ratingRepository.save(ratingToUpdate);
    }

    @Override
    public void delete(Integer id) {
        Rating ratingToDelete = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating to delete not found : Id used " + id));

        ratingRepository.delete(ratingToDelete);
    }
}



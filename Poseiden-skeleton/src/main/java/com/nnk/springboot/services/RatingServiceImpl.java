package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public void add(Rating ratingToAdd) { ratingRepository.save(ratingToAdd); }

    @Override
    public Rating findById(Integer id) {
        return ratingRepository.findById(id)     //optional<rating>
                .orElseThrow(() -> new IllegalArgumentException("Rating not found : Id used " + id));
    }

    @Override
    public void update(Rating rating) { // autre solution pour update : delete et insert - voir si pb avec rating id
        Rating ratingToUpdate = ratingRepository.findById(rating.getRatingId())
                .orElseThrow(() -> new IllegalArgumentException("Rating to update not found : Id used " + rating.getRatingId()));

        ratingToUpdate.setMoodysRating(rating.getMoodysRating());
        ratingToUpdate.setSandPRating(rating.getSandPRating());
        ratingToUpdate.setFitchRating(rating.getFitchRating());
        ratingToUpdate.setOrderNumber(rating.getOrderNumber());

        ratingRepository.save(ratingToUpdate);
    }

    @Override
    public void delete(Integer id) {
        Rating ratingToDelete = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rating to delete not found : Id used " + id));

        ratingRepository.delete(ratingToDelete);
    }
}



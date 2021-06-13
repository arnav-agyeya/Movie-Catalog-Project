package com.example.movieratingservice.resource;

import com.example.movieratingservice.model.Rating;
import com.example.movieratingservice.model.RatingImpl;
import com.example.movieratingservice.model.UserRatingsWrapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingdata")
public class RatingService {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new RatingImpl("ID1", 9);
    }

    //Using a list will jeopardise future changes, imagine wanting to add an property to response
    // Use an object-wrapper to ensure backward compatibility.
    @RequestMapping("users/{userId}")
    public UserRatingsWrapper getMoviesWatchedByUser(@PathVariable("userId") String userId){
        return new UserRatingsWrapper(Arrays.asList(
                new RatingImpl("123", 8),
                new RatingImpl("456", 9)
        ));
    }
}

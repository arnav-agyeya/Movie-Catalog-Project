package com.example.movieratingservice.model;

import java.util.List;

public class UserRatingsWrapper {
    List<Rating> ratings;

    public UserRatingsWrapper(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}

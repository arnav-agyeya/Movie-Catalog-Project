package com.example.moviecatalogservice.models;

import java.util.List;

public class UserRatingsWrapper {
    List<RatingImpl> ratings;

    public UserRatingsWrapper() {
    }

    public UserRatingsWrapper(List<RatingImpl> ratings) {
        this.ratings = ratings;
    }

    public List<RatingImpl> getRatings() {
        return ratings;
    }
}

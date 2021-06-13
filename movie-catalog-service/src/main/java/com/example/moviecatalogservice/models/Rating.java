package com.example.moviecatalogservice.models;

public interface Rating {
    String getMovieId();

    void setMovieId(String movieId);

    int getMovieRating();

    void setMovieRating(int movieRating);
}

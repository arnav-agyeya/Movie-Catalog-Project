package com.example.movieratingservice.model;

public interface Rating {
    String getMovieId();

    void setMovieId(String movieId);

    int getMovieRating();

    void setMovieRating(int movieRating);
}

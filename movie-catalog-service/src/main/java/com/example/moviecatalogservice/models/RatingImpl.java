package com.example.moviecatalogservice.models;

public class RatingImpl implements Rating {

    private String movieId;
    private int movieRating;

    public RatingImpl(String movieId, int movieRating) {
        this.movieId = movieId;
        this.movieRating = movieRating;
    }

    public RatingImpl() {
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }
}

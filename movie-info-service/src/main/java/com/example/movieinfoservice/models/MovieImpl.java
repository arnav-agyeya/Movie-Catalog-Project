package com.example.movieinfoservice.models;

public class MovieImpl implements Movie {

    private String movieName;
    private String movieId;

    public MovieImpl(String movieName, String movieId) {
        this.movieName = movieName;
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}

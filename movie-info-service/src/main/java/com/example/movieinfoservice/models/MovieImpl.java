package com.example.movieinfoservice.models;

public class MovieImpl implements Movie {

    private String movieName;
    private String movieId;
    private String movieOverView;

    public MovieImpl(String movieId, String movieName, String movieOverView) {
        this.movieName = movieName;
        this.movieId = movieId;
        this.movieOverView = movieOverView;
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

    public String getMovieOverView() {
        return movieOverView;
    }

    public void setMovieOverView(String movieOverView) {
        this.movieOverView = movieOverView;
    }
}

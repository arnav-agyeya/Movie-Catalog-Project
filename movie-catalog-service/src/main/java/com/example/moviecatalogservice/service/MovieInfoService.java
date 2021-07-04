package com.example.moviecatalogservice.service;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.MovieImpl;
import com.example.moviecatalogservice.models.RatingImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String MOVIE_INFO_SERVICE = "movie-info-service";

    @HystrixCommand(fallbackMethod = "getFallBackCatalogItem")
    public CatalogItem getCatalogItem(RatingImpl rating) {
        MovieImpl movie =
                restTemplate.getForObject("http://" + MOVIE_INFO_SERVICE + "/movies/" + rating.getMovieId(),
                        MovieImpl.class);

        assert movie != null;
        return new CatalogItem(movie.getMovieName(), movie.getMovieOverView(), rating.getMovieRating());
    }

    public CatalogItem getFallBackCatalogItem(RatingImpl rating) {
        return new CatalogItem("No Movie", "", rating.getMovieRating());
    }
}

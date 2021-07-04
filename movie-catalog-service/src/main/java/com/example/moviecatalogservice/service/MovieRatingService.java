package com.example.moviecatalogservice.service;

import com.example.moviecatalogservice.models.RatingImpl;
import com.example.moviecatalogservice.models.UserRatingsWrapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class MovieRatingService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String MOVIE_RATING_SERVICE = "movie-rating-service";

    @HystrixCommand(fallbackMethod = "getFallBackUserRatings")
    public List<RatingImpl> getUserRatings(String userId) {
        UserRatingsWrapper userRatingsWrapper =
                restTemplate.getForObject("http://" + MOVIE_RATING_SERVICE + "/ratingdata/users/" + userId,
                        UserRatingsWrapper.class);
        assert userRatingsWrapper != null;
        return userRatingsWrapper.getRatings();
    }

    public List<RatingImpl> getFallBackUserRatings(String userId) {
        return Collections.singletonList(new RatingImpl("NoId", 0));
    }

}

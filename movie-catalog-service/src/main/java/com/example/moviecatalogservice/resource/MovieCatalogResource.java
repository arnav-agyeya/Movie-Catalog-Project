package com.example.moviecatalogservice.resource;

import com.example.moviecatalogservice.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@ComponentScan
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder builder;
    @Autowired
    private DiscoveryClient discoveryClient;

    private static final String MOVIE_INFO_SERVICE = "movie-info-service";
    private static final String MOVIE_RATING_SERVICE = "movie-rating-service";

    //this can be made async , reactive java
    @RequestMapping("/{userId}")
    public List<ICatalogItem> getCatalog(@PathVariable("userId") String userId) {
        //get all rated movies Ids
        UserRatingsWrapper userRatingsWrapper =
                restTemplate.getForObject("http://" + MOVIE_RATING_SERVICE + "/ratingdata/users/" + userId,
                UserRatingsWrapper.class);
        assert userRatingsWrapper != null;
        List<Rating> ratings = userRatingsWrapper.getRatings();

        //For each movie Ids call info service and get details
        assert ratings != null;
        return ratings.stream()
                      .map(rating -> {
                   MovieImpl movie =
                           restTemplate.getForObject("http://" + MOVIE_INFO_SERVICE + "/movies/" + rating.getMovieId(),
                           MovieImpl.class);

                  assert movie != null;
                   return new CatalogItem(movie.getMovieName(), "Desc", rating.getMovieRating());
                })
                      .collect(Collectors.toList());
    }
}

/*
        //For getting a list
        List<RatingImpl> ratings = restTemplate.exchange("http://localhost:8087/ratingdata/users/" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingImpl>>() {
                }).getBody();
        // using webclient builder
                MovieImpl movie = builder.build()
                                         .get()
                                         .uri("http://localhost:8086/movies/" + rating.getMovieId())
                                         .retrieve()
                                         .bodyToMono(MovieImpl.class)
                                         .block();
*/


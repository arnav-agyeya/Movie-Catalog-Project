package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){
        if(movieId.equals("123")){
            return new MovieImpl("TestMov1", "123");
        }
        return new MovieImpl("TestMov2", "456");
    }
}

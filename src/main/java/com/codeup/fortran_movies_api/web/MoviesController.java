package com.codeup.fortran_movies_api.web;

import com.codeup.fortran_movies_api.data.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/movies", headers = "Accept=application/json")
public class MoviesController {

    @GetMapping
    public Movie one(){
        return new Movie(1, "The Big Lebowski",
                "1995", "Cohen Brothers",
                "Jeff Bridges, John Goodman, Steve Buscemi",
                "idk", "comedy", "comedy",
                "Wanted to go bowling");
    }

//    @GetMapping
//    private List<> getAll(@PathVariable ArrayList movies) {
//        ArrayList<> movies = new ArrayList<>();
//
//        movies spaceBalls = new Movie(1, "SpaceBalls");
//        movies returnJedi = new Movie(2, "Return of the Jedi");
//        movies revengeSith = new Movie(3, "Revenge of the Sith");
//
//        return movies;
//    }
//
//    @GetMapping("id")
//    public movies getById(@PathVariable Long id){
//
//    }
}


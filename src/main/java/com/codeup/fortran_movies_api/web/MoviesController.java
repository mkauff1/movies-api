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
    private ArrayList<movies> getAll() {
        List movies = new ArrayList<>();

        movies spaceBalls = new Movie(1, "SpaceBalls");
        movies returnJedi = new Movie(2, "Return of the Jedi");
        movies revengeSith = new Movie(3, "Revenge of the Sith");

        return movies;
    }

    @GetMapping("id")
    public movies getById(@PathVariable Long id){

    }
}


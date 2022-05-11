package com.codeup.fortran_movies_api.web;

import com.codeup.fortran_movies_api.data.Movie;
import com.codeup.fortran_movies_api.data.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/movies")
public class MoviesController {

    private final MovieRepository movieRepository;

    public MoviesController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // TODO: put the expected path out to the side of the method annotation
    //  -> this helps to keep track so we don't have to guess if methods conflict on the same path
    @GetMapping("all") // /api/movies/all
    public List<Movie> getAll() {
        return movieRepository.findAll(); // TODO: findAll() will return a list of objects and is provided by the JpaRepository
    }

    // /api/movies/3 <- 3 is the path variable for id
    @GetMapping("{id}") // Define the path variable to use here
    public Movie getById(@PathVariable long id) { // Actually use the path variable here by annotating a parameter with @PathVariable
        return movieRepository.findById(id).orElse(null); // prevent errors by returning null... not the greatest practice, but it'll do for now
    }

    @GetMapping("search") // /api/movies/search?title=<movieTitle>
    public List<Movie> getByTitle(@RequestParam("title") String title){
        // TODO: we need to create the findByTitle() method in our MoviesRepository - magic!
        return movieRepository.findByTitle(title);
    }

    @GetMapping("search/year") // api/movies/search/year
    public List<Movie> getByYearRange(@RequestParam("startYear") int startYear, @RequestParam("endYear") int endYear){
        // TODO: @RequestParam expects a query parameter in the request URL
        //  to have a param matching what is in the annotation (ie: @RequestParam("startYear"))
        return movieRepository.findByYearRange(startYear, endYear);
    }

    @PostMapping // /api/movies POST
    public void create(@RequestBody Movie movie){
        // add to our movies list (fake db)
        movieRepository.save(movie);
    }

    @PostMapping ("many")// /api/movies/many POST
    public void createMany(@RequestBody List<Movie> movies){ // @RequestBody is very important to knowing how the Request's body maps
        // saveAll() lets you pass a collection as an argument and commit all the objects to the database
        movieRepository.saveAll(movies);
    }


    // TODO: make a delete request method here!
    @DeleteMapping("{id}") // api/movies/{id} -> api/movies/3 DELETE
    public void deleteById(@PathVariable long id) throws IOException {
        try {
            movieRepository.deleteById(id);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No matching movie with ID: " + id);
        }
    }



}
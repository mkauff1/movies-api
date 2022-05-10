package com.codeup.fortran_movies_api.web;

import com.codeup.fortran_movies_api.data.Movie;
import com.codeup.fortran_movies_api.data.MovieRepository;
import org.springframework.web.bind.annotation.*;
//import com.codeup.restblog.data.PostRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/movies")
public class MoviesController {

    // Let's set up a temporary backing field to give us a list of movies to work with.
    // We'll remove this once we integrate with the database
    private List<Movie> sampleMovies = setMovies();

    private final MovieRepository movieRepository;

    public MoviesController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public Movie one(){
        return sampleMovies.get(1);
    }

    @GetMapping("all") // Path becomes: /api/movies/all
    public List<Movie> getAll() {
        //return sampleMovies;
        return movieRepository.findAll();
    }

    @GetMapping("id") //Define the path variable to use here
    public Movie getById(@PathVariable int id){ // Actually use the path variable here by annotating a parameter with @Pathvariable
          System.out.println(id);
            return sampleMovies.stream().filter((movie) -> {
                return movie.getId() == id;
            })
            .findFirst()
            .orElse(null);
    }

    @GetMapping("title")
    public Movie getByTitle(@PathVariable String title){
        Movie movieToReturn =  null;
        for (Movie movie : sampleMovies){
            if (movie.getTitle().equals(title)){
                movieToReturn = movie;
            }
        }
        return movieToReturn;
    }
    @PostMapping
    public void create(@RequestBody Movie movie){
        System.out.println(movie);
        // add to our movies list (fake db)
        movieRepository.save(movie);
    }

    @PostMapping("many") // api/movies/many POST
    public void createMany(@RequestBody List<Movie> movies){ //@RequestBody is very important to knowing how the Requests body maps
        sampleMovies.addAll(movies); // addAll (On the collection object) allows us to add all
    }
    // This utility method simply sets up and populates our sampleMovies backing field
    // Will remove once we integrate with the database
    private List<Movie> setMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie(2, "Pulp Fiction", "1994", "Quentin Tarantino",
                "Samuel L. Jackson, Uma Therman, Bruce Willis, John Travolta, Ving Rhames",
                "10", "action, drama, suspense, cult classic, crime",
                "Vincent Vega (John Travolta) and Jules Winnfield (Samuel L. Jackson) are hitmen with a penchant for philosophical discussions. " +
                        "In this ultra-hip, multi-strand crime movie, their storyline is interwoven with those of their boss, " +
                        "gangster Marsellus Wallace (Ving Rhames) ; his actress wife, Mia (Uma Thurman) ; " +
                        "struggling boxer Butch Coolidge (Bruce Willis) ; master fixer Winston Wolfe (Harvey Keitel) and a nervous pair of armed robbers" +
                        "Hello", "Goodbye"));
        movies.add(new Movie(1, "The Big Lebowski",
                "1995", "The Cohen Bros",
                "Jeff Bridges, John Goodman, Steve Buscemi",
                "idk bro", "comedy, drama?",
                "the dude just wanted to relax and go bowling", "Goodbye"));

        return movies;
    }
}


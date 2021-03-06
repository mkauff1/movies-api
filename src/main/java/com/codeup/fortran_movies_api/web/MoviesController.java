package com.codeup.fortran_movies_api.web;

import com.codeup.fortran_movies_api.data.Director;
import com.codeup.fortran_movies_api.data.DirectorsRepository;
import com.codeup.fortran_movies_api.data.Genre;
import com.codeup.fortran_movies_api.data.GenresRepository;
import com.codeup.fortran_movies_api.data.Movie;
import com.codeup.fortran_movies_api.data.MoviesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/movies")
public class MoviesController {

    private final MoviesRepository moviesRepository;
    private final DirectorsRepository directorsRepository;
    private final GenresRepository genresRepository;

    public MoviesController(MoviesRepository moviesRepository, DirectorsRepository directorsRepository, GenresRepository genresRepository) {
        this.moviesRepository = moviesRepository;
        this.directorsRepository = directorsRepository;
        this.genresRepository = genresRepository;
    }

    @GetMapping("all") // /api/movies/all
    public List<MovieDto> getAll() {
        //return moviesRepository.findAll(); // TODO: findAll() will return a list of objects and is provided by the JpaRepository
        List<Movie> movieEntities = moviesRepository.findAll();
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie: movieEntities) {
            movieDtos.add(new MovieDto(movie.getId(),
                    movie.getTitle(),
                    movie.getYear(),
                    movie.getPlot(),
                    movie.getPoster(),
                    movie.getRating(),

                    movie.getGenre()
                        .stream()
                        .map(Genre::getName)
                        .collect(Collectors.joining(", ")),
                    movie.getDirector().getName()));
        }
        return movieDtos;

    }

    // /api/movies/3 <- 3 is the path variable for id
    @GetMapping("{id}") // Define the path variable to use here
    public Movie getById(@PathVariable long id) { // Actually use the path variable here by annotating a parameter with @PathVariable
        return moviesRepository.findById(id).orElse(null); // prevent errors by returning null... not the greatest practice, but it'll do for now
    }

    @GetMapping("search") // /api/movies/search?title=<movieTitle>
    public List<Movie> getByTitle(@RequestParam("title") String title){
        // TODO: we need to create the findByTitle() method in our MoviesRepository - magic!
        return moviesRepository.findByTitle(title);
    }

    @GetMapping("search/year") // api/movies/search/year
    public List<Movie> getByYearRange(@RequestParam("startYear") int startYear, @RequestParam("endYear") int endYear){
        return moviesRepository.findByYearRange(startYear, endYear);
    }

    @GetMapping("search/director")
    public List<Director> getByDirector(@RequestParam("name") String directorName){
        List<Director> directors = directorsRepository.findByName(directorName);
        return directors;
    }

    @GetMapping("search/genre")
    public Genre getByGenre(@RequestParam("name") String genreType){
        Genre genres = genresRepository.findGenreByName(genreType);
        return genres;
    }

//    @PostMapping // /api/movies POST
//    public void create(@RequestBody Movie movie){
//        // add to our movies list (fake db)
//        moviesRepository.save(movie);
//    }

    @PostMapping
    public void create(@RequestBody MovieDto movieDto) {
        Movie movieToAdd = new Movie(
                movieDto.getId(),
                movieDto.getTitle(),
                movieDto.getYear(),
                movieDto.getPlot(),
                movieDto.getPoster(),
                movieDto.getRating()
//                movieDto.getGenre(),
//                movieDto.getDirector()
        );

        List<Director> directorsInDb = directorsRepository.findByName(movieDto.getDirector());
        if (directorsInDb.isEmpty()) {
            Director newDirector = new Director(movieDto.getDirector());
            movieToAdd.setDirector(directorsRepository.save(newDirector));
        } else {
            movieToAdd.setDirector(directorsInDb.get(0));
        }

        String[] genres = movieDto.getGenre().split(", ");
        List<Genre> movieGenre = new ArrayList<>();
        for (String genre : genres) {
            Genre genreInDb = genresRepository.findGenreByName(genre);
            System.out.println(genreInDb);
            if (genreInDb == null) {
                Genre newGenre = new Genre(genre);
                movieGenre.add(genresRepository.save(newGenre));
            } else {
                movieGenre.add(genreInDb);
            }
        }
        movieToAdd.setGenre(movieGenre);
        moviesRepository.save(movieToAdd);
    }

    @PostMapping ("many")// /api/movies/many POST
    public void createMany(@RequestBody List<Movie> movies){ // @RequestBody is very important to knowing how the Request's body maps
        // saveAll() lets you pass a collection as an argument and commit all the objects to the database
        moviesRepository.saveAll(movies);
    }

    @PutMapping
    public void updateOne(@RequestBody Movie movie){
        moviesRepository.save(movie);
    }

    @DeleteMapping("{id}") // api/movies/{id} -> api/movies/3 DELETE
    public void deleteById(@PathVariable long id) throws IOException {
        try {
            moviesRepository.deleteById(id);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No matching movie with ID: " + id);
        }
    }

}
package com.codeup.fortran_movies_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitle(String title);

    @Query(nativeQuery = true,
        value = "SELECT  * FROM movies m\n" +
            "WHERE m.year  >= ? AND m.year <= ?;")
    List<Movie> findByYearRange(Integer startYear, Integer endYear);
}

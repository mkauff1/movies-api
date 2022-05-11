package com.codeup.fortran_movies_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GenresRepository extends JpaRepository<Director, Integer>{
    List<Genre> findByName(String name);
}

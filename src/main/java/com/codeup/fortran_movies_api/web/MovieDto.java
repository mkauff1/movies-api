package com.codeup.fortran_movies_api.web;

public class MovieDto {
    private Long id;
    private String title;
    private String rating;
    private String poster;
    private String year;
    private String genre;
    private String director;
    private String plot;

    public MovieDto() {
    }

    public MovieDto(Long id, String title, String rating, String poster, String year, String genre, String director, String plot) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.poster = poster;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.plot = plot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", poster='" + poster + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", plot='" + plot + '\'' +
                '}';
    }
}

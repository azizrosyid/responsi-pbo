package model;

import entity.Movie;

public interface MovieModel {
    public Movie[] getAllMovies();
    public Movie getMovie(String title);
    public void addMovie(Movie movie);
    public void deleteMovieByTitle(String title);
    public void updateMovie(Movie movie);
}

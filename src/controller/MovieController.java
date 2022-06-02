package controller;

import entity.Movie;
import model.MovieModel;
import view.MovieView;

public class MovieController {
    private final MovieModel model;
    private final MovieView view;

    private static final double MINIMUM_VALUE = 0;
    private static final double MAXIMUM_VALUE = 5;

    public MovieController(MovieModel model, MovieView view) {
        this.model = model;
        this.view = view;

        this.addButtonListener();
        this.updateMovie();
    }

    private void addButtonListener() {
        this.view.setAddButtonListener(e -> {
            try {
                Movie movie = view.getMovie();

                validateMovie(movie);

                model.addMovie(movie);
                updateMovie();
                view.resetForm();
            } catch (Exception ex) {
                view.showErrorMessage(ex.getMessage());
            }
        });

        this.view.setDeleteButtonListener(e -> {
            try {
                Movie movie = view.getMovie();
                validateMovie(movie);

                model.deleteMovieByTitle(movie.getJudul());
                updateMovie();
                view.resetForm();

            } catch (Exception ex) {
                view.showErrorMessage(ex.getMessage());
            }
        });

        this.view.setUpdateButtonListener(e -> {
            try {
                Movie movie = view.getMovie();
                validateMovie(movie);

                model.updateMovie(movie);
                updateMovie();
                view.resetForm();
            } catch (Exception ex) {
                view.showErrorMessage(ex.getMessage());
            }
        });
    }

    public void updateMovie() {
        Movie[] movies = model.getAllMovies();
        view.updateAllMovie(movies);
    }

    private void validateMovie(Movie movie) {
        validateJudul(movie.getJudul());
        validateAlur(movie.getAlur());
        validatePenokohan(movie.getPenokohan());
        validateAkting(movie.getAkting());
    }

    private void validateJudul(String judul) {
        if (judul.isEmpty()) {
            throw new RuntimeException("Judul tidak boleh kosong");
        }

        if (judul.length() >= 30) {
            throw new RuntimeException("Judul tidak boleh lebih dari 30 karakter");
        }
    }

    private void validateAlur(double alur) {
        if (alur <= MINIMUM_VALUE) {
            throw new RuntimeException("Alur tidak boleh kosong");
        }
        if (alur > MAXIMUM_VALUE) {
            throw new RuntimeException("Alur tidak boleh lebih dari 5");
        }
    }

    private void validatePenokohan(double penokohan) {
        if (penokohan <= MINIMUM_VALUE) {
            throw new RuntimeException("Penokohan tidak boleh kosong");
        }
        if (penokohan > MAXIMUM_VALUE) {
            throw new RuntimeException("Penokohan tidak boleh lebih dari 5");
        }
    }

    private void validateAkting(double akting) {
        if (akting <= MINIMUM_VALUE) {
            throw new RuntimeException("Akting tidak boleh kosong");
        }
        if (akting > MAXIMUM_VALUE) {
            throw new RuntimeException("Akting tidak boleh lebih dari 5");
        }
    }
}
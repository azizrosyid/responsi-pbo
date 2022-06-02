package model;

import entity.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MovieModelImpl implements MovieModel {

    private final Connection connection;

    public MovieModelImpl(Connection connection) {
        this.connection = connection;
    }

    private Movie extractMovie(ResultSet rs) throws Exception {
        Movie movie = new Movie();
        movie.setJudul(rs.getString("judul"));
        movie.setAlur(rs.getDouble("alur"));
        movie.setPenokohan(rs.getDouble("penokohan"));
        movie.setAkting(rs.getDouble("akting"));
        return movie;
    }

    @Override
    public Movie[] getAllMovies() {
        String sql = "SELECT * FROM movie";
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Movie movie = this.extractMovie(resultSet);
                movies.add(movie);
            }
            return movies.toArray(new Movie[movies.size()]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie getMovie(String title) {
        String sql = "SELECT * FROM movie WHERE judul = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Movie movie = new Movie();
                movie.setJudul(resultSet.getString("judul"));
                movie.setAlur(resultSet.getDouble("alur"));
                movie.setPenokohan(resultSet.getDouble("penokohan"));
                movie.setAkting(resultSet.getDouble("akting"));
                return movie;
            }
            throw new RuntimeException("Movie not found");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addMovie(Movie movie) {
        String sql = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movie.getJudul());
            preparedStatement.setDouble(2, movie.getAlur());
            preparedStatement.setDouble(3, movie.getPenokohan());
            preparedStatement.setDouble(4, movie.getAkting());
            preparedStatement.setDouble(5, movie.getNilai());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new RuntimeException("Movie already exists");
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMovieByTitle(String title) {
        String sql = "DELETE FROM movie WHERE judul = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMovie(Movie movie) {
        String sql = "UPDATE movie SET alur = ?, penokohan = ?, akting = ?, nilai = ? WHERE judul = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, movie.getAlur());
            preparedStatement.setDouble(2, movie.getPenokohan());
            preparedStatement.setDouble(3, movie.getAkting());
            preparedStatement.setDouble(4, movie.getNilai());
            preparedStatement.setString(5, movie.getJudul());

            preparedStatement.executeUpdate();

            if (preparedStatement.getUpdateCount() == 0) {
                throw new RuntimeException("Movie not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

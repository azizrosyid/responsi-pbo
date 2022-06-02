import controller.MovieController;
import entity.Movie;
import model.MovieModel;
import model.MovieModelImpl;
import util.DatabaseUtil;
import view.MovieView;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection dbConnection = DatabaseUtil.getConnection();
        MovieModel movieModel = new MovieModelImpl(dbConnection);
        MovieView movieView = new MovieView();

        MovieController movieController = new MovieController(movieModel, movieView);
    }
}

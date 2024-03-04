package com.harrypotterapp.tesicnorpruebatecnica;

import com.harrypotterapp.tesicnorpruebatecnica.DB.HPMovie;
import com.harrypotterapp.tesicnorpruebatecnica.DB.HPMovieImplementation;
import com.harrypotterapp.tesicnorpruebatecnica.DB.OMDBAPICall;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import javafx.embed.swing.SwingFXUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;


public class HarryPotterMDBController implements Initializable {
    @FXML
    private ListView<String> movieList;
    @FXML
    private Label yearTag;
    @FXML
    private Label synopsisTag;
    @FXML
    private Rating userRating;
    @FXML
    private ScrollPane scrollSynopsis;
    @FXML
    private Label id;
    @FXML
    private Label ratingTag;
    @FXML
    private Label copyright;
    @FXML
    private Label title;
    @FXML
    private ImageView moviePoster;
    @FXML
    private Text textSynopsis;
    @FXML
    private Button updateButton;
    @FXML
    private Label updateTag;

    String currentMovie;
    Image image = null;
    HPMovieImplementation movieDao = new HPMovieImplementation();
    List<HPMovie> dbMovieList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //APICall and upload to the database
        List <HPMovie> APIList = OMDBAPICall.apiCall();
        for (HPMovie movie : APIList) {
            try {
                if (movieDao.getMovie(movie.getID()) == null) {
                    movieDao.add(movie);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        //Database Fetch
        List<String> titleList = new ArrayList<>();

        try {
            dbMovieList = movieDBFetch(movieDao);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (HPMovie movie : dbMovieList) {
                titleList.add(movie.getTitle());
        }
        movieList.getItems().addAll(titleList);

        //Movie Selection Listener
        movieList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                //Load Movie Data
                currentMovie = movieList.getSelectionModel().getSelectedItem();
                for (HPMovie movie : dbMovieList) {
                    if (movie.getTitle().equals(currentMovie)) {
                        title.setText(currentMovie);
                        URL imageUrl;
                        try {
                           imageUrl = new URL(movie.getImgUrl());
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                        BufferedImage poster;
                        try {
                            poster = ImageIO.read(imageUrl);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        image = SwingFXUtils.toFXImage(poster, null);
                        moviePoster.setImage(image);
                        yearTag.setText("Year of release: "+movie.getYear());
                        textSynopsis.setText(movie.getSinopsis());
                        id.setText("IMDB ID: "+movie.getID());
                        ratingTag.setText("User Rating: " + movie.getUserRating() + " / 5");
                        userRating.setRating(movie.getUserRating());
                    }
                }

                try {
                    dbMovieList = movieDBFetch(movieDao);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }

        });
        movieList.getSelectionModel().select(0);
        //Rating Selection Listener
        userRating.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updateButton.setVisible(true);
            }
        });
        //Update Button Press
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateTag.setVisible(true);
                userRating.setDisable(true);
                movieList.setMouseTransparent(true);
                int updatedRating = (int) userRating.getRating();
                String auxIDFull = id.getText();
                String auxID = auxIDFull.substring(9);
                try {
                    movieDao.updateRating(auxID,updatedRating);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                ratingTag.setText("User Rating: " + updatedRating + " / 5");
                PauseTransition espera = new PauseTransition(Duration.seconds(2));
                espera.setOnFinished(e -> updateSuccess());
                espera.play();
            }
        });
    }

    public void updateSuccess(){
        movieList.setMouseTransparent(false);
        userRating.setDisable(false);
        updateTag.setVisible(false);
        updateButton.setVisible(false);
    }

    public List<HPMovie> movieDBFetch (HPMovieImplementation movieDao) throws SQLException {
        return movieDao.getMovies();
    }
}
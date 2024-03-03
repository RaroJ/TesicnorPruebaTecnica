package com.harrypotterapp.tesicnorpruebatecnica;

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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Rating;

import java.net.URL;
import java.util.ResourceBundle;


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

    String[] movies = {"Harry Potter 1", "Harry Potter 2", "Harry Potter 3"};
    String currentMovie;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieList.getItems().addAll(movies);

        //Movie Selection Listener
        movieList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                //Load Movie Data
                currentMovie = movieList.getSelectionModel().getSelectedItem();
                title.setText(currentMovie);

            }

        });
        movieList.getSelectionModel().select(0);
        //Rating Selection Listener
        userRating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
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
                int updatedRating = (int)userRating.getRating();
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
}
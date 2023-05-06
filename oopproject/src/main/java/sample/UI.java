package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UI {
    @FXML
    private Button buttonProblem;

    public void problem(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("problems.fxml");
    }

    @FXML
    private Button buttonParking;

    public void parking(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("parking.fxml");
    }

    @FXML
    private Button buttonCard;

    public void card(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("card.fxml");
    }

    @FXML
    private Button buttonManage;

    public void manage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("manage.fxml");
    }

    @FXML
    private Button buttonStatistics;

    public void statistics(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("statistics.fxml");
    }

    @FXML
    private Button Logout;

    public void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("Are you sure to log out?");

        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get()==buttonTypeYes){
            Main m = new Main();
            m.changeScene("login.fxml");
        }else{
            return;
        }


    }
}
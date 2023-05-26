package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.function.Main;

import java.io.IOException;

public class Statistic extends AdminUI {
    @FXML
    private Button buttonCardStatistic;

    public void CardStatistic(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("VehicleStatistic.fxml");
    }

    @FXML
    private Button buttonVehicleStatistic;

    public void VehicleStatistic(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("CardStatistic.fxml");
    }
}
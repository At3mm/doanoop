package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sorce.Vehicles;

import java.net.URL;
import java.util.ResourceBundle;

public class parking extends UI implements Initializable {
    @FXML
    private Button add;
    @FXML
    private TextField Stt;
    @FXML
    private TextField BienSo;
    @FXML
    private TextField MaKhu;
    @FXML
    private TextField MaThe;
    @FXML
    private TextField LoaiXe;

    @FXML
    private TableView<Vehicles> table;
    @FXML
    private TableColumn<Vehicles, String> SttColumn;
    @FXML
    private TableColumn<Vehicles, String> bien_soColumn;
    @FXML
    private TableColumn<Vehicles, String> ma_khuColumn;
    @FXML
    private TableColumn<Vehicles, String> ma_theColumn;
    @FXML
    private TableColumn<Vehicles, String> loai_xeColumn;


    private ObservableList<Vehicles> vehicleslist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehicleslist = FXCollections.observableArrayList();
        SttColumn.setCellValueFactory(new PropertyValueFactory<Vehicles, String>("Stt"));
        bien_soColumn.setCellValueFactory(new PropertyValueFactory<Vehicles, String>("bien_so"));
        ma_khuColumn.setCellValueFactory(new PropertyValueFactory<Vehicles, String>("ma_khu"));
        ma_theColumn.setCellValueFactory(new PropertyValueFactory<Vehicles, String>("ma_the"));
        loai_xeColumn.setCellValueFactory(new PropertyValueFactory<Vehicles, String>("loai_xe"));
        table.setItems(vehicleslist);
    }

    public void add (ActionEvent e){
        Vehicles newvehicle = new Vehicles();
        newvehicle.setStt(Stt.getText());
        newvehicle.setBien_so(BienSo.getText());
        newvehicle.setMa_khu(MaKhu.getText());
        newvehicle.setMa_the(MaThe.getText());
        newvehicle.setLoai_xe(LoaiXe.getText());
        vehicleslist.add(newvehicle);
    }
}

package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TableView<GuiXe> table;

    @FXML
    private TableColumn<GuiXe, Integer> maColumn;
    @FXML
    private TableColumn<GuiXe, String> biensoColumn;
    @FXML
    private TableColumn<GuiXe, String> loaixeColumn;
    @FXML
    private TableColumn<GuiXe, Integer> khuColumn;

    @FXML
    private ObservableList<GuiXe> guixeList;

    @FXML
    private TextField maText;
    @FXML
    private TextField biensoText;
    @FXML
    private TextField loaixeText;
    @FXML
    private TextField khuText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guixeList = FXCollections.observableArrayList();

        maColumn.setCellValueFactory(new PropertyValueFactory<GuiXe, Integer>("Ma"));
        biensoColumn.setCellValueFactory(new PropertyValueFactory<GuiXe, String>("BienSo"));
        loaixeColumn.setCellValueFactory(new PropertyValueFactory<GuiXe, String>("LoaiXe"));
        khuColumn.setCellValueFactory(new PropertyValueFactory<GuiXe, Integer>("Khu"));
        table.setItems(guixeList);
    }

    public void add (ActionEvent e){
        GuiXe newGuiXe = new GuiXe();
        newGuiXe.setMa(Integer.parseInt(maText.getText()));
        newGuiXe.setBienso(biensoText.getText());
        newGuiXe.setLoaixe(loaixeText.getText());
        newGuiXe.setKhu(Integer.parseInt(khuText.getText()));
    }

    public void delete (ActionEvent e){
        GuiXe selected = table.getSelectionModel().getSelectedItem();
        guixeList.remove(selected);
    }
}

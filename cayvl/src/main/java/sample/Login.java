package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class Login {

    public Login() {

    }

    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        Main m = new Main();
        if (username.getText().equals("testing113") && password.getText().equals("123")) {
            wrongLogin.setText("Success! Please wait...");

            m.changeScene("AdminUI.fxml");

        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("Please enter data");

        }else if (username.getText().isEmpty()){
            wrongLogin.setText("Invalid Username");

        }else if (password.getText().isEmpty()) {
            wrongLogin.setText("Invalid Password");

        }else  {
            wrongLogin.setText("Wrong Username or Password.");
        }
    }
}
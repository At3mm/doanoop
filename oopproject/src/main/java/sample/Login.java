package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;

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

    private Connection connection;

    public void initialize() {
        try {
            // Kết nối với cơ sở dữ liệu MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/do_an_hdt", "root", "Viet1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void userLogin(ActionEvent event) {
        try {
            checkLogin();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkLogin() throws IOException, SQLException {
        Main m = new Main();

        // Kiểm tra dữ liệu đầu vào
        String query = "SELECT * FROM taikhoan WHERE TenTK = ? AND Matkhau = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username.getText());
        statement.setString(2, password.getText());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            wrongLogin.setText("Success! Please wait...");

            m.changeScene("mainUI.fxml");

        } else if (username.getText().isEmpty() || password.getText().isEmpty()) {
            wrongLogin.setText("Please enter data");

        }else if (username.getText().isEmpty()){
            wrongLogin.setText("Invalid Username");

        }else if (password.getText().isEmpty()) {
            wrongLogin.setText("Invalid Password");

        }else  {
            wrongLogin.setText("Wrong Username or Password.");
        }

        resultSet.close();
        statement.close();
    }
}
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.*;

public class Login {

    public Login() throws SQLException, IOException {

    }

    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private String usernameText;
    private String passwordText;
    private String tK;
    private String mK;

    public Login(String usernameText, String passwordText, String tK, String mK) throws SQLException, IOException {
        this.usernameText = usernameText;
        this.passwordText = passwordText;
        this.tK = tK;
        this.mK = mK;
    }

    public String getUsernameText() {
        return usernameText;
    }

    public void setUsernameText(String usernameText) {
        this.usernameText = usernameText;
    }

    public String getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(String passwordText) {
        this.passwordText = passwordText;
    }

    private Connection connection;

    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    public void initializeDatabaseConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/do_an_hdt", "root", "Viet1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void checkLogin() throws IOException {
        initializeDatabaseConnection();
        String usernameText = username.getText();
        String passwordText = password.getText();

        String adminQuery = "SELECT * FROM tai_khoan WHERE TenTK = ? AND Matkhau = ? AND LoaiTK = 'Admin'";
        String employeeQuery = "SELECT * FROM tai_khoan WHERE TenTK = ? AND Matkhau = ? AND LoaiTK = 'Nhân viên'";

        try (PreparedStatement adminStatement = connection.prepareStatement(adminQuery);
             PreparedStatement employeeStatement = connection.prepareStatement(employeeQuery)) {

            adminStatement.setString(1, usernameText);
            adminStatement.setString(2, passwordText);

            employeeStatement.setString(1, usernameText);
            employeeStatement.setString(2, passwordText);

            ResultSet adminResult = adminStatement.executeQuery();
            ResultSet employeeResult = employeeStatement.executeQuery();

            if (adminResult.next()) {
                Main m = new Main();
                m.changeScene("AdminUI.fxml");
            } else if (employeeResult.next()) {
                Main m = new Main();
                m.changeScene("EmployeeUI.fxml");
            } else if(username.getText().isEmpty() && password.getText().isEmpty()){
                wrongLogin.setVisible(true);
                wrongLogin.setText("Hãy nhập tài khoản và mật khẩu.");
            } else{
                wrongLogin.setVisible(true);
                wrongLogin.setText("Sai tài khoản hoặc mật khẩu.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
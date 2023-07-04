package sample.Employee;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Area extends EmployeeUI implements Initializable {

    @FXML
    private Label tongXeMay;
    @FXML
    private Label khuA;
    @FXML
    private Label khuB;
    @FXML
    private Label khuC;
    @FXML
    private Label khuD;
    @FXML
    private Label khuE;
    @FXML
    private Label tongXeDap;
    @FXML
    private Label khuF;
    @FXML
    private Label khuG;
    @FXML
    private Label khuH;
    @FXML
    private Label khuI;
    @FXML
    private Label khuJ;

    private Connection connection;

    public void initializeDatabaseConnection() {
        try {
            // Kết nối với cơ sở dữ liệu MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/do_an_hdt", "root", "Viet1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initializeDatabaseConnection();
            Statement statement = connection.createStatement();

            //Tổng xe máy
            // Chuẩn bị câu truy vấn SQL
            String query1 = "SELECT COUNT(LoaiXe) FROM nhan_tra_xe_ngay NATURAL JOIN the_ngay WHERE LoaiXe = 'Xe máy'";
            // Thực thi câu truy vấn và lấy kết quả
            ResultSet resultSet = statement.executeQuery(query1);
            // Lấy giá trị từ kết quả truy vấn
            int SM1 = 0;
            if (resultSet.next()) {
                SM1 = resultSet.getInt(1);
            }
            String query2 = "SELECT COUNT(LoaiXe) FROM nhan_tra_xe_thang NATURAL JOIN the_thang WHERE LoaiXe = 'Xe máy'";
            ResultSet resultSet1 = statement.executeQuery(query2);
            int SM2 = 0;
            if (resultSet1.next()) {
                SM2 = resultSet1.getInt(1);
            }
            int SM = SM1 + SM2;
            tongXeMay.setText(String.valueOf(SM));

            //Tổng xe đạp
            // Chuẩn bị câu truy vấn SQL
            String query3 = "SELECT COUNT(LoaiXe) FROM nhan_tra_xe_ngay NATURAL JOIN the_ngay WHERE LoaiXe = 'Xe đạp'";
            // Thực thi câu truy vấn và lấy kết quả
            ResultSet resultSet2 = statement.executeQuery(query3);
            // Lấy giá trị từ kết quả truy vấn
            int XD1 = 0;
            if (resultSet2.next()) {
                XD1 = resultSet2.getInt(1);
            }
            String query4 = "SELECT COUNT(LoaiXe) FROM nhan_tra_xe_thang NATURAL JOIN the_thang WHERE LoaiXe = 'Xe đạp'";
            ResultSet resultSet3 = statement.executeQuery(query4);
            int XD2 = 0;
            if (resultSet3.next()) {
                XD2 = resultSet3.getInt(1);
            }
            int XD = XD1 + XD2;
            tongXeDap.setText(String.valueOf(XD));

            //KhuA
            String queryA1 = "SELECT COUNT(Khu) FROM nhan_tra_xe_ngay WHERE Khu = 'A'";
            ResultSet resultSetA1 = statement.executeQuery(queryA1);
            int A1 = 0;
            if (resultSetA1.next()){
                A1 = resultSetA1.getInt(1);
            }
            String queryA2 = "SELECT COUNT(Khu) FROM nhan_tra_xe_thang WHERE Khu = 'A'";
            ResultSet resultSetA2 = statement.executeQuery(queryA2);
            int A2 = 0;
            if (resultSetA2.next()){
                A2 = resultSetA2.getInt(1);
            }
            int A = A1 + A2;
            khuA.setText(String.valueOf(A));

            //KhuB
            String queryB1 = "SELECT COUNT(Khu) FROM nhan_tra_xe_ngay WHERE Khu = 'B'";
            ResultSet resultSetB1 = statement.executeQuery(queryB1);
            int B1 = 0;
            if (resultSetB1.next()){
                B1 = resultSetB1.getInt(1);
            }
            String queryB2 = "SELECT COUNT(Khu) FROM nhan_tra_xe_thang WHERE Khu = 'B'";
            ResultSet resultSetB2 = statement.executeQuery(queryB2);
            int B2 = 0;
            if (resultSetB2.next()){
                B2 = resultSetB2.getInt(1);
            }
            int B = B1 + B2;
            khuB.setText(String.valueOf(B));

            //KhuC
            String queryC1 = "SELECT COUNT(Khu) FROM nhan_tra_xe_ngay WHERE Khu = 'C'";
            ResultSet resultSetC1 = statement.executeQuery(queryC1);
            int C1 = 0;
            if (resultSetC1.next()){
                C1 = resultSetC1.getInt(1);
            }
            String queryC2 = "SELECT COUNT(Khu) FROM nhan_tra_xe_thang WHERE Khu = 'C'";
            ResultSet resultSetC2 = statement.executeQuery(queryC2);
            int C2 = 0;
            if (resultSetC2.next()){
                C2 = resultSetC2.getInt(1);
            }
            int C = C1 + C2;
            khuC.setText(String.valueOf(C));

            //KhuD
            String queryD1 = "SELECT COUNT(Khu) FROM nhan_tra_xe_ngay WHERE Khu = 'D'";
            ResultSet resultSetD1 = statement.executeQuery(queryD1);
            int D1 = 0;
            if (resultSetD1.next()){
                D1 = resultSetD1.getInt(1);
            }
            String queryD2 = "SELECT COUNT(Khu) FROM nhan_tra_xe_thang WHERE Khu = 'D'";
            ResultSet resultSetD2 = statement.executeQuery(queryD2);
            int D2 = 0;
            if (resultSetD2.next()){
                D2 = resultSetD2.getInt(1);
            }
            int D = D1 + D2;
            khuD.setText(String.valueOf(D));

            //KhuE
            String queryE1 = "SELECT COUNT(Khu) FROM nhan_tra_xe_ngay WHERE Khu = 'E'";
            ResultSet resultSetE1 = statement.executeQuery(queryE1);
            int E1 = 0;
            if (resultSetE1.next()){
                E1 = resultSetE1.getInt(1);
            }
            String queryE2 = "SELECT COUNT(Khu) FROM nhan_tra_xe_thang WHERE Khu = 'E'";
            ResultSet resultSetE2 = statement.executeQuery(queryE2);
            int E2 = 0;
            if (resultSetE2.next()){
                E2 = resultSetE2.getInt(1);
            }
            int E = E1 + E2;
            khuE.setText(String.valueOf(E));

            //KhuF
            String queryF1 = "SELECT COUNT(Khu) FROM nhan_tra_xe_ngay WHERE Khu = 'F'";
            ResultSet resultSetF1 = statement.executeQuery(queryF1);
            int F1 = 0;
            if (resultSetF1.next()){
                F1 = resultSetF1.getInt(1);
            }
            String queryF2 = "SELECT COUNT(Khu) FROM nhan_tra_xe_thang WHERE Khu = 'F'";
            ResultSet resultSetF2 = statement.executeQuery(queryF2);
            int F2 = 0;
            if (resultSetF2.next()){
                F2 = resultSetF2.getInt(1);
            }
            int F = F1 + F2;
            khuF.setText(String.valueOf(F));

            //KhuG
            String queryG1 = "SELECT COUNT(Khu) FROM nhan_tra_xe_ngay WHERE Khu = 'G'";
            ResultSet resultSetG1 = statement.executeQuery(queryG1);
            int G1 = 0;
            if (resultSetG1.next()){
                G1 = resultSetG1.getInt(1);
            }
            String queryG2 = "SELECT COUNT(Khu) FROM nhan_tra_xe_thang WHERE Khu = 'G'";
            ResultSet resultSetG2 = statement.executeQuery(queryG2);
            int G2 = 0;
            if (resultSetG2.next()){
                G2 = resultSetG2.getInt(1);
            }
            int G = G1 + G2;
            khuG.setText(String.valueOf(G));

            //KhuH
            String queryH1 = "SELECT COUNT(Khu) FROM nhan_tra_xe_ngay WHERE Khu = 'H'";
            ResultSet resultSetH1 = statement.executeQuery(queryH1);
            int H1 = 0;
            if (resultSetH1.next()){
                H1 = resultSetH1.getInt(1);
            }
            String queryH2 = "SELECT COUNT(Khu) FROM nhan_tra_xe_thang WHERE Khu = 'H'";
            ResultSet resultSetH2 = statement.executeQuery(queryH2);
            int H2 = 0;
            if (resultSetH2.next()){
                H2 = resultSetH2.getInt(1);
            }
            int H = H1 + H2;
            khuH.setText(String.valueOf(H));

            //KhuI
            String queryI1 = "SELECT COUNT(Khu) FROM nhan_tra_xe_ngay WHERE Khu = 'I'";
            ResultSet resultSetI1 = statement.executeQuery(queryI1);
            int I1 = 0;
            if (resultSetI1.next()){
                I1 = resultSetI1.getInt(1);
            }
            String queryI2 = "SELECT COUNT(Khu) FROM nhan_tra_xe_thang WHERE Khu = 'I'";
            ResultSet resultSetI2 = statement.executeQuery(queryI2);
            int I2 = 0;
            if (resultSetI2.next()){
                I2 = resultSetI2.getInt(1);
            }
            int I = I1 + I2;
            khuI.setText(String.valueOf(I));

            //KhuJ
            String queryJ1 = "SELECT COUNT(Khu) FROM nhan_tra_xe_ngay WHERE Khu = 'J'";
            ResultSet resultSetJ1 = statement.executeQuery(queryJ1);
            int J1 = 0;
            if (resultSetJ1.next()){
                J1 = resultSetJ1.getInt(1);
            }
            String queryJ2 = "SELECT COUNT(Khu) FROM nhan_tra_xe_thang WHERE Khu = 'J'";
            ResultSet resultSetJ2 = statement.executeQuery(queryJ2);
            int J2 = 0;
            if (resultSetJ2.next()){
                J2 = resultSetJ2.getInt(1);
            }
            int J = J1 + J2;
            khuJ.setText(String.valueOf(J));

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}



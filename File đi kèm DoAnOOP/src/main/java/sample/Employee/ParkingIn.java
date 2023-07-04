package sample.Employee;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParkingIn extends EmployeeUI implements Initializable {

    @FXML
    private Label nhanNhanLabel;
    @FXML
    private ComboBox<String> loaiTheBox;
    @FXML
    private ComboBox<String> loaiXeBox;
    @FXML
    private ComboBox<String> maTheBox;
    @FXML
    private ComboBox<String> khuBox;
    @FXML
    private TextField bienSoXeText;
    @FXML
    private Button addVehicle;
    @FXML
    private Button lamMoi;

    private Connection connection;

    public void initializeDatabaseConnection() {
        try {
            // Kết nối với cơ sở dữ liệu MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/do_an_hdt", "root", "Viet1234");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeDatabaseConnection();

        //Thời gian thực
        LocalDate currentDate = LocalDate.now();
        nhanNhanLabel.setText(currentDate.toString());

        //Giới hạn kí tự
        final int max1 = 10;
        bienSoXeText.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if(bienSoXeText.getText().length() >= max1){
                keyEvent.consume();
            }
        });

        // Thêm các lựa chọn vào ComboBox
        ObservableList<String> options1 = FXCollections.observableArrayList("Xe máy", "Xe đạp");
        loaiXeBox.setItems(options1);
        ObservableList<String> options = FXCollections.observableArrayList("Thẻ ngày", "Thẻ tháng");
        loaiTheBox.setItems(options);

        loaiXeBox.setOnAction(event ->{
            bienSoXeText.setEditable(true);
            String selectedValue = loaiXeBox.getValue() != null ? loaiXeBox.getValue() : null;
            if ("Xe máy".equals(selectedValue)) {
                ObservableList<String> option = FXCollections.observableArrayList("A","B","C","D","E");
                khuBox.setItems(option);
                //Kiểm tra giới hạn của khu vực
                checkArea1();

            } else if("Xe đạp".equals(selectedValue)){
                ObservableList<String> option = FXCollections.observableArrayList("F","G","H","I","J");
                khuBox.setItems(option);
                //Kiểm tra giới hạn của khu vực
                checkArea2();
            }
        });

        loaiTheBox.setOnAction(event -> {
            bienSoXeText.setEditable(true);
            String selectedValue = loaiTheBox.getValue() != null ? loaiTheBox.getValue() : null;
            if ("Thẻ ngày".equals(selectedValue)) {
                try {
                    // Truy vấn dữ liệu từ cơ sở dữ liệu
                    String query = "SELECT MaTheNgay, LoaiThe FROM the_ngay " +
                            "WHERE (MaTheNgay IS NOT NULL) AND (LoaiThe IS NULL)";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    // Khởi tạo danh sách options
                    ObservableList<String> options3 = FXCollections.observableArrayList();

                    // Lấy dữ liệu từ ResultSet và thêm vào danh sách
                    while (resultSet.next()) {
                        options3.add(resultSet.getString("MaTheNgay"));
                    }

                    // Đưa danh sách options vào ComboBox
                    maTheBox.setItems(options3);

                    // Xoá dữ liệu trong loaiXeBox
                    loaiXeBox.setValue(null);

                    // Xoá dữ liệu trong bienSoXeText
                    bienSoXeText.clear();

                    statement.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
                }

            } else if ("Thẻ tháng".equals(selectedValue)) {
                try {
                    // Xoá dữ liệu trong maTheBox
                    maTheBox.getItems().clear();
                    // Đặt giá trị của loaiXeBox về null
                    loaiXeBox.setValue(null);
                    // Xoá dữ liệu trong bienSoXeText
                    bienSoXeText.clear();

                    // Truy vấn dữ liệu từ cơ sở dữ liệu
                    String query = "SELECT MaTheThang FROM the_thang WHERE Do = '0'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    // Khởi tạo danh sách options
                    ObservableList<String> options4 = FXCollections.observableArrayList();

                    // Lấy dữ liệu từ ResultSet và thêm vào danh sách
                    while (resultSet.next()) {
                        options4.add(resultSet.getString("MaTheThang"));
                    }

                    // Đưa danh sách options vào ComboBox
                    maTheBox.setItems(options4);

                    statement.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
                }
            }
        });

        maTheBox.setOnAction(event -> {
            String selectedValue = maTheBox.getValue() != null ? maTheBox.getValue() : null;
            try {
                ObservableList<String> options2 = FXCollections.observableArrayList("Xe máy", "Xe đạp");
                loaiXeBox.setItems(options2);
                String query = "SELECT LoaiXe, BienSoXe FROM the_thang WHERE MaTheThang = ?";
                PreparedStatement statement1 = connection.prepareStatement(query);
                statement1.setString(1, selectedValue);
                ResultSet resultSet = statement1.executeQuery();

                if (resultSet.next()) {
                    String loaiXe = resultSet.getString("LoaiXe");
                    String bienSoXe = resultSet.getString("BienSoXe");
                    bienSoXeText.setText(bienSoXe);
                    bienSoXeText.setEditable(false);

                    // Lấy danh sách các mục hiện tại trong ComboBox
                    ObservableList<String> items = loaiXeBox.getItems();

                    // Tạo một danh sách mới để lưu trữ các mục khớp (nếu có)
                     List <String> matchingItems = new ArrayList<>();

                    // Duyệt qua các mục và kiểm tra khớp
                    for (String item : items) {
                        if (item.equals(loaiXe)) {
                            matchingItems.add(item);
                        }
                    }

                    // Xóa sạch ComboBox và chỉ thêm lại các mục khớp
                    loaiXeBox.getItems().clear();
                    loaiXeBox.getItems().addAll(matchingItems);
                    loaiXeBox.setValue(loaiXe);
                }
                statement1.close();

            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
            }
        });
    }

    public void checkArea2(){
        try {
            ObservableList<String> option = FXCollections.observableArrayList("F","G","H","I","J");
            Statement statement = connection.createStatement();

            //Khu F
            String queryF = "SELECT COUNT(Khu) AS KhuF FROM (SELECT Khu FROM nhan_tra_xe_ngay WHERE Khu = 'F' " +
                    "UNION ALL SELECT Khu FROM nhan_tra_xe_thang WHERE Khu = 'F') AS UnionTable";
            ResultSet resultSetF = statement.executeQuery(queryF);
            int F = 0;
            if (resultSetF.next()){
                F = resultSetF.getInt(1);
            }
            if (F >= 20){
                option.remove("F");
                khuBox.setItems(option);
            }
            resultSetF.close();

            //Khu G
            String queryG = "SELECT COUNT(Khu) AS KhuG FROM (SELECT Khu FROM nhan_tra_xe_ngay WHERE Khu = 'G' " +
                    "UNION ALL SELECT Khu FROM nhan_tra_xe_thang WHERE Khu = 'G') AS UnionTable";
            ResultSet resultSetG = statement.executeQuery(queryG);
            int G = 0;
            if (resultSetG.next()){
                G = resultSetG.getInt(1);
            }
            if (G >= 20){
                option.remove("G");
                khuBox.setItems(option);
            }
            resultSetG.close();

            //Khu H
            String queryH = "SELECT COUNT(Khu) AS KhuH FROM (SELECT Khu FROM nhan_tra_xe_ngay WHERE Khu = 'H' " +
                    "UNION ALL SELECT Khu FROM nhan_tra_xe_thang WHERE Khu = 'H') AS UnionTable";
            ResultSet resultSetH = statement.executeQuery(queryH);
            int H = 0;
            if (resultSetH.next()){
                H = resultSetH.getInt(1);
            }
            if (H >= 20){
                option.remove("H");
                khuBox.setItems(option);
            }
            resultSetH.close();

            //Khu I
            String queryI = "SELECT COUNT(Khu) AS KhuI FROM (SELECT Khu FROM nhan_tra_xe_ngay WHERE Khu = 'I' " +
                    "UNION ALL SELECT Khu FROM nhan_tra_xe_thang WHERE Khu = 'I') AS UnionTable";
            ResultSet resultSetI = statement.executeQuery(queryI);
            int I = 0;
            if (resultSetI.next()){
                I = resultSetI.getInt(1);
            }
            if (I >= 20){
                option.remove("I");
                khuBox.setItems(option);
            }

            //Khu J
            String queryJ = "SELECT COUNT(Khu) AS KhuJ FROM (SELECT Khu FROM nhan_tra_xe_ngay WHERE Khu = 'J' " +
                    "UNION ALL SELECT Khu FROM nhan_tra_xe_thang WHERE Khu = 'J') AS UnionTable";
            ResultSet resultSetJ = statement.executeQuery(queryJ);
            int J = 0;
            if (resultSetJ.next()){
                J = resultSetJ.getInt(1);
            }
            if (J >= 20){
                option.remove("J");
                khuBox.setItems(option);
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void checkArea1(){
        try {
            //Khu A
            ObservableList<String> option = FXCollections.observableArrayList("A","B","C","D","E");
            Statement statement = connection.createStatement();

            String queryA = "SELECT COUNT(Khu) AS KhuA FROM (SELECT Khu FROM nhan_tra_xe_ngay WHERE Khu = 'A' " +
                    "UNION ALL SELECT Khu FROM nhan_tra_xe_thang WHERE Khu = 'A') AS UnionTable";
            ResultSet resultSetA = statement.executeQuery(queryA);
            int A = 0;
            if (resultSetA.next()){
                A = resultSetA.getInt(1);
            }
            if (A >= 20){
                option.remove("A");
                khuBox.setItems(option);
            }
            resultSetA.close();

            //Khu B
            String queryB = "SELECT COUNT(Khu) AS KhuB FROM (SELECT Khu FROM nhan_tra_xe_ngay WHERE Khu = 'B' " +
                    "UNION ALL SELECT Khu FROM nhan_tra_xe_thang WHERE Khu = 'B') AS UnionTable";
            ResultSet resultSetB = statement.executeQuery(queryB);
            int B = 0;
            if (resultSetB.next()){
                B = resultSetB.getInt(1);
            }
            if (B >= 20){
                option.remove("B");
                khuBox.setItems(option);
            }
            resultSetB.close();

            //Khu C
            String queryC = "SELECT COUNT(Khu) AS KhuC FROM (SELECT Khu FROM nhan_tra_xe_ngay WHERE Khu = 'C' " +
                    "UNION ALL SELECT Khu FROM nhan_tra_xe_thang WHERE Khu = 'C') AS UnionTable";
            ResultSet resultSetC = statement.executeQuery(queryC);
            int C = 0;
            if (resultSetC.next()){
                C = resultSetC.getInt(1);
            }
            if (C >= 20){
                option.remove("C");
                khuBox.setItems(option);
            }
            resultSetC.close();

            //Khu D
            String queryD = "SELECT COUNT(Khu) AS KhuD FROM (SELECT Khu FROM nhan_tra_xe_ngay WHERE Khu = 'D' " +
                    "UNION ALL SELECT Khu FROM nhan_tra_xe_thang WHERE Khu = 'D') AS UnionTable";
            ResultSet resultSetD = statement.executeQuery(queryD);
            int D = 0;
            if (resultSetD.next()){
                D = resultSetD.getInt(1);
            }
            if (D >= 20){
                option.remove("D");
                khuBox.setItems(option);
            }
            resultSetD.close();

            //Khu E
            String queryE = "SELECT COUNT(Khu) AS KhuE FROM (SELECT Khu FROM nhan_tra_xe_ngay WHERE Khu = 'E' " +
                    "UNION ALL SELECT Khu FROM nhan_tra_xe_thang WHERE Khu = 'E') AS UnionTable";
            ResultSet resultSetE = statement.executeQuery(queryE);
            int E = 0;
            if (resultSetE.next()){
                E = resultSetE.getInt(1);
            }
            if (E >= 20){
                option.remove("E");
                khuBox.setItems(option);
            }
            resultSetE.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void NhanXe() {
        String loaiThe = loaiTheBox.getValue() != null ? loaiTheBox.getValue() : null;
        String loaiXe = loaiXeBox.getValue() != null ? loaiXeBox.getValue() : null;
        String maThe = maTheBox.getValue() != null ? maTheBox.getValue() : null;
        String khu = khuBox.getValue() != null ? khuBox.getValue() : null;
        String bienSoXe = bienSoXeText.getText();
        String ngayNhan = nhanNhanLabel.getText();

        // Kiểm tra dữ liệu đầu vào
        if (loaiThe == null || loaiXe == null || bienSoXe.isEmpty() || maThe == null || khu == null ) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            return;
        }
        try {
           if (loaiThe.equals("Thẻ ngày")) {
               //Kiểm tra sự tồn tại của biển số xe
               String query3 = "SELECT BienSoXe FROM the_ngay WHERE MaTheNgay != ? AND BienSoXe = ?";
               PreparedStatement checkStatement = connection.prepareStatement(query3);
               checkStatement.setString(1,maThe);
               checkStatement.setString(2,bienSoXe);
               ResultSet resultSet3 = checkStatement.executeQuery();
               if (resultSet3.next()){
                   showAlert(Alert.AlertType.ERROR, "Lỗi", "Xe đang đỗ trong bãi", "Hãy chọn một biển số xe khác. ");
                   return;
               }

               checkStatement.close();

                // Chuẩn bị câu truy vấn SQL
                String query = "INSERT INTO nhan_tra_xe_ngay (MaTheNgay, NgayNhan, GioNhan, Khu) VALUES (?,?, CURTIME(), ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, maThe);
                statement.setString(2, ngayNhan);
                statement.setString(3, khu);

                String query1 = "INSERT INTO thong_ke_the_ngay (MaThe, NgayNhan, GioNhan, Khu, LoaiXe, BienSoXe) VALUES (?, ?, CURTIME(), ?, ?, ?)";
                PreparedStatement statement1 = connection.prepareStatement(query1);
                statement1.setString(1, maThe);
                statement1.setString(2, ngayNhan);
                statement1.setString(3, khu);
                statement1.setString(4, loaiXe);
                statement1.setString(5, bienSoXe);

                String query2 = "UPDATE the_ngay SET LoaiThe = ?, LoaiXe = ?, BienSoXe = ? WHERE MaTheNgay = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1, loaiThe);
                statement2.setString(2, loaiXe);
                statement2.setString(3, bienSoXe);
                statement2.setString(4, maThe);

                // Thực thi câu truy vấn
                int rowsAffected = statement.executeUpdate();
                int rowsAffected1 = statement1.executeUpdate();
                int rowsAffected2 = statement2.executeUpdate();
                if (rowsAffected > 0 && rowsAffected1 > 0 && rowsAffected2 > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thành công !", "Dữ liệu đã được thêm vào cơ sở dữ liệu ");
                    clearFields();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Thông báo", "Thêm dữ liệu thất bại !", "Có lỗi xảy ra trong quá trình thực hiện");
                }

                statement.close();
                statement1.close();
                statement2.close();

           }
           else{
                // Chuẩn bị câu truy vấn SQL
                String query = "INSERT INTO nhan_tra_xe_thang (MaTheThang, NgayNhan, GioNhan, Khu) VALUES (?,?, CURTIME(), ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, maThe);
                statement.setString(2, ngayNhan);
                statement.setString(3, khu);

                String query1 = "INSERT INTO thong_ke_the_thang (MaThe, NgayNhan, GioNhan, Khu, LoaiXe, BienSoXe) VALUES (?, ?, CURTIME(), ?, ?, ?)";
                PreparedStatement statement1 = connection.prepareStatement(query1);
                statement1.setString(1, maThe);
                statement1.setString(2, ngayNhan);
                statement1.setString(3, khu);
                statement1.setString(4, loaiXe);
                statement1.setString(5, bienSoXe);

                String query2 = "UPDATE the_thang SET Do = '1' WHERE MaTheThang = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1,maThe);

               // Thực thi câu truy vấn
               int rowsAffected = statement.executeUpdate();
               int rowsAffected1 = statement1.executeUpdate();
               int rowsAffected2 = statement2.executeUpdate();
               if (rowsAffected > 0 && rowsAffected1 > 0 && rowsAffected2 > 0 ) {
                   showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thành công !", "Dữ liệu đã được thêm vào cơ sở dữ liệu ");
                   clearFields();
               } else {
                   showAlert(Alert.AlertType.ERROR, "Thông báo", "Thêm dữ liệu thất bại !", "Có lỗi xảy ra trong quá trình thực hiện");
               }

               statement.close();
               statement1.close();
               statement2.close();
           }

        } catch(SQLException e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void reLoadData() {
        loaiTheBox.setValue(null);
        maTheBox.getItems().clear();
        loaiXeBox.setValue(null);
        khuBox.getItems().clear();
        bienSoXeText.clear();
        bienSoXeText.setEditable(true);

    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        loaiTheBox.setValue(null);
        maTheBox.getItems().clear();
        loaiXeBox.setValue(null);
        khuBox.getItems().clear();
        bienSoXeText.clear();
        bienSoXeText.setEditable(true);

    }
}

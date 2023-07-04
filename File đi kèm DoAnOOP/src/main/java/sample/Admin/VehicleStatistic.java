package sample.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;

public class VehicleStatistic extends AdminUI implements Initializable{

    @FXML
    private DatePicker ngayNhanPicker;
    @FXML
    private DatePicker ngayTraPicker;
    @FXML
    private Button searchButton;
    @FXML
    private Label soTienLabel;
    @FXML
    private Label soLuotLabel;
    @FXML
    private Button reLoad;
    @FXML
    private TableView<VehicleStatistic> tableView;
    @FXML
    private TableColumn<String, VehicleStatistic> maTheColumn;
    @FXML
    private TableColumn<String, VehicleStatistic> loaiXeColumn;
    @FXML
    private TableColumn<String, VehicleStatistic> bienSoXeColumn;
    @FXML
    private TableColumn<String, VehicleStatistic> ngayNhanColumn;
    @FXML
    private TableColumn<String, VehicleStatistic> ngayTraColumn;
    @FXML
    private TableColumn<String, VehicleStatistic> khuColumn;
    @FXML
    private TableColumn<String, VehicleStatistic> soTienColumn;

    private ObservableList<VehicleStatistic> vehicleStatisticsList;
    private Connection connection;
    private String maThe;
    private String loaiXe;
    private String bienSoXe;
    private String khu;
    private String soTien;
    private Date ngayNhan;
    private Date ngayTra;

    public VehicleStatistic(String maThe, String loaiXe, String bienSoXe, Date ngayNhan, Date ngayTra, String khu, String soTien) {
        this.maThe = maThe;
        this.loaiXe = loaiXe;
        this.bienSoXe = bienSoXe;
        this.khu = khu;
        this.soTien = soTien;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
    }

    public VehicleStatistic(){

    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public String getKhu() {
        return khu;
    }

    public void setKhu(String khu) {
        this.khu = khu;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public void initializeDatabaseConnection() {
        try {
            // Kết nối với cơ sở dữ liệu MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/do_an_hdt", "root", "Viet1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromDataBase(){
    try {
        // Truy vấn dữ liệu từ cơ sở dữ liệu
        String query = "SELECT * FROM thong_ke_the_thang natural join loai_xe_thang WHERE NgayTra " +
                "UNION SELECT * FROM thong_ke_the_ngay natural join loai_xe_ngay WHERE NgayTra ";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Xóa dữ liệu cũ trong danh sách
        vehicleStatisticsList.clear();

        // Lấy dữ liệu từ ResultSet và thêm vào danh sách
        while (resultSet.next()) {
            String maThe = resultSet.getString("MaThe");
            String loaiXe = resultSet.getString("LoaiXe");
            String bienSo = resultSet.getString("BienSoXe");
            Date ngayNhan = resultSet.getDate("NgayNhan");
            Date ngayTra = resultSet.getDate("NgayTra");
            String khu = resultSet.getString("Khu");
            String soTien = resultSet.getString("SoTien");

            // Tạo một đối tượng card từ dữ liệu tìm thấy
            VehicleStatistic vs = new VehicleStatistic(maThe, loaiXe, bienSo, ngayNhan, ngayTra, khu, soTien);

            // Thêm đối tượng card vào danh sách kết quả
            vehicleStatisticsList.add(vs);
            }
        } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeDatabaseConnection();
        tongTien();
        tongLuot();
        // Khởi tạo danh sách và gán cho bảng
        vehicleStatisticsList = FXCollections.observableArrayList();

        // Thiết lập giá trị cột dữ liệu cho bảng
        maTheColumn.setCellValueFactory(new PropertyValueFactory<>("maThe"));
        loaiXeColumn.setCellValueFactory(new PropertyValueFactory<>("loaiXe"));
        bienSoXeColumn.setCellValueFactory(new PropertyValueFactory<>("bienSoXe"));
        ngayNhanColumn.setCellValueFactory(new PropertyValueFactory<>("ngayNhan"));
        ngayTraColumn.setCellValueFactory(new PropertyValueFactory<>("ngayTra"));
        khuColumn.setCellValueFactory(new PropertyValueFactory<>("khu"));
        soTienColumn.setCellValueFactory(new PropertyValueFactory<>("soTien"));

        // Gán danh sách cho bảng
        tableView.setItems(vehicleStatisticsList);
        loadDataFromDataBase();
    }

    public void tongTien(){
        try {
            String query = "SELECT SUM(SoTien) AS TongTien FROM (SELECT SoTien FROM thong_ke_the_thang NATURAL JOIN loai_xe_thang WHERE NgayTra " +
                    "UNION ALL SELECT SoTien FROM thong_ke_the_ngay NATURAL JOIN loai_xe_ngay WHERE NgayTra) AS UnionTable ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int T = 0;
            if (resultSet.next()) {
                T = resultSet.getInt(1);
            }
            soTienLabel.setText(String.valueOf(T));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tongLuot(){
        try {
            String query = "SELECT COUNT(MaThe) AS TongLuot FROM (SELECT MaThe FROM thong_ke_the_thang NATURAL JOIN loai_xe_thang WHERE NgayTra " +
                    "UNION ALL SELECT MaThe FROM thong_ke_the_ngay NATURAL JOIN loai_xe_ngay WHERE NgayTra) AS UnionTable ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int T = 0;
            if (resultSet.next()) {
                T = resultSet.getInt(1);
            }
            soLuotLabel.setText(String.valueOf(T));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchCard() {
        LocalDate ngayNhan = ngayNhanPicker.getValue();
        LocalDate ngayTra = ngayTraPicker.getValue();

        // Kiểm tra dữ liệu đầu vào
        if (ngayNhanPicker.getValue() == null || ngayTraPicker.getValue() == null ) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDataBase();
            return;
        }

        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "SELECT * FROM thong_ke_the_ngay NATURAL JOIN loai_xe_ngay WHERE NgayTra >= ? AND NgayTra <= ? " +
                    "UNION SELECT * FROM thong_ke_the_thang NATURAL JOIN loai_xe_thang WHERE NgayTra >= ? AND NgayTra <= ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf((ngayNhan)));
            statement.setString(2, String.valueOf((ngayTra)));
            statement.setString(3, String.valueOf((ngayNhan)));
            statement.setString(4, String.valueOf((ngayTra)));

            // Thực thi câu truy vấn SQL
            ResultSet resultSet = statement.executeQuery();

            // Tạo một danh sách mới để lưu trữ kết quả
            List<VehicleStatistic> searchResults = new ArrayList<>();

            // Kiểm tra từng dòng dữ liệu trong ResultSet
            while (resultSet.next()) {
                String resultMaThe = resultSet.getString("MaThe");
                String resultLoaiXe = resultSet.getString("LoaiXe");
                String resultBienSo = resultSet.getString("BienSoXe");
                Date resultNgayNhan = resultSet.getDate("NgayNhan");
                Date resultNgayTra = resultSet.getDate("NgayTra");
                String resultKhu = resultSet.getString("Khu");
                String resultSoTien = resultSet.getString("SoTien");

                // Tạo một đối tượng từ dữ liệu tìm thấy
                VehicleStatistic vs = new VehicleStatistic(resultMaThe, resultLoaiXe, resultBienSo, resultNgayNhan, resultNgayTra, resultKhu, resultSoTien);

                // Thêm đối tượn vào danh sách kết quả
                searchResults.add(vs);
            }
            if (!searchResults.isEmpty()) {
                // Xóa dữ liệu cũ trong bảng
                tableView.getItems().clear();

                // Thêm dữ liệu mới vào bảng
                tableView.getItems().addAll(searchResults);

            } else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Lỗi", "Không tìm thấy dữ liệu trùng khớp !");
                tableView.getItems().clear();
            }

            //Update tongTien
            String query1 = "SELECT SUM(SoTien) AS TongTien FROM (SELECT SoTien FROM thong_ke_the_thang NATURAL JOIN loai_xe_thang WHERE NgayTra >= ? AND NgayTra <= ? " +
                    "UNION ALL SELECT SoTien FROM thong_ke_the_ngay NATURAL JOIN loai_xe_ngay WHERE NgayTra >= ? AND NgayTra <= ?) AS UnionTable ";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, String.valueOf((ngayNhan)));
            statement1.setString(2, String.valueOf((ngayTra)));
            statement1.setString(3, String.valueOf((ngayNhan)));
            statement1.setString(4, String.valueOf((ngayTra)));
            ResultSet resultSet1 = statement1.executeQuery();
            int M = 0;
            if (resultSet1.next()) {
                M = resultSet1.getInt(1);
            }
            soTienLabel.setText(String.valueOf(M));

            //Update tongLuot
            String query2 = "SELECT COUNT(MaThe) AS TongLuot FROM (SELECT MaThe FROM thong_ke_the_thang NATURAL JOIN loai_xe_thang WHERE NgayTra >= ? AND NgayTra <= ?" +
                    "UNION ALL SELECT MaThe FROM thong_ke_the_ngay NATURAL JOIN loai_xe_ngay WHERE NgayTra >= ? AND NgayTra <= ?) AS UnionTable ";
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, String.valueOf((ngayNhan)));
            statement2.setString(2, String.valueOf((ngayTra)));
            statement2.setString(3, String.valueOf((ngayNhan)));
            statement2.setString(4, String.valueOf((ngayTra)));
            ResultSet resultSet2 = statement2.executeQuery();
            int N = 0;
            if (resultSet2.next()) {
                N = resultSet2.getInt(1);
            }
            soLuotLabel.setText(String.valueOf(N));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void reLoad(){
        ngayNhanPicker.setValue(null);
        ngayTraPicker.setValue(null);
        loadDataFromDataBase();
        tongLuot();
        tongTien();
    }
}

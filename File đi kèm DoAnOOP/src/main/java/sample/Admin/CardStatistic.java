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


public class CardStatistic extends AdminUI implements Initializable{

    @FXML
    private Button searchButton;
    @FXML
    private Button reloadButton;
    @FXML
    private DatePicker ngayBD;
    @FXML
    private DatePicker ngayKT;
    @FXML
    private ComboBox<String> loaiXeBox;
    @FXML
    private TableView<CardStatistic> tableView;
    @FXML
    private TableColumn<CardStatistic, String> maTheColumn;
    @FXML
    private TableColumn<CardStatistic, String> tenKHColumn;
    @FXML
    private TableColumn<CardStatistic, String> loaiXeColumn;
    @FXML
    private TableColumn<CardStatistic, String> bienSoColumn;
    @FXML
    private TableColumn<CardStatistic, String> SDTColumn;
    @FXML
    private TableColumn<CardStatistic, String> ngayDangKyColumn;
    @FXML
    private TableColumn<CardStatistic, String> soTienColumn;
    @FXML
    private Label soTienLabel;
    @FXML
    private Label soVeLabel;

    private ObservableList<CardStatistic> cardStatisticList;
    private Connection connection;
    private String maThe;
    private String tenKH;
    private String loaiXe;
    private String bienSo;
    private String SDT;
    private LocalDate ngayDangKy;
    private String soTien;

    public CardStatistic(String maThe, String tenKH, String loaiXe, String bienSo, String SDT, LocalDate ngayDangKy, String soTien) {
        this.maThe = maThe;
        this.tenKH = tenKH;
        this.loaiXe = loaiXe;
        this.bienSo = bienSo;
        this.SDT = SDT;
        this.ngayDangKy = ngayDangKy;
        this.soTien = soTien;
    }

    public CardStatistic(){

    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public LocalDate getNgayDKy() {
        return ngayDangKy;
    }

    public void setNgayDKy(LocalDate ngayDKy) {
        this.ngayDangKy = ngayDKy;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public void initializeDatabaseConnection() {
        try {
            // Kết nối với cơ sở dữ liệu MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/do_an_hdt", "root", "Viet1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void loadDataFromDatabase() {
        try {
            // Truy vấn dữ liệu từ cơ sở dữ liệu
            String query = "SELECT * FROM the_thang NATURAL JOIN loai_xe_the_thang";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Xóa dữ liệu cũ trong danh sách
            cardStatisticList.clear();

            // Lấy dữ liệu từ ResultSet và thêm vào danh sách
            while (resultSet.next()) {
                String maThe = resultSet.getString("MaTheThang");
                String tenKH = resultSet.getString("TenKH");
                String loaiXe = resultSet.getString("LoaiXe");
                String bienSo = resultSet.getString("BienSoXe");
                String SDT = resultSet.getString("SDT");
                LocalDate ngayDKy = resultSet.getDate("NgayDangKi").toLocalDate();
                String soTien = resultSet.getString("SoTien");

                // Tạo một đối tượng card từ dữ liệu tìm thấy
                CardStatistic cs = new CardStatistic(maThe, tenKH, loaiXe, bienSo, SDT, ngayDKy, soTien);

                // Thêm đối tượng card vào danh sách kết quả
                cardStatisticList.add(cs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeDatabaseConnection();
        tongTien();
        tongVe();

        // Thêm các lựa chọn vào ComboBox
        ObservableList<String> options = FXCollections.observableArrayList("Xe máy", "Xe đạp");
        loaiXeBox.setItems(options);

        // Khởi tạo danh sách và gán cho bảng
        cardStatisticList = FXCollections.observableArrayList();

        // Thiết lập giá trị cột dữ liệu cho bảng
        maTheColumn.setCellValueFactory(new PropertyValueFactory<>("maThe"));
        tenKHColumn.setCellValueFactory(new PropertyValueFactory<>("tenKH"));
        loaiXeColumn.setCellValueFactory(new PropertyValueFactory<>("loaiXe"));
        bienSoColumn.setCellValueFactory(new PropertyValueFactory<>("bienSo"));
        SDTColumn.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        ngayDangKyColumn.setCellValueFactory(new PropertyValueFactory<>("ngayDKy"));
        soTienColumn.setCellValueFactory(new PropertyValueFactory<>("soTien"));

        // Gán danh sách cho bảng
        tableView.setItems(cardStatisticList);
        loadDataFromDatabase();
    }
    public void tongTien(){
        try {
            String query = "SELECT SUM(SoTien) FROM the_thang NATURAL JOIN loai_xe_the_thang ";
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

    public void tongVe(){
        try {
            String query = "SELECT COUNT(MaTheThang) FROM the_thang NATURAL JOIN loai_xe_the_thang ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int T = 0;
            if (resultSet.next()) {
                T = resultSet.getInt(1);
            }
            soVeLabel.setText(String.valueOf(T));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchCard() {
        String loaiXe = loaiXeBox.getValue() != null ? loaiXeBox.getValue().toString() : null;

        // Kiểm tra dữ liệu đầu vào
        if (ngayBD.getValue() == null || ngayKT.getValue() == null || loaiXeBox.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDatabase();
            return;
        }

        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "SELECT * FROM the_thang NATURAL JOIN loai_xe_the_thang WHERE NgayDangKi >= ? AND NgayDangKi <= ? AND LoaiXe = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf((ngayBD.getValue())));
            statement.setString(2, String.valueOf((ngayKT.getValue())));
            statement.setString(3, String.valueOf(loaiXe));

            // Thực thi câu truy vấn SQL
            ResultSet resultSet = statement.executeQuery();

            // Tạo một danh sách mới để lưu trữ kết quả
            List<CardStatistic> searchResults = new ArrayList<>();

            // Kiểm tra từng dòng dữ liệu trong ResultSet
            while (resultSet.next()) {
                String resultMaThe = resultSet.getString("MaTheThang");
                String resultTenKH = resultSet.getString("TenKH");
                String resultLoaiXe = resultSet.getString("LoaiXe");
                String resultBienSo = resultSet.getString("BienSoXe");
                String resultSDT = resultSet.getString("SDT");
                LocalDate resultNgayDKy = resultSet.getDate("NgayDangKi").toLocalDate();
                String resultSoTien = resultSet.getString("SoTien");

                // Tạo một đối tượng từ dữ liệu tìm thấy
                CardStatistic cs = new CardStatistic(resultMaThe, resultTenKH, resultLoaiXe, resultBienSo, resultSDT, resultNgayDKy, resultSoTien);

                // Thêm đối tượn vào danh sách kết quả
                searchResults.add(cs);
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
            String query1 = "SELECT SUM(SoTien) FROM the_thang NATURAL JOIN loai_xe_the_thang WHERE NgayDangKi >= ? AND NgayDangKi <= ? AND LoaiXe = ?";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, String.valueOf((ngayBD.getValue())));
            statement1.setString(2, String.valueOf((ngayKT.getValue())));
            statement1.setString(3, String.valueOf(loaiXe));
            ResultSet resultSet1 = statement1.executeQuery();
            int M = 0;
            if (resultSet1.next()) {
                M = resultSet1.getInt(1);
            }
            soTienLabel.setText(String.valueOf(M));

            //Update tongVe
            String query2 = "SELECT COUNT(MaTheThang) FROM the_thang NATURAL JOIN loai_xe_the_thang WHERE NgayDangKi >= ? AND NgayDangKi <= ? AND LoaiXe = ?";
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, String.valueOf((ngayBD.getValue())));
            statement2.setString(2, String.valueOf((ngayKT.getValue())));
            statement2.setString(3, String.valueOf(loaiXe));
            ResultSet resultSet2 = statement2.executeQuery();
            int N = 0;
            if (resultSet2.next()) {
                N = resultSet2.getInt(1);
            }
            soVeLabel.setText(String.valueOf(N));

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

    public void setReload(){
        loaiXeBox.setValue(null);
        ngayBD.setValue(null);
        ngayKT.setValue(null);
        loadDataFromDatabase();
        tongTien();
        tongVe();
    }

}

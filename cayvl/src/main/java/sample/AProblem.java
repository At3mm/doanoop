package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;


public class AProblem extends AdminUI implements Initializable {

    @FXML
    private Button searchButton;
    @FXML
    private Button reload;
    @FXML
    private DatePicker ngayBatDau;
    @FXML
    private DatePicker ngayKetThuc;
    @FXML
    private TableView<AProblem> tableView;
    @FXML
    private TableColumn<AProblem, String> maSuCoColumn;
    @FXML
    private TableColumn<AProblem, String> tenNVColumn;
    @FXML
    private TableColumn<AProblem, String> ngayColumn;
    @FXML
    private TableColumn<AProblem, String> gioColumn;
    @FXML
    private TableColumn<AProblem, String> loaiXeColumn;
    @FXML
    private TableColumn<AProblem, String> bienSoXeColumn;
    @FXML
    private TableColumn<AProblem, String> moTaSuCoColumn;
    @FXML
    private TextField TenNhanVien;

    private ObservableList<AProblem> AProblemList;
    private Connection connection;
    private String maSuCo;
    private String tenNhanVien;
    private Date ngay;
    private Time gio;
    private String loaiXe;
    private String bienSoXe;
    private String moTaSuCo;

    public AProblem(String maSuCo, String tenNhanVien, Date ngay, Time gio, String loaiXe, String bienSoXe, String moTaSuCo) {
        this.maSuCo = maSuCo;
        this.tenNhanVien = tenNhanVien;
        this.ngay = ngay;
        this.gio = gio;
        this.loaiXe = loaiXe;
        this.bienSoXe = bienSoXe;
        this.moTaSuCo = moTaSuCo;
    }

    public AProblem() {

    }

    public String getMaSuCo() {
        return maSuCo;
    }

    public void setMaSuCo(String maSuCo) {
        this.maSuCo = maSuCo;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Time getGio() {
        return gio;
    }

    public void setGio(Time gio) {
        this.gio = gio;
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

    public String getMoTaSuCo() {
        return moTaSuCo;
    }

    public void setMoTaSuCo(String moTaSuCo) {
        this.moTaSuCo = moTaSuCo;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeDatabaseConnection();
        // Khởi tạo danh sách và gán cho bảng
        AProblemList = FXCollections.observableArrayList();
        // Thiết lập giá trị cột dữ liệu cho bảng
        maSuCoColumn.setCellValueFactory(new PropertyValueFactory<>("maSuCo"));
        tenNVColumn.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
        gioColumn.setCellValueFactory(new PropertyValueFactory<>("gio"));
        ngayColumn.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        loaiXeColumn.setCellValueFactory(new PropertyValueFactory<>("loaiXe"));
        bienSoXeColumn.setCellValueFactory(new PropertyValueFactory<>("bienSoXe"));
        moTaSuCoColumn.setCellValueFactory(new PropertyValueFactory<>("moTaSuCo"));
        // Gán danh sách cho bảng
        tableView.setItems(AProblemList);
        loadProblemsFromDatabase();
    }

    public void initializeDatabaseConnection() {
        try {
            // Kết nối với cơ sở dữ liệu MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/do_an_hdt", "root", "Viet1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadProblemsFromDatabase() {
        try {
            // Truy vấn dữ liệu từ cơ sở dữ liệu
            String query = "SELECT * FROM su_co";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Xóa dữ liệu cũ trong danh sách
            AProblemList.clear();
            // Lấy dữ liệu từ ResultSet và thêm vào danh sách
            while (resultSet.next()) {
                String maSuCo = resultSet.getString("MaSC");
                String tenNhanVien = resultSet.getString("TenNV");
                Date ngay = resultSet.getDate("Ngay");
                Time gio = resultSet.getTime("Gio");
                String loaiXe = resultSet.getString("LoaiXe");
                String bienSoXe = resultSet.getString("BienSoXe");
                String moTaSuCo = resultSet.getString("MoTa");
                // Tạo một đối tượng problem từ dữ liệu tìm thấy
                AProblem problem = new AProblem(maSuCo, tenNhanVien, ngay, gio, loaiXe, bienSoXe, moTaSuCo );
                // Thêm đối tượng problem vào danh sách kết quả
                AProblemList.add(problem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchProblem() {
        String tenNhanVien = TenNhanVien.getText();
        LocalDate ngayBatDauValue = ngayBatDau.getValue();
        LocalDate ngayKetThucValue = ngayKetThuc.getValue();

        // Kiểm tra dữ liệu đầu vào
        if (tenNhanVien.isEmpty() || ngayBatDau.getValue() == null || ngayKetThuc.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadProblemsFromDatabase();
            return;
        }

        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "SELECT * FROM su_co WHERE TenNV = ? AND Ngay >= ? AND Ngay <= ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tenNhanVien );
            statement.setString(2, String.valueOf(Date.valueOf(ngayBatDau.getValue())));
            statement.setString(3, String.valueOf(Date.valueOf(ngayKetThuc.getValue())));

            // Thực thi câu truy vấn SQL
            ResultSet resultSet = statement.executeQuery();

            // Tạo một danh sách mới để lưu trữ kết quả
            List<AProblem> searchResults = new ArrayList<>();

            // Kiểm tra từng dòng dữ liệu trong ResultSet
            while (resultSet.next()) {
                String resultMaSuCo = resultSet.getString("MaSC");
                String resultTenNV = resultSet.getString("TenNV");
                Date resultNgay = resultSet.getDate("Ngay");
                Time resultGio = resultSet.getTime("Gio");
                String resultLoaiXe = resultSet.getString("LoaiXe");
                String resultBienSoXe = resultSet.getString("BienSoXe");
                String resultMoTaSuCo = resultSet.getString("MoTa");
                // Tạo một đối tượng problem từ dữ liệu tìm thấy
                AProblem problem = new AProblem(resultMaSuCo, resultTenNV, resultNgay, resultGio,resultLoaiXe, resultBienSoXe, resultMoTaSuCo);
                // Thêm đối tượng problem vào danh sách kết quả
                searchResults.add(problem);
            }
            if (!searchResults.isEmpty()) {
                // Xóa dữ liệu cũ trong bảng
                tableView.getItems().clear();
                // Thêm dữ liệu mới vào bảng
                tableView.getItems().addAll(searchResults);
                // Xóa dữ liệu trong các trường nhập liệu
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không tìm thấy kết quả", "Dữ liệu không khớp với bộ nhớ !");
                tableView.getItems().clear();
            }
            // Đóng câu truy vấn
            statement.close();
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

    private void clearFields() {

    }

    public void setReload(){
        TenNhanVien.clear();
        ngayBatDau.setValue(null);
        ngayKetThuc.setValue(null);
        loadProblemsFromDatabase();

    }

}
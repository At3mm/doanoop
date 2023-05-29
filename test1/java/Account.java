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

public class Account extends AdminUI implements Initializable {

    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button loadButton;

    @FXML
    private TextField MaNhanVien;
    @FXML
    private TextField TenNhanVien;
    @FXML
    private TextField SoDienThoai;
    @FXML
    private TextField TenTaiKhoan;
    @FXML
    private TextField MatKhau;
    @FXML
    private TextField LoaiTaiKhoan;
    @FXML
    private TextField TimKiem;

    @FXML
    private TableView<Account> tableView;
    @FXML
    private TableColumn<Account, String> maNhanVienColumn;
    @FXML
    private TableColumn<Account, String> tenNhanVienColumn;
    @FXML
    private TableColumn<Account, String> soDienThoaiColumn;
    @FXML
    private TableColumn<Account, String> tenTaiKhoanColumn;
    @FXML
    private TableColumn<Account, String> matKhauColumn;
    @FXML
    private TableColumn<Account, String> loaiTaiKhoanColumn;

    private ObservableList<Account> AccountList;
    private Connection connection;

    private String maNhanVien;
    private String tenNhanVien;
    private String soDienThoai;
    private String tenTaiKhoan;
    private String matKhau;
    private String loaiTaiKhoan;

    public Account(String maNhanVien, String tenNhanVien, String soDienThoai,String tenTaiKhoan, String matKhau, String loaiTaiKhoan) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.soDienThoai = soDienThoai;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public Account() {

    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }


    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeDatabaseConnection();

        // Khởi tạo danh sách và gán cho bảng
        AccountList = FXCollections.observableArrayList();

        // Thiết lập giá trị cột dữ liệu cho bảng
        maNhanVienColumn.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
        tenNhanVienColumn.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
        soDienThoaiColumn.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        tenTaiKhoanColumn.setCellValueFactory(new PropertyValueFactory<>("tenTaiKhoan"));
        matKhauColumn.setCellValueFactory(new PropertyValueFactory<>("matKhau"));
        loaiTaiKhoanColumn.setCellValueFactory(new PropertyValueFactory<>("loaiTaiKhoan"));

        // Gán danh sách cho bảng
        tableView.setItems(AccountList);
        loadProblemsFromDatabase();
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() > -1) {
                // Lấy dòng được chọn trong bảng
                Account selectedAccount = tableView.getSelectionModel().getSelectedItem();
                if (selectedAccount != null) {
                    // Gán giá trị thuộc tính vào các TextField tương ứng
                    MaNhanVien.setText(selectedAccount.getMaNhanVien());
                    TenNhanVien.setText(selectedAccount.getTenNhanVien());
                    SoDienThoai.setText(selectedAccount.getSoDienThoai());
                    TenTaiKhoan.setText(selectedAccount.getTenTaiKhoan());
                    MatKhau.setText(selectedAccount.getMatKhau());
                    LoaiTaiKhoan.setText(selectedAccount.getLoaiTaiKhoan());
                }
            }
        });
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
            String query = "SELECT * FROM tai_khoan";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Xóa dữ liệu cũ trong danh sách
            AccountList.clear();

            // Lấy dữ liệu từ ResultSet và thêm vào danh sách
            while(resultSet.next()) {
                String maNhanVien = resultSet.getString("MaNV");
                String tenNhanVien = resultSet.getString("TenNV");
                String soDienThoai = resultSet.getString("SDT");
                String tenTaiKhoan = resultSet.getString("TenTK");
                String matKhau = resultSet.getString("Matkhau");
                String loaiTaiKhoan = resultSet.getString("LoaiTK");
                // Tạo một đối tượng problem từ dữ liệu tìm thấy
                Account account = new Account(maNhanVien, tenNhanVien, soDienThoai, tenTaiKhoan, matKhau,loaiTaiKhoan);

                // Thêm đối tượng problem vào danh sách kết quả
                AccountList.add(account);
                clearFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAccount() {
        String maNhanVien = MaNhanVien.getText();
        String tenNhanVien = TenNhanVien.getText();
        String soDienThoai = SoDienThoai.getText();
        String tenTaiKhoan = TenTaiKhoan.getText();
        String matKhau = MatKhau.getText();
        String loaiTaiKhoan = LoaiTaiKhoan.getText();

        // Kiểm tra dữ liệu đầu vào
        if (maNhanVien.isEmpty() || tenNhanVien.isEmpty() || soDienThoai.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty() || loaiTaiKhoan.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadProblemsFromDatabase();
            return;
        }
        try{
            // Chuẩn bị câu truy vấn SQL
            String query = "INSERT INTO tai_khoan (MaNV, TenNV, SDT, TenTK, Matkhau, LoaiTK) VALUES (?, ?, ?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maNhanVien);
            statement.setString(2, tenNhanVien);
            statement.setString(3, soDienThoai);
            statement.setString(4, tenTaiKhoan);
            statement.setString(5, matKhau);
            statement.setString(6, loaiTaiKhoan);

            // Thực thi câu truy vấn
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION,"Thông báo","Thành công !","Dữ liệu đã được thêm vào cơ sở dữ liệu ");
                loadProblemsFromDatabase();
                clearFields();
            }else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Thêm dữ liệu thất bại !", "Có lỗi xảy ra trong quá trình thực hiện");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyAccount(){
        String maNhanVien = MaNhanVien.getText();
        String tenNhanVien = TenNhanVien.getText();
        String soDienThoai = SoDienThoai.getText();
        String tenTaiKhoan = TenTaiKhoan.getText();
        String matKhau = MatKhau.getText();
        String loaiTaiKhoan = LoaiTaiKhoan.getText();

        // Kiểm tra dữ liệu đầu vào
        if (maNhanVien.isEmpty() || tenNhanVien.isEmpty() || soDienThoai.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty() || loaiTaiKhoan.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadProblemsFromDatabase();
            return;
        }
        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "UPDATE tai_khoan SET TenNV = ?, SDT = ?, TenTK = ?, Matkhau = ?, LoaiTK = ? WHERE MaNV = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tenNhanVien);
            statement.setString(2, soDienThoai);
            statement.setString(3, tenTaiKhoan);
            statement.setString(4, matKhau);
            statement.setString(5, loaiTaiKhoan);
            statement.setString(6, maNhanVien);

            // Thực thi câu truy vấn
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION,"Thông báo","Thành công !","Dữ liệu đã được cập nhật vào cơ sở dữ liệu ");
                loadProblemsFromDatabase();
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Cập nhật dữ liệu thất bại !", "Có lỗi xảy ra trong quá trình thực hiện");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(){
        String maNhanVien = MaNhanVien.getText();
        String tenNhanVien = TenNhanVien.getText();
        String soDienThoai = SoDienThoai.getText();
        String tenTaiKhoan = TenTaiKhoan.getText();
        String matKhau = MatKhau.getText();
        String loaiTaiKhoan = LoaiTaiKhoan.getText();

        // Kiểm tra dữ liệu đầu vào
        if (maNhanVien.isEmpty() || tenNhanVien.isEmpty() || soDienThoai.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty() || loaiTaiKhoan.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadProblemsFromDatabase();
            return;
        }

        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "DELETE FROM tai_khoan WHERE TenNV = ? AND SDT = ? AND TenTK = ? AND Matkhau = ? AND LoaiTK = ? AND MaNV = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tenNhanVien);
            statement.setString(2, soDienThoai);
            statement.setString(3, tenTaiKhoan);
            statement.setString(4, matKhau);
            statement.setString(5, loaiTaiKhoan);
            statement.setString(6, maNhanVien);

            // Thực thi câu truy vấn
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION,"Thông báo","Thành công !","Dữ liệu đã được cập nhật vào cơ sở dữ liệu ");
                loadProblemsFromDatabase();
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Cập nhật dữ liệu thất bại !", "Có lỗi xảy ra trong quá trình thực hiện");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchAccount(){
        String timKiem = TimKiem.getText();

        // Kiểm tra dữ liệu đầu vào
        if (timKiem.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadProblemsFromDatabase();
            return;
        }
        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "SELECT * FROM tai_khoan WHERE TenTK = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, timKiem);

            // Thực thi câu truy vấn SQL
            ResultSet resultSet = statement.executeQuery();

            // Tạo một danh sách mới để lưu trữ kết quả
            List<Account> searchResults = new ArrayList<>();

            // Kiểm tra từng dòng dữ liệu trong ResultSet
            while(resultSet.next()) {
                String resultMaNhanVien = resultSet.getString("MaNV");
                String resultTenNhanVien = resultSet.getString("TenNV");
                String resultSoDienThoai = resultSet.getString("SDT");
                String resultTenTaiKhoan = resultSet.getString("TenTK");
                String resultMatKhau = resultSet.getString("Matkhau");
                String resultLoaiTaiKhoan = resultSet.getString("LoaiTK");
                // Tạo một đối tượng problem từ dữ liệu tìm thấy
                Account account = new Account(resultMaNhanVien,resultTenNhanVien,resultSoDienThoai,resultTenTaiKhoan,resultMatKhau,resultLoaiTaiKhoan);

                // Thêm đối tượng problem vào danh sách kết quả
                searchResults.add(account);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void reload(){
        loadProblemsFromDatabase();
        MaNhanVien.clear();
        TenNhanVien.clear();
        SoDienThoai.clear();
        TenTaiKhoan.clear();
        MatKhau.clear();
        LoaiTaiKhoan.clear();
    }

    private void clearFields() {
        MaNhanVien.clear();
        TenNhanVien.clear();
        SoDienThoai.clear();
        TenTaiKhoan.clear();
        MatKhau.clear();
        LoaiTaiKhoan.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


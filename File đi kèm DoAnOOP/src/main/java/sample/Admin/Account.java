package sample.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

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
    private TextField maNhanVienText;
    @FXML
    private TextField tenNhanVienText;
    @FXML
    private TextField soDienThoaiText;
    @FXML
    private TextField tenTaiKhoanText;
    @FXML
    private TextField matKhauText;
    @FXML
    private Label loaiTaiKhoanLabel;
    @FXML
    private TextField timKiemText;
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

    private ObservableList<Account> accountList;
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
        String options = ("Nhân viên");
        loaiTaiKhoanLabel.setText(options);

        //Giới hạn kí tự
        final int max = 10;
        maNhanVienText.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (maNhanVienText.getText().length() >= max) {
                keyEvent.consume();
            }
        });
        soDienThoaiText.addEventFilter(KeyEvent.KEY_TYPED, keyEvent1 -> {
            if (soDienThoaiText.getText().length() >= max) {
                keyEvent1.consume();
            }
        });
        final int max1 = 16;
        tenTaiKhoanText.addEventFilter(KeyEvent.KEY_TYPED, keyEvent2 -> {
            if (tenTaiKhoanText.getText().length() >= max1) {
                keyEvent2.consume();
            }
        });
        matKhauText.addEventFilter(KeyEvent.KEY_TYPED, keyEvent3 -> {
            if (matKhauText.getText().length() >= max1) {
                keyEvent3.consume();
            }
        });

        // Khởi tạo danh sách và gán cho bảng
        accountList = FXCollections.observableArrayList();

        // Thiết lập giá trị cột dữ liệu cho bảng
        maNhanVienColumn.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
        tenNhanVienColumn.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
        soDienThoaiColumn.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        tenTaiKhoanColumn.setCellValueFactory(new PropertyValueFactory<>("tenTaiKhoan"));
        matKhauColumn.setCellValueFactory(new PropertyValueFactory<>("matKhau"));
        loaiTaiKhoanColumn.setCellValueFactory(new PropertyValueFactory<>("loaiTaiKhoan"));

        // Gán danh sách cho bảng
        tableView.setItems(accountList);
        loadDataFromDatabase();
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() > -1) {
                // Lấy dòng được chọn trong bảng
                Account selectedAccount = tableView.getSelectionModel().getSelectedItem();
                if (selectedAccount != null) {
                    // Gán giá trị thuộc tính vào các TextField tương ứng
                    maNhanVienText.setText(selectedAccount.getMaNhanVien());
                    tenNhanVienText.setText(selectedAccount.getTenNhanVien());
                    soDienThoaiText.setText(selectedAccount.getSoDienThoai());
                    tenTaiKhoanText.setText(selectedAccount.getTenTaiKhoan());
                    matKhauText.setText(selectedAccount.getMatKhau());
                    loaiTaiKhoanLabel.setText(selectedAccount.getLoaiTaiKhoan());
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
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void loadDataFromDatabase() {
        try {
            // Truy vấn dữ liệu từ cơ sở dữ liệu
            String query = "SELECT * FROM tai_khoan WHERE LoaiTK = 'Nhân viên'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Xóa dữ liệu cũ trong danh sách
            accountList.clear();

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
                accountList.add(account);
                clearFields();
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void addAccount() {
        String maNhanVien = maNhanVienText.getText();
        String tenNhanVien = tenNhanVienText.getText();
        String soDienThoai = soDienThoaiText.getText();
        String tenTaiKhoan = tenTaiKhoanText.getText();
        String matKhau = matKhauText.getText();
        String loaiTaiKhoan = loaiTaiKhoanLabel.getText();

        // Kiểm tra dữ liệu đầu vào
        if (maNhanVien.isEmpty() || tenNhanVien.isEmpty() || soDienThoai.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty() || loaiTaiKhoan.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDatabase();
            return;
        }
        try{
            // Kiểm tra sự tồn tại của các thuộc tính MaNV, TenTK, LoaiTK
            String query1 = "SELECT * FROM tai_khoan WHERE MaNV = ?";
            PreparedStatement checkStatement1 = connection.prepareStatement(query1);
            checkStatement1.setString(1, maNhanVien);
            ResultSet resultSet1 = checkStatement1.executeQuery();

            if (resultSet1.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Mã nhân viên đã tồn tại", "Vui lòng chọn một mã khác.");
                return;
            }
            checkStatement1.close();

            String query2 = "SELECT * FROM tai_khoan WHERE TenTK = ?";
            PreparedStatement checkStatement2 = connection.prepareStatement(query2);
            checkStatement2.setString(1, tenTaiKhoan);
            ResultSet resultSet2 = checkStatement2.executeQuery();

            if (resultSet2.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Tài khoản đã tồn tại", "Vui lòng chọn một tài khoản khác.");
                return;
            }
            checkStatement2.close();

            String query3 = "SELECT * FROM tai_khoan WHERE SDT = ?";
            PreparedStatement checkStatement3 = connection.prepareStatement(query3);
            checkStatement3.setString(1, soDienThoai);
            ResultSet resultSet3 = checkStatement3.executeQuery();

            if (resultSet3.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Số điện thoại đã tồn tại", "Vui lòng chọn một số khác.");
                return;
            }
            checkStatement3.close();

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
                loadDataFromDatabase();
                clearFields();
            }else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Thêm dữ liệu thất bại !", "Có lỗi xảy ra trong quá trình thực hiện");
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void modifyAccount(){
        String maNhanVien = maNhanVienText.getText();
        String tenNhanVien = tenNhanVienText.getText();
        String soDienThoai = soDienThoaiText.getText();
        String tenTaiKhoan = tenTaiKhoanText.getText();
        String matKhau = matKhauText.getText();
        String loaiTaiKhoan = loaiTaiKhoanLabel.getText();

        // Kiểm tra dữ liệu đầu vào
        if (maNhanVien.isEmpty() || tenNhanVien.isEmpty() || soDienThoai.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty() || loaiTaiKhoan.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDatabase();
            return;
        }
        try {
            // Kiểm tra sự tồn tại của các thuộc tính MaNV, TenTK, SDT
            String query1 = "SELECT * FROM tai_khoan WHERE MaNV = ?";
            PreparedStatement checkStatement1 = connection.prepareStatement(query1);
            checkStatement1.setString(1, maNhanVien);
            ResultSet resultSet1 = checkStatement1.executeQuery();

            if (!resultSet1.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Mã nhân viên không tồn tại", "Vui lòng chọn một mã khác.");
                return;
            }

            String tenNhanVien1 = resultSet1.getString("TenNV");
            if (!tenNhanVien.equals(tenNhanVien1)){
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Sai tên nhân viên", "Vui lòng không thay đổi tên nhân viên. ");
                return;
            }
            checkStatement1.close();

            String query2 = "SELECT * FROM tai_khoan WHERE MaNV != ? AND SDT = ?";
            PreparedStatement checkStatement2 = connection.prepareStatement(query2);
            checkStatement2.setString(1, maNhanVien);
            checkStatement2.setString(2, soDienThoai);
            ResultSet resultSet2 = checkStatement2.executeQuery();

            if (resultSet2.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Số điện thoại đã tồn tại", "Vui lòng chọn một số khác.");
                return;
            }
            checkStatement2.close();

            String query3 = "SELECT * FROM tai_khoan WHERE MaNV != ? AND TenTK = ?";
            PreparedStatement checkStatement3 = connection.prepareStatement(query3);
            checkStatement3.setString(1, maNhanVien);
            checkStatement3.setString(2, tenTaiKhoan);
            ResultSet resultSet3 = checkStatement3.executeQuery();

            if (resultSet3.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Tài khoản đã tồn tại", "Vui lòng chọn tên tài khoản khác.");
                return;
            }
            checkStatement3.close();

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
                loadDataFromDatabase();
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Cập nhật dữ liệu thất bại !", "Có lỗi xảy ra trong quá trình thực hiện");
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void deleteAccount(){
        String maNhanVien = maNhanVienText.getText();
        String tenNhanVien = tenNhanVienText.getText();
        String soDienThoai = soDienThoaiText.getText();
        String tenTaiKhoan = tenTaiKhoanText.getText();
        String matKhau = matKhauText.getText();
        String loaiTaiKhoan = loaiTaiKhoanLabel.getText();
        // Kiểm tra dữ liệu đầu vào
        if (maNhanVien.isEmpty() || tenNhanVien.isEmpty() || soDienThoai.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty() || loaiTaiKhoan.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDatabase();
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
                loadDataFromDatabase();
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Cập nhật dữ liệu thất bại !", "Có lỗi xảy ra trong quá trình thực hiện");
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void searchAccount(){
        String timKiem = timKiemText.getText();

        // Kiểm tra dữ liệu đầu vào
        if (timKiem.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDatabase();
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
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }
    public void reload(){
        loadDataFromDatabase();
        maNhanVienText.clear();
        tenNhanVienText.clear();
        soDienThoaiText.clear();
        tenTaiKhoanText.clear();
        matKhauText.clear();
    }

    private void clearFields() {
        maNhanVienText.clear();
        tenNhanVienText.clear();
        soDienThoaiText.clear();
        tenTaiKhoanText.clear();
        matKhauText.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


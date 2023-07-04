package sample.Employee;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;

import java.time.LocalDate;

public class EProblem extends EmployeeUI implements Initializable {

    @FXML
    private Button reLoad;
    @FXML
    private Button addProblem;
    @FXML
    private Button deleteProblem;
    @FXML
    private Button modifyProblem;
    @FXML
    private TextField maSuCoText;
    @FXML
    private TextField tenNhanVienText;
    @FXML
    private TextField moTaText;
    @FXML
    private Label loaiXeLabel;
    @FXML
    private Label bienSoXeLabel;
    @FXML
    private Label ngayGhiNhanLabel;
    @FXML
    private ComboBox<String> maTheBox;
    @FXML
    private TableView<EProblem> tableView;
    @FXML
    private TableColumn<EProblem, String> maSuCoColumn;
    @FXML
    private TableColumn<EProblem, String> tenNVColumn;
    @FXML
    private TableColumn<EProblem, String> maTheColumn;
    @FXML
    private TableColumn<EProblem, String> ngayNhanColumn;
    @FXML
    private TableColumn<EProblem, String> gioNhanColumn;
    @FXML
    private TableColumn<EProblem, String> loaiXeColumn;
    @FXML
    private TableColumn<EProblem, String> bienSoXeColumn;
    @FXML
    private TableColumn<EProblem, String> moTaSuCoColumn;
    @FXML
    private TableColumn<EProblem, String> loaiTheColumn;


    private ObservableList<EProblem> eProblemsList;
    private Connection connection;
    private String maSuCo;
    private String tenNhanVien;
    private String maThe;
    private Date ngayNhan;
    private Time gioNhan;
    private String loaiXe;
    private String bienSoXe;
    private String moTaSuCo;

    public EProblem(String maSuCo, String tenNhanVien,String maThe, Date ngayNhan, Time gioNhan, String loaiXe, String bienSoXe, String moTaSuCo) {
        this.maSuCo = maSuCo;
        this.tenNhanVien = tenNhanVien;
        this.maThe = maThe;
        this.ngayNhan = ngayNhan;
        this.gioNhan = gioNhan;
        this.loaiXe = loaiXe;
        this.bienSoXe = bienSoXe;
        this.moTaSuCo = moTaSuCo;
    }

    public EProblem() {
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

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public Time getGioNhan() {
        return gioNhan;
    }

    public void setGioNhan(Time gioNhan) {
        this.gioNhan = gioNhan;
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
        LocalDate currentTime = LocalDate.now();
        ngayGhiNhanLabel.setText(currentTime.toString());

        //Giới hạn kí tự
        final int max = 10;
        maSuCoText.addEventFilter(KeyEvent.KEY_TYPED, keyEvent1 -> {
            if (maSuCoText.getText().length() >= max) {
                keyEvent1.consume();
            }
        });

        //Đưa mã thẻ vào maTheBox
        try{
            // Truy vấn dữ liệu từ cơ sở dữ liệu
            String query = "SELECT DISTINCT MaThe FROM thong_ke_the_ngay UNION SELECT DISTINCT MaThe FROM thong_ke_the_thang ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Khởi tạo danh sách options
            ObservableList<String> options = FXCollections.observableArrayList();

            // Lấy dữ liệu từ ResultSet và thêm vào danh sách
            while (resultSet.next()) {
                options.add(resultSet.getString("MaThe"));
            }

            // Đưa danh sách options vào ComboBox
            maTheBox.setItems(options);

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));

        }
        maTheBox.setOnAction(event -> {
            String selectedValue = maTheBox.getValue() != null ? maTheBox.getValue() : null;
            try {
                if (selectedValue != null) {
                    String query1 = "SELECT DISTINCT LoaiXe, BienSoXe FROM thong_ke_the_ngay WHERE MaThe = ?" +
                            "UNION SELECT DISTINCT LoaiXe, BienSoXe FROM thong_ke_the_thang WHERE MaThe = ? ";
                    PreparedStatement statement1 = connection.prepareStatement(query1);
                    statement1.setString(1, selectedValue);
                    statement1.setString(2, selectedValue);
                    ResultSet resultSet1 = statement1.executeQuery();

                    if (resultSet1.next()) {
                        String loaiXe = resultSet1.getString("LoaiXe");
                        String bienSoXe = resultSet1.getString("BienSoXe");
                        loaiXeLabel.setText(loaiXe);
                        bienSoXeLabel.setText(bienSoXe);
                    }
                    statement1.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
            }
        });

        // Khởi tạo danh sách và gán cho bảng
        eProblemsList = FXCollections.observableArrayList();

        // Thiết lập giá trị cột dữ liệu cho bảng
        maSuCoColumn.setCellValueFactory(new PropertyValueFactory<>("maSuCo"));
        tenNVColumn.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
        maTheColumn.setCellValueFactory(new PropertyValueFactory<>("maThe"));
        gioNhanColumn.setCellValueFactory(new PropertyValueFactory<>("gioNhan"));
        ngayNhanColumn.setCellValueFactory(new PropertyValueFactory<>("ngayNhan"));
        loaiXeColumn.setCellValueFactory(new PropertyValueFactory<>("loaiXe"));
        bienSoXeColumn.setCellValueFactory(new PropertyValueFactory<>("bienSoXe"));
        moTaSuCoColumn.setCellValueFactory(new PropertyValueFactory<>("moTaSuCo"));

        // Gán danh sách cho bảng
        tableView.setItems(eProblemsList);
        loadDataFromDatabase();
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() > -1) {
                // Lấy dòng được chọn trong bảng
                EProblem selectedEProblem = tableView.getSelectionModel().getSelectedItem();
                if (selectedEProblem != null) {
                    // Gán giá trị thuộc tính vào các TextField tương ứng
                    maSuCoText.setText(selectedEProblem.getMaSuCo());
                    tenNhanVienText.setText(selectedEProblem.getTenNhanVien());
                    maTheBox.setValue(selectedEProblem.getMaThe());
                    moTaText.setText(selectedEProblem.getMoTaSuCo());
                    ngayGhiNhanLabel.setText(String.valueOf(selectedEProblem.getNgayNhan().toLocalDate()));
                    loaiXeLabel.setText(selectedEProblem.getLoaiXe());
                    bienSoXeLabel.setText(selectedEProblem.getBienSoXe());
                }
            }
        });
        loadDataFromDatabase();
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

    public void loadDataFromDatabase(){
        try {
            // Truy vấn dữ liệu từ cơ sở dữ liệu
            String query = "SELECT * FROM su_co ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Xóa dữ liệu cũ trong danh sách
            eProblemsList.clear();

            // Lấy dữ liệu từ ResultSet và thêm vào danh sách
            while (resultSet.next()) {
                String maSuCo = resultSet.getString("MaSC");
                String tenNhanVien = resultSet.getString("TenNV");
                String maThe = resultSet.getString("MaThe");
                Date ngay = resultSet.getDate("Ngay");
                Time gio = resultSet.getTime("Gio");
                String loaiXe = resultSet.getString("LoaiXe");
                String bienSoXe = resultSet.getString("BienSoXe");
                String moTaSuCo = resultSet.getString("MoTa");

                // Tạo một đối tượng problem từ dữ liệu tìm thấy
                EProblem problem = new EProblem(maSuCo, tenNhanVien,maThe, ngay, gio, loaiXe, bienSoXe, moTaSuCo);

                // Thêm đối tượng problem vào danh sách kết quả
                eProblemsList.add(problem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void addProblem(){
        String maSuCo = maSuCoText.getText();
        String tenNhanVien = tenNhanVienText.getText();
        String maThe = maTheBox.getValue() != null ? maTheBox.getValue() : null;
        String loaiXe = loaiXeLabel.getText();
        String moTa = moTaText.getText();
        String bienSoXe = bienSoXeLabel.getText();
        String ngayNhan = ngayGhiNhanLabel.getText();

        // Kiểm tra dữ liệu đầu vào
        if (tenNhanVien.isEmpty() || maSuCo.isEmpty() || moTa.isEmpty() || bienSoXe.isEmpty() || loaiXe.isEmpty() || maThe.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDatabase();
            return;
        }

        try {
            //Kiểm tra sự tồn tại của MaSC
            String query1 = "SELECT * FROM su_co WHERE MaSC = ?";
            PreparedStatement checkStatement1 = connection.prepareStatement(query1);
            checkStatement1.setString(1, maSuCo);
            ResultSet resultSet1 = checkStatement1.executeQuery();

            if (resultSet1.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Mã sự cố đã tồn tại", "Vui lòng chọn một mã khác.");
                return;
            }
            checkStatement1.close();

            String query2 = "SELECT * FROM tai_khoan WHERE TenNV = ?";
            PreparedStatement checkStatement2 = connection.prepareStatement(query2);
            checkStatement2.setString(1, tenNhanVien);
            ResultSet resultSet2 = checkStatement2.executeQuery();

            if (!resultSet2.next()){
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không tồn tại nhân viên ", "Vui lòng nhập đúng tên nhân viên.");
                return;

            }
            checkStatement2.close();

            // Chuẩn bị câu truy vấn SQL
            String query = "INSERT INTO su_co (MaSC, TenNV, MaThe ,LoaiXe, BienSoXe, MoTa, Ngay, Gio) VALUES (?, ?, ?, ?, ?, ?, ?,CURTIME()) ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maSuCo);
            statement.setString(2, tenNhanVien);
            statement.setString(3, maThe);
            statement.setString(4, loaiXe);
            statement.setString(5, bienSoXe);
            statement.setString(6, moTa);
            statement.setString(7, ngayNhan);

            // Thực thi câu truy vấn
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thành công !", "Dữ liệu đã được thêm");
                clearFields();
                loadDataFromDatabase();
            } else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Lỗi", "Có lỗi xảy ra trong quá trình thêm dữ liệu");
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void deleteProblem(){
        String maSuCo = maSuCoText.getText();
        String tenNhanVien = tenNhanVienText.getText();
        String maThe = maTheBox.getValue() != null ? maTheBox.getValue() : null;
        String moTa = moTaText.getText();
        String bienSoXe = bienSoXeLabel.getText();
        String ngayNhan = ngayGhiNhanLabel.getText();
        String loaiXe = loaiXeLabel.getText();

        // Kiểm tra dữ liệu đầu vào
        if (tenNhanVien.isEmpty() || maSuCo.isEmpty() || moTa.isEmpty() || bienSoXe.isEmpty() || loaiXe.isEmpty() || maThe.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDatabase();
            return;
        }

        try {
            // Kiểm tra sự tồn tại của các thuộc tính MaSC
            String query1 = "SELECT * FROM su_co WHERE MaSC = ?";
            PreparedStatement checkStatement1 = connection.prepareStatement(query1);
            checkStatement1.setString(1, maSuCo);
            ResultSet resultSet1 = checkStatement1.executeQuery();
            if (!resultSet1.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Mã sự cố không tồn tại", "Vui lòng chọn một mã khác.");
                return;
            }
            checkStatement1.close();

            // Chuẩn bị câu truy vấn SQL
            String query = "DELETE FROM su_co  WHERE MaSC = ? AND TenNV = ? AND MaThe = ? AND LoaiXe = ? AND BienSoXe= ? AND MoTa= ? AND Ngay = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maSuCo);
            statement.setString(2, tenNhanVien);
            statement.setString(3, maThe);
            statement.setString(4, loaiXe);
            statement.setString(5, bienSoXe);
            statement.setString(6, moTa);
            statement.setString(7, ngayNhan);

            // Thực thi câu truy vấn
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thành công !", "Dữ liệu đã bị xoá");
                clearFields();
                loadDataFromDatabase();
            } else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Lỗi", "Có lỗi xảy ra trong quá trình xoá dữ liệu");
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }

    }

    public void modifyProblem(){
        String maSuCo = maSuCoText.getText();
        String tenNhanVien = tenNhanVienText.getText();
        String maThe = maTheBox.getValue() != null ? maTheBox.getValue() : null;
        String moTa = moTaText.getText();
        String bienSoXe = bienSoXeLabel.getText();
        String loaiXe = loaiXeLabel.getText();

        // Kiểm tra dữ liệu đầu vào
        if (tenNhanVien.isEmpty() || maSuCo.isEmpty() || moTa.isEmpty() || bienSoXe.isEmpty() || loaiXe.isEmpty()  || maThe.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDatabase();
            return;
        }

        try {
            // Kiểm tra sự tồn tại của các thuộc tính MaSC
            String query1 = "SELECT * FROM su_co WHERE MaSC = ?";
            PreparedStatement checkStatement1 = connection.prepareStatement(query1);
            checkStatement1.setString(1, maSuCo);
            ResultSet resultSet1 = checkStatement1.executeQuery();

            if (!resultSet1.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Mã sự cố không tồn tại", "Vui lòng chọn một mã khác.");
                return;
            }
            checkStatement1.close();

            String query2 = "SELECT * FROM tai_khoan WHERE TenNV = ?";
            PreparedStatement checkStatement2 = connection.prepareStatement(query2);
            checkStatement2.setString(1, tenNhanVien);
            ResultSet resultSet2 = checkStatement2.executeQuery();

            if (!resultSet2.next()){
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không tồn tại nhân viên ", "Vui lòng nhập đúng tên nhân viên.");
                return;
            }
            checkStatement2.close();

            // Chuẩn bị câu truy vấn SQL
            String query = "UPDATE su_co SET TenNV = ?, MaThe = ?, LoaiXe = ?, BienSoXe= ?, MoTa= ? WHERE MaSC = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tenNhanVien);
            statement.setString(2, maThe);
            statement.setString(3, loaiXe);
            statement.setString(4, bienSoXe);
            statement.setString(5, moTa);
            statement.setString(6, maSuCo);

            // Thực thi câu truy vấn
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thành công !", "Dữ liệu đã được cập nhật");
                clearFields();
                loadDataFromDatabase();
            } else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Lỗi", "Có lỗi xảy ra trong quá trình cập nhật dữ liệu");
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void setReload(){
        loadDataFromDatabase();
        maSuCoText.clear();
        tenNhanVienText.clear();
        moTaText.clear();
        bienSoXeLabel.setText(null);
        loaiXeLabel.setText(null);
        maTheBox.setValue(null);
        LocalDate currentTime = LocalDate.now();
        ngayGhiNhanLabel.setText(currentTime.toString());
    }

    private void clearFields() {
        maSuCoText.clear();
        tenNhanVienText.clear();
        moTaText.clear();
        bienSoXeLabel.setText(null);
        loaiXeLabel.setText(null);
        maTheBox.setValue(null);
        LocalDate currentTime = LocalDate.now();
        ngayGhiNhanLabel.setText(currentTime.toString());
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

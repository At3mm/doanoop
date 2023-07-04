package sample.Employee;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

public class Card extends EmployeeUI implements Initializable {

    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button loadButton;
    @FXML
    private TextField maTheText;
    @FXML
    private TextField bienSoXeText;
    @FXML
    private TextField tenKHText;
    @FXML
    private TextField soDienThoaiText;
    @FXML
    private ComboBox<String> loaiXeBox;
    @FXML
    private Label ngayDangKiLabel;
    @FXML
    private TableView<Card> tableView;
    @FXML
    private TableColumn<Card, String> maThelumn;
    @FXML
    private TableColumn<Card, String> bienSoXeColumn;
    @FXML
    private TableColumn<Card, String> tenKHColumn;
    @FXML
    private TableColumn<Card, String> soDienThoaiColumn;
    @FXML
    private TableColumn<Card, String> ngayDangKiColumn;
    @FXML
    private TableColumn<Card, String> loaiXeColumn;

    private ObservableList<Card> cardList;
    private Connection connection;
    private String maThe;
    private String bienSoXe;
    private String tenKH;
    private String loaiXe;
    private String soDienThoai;
    private LocalDate ngayDangKi;

    public Card(String maThe, String bienSoXe, String tenKH, String loaiXe, String soDienThoai, LocalDate ngayDangKi) {
        this.maThe = maThe;
        this.bienSoXe = bienSoXe;
        this.tenKH = tenKH;
        this.loaiXe = loaiXe;
        this.soDienThoai = soDienThoai;
        this.ngayDangKi = ngayDangKi;
    }

    public Card() {

    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
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

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public LocalDate getNgayDangKi() {
        return ngayDangKi;
    }

    public void setNgayDangKi(LocalDate ngayDangKi) {
        this.ngayDangKi = ngayDangKi;
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
            String query = "SELECT * FROM the_thang";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Xóa dữ liệu cũ trong danh sách
            cardList.clear();

            // Lấy dữ liệu từ ResultSet và thêm vào danh sách
            while (resultSet.next()) {
                String maThe = resultSet.getString("MaTheThang");
                String loaiXe = resultSet.getString("LoaiXe");
                String bienSoXe = resultSet.getString("BienSoXe");
                String soDienThoai = resultSet.getString("SDT");
                String tenKH = resultSet.getString("TenKH");
                LocalDate ngayDangKi = resultSet.getDate("NgayDangKi").toLocalDate();

                // Tạo một đối tượng card từ dữ liệu tìm thấy
                Card card = new Card(maThe, bienSoXe, tenKH, loaiXe, soDienThoai, ngayDangKi);

                // Thêm đối tượng card vào danh sách kết quả
                cardList.add(card);
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeDatabaseConnection();
        LocalDate currentDate = LocalDate.now();
        ngayDangKiLabel.setText(currentDate.toString());

        //Khi chọn loại xe khác thì ô biển số tự động xoá
        loaiXeBox.setOnAction(actionEvent -> {
            bienSoXeText.clear();
        });

        //Giới hạn kí tự
        final int max = 10;
        maTheText.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (maTheText.getText().length() >= max) {
                keyEvent.consume();
            }
        });
        bienSoXeText.addEventFilter(KeyEvent.KEY_TYPED, keyEvent1 ->  {
            if (bienSoXeText.getText().length() >= max) {
                keyEvent1.consume();
            }
        });
        soDienThoaiText.addEventFilter(KeyEvent.KEY_TYPED, keyEvent2 ->  {
            if (soDienThoaiText.getText().length() >= max) {
                keyEvent2.consume();
            }
        });

        // Thêm các lựa chọn vào ComboBox
        ObservableList<String> options = FXCollections.observableArrayList("Xe máy", "Xe đạp");
        loaiXeBox.setItems(options);

        // Khởi tạo danh sách và gán cho bảng
        cardList = FXCollections.observableArrayList();

        // Thiết lập giá trị cột dữ liệu cho bảng
        maThelumn.setCellValueFactory(new PropertyValueFactory<>("maThe"));
        loaiXeColumn.setCellValueFactory(new PropertyValueFactory<>("loaiXe"));
        soDienThoaiColumn.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        tenKHColumn.setCellValueFactory(new PropertyValueFactory<>("tenKH"));
        bienSoXeColumn.setCellValueFactory(new PropertyValueFactory<>("bienSoXe"));
        ngayDangKiColumn.setCellValueFactory(new PropertyValueFactory<>("ngayDangKi"));

        // Gán danh sách cho bảng
        tableView.setItems(cardList);
        loadDataFromDatabase();
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() > -1) {
                // Lấy dòng được chọn trong bảng
                Card selectedCard = tableView.getSelectionModel().getSelectedItem();
                if (selectedCard != null) {
                    // Gán giá trị thuộc tính vào các TextField tương ứng
                    maTheText.setText(selectedCard.getMaThe());
                    loaiXeBox.setValue(selectedCard.getLoaiXe());
                    soDienThoaiText.setText(selectedCard.getSoDienThoai());
                    tenKHText.setText(selectedCard.getTenKH());
                    bienSoXeText.setText(selectedCard.getBienSoXe());
                    ngayDangKiLabel.setText(String.valueOf(LocalDate.parse(selectedCard.getNgayDangKi().toString())));
                }
            }
        });
    }

    public void addCard(){
        String maThe = maTheText.getText();
        String tenKH = tenKHText.getText();
        String SDT = soDienThoaiText.getText();
        String bienSoXe = bienSoXeText.getText();
        String ngayDangKi = ngayDangKiLabel.getText();
        String loaiXe = loaiXeBox.getValue();

        // Kiểm tra dữ liệu đầu vào
        if (maThe.isEmpty() || tenKH.isEmpty() || SDT.isEmpty() || bienSoXe.isEmpty() || loaiXe.isEmpty() ) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDatabase();
            return;
        }

        try {
            //Kiểm tra sự tồn tại maTheThang, SDT
            String query = "SELECT * FROM the_thang WHERE MaTheThang = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setString(1,maThe);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Mã thẻ đã tồn tại", "Vui lòng chọn một mã khác.");
                return;
            }
            checkStatement.close();

            String query1 = "SELECT * FROM the_thang WHERE SDT = ?";
            PreparedStatement checkStatement1 = connection.prepareStatement(query1);
            checkStatement1.setString(1,SDT);
            ResultSet resultSet1 = checkStatement1.executeQuery();

            if (resultSet1.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Số điện thoại đã tồn tại", "Vui lòng chọn một số khác.");
                return;
            }
            checkStatement1.close();

            //Kiểm tra sự tồn tại của bienSoXe
            String query2 = "SELECT * FROM the_thang WHERE BienSoXe = ? AND LoaiXe = 'Xe máy'";
            PreparedStatement checkStatement2 = connection.prepareStatement(query2);
            checkStatement2.setString(1, bienSoXe);
            ResultSet resultSet2 = checkStatement2.executeQuery();
            if (resultSet2.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Biển số xe đã tồn tại", "Vui lòng chọn biển số khác.");
                return;
            }
            checkStatement2.close();

            // Chuẩn bị câu truy vấn SQL
            String query3 = "INSERT INTO the_thang (MaTheThang, TenKH, LoaiXe, BienSoXe, SDT, NgayDangKi, GioDangKi, LoaiThe, Do) VALUES (?, ?, ?, ?, ?, ?,CURTIME(),'Thẻ tháng','0') ";
            PreparedStatement statement3 = connection.prepareStatement(query3);
            statement3.setString(1, maThe);
            statement3.setString(2, tenKH);
            statement3.setString(3, loaiXe);
            statement3.setString(4, bienSoXe);
            statement3.setString(5, SDT);
            statement3.setString(6, ngayDangKi);

            // Thực thi câu truy vấn
            int rowsAffected = statement3.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thành công !", "Dữ liệu đã được thêm");
                clearFields();
                loadDataFromDatabase();
            } else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Lỗi", "Có lỗi xảy ra trong quá trình thêm dữ liệu");
            }
            statement3.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Thông báo","Lỗi", String.valueOf(e));
        }
    }

    public void deleteCard(){
        String maThe = maTheText.getText();
        String tenKH = tenKHText.getText();
        String SDT = soDienThoaiText.getText();
        String bienSoXe = bienSoXeText.getText();
        String ngayDangKi = ngayDangKiLabel.getText();
        String loaiXe = loaiXeBox.getValue();

        // Kiểm tra dữ liệu đầu vào
        if (maThe.isEmpty() || tenKH.isEmpty() || SDT.isEmpty() || bienSoXe.isEmpty() || loaiXe.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDatabase();
            return;
        }

        try {
            //Kiểm tra sự tồn tại của MaSC
            String query = "SELECT * FROM the_thang WHERE MaTheThang = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setString(1, maThe);
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Mã thẻ không tồn tại", "Vui lòng chọn một mã khác.");
                return;
            }
            checkStatement.close();

            // Chuẩn bị câu truy vấn SQL
            String query1 = "DELETE FROM the_thang WHERE MaTheThang = ? AND TenKH = ? AND LoaiXe = ? AND BienSoXe= ? AND SDT = ? AND NgayDangKi = ? ";
            PreparedStatement statement = connection.prepareStatement(query1);
            statement.setString(1, maThe);
            statement.setString(2, tenKH);
            statement.setString(3, loaiXe);
            statement.setString(4, bienSoXe);
            statement.setString(5, SDT);
            statement.setString(6, ngayDangKi);

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
        }
    }

    public void modifyCard(){
        String maThe = maTheText.getText();
        String tenKH = tenKHText.getText();
        String SDT = soDienThoaiText.getText();
        String bienSoXe = bienSoXeText.getText();
        String ngayDangKi = ngayDangKiLabel.getText();
        String loaiXe = loaiXeBox.getValue();

        // Kiểm tra dữ liệu đầu vào
        if (maThe.isEmpty() || tenKH.isEmpty() || SDT.isEmpty() || bienSoXe.isEmpty() || loaiXe.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thiếu thông tin", "Hãy điền dữ liệu vào các ô còn trống !");
            loadDataFromDatabase();
            return;
        }

        try {
            //Kiểm tra sự tồn tại của MaSC
            String query = "SELECT * FROM the_thang WHERE MaTheThang = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setString(1, maThe);
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Lỗi", "Mã thẻ không tồn tại.");
                return;
            }

            String tenKhachHang = resultSet.getString("TenKH");
            if (!tenKhachHang.equals(tenKH)){
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Lỗi", "Vui lòng không thay đổi tên khách hàng.");
                return;
            }
            checkStatement.close();

            String query1 = "SELECT * FROM the_thang WHERE MaTheThang != ? AND BienSoXe = ? AND LoaiXe = 'Xe máy' ";
            PreparedStatement checkStatement1 = connection.prepareStatement(query1);
            checkStatement1.setString(1, maThe);
            checkStatement1.setString(2, bienSoXe);
            ResultSet resultSet1 = checkStatement1.executeQuery();
            if (resultSet1.next()){
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Biển số xe đã tồn tại", "Vui lòng chọn biển số khác.");
                return;
            }
            checkStatement1.close();

            String query2 = "SELECT * FROM the_thang WHERE MaTheThang != ? AND SDT = ?";
            PreparedStatement checkStatement2 = connection.prepareStatement(query2);
            checkStatement2.setString(1, maThe);
            checkStatement2.setString(2, SDT);
            ResultSet resultSet2 = checkStatement2.executeQuery();

            if (resultSet2.next()){
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Số điện thoại đã tồn tại", "Vui lòng chọn số khác.");
                return;
            }
            checkStatement2.close();

            // Chuẩn bị câu truy vấn SQL
            String query4 = "UPDATE the_thang SET TenKH = ?, LoaiXe = ?, BienSoXe= ?, SDT = ?, NgayDangKi = ? WHERE MaTheThang = ?";
            PreparedStatement statement = connection.prepareStatement(query4);
            statement.setString(1, tenKH);
            statement.setString(2, loaiXe);
            statement.setString(3, bienSoXe);
            statement.setString(4, SDT);
            statement.setString(5, ngayDangKi);
            statement.setString(6, maThe);

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

    public void reLoad(){
        maTheText.clear();
        tenKHText.clear();
        bienSoXeText.clear();
        soDienThoaiText.clear();
        loaiXeBox.setValue(null);
        LocalDate currentDate = LocalDate.now();
        ngayDangKiLabel.setText(currentDate.toString());
        loadDataFromDatabase();
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        maTheText.clear();
        tenKHText.clear();
        bienSoXeText.clear();
        soDienThoaiText.clear();
        loaiXeBox.setValue(null);
        LocalDate currentDate = LocalDate.now();
        ngayDangKiLabel.setText(currentDate.toString());
    }

}

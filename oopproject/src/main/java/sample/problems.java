package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;


public class problems extends UI implements Initializable{
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField MaSuCo;
    @FXML
    private TextField MaNhanVien;
    @FXML
    private TextField BienSoXe;
    @FXML
    private TextField MoTa;

    @FXML
    private TableView<problems> table;
    @FXML
    private TableColumn<problems, String> maSuCoColumn;
    @FXML
    private TableColumn<problems, String> maNVColumn;
    @FXML
    private TableColumn<problems, String> bienSoXeColumn;
    @FXML
    private TableColumn<problems, String> moTaSuCoColumn;
    @FXML
    private TableColumn<problems, String> gioColumn;
    @FXML
    private TableColumn<problems, String> ngayColumn;
    private ObservableList<problems> problemList;

    private Connection connection;

    private String maSuCo;
    private String maNhanVien;
    private String bienSoXe;
    private String moTaSuCo;
    private Time Gio;
    private Date Ngay;


    public problems(String maSuCo, String maNhanVien, String bienSoXe, String moTaSuCo, Time Gio, Date Ngay) {
        this.maSuCo = maSuCo;
        this.maNhanVien = maNhanVien;
        this.bienSoXe = bienSoXe;
        this.moTaSuCo = moTaSuCo;
        this.Gio = Gio;
        this.Ngay = Ngay;
    }

    public problems(){
    }

    public String getMaSuCo() {
        return maSuCo;
    }

    public void setMaSuCo(String maSuCo) {
        this.maSuCo = maSuCo;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
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

    public Time getGio() {
        return Gio;
    }

    public void setGio(Time gio) {
        Gio = gio;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeDatabaseConnection();
        // Khởi tạo danh sách và gán cho bảng
        problemList = FXCollections.observableArrayList();
        // Thiết lập giá trị cột dữ liệu cho bảng
        maSuCoColumn.setCellValueFactory(new PropertyValueFactory<>("maSuCo"));
        maNVColumn.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
        bienSoXeColumn.setCellValueFactory(new PropertyValueFactory<>("bienSoXe"));
        moTaSuCoColumn.setCellValueFactory(new PropertyValueFactory<>("moTaSuCo"));
        gioColumn.setCellValueFactory(new PropertyValueFactory<>("Gio"));
        ngayColumn.setCellValueFactory(new PropertyValueFactory<>("Ngay"));
        // Gán danh sách cho bảng
        table.setItems(problemList);
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
            String query = "SELECT * FROM suco";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Xóa dữ liệu cũ trong danh sách
            problemList.clear();

            // Lấy dữ liệu từ ResultSet và thêm vào danh sách
            while (resultSet.next()) {
                String maSuCo = resultSet.getString("MaSC");
                String maNhanVien = resultSet.getString("MaNV");
                String bienSoXe = resultSet.getString("BienSoXe");
                String moTaSuCo = resultSet.getString("MoTa");
                Time Gio = resultSet.getTime("Gio");
                Date Ngay = resultSet.getDate("Ngay");

                problems problem = new problems(maSuCo, maNhanVien, bienSoXe, moTaSuCo, Gio, Ngay);
                problemList.add(problem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProblem() {
        String maSuCo = MaSuCo.getText();
        String maNhanVien = MaNhanVien.getText();
        String bienSoXe = BienSoXe.getText();
        String moTaSuCo = MoTa.getText();

        // Kiểm tra dữ liệu đầu vào
        if (maSuCo.isEmpty() || maNhanVien.isEmpty() || bienSoXe.isEmpty() || moTaSuCo.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Missing Information", "Please fill in all the fields.");
            return;
        }

        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "INSERT INTO suco (MaSC, MaNV, BienSoXe, MoTa, Ngay, Gio) VALUES (?, ?, ?, ?,CURDATE(),CURTIME()) ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maSuCo);
            statement.setString(2, maNhanVien);
            statement.setString(3, bienSoXe);
            statement.setString(4, moTaSuCo);

            // Thực thi câu truy vấn
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Problem Added", "The problem has been successfully added.");
                clearFields();
                loadProblemsFromDatabase();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to Add Problem", "An error occurred while adding the problem.");
            }

            // Đóng câu truy vấn
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyProblem() {
        String maSuCo = MaSuCo.getText();
        String maNhanVien = MaNhanVien.getText();
        String bienSoXe = BienSoXe.getText();
        String moTaSuCo = MoTa.getText();

        // Kiểm tra dữ liệu đầu vào
        if (maSuCo.isEmpty() || maNhanVien.isEmpty() || bienSoXe.isEmpty() || moTaSuCo.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Missing Information", "Please fill in all the fields.");
            return;
        }

        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "UPDATE suco SET MaNV = ?, BienSoXe = ?, MoTa = ?, ThoiGianGhiNhan = NOW() WHERE MaSC = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maNhanVien);
            statement.setString(2, bienSoXe);
            statement.setString(3, moTaSuCo);
            statement.setString(4, maSuCo);

            // Thực thi câu truy vấn SQL
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Problem Modified", "The problem has been successfully modified.");
                clearFields();
                loadProblemsFromDatabase();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to Modify Problem", "An error occurred while modifying the problem.");
            }

            // Đóng câu truy vấn
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProblem() {
        String maSuCo = MaSuCo.getText();

        // Kiểm tra dữ liệu đầu vào
        if (maSuCo.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Missing Information", "Please fill in all the fields.");
            return;
        }

        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "DELETE FROM suco WHERE MaSC = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maSuCo);

            // Thực thi câu truy vấn SQL
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Problem Deleted", "The problem has been successfully deleted.");
                clearFields();
                loadProblemsFromDatabase();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to Delete Problem", "An error occurred while deleting the problem.");
            }

            // Đóng câu truy vấn
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchProblem() {
        String maSuCo = MaSuCo.getText();
        String maNhanVien = MaNhanVien.getText();
        String bienSoXe = BienSoXe.getText();
        String moTaSuCo = MoTa.getText();

        // Kiểm tra dữ liệu đầu vào
        if (maSuCo.isEmpty() && maNhanVien.isEmpty() && bienSoXe.isEmpty() && moTaSuCo.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Missing Information", "Please fill in at least one field.");
            return;
        }

        try {
            // Chuẩn bị câu truy vấn SQL
            String query = "SELECT * FROM suco WHERE MaSC LIKE ? OR MaNV LIKE ? OR BienSoXe LIKE ? OR MoTa LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + maSuCo + "%");
            statement.setString(2, "%" + maNhanVien + "%");
            statement.setString(3, "%" + bienSoXe + "%");
            statement.setString(4, "%" + moTaSuCo + "%");

            // Thực thi câu truy vấn SQL
            ResultSet resultSet = statement.executeQuery();

            // Tạo một StringBuilder để xây dựng chuỗi kết quả
            StringBuilder resultBuilder = new StringBuilder();

            // Kiểm tra từng dòng dữ liệu trong ResultSet
            while (resultSet.next()) {
                String resultMaSuCo = resultSet.getString("MaSC");
                String resultMaNhanVien = resultSet.getString("MaNV");
                String resultBienSoXe = resultSet.getString("BienSoXe");
                String resultMoTaSuCo = resultSet.getString("MoTa");
                String resultThoiGianGhiNhan = resultSet.getString("ThoiGianGhiNhan");

                // Kiểm tra nếu thuộc tính MaSC khớp với dữ liệu được nhập
                if (resultMaSuCo.equals(maSuCo) || resultMaNhanVien.equals(maNhanVien) || resultBienSoXe.equals(bienSoXe) || resultMoTaSuCo.equals(moTaSuCo)) {
                    // Xây dựng chuỗi kết quả
                    resultBuilder.append("MaSC: ").append(resultMaSuCo).append("\n");
                    resultBuilder.append("MaNV: ").append(resultMaNhanVien).append("\n");
                    resultBuilder.append("BienSoXe: ").append(resultBienSoXe).append("\n");
                    resultBuilder.append("MoTa: ").append(resultMoTaSuCo).append("\n");
                    resultBuilder.append("ThoiGianGhiNhan: ").append(resultThoiGianGhiNhan).append("\n");
                    resultBuilder.append("---------------------\n");
                }
            }

            String result = resultBuilder.toString();
            if (!result.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Results", result);
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No Results Found", "No matching records found in the database.");
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
        MaSuCo.clear();
        MaNhanVien.clear();
        BienSoXe.clear();
        MoTa.clear();
    }


}

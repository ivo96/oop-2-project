import com.mysql.cj.xdevapi.SqlDataResult;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.EventListener;


public class MainController {
    @FXML
    private TextField name;
    @FXML
    private TextField city;
    @FXML
    private TextField salary;
    @FXML
    private TextField id;
    @FXML
    private TextField updatedSalary;
    @FXML
    private TextField correlationField;

    @FXML
    private TableColumn<EmployeeModel, Integer> colEmpId;
    @FXML
    private TableColumn<EmployeeModel, String> colEmpName;
    @FXML
    private TableColumn<EmployeeModel, Double> colEmpSalary;
    @FXML
    private TableColumn<EmployeeModel, String> colEmpCity;

    @FXML
    private TableView table;

    @FXML
    private void insertEmployee(ActionEvent event)throws ClassNotFoundException, SQLException {
        EmployeeDAO.insertEmployee(name.getText(), salary.getText(), city.getText());
        ObservableList<EmployeeModel> empList = EmployeeDAO.getAllRecords();
        table.setItems(empList);
    }
    @FXML
    private void updateEmployee(ActionEvent event) throws ClassNotFoundException, SQLException {
        EmployeeDAO.updateEmployee(id.getText(), updatedSalary.getText());
        ObservableList<EmployeeModel> empList = EmployeeDAO.getAllRecords();
        table.setItems(empList);
    }
    @FXML
    private void deleteEmployee(ActionEvent event) throws ClassNotFoundException, SQLException {
        EmployeeDAO.deleteEmployee(id.getText());
        ObservableList<EmployeeModel> empList = EmployeeDAO.getAllRecords();
        table.setItems(empList);
    }

    @FXML
    private void initialize() throws Exception {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colEmpCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        ObservableList<EmployeeModel> empList = EmployeeDAO.getAllRecords();
        table.setItems(empList);
    }

    @FXML
    private void clearTextFields(ActionEvent event) throws Exception {
        name.clear();
        city.clear();
        salary.clear();
        id.clear();
        updatedSalary.clear();
        correlationField.clear();
    }

    @FXML
    private void showCorrelation(ActionEvent event) throws Exception {
        Correlation correlation = new Correlation();
        String corr = Double.toString(correlation.getCorrelation());
        correlationField.setText(corr);
    }
}

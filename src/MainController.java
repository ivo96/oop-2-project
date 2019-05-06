import com.mysql.cj.xdevapi.SqlDataResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;


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
    private void insertEmployee(ActionEvent event)throws ClassNotFoundException, SQLException {
        EmployeeDAO.insertEmployee(name.getText(), salary.getText(), city.getText());
    }
    @FXML
    private void updateEmployee(ActionEvent event) throws ClassNotFoundException, SQLException {
        EmployeeDAO.updateEmployee(id.getText(), updatedSalary.getText());
    }
    @FXML
    private void deleteEmployee(ActionEvent event) throws ClassNotFoundException, SQLException {
        EmployeeDAO.deleteEmployee(id.getText());
    }
}

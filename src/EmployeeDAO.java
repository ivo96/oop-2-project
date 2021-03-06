import com.util.ConnectionConfiguration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EmployeeDAO {
    public static void insertEmployee(String name, String salary, String city) {
        int sal = Integer.parseInt(salary);
        String insertTableSQL = "INSERT INTO employee"
                + "(name, salary, city) VALUES"
                + "(?,?,?)";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            if (connection != null) {
                System.out.println("Connection established");
                preparedStatement = connection.prepareStatement(insertTableSQL);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, sal);
                preparedStatement.setString(3, city);
                preparedStatement.executeUpdate();
                System.out.println("Record is inserted into employee table!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void updateEmployee(String id, String salary) {
        int tmpId = Integer.parseInt(id);
        System.out.print(tmpId);
        int sal = Integer.parseInt(salary);
        System.out.print(sal);
        String updateSql = "UPDATE employee " +
                "SET salary = ? WHERE id = ?;";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            if (connection != null) {
                System.out.println("Connection established");
                preparedStatement = connection.prepareStatement(updateSql);
                preparedStatement.setInt(1, sal);
                preparedStatement.setInt(2, tmpId);
                preparedStatement.executeUpdate();
                System.out.println("Record is updated into employee table!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteEmployee(String id) {
        int tmpId = Integer.parseInt(id);
//        System.out.print(tmpId);
        String deleteSql = "DELETE FROM employee WHERE id = ?;";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            if (connection != null) {
                System.out.println("Connection established");
                preparedStatement = connection.prepareStatement(deleteSql);
                preparedStatement.setInt(1, tmpId);
                preparedStatement.executeUpdate();
                System.out.println("Record is deleted from employee table!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ObservableList<EmployeeModel> getAllRecords() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        ObservableList<EmployeeModel> arrList = FXCollections.observableArrayList();
        Statement statement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            if (connection != null) {
                System.out.println("Connection established");
                statement = connection.createStatement();
                String sql = "SELECT id, name, salary, city FROM employee";
                ResultSet res = statement.executeQuery(sql);
                while (res.next()) {
                    EmployeeModel emp = new EmployeeModel();
                    emp.setId(res.getInt("id"));
                    emp.setName(res.getString("name"));
                    emp.setSalary(res.getDouble("salary"));
                    emp.setCity(res.getString("city"));
                    arrList.add(emp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return arrList;
        }
    }
}

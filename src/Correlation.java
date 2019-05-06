import com.util.ConnectionConfiguration;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.*;
import static java.lang.Math.sqrt;

public class Correlation implements ReadWriteDelete {

    private ArrayList<MonthlySalary> city1List;
    private ArrayList<MonthlySalary> city2List;

    private double correlation;

    Correlation() {
        city1List = new ArrayList<>();
        city2List = new ArrayList<>();
        this.correlation = 0;
        fillArray1();
        fillArray2();
        this.correlation = getCalculatedCorrelation();
    }

    private void fillArray1() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            if (connection != null) {
                System.out.println("Connection established");
                statement = connection.createStatement();
                String sql1 = "SELECT name, salary FROM employee WHERE city='Sofia'";
                ResultSet res1 = statement.executeQuery(sql1);
                while (res1.next()) {
                    String tempName = res1.getString("name");
                    double tempSalary = res1.getDouble("salary");
                    MonthlySalary monthlySalary = new MonthlySalary(tempName, tempSalary);
                    city1List.add(monthlySalary);
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
        }
    }

    private void fillArray2() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            if (connection != null) {
                System.out.println("Connection established");
                statement = connection.createStatement();
                String sql1 = "SELECT name, salary FROM employee WHERE city='Varna'";
                ResultSet res1 = statement.executeQuery(sql1);
                while (res1.next()) {
                    //Retrieve by column name
                    String tempName = res1.getString("name");
                    double tempSalary = res1.getDouble("salary");
                    MonthlySalary monthlySalary = new MonthlySalary(tempName, tempSalary);
                    city2List.add(monthlySalary);
                    //Print
//                    System.out.print("Varna: \n");
//                    System.out.print("Name: " + tempName);
//                    System.out.print(", Salary: " + tempSalary);
//                    System.out.print(" city:" + city);
//                    System.out.print("\n");
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
        }
    }

    private double getSUMxx() {
        double sumCity = 0;
        double multipliedSum = 0;
        for (MonthlySalary sal :
                city1List) {
            sumCity = sumCity + sal.getSalary();
            multipliedSum = multipliedSum + (sal.getSalary() + sal.getSalary());
        }
        return multipliedSum-(sumCity*sumCity/city1List.size());
    }

    private double getSUMyy() {
        double sumCity = 0;
        double multipliedSum = 0;
        for (MonthlySalary sal :
                city2List) {
            sumCity = sumCity + sal.getSalary();
            multipliedSum = multipliedSum + (sal.getSalary() + sal.getSalary());
        }
        return multipliedSum-(sumCity*sumCity/city2List.size());
    }

    private double getSUMxy() {
        double sumCity1 = 0;
        double sumCity2 = 0;
        double sumMultiplied = 0;
        for (MonthlySalary sal: city1List) {
            sumCity1 = sumCity1 + sal.getSalary();
        }
        for (MonthlySalary sal: city2List) {
            sumCity2 = sumCity2 + sal.getSalary();
        }
        Iterator itFirst = city1List.iterator();
        Iterator itSecond = city2List.iterator();
        while (itFirst.hasNext() && itSecond.hasNext()) {
            MonthlySalary city1 = (MonthlySalary) itFirst.next();
            MonthlySalary city2 = (MonthlySalary) itSecond.next();

            sumMultiplied = sumMultiplied + (city1.getSalary()*city2.getSalary());
        }
        return sumMultiplied -(sumCity1*sumCity2/(city1List.size()+city2List.size()));
    }

    public double getCalculatedCorrelation() {
        double SUMxx = getSUMxx();
        double SUMyy = getSUMyy();
        double SUMxy = getSUMxy();
        return (SUMxy/(sqrt(SUMxx*SUMyy)));
    }

    public double getCorrelation() {
        return correlation;
    }


    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }
}


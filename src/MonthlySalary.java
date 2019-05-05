public class MonthlySalary implements Comparable<MonthlySalary> {
    private String name;
    private double salary;

    MonthlySalary() {
        name = "";
        salary = 0;
    }

    MonthlySalary(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(MonthlySalary monthlySalary) {
        if (salary == monthlySalary.salary) {
            return 0;
        } else if (salary > monthlySalary.salary) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Name = " + name + " Salary = " + salary;
    }
}

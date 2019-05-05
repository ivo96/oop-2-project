import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.sqrt;

public class Correlation implements ReadWriteDelete {

    private ArrayList<MonthlySalary> city1List;
    private ArrayList<MonthlySalary> city2List;

    private double correlation;

    Correlation() {
        city1List = new ArrayList<>();
        city2List = new ArrayList<>();
        correlation = 0;
    }

    private double getSUM(ArrayList<MonthlySalary> first, ArrayList<MonthlySalary> second) {
        double sumFirst = 0;
        double sumSecond = 0;
        double multipliedSum = 0;
        Iterator itFirst = first.iterator();
        Iterator itSecond = second.iterator();
        while (itFirst.hasNext() && itSecond.hasNext()) {
            MonthlySalary city1 = (MonthlySalary) itSecond.next();
            MonthlySalary city2 = (MonthlySalary) itSecond.next();

            sumFirst = sumFirst + city1.getSalary();
            sumSecond = sumSecond + city2.getSalary();
            multipliedSum = multipliedSum + (city1.getSalary()*city2.getSalary());
        }
        int n;

        if (first.size() < second.size()) {
            n = first.size();
        } else if (first.size() > second.size()) {
            n = second.size();
        } else {
            n = first.size();
        }
        return multipliedSum-(sumFirst*sumSecond/n);
    }

    public void calculateCorrelation() {
        double SUMxx = getSUM(city1List, city1List);
        double SUMyy = getSUM(city2List, city2List);
        double SUMxy = getSUM(city1List, city2List);

        this.correlation = (SUMxy/(sqrt(SUMxx*SUMyy)));
    }

    public double getCorrelation() {
        return correlation;
    }
}


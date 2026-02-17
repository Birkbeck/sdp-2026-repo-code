package creational.prototype.employees;

import java.util.ArrayList;
import java.util.List;

public class Employees implements Cloneable {

    private final List<String> empList;

    public Employees() {
        empList = new ArrayList<>();
    }

    public Employees(List<String> list) {
        this.empList = list;
    }

    public void loadData() {
        //read all employees from database and put into the list
        empList.add("Fred");
        empList.add("James");
        empList.add("David");
        empList.add("Lisa");
    }

    public List<String> getEmpList() {
        return empList;
    }

    @Override
    public Employees clone() {
        List<String> temp = new ArrayList<>(getEmpList());
        return new Employees(temp);
    }

}
package demos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

record Employee(String name, int age) {
    public static List<Employee> load() {
        return List.of(
                new Employee("Tom Jones", 45),
                new Employee("Harry Major", 25),
                new Employee("Ethan Hardy", 65),
                new Employee("Nancy Smith", 15),
                new Employee("Deborah Sprightly", 29));
    }
}


/*
       Example of "Higher-order functions" (HoFs) ---
                     functions/methods that takes another function(s)/method(s) as arguments.
 */

public class HigherOrderFunctionsExample {

    public static void main(String... args) {
        List<Employee> employeeList = Employee.load();

        System.out.println("Employees");
        printEmployeeNames(employeeList);
        System.out.println("Employee first names");
        printEmployeeFirstNames(employeeList);
        System.out.println("Employee initials");
        printEmployeeInitials(employeeList);
    }

    public static void printEmployeeNames(List<Employee> employeeList) {
        // This is the java.util.function.Function instance which is used to map
        // an Employee object to a String value.
        // The lambda expression takes as input an Employee object and returns their name,
        // which is a String value, as output.
        Function<Employee, String> funcEmpToString = (Employee e) -> {
            return e.name();
        };

        // The list of employees is passed to method convertEmpListToNamesList() along with the Function object funcEmpToString;
        List<String> empNameList = convertEmpListToNamesList(employeeList, funcEmpToString);

        empNameList.forEach(s -> System.out.println(s));
    }

    public static void printEmployeeFirstNames(List<Employee> employeeList) {

        // Function instance funcEmpToString maps an Employee object to a String value of their name.
        // A more concise lambda expression is used here
        Function<Employee, String> funcEmpToString = e -> e.name();

        // Function instance firstNameFunction maps a name to the first name
        // using the substring method of String.
        Function<String, String> firstNameFunction =
                (String name) -> {
                    int index = name.indexOf(" ");
                    return name.substring(0, index);
                };

        // Default method compose() is used to combine firstNameFunction with funcEmpToString.
        // What the combined method does is that it first obtains the name of an Employee.
        // It then maps the full name into just their first name.
        // This combined function is passed as Function<Employee, String> parameter to
        // convertEmpListToNamesList() method along with the employee list.
        // When the convertEmpListToNamesList() applies the combined function to each of the Employee objects,
        // then the result is the list of first names of each employee.
        List<String> empFirstNameList = convertEmpListToNamesList(
                employeeList,
                firstNameFunction.compose(funcEmpToString));

        empFirstNameList.forEach(s -> System.out.println(s));
    }

    public static void printEmployeeInitials(List<Employee> employeeList) {
        // Function instance funcEmpToString maps an Employee object to a String of their name.
        // Use a method reference here (even more concise)
        Function<Employee, String> funcEmpToString = Employee::name;

        // Function instance initialFunction maps a String to its initial, or first letter.
        Function<String, String> initialFunction = s -> s.substring(0, 1);

        // Default method andThen() is used to combine initialFunction with funcEmpToString.
        // What the combined method does is that it first maps an Employee to their name and then takes out the
        // first letter from the name as a String value.
        // This combined function is passed as Function parameter to convertEmpListToNamesList()
        // method along with the employee list.
        // When the convertEmpListToNamesList() applies the combined function to each Employee object,
        // then the result is a String list with first letters of names of each employee.
        List<String> empNameListInitials = convertEmpListToNamesList(
                employeeList,
                funcEmpToString.andThen(initialFunction));

        empNameListInitials.forEach(s -> System.out.println(s));
    }

    /*
       The method convertEmpListToNamesList() iterates over all the employees in the employee list,
       applies the function funcEmpToString to each of the Employee objects,
       getting back the employee names in String format, which it puts in an Employee name list and
       sends it back to the main() method.
     */

    public static List<String> convertEmpListToNamesList(List<Employee> employeeList,
                                                         Function<Employee, String> funcEmpToString) {
        List<String> empNameList = new ArrayList<>();
        for (Employee emp : employeeList) {
            empNameList.add(funcEmpToString.apply(emp));
        }
        return empNameList;
    }
}

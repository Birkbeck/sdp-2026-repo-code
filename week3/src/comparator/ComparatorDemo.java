package comparator;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.*;

record Person(String firstName, String middleName, String lastName) {

    public Person(String firstName, String lastName) {
        this(firstName, null, lastName);
    }

    public String name() {
        return middleName == null
                ? firstName + " " + lastName
                : firstName + " " + middleName + " " + lastName;
    }

    @Override
    public String toString() {
        return name();
    }
}


public class ComparatorDemo {
    public static void main(String[] args) {
        Person[] people = {
                new Person("George", "Washington"),
                new Person("John", "Adams"),
                new Person("Thomas", "Jefferson"),
                new Person("James", "Madison"),
                new Person("James", "Monroe"),
                new Person("John", "Quincy", "Adams"),
                new Person("Andrew", "Jackson"),
                new Person("Martin", "van Buren"),
                new Person("William", "Henry", "Harrison"),
                new Person("John", "Tyler"),
                new Person("James", "Knox", "Polk"),
                new Person("Zachary", "Taylor"),
                new Person("Millard", "Fillmore"),
                new Person("Franklin", "Pierce"),
                new Person("James", "Buchanan"),
                new Person("Abraham", "Lincoln"),
                new Person("Andrew", "Johnson"),
                new Person("Ulysses", "S.", "Grant"),
                new Person("Rutherford", "Birchard", "Hayes"),
                new Person("James", "Abram", "Garfield"),
                new Person("Grover", "Cleveland"),
                new Person("Benjamin", "Harrison"),
                new Person("Grover", "Cleveland"),
                new Person("William", "McKinley"),
                new Person("Theodore", "Roosevelt"),
                new Person("William", "Howard", "Taft"),
                new Person("Woodrow", "Wilson"),
                new Person("Warren", "Gamaliel", "Harding"),
                new Person("Calvin", "Coolidge"),
                new Person("Herbert", "Hoover"),
                new Person("Franklin", "Delano", "Roosevelt"),
                new Person("Harry", "S.", "Truman"),
                new Person("Dwight", "David", "Eisenhower"),
                new Person("John", "Fitzgerald", "Kennedy"),
                new Person("Lyndon", "Baines", "Johnson"),
                new Person("Richard", "Mulhouse", "Nixon"),
                new Person("Gerald", "Ford"),
                new Person("James", "Earl", "Carter"),
                new Person("Ronald", "Reagan"),
                new Person("George", "Herbert Walker", "Bush"),
                new Person("William", "Jefferson", "Clinton"),
                new Person("George", "Walker", "Bush"),
                new Person("Barack", "Hussein", "Obama")
        };

        Arrays.sort(people,
                Comparator.comparing(Person::name));
        System.out.println("by name: " + Arrays.toString(people));

        Arrays.sort(people,
                Comparator.comparing(Person::lastName)
                    .thenComparing(Person::firstName));
        System.out.println("by last name, then first name: " + Arrays.toString(people));

        Arrays.sort(people,
                Comparator.comparing(Person::name,
                        comparingInt(String::length)));
        System.out.println("by the length of the name: " + Arrays.toString(people));

        Arrays.sort(people,
                comparing(Person::middleName, nullsFirst(naturalOrder())));
        System.out.println("by the middle name, with nulls first: " + Arrays.toString(people));

        Arrays.sort(people,
                comparing(Person::name, reverseOrder()));
        System.out.println("by name in reverse order: " + Arrays.toString(people));
    }
}

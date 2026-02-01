package five;

import java.util.List;
import java.util.stream.Stream;

//Use map() to create a new stream that contains only
//selected aspects of the original stream.
public class StreamDemo {
    public static void main(String[] args) {
    // A list of names, phone numbers, and e-mail addresses.
        List<NamePhoneEmail> myList = List.of(
            new NamePhoneEmail("Fred", "555-5555", "Fred@dodgy.com"),
            new NamePhoneEmail("James", "555-4444", "James@dodgy.com"),
            new NamePhoneEmail("Mary", "555-3333", "Mary@dodgy.com"));
        System.out.println("Original values in myList: ");
        myList.stream().forEach(a ->
            System.out.println(a.name() + " " + a.phone() + " "
                    + a.email())
        );
        System.out.println();
        // Map just the names and phone numbers to a new stream.
        Stream<NamePhone> nameAndPhone = myList.stream()
                .map(a -> new NamePhone(a.name(), a.phone()));
        System.out.println("List of names and phone numbers: ");
        nameAndPhone.forEach(a ->
            System.out.println(a.name() + " " + a.phone())
        );
    }
}

record NamePhoneEmail(String name, String phone, String email) {
}

record NamePhone(String name, String phone) {
}
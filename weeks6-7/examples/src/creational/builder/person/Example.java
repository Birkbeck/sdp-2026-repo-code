package creational.builder.person;


public class Example {
    public static void main(String[] args) {
        Person p = Person.Builder
                .aPerson()
                .withFirst("Fred")
                .withSecond("Bloggs")
                .withAge(12)
                .build();
        System.out.println(p);
    }
}

// builder for mutable objects
class Person {
    private String first;
    private String second;
    private int age;
    private String address;

    // default constructor - private (accessible only from Builder)
    private Person() {
    }

    // copy constructor
    private Person(Person person) {
        this.first = person.first;
        this.second = person.second;
        this.age = person.age;
        this.address = person.address;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
            "first='" + first + '\'' +
            ", second='" + second + '\'' +
            ", age=" + age +
            ", address='" + address + '\'' +
            '}';
    }

    public static final class Builder {
        private final Person person;

        private Builder() {
            person = new Person();
        }

        public static Builder aPerson() {
            return new Builder();
        }

        public Builder withFirst(String first) {
            person.setFirst(first);
            return this;
        }

        public Builder withSecond(String second) {
            person.setSecond(second);
            return this;
        }

        public Builder withAge(int age) {
            person.setAge(age);
            return this;
        }

        public Builder withAddress(String address) {
            person.setAddress(address);
            return this;
        }

        public Person build() {
            return new Person(person);
        }
    }
}



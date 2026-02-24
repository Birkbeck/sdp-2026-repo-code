package reflection.question4;

import java.io.File;

record SimpleTestClass(int i, String s, double d, float f, char ch) {
}

public class Driver {

    public static void main(String[] args) {
        String s = "Hello";
        System.out.println(UniversalToString.toString(s));
        File f = new File("test.txt");
        System.out.println(UniversalToString.toString(f));
        SimpleTestClass t = new SimpleTestClass(1, "str", 2, 4, 'a');
        System.out.println(UniversalToString.toString(t));
    }
}
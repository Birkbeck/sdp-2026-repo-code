package variablecapture;


@FunctionalInterface
interface MyFactory {
    String create(char[] chars);
}

public class VariableCaptureExample {
    public static void main(String[] args) {
        MyFactory myFactory = chars -> new String(chars);

        String myString = "Test"; // needs to be effectively final to be used in a lambda

        myFactory = chars -> myString + ":" + new String(chars);

        // myString = ""; is not allowed because myString needs to be effectively final
    }
}

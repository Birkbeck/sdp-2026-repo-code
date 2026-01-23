package lambdas;

import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        /*
         * 1. Supplier instance with a lambda expression
         * We define a lambda expression which takes no input and returns a new String object with value set to “hello”.
         * This lambda is then assigned to a Supplier<String> instance named helloStrSupplier.
         * Invoking functional method get() on helloStrSupplier results in a String helloStr,
         *  which is then printed to show that it indeed contains the value “hello”.
         */
        Supplier<String> helloStrSupplier = () -> "Hello";
        String helloStr = helloStrSupplier.get();
        System.out.println("helloStr is ->" + helloStr + "<-");

        /*
         * 1. Supplier instance with a lambda expression
         * We define a lambda expression which takes no input and returns a String object
         * obtained by calling Currency.getInstance(Locale.CHINA).getCurrencyCode().
         * This lambda is then assigned to a Supplier<String> instance named currencyCodeSupplier.
         * Invoking functional method get() on currencyCodeSupplier results in a String "CNY", which is then printed.
         */
        Supplier<String> currencyCodeSupplier =
                () -> Currency.getInstance(Locale.CHINA).getCurrencyCode();
        String currencyCode = currencyCodeSupplier.get();
        System.out.println("currencyCode is ->" + currencyCode + "<-");

        /*
         * 2. Supplier instance using method reference to default constructor
         * We create a Supplier<String> instance named emptyStrSupplier.
         * emptyStrSupplier is then used to create a String object named emptyStr using the get() method.
         * emptyStr‘s value is then printed to show its value is empty as defined.
         */
        Supplier<String> emptyStrSupplier = String::new;
        String emptyStr = emptyStrSupplier.get();
        System.out.println("emptyStr is ->" + emptyStr + "<-");

        /*
         * 3. Supplier instance using method reference to a static method.
         * The getSystemDate() method of SupplierFunctionExample class is used
         * to create a Supplier<Date> instance named dateSupplier.
         * dateStrSupplier is then used to create a Date object named systemDate by invoking get() method on it.
         * systemDate‘s value is then printed to show its value.
         */
        Supplier<Date> dateSupplier = SupplierExample::getSystemDate;
        Date systemDate = dateSupplier.get();
        System.out.println("systemDate is ->" + systemDate + "<-");
    }

    /*
     * getSystemDate() is a static method which simply returns the current system date and does not take any input.
     * The method signature matches the function descriptor of Supplier i.e. () -> T .
     * In the main() method we have shown how to instantiate a Supplier interface instance in following three ways –
     */
    public static Date getSystemDate() {
        return new Date();
    }
}


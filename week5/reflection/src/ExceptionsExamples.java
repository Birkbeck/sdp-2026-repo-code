import java.io.FileReader;
import java.io.IOException;

public class ExceptionsExamples {
    public static void main(String... args) {
        String connection = getConnectionString();

        int i = 1, j = 0;
        try {
            int k = i / j;
        }
        // it's better to check for 0 (rather than catching the exception)
        catch (ArithmeticException e) {
            // replacing the message in an exception is pointless
            throw new ArithmeticException("division by zero");
        }
    }

    // this is how checked exception help to separate normal flow from handling errors
    public static String getConnectionString2() {
        try (var reader = new FileReader("connection.properties")) {
            char[] buffer = new char[1024];
            reader.read(buffer);
            return new String(buffer);
        }
        catch (IOException e) {
            return "jdbc:mysql://localhost:3306/";
        }
    }

    // old-school way of dealing with errors
    // (relies on magic numbers and endless status checks)
    public static String getConnectionString() {
        int file_id = open_file("connection.properties");
        if (file_id == -1) {
            int error = get_error();
            System.err.println("Error opening a file: " + error);
            System.exit(-1);
        }
        byte[] buffer = new byte[1024];
        int error = read_from_file(file_id, buffer, 1024);
        if (error != -1) {
            System.err.println("Error reading a file: " + error);
            System.exit(-2);
        }
        close_file(file_id);
        return new String(buffer);
    }

    public static int open_file(String filename) {
        return 0;
    }

    public static int get_error() {
        return 0;
    }

    public static void close_file(int file_id) {

    }

    public static int read_from_file(int file_id, byte[] buffer, int buffer_len) {
        return 0;
    }
}

package template;

import java.util.Random;

public class Resource implements AutoCloseable {
    public Resource() {
        System.out.println("Resource created");
    }

    public void useResource() {
        riskyOperation();
        System.out.println("Resource used");
    }

    public void employResource() {
        riskyOperation();
        System.out.println("Resource employed");
    }

    @Override
    public void close() {
        System.out.println("Resource disposed");
    }

    private void riskyOperation() {
        if (new Random().nextInt(10) == 0) {
            throw new RuntimeException();
        }
    }
}

class IntContainer {
    public int i;
}

public class ExceptionsDemo {
    static void main() {
        IntContainer container = new IntContainer();
        try {
            m(1, container);
        }
        catch (NullPointerException e) {
            IO.println("Caught NullPointerException with container = " + container.i);
        }
    }

    static void m(int i, IntContainer c) {
        try {
            i++;
            c.i = i;
            if (i == 5)
                throw new NullPointerException();
            m(i, c);
            IO.println("after calling m with i = " + i);
        }
        finally {
            IO.println("exiting m with with i = " + i);
        }
    }
}

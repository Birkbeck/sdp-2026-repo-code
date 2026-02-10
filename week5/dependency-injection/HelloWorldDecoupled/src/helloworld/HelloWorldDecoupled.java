package helloworld;

public class HelloWorldDecoupled {
    public static void main(String... args) {
        var mr = new StandardOutMessageRenderer();
        var mp = new HelloWorldMessageProvider();
        mr.setMessageProvider(mp);
        mr.render();
    }
}

















// With var, the compiler *infers* the type of the variables
// from the expression on the right-hand side
// (StandardOutMessageRenderer and HelloWorldMessageProvider, respectively).

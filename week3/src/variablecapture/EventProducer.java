package variablecapture;

import java.util.function.Function;

@FunctionalInterface
public interface EventProducer {
    <T, R> void listen(Function<T, R> o);
}

class EventConsumerImpl {
    private final String name = "MyConsumer";

    public void attach(EventProducer eventProducer) {
        // lambda expressions can access all instance variables/methods
        eventProducer.listen(e -> {
            // this in a lambda expression refers to the object whose method creates the lambda
            // (in contrast, in anonymous classes this refers to the instance of the anonymous class)
            System.out.println(this.name);
            return this.name;
        });
    }
}

class EventConsumerStaticImpl {
    private static final String someStaticVar = "Some text";

    public void attach(EventProducer eventProducer) {
        // lambda expressions can also access static variables/methods
        eventProducer.listen(e -> {
            System.out.println(someStaticVar);
            return someStaticVar;
        });
    }
}

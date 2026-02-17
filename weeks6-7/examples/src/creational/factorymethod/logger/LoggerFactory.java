package creational.factorymethod.logger;

public abstract class LoggerFactory {

    public static LoggerFactory getFactory(String f) throws ReflectiveOperationException {
        return Class.forName(f)
                .asSubclass(LoggerFactory.class)
                .getDeclaredConstructor()
                .newInstance();
    }

    public abstract Logger getLogger();
}

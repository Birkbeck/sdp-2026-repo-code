public abstract class AbstractNotifier implements Notifier {

    protected String generateWeatherAlert(String weatherConditions) {
        return "It is " + weatherConditions;
    }
}

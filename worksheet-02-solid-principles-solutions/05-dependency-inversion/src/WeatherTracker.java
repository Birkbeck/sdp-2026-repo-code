import java.util.List;

/**
 * WeatherTracker depends on Notifier
 * Emailer depends on Notifier
 * Phone depends on Notifier
 */

public class WeatherTracker {
    private String currentConditions;
    private final List<Notifier> notifierList;

    @Deprecated
    WeatherTracker() {
        this(List.of(new Emailer(), new Phone()));
    }

    public WeatherTracker(List<Notifier> notifierList) {
        this.notifierList = notifierList;
    }

    public String getCurrentConditions() {
        return currentConditions;
    }

    public void setCurrentConditions(String weatherDescription) {
        currentConditions = weatherDescription;
        for (Notifier notifier : notifierList)
            notifier.notify(weatherDescription);
    }
}

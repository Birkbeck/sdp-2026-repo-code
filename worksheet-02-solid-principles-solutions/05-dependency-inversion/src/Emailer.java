
public class Emailer extends AbstractNotifier {
    @Override
    public void notify(String weatherConditions) {
        if ("sunny".equals(weatherConditions)) {
            String alert = generateWeatherAlert(weatherConditions);
            System.out.print(alert);
        }
    }
}

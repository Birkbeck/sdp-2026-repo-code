
public class Phone extends AbstractNotifier {
    @Override
    public void notify(String weatherConditions) {
        if ("rainy".equals(weatherConditions)) {
            String alert = generateWeatherAlert(weatherConditions);
            System.out.print(alert);
        }
    }
}

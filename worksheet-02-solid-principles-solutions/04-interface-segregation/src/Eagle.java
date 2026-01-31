public class Eagle implements FlyingCreature, FeatheredCreature {
    private String currentLocation;
    private int numberOfFeathers;

    public Eagle(int initialNumberOfFeathers) {
        this.numberOfFeathers = initialNumberOfFeathers;
    }

    @Override
    public void fly() {
        this.currentLocation = "in the air";
    }

    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void molt() {
        this.numberOfFeathers--;
    }

    @Override
    public int getNumberOfFeathers() {
        return numberOfFeathers;
    }
}

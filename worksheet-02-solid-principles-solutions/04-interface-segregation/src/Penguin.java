public class Penguin implements SwimmingCreature, FeatheredCreature {
    private String currentLocation;
    private int numberOfFeathers;

    public Penguin(int initialFeatherCount) {
        this.numberOfFeathers = initialFeatherCount;
    }

    @Override
    public void swim() {
        this.currentLocation = "in the water";
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

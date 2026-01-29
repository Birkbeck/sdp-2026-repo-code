package solid.refactored.dip;

public interface ButtonClient {
    void turnOn();
    void turnOff();
}

class Lamp implements ButtonClient {
    @Override
    public void turnOn() { /* implementation */ }
    @Override
    public void turnOff() { /* implementation */ }
}


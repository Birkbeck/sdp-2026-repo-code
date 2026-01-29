package solid.refactored.dip;

public interface Button {
    void push();
}

class ButtonImpl implements Button {
    private ButtonClient client;
    private boolean state;

    ButtonImpl(ButtonClient client) { this.client = client; }

    @Override
    public void push() {
        state = !state;
        if (state)
            client.turnOn();
        else
            client.turnOff();
    }
}

package solid.refactored.isp;

public class TimedDoor implements Door {
    @Override
    public void lock() {
        /* implementation */
    }

    @Override
    public void unlock() {
        /* implementation */
    }

    @Override
    public boolean isOpen() {
        /* implementation */
        return false;
    }

    void timeout() {
        /* implementation */
    }
}

class TimedDoorAdapter implements TimerClient {
    private final TimedDoor door;

    TimedDoorAdapter(TimedDoor door) {
        this.door = door;
    }

    @Override
    public void timeout() {
        door.timeout();
    }
}

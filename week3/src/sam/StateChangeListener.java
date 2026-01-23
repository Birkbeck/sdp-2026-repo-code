package sam;

@FunctionalInterface
public interface StateChangeListener {
    void onStateChange(State oldState, State newState);
}
package behavioural.state.mp3player;

public class PlayingState implements State {
    public void pressPlay(MP3PlayerContext context) {
        context.setState(new StandbyState());
    }
}
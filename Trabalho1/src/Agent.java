import jdk.jshell.spi.ExecutionControl;
import tiles.Tile;

public class Agent extends Tile {
    private boolean foundTarget;
    private int initialX;
    private int initialY;

    public Agent(int x, int y) {
        super(x, y);
        initialX = x;
        initialY = y;
    }

    @Override
    public String getRep() {
        return ">";
    }

    public boolean foundTarget() {
        return foundTarget;
    }

    public void move(int x, int y) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not yet");
    }

    public Tile checkLeft() {

    }

    public Tile checkRight() {

    }

    public Tile checkFront() {

    }

}

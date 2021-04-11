import jdk.jshell.spi.ExecutionControl;
import tiles.Target;
import tiles.Tile;

import java.util.ArrayList;
import java.util.LinkedList;

public class Agent extends Tile {
    private boolean foundTarget;
    private int initialX;
    private int initialY;
    private Direction dir = Direction.LEFT;

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

    public void move(int x, int y) {

    }

    public Tile checkLeft(LinkedList<Tile> tiles) {
        return null;
    }

    public Tile checkRight(LinkedList<Tile> tiles) {
        return null;
    }

    public Tile checkForTarget(LinkedList<Tile> tiles) {
        for (Tile tile :tiles) {
            if (tile instanceof Target) {
                return tile;
            }
        }
        return tiles.getFirst();
    }

    public void scanTarget(Environment map) {

    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void moveFront() {

    }
}

import jdk.jshell.spi.ExecutionControl;
import tiles.Object;
import tiles.Target;
import tiles.Tile;

import java.util.ArrayList;
import java.util.LinkedList;

public class Agent extends Tile {
    private LinkedList<Tile> crossedTiles = new LinkedList<>();
    private boolean foundTarget;
    private int initialX;
    private int initialY;
    private Direction dir = Direction.RIGHT;
    private Direction turn = Direction.RIGHT;

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

    public void move(LinkedList<Tile> tilesFront, LinkedList<Tile> tiles45Right, LinkedList<Tile> tiles45Left) {
        if (!(tilesFront.getFirst() instanceof Object)) {
            super.setY(this.getY() + 1);
        }
        else if (!(tiles45Right.getFirst() instanceof Object) && turn == Direction.RIGHT) {
            super.setX(tiles45Right.getFirst().getX());
            super.setY(tiles45Right.getFirst().getY());
            this.dir = Direction.changeDirection(this, turn);
        }
        else if (!(tiles45Left.getFirst() instanceof Object) && turn == Direction.LEFT) {
            super.setX(tiles45Left.getFirst().getX());
            super.setY(tiles45Left.getFirst().getY());
            this.dir = Direction.changeDirection(this, turn);
        }
        else {
            this.dir = Direction.changeDirection(this, turn);
        }

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

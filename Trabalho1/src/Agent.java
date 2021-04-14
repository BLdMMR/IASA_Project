import jdk.jshell.spi.ExecutionControl;
import tiles.*;
import tiles.Object;

import java.util.ArrayList;
import java.util.LinkedList;

public class Agent extends Tile {
    private LinkedList<Tile> crossedTiles = new LinkedList<>();
    private boolean foundTarget;
    private int turnCounter = 0;
    private Direction dir = Direction.RIGHT;
    private Direction turn = Direction.RIGHT;

    public Agent(int x, int y) {
        super(x, y);
        crossedTiles.addFirst(new InitialPoint(x, y));
    }

    @Override
    public String getRep() {
        return ">";
    }

    public boolean foundTarget() {
        return foundTarget;
    }

    public void move(LinkedList<Tile> tilesFront, LinkedList<Tile> tiles45Right, LinkedList<Tile> tiles45Left) {
        if (turnCounter == 4) {
            if (turn == Direction.LEFT) turn = Direction.RIGHT;
            else turn = Direction.LEFT;
            turnCounter = 0;
        }
        if (!(tilesFront.getFirst() instanceof Object)) {
            super.setY(tilesFront.getFirst().getY());
            super.setX(tilesFront.getFirst().getX());

        }
        else if (!(tiles45Right.getFirst() instanceof Object) && turn == Direction.RIGHT) {
            super.setX(tiles45Right.getFirst().getX());
            super.setY(tiles45Right.getFirst().getY());
            this.dir = Direction.changeDirection(this, turn);
            ++turnCounter;
        }
        else if (!(tiles45Left.getFirst() instanceof Object) && turn == Direction.LEFT) {
            super.setX(tiles45Left.getFirst().getX());
            super.setY(tiles45Left.getFirst().getY());
            this.dir = Direction.changeDirection(this, turn);
            ++turnCounter;
        }
        else {
            this.dir = Direction.changeDirection(this, turn);
        }
        crossedTiles.addLast(new Crossed(super.getX(), super.getY()));
    }

    public boolean checkForTarget(LinkedList<Tile> tilesToAnalyse) {
        for (Tile tile : tilesToAnalyse) {
            if (tile instanceof Target) {
                return true;
            }
        }
        return false;
    }

    public Direction getDir() {
        return dir;
    }

    public void moveToTarget(LinkedList<Tile> toAnalyse) {
        Tile dst = toAnalyse.removeFirst();
        while (!(dst instanceof Target)) {
            super.setX(dst.getX());
            super.setY(dst.getY());
            crossedTiles.add(new Crossed(dst.getX(), dst.getY()));
            dst = toAnalyse.removeFirst();
        }
        super.setX(dst.getX());
        super.setY(dst.getY());
        crossedTiles.add(this);
        this.foundTarget = true;

    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public Direction getTurn() {
        return turn;
    }

    public LinkedList<Tile> getCrossed() {
        return crossedTiles;
    }
}

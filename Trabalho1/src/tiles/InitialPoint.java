package tiles;

public class InitialPoint extends Tile{
    public InitialPoint(int x, int y) {
        super(x, y);
    }

    @Override
    public String getRep() {
        return "X";
    }
}

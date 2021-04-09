package tiles;

public class Target extends Tile{
    public Target(int x, int y) {
        super(x, y);
    }

    @Override
    public String getRep() {
        return "A";
    }
}

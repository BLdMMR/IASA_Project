package tiles;

public class Crossed extends Tile{
    public Crossed(int x, int y) {
        super(x, y);
    }

    @Override
    public String getRep() {
        return "*";
    }
}

package tiles;

public class Empty extends Tile{
    public Empty(int x, int y) {
        super(x, y);
    }

    @Override
    public String getRep() {
        return ".";
    }
}

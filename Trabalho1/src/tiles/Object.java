package tiles;

public class Object extends Tile{
    public Object(int x, int y) {
        super(x, y);
    }

    @Override
    public String getRep() {
        return "O";
    }

}

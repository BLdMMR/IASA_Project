import tiles.Empty;
import tiles.Object;
import tiles.Target;
import tiles.Tile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Environment {
    private int LINE_SIZE;
    private ArrayList<Tile> map = new ArrayList<>();
    private Agent agent;


    public Environment() {
        try (FileInputStream is = new FileInputStream("D:\\ISEL\\2021v\\IASA\\Trabalho1\\src\\amb_p1.das")) {
            byte[] bytes = is.readAllBytes();

            for (int i = 0; i < bytes.length; i++) {
                if ((char)bytes[i] == ' '){
                    LINE_SIZE = i;
                    break;
                }
            }

            int x = 0;
            int y = 0;
            for(int i = 0; i < bytes.length; ++i) {

                if (x == LINE_SIZE) {
                    ++y;
                    ++i;
                    x = 0;
                }

                char tileChar = (char) bytes[i];
                switch (tileChar) {
                    case 'O': {
                        map.add(new Object(x++, y));
                        break;
                    }
                    case '.': {
                        map.add(new Empty(x++, y));
                        break;
                    }
                    case 'A': {
                        map.add(new Target(x++, y));
                        break;
                    }
                    case '>': {
                        this.agent = new Agent(x++, y);
                        map.add(this.agent);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error with reading environment setting file");
        }
    }

    public ArrayList<Tile> getMap() {
        return map;
    }

    public void printMap() {
        int i = 0;
        for(Tile tile : map) {
            if (i == LINE_SIZE) {
                System.out.println();
                i = 0;
            }
            System.out.print(tile.getRep());
            ++i;
        }
    }

    public int findElementInMap(int x, int y) {
        for(Tile tile :map) {
            if (tile.getX() == x && tile.getY() == y) {
                return map.indexOf(tile);
            }
        }
        return -1;
    }

    public Agent getAgent() {
        return agent;
    }
}

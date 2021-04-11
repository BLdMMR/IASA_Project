import tiles.Empty;
import tiles.Object;
import tiles.Target;
import tiles.Tile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Environment {
    private int NUM_OF_COLLS;
    private int NUM_OF_LINES = 1;

    private LinkedList<LinkedList<Tile>> map = new LinkedList<>();

    private Agent agent;


    public Environment() {
        File inFile = new File("./src/amb_p1.das");
        try (Scanner in = new Scanner(inFile)) {
            in.useDelimiter("[/n]");
            int lineCount = 0;

            while (in.hasNextLine()) {
                map.add(lineCount, new LinkedList<>());

                char[] chars = in.nextLine().trim().toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char tileChar = chars[i];
                    switch (tileChar) {
                        case 'O': {
                            map.get(lineCount).add(new Object(lineCount, i));
                            break;
                        }
                        case '.': {
                            map.get(lineCount).add(new Empty(lineCount, i));
                            break;
                        }
                        case '>': {
                            map.get(lineCount).add(new Agent(lineCount, i));
                            this.agent = (Agent) map.get(lineCount).get(i);
                            break;
                        }
                        case 'A': {
                            map.get(lineCount).add(new Target(lineCount, i));
                            break;
                        }

                    }
                }
                ++lineCount;
            }
            NUM_OF_COLLS = map.getFirst().size();
            NUM_OF_LINES = lineCount;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printMap() {
        for (int i = 0; i < NUM_OF_LINES; i++) {
            for (int j = 0; j < NUM_OF_COLLS; j++) {
                System.out.print(map.get(i).get(j).getRep());
            }
            System.out.println();
        }
    }

    public Tile getTileFromCoordinates(int x, int y) {
        return map.get(x).get(y);
    }

    public LinkedList<Tile> getTilesLeft(int x, int y, Direction dir) {
        LinkedList<Tile> tiles45Left = new LinkedList<>();
        Tile tile;
        int i = 1;
        switch (dir) {
            case LEFT : {
                while ((tile = getTileFromCoordinates(x - i, y + i)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
            case RIGHT : {
                while ((tile = getTileFromCoordinates(x + i, y - i)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
            case DOWN : {
                while ((tile = getTileFromCoordinates(x + i, y + i)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
            case UP : {
                while ((tile = getTileFromCoordinates(x - i, y - i)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
        }
        return tiles45Left;
    }

    public LinkedList<Tile> getTilesRight(int x, int y, Direction dir) {
        LinkedList<Tile> tiles45Left = new LinkedList<>();
        Tile tile;
        int i = 1;
        switch (dir) {
            case LEFT : {
                while ((tile = getTileFromCoordinates(x - i, y - i)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
            case RIGHT : {
                while ((tile = getTileFromCoordinates(x + i, y + i)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
            case DOWN : {
                while ((tile = getTileFromCoordinates(x - i, y + i)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
            case UP : {
                while ((tile = getTileFromCoordinates(x + i, y - i)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
        }
        return tiles45Left;
    }

    public LinkedList<Tile> getTilesFront(int x, int y, Direction dir) {
        LinkedList<Tile> tiles45Left = new LinkedList<>();
        Tile tile;
        int i = 1;
        switch (dir) {
            case LEFT : {
                while ((tile = getTileFromCoordinates(x - i, y)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
            case RIGHT : {
                while ((tile = getTileFromCoordinates(x + i, y)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
            case DOWN : {
                while ((tile = getTileFromCoordinates(x, y + i)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
            case UP : {
                while ((tile = getTileFromCoordinates(x, y - i)) instanceof Object){
                    tiles45Left.add(tile);
                    ++i;
                }
            }
        }
        return tiles45Left;
    }

    public Agent getAgent() {
        return agent;
    }
}

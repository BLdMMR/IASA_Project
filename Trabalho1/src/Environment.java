import tiles.*;
import tiles.Object;

import javax.script.ScriptEngine;
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
        int xFactor = 0;
        int yFactor = 0;
        switch(dir) {
            case LEFT: {
                xFactor = 1;
                yFactor = -1;
                break;
            }
            case RIGHT: {
                xFactor = -1;
                yFactor = 1;
                break;
            }
            case UP: {
                xFactor = -1;
                yFactor = -1;
                break;
            }
            case DOWN: {
                xFactor = 1;
                yFactor = 1;
                break;
            }
            case UPLEFT: {
                xFactor = 0;
                yFactor = -1;
                break;
            }
            case UPRIGHT: {
                xFactor = -1;
                yFactor = 0;
                break;
            }
            case DOWNLEFT: {
                xFactor = 1;
                yFactor = 0;
                break;
            }
            case DOWNRIGHT: {
                xFactor = 0;
                yFactor = 1;
                break;
            }
        }
        int i = 1;

        while (true) {
            Tile toAdd = getTileFromCoordinates(x + (i * xFactor), y + (i * yFactor));
            if (toAdd instanceof Object) {
                tiles45Left.add(toAdd);
                break;
            }
            tiles45Left.add(toAdd);
            i++;
        }
        return tiles45Left;
    }

    public LinkedList<Tile> getTilesRight(int x, int y, Direction dir) {
        LinkedList<Tile> tiles45Right = new LinkedList<>();
        int xFactor = 0;
        int yFactor = 0;
        switch(dir) {
            case LEFT: {
                xFactor = -1;
                yFactor = -1;
                break;
            }
            case RIGHT: {
                xFactor = 1;
                yFactor = 1;
                break;
            }
            case UP: {
                xFactor = -1;
                yFactor = 1;
                break;
            }
            case DOWN: {
                xFactor = 1;
                yFactor = -1;
                break;
            }
            case UPLEFT: {
                xFactor = -1;
                yFactor = 0;
                break;
            }
            case UPRIGHT: {
                xFactor = 0;
                yFactor = 1;
                break;
            }
            case DOWNLEFT: {
                xFactor = 0;
                yFactor = -1;
                break;
            }
            case DOWNRIGHT: {
                xFactor = 1;
                yFactor = 0;
                break;
            }
        }
        int i = 1;

        while (true) {
            Tile toAdd = getTileFromCoordinates(x + (i * xFactor), y + (i * yFactor));
            if (toAdd instanceof Object) {
                tiles45Right.add(toAdd);
                break;
            }
            tiles45Right.add(toAdd);
            i++;
        }
        return tiles45Right;
    }

    public LinkedList<Tile> getTilesFront(int x, int y, Direction dir) {
        LinkedList<Tile> tilesFront = new LinkedList<>();
        int xFactor = 0;
        int yFactor = 0;
        switch(dir) {
            case LEFT: {
                xFactor = 0;
                yFactor = -1;
                break;
            }
            case RIGHT: {
                xFactor = 0;
                yFactor = 1;
                break;
            }
            case UP: {
                xFactor = -1;
                yFactor = 0;
                break;
            }
            case DOWN: {
                xFactor = 1;
                yFactor = 0;
                break;
            }
            case UPLEFT: {
                xFactor = -1;
                yFactor = -1;
                break;
            }
            case UPRIGHT: {
                xFactor = -1;
                yFactor = 1;
                break;
            }
            case DOWNLEFT: {
                xFactor = 1;
                yFactor = -1;
                break;
            }
            case DOWNRIGHT: {
                xFactor = 1;
                yFactor = 1;
                break;
            }
        }
        int i = 1;

        while (true) {
            Tile toAdd = getTileFromCoordinates(x + (i * xFactor), y + (i * yFactor));
            if (toAdd instanceof Object) {
                tilesFront.add(toAdd);
                break;
            }
            tilesFront.add(toAdd);
            i++;
        }

        return tilesFront;
    }

    public Agent getAgent() {
        return agent;
    }

    public void updateMap(LinkedList<Tile> crossed) {
        for (Tile tile : crossed) {
            map.get(tile.getX()).remove(tile.getY());
            map.get(tile.getX()).add(tile.getY(), tile);
        }
    }
}

import tiles.Empty;
import tiles.Target;
import tiles.Tile;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    public static void main(String[] args) {
        Environment env = new Environment();
        env.printMap();
        Agent agent = env.getAgent();
//        System.out.println("Agent Coordinates: " + agent.getX() + ", " + agent.getY());
//        System.out.println("Agent Direction: " + agent.getDir());
//        for(Tile tile :env.getTilesFront(13, 16, Direction.UP)) {
//                System.out.println(tile);
//        }
        int i = 0;
        while(!agent.foundTarget()) {

            LinkedList<Tile> toAnalyseFront = env.getTilesFront(agent.getX(), agent.getY(), agent.getDir());
            printList(toAnalyseFront);
            if (checkForTarget(toAnalyseFront)) {
                //move to target
            }
            LinkedList<Tile> toAnalyseRight = env.getTilesRight(agent.getX(), agent.getY(), agent.getDir());
            printList(toAnalyseRight);
            if (checkForTarget(toAnalyseRight)) {
                //move to target
            }
            LinkedList<Tile> toAnalyseLeft = env.getTilesLeft(agent.getX(), agent.getY(), agent.getDir());
            printList(toAnalyseLeft);
            if (checkForTarget(toAnalyseLeft)) {
                //move to target
            }
            //if target not found
            agent.move(toAnalyseFront, toAnalyseRight, toAnalyseLeft);
            System.out.println(agent.getX() + ", " + agent.getY() + "->" + agent.getDir());

            if (i++ == 14) break;
        }
    }

    public static void printList(List list) {
        for (var elem : list) {
            System.out.println(elem);
        }
        System.out.println("---------------");
    }

    public static boolean checkForTarget(LinkedList<Tile> tilesToAnalyse) {
        for (Tile tile : tilesToAnalyse) {
            if (tile instanceof Target) {
                System.out.println("Target Found");
                return true;
                //move to target
            }
        }
        return false;
    }

}

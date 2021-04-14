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
        System.out.println("----------------------");
        Agent agent = env.getAgent();

        while(!agent.foundTarget()) {

            LinkedList<Tile> toAnalyseFront = env.getTilesFront(agent.getX(), agent.getY(), agent.getDir());
            if (agent.checkForTarget(toAnalyseFront)) {
                agent.moveToTarget(toAnalyseFront);
                break;
            }
            LinkedList<Tile> toAnalyseRight = env.getTilesRight(agent.getX(), agent.getY(), agent.getDir());
            if (agent.checkForTarget(toAnalyseRight)) {
                agent.moveToTarget(toAnalyseRight);
                break;
            }
            LinkedList<Tile> toAnalyseLeft = env.getTilesLeft(agent.getX(), agent.getY(), agent.getDir());
            if (agent.checkForTarget(toAnalyseLeft)) {
                agent.moveToTarget(toAnalyseLeft);
                break;
            }
            //if target not found
            agent.move(toAnalyseFront, toAnalyseRight, toAnalyseLeft);
        }
        env.updateMap(agent.getCrossed());
        env.printMap();
    }

    public static void printList(LinkedList<Tile> list) {
        for (Tile elem : list) {
            System.out.print(elem.getRep());
        }
        System.out.println("\n---------------");
    }

}

import tiles.Empty;
import tiles.Object;
import tiles.Target;
import tiles.Tile;

import java.util.LinkedList;

public class Controller {

    public static void main(String[] args) {
        Environment env = new Environment();
        env.printMap();
        Agent agent = env.getAgent();
        while(!agent.foundTarget()) {

            Tile frontChecker = agent.checkForTarget(env.getTilesFront(agent.getX(), agent.getY(), agent.getDir()));

            if (frontChecker instanceof Target) { /*if target is in front*/
                agent.moveFront();
                continue;
            }

            Tile rightChecker = agent.checkForTarget(env.getTilesRight(agent.getX(), agent.getY(), agent.getDir()));
            if (rightChecker instanceof Target) {/*if target is on the right*/
                agent.moveRight();
                continue;
            }

            Tile leftChecker = agent.checkForTarget(env.getTilesLeft(agent.getX(), agent.getY(), agent.getDir()));
            if (leftChecker instanceof Target) {/*if target is on the left*/
                agent.moveLeft();
                continue;
            }

            if (frontChecker instanceof Object) {
                if (rightChecker instanceof Empty) agent.moveRight();
                else if (leftChecker instanceof Empty) agent.moveLeft();
            }
        }
    }

}

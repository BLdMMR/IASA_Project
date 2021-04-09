import tiles.Tile;

import java.util.LinkedList;
import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        Environment env = new Environment();

        env.printMap();

        Agent agent = env.getAgent();
        while(!agent.foundTarget()) {

        }

        Scanner in = new Scanner(System.in);
        in.nextLine();
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();

        }
    }
}

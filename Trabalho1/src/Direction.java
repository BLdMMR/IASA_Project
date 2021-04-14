public enum Direction {
    LEFT, RIGHT, UP, DOWN, UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT;

    public static Direction changeDirection(Agent agt, Direction turn) {
        switch (turn) {
            case LEFT: {
                switch (agt.getDir()) {
                    case UP: return UPLEFT;
                    case UPLEFT: return LEFT;
                    case LEFT: return DOWNLEFT;
                    case DOWNLEFT: return DOWN;
                    case DOWN: return DOWNRIGHT;
                    case DOWNRIGHT: return RIGHT;
                    case RIGHT: return UPRIGHT;
                    case UPRIGHT: return UP;
                }
            }
            case RIGHT: {
                switch (agt.getDir()) {
                    case UP: return UPRIGHT;
                    case UPRIGHT: return RIGHT;
                    case RIGHT: return DOWNRIGHT;
                    case DOWNRIGHT: return DOWN;
                    case DOWN: return DOWNLEFT;
                    case DOWNLEFT: return LEFT;
                    case LEFT: return UPLEFT;
                    case UPLEFT: return UP;
                }
            }
        }
        return agt.getDir();
    }
}


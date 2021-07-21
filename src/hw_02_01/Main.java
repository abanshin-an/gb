package hw_02_01;


public class Main {
    public static void main(String[] args) {
        RunAndJumpable[] rjs = makeRunAndJumpables();
        Obstacle[] obs = makeObstacles();
        overcomeAll(rjs, obs);
    }

    private static RunAndJumpable[] makeRunAndJumpables() {
        RunAndJumpable[] rj;
        rj = new RunAndJumpable[]{
                new Human("Spiderman", 11, 2),
                new Human("Batman", 22, 7),
                new Human("Superman", 31, 10),
                new Robot("WALL-E",7,4),
                new Robot("R2D2",22,5),
                new Robot("Terminator",32,8),
                new Cat("Garfield",11,4),
                new Cat("Tom",25,7),
                new Cat("Matroskin",31,10),

        };
        return rj;
    }

    private static Obstacle[] makeObstacles() {
        Obstacle[] o;
        o = new Obstacle[]{
                new Treadmill(10),
                new Wall(3),
                new Treadmill(20),
                new Wall(6),
                new Treadmill(30),
                new Wall(9),
        };
        return o;
    }

    private static void overcomeAll(RunAndJumpable[] rjs, Obstacle[] obs) {
        for (RunAndJumpable r : rjs) {
            for (Obstacle o1 : obs) {
                if (!o1.overcome(r)) {
                    break;
                }
            }
        }
    }

}

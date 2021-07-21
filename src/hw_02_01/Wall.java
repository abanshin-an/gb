package hw_02_01;

public class Wall extends Obstacle {

    public Wall(int value) {
        super(value);
    }

    @Override
    public boolean overcome(RunAndJumpable rj) {
        return rj.jump(value);
    }
}

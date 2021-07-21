package hw_02_01;

public class Treadmill extends Obstacle {

    public Treadmill(int value) {
        super(value);
    }

    @Override
    public boolean overcome(RunAndJumpable rj) {
        return rj.run(value);
    }
}

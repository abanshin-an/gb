package hw_02_01;

public abstract class Obstacle {
    protected int value;

    public Obstacle(int value) {
        this.value = value;
    }

    abstract public boolean overcome(RunAndJumpable rj );
}


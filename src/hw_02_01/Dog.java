package hw_02_01;

public class Dog implements RunAndJumpable {
    private String name;
    private int runningDistanceLimit;
    private int jumpHeightLimit;

    public Dog(String name, int runningDistanceLimit, int jumpHeightLimit) {
        this.name = name;
        this.runningDistanceLimit = runningDistanceLimit;
        this.jumpHeightLimit = jumpHeightLimit;
    }

    @Override
    public String getName() {
        return "Пес "+name;
    }

    @Override
    public int getRunLimit() {
        return runningDistanceLimit;
    }

    @Override
    public int getJumpLimit() {
        return jumpHeightLimit;
    }
}

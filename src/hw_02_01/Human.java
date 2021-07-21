package hw_02_01;

public class Human implements RunAndJumpable{
    final private String name;
    final private int runningDistanceLimit;
    final private int jumpHeightLimit;

    public Human(String name, int runningDistanceLimit, int jumpHeightLimit) {
        this.name = name;
        this.runningDistanceLimit = runningDistanceLimit;
        this.jumpHeightLimit = jumpHeightLimit;
    }

    @Override
    public String getName() {
        return "Человек "+name;
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

package hw_02_01;

public class Robot implements RunAndJumpable {
    final private int runningDistanceLimit;
    final private String name;
    final private int jumpHeightLimit;

    public Robot(String name, int runningDistanceLimit, int jumpHeightLimit) {
        this.name = name;
        this.runningDistanceLimit = runningDistanceLimit;
        this.jumpHeightLimit = jumpHeightLimit;
    }


    @Override
    public String getName() {
        return "Робот "+name;
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

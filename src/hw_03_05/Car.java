package hw_03_05;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Car implements Runnable {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(MainClass.CARS_COUNT);
    private static int CARS_COUNT;

    public static void setWinner(boolean winner) {
        Car.winner = winner;
    }

    private static volatile boolean winner = false;
    private final Race race;
    private final int speed;
    private final String name;
    private final Random random;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        random = new Random();
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            cyclicBarrier.await();
            sleep(500L + random.nextInt(800));
            System.out.println(this.name + " готов");
            MainClass.startLatch.countDown();
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
            currentThread().interrupt();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            {
                race.getStages().get(i).go(this);
                if (i == race.getStages().size() - 1 && !Car.winner) {
                    Car.setWinner(true);
                    System.out.println(getName() + " WIN");
                }
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    currentThread().interrupt();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }
        }
        MainClass.finishLatch.countDown();
    }
}

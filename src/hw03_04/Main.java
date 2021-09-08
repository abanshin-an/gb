package hw03_04;

public class Main {
    /*
    1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
    Используйте wait/notify/notifyAll.
     */
    private static final Object mon = new Object();
    private static final char[] letters = new char[]{'A', 'B', 'C'};
    private static volatile char currentLetter = letters[0];

    public static void main(String[] args) {
        Main m = new Main();
        Thread t1 = new Thread(() -> m.printLetter(0));
        Thread t2 = new Thread(() -> m.printLetter(1));
        Thread t3 = new Thread(() -> m.printLetter(2));
        t1.start();
        t2.start();
        t3.start();
    }

    public void printLetter(int index) {
        try {
            for (int i = 0; i < 5; i++)
                synchronized (mon) {
                    while (currentLetter != letters[index]) {
                        mon.wait();
                    }
                    System.out.print(letters[index]);
                    currentLetter = letters[(index + 1) % 3];
                    mon.notifyAll();
                }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }


}

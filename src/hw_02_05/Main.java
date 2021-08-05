package hw_02_05;

import java.util.Arrays;

@SuppressWarnings("IntegerDivisionInFloatingPointContext")
public class Main {

    static final int SIZE = 10_000_000;
    static final int H = SIZE / 2;
    float[] arr = new float[SIZE];
    float[] arr1 = new float[SIZE];
    float[] a1 = new float[H];
    float[] a2 = new float[SIZE - H];

    public static void main(String[] args) {
        Main m = new Main();
        m.run1();
        m.run2();
        m.check();
    }

    public void init(float[] a) {
        Arrays.fill(a, 1.0f);
    }

    public void run1() {
        long t = System.currentTimeMillis();
        init(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("duration without threads " + (System.currentTimeMillis() - t) + " millis");
    }

    public void run2() {
        long t = System.currentTimeMillis();
        init(arr1);
        split(arr1);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < H; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < H; i++) {
                a2[i] = (float) (a2[i] * Math.sin(0.2f + (i + H) / 5) * Math.cos(0.2f + (i + H) / 5) * Math.cos(0.4f + (i + H) / 2));
            }
        });
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        merge(arr1);
        System.out.println("duration with threads " + (System.currentTimeMillis() - t) + " millis");
    }

    public void split(float[] a) {
        System.arraycopy(a, 0, a1, 0, H);
        System.arraycopy(a, H, a2, 0, a.length - H); // Для четного SIZE это неважно, но чтобы руку не сбивать :)
    }

    public void merge(float[] a) {
        System.arraycopy(a1, 0, a, 0, H);
        System.arraycopy(a2, 0, a, H, a.length - H);
    }

    public void check() {
        for (int i = 0; i < SIZE; i++) {
            if(arr[i]!=arr1[i]) {
                System.out.println(" arr["+i+"]("+arr[i]+"!=arr1["+i+"]("+arr1[i]);
                return;
            }
        }
        System.out.println("arr equals arr1");
    }
}

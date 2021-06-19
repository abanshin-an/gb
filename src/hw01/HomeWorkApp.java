package hw01;

import static java.lang.Math.random;

public class HomeWorkApp {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
    }

    public static void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");
    }

    public static void checkSumSign() {
        int a = (int) (random() * 10 - 5);
        int b = (int) (random() * 10 - 5);
//        System.out.println("a+b= " + (a + b));
        if ((a + b) >= 0) {
            System.out.println("Сумма положительная");
        } else
            System.out.println("Сумма отрицательная");
    }

    private static void printColor() {
        int value = (int) (random() * 300 - 100);
//        System.out.printf("value= %d \n", value);
        if (value <= 0)
            System.out.println("Красный");
        else if (value <= 100)
            System.out.println("Желтый");
        else
            System.out.println("Зеленый");
    }

    private static void compareNumbers() {
        int a = (int) (random() * 10 - 5);
        int b = (int) (random() * 10 - 5);
//        System.out.println("a= " + a + "; b= " + b);
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }
}

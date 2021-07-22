package hw_02_02;

import java.util.Arrays;

public class Main {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset
    // Bold
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    static String[][] a1 = new String[][]{
            {"1", "2", "3", "4"},
            {"11", "12", "13", "14"},
            {"21", "22", "23", "24"}
    };
    static String[][] a2 = new String[][]{
            {"1", "2", "3", "4"},
            {"11", "12", "13", "14"},
            {"21", "22", "23"},
            {"31", "32", "33", "34"}
    };
    static String[][] a3 = new String[][]{
            {"1", "2", "3", "4"},
            {"11", "12", "13", "14"},
            {"21", "22", "z23", "24"},
            {"31", "32", "33", "34"}
    };
    static String[][] a4 = new String[][]{
            {"1", "2", "3", "4"},
            {"11", "12", "13", "14"},
            {"21", "22", "23", "24"},
            {"31", "32", "33", "34"}
    };

    static public int run(String[][] a) {
        int sum = 0;
        if (a.length != 4) {
            throw new MyArraySizeException(a, -1, a.length);
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != 4) {
                throw new MyArraySizeException(a, i, a[i].length);
            }
            for (int j = 0; j < a[i].length; j++) {
                int n;
                try {
                    n = Integer.parseInt(a[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(a, i, j, a[i][j]);
                }
                sum += n;
            }
        }
        return sum;
    }

    private static void printArray(String[][] a) {
        System.out.println(GREEN_BOLD + "[");
        for (String[] a1 : a) {
            System.out.println(Arrays.toString(a1));
        }
        System.out.println("]" + RESET);
    }

    public static void printSum(String[][] a) {
        int sum;
        try {
            sum = run(a);
            printArray(a);
            System.out.println(PURPLE_BOLD + "sum = " + sum + RESET);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        printSum(a1);
        printSum(a2);
        printSum(a3);
        printSum(a4);
    }
}

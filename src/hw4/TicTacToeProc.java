package hw4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeProc {
    private static final int SIZE = 5;
    private static final int DOTS_TO_WIN = 4;
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';// User
    private static final char DOT_O = 'O';// Computer
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();
    private static char[][] map;

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (char[] a : map) {
            Arrays.fill(a, DOT_EMPTY);
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        initMap();
        printMap();
        System.out.println("Первый ход человека(1) или компьютера(2)");
        int whoFirst = sc.nextInt();
        if (whoFirst == 2) {
            moveAI();
            printMap();
        }
        while (true) {
            moveHuman();
            if (checkWinLines(DOT_X, DOTS_TO_WIN) == 0) {
                printMap();
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                printMap();
                System.out.println("Ничья");
                break;
            }
            moveAI();
            printMap();
            if (checkWinLines(DOT_O, DOTS_TO_WIN) == 0) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    public static void moveAI() {
        int bestX = -1;
        int bestY = -1;
        int bestCounter = Integer.MAX_VALUE;
        int counter;

        // Выстроить свою линию
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_O;
                    counter = checkWinLines(DOT_O, DOTS_TO_WIN);
                    if (counter == 0) return;
                    if ((counter < bestCounter) || (rand.nextDouble() > 0.5 && counter == bestCounter)) {
                        // выбрать лучший вариант, при равных - выбрать случайный
                        bestY = i;
                        bestX = j;
                        bestCounter = counter;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        // Сбить линию противника
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_X;
                    counter = checkWinLines(DOT_X, DOTS_TO_WIN);
                    if (counter == 0) {
                        map[i][j] = DOT_O;
                        return;
                    }
                    if ((counter < bestCounter) || (rand.nextDouble() > 0.5 && counter == bestCounter)) {
                        // выбрать лучший вариант, при равных - выбрать случайный
                        bestY = i;
                        bestX = j;
                        bestCounter = counter;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        // Если подходящих вариантов нет - выбрать случайный
        if (bestX == -1)
            do {
                bestX = rand.nextInt(SIZE);
                bestY = rand.nextInt(SIZE);
            } while (!isCellInvalid(bestX, bestY));
        map[bestY][bestX] = DOT_O;
    }

    public static void moveHuman() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (isCellInvalid(x, y));
        map[y][x] = DOT_X;
    }

    public static boolean isCellInvalid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return true;
        return map[y][x] != DOT_EMPTY;
    }

    public static boolean isMapFull() {
        for (char[] a : map) {
            for (char c: a) {
                if (c == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static int checkWinLines(char dot, int dotsToWin) {
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int[] a = new int[]{
                        checkLine(i, j, 0, 1, dot, dotsToWin),
                        checkLine(i, j, 1, 0, dot, dotsToWin),
                        checkLine(i, j, 1, 1, dot, dotsToWin),
                        checkLine(i, j, -1, 1, dot, dotsToWin)};
                int value = Arrays.stream(a).min().getAsInt();
                if (value == 0)
                    return 0;
                minValue = Math.min(value, minValue);
            }
        }
        return minValue;
    }

    public static int checkLine(int cy, int cx, int vy, int vx, int dot, int dotsToWin) {
        if (cx + vx * (dotsToWin - 1) > SIZE - 1 || cy + vy * (dotsToWin - 1) > SIZE - 1 || cy + vy * (dotsToWin - 1) < 0) {
            return Integer.MAX_VALUE; // встретилось препятствие
        }
        int counter = dotsToWin; // количество dot-симаволов
        boolean isObstacle = false;
        boolean isWinLine = true;
        char dotObstacle = dot == DOT_O ? DOT_X : DOT_O;
        for (int i = 0; i < dotsToWin; i++) {
            char sym = map[cy + i * vy][cx + i * vx];
            if (sym != dot) {
                isWinLine = false;
                isObstacle = sym == dotObstacle;
            } else
                counter--;
        }
        if (isWinLine)
            return 0; // победная линия
        else if (isObstacle)
            return Integer.MAX_VALUE; // встретилась чужая фишка
        else return counter; // до полной линии не хватает counter фишек
    }
}
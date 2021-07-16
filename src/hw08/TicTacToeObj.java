package hw08;

import java.util.Arrays;
import java.util.Random;

public class TicTacToeObj {
    static final char DOT_EMPTY = '•';
    static final char DOT_X = 'X';// User
    static final char DOT_O = 'O';// Computer
    private static final Random rand = new Random();
    static int boardSize;
    static int dotsToWin;

    public static Status getStatus() {
        return status;
    }

    private static Status status= Status.NOT_INIT;
    static int[][] winLine= new int[2][2];
    private static char[][] map;

    public static void initMap() {
        map = new char[boardSize][boardSize];
        for (char[] a : map) {
            Arrays.fill(a, DOT_EMPTY);
        }
    }

    public static void printMap() {
        for (int i = 0; i <= boardSize; i++) {
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

    public static void turn() {
        if (checkWinLines(DOT_X, dotsToWin) == 0) {
            printMap();
            System.out.println("Победил человек");
            status = Status.WIN_HUMAN;
            return;
        }
        if (isMapFull()) {
            printMap();
            System.out.println("Ничья");
            status=Status.DRAW;
            return;
        }
        moveAI();
        printMap();
        if (checkWinLines(DOT_O, dotsToWin) == 0) {
            System.out.println("Победил Искуственный Интеллект");
            status=Status.WIN_AI;
            return;
        }
        if (isMapFull()) {
            System.out.println("Ничья");
            status=Status.DRAW;
            return;
        }
        status= Status.GAME_IS_ON;
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
                    counter = checkWinLines(DOT_O, dotsToWin);
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
                    counter = checkWinLines(DOT_X, dotsToWin);
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
                bestX = rand.nextInt(boardSize);
                bestY = rand.nextInt(boardSize);
            } while (isCellValid(bestX, bestY));
        map[bestY][bestX] = DOT_O;
    }

    public static void moveHuman(int x, int y) {
        if (isCellValid(x, y)) {
            map[y][x] = DOT_X;
            turn();
        }
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) return false;
        return map[y][x] == DOT_EMPTY;
    }

    public static boolean isMapFull() {
        for (char[] a : map) {
            for (char c : a) {
                if (c == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static int checkWinLines(char dot, int dotsToWin) {
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < boardSize; i++) {
            winLine[0][1] = i;
            for (int j = 0; j < boardSize; j++) {
                winLine[0][0] = j;
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
        if (cx + vx * (dotsToWin - 1) > boardSize - 1 || cy + vy * (dotsToWin - 1) > boardSize - 1 || cy + vy * (dotsToWin - 1) < 0) {
            return Integer.MAX_VALUE; // встретилось препятствие
        }
        int counter = dotsToWin; // количество dot-симаволов
        boolean isObstacle = false;
//        boolean isWinLine = true;
        char dotObstacle = dot == DOT_O ? DOT_X : DOT_O;
        for (int i = 0; i < dotsToWin; i++) {
            char sym = map[cy + i * vy][cx + i * vx];
            if (sym != dot) {
//                isWinLine = false;
                isObstacle = sym == dotObstacle;
            } else
                counter--;
        }
        if (counter==0) {
            winLine[1][0] = cx + (dotsToWin-1) * vx;
            winLine[1][1] = cy + (dotsToWin-1) * vy;
            System.out.println("\nwin line "+Arrays.toString(winLine[0])+"-"+Arrays.toString(winLine[1]));
            return 0; // победная линия
        } else if (isObstacle)
            return Integer.MAX_VALUE; // встретилась чужая фишка
        else return counter; // до полной линии не хватает counter фишек
    }

    public static void startGame(int boardSize, int dotsToWin, boolean isAiFirst) {
        TicTacToeObj.boardSize = boardSize;
        TicTacToeObj.dotsToWin = dotsToWin;
        for (int[] ints : winLine) {
            Arrays.fill(ints, 0);
        }
        initMap();
        printMap();
        status= Status.GAME_IS_ON;
        if (isAiFirst) {
            moveAI();
            printMap();
        }
    }

    public static char cell(int x, int y ){
        if (x>=0 && x< boardSize && y>=0 && y < boardSize) {
            return map[y][x];
        }
        return DOT_EMPTY;
    }

    public static int[][] getWinLine(){
        return winLine;
    }
}


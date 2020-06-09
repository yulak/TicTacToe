package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class Logic {
    static int SIZE = 3;
    static int DOTS_TO_WIN = 3;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;
    static boolean gameFinished = false;

    static Random random = new Random();

    public static void go() {
        gameFinished = true;

        printMap();

        if (checkWinLines(DOT_X)) {
            System.out.println("Ты победил!");
            return;
        }

        if (isFull()) {
            System.out.println("Ничья");
            return;
        }

        aiTurn();
        printMap();

        if (checkWinLines(DOT_O)) {
            System.out.println("ПК Вин!");
            return;
        }

        if (isFull()) {
            System.out.println("Ничья");
            return;
        }

        gameFinished = false;
    }


    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void setHumanCoords(int x, int y) {
        if (isCellValid(y, x)) {
            map[y][x] = DOT_X;
            go();
        }

    }

    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x, y;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_O;
                    if (checkWinLines(DOT_O)) {
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_X;
                    if (checkWinLines(DOT_X)) {
                        map[i][j] = DOT_O;
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;
    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    static boolean checkLine(int cy, int cx, int vy, int vx, char dot) {
        if (cx + vx * (DOTS_TO_WIN - 1) > SIZE - 1 || cy + vy * (DOTS_TO_WIN - 1) > SIZE - 1 ||
                cy + vy * (DOTS_TO_WIN - 1) < 0) {
            return false;
        }

        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[cy + i * vy][cx + i * vx] != dot) {
                return false;
            }
        }
        return true;
    }

    static boolean checkWinLines(char dot) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkLine(i, j, 0, 1, dot) || checkLine(i, j, 1, 0, dot) ||
                        checkLine(i, j, 1, 1, dot) || checkLine(i, j, -1, 1, dot)) {
                    return true;
                }
            }
        }
        return false;
    }


//    public static boolean checkWin(char c){
//        if (map[0][0] == c && map[0][1] == c && map[0][2] == c){
//            return true;
//        }
//        if (map[1][0] == c && map[1][1] == c && map[1][2] == c){
//            return true;
//        }
//        if (map[2][0] == c && map[2][1] == c && map[2][2] == c){
//            return true;
//        }
//
//        if (map[0][0] == c && map[1][0] == c && map[2][0] == c){
//            return true;
//        }
//        if (map[0][1] == c && map[1][1] == c && map[2][1] == c){
//            return true;
//        }
//        if (map[0][2] == c && map[1][2] == c && map[2][2] == c){
//            return true;
//        }
//
//        if (map[0][0] == c && map[1][1] == c && map[2][2] == c){
//            return true;
//        }
//
//        if (map[0][2] == c && map[1][1] == c && map[2][0] == c){
//            return true;
//        }
//
//        return false;
//    }

}

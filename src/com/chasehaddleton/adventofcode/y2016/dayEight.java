package com.chasehaddleton.adventofcode.y2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// in/dayEight.in
// 116
// UPOJFLBCEZ

public class dayEight extends AdventOfCode {
    ArrayList<String> input = new ArrayList<>();

    void readInput(Scanner sc) {
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
    }

    String output1() {
        Screen screen = new Screen();

        input.forEach(screen::execute);

        return String.valueOf(screen.count());
    }

    String output2() {
        Screen screen = new Screen();

        input.forEach(screen::execute);

        screen.print();
        return "";
    }
}

class Screen {
    boolean[][] screen;

    public Screen() {
        this.screen = new boolean[6][50];

        for (boolean[] temp : screen) {
            Arrays.fill(temp, false);
        }
    }

    void execute(String command) {
        if (command.contains("rect")) {
            int[] coord = Arrays.stream(command.replaceAll("\\s+", "").replaceAll("[A-z]+", " ").trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            set(coord[0], coord[1]);
        } else if (command.contains("column")) {
            int[] coord = Arrays.stream(command.replaceAll("\\s+|=", "").replaceAll("[A-z]+", " ").trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            shiftCol(coord[0], coord[1]);
        } else {
            int[] coord = Arrays.stream(command.replaceAll("\\s+|=", "").replaceAll("[A-z]+", " ").trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            shiftRow(coord[0], coord[1]);
        }
    }

    private void shiftRow(int y, int shift) {
        screen[y] = rightShift(screen[y], shift);
    }

    private boolean[] rightShift(boolean[] arr, int shift) {
        boolean[] temp = new boolean[arr.length];

        System.arraycopy(arr, 0, temp, shift, arr.length - shift);
        System.arraycopy(arr, arr.length - shift, temp, 0, shift);

        return temp;
    }

    private void shiftCol(int x, int shift) {
        setCol(rightShift(getCol(x), shift), x);
    }

    private void setCol(boolean[] newCol, int x) {
        for (int i = 0; i < 6; i++) {
            screen[i][x] = newCol[i];
        }
    }

    private boolean[] getCol(int x) {
        boolean[] temp = new boolean[6];

        for (int i = 0; i < 6; i++) {
            temp[i] = screen[i][x];
        }

        return temp;
    }

    private void set(int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                screen[j][i] = true;
            }
        }
    }

    void print() {
        for (boolean[] temp : screen) {
            for (boolean cur : temp) {
                if (cur) {
                    System.out.print('#');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }

    int count() {
        int count = 0;

        for (boolean[] row : screen) {
            for (boolean cell : row) {
                if (cell) ++count;
            }
        }

        return count;
    }
}

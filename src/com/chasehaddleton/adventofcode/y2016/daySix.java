package com.chasehaddleton.adventofcode.y2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

// in/daySix.in
// qzedlxso
// ucmifjae

public class daySix extends AdventOfCode {
    ArrayList<String> input = new ArrayList<>();

    void readInput(Scanner sc) {
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
    }


    String output1() {
        int[][] count = new int[8][26];

        for (int[] cur : count) {
            Arrays.fill(cur, 0);
        }

        for (String line : input) {
            for (int i = 0; i < line.length(); i++) {
                ++count[i][line.charAt(i) - 97];
            }
        }

        StringBuilder out = new StringBuilder();

        for (int[] cur : count) {
            int max = IntStream.of(cur).max().getAsInt();

            for (int i = 0; i < cur.length; i++) {
                if (cur[i] == max) {
                    out.append((char) (i + 97));
                    break;
                }
            }
        }

        return out.toString();
    }


    String output2() {
        int[][] count = new int[8][26];

        for (int[] cur : count) {
            Arrays.fill(cur, 0);
        }

        for (String line : input) {
            for (int i = 0; i < line.length(); i++) {
                ++count[i][line.charAt(i) - 97];
            }
        }

        StringBuilder out = new StringBuilder();

        for (int[] cur : count) {
            int max = IntStream.of(cur).min().getAsInt();

            for (int i = 0; i < cur.length; i++) {
                if (cur[i] == max) {
                    out.append((char) (i + 97));
                    break;
                }
            }
        }

        return out.toString();
    }
}
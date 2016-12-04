package com.chasehaddleton.adventofcode.y2016;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

abstract public class AdventOfCode {
    public void solveQ1() {
        try (Scanner sc = new Scanner(new FileInputStream(new File("").getAbsolutePath() + "/src/com/chasehaddleton/adventofcode/y2016/in/" + this.getClass().getSimpleName() + ".in"))) {
            readInput(sc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(output1());
    }

    public void solveQ2() {
        try (Scanner sc = new Scanner(new FileInputStream(new File("").getAbsolutePath() + "/src/com/chasehaddleton/adventofcode/y2016/in/" + this.getClass().getSimpleName() + ".in"))) {
            readInput(sc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(output2());
    }

    abstract void readInput(Scanner sc);

    abstract String output1();

    abstract String output2();
}

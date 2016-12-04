package com.chasehaddleton.adventofcode.y2016;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// in/dayThree.in
// 862
// 1577

public class dayThree extends AdventOfCode {
    private ArrayList<Triangle> input = new ArrayList<>();

    public void solveQ1() {
        try (Scanner sc = new Scanner(new FileInputStream("/Users/chasehaddleton/Documents/Programming/Advent of Code 2016/src/com/chasehaddleton/adventofcode/y2016/in/dayThree.in"))) {
            readInput1(sc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(output1());
    }

    public void solveQ2() {
        try (Scanner sc = new Scanner(new FileInputStream("/Users/chasehaddleton/Documents/Programming/Advent of Code 2016/src/com/chasehaddleton/adventofcode/y2016/in/dayThree.in"))) {
            readInput2(sc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(output2());
    }

    private void readInput1(Scanner sc) {
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().trim().split("\\s+"); // Split the input into an array of the numbers

            input.add(new Triangle(line[0], line[1], line[2])); // Create the new triangle
        }
    }

    private void readInput2(Scanner sc) {
        ArrayList<String[]> in = new ArrayList<>();

        while (sc.hasNextLine()) { // Read all input
            in.add(sc.nextLine().trim().split("\\s+"));
        }

        for (int i = 0; i < in.size() - 2; i += 3) { // Make all triangles
            input.add(new Triangle(in.get(i)[0], in.get(i + 1)[0], in.get(i + 2)[0]));
            input.add(new Triangle(in.get(i)[1], in.get(i + 1)[1], in.get(i + 2)[1]));
            input.add(new Triangle(in.get(i)[2], in.get(i + 1)[2], in.get(i + 2)[2]));

        }
    }

    void readInput(Scanner sc) {

    }

    String output1() {
        int count = 0;

        for (Triangle tri : input) { // Count triangles
            count += (tri.isPossible()) ? 1 : 0;
        }

        return String.valueOf(count);
    }

    String output2() {
        int count = 0;

        for (Triangle tri : input) { // Count triangles
            count += (tri.isPossible()) ? 1 : 0;
        }

        return String.valueOf(count);
    }
}

class Triangle {
    private int sideOne, sideTwo, sideThree;

    public Triangle(String sideOne, String sideTwo, String sideThree) {
        this.sideOne = Integer.parseInt(sideOne.trim());
        this.sideTwo = Integer.parseInt(sideTwo.trim());
        this.sideThree = Integer.parseInt(sideThree.trim());
    }

    boolean isPossible() {
        // All of the the sums of any two sides must exceed the length of the third size
        return (sideOne + sideTwo > sideThree) && (sideTwo + sideThree > sideOne) && (sideThree + sideOne > sideTwo);
    }
}
package com.chasehaddleton.adventofcode.y2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.IntBinaryOperator;

// in/dayOne.in
// 288
// 111

public class dayOne extends AdventOfCode {
    private ArrayList<String> input = new ArrayList<>();

    void readInput(Scanner sc) {
        Arrays.asList(sc.nextLine().split(", ")).forEach((x) -> input.add(x));
    }

    String output1() {
        Pos pos = new Pos();
        Facing facing = new Facing(Facing.north);

        input.forEach((cur) -> { // run every command
            int dist = Integer.parseInt(cur.substring(1, cur.length())); // figure out how far we need to travel
            switch (facing.change(cur.substring(0, 1))) { // figure out the direction to travel
                case Facing.north:
                    pos.changeY(dist, (y, dis) -> (y + dis)); // move north
                    break;
                case Facing.east:
                    pos.changeX(dist, (x, dis) -> (x + dis)); // move east
                    break;
                case Facing.south:
                    pos.changeY(dist, (y, dis) -> (y - dis)); // move south
                    break;
                case Facing.west:
                    pos.changeX(dist, (x, dis) -> (x - dis)); // move west
                    break;
            }
        });

        return String.valueOf(pos.getVal());
    }

    String output2() {
        Pos pos = new Pos();
        Facing facing = new Facing(Facing.north);
        HashMap<String, Integer> visited = new HashMap<>();
        visited.put(pos.toString(), 0);

        for (String cur : input) { // run (almost) every command
            facing.change(cur.substring(0, 1)); // find the new direction of travel
            for (int i = 0; i < Integer.parseInt(cur.substring(1, cur.length())); i++) { // travel one unit each time
                switch (facing.direction()) {
                    case Facing.north:
                        pos.changeY(1, (y, dis) -> (y + dis)); // move north
                        break;
                    case Facing.east:
                        pos.changeX(1, (x, dis) -> (x + dis)); // move east
                        break;
                    case Facing.south:
                        pos.changeY(1, (y, dis) -> (y - dis)); // move south
                        break;
                    case Facing.west:
                        pos.changeX(1, (x, dis) -> (x - dis)); // west
                        break;
                }

                int count;

                if (visited.get(pos.toString()) == null) { // check if we've visited this place before
                    count = -1; // we haven't!
                } else {
                    return String.valueOf(pos.getVal());
                }

                visited.put(pos.toString(), ++count); // add the visited location to the hash map
            }
        }
        return String.valueOf(new Pos().getVal());
    }
}

class Pos {
    private int x;
    private int y;

    public Pos() {
        this.x = 0;
        this.y = 0;
    }

    void changeX(int dis, IntBinaryOperator act) {
        x = act.applyAsInt(x, dis);
    }

    void changeY(int dis, IntBinaryOperator act) {
        y = act.applyAsInt(y, dis);
    }

    public int getVal() {
        return Math.abs(x) + Math.abs(y);
    }

    public String toString() {
        return "X" + x + "Y" + y;
    }
}

class Facing {
    private int facing;
    public static final int north = 0, east = 1, south = 2, west = 3;

    public Facing(int facing) {
        this.facing = facing;
    }

    int change(String rot) {
        if (rot.equals("R")) {
            if (facing == west) {
                facing = north;
                return facing;
            }
            return ++facing;
        } else {
            if (facing == north) {
                facing = west;
                return facing;
            }
            return --facing;
        }
    }

    public int direction() {
        return facing;
    }
}
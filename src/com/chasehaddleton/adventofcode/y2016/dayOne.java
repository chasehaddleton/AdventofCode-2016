package com.chasehaddleton.adventofcode.y2016;

import java.util.*;
import java.util.function.IntBinaryOperator;

// R4, R3, L3, L2, L1, R1, L1, R2, R3, L5, L5, R4, L4, R2, R4, L3, R3, L3, R3, R4, R2, L1, R2, L3, L2, L1, R3, R5, L1, L4, R2, L4, R3, R1, R2, L5, R2, L189, R5, L5, R52, R3, L1, R4, R5, R1, R4, L1, L3, R2, L2, L3, R4, R3, L2, L5, R4, R5, L2, R2, L1, L3, R3, L4, R4, R5, L1, L1, R3, L5, L2, R76, R2, R2, L1, L3, R189, L3, L4, L1, L3, R5, R4, L1, R1, L1, L1, R2, L4, R2, L5, L5, L5, R2, L4, L5, R4, R4, R5, L5, R3, L1, L3, L1, L1, L3, L4, R5, L3, R5, R3, R3, L5, L5, R3, R4, L3, R3, R1, R3, R2, R2, L1, R1, L3, L3, L3, L1, R2, L1, R4, R4, L1, L1, R3, R3, R4, R1, L5, L2, R2, R3, R2, L3, R4, L5, R1, R4, R5, R4, L4, R1, L3, R1, R3, L2, L3, R1, L2, R3, L3, L1, L3, R4, L4, L5, R3, R5, R4, R1, L2, R3, R5, L5, L4, L1, L1
// 288
// 111

public class dayOne {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<String> input = new ArrayList<>();

    public void solveQ1() {
        readInput();
        System.out.println(output1());
    }

    public void solveQ2() {
        readInput();
        System.out.println(output2().getVal());
    }

    private void readInput() {
        List<String> inLst = Arrays.asList(sc.nextLine().split(", "));

        inLst.forEach((x) -> input.add(x));
    }

    private int output1() {
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

        return pos.getVal();
    }

    private Pos output2() {
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
                    return pos;
                }

                visited.put(pos.toString(), ++count); // add the visited location to the hash map
            }
        }
        return new Pos();
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
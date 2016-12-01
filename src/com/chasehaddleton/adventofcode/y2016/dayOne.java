package com.chasehaddleton.adventofcode.y2016;

import java.util.*;
import java.util.function.IntBinaryOperator;


// R4, R3, L3, L2, L1, R1, L1, R2, R3, L5, L5, R4, L4, R2, R4, L3, R3, L3, R3, R4, R2, L1, R2, L3, L2, L1, R3, R5, L1, L4, R2, L4, R3, R1, R2, L5, R2, L189, R5, L5, R52, R3, L1, R4, R5, R1, R4, L1, L3, R2, L2, L3, R4, R3, L2, L5, R4, R5, L2, R2, L1, L3, R3, L4, R4, R5, L1, L1, R3, L5, L2, R76, R2, R2, L1, L3, R189, L3, L4, L1, L3, R5, R4, L1, R1, L1, L1, R2, L4, R2, L5, L5, L5, R2, L4, L5, R4, R4, R5, L5, R3, L1, L3, L1, L1, L3, L4, R5, L3, R5, R3, R3, L5, L5, R3, R4, L3, R3, R1, R3, R2, R2, L1, R1, L3, L3, L3, L1, R2, L1, R4, R4, L1, L1, R3, R3, R4, R1, L5, L2, R2, R3, R2, L3, R4, L5, R1, R4, R5, R4, L4, R1, L3, R1, R3, L2, L3, R1, L2, R3, L3, L1, L3, R4, L4, L5, R3, R5, R4, R1, L2, R3, R5, L5, L4, L1, L1
// 288

public class dayOne {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<String> input = new ArrayList<>();

    public void solveQ1() {
        readInput();
        output1();
    }

    public void solveQ2() {
        readInput();
        System.out.println(output2().getVal());
    }

    private void readInput() {
        List<String> inLst = Arrays.asList(sc.nextLine().split(", "));

        inLst.forEach((x) -> input.add(x));
    }

    private void output1() {
        Pos pos = new Pos();
        Facing facing = new Facing(Facing.north);

        input.forEach((cur) -> {
            switch (facing.change(cur.substring(0, 1))) {
                case Facing.north:
                    pos.changeY(Integer.parseInt(cur.substring(1, cur.length())), (y, dis) -> (y + dis));
                    break;
                case Facing.east:
                    pos.changeX(Integer.parseInt(cur.substring(1, cur.length())), (x, dis) -> (x + dis));
                    break;
                case Facing.south:
                    pos.changeY(Integer.parseInt(cur.substring(1, cur.length())), (y, dis) -> (y - dis));
                    break;
                case Facing.west:
                    pos.changeX(Integer.parseInt(cur.substring(1, cur.length())), (x, dis) -> (x - dis));
                    break;
            }
        });

        System.out.println(pos.getVal());
    }

    private Pos output2() {
        Pos pos = new Pos();
        Facing facing = new Facing(Facing.north);
        HashMap<String, Integer> visited = new HashMap<>();
        visited.put(pos.toString(), 0);

        for (String cur : input) {
            facing.change(cur.substring(0, 1));
            for (int i = 0; i < Integer.parseInt(cur.substring(1, cur.length())); i++) {
                switch (facing.direction()) {
                    case Facing.north:
                        pos.changeY(1, (y, dis) -> (y + dis));
                        break;
                    case Facing.east:
                        pos.changeX(1, (x, dis) -> (x + dis));
                        break;
                    case Facing.south:
                        pos.changeY(1, (y, dis) -> (y - dis));
                        break;
                    case Facing.west:
                        pos.changeX(1, (x, dis) -> (x - dis));
                        break;
                }

                Integer count = visited.get(pos.toString());

                if (count == null) {
                    count = -1;
                }

                if (count >= 0) {
                    return pos;
                }

                visited.put(pos.toString(), ++count);
            }
        }
        return new Pos();
    }
}

class Pos {
    private int x;
    private int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pos() {
        this(0, 0);
    }

    public Pos(Pos old) {
        this(old.getX(), old.getY());
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
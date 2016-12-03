package com.chasehaddleton.adventofcode.y2016;

import java.util.ArrayList;
import java.util.Scanner;

/*
LLULLLRLDLLLRLUURDDLRDLDURULRLUULUDDUDDLLLURRLDRRLDRRRLDUDLRDLRRDLLDUDUDUDRLUDUUDLLLRDURUDUULUDLRDUUUDUUDURLDUULLRDLULDUURUDRDDLDRLURLRURRDUURLRLUURURUUULLRLLULRUURLULURDLLRRUDLUDULDRDRLRULUURRDRULLRUUUDLRLDLUURRRURDLUDDRRUDRLUDRDLLLLLRULLDUDRLRRDDULDLRUURRRRRLDLDLRDURDRUUURDLRDDDDULURRRRDUURLULLLDLRULRDULRUDLRRLRDLLRLLLUDDLRDRURDDLLLLDUDRDLRURRDLRDDDLDULDRLRULUUDRRRUUULLLURRDDUULURULDURRLLULLDRURUUULRLRDRRUDRDRRDURRUUUULDRDDDUDLDDURLLRR
LDLRRRUURDLDDRLRRDLLULRULLLUDUUDUDLRULLDRUDRULLDULURDRDDLRURDDULLLLDLRDRDRDDURLURLURLUDRDDRDULULUDDRURRDLLDUURDRDDLRLLURRDLRDDULDLULURDRDLUDRRUUDULLULURRDUDRUUUDRULDLDURLRRUDURLDLRRUURRRURDLUDRLDUDRRUDUURURUDDUUDRDULRDLUDRRRLDRURLLRDDDLUDRDUDURDDDRRDDRRRLLRRDDLDDLRUURRURDLLDRLRRDLLUDRRRURURLRDRLLRLRLRULLRURLDLRRULLRRRDULUUULDRDLLURDDLDLRDRLUUDLLUDDLDRRLDLRUDRUDLLUURLLULURUDUDRLULLUDRURDDLDLDDUDLRDDRRURLRLLUDDUDRUURRURRULDRLDDRLLRRLDDURRDLDULLLURULLLRUURLRRRRUUULRLLLURRLRLRUDRDUUUDUUUDDLULLDLLLLDLDRULDRUUULDDDLURLDLRLULRUDDDDURDDLU
RURLURRDLDULLULDDDLRUULLUURLRUDRUDRRUDDLDDDDRRDLRURLRURLDDDUDDUURRDRULDRRRULRDRDDLRUDULRLURDUUDRRLDLRDRURDLDRRRRDRURUUDDDLLRDRDUDUDUDLLULURULRRLRURUULUULDDDDURULRULLRUDUURLURDUDLUDLUDRLLDUUDUULRLRLUUDRDULDULRURDRRRULRUDLRURDDULUDULLRLRURURUULLULDRURLLRRUUDDUUURRDLURUURULRDRRDDUDULRDDLUDLURURUURDRULLRDDLLRDDLDRDUDRRDLUURRLRLUURRULUDURLDDRLLURRDDDLDDRURULLDDRLUDDLRLURDUDULLRDULLLDLLUDDRUDRUDDUUDRDRULRL
RLRDRDULULUDLUDRDRLUDLDLLUDURULDDDUDLRURLLRLRLDLDRLDURDLRRURLULLULURLLDRRDRLUDRLRDLLULRULURRURURUULRDUDLLRDLRRRRRLUURDRRRDLRUDLLDLLDLRUUUDLLLDDDLRDULLRUUDDRLDDURRRDLRLRLDDDDLRDRULLUURUUDRRLLRLLRDDLLRURRRRDRULRRLLRLLLRLDRRLDDDURRURLDURUURRLRLRLDRURULLRLRUDLDUURDLLRLDLURUUUDLLRDRDDDDDDRLDRRRLRRRRURUDLDDRDLLURUDLRRLDDDLUDUDUULRDULULUDDULUUDLLLLRLDDUUULRLRDULURDURRRURRULURRRDRDLDDURDLURUDURRRDDRLRLUDLUDDLUULLDURLURDDUDDLRUUUDRLLDRURL
ULUDLLUDDULRUURDRURDUDUDLUURDDDRRLUDURURDRURRLDRDURLRLLRRDDRRDRRRUULURUDURUDULRRRRDDLDURRLRRDUDDDRLLLULDRLRLURRDUURDURRRURRDLUDUDDRLDLURRRDDRLLRDRDDRDURRRRLURRLUDDURRULRUDUDULDRUDDRULLUUULDURRRLDRULLURULLRUDLDUDDLDULDLUUDRULULDLLDRULLRUULDUDUUDRLRRLDLUULUDLLDDRLRRDDLLURURDULRRDDRURDRLRLULDLDURULLUUUDURURDLDUDDDDUUULUDLUURRULLDLRLURDLURLRLDDURRLDDRRRDUUULLUULDLLDLLDDRLRRUDLULDRLULDULULRRLRULUUURURUUURDUUDDURLLUDDRLRDDLUURRUULRDLDDRLULUULRDRURLUURDRDUURUDLRR
*/
// 65556
// CB779

public class dayTwo {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<String> input = new ArrayList<>();

    public void solveQ1() {
        readInput();
        System.out.println(output1());
    }

    public void solveQ2() {
        readInput();
        System.out.println(output2());
    }

    private void readInput() {
        while (true) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) break;

            input.add(line);
        }
    }

    private String output1() {
        StringBuilder out = new StringBuilder();
        KeyPadPos pos = new KeyPadPos();

        for (String cur : input) { // determine each key press
            for (char ch : cur.toCharArray()) {
                pos.move(ch); // move for each command
            }
            out.append(pos.toNum()); // add the final key to the output
        }

        return out.toString();
    }

    private String output2() {
        StringBuilder out = new StringBuilder();
        TrianglePadPos pos = new TrianglePadPos();

        for (String cur : input) { // determine each key press
            for (char ch : cur.toCharArray()) {
                pos.move(ch); // move for each command
            }
            out.append(pos.toKey()); // add the final key to output
        }

        return out.toString();
    }
}

class TrianglePadPos {
    private int x;
    private int y;
    private String[][] key = {{"", "", "1", "", ""}, {"", "2", "", "4", ""}, {"5", "6", "7", "8", "9"}, {"", "A", "B", "C", ""}, {"", "", "D", "", ""}};

    public TrianglePadPos() {
        // we start at the number 5
        this.x = 0;
        this.y = 2;
    }

    void move(char direction) {
        int newX = x;
        int newY = y;

        // moves us to the new spot, making sure we don't leave the array bounds
        switch (direction) {
            case 'L':
                if (x - 1 >= 0) --newX;
                break;
            case 'R':
                if (x + 1 <= 4) ++newX;
                break;
            case 'U':
                if (y - 1 >= 0) --newY;
                break;
            case 'D':
                if (y + 1 <= 4) ++newY;
                break;
        }

        if (key[newY][newX].isEmpty()) return;  // if we would move to an unreachable spot, don't move there

        // move ot the reachable spot
        x = newX;
        y = newY;
    }

    String toKey() {
        return key[y][x];
    }
}

class KeyPadPos {
    private int x;
    private int y;

    public KeyPadPos() {
        this.x = 0;
        this.y = 0;
    }

    void move(char direction) {
        // moves us to the new spot, making sure we don't leave the bounds
        switch (direction) {
            case 'L':
                if (x - 1 >= -1) --x;
                break;
            case 'R':
                if (x + 1 <= 1) ++x;
                break;
            case 'U':
                if (y - 1 >= -1) --y;
                break;
            case 'D':
                if (y + 1 <= 1) ++y;
                break;
        }
    }

    int toNum() {
        switch (y) {
            case -1:
                switch (x) {
                    case -1:
                        return 1;
                    case 0:
                        return 2;
                    case 1:
                        return 3;
                }
            case 0:
                switch (x) {
                    case -1:
                        return 4;
                    case 0:
                        return 5;
                    case 1:
                        return 6;
                }
            case 1:
                switch (x) {
                    case -1:
                        return 7;
                    case 0:
                        return 8;
                    case 1:
                        return 9;
                }
        }

        return -1;
    }
}
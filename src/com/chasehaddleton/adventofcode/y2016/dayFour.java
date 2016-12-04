package com.chasehaddleton.adventofcode.y2016;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// dayFour.in
// 361724
// 482

public class dayFour {
    private ArrayList<Room> input = new ArrayList<>();

    public void solveQ1() {
        try (Scanner sc = new Scanner(new FileInputStream("/Users/chasehaddleton/Documents/Programming/Advent of Code 2016/src/com/chasehaddleton/adventofcode/y2016/dayFour.in"))) {
            readInput1(sc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(output1());
    }

    public void solveQ2() {
        try (Scanner sc = new Scanner(new FileInputStream("/Users/chasehaddleton/Documents/Programming/Advent of Code 2016/src/com/chasehaddleton/adventofcode/y2016/dayFour.in"))) {
            readInput2(sc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(output2());
    }

    private void readInput1(Scanner sc) {
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim(); // Split the input into an array of the numbers

            input.add(new Room(line)); // Create the new triangle
        }
    }

    private void readInput2(Scanner sc) {
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim(); // Split the input into an array of the numbers

            input.add(new Room(line)); // Create the new triangle
        }
    }

    private String output1() {
        int count = 0;

        for (Room cur : input) { // Count triangles
            count += (cur.checksumIsValid()) ? cur.getSectorId() : 0;
        }

        return String.valueOf(count);
    }

    private String output2() {
        int count = 0;

        for (Room cur : input) { // Count triangles
            if (cur.decryptName().contains("north")) {
                count = cur.getSectorId();
                break;
            }
        }

        return String.valueOf(count);
    }
}

class Room {
    private String checksum, name;
    private int sectorId;

    public Room(String checksum, String name, int sectorId) {
        this.checksum = checksum;
        this.name = name;
        this.sectorId = sectorId;
    }

    public Room(String line) {
        checksum = line.substring(line.indexOf("[") + 1, line.length() - 1);
        sectorId = Integer.parseInt(line.substring(line.lastIndexOf("-") + 1, line.indexOf("[")));
        name = line.substring(0, line.length() - checksum.length() - String.valueOf(sectorId).length() - 3);
    }

    public int getSectorId() {
        return sectorId;
    }

    String decryptName() {
        StringBuilder out = new StringBuilder();

        // shift each letter, or convert to a space
        for (char ch : name.toCharArray()) {
            if (ch == '-') {
                out.append(' ');
            } else {
                // We can make our lives easier by taking the modulus of the sector ID, as any time we shift 26 + r
                // times, where 0 < r < 26, we could have just shifted r times
                char val = (char) ((int) ch + (sectorId % 26));

                if (val > 'z') {
                    out.append((char) ((int) val - 26)); // make sure it's still in the right range
                } else {
                    out.append(val);
                }
            }
        }

        return out.toString();
    }

    boolean checksumIsValid() {
        HashMap<Character, Integer> map = new HashMap<>();

        // count the occurrences of each character
        for (Character cur : name.toCharArray()) {
            Integer count = map.get(cur);

            if (count == null) count = 0;

            map.put(cur, ++count);
        }

        List<Character> keys = map.entrySet().stream()
                .filter(ele -> !(ele.getKey() == '-')) // Remove the dashes
                .sorted((o1, o2) -> { // sort based off highest value first, then alphabetically by key
                    if (o1.getValue() < o2.getValue()) return 1;
                    if (o1.getValue() > o2.getValue()) return -1;
                    if (o1.getKey() < o2.getKey()) return -1;
                    if (o1.getKey() > o2.getKey()) return 1;
                    return 0;

                })
                .map(Map.Entry::getKey) // get each key
                .collect(Collectors.toList()); // collect into a list

        StringBuilder out = new StringBuilder();

        // since the keys are the right order for the checksum, we can just grab as many as we need
        for (int i = 0; i < checksum.length(); i++) {
            out.append(keys.get(i));
        }

        return out.toString().equals(checksum);
    }
}
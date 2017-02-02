package com.chasehaddleton.adventofcode.y2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// in/dayTen.in
//
//

public class dayTen extends AdventOfCode {
    private ArrayList<String> input = new ArrayList<>();
    private HashMap<Integer, Bot> map = new HashMap<>();

    private Pattern botBotBotGive = Pattern.compile("bot ([\\d]+) gives low to bot ([\\d]+) and high to bot ([\\d]+)");
    private Pattern botOutputBotGive = Pattern.compile("bot (\\d+) gives low to output (\\d+) and high to bot (\\d+)");
    private Pattern botOutputOutputGive = Pattern.compile("bot (\\d+) gives low to output (\\d+) and high to output (\\d+)");
    private Pattern newVal = Pattern.compile("value (\\d+) goes to bot (\\d+)");

    void readInput(Scanner sc) {
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
    }

    String output1() {
        int count = 0;

        for (String st : input) {
            Matcher bBBG = botBotBotGive.matcher(st);
            if (bBBG.find()) {
                Bot cur = getBot(Integer.parseInt(bBBG.group(1)));
                Bot lowBot = getBot(Integer.parseInt(bBBG.group(2)));
                Bot highBot = getBot(Integer.parseInt(bBBG.group(3)));

                Chip high = cur.getHigh();
                Chip low = cur.getLow();

                lowBot.add(low);
                highBot.add(high);

                cur.remove();

                map.put(cur.getId(), cur);
                map.put(lowBot.getId(), cur);
                map.put(highBot.getId(), cur);

                continue;
            }

            Matcher bOBG = botOutputBotGive.matcher(st);
            if (bOBG.find()) {
                Bot cur = getBot(Integer.parseInt(bOBG.group(1)));
                Bot highBot = getBot(Integer.parseInt(bOBG.group(3)));

                Chip high = cur.getHigh();
                Chip low = cur.getLow();

                if (high == null || low == null) {
                    continue;
                }

                highBot.add(high);

                cur.remove();

                map.put(cur.getId(), cur);
                map.put(highBot.getId(), cur);

                continue;
            }

            Matcher bOOG = botOutputOutputGive.matcher(st);
            if (bOOG.find()) {
                Bot cur = getBot(Integer.parseInt(bOOG.group(1)));

                Chip high = cur.getHigh();
                Chip low = cur.getLow();

                if (high == null || low == null) {
                    continue;
                }

                cur.remove();

                map.put(cur.getId(), cur);

                continue;
            }

            Matcher nV = newVal.matcher(st);
            if (nV.find()) {
                Chip newChip = new Chip(Integer.parseInt(nV.group(1)));
                Bot cur = getBot(Integer.parseInt(nV.group(2)));

                cur.add(newChip);

                map.put(cur.getId(), cur);

                continue;
            }

            System.err.println("Somehow nothing matched... oops...");
            System.err.println("Error on: " + st);
        }


        return String.valueOf(count);
    }

    String output2() {
        return null;
    }

    private Bot getBot(int id) {
        Bot cur = map.get(id);

        if (cur == null) {
            cur = new Bot(id);
        }

        return cur;
    }
}

class Bot {
    private Chip[] chips;
    private int id;

    public Bot(int id) {
        this.id = id;
        chips = new Chip[2];
    }

    void add(Chip chip) {
        if (chips[0] == null) {
            chips[0] = chip;
        } else {
            chips[1] = chip;
        }
    }

    Chip getChip(int chipNum) {
        if (chips[0] != null && chips[0].getId() == chipNum) return chips[0];

        if (chips[1] != null && chips[1].getId() == chipNum) return chips[1];

        return null;
    }

    Chip getLow() {
        if (chips[0] != null && chips[1] != null && chips[0].getId() > chips[1].getId()) return chips[1];

        if (chips[0] != null && chips[1] != null && chips[0].getId() < chips[1].getId()) return chips[0];

        return null;
    }

    Chip getHigh() {
        if (chips[0] != null && chips[1] != null && chips[0].getId() > chips[1].getId()) return chips[0];

        if (chips[0] != null && chips[1] != null && chips[0].getId() < chips[1].getId()) return chips[1];

        return null;
    }

    void remove() {
        chips[0] = null;
        chips[1] = null;
    }

    public int getId() {
        return id;
    }
}

class Chip {
    private int val;

    public Chip(int val) {
        this.val = val;
    }

    public int getId() {
        return val;
    }
}

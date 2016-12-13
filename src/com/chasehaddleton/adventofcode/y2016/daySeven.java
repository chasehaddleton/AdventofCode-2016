package com.chasehaddleton.adventofcode.y2016;

import java.util.ArrayList;
import java.util.Scanner;

// in/daySeven.in
// 118
// 260

public class daySeven extends AdventOfCode {
    private ArrayList<IP> input = new ArrayList<>();

    void readInput(Scanner sc) {
        while (sc.hasNextLine()) {
            input.add(new IP(sc.nextLine()));
        }
    }

    String output1() {
        return String.valueOf(input.stream().filter((ip) -> (!ip.hypernetAbba() && ip.abba())).count());

    }

    String output2() {
        return String.valueOf(input.stream().filter((ip) -> (ip.aba())).count());
    }
}

class IP {
    private String address, hypernets;

    public IP(String line) {
        StringBuilder temp = new StringBuilder();
        // Break the address into sections
        String[] groups = line.split("([\\[\\]])");

        for (int i = 0; i < groups.length; i++) {
            // Each hypernet must be proceeded by an address sequence, thus every other element in the array is a hypernet
            if (i % 2 == 1) {
                temp.append(groups[i]).append(" ");
            }
        }
        // Set the hypernet equal to the resulting string
        hypernets = temp.toString().trim();

        // Remove all hypernets from the address
        address = line.replaceAll("\\[([\\w]*)\\]", " ");

    }

    boolean abba() {
        // Search for abbas in the address
        return searchForAbba(address);
    }

    boolean hypernetAbba() {
        // Search for abbas in the hypernet
        return searchForAbba(hypernets);
    }

    boolean aba() {
        ArrayList<String> aba = new ArrayList<>();

        // Search for all the abas in the string
        for (int i = 0; i < address.length() - 2; i++) {
            StringBuilder temp = new StringBuilder();
            temp.append(address.substring(i, i + 3));

            if (temp.reverse().toString().equals(address.substring(i, i + 3)) && temp.charAt(0) != address.charAt(i + 1)) {
                // We found one!
                aba.add(temp.toString());
            }
        }

        // Check if the hypernet has a matching aba
        return aba.stream().anyMatch((match) -> (hypernets.contains("" + match.charAt(1) + match.charAt(0) + match.charAt(1))));
    }

    private boolean searchForAbba(String search) {
        // Search for abbas in the search string
        for (int i = 0; i < search.length() - 3; i++) {
            StringBuilder temp = new StringBuilder();
            temp.append(search.substring(i, i + 2));

            if (temp.reverse().toString().equals(search.substring(i + 2, i + 4)) && temp.charAt(1) != search.charAt(i + 2)) {
                // We found one!
                return true;
            }
        }

        return false;
    }
}
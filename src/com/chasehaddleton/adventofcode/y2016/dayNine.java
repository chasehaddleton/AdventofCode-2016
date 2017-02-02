package com.chasehaddleton.adventofcode.y2016;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// in/dayNine.in
// 74532
//

public class dayNine extends AdventOfCode {
    String input;

    void readInput(Scanner sc) {
        input = sc.nextLine();
    }

    String output1() {
        int out = 0;
        boolean duplicationGroup = false;
        int numberOfCharToRecord = 0;
        boolean recordRepetitionNumbers = false;
        StringBuilder numberOfCharToRecordS = new StringBuilder();
        StringBuilder numberOfTimeToRepeat = new StringBuilder();

        for (char cur : input.toCharArray()) {
            if (numberOfCharToRecord == 0 && cur == '(') {
                duplicationGroup = true;
                numberOfCharToRecord = 0;
                numberOfCharToRecordS = new StringBuilder();
                numberOfTimeToRepeat = new StringBuilder();
                continue;
            } else if (numberOfCharToRecord == 0 && cur == ')') {
                duplicationGroup = false;
                recordRepetitionNumbers = false;
                numberOfCharToRecord = Integer.parseInt(numberOfCharToRecordS.toString());
                continue;
            }

            if (duplicationGroup) {
                if (cur == 'x') {
                    recordRepetitionNumbers = true;
                    continue;
                }

                if (!recordRepetitionNumbers) {
                    numberOfCharToRecordS.append(cur);
                } else {
                    numberOfTimeToRepeat.append(cur);
                }
            } else if (numberOfCharToRecord > 0) {
                if (numberOfCharToRecord-- - 1 == 0) {
                    numberOfCharToRecord = 0;
                    out += Integer.parseInt(numberOfCharToRecordS.toString()) * Integer.parseInt(numberOfTimeToRepeat.toString());
                }
            } else {
                ++out;
            }
        }

        return String.valueOf(out);
    }

    String output2() {
        long length = input.replaceAll("[()0-9x]", "").length();

        Matcher decompress = Pattern.compile("\\((\\d+)x(\\d+)\\)").matcher(input);

        if (decompress.find()) {
            int i = 1;

            while (i < decompress.groupCount()) {
                int numToRepeat = Integer.parseInt(decompress.group(i++));
                int numRepeat = Integer.parseInt(decompress.group(i));


            }
        }

        return String.valueOf(length);
    }
}
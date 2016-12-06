package com.chasehaddleton.adventofcode.y2016;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

// in/dayFive.in
// d4cd2ee1
// f2c730e5

public class dayFive extends AdventOfCode {
    private String input;

    void readInput(Scanner sc) {
        while (sc.hasNextLine()) {
            input = sc.nextLine();
        }
    }

    String output1() {
        StringBuilder out = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            int count = -1;
            while (true) {
                // Create the hash and convert it to a string
                String hash = convertByteArrayToHexString(md.digest((input + Integer.toString(++count)).getBytes("UTF-8")));

                // Check if it meets the requirements
                if (hash.substring(0, 5).equals("00000")) {
                    out.append(hash.charAt(5));

                    // Check if the passcode is finished
                    if (out.length() == 8) break;
                }
            }
        } catch (NoSuchAlgorithmException | NullPointerException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        return out.toString();
    }

    String output2() {
        Character[] out = new Character[8];
        char replaceChar = '!';

        Arrays.fill(out, replaceChar);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            int count = -1, passCharCount = 0;
            while (true) {
                // Create the hash and convert it to a string
                String hash = convertByteArrayToHexString(md.digest((input + Integer.toString(++count)).getBytes("UTF-8")));

                // Check if it meets the requirements
                if (hash.substring(0, 5).equals("00000") && hash.charAt(5) >= '0' && hash.charAt(5) <= '7' && out[Character.getNumericValue(hash.charAt(5))] == replaceChar) {
                    // Assign the character to the right position
                    out[Character.getNumericValue(hash.charAt(5))] = hash.charAt(6);

                    // Check if we've finished the passcode
                    if (++passCharCount == 8) break;
                }
            }
        } catch (NoSuchAlgorithmException | NullPointerException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        // Assemble the passcode into a string
        StringBuilder outSt = new StringBuilder();
        for (Character ch : out) {
            outSt.append(ch);
        }
        return outSt.toString();
    }

    // http://www.codejava.net/coding/how-to-calculate-md5-and-sha-hash-values-in-java
    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte bt : arrayBytes) {
            stringBuffer.append(Integer.toString((bt & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }
}
package com.chasehaddleton.adventofcode.y2016;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

// dayFour.in
// d4cd2ee1
// f2c730e5

public class dayFive extends AdventOfCode {
    String input;

    void readInput(Scanner sc) {
        while (sc.hasNextLine()) {
            input = sc.nextLine();
        }
    }

    String output1() {
        MessageDigest md;

        StringBuilder out = new StringBuilder();

        try {
            md = MessageDigest.getInstance("MD5");

            int count = -1;
            while (true) {
                byte[] bytesOfMessage;

                bytesOfMessage = (input + Integer.toString(++count)).getBytes("UTF-8");

                String hash = convertByteArrayToHexString(md.digest(bytesOfMessage));

                if (hash.substring(0, 5).equals("00000")) {
                    out.append(hash.charAt(5));
                    if (out.length() == 8) break;
                }
            }
        } catch (NoSuchAlgorithmException | NullPointerException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        return out.toString();
    }

    String output2() {
        MessageDigest md;
        Character[] out = new Character[8];

        Arrays.fill(out, '!');

        try {
            md = MessageDigest.getInstance("MD5");

            int count = -1, i = 0;
            while (true) {
                byte[] bytesOfMessage;

                bytesOfMessage = (input + Integer.toString(++count)).getBytes("UTF-8");

                String hash = convertByteArrayToHexString(md.digest(bytesOfMessage));

                if (hash.substring(0, 5).equals("00000") && hash.charAt(5) >= '0' && hash.charAt(5) <= '7' && out[Character.getNumericValue(hash.charAt(5))] == '!') {
                    out[Character.getNumericValue(hash.charAt(5))] = hash.charAt(6);
                    if (++i == 8) break;
                }
            }
        } catch (NoSuchAlgorithmException | NullPointerException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

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



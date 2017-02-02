package com.chasehaddleton.adventofcode.y2016;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// in/dayFourteen.in
//
//

public class dayFourteen extends AdventOfCode {
    private String nonce;
    private HashMap<Integer, String> hashes = new HashMap<>();

    void readInput(Scanner sc) {
        nonce = sc.nextLine();
    }

    String output1() {
        int pos = 0;
        boolean notFound = false;

        /*while (!notFound) {
            generateHashes(pos, pos + 2000);
            pos += 2000;

            Objects matches = hashes.entrySet().stream()
                    .filter(hash -> Pattern.compile("([a-z\\d])\\1\\1").matcher(hash.getValue()).find())
                    .map(result -> ).collect(Collectors.toList());
        }*/


        return null;
    }

    void generateHashes(int start, int num) {
        MessageDigest md;
        /*try {
            md = MessageDigest.getInstance("MD5");
            IntStream.rangeClosed(start, num).forEach(x -> hashes.put(x, convertByteArrayToHexString(md.digest((nonce + Integer.toString(x)).getBytes("UTF-8")))));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }*/
    }

    String output2() {
        return null;
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

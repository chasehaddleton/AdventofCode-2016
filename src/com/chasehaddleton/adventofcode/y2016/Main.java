package com.chasehaddleton.adventofcode.y2016;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int[] num2 = {2824, 1774, -1490, -9084, -9696, 23094};
        System.out.println(findEvenIndex(num2));
    }

    public static int findEvenIndex(int[] arr) {
        if (arr.length < 3) {
            return -1;
        }

        for (int i = 1; i < arr.length - 1; i++) {
            if (IntStream.of(subArr(arr, -1, i - 1)).sum() == IntStream.of(subArr(arr, i, arr.length - 1)).sum()) {
                return i;
            }
            System.out.println(IntStream.of(subArr(arr, i, arr.length - 1)).sum() + " =? " + IntStream.of(subArr(arr, 0, i)).sum());
        }

        return -1;
    }

    public static int[] subArr(int[] arr, int start, int end) {
        int[] out = new int[end - start];

        for (int i = 0; i < out.length; i++) {
            out[i] = arr[i + start + 1];
        }

        return out;
    }
}

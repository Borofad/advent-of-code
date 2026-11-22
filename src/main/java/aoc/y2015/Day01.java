package aoc.y2015;

import aoc.util.Input;

import java.util.List;

public class Day01 {
    public static void main(String[] args) {
        List<String> input = Input.lines(2015, 1);

        System.out.println(findFinalFloor(input.get(0)));
        System.out.println(findFirstPosOfBasement(input.get(0)));
    }

    private static int findFirstPosOfBasement(String input) {
        char[] inputArr = input.toCharArray();
        int res = 0;
        for (int i = 0; i < inputArr.length; i++) {
            char c = inputArr[i];
            if (c == '(') {
                res++;
            } else {
                res--;
            }

            if (res == -1) {
                return i + 1;
            }
        }

        return -1;
    }

    private static int findFinalFloor(String input) {
        int res = 0;
        for (char c : input.toCharArray()) {
            if (c == '(') {
                res++;
            } else {
                res--;
            }
        }

        return res;
    }
}

package aoc.y2015;

import aoc.util.Input;

import java.util.List;

public class Day05 {
    private static final String VOWELS = "aeiou";
    private static final String FORBIDDEN_CONSECUTIVE = "bdqy";

    public static void main(String[] args) {
        List<String> input = Input.lines(2015, 5);

        System.out.println(countNiceStrings1(input));
        System.out.println(countNiceStrings2(input));
    }

    private static int countNiceStrings2(List<String> input) {
        int res = 0;
        for (String s : input) {
            if (isNiceString2(s)) {
                res++;
            }
        }

        return res;
    }

    private static boolean isNiceString2(String s) {
        boolean niceTriplet = false;
        int[][] freq = new int[26][26];
        boolean pairExistsTwice = false;
        for (int i = 0; i < s.length() - 1; i++) {
            if (i < s.length() - 2 && s.charAt(i) == s.charAt(i + 2)) {
                niceTriplet = true;
            }

            int currCharIdx = s.charAt(i) - 'a';
            int nextCharIdx = s.charAt(i + 1) - 'a';
            freq[currCharIdx][nextCharIdx]++;
            if (currCharIdx == nextCharIdx) {
                if ((i > 1 && s.charAt(i - 1) - 'a' == currCharIdx && freq[currCharIdx][nextCharIdx] >= 3)
                        || (i > 1 && s.charAt(i - 1) - 'a' != currCharIdx && freq[currCharIdx][nextCharIdx] >= 2)) {

                    pairExistsTwice = true;
                }
            } else if (freq[currCharIdx][nextCharIdx] >= 2) {
                pairExistsTwice = true;
            }

            if (niceTriplet && pairExistsTwice) {
                break;
            }
        }

        return niceTriplet && pairExistsTwice;
    }

    private static int countNiceStrings1(List<String> input) {
        int res = 0;
        for (String s : input) {
            if (isNiceString1(s)) {
                res++;
            }
        }

        return res;
    }

    private static boolean isNiceString1(String s) {
        int vowelCount = 0;
        boolean twiceInARow = false;
        char prev = '\0';
        for (char c : s.toCharArray()) {
            if (VOWELS.indexOf(c) != -1) {
                vowelCount++;
            }

            if (prev == c) {
                twiceInARow = true;
            }

            if (FORBIDDEN_CONSECUTIVE.indexOf(c) != -1 && prev == c - 1) {
                return false;
            }

            prev = c;
        }

        return vowelCount >= 3 && twiceInARow;
    }
}
